import hkj.sisca.auxiliary.Authorization;
import hkj.sisca.auxiliary.Tag;
import hkj.sisca.cas.communication.manager.CommunicationManagerCAS;
import hkj.sisca.cas.communication.manager.CommunicationManagerCAS.TagListUpdateContainer;
import hkj.sisca.cas.communication.manager.CommunicationManagerCAS.TagUpdateListName;
import hkj.sisca.cas.communication.manager.CommunicationManagerCAS.TagUpdateType;
import hkj.sisca.cas.communication.manager.CommunicationManagerConstants.ClientType;
import hkj.sisca.utilities.ClientSocket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;
import databases.DBManager;


/** Permission Manager
 * 	Manage Permission View, Permission Information View, Add Permission View and Permission SAD View
 *  @author Juan Pablo Bermœdez Reyes
 *  Last Modified: April 6, 2014
 */
public class PermissionManager {

	/**
	 * Fields
	 */
	static DBManager dbman;
	static ArrayList<Object> availablePermission;
	static DefaultListModel availablePermissionModelList;
	static Boolean updateJList=true;
	private static JList PermissionList;
	static Boolean isSpecialPermission=false;
	private static JList SpecialPermissionList;
	private static Boolean updateSpecialJList=true;
	private static ArrayList<Object> availableSpecialPermission;
	private static DefaultListModel availableSpecialPermissionModelList;
	private static ArrayList<String> currentPermissionInfo;
	private static String speacial_permission_id;

	/**
	 * Constructor
	 */
	PermissionManager(){

	}

	//////////////////////////////////////////////////////////////////
	//   Permission View						        //
	//////////////////////////////////////////////////////////////////

	/** Permission View
	 *  Generates the Permission View JPanel 
	 *  @return menuPanelPermission JPanel 
	 */
	static JPanel permissionView(){
		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelPermission = new JPanel();
		menuPanelPermission.setBackground(new Color(255,239,80));
		menuPanelPermission.setPreferredSize(new Dimension(10, 30));
		menuPanelPermission.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(250, 10));
		menuPanelPermission.add(menuOptionsPanel, BorderLayout.WEST);
		menuOptionsPanel.setLayout(null);

		JLabel homeLabel = new JLabel(" Home");
		homeLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		homeLabel.setBounds(0, -11, 68, 53);
		menuOptionsPanel.add(homeLabel);
		homeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(HKJ_SisCA_MainPage.windowPanel); // Wrong Way! =S
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		homeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homeLabel.setForeground((java.awt.Color) null);
		homeLabel.setFont(new Font("Dialog", Font.BOLD, 14));

		JLabel PermissionManagerLabel = new JLabel("Permission");
		PermissionManagerLabel.setOpaque(true);
		PermissionManagerLabel.setBackground(new Color (220,220,220));
		PermissionManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		PermissionManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		PermissionManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PermissionManagerLabel.setForeground((java.awt.Color) null);
		PermissionManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		PermissionManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		PermissionManagerLabel.setBounds(66, -11, 109, 53);
		menuOptionsPanel.add(PermissionManagerLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelPermission.add(logOutPanel, BorderLayout.EAST);
		logOutPanel.setLayout(null);

		JLabel logOutLabel = new JLabel("LogOut");
		logOutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.setActiveUsername(null);
				HKJ_SisCA_MainPage.frame.setContentPane(LogInManager.standByView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		logOutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logOutLabel.setBounds(0, -11, 75, 52);
		logOutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logOutLabel.setForeground((java.awt.Color) null);
		logOutLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		logOutLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		logOutPanel.add(logOutLabel);

		JPanel userNamePanel = new JPanel();
		menuPanelPermission.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel(HKJ_SisCA_MainPage.getActiveUsername());
		userNamePanel.add(userNameLabel, BorderLayout.EAST);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground((java.awt.Color) null);
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));



		/////////////////////////////////////////////////////////
		//Main Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel mainPanelPermission = new JPanel();
		mainPanelPermission.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelPermission.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelPermission.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelPermission.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelPermission.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelPermission.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[1242px]", "[61px][372px]"));

		JPanel searchAndAddPanel = new JPanel();
		searchAndAddPanel.setBackground(new Color(250,250,250));
		centerPanel.add(searchAndAddPanel, "cell 0 0,grow");
		searchAndAddPanel.setLayout(new MigLayout("", "[48px][200][34.00px][][][grow][][][][][][][][][][][][][][][][][][][][][200][][][][]", "[29px]"));

		JLabel searchLabel = new JLabel("Search Permission:");
		searchAndAddPanel.add(searchLabel, "cell 0 0,alignx left,aligny center");
		searchLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));


		////////////////////
		//Update JList by textField and Go Button
		////////////////////


		final JTextField textFieldSearch = new JTextField();
		textFieldSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				availablePermission= new ArrayList<Object>();
				availablePermissionModelList= new DefaultListModel(); 

				//Update del JList
				updateJList= false;
				availablePermissionModelList.clear();
				System.out.println("Hola");
				String searchField="'"+textFieldSearch.getText()+"'";

				System.out.println("text field"+textFieldSearch.getText());
				String query= "Select * from sisca_permission where sisca_permission_active= 'true' and sisca_permission_tag_number ~*"+searchField;
				try {
					dbman= new DBManager();
					availablePermission= dbman.getFromDB(query);
					availablePermission= dbman.getAvailablePermission(availablePermission);
					for(int i=0; i<availablePermission.size(); i++){
						availablePermissionModelList.addElement(availablePermission.get(i));
					}
				} catch (ClassNotFoundException e1) {
					//TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					//TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					//TODO Auto-generated catch block
					e1.printStackTrace();
				}

				PermissionList.setModel(availablePermissionModelList);

			}
		});
		searchAndAddPanel.add(textFieldSearch, "cell 1 0,growx,aligny top");
		textFieldSearch.setColumns(10);

		JButton goButton = new JButton("Go");
		goButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				availablePermission= new ArrayList<Object>();
				availablePermissionModelList= new DefaultListModel(); 

				//Update del JList
				updateJList= false;
				availablePermissionModelList.clear();
				//System.out.println("Hola");
				String searchField="'"+textFieldSearch.getText()+"'";

				System.out.println("text field"+textFieldSearch.getText());
				String query= "Select * from sisca_permission where sisca_permission_active= 'true' and sisca_permission_tag_number ~*"+searchField;
				try {
					dbman= new DBManager();
					availablePermission= dbman.getFromDB(query);
					availablePermission= dbman.getAvailablePermission(availablePermission);
					for(int i=0; i<availablePermission.size(); i++){
						availablePermissionModelList.addElement(availablePermission.get(i));
					}
				} catch (ClassNotFoundException e1) {
					//TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					//TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					//TODO Auto-generated catch block
					e1.printStackTrace();
				}

				PermissionList.setModel(availablePermissionModelList);

			}
		});
		goButton.setPreferredSize(new Dimension(10, 29));
		searchAndAddPanel.add(goButton, "cell 2 0");


		JButton addNewPermissionBtn = new JButton("Add New Permission");
		addNewPermissionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addPermissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		searchAndAddPanel.add(addNewPermissionBtn, "cell 15 0,alignx left,aligny top");





		JLabel lblSearchSpecialPermission = new JLabel("Search Special Permission:");
		lblSearchSpecialPermission.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		searchAndAddPanel.add(lblSearchSpecialPermission, "cell 25 0,alignx trailing");

		final JTextField specialPermissiontextField = new JTextField();
		specialPermissiontextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				availableSpecialPermission= new ArrayList<Object>();
				availableSpecialPermissionModelList= new DefaultListModel(); 

				//Update del SpecialJList
				updateSpecialJList= false;
				availableSpecialPermissionModelList.clear();

				String searchField="'"+specialPermissiontextField.getText()+"'";

				System.out.println("text field"+specialPermissiontextField.getText());
				String query= "Select * from sisca_special_permission where sisca_special_permission_active= 'true' and sisca_special_permission_tag_number ~*"+searchField;
				try {
					dbman= new DBManager();
					availableSpecialPermission= dbman.getFromDB(query);
					availableSpecialPermission= dbman.getAvailableSpecialPermission(availableSpecialPermission);
					for(int i=0; i<availableSpecialPermission.size(); i++){
						availableSpecialPermissionModelList.addElement(availableSpecialPermission.get(i));
					}
				} catch (ClassNotFoundException e1) {
					//TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					//TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					//TODO Auto-generated catch block
					e1.printStackTrace();
				}

				SpecialPermissionList.setModel(availableSpecialPermissionModelList);

			}
		});
		specialPermissiontextField.setColumns(10);
		searchAndAddPanel.add(specialPermissiontextField, "cell 26 0,growx");

		JButton specialPermissionGobutton = new JButton("Go");
		specialPermissionGobutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				availableSpecialPermission= new ArrayList<Object>();
				availableSpecialPermissionModelList= new DefaultListModel(); 

				//Update del SpecialJList
				updateSpecialJList= false;
				availableSpecialPermissionModelList.clear();

				String searchField="'"+specialPermissiontextField.getText()+"'";

				System.out.println("text field"+specialPermissiontextField.getText());
				String query= "Select * from sisca_special_permission where sisca_special_permission_active= 'true' and sisca_special_permission_tag_number ~*"+searchField;
				try {
					dbman= new DBManager();
					availableSpecialPermission= dbman.getFromDB(query);
					availableSpecialPermission= dbman.getAvailableSpecialPermission(availableSpecialPermission);
					for(int i=0; i<availableSpecialPermission.size(); i++){
						availableSpecialPermissionModelList.addElement(availableSpecialPermission.get(i));
					}
				} catch (ClassNotFoundException e1) {
					//TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					//TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					//TODO Auto-generated catch block
					e1.printStackTrace();
				}

				SpecialPermissionList.setModel(availableSpecialPermissionModelList);

			}
		});
		specialPermissionGobutton.setPreferredSize(new Dimension(10, 29));
		searchAndAddPanel.add(specialPermissionGobutton, "cell 27 0");





		JPanel PermissionListPanel = new JPanel();
		PermissionListPanel.setBackground(new Color(250,250,250));

		centerPanel.add(PermissionListPanel, "flowx,cell 0 1,alignx center,growy");
		PermissionListPanel.setLayout(new MigLayout("", "[400][77.00][400]", "[360px,grow]"));

		JScrollPane PermissionScrollPane = new JScrollPane();
		PermissionListPanel.add(PermissionScrollPane, "cell 0 0,grow");


		PermissionList= new JList();
		SpecialPermissionList= new JList();

		if (updateJList== true && updateSpecialJList==true){


			availablePermission= new ArrayList<Object>();
			availablePermissionModelList= new DefaultListModel();

			availableSpecialPermission= new ArrayList<Object>();
			availableSpecialPermissionModelList= new DefaultListModel();

			String query= "Select * from sisca_permission where sisca_permission_active= 'true' ORDER BY sisca_permission_tag_number";
			String query2= "Select * from sisca_special_permission where sisca_special_permission_active= 'true' ORDER BY sisca_special_permission_tag_number";
			try {
				dbman= new DBManager();

				availablePermission= dbman.getFromDB(query);
				availablePermission= dbman.getAvailablePermission(availablePermission);

				availableSpecialPermission= dbman.getFromDB(query2);
				availableSpecialPermission= dbman.getAvailableSpecialPermission(availableSpecialPermission);


				for(int i=0; i<availablePermission.size(); i++){
					availablePermissionModelList.addElement(availablePermission.get(i));
				}

				for(int i=0; i<availableSpecialPermission.size(); i++){
					availableSpecialPermissionModelList.addElement(availableSpecialPermission.get(i));
				}

			} catch (ClassNotFoundException e1) {
				//TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				//TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				//TODO Auto-generated catch block
				e1.printStackTrace();
			}
			PermissionList.setModel(availablePermissionModelList);
			SpecialPermissionList.setModel(availableSpecialPermissionModelList);
		}
		else{
			updateJList=true;
			updateSpecialJList=true;

			PermissionList.setModel(availablePermissionModelList);
			SpecialPermissionList.setModel(availableSpecialPermissionModelList);
		}


		PermissionList.setSelectionForeground(UIManager.getColor("Button.darkShadow"));
		PermissionList.setSelectionBackground(UIManager.getColor("Button.background"));
		PermissionList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){



					String query1= "Select * from sisca_permission where sisca_permission_tag_number ilike"+"'"+(String)PermissionList.getSelectedValue()+"'";
					System.out.println(query1);
					String[] list = null;
					try {
						dbman= new DBManager();
						list = ((DBManager) dbman).getPermissionInfoFromDB(query1);
					} catch (SQLException e1) {
						//TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						//TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("ID del permiso seleccionado"+ list[0]);

					HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView((String)PermissionList.getSelectedValue(), Integer.parseInt(list[0]))); // Wrong Way! =S
					HKJ_SisCA_MainPage.frame.pack();
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			}
		});

		JScrollPane speacialPermissionScrollPane = new JScrollPane();
		PermissionListPanel.add(speacialPermissionScrollPane, "cell 2 0,grow");


		// Special
		SpecialPermissionList.setSelectionForeground(UIManager.getColor("Button.darkShadow"));
		SpecialPermissionList.setSelectionBackground(UIManager.getColor("Button.background"));
		SpecialPermissionList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){



					String query1= "Select * from sisca_special_permission where sisca_special_permission_tag_number ilike"+"'"+(String)SpecialPermissionList.getSelectedValue()+"'";
					System.out.println(query1);
					String[] list = null;
					try {
						dbman= new DBManager();
						list = ((DBManager) dbman).getPermissionInfoFromDB(query1);
					} catch (SQLException e1) {
						//TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						//TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("ID del permiso seleccionado"+ list[0]);

					HKJ_SisCA_MainPage.frame.setContentPane(specialPermissionInformationView((String)SpecialPermissionList.getSelectedValue(), Integer.parseInt(list[0]))); // Wrong Way! =S
					HKJ_SisCA_MainPage.frame.pack();
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			}
		});



		PermissionScrollPane.setViewportView(PermissionList);
		speacialPermissionScrollPane.setViewportView(SpecialPermissionList);





		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelPermission= new JPanel();
		windowPanelPermission.setLayout(new BorderLayout(0, 0));
		windowPanelPermission.add(menuPanelPermission, BorderLayout.NORTH);
		windowPanelPermission.add(mainPanelPermission, BorderLayout.CENTER);


		return windowPanelPermission;
	}

	//////////////////////////////////////////////////////////////////
	// Permission Information View						            //
	//////////////////////////////////////////////////////////////////

	/** Permission Information View
	 *  Generates the Permission Information JPanel
	 *  @param tagNumber Variable for the Permission Tag Number to be view
	 * 	@return menuPanelPermissionInformation JPanel 
	 */
	private static JPanel permissionInformationView( String tagNumber, Integer tagID){


		final String currentTagNumber = tagNumber;
		final Integer currentTagID = tagID;

		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelPermissionInformation = new JPanel();
		menuPanelPermissionInformation.setBackground(new Color(255,239,80));
		menuPanelPermissionInformation.setPreferredSize(new Dimension(10, 30));
		menuPanelPermissionInformation.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(375, 10));
		menuPanelPermissionInformation.add(menuOptionsPanel, BorderLayout.WEST);
		menuOptionsPanel.setLayout(null);

		JLabel homeLabel = new JLabel(" Home");
		homeLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		homeLabel.setBounds(0, -11, 68, 53);
		menuOptionsPanel.add(homeLabel);
		homeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(HKJ_SisCA_MainPage.windowPanel); // Wrong Way! =S
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		homeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homeLabel.setForeground((java.awt.Color) null);
		homeLabel.setFont(new Font("Dialog", Font.BOLD, 14));

		JLabel PermissionManagerLabel = new JLabel("Permission");
		PermissionManagerLabel.setPreferredSize(new Dimension(30, 16));
		PermissionManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		PermissionManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		PermissionManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PermissionManagerLabel.setForeground((java.awt.Color) null);
		PermissionManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		PermissionManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		PermissionManagerLabel.setBounds(66, -11, 95, 53);
		menuOptionsPanel.add(PermissionManagerLabel);

		JLabel listPermissionLabel = new JLabel("Permission Information");
		listPermissionLabel.setOpaque(true);
		listPermissionLabel.setBackground(new Color (220,220,220));
		listPermissionLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String query1= "Select sisca_permission_id from sisca_permission where sisca_permission_tag_number="+"'"+currentTagNumber+"'";

				String[] list = null;
				try {
					dbman= new DBManager();
					list = ((DBManager) dbman).getPermissionInfoFromDB(query1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView(currentTagNumber, Integer.parseInt(list[0])));
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		listPermissionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listPermissionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		listPermissionLabel.setForeground((java.awt.Color) null);
		listPermissionLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		listPermissionLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listPermissionLabel.setBounds(159, -11, 210, 53);
		menuOptionsPanel.add(listPermissionLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelPermissionInformation.add(logOutPanel, BorderLayout.EAST);
		logOutPanel.setLayout(null);

		JLabel logOutLabel = new JLabel("LogOut");
		logOutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.setActiveUsername(null);
				HKJ_SisCA_MainPage.frame.setContentPane(LogInManager.standByView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		logOutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logOutLabel.setBounds(0, -11, 75, 52);
		logOutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logOutLabel.setForeground((java.awt.Color) null);
		logOutLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		logOutLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		logOutPanel.add(logOutLabel);

		JPanel userNamePanel = new JPanel();
		userNamePanel.setPreferredSize(new Dimension(200, 10));
		menuPanelPermissionInformation.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel(HKJ_SisCA_MainPage.getActiveUsername());
		userNamePanel.add(userNameLabel, BorderLayout.EAST);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground((java.awt.Color) null);
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));

		JLayeredPane liveSystemPanel2 = new JLayeredPane();
		liveSystemPanel2.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));


		/////////////////////////////////////////////////////////
		//Left Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel leftPanelPermissionInformation = new JPanel();
		leftPanelPermissionInformation.setBackground(new Color(245,245,245));
		leftPanelPermissionInformation.setPreferredSize(new Dimension(390, 10));
		leftPanelPermissionInformation.setLayout(new BorderLayout(0, 0));

		final JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanelPermissionInformation.add(scrollPane);

		JLayeredPane liveSystemPanel21 = new JLayeredPane();
		scrollPane.setViewportView(liveSystemPanel);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel21.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));



		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px]"));


		//LeftPanel Labels
		JLabel PermissionListLabel = new JLabel("PERMISSIONS LIST");
		liveSystemPanel.add(PermissionListLabel, "cell 0 0,alignx center,aligny top");
		PermissionListLabel.setPreferredSize(new Dimension(100, 50));
		PermissionListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PermissionListLabel.setForeground(java.awt.Color.BLACK);
		PermissionListLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		ArrayList<Object> PermissionUsernameListView = getPermissionList();
		int position = 1;
		for(int i=0; i<PermissionUsernameListView.size() && i<10 ; i++){
			position = position+2;
			final JLabel permissionLabel = new JLabel(PermissionUsernameListView.get(i).toString());
			permissionLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String query1= "Select sisca_permission_id from sisca_permission where sisca_permission_tag_number="+"'"+permissionLabel.getText()+"' and sisca_permission_active='true'";

					String[] list = null;
					try {
						dbman= new DBManager();
						list = ((DBManager) dbman).getPermissionInfoFromDB(query1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView(currentTagNumber, Integer.parseInt(list[0])));
					HKJ_SisCA_MainPage.frame.pack();
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			});
			permissionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			liveSystemPanel.add(permissionLabel, "cell 0 "+ position +" ,alignx left,aligny top");
		}


		//Separators
		JSeparator separator = new JSeparator();
		liveSystemPanel.add(separator, "cell 0 4,growx,aligny top");

		JSeparator separator_1 = new JSeparator();
		liveSystemPanel.add(separator_1, "cell 0 6,growx,aligny top");

		JSeparator separator_2 = new JSeparator();
		liveSystemPanel.add(separator_2, "cell 0 8,growx,aligny top");

		JSeparator separator_3 = new JSeparator();
		liveSystemPanel.add(separator_3, "cell 0 10,growx,aligny top");

		JSeparator separator_4 = new JSeparator();
		liveSystemPanel.add(separator_4, "cell 0 12,growx,aligny top");

		JSeparator separator_5 = new JSeparator();
		liveSystemPanel.add(separator_5, "cell 0 14,growx,aligny top");

		JSeparator separator_7 = new JSeparator();
		liveSystemPanel.add(separator_7, "cell 0 18,growx,aligny top");

		JSeparator separator_6 = new JSeparator();
		liveSystemPanel.add(separator_6, "cell 0 16,growx,aligny top");

		JSeparator separator_8 = new JSeparator();
		liveSystemPanel.add(separator_8, "cell 0 20,growx,aligny top");

		JSeparator separator_9 = new JSeparator();
		liveSystemPanel.add(separator_9, "cell 0 22,growx,aligny top");

		JSeparator separator_10 = new JSeparator();
		liveSystemPanel.add(separator_10, "cell 0 2,growx,aligny top");

		JPanel panel_100 = new JPanel();
		panel_100.setBackground(new Color(245,245,245));
		liveSystemPanel.add(panel_100, "cell 0 1,growx,aligny top");
		panel_100.setLayout(new BoxLayout(panel_100, BoxLayout.X_AXIS));

		JLabel searchLabel = new JLabel("Search:");
		panel_100.add(searchLabel);
		searchLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		final JTextField searchTextField = new JTextField();
		/**
		 * CODIDO DE LA BARRA DE SEARCH
		 */
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Update del JList
				updateJList= false;
				availablePermissionModelList.clear();

				String searchField="'"+searchTextField.getText()+"'";
				String query= "Select * from sisca_permission where sisca_permission_active= 'true' and sisca_permission_tag_number ~*"+searchField;
				try {
					dbman= new DBManager();
					availablePermission= dbman.getFromDB(query);
					availablePermission= dbman.getAvailablePermission(availablePermission);
					for(int i=0; i<availablePermission.size(); i++){
						availablePermissionModelList.addElement(availablePermission.get(i));
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		panel_100.add(searchTextField);
		searchTextField.setColumns(10);

		JPanel viewAndAddBynPanel = new JPanel();
		viewAndAddBynPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(viewAndAddBynPanel, "cell 0 23,alignx left,aligny top");
		viewAndAddBynPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton viewAllButton = new JButton("View All");
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButtonPermission = new JButton("Add New Permission");
		addNewButtonPermission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addPermissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(addNewButtonPermission);

		/////////////////////////////////////////////////////////
		//Main Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel mainPanelPermissionInformation = new JPanel();
		mainPanelPermissionInformation.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelPermissionInformation.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelPermissionInformation.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelPermissionInformation.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelPermissionInformation.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelPermissionInformation.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[849.00px]", "[][12px][][19px][12px][50][][][][50][][][][][][][16.00][][19px][-40.00px]"));

		/////////////////////////////////////////////////////////
		// Query for Fill Permission Information
		/////////////////////////////////////////////////////////
		String processTagID="'"+currentTagID+"'";

		String query= "Select * from ((sisca_permission natural join sisca_vehicle) natural join sisca_applicant) natural join sisca_notification"+
				" where sisca_permission_vehicle_id= sisca_vehicle_id and sisca_permission_applicant_id=sisca_applicant_id and sisca_permission_notification_id=sisca_notification_id "
				+ " and sisca_permission_id = "+processTagID;

		System.out.println("Printing Query:"+query );

		ArrayList<String> availablePermission = null;

		try {
			dbman= new DBManager();
			availablePermission = new ArrayList<String>();
			availablePermission= dbman.getAllPermissionInfoFromDB(query);

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		JLabel lblNewLabelApplicant = new JLabel(availablePermission.get(33)+"\t"+availablePermission.get(34));
		lblNewLabelApplicant.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		centerPanel.add(lblNewLabelApplicant, "cell 0 0,alignx center");

		JLabel lblNewLabelPermissionInformation = new JLabel("PERMISSION INFORMATION");
		lblNewLabelPermissionInformation.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		centerPanel.add(lblNewLabelPermissionInformation, "cell 0 2,alignx left");

		JLabel lblNewLabelTagNumber = new JLabel("Tag Number: "+ availablePermission.get(1));
		centerPanel.add(lblNewLabelTagNumber, "flowx,cell 0 3");

		JLabel lblNewLabel = new JLabel("                            Authorization Type: "+availablePermission.get(14));
		centerPanel.add(lblNewLabel, "cell 0 3");

		JLabel lblNewLabelDeliveryDate = new JLabel("Delivery Date: "+availablePermission.get(8) );
		centerPanel.add(lblNewLabelDeliveryDate, "flowx,cell 0 4");

		JLabel lblNewLabelExpirationDate = new JLabel("                          Expiration Date: " +availablePermission.get(2));
		centerPanel.add(lblNewLabelExpirationDate, "cell 0 4");

		JLabel lblNewLabelNotificationDate = new JLabel("                               Notification Date: " +availablePermission.get(47));
		centerPanel.add(lblNewLabelNotificationDate, "cell 0 4");


		JLabel lblNewLabelApplicantInformation = new JLabel("APLICANT INFORMATION");
		lblNewLabelApplicantInformation.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		centerPanel.add(lblNewLabelApplicantInformation, "cell 0 5,alignx left,aligny bottom");

		JLabel lblNewLabelPhone = new JLabel("Phone: " +availablePermission.get(35));
		centerPanel.add(lblNewLabelPhone, "cell 0 6");

		JLabel lblNewLabelEmail = new JLabel("Email: " +availablePermission.get(36));
		centerPanel.add(lblNewLabelEmail, "cell 0 7");

		if(availablePermission.get(39)== "t"){
			JLabel lblNewLabelHandicap = new JLabel("Handicap Permission? Yes");
			centerPanel.add(lblNewLabelHandicap, "cell 0 8");
		}
		else{
			JLabel lblNewLabelHandicap = new JLabel("Handicap Permission? No");
			centerPanel.add(lblNewLabelHandicap, "cell 0 8");
		}

		JLabel lblNewLabelLicenseNumber = new JLabel("                               License Number: "+ availablePermission.get(37));
		centerPanel.add(lblNewLabelLicenseNumber, "cell 0 8");

		JLabel lblNewLabelVehicleInformation = new JLabel("VEHICLE INFORMATION");
		lblNewLabelVehicleInformation.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		centerPanel.add(lblNewLabelVehicleInformation, "cell 0 9,alignx left,aligny bottom");

		JLabel lblNewLabelVIN = new JLabel("VIN: "+availablePermission.get(16));
		centerPanel.add(lblNewLabelVIN, "cell 0 10");

		JLabel lblNewLabelPlate = new JLabel("Plate: " +availablePermission.get(17));
		centerPanel.add(lblNewLabelPlate, "cell 0 11");

		JLabel lblNewLabelBrand = new JLabel("Brand: "+availablePermission.get(19));
		centerPanel.add(lblNewLabelBrand, "cell 0 12");

		JLabel lblNewLabelModel = new JLabel("Model: "+availablePermission.get(20));
		centerPanel.add(lblNewLabelModel, "cell 0 13");

		JLabel lblNewLabelCountry = new JLabel("Country: "+availablePermission.get(18));
		centerPanel.add(lblNewLabelCountry, "cell 0 14");

		JLabel lblNewLabelYear = new JLabel("Year: "+availablePermission.get(21));
		centerPanel.add(lblNewLabelYear, "cell 0 15");

		JLabel lblNewLabelColor = new JLabel("Color: "+availablePermission.get(22));
		centerPanel.add(lblNewLabelColor, "cell 0 16");

		JLabel lblNewLabelOwnerName = new JLabel("Owner's Name: "+availablePermission.get(23)+"\t"+availablePermission.get(24));
		centerPanel.add(lblNewLabelOwnerName, "cell 0 17");

		JPanel editAndRemovePanel = new JPanel();
		editAndRemovePanel.setBackground(new Color(250,250,250));
		centerPanel.add(editAndRemovePanel, "cell 0 18,grow");
		editAndRemovePanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));


		//////////////////////////////////////////
		// Edit and Remove Buttons
		//////////////////////////////////////


		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(editPermissionView(currentTagNumber, currentTagID));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		editAndRemovePanel.add(editButton, "cell 0 0");

		// REMOVE FRO PERMISSION INFORMATION VIEW
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");


				if(sure==0){



					String query2= "Select * from ((sisca_permission natural join sisca_vehicle) natural join sisca_applicant) natural join sisca_notification"+
							" where sisca_permission_vehicle_id= sisca_vehicle_id and sisca_permission_applicant_id=sisca_applicant_id  and sisca_permission_notification_id=sisca_notification_id"
							+ " and sisca_permission_id = "+"'"+currentTagID+"'";


					ArrayList availablePermission = null;

					try {
						dbman= new DBManager();
						availablePermission= dbman.getAllPermissionInfoFromDB(query2);

					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Calendar cal = Calendar.getInstance();

					String creationDate= "'"+dateFormat.format(cal.getTime())+"'";
					String createdBy= ""+HKJ_SisCA_MainPage.loggedUsernaneWith+"";
					String queryA= "Update sisca_permission SET sisca_permission_active= 'false', sisca_permission_deletedby= "+createdBy+" , sisca_permission_deletedate="+creationDate
							+ " where sisca_permission_id="+"'"+currentTagID+"'";
					String queryB= "Update sisca_vehicle SET sisca_vehicle_active= 'false', sisca_vehicle_deletedby= "+createdBy+" , sisca_vehicle_deletedate="+creationDate
							+ "where sisca_vehicle_id="+"'"+availablePermission.get(15)+"'";
					String queryC= "Update sisca_applicant SET sisca_applicant_active= 'false' , sisca_applicant_deletedby= "+createdBy+" , sisca_applicant_deletedate="+creationDate
							+ "where sisca_applicant_id="+"'"+availablePermission.get(32)+"'";
					String queryD= "Update sisca_notification SET sisca_notification_active= 'false' , sisca_notification_deletedby= "+createdBy+" , sisca_notification_deletedate="+creationDate
							+ "where sisca_notification_id="+"'"+availablePermission.get(46)+"'";

					try {
						dbman= new DBManager();

						dbman.updatetDB(queryA);
						dbman.updatetDB(queryB);
						dbman.updatetDB(queryC);
						dbman.updatetDB(queryD);

						HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
						HKJ_SisCA_MainPage.frame.pack(); 
						HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				else{

					HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView(currentTagNumber,currentTagID));
					HKJ_SisCA_MainPage.frame.pack();
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			}
		});
		editAndRemovePanel.add(btnRemove, "cell 26 0");






		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelPermissionInformation= new JPanel();
		windowPanelPermissionInformation.setLayout(new BorderLayout(0, 0));
		windowPanelPermissionInformation.add(menuPanelPermissionInformation, BorderLayout.NORTH);
		windowPanelPermissionInformation.add(leftPanelPermissionInformation, BorderLayout.WEST);
		windowPanelPermissionInformation.add(mainPanelPermissionInformation, BorderLayout.CENTER);

		return windowPanelPermissionInformation;
	}



	//////////////////////////////////////////////////////////////////
	//   Special Permission Information View					    //
	//////////////////////////////////////////////////////////////////

	/** Special Permission Information View
	 *  Generates the Special Permission Information JPanel
	 *  
	 * 	@return windowPanelSpecialPermissionInformation JPanel 
	 */
	private static  JPanel specialPermissionInformationView(String tagNumber, int special_permission_id) {

		final String currentTagNumber = tagNumber;
		final Integer currentTagID = special_permission_id;

		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelPermissionInformation = new JPanel();
		menuPanelPermissionInformation.setBackground(new Color(255,239,80));
		menuPanelPermissionInformation.setPreferredSize(new Dimension(10, 30));
		menuPanelPermissionInformation.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(400, 10));
		menuPanelPermissionInformation.add(menuOptionsPanel, BorderLayout.WEST);
		menuOptionsPanel.setLayout(null);

		JLabel homeLabel = new JLabel(" Home");
		homeLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		homeLabel.setBounds(0, -11, 68, 53);
		menuOptionsPanel.add(homeLabel);
		homeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(HKJ_SisCA_MainPage.windowPanel); // Wrong Way! =S
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		homeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homeLabel.setForeground((java.awt.Color) null);
		homeLabel.setFont(new Font("Dialog", Font.BOLD, 14));

		JLabel PermissionManagerLabel = new JLabel("Permission");
		PermissionManagerLabel.setPreferredSize(new Dimension(30, 16));
		PermissionManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		PermissionManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		PermissionManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PermissionManagerLabel.setForeground((java.awt.Color) null);
		PermissionManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		PermissionManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		PermissionManagerLabel.setBounds(66, -11, 95, 53);
		menuOptionsPanel.add(PermissionManagerLabel);

		JLabel listPermissionLabel = new JLabel("Special Permission Information");
		listPermissionLabel.setOpaque(true);
		listPermissionLabel.setBackground(new Color (220,220,220));
		listPermissionLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				HKJ_SisCA_MainPage.frame.setContentPane(specialPermissionInformationView(currentTagNumber, currentTagID));
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		listPermissionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listPermissionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		listPermissionLabel.setForeground((java.awt.Color) null);
		listPermissionLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		listPermissionLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listPermissionLabel.setBounds(159, -11, 241, 53);
		menuOptionsPanel.add(listPermissionLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelPermissionInformation.add(logOutPanel, BorderLayout.EAST);
		logOutPanel.setLayout(null);

		JLabel logOutLabel = new JLabel("LogOut");
		logOutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.setActiveUsername(null);
				HKJ_SisCA_MainPage.frame.setContentPane(LogInManager.standByView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		logOutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logOutLabel.setBounds(0, -11, 75, 52);
		logOutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logOutLabel.setForeground((java.awt.Color) null);
		logOutLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		logOutLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		logOutPanel.add(logOutLabel);

		JPanel userNamePanel = new JPanel();
		userNamePanel.setPreferredSize(new Dimension(200, 10));
		menuPanelPermissionInformation.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel(HKJ_SisCA_MainPage.getActiveUsername());
		userNamePanel.add(userNameLabel, BorderLayout.EAST);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground((java.awt.Color) null);
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));

		JLayeredPane liveSystemPanel2 = new JLayeredPane();
		liveSystemPanel2.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));


		/////////////////////////////////////////////////////////
		//cretedBy
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel leftPanelPermissionInformation = new JPanel();
		leftPanelPermissionInformation.setBackground(new Color(245,245,245));
		leftPanelPermissionInformation.setPreferredSize(new Dimension(390, 10));
		leftPanelPermissionInformation.setLayout(new BorderLayout(0, 0));

		final JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanelPermissionInformation.add(scrollPane);

		JLayeredPane liveSystemPanel21 = new JLayeredPane();
		scrollPane.setViewportView(liveSystemPanel);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel21.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));



		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px]"));


		//LeftPanel Labels
		JLabel SpecialPermissionListLabel = new JLabel("SPECIAL PERMISSIONS LIST");
		liveSystemPanel.add(SpecialPermissionListLabel, "cell 0 0,alignx center,aligny top");
		SpecialPermissionListLabel.setPreferredSize(new Dimension(100, 50));
		SpecialPermissionListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SpecialPermissionListLabel.setForeground(java.awt.Color.BLACK);
		SpecialPermissionListLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		ArrayList<Object> PermissionUsernameListView = getSpeciaPermissionList();
		int position = 1;
		for(int i=0; i<PermissionUsernameListView.size() && i<10 ; i++){
			position = position+2;
			final JLabel permissionLabel = new JLabel(PermissionUsernameListView.get(i).toString());
			permissionLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String query1= "Select sisca_special_permission_id from sisca_special_permission where sisca_special_permission_tag_number="+"'"+permissionLabel.getText()+"' and sisca_special_permission_active='true' ";

					String[] list = null;
					try {
						dbman= new DBManager();
						list = ((DBManager) dbman).getPermissionInfoFromDB(query1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					HKJ_SisCA_MainPage.frame.setContentPane(specialPermissionInformationView(permissionLabel.getText(), Integer.parseInt(list[0])));
					HKJ_SisCA_MainPage.frame.pack();
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			});
			permissionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			liveSystemPanel.add(permissionLabel, "cell 0 "+ position +" ,alignx left,aligny top");
		}


		//Separators
		JSeparator separator = new JSeparator();
		liveSystemPanel.add(separator, "cell 0 4,growx,aligny top");

		JSeparator separator_1 = new JSeparator();
		liveSystemPanel.add(separator_1, "cell 0 6,growx,aligny top");

		JSeparator separator_2 = new JSeparator();
		liveSystemPanel.add(separator_2, "cell 0 8,growx,aligny top");

		JSeparator separator_3 = new JSeparator();
		liveSystemPanel.add(separator_3, "cell 0 10,growx,aligny top");

		JSeparator separator_4 = new JSeparator();
		liveSystemPanel.add(separator_4, "cell 0 12,growx,aligny top");

		JSeparator separator_5 = new JSeparator();
		liveSystemPanel.add(separator_5, "cell 0 14,growx,aligny top");

		JSeparator separator_7 = new JSeparator();
		liveSystemPanel.add(separator_7, "cell 0 18,growx,aligny top");

		JSeparator separator_6 = new JSeparator();
		liveSystemPanel.add(separator_6, "cell 0 16,growx,aligny top");

		JSeparator separator_8 = new JSeparator();
		liveSystemPanel.add(separator_8, "cell 0 20,growx,aligny top");

		JSeparator separator_9 = new JSeparator();
		liveSystemPanel.add(separator_9, "cell 0 22,growx,aligny top");

		JSeparator separator_10 = new JSeparator();
		liveSystemPanel.add(separator_10, "cell 0 2,growx,aligny top");

		JPanel panel_100 = new JPanel();
		panel_100.setBackground(new Color(245,245,245));
		liveSystemPanel.add(panel_100, "cell 0 1,growx,aligny top");
		panel_100.setLayout(new BoxLayout(panel_100, BoxLayout.X_AXIS));

		JLabel searchLabel = new JLabel("Search:");
		panel_100.add(searchLabel);
		searchLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		final JTextField searchTextField = new JTextField();
		/**
		 * CODIDO DE LA BARRA DE SEARCH
		 */
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Update del JList
				updateSpecialJList= false;
				availableSpecialPermissionModelList.clear();

				String searchField="'%"+searchTextField.getText()+"%'";
				String query= "Select * from sisca_special_permission where sisca_special_permission_tag_number ilike"+searchField+ " and sisca_special_permission_active='true'";
				try {
					dbman= new DBManager();
					availableSpecialPermission= dbman.getFromDB(query);
					availableSpecialPermission= dbman.getAvailableSpecialPermission(availableSpecialPermission);
					for(int i=0; i<availablePermission.size(); i++){
						availableSpecialPermissionModelList.addElement(availableSpecialPermission.get(i));
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		panel_100.add(searchTextField);
		searchTextField.setColumns(10);

		JPanel viewAndAddBynPanel = new JPanel();
		viewAndAddBynPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(viewAndAddBynPanel, "cell 0 23,alignx left,aligny top");
		viewAndAddBynPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton viewAllButton = new JButton("View All");
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButtonPermission = new JButton("Add New Permission");
		addNewButtonPermission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addPermissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(addNewButtonPermission);

		/////////////////////////////////////////////////////////
		//Main Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel mainPanelPermissionInformation = new JPanel();
		mainPanelPermissionInformation.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelPermissionInformation.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelPermissionInformation.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelPermissionInformation.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelPermissionInformation.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelPermissionInformation.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[59.00][849.00px]", "[][19px][][][][][][][][][16.00][][19px][-40.00px]"));

		/////////////////////////////////////////////////////////
		// Query for Fill Permission Information
		/////////////////////////////////////////////////////////
		String processTagID="'"+currentTagID+"'";

		String query= "Select * from sisca_special_permission where sisca_special_permission_id = "+processTagID;

		System.out.println("Printing Query:"+query );

		ArrayList<String> availablePermission = null;

		try {
			dbman= new DBManager();
			availablePermission = new ArrayList<String>();
			availablePermission= dbman.getAllPermissionInfoFromDB(query);

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblNewLabelTagNumber = new JLabel(availablePermission.get(1));
		lblNewLabelTagNumber.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		centerPanel.add(lblNewLabelTagNumber, "cell 1 0,alignx center");

		JLabel lblNewLabel = new JLabel("Authorization Type: "+availablePermission.get(3));
		centerPanel.add(lblNewLabel, "cell 1 2");

		JLabel lblNewLabelDeliveryDate = new JLabel("Delivery Date: "+availablePermission.get(5) );
		centerPanel.add(lblNewLabelDeliveryDate, "flowx,cell 1 4");

		JLabel lblNewLabelExpirationDate = new JLabel("Expiration Date: " +availablePermission.get(2));
		centerPanel.add(lblNewLabelExpirationDate, "cell 1 6");

		JPanel editAndRemovePanel = new JPanel();
		editAndRemovePanel.setBackground(new Color(250,250,250));
		centerPanel.add(editAndRemovePanel, "cell 1 12,grow");
		editAndRemovePanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));


		//////////////////////////////////////////
		// Edit and Remove Buttons
		//////////////////////////////////////


		//EDIT PERMISSION
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(editSpecialPermissionView(currentTagNumber, currentTagID));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}


		});
		editAndRemovePanel.add(editButton, "cell 0 0");


		// REMOVE SPECIAL PERMISSION
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");


				if(sure==0){

					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Calendar cal = Calendar.getInstance();

					String creationDate= "'"+dateFormat.format(cal.getTime())+"'";
					String createdBy= ""+HKJ_SisCA_MainPage.loggedUsernaneWith+"";
					String queryA= "Update sisca_special_permission SET sisca_special_permission_active= 'false', sisca_special_permission_deletedby= "+createdBy+" , sisca_special_permission_deletedate="+creationDate
							+ " where sisca_special_permission_id="+"'"+currentTagID+"'";

					try {
						dbman= new DBManager();

						dbman.updatetDB(queryA);


						HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
						HKJ_SisCA_MainPage.frame.pack(); 
						HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				else{


					HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView(currentTagNumber, currentTagID));
					HKJ_SisCA_MainPage.frame.pack();
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			}

		});
		editAndRemovePanel.add(btnRemove, "cell 26 0");






		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelPermissionInformation= new JPanel();
		windowPanelPermissionInformation.setLayout(new BorderLayout(0, 0));
		windowPanelPermissionInformation.add(menuPanelPermissionInformation, BorderLayout.NORTH);
		windowPanelPermissionInformation.add(leftPanelPermissionInformation, BorderLayout.WEST);
		windowPanelPermissionInformation.add(mainPanelPermissionInformation, BorderLayout.CENTER);

		return windowPanelPermissionInformation;
	}


	//////////////////////////////////////////////////////////////////
	//   Add Permission View						                //
	//////////////////////////////////////////////////////////////////


	/** Add Permission View
	 *  Generates the Add Permission JPanel
	 *  
	 * 	@return menuPanelAddPermission JPanel 
	 */

	private static JPanel addPermissionView(){



		final JTextField textFieldTagNumber;
		final JTextField textFieldDeliveryDate;
		final JTextField textFieldNotificationDate;
		final JTextField textFieldExpirationDate;
		final JTextField textFieldPhone;
		final JTextField textFieldEmail;
		final JTextField textFieldLicenseNumber;
		final JTextField textFieldVIN;
		final JTextField textFieldPlate;
		final JTextField textFieldBrand;
		final JTextField textFieldModel;
		final JTextField textFieldCountry;
		final JTextField textFieldYear;
		final JTextField textFieldColor;
		final JTextField textFieldOwnerFirstName;
		final JTextField textFieldOwnerLastName;
		final JTextField textFieldAFirstName;
		final JTextField textFieldALastName;

		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelAddPermission = new JPanel();
		menuPanelAddPermission.setBackground(new Color(255,239,80));
		menuPanelAddPermission.setPreferredSize(new Dimension(10, 30));
		menuPanelAddPermission.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(375, 10));
		menuPanelAddPermission.add(menuOptionsPanel, BorderLayout.WEST);
		menuOptionsPanel.setLayout(null);

		JLabel homeLabel = new JLabel(" Home");
		homeLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		homeLabel.setBounds(0, -11, 68, 53);
		menuOptionsPanel.add(homeLabel);
		homeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(HKJ_SisCA_MainPage.windowPanel); // Wrong Way! =S
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		homeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homeLabel.setForeground((java.awt.Color) null);
		homeLabel.setFont(new Font("Dialog", Font.BOLD, 14));

		JLabel PermissionManagerLabel = new JLabel("Permission");
		PermissionManagerLabel.setPreferredSize(new Dimension(30, 16));
		PermissionManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		PermissionManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		PermissionManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PermissionManagerLabel.setForeground((java.awt.Color) null);
		PermissionManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		PermissionManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		PermissionManagerLabel.setBounds(66, -11, 95, 53);
		menuOptionsPanel.add(PermissionManagerLabel);

		JLabel listPermissionLabel = new JLabel("Add Permission");
		listPermissionLabel.setOpaque(true);
		listPermissionLabel.setBackground(new Color (220,220,220));
		listPermissionLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(addPermissionView());
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		listPermissionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listPermissionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		listPermissionLabel.setForeground((java.awt.Color) null);
		listPermissionLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		listPermissionLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listPermissionLabel.setBounds(159, -11, 210, 53);
		menuOptionsPanel.add(listPermissionLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelAddPermission.add(logOutPanel, BorderLayout.EAST);
		logOutPanel.setLayout(null);

		JLabel logOutLabel = new JLabel("LogOut");
		logOutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.setActiveUsername(null);
				HKJ_SisCA_MainPage.frame.setContentPane(LogInManager.standByView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		logOutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logOutLabel.setBounds(0, -11, 75, 52);
		logOutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logOutLabel.setForeground((java.awt.Color) null);
		logOutLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		logOutLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		logOutPanel.add(logOutLabel);

		JPanel userNamePanel = new JPanel();
		userNamePanel.setPreferredSize(new Dimension(200, 10));
		menuPanelAddPermission.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel(HKJ_SisCA_MainPage.getActiveUsername());
		userNamePanel.add(userNameLabel, BorderLayout.EAST);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground((java.awt.Color) null);
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));

		JLayeredPane liveSystemPanel2 = new JLayeredPane();
		liveSystemPanel2.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));


		/////////////////////////////////////////////////////////
		//Left Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel leftPanelAddPermission = new JPanel();
		leftPanelAddPermission.setBackground(new Color(245,245,245));
		leftPanelAddPermission.setPreferredSize(new Dimension(390, 10));
		leftPanelAddPermission.setLayout(new BorderLayout(0, 0));

		final JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanelAddPermission.add(scrollPane);

		JLayeredPane liveSystemPanel21 = new JLayeredPane();
		scrollPane.setViewportView(liveSystemPanel);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel21.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));



		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px]"));


		//LeftPanel Labels
		JLabel PermissionListLabel = new JLabel("PERMISSIONS LIST");
		liveSystemPanel.add(PermissionListLabel, "cell 0 0,alignx center,aligny top");
		PermissionListLabel.setPreferredSize(new Dimension(100, 50));
		PermissionListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PermissionListLabel.setForeground(java.awt.Color.BLACK);
		PermissionListLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		ArrayList<Object> PermissionUsernameListView = getPermissionList();
		int position = 1;
		for(int i=0; i<PermissionUsernameListView.size() && i<10 ; i++){
			position = position+2;
			final JLabel permissionLabel = new JLabel(PermissionUsernameListView.get(i).toString());
			permissionLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String query1= "Select sisca_permission_id from sisca_permission where sisca_permission_tag_number="+"'"+permissionLabel.getText()+"' and sisca_permission_active='true'";

					String[] list = null;
					try {
						dbman= new DBManager();
						list = ((DBManager) dbman).getPermissionInfoFromDB(query1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView(permissionLabel.getText(), Integer.parseInt(list[0])));
					HKJ_SisCA_MainPage.frame.pack();
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			});
			permissionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			liveSystemPanel.add(permissionLabel, "cell 0 "+ position +" ,alignx left,aligny top");
		}


		//Separators
		JSeparator separator = new JSeparator();
		liveSystemPanel.add(separator, "cell 0 4,growx,aligny top");

		JSeparator separator_1 = new JSeparator();
		liveSystemPanel.add(separator_1, "cell 0 6,growx,aligny top");

		JSeparator separator_2 = new JSeparator();
		liveSystemPanel.add(separator_2, "cell 0 8,growx,aligny top");

		JSeparator separator_3 = new JSeparator();
		liveSystemPanel.add(separator_3, "cell 0 10,growx,aligny top");

		JSeparator separator_4 = new JSeparator();
		liveSystemPanel.add(separator_4, "cell 0 12,growx,aligny top");

		JSeparator separator_5 = new JSeparator();
		liveSystemPanel.add(separator_5, "cell 0 14,growx,aligny top");

		JSeparator separator_7 = new JSeparator();
		liveSystemPanel.add(separator_7, "cell 0 18,growx,aligny top");

		JSeparator separator_6 = new JSeparator();
		liveSystemPanel.add(separator_6, "cell 0 16,growx,aligny top");

		JSeparator separator_8 = new JSeparator();
		liveSystemPanel.add(separator_8, "cell 0 20,growx,aligny top");

		JSeparator separator_9 = new JSeparator();
		liveSystemPanel.add(separator_9, "cell 0 22,growx,aligny top");

		JSeparator separator_10 = new JSeparator();
		liveSystemPanel.add(separator_10, "cell 0 2,growx,aligny top");

		JPanel panel_100 = new JPanel();
		panel_100.setBackground(new Color(245,245,245));
		liveSystemPanel.add(panel_100, "cell 0 1,growx,aligny top");
		panel_100.setLayout(new BoxLayout(panel_100, BoxLayout.X_AXIS));

		JLabel searchLabel = new JLabel("Search:");
		panel_100.add(searchLabel);
		searchLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		final JTextField searchTextField = new JTextField();

		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Update del JList
				updateJList= false;
				availablePermissionModelList.clear();

				String searchField="'"+searchTextField.getText()+"'";
				String query= "Select * from sisca_permission where sisca_permission_active= 'true' and sisca_permission_tag_number ~*"+searchField;
				try {
					dbman= new DBManager();
					availablePermission= dbman.getFromDB(query);
					availablePermission= dbman.getAvailablePermission(availablePermission);
					for(int i=0; i<availablePermission.size(); i++){
						availablePermissionModelList.addElement(availablePermission.get(i));
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		panel_100.add(searchTextField);
		searchTextField.setColumns(10);

		JPanel viewAndAddBynPanel = new JPanel();
		viewAndAddBynPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(viewAndAddBynPanel, "cell 0 23,alignx left,aligny top");
		viewAndAddBynPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton viewAllButton = new JButton("View All");
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButtonPermission = new JButton("Add New Permission");
		addNewButtonPermission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addPermissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		//viewAndAddBynPanel.add(addNewButtonPermission);



		/////////////////////////////////////////////////////////
		//Main Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel mainPanelAddPermission = new JPanel();
		mainPanelAddPermission.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelAddPermission.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelAddPermission.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAddPermission.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAddPermission.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelAddPermission.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[849.00px]", "[][50][19px][19px][][50][][][][50][][][16.00][19px][-40.00px]"));



		JLabel lblNewLabelApplicant = new JLabel("ADD NEW PERMISSION\n");
		lblNewLabelApplicant.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		centerPanel.add(lblNewLabelApplicant, "cell 0 0,alignx center");

		JLabel lblNewLabelPermissionInformation = new JLabel("PERMISSION INFORMATION");
		lblNewLabelPermissionInformation.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		centerPanel.add(lblNewLabelPermissionInformation, "cell 0 1,alignx left,aligny bottom");

		JLabel lblSpecialPermission = new JLabel("Special Permission?");
		centerPanel.add(lblSpecialPermission, "flowx,cell 0 2");

		final JRadioButton rdbtnYesRadioButton = new JRadioButton("Yes");
		centerPanel.add(rdbtnYesRadioButton, "cell 0 2");

		final JRadioButton rdbtnNoRadioButton = new JRadioButton("No       ");
		centerPanel.add(rdbtnNoRadioButton, "cell 0 2");

		JLabel lblNewLabelTagNumber = new JLabel("Tag Number:");
		centerPanel.add(lblNewLabelTagNumber, "cell 0 2,alignx left");

		JLabel lblNewLabelExpirationDate = new JLabel("Expiration Date (YYYY-MM-DD):");
		centerPanel.add(lblNewLabelExpirationDate, "flowx,cell 0 3");

		JLabel lblNewLabelNotificationDate = new JLabel("Notifications?");
		centerPanel.add(lblNewLabelNotificationDate, "flowx,cell 0 4");

		JLabel lblNewLabelApplicantInformation = new JLabel("APLICANT INFORMATION");
		lblNewLabelApplicantInformation.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		centerPanel.add(lblNewLabelApplicantInformation, "cell 0 5,alignx left,aligny bottom");

		JLabel lblApplicantsFirstName = new JLabel("Applicant's First Name:");
		centerPanel.add(lblApplicantsFirstName, "flowx,cell 0 6");

		JLabel lblNewLabelPhone = new JLabel("Phone:");
		centerPanel.add(lblNewLabelPhone, "flowx,cell 0 7");

		textFieldPhone = new JTextField();
		centerPanel.add(textFieldPhone, "cell 0 7");
		textFieldPhone.setColumns(10);

		JLabel lblNewLabelEmail = new JLabel("Email:");
		centerPanel.add(lblNewLabelEmail, "cell 0 7");

		textFieldEmail = new JTextField();
		centerPanel.add(textFieldEmail, "cell 0 7");
		textFieldEmail.setColumns(10);

		JLabel lblNewLabelHandicap = new JLabel("Handicap Permission?");
		centerPanel.add(lblNewLabelHandicap, "cell 0 7");

		JLabel lblNewLabelLicenseNumber = new JLabel("License Number:");
		centerPanel.add(lblNewLabelLicenseNumber, "flowx,cell 0 8");

		JLabel lblNewLabelVehicleInformation = new JLabel("VEHICLE INFORMATION");
		lblNewLabelVehicleInformation.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		centerPanel.add(lblNewLabelVehicleInformation, "cell 0 9,alignx left,aligny bottom");

		JLabel lblNewLabelVIN = new JLabel("VIN:");
		centerPanel.add(lblNewLabelVIN, "flowx,cell 0 10");

		JLabel lblNewLabelColor = new JLabel("Color:");
		centerPanel.add(lblNewLabelColor, "flowx,cell 0 12");

		textFieldTagNumber = new JTextField();
		textFieldTagNumber.setPreferredSize(new Dimension(500, 28));
		centerPanel.add(textFieldTagNumber, "cell 0 2");
		textFieldTagNumber.setColumns(10);


		JLabel lblNewLabelModel = new JLabel("Model:");
		centerPanel.add(lblNewLabelModel, "flowx,cell 0 11");

		textFieldVIN = new JTextField();
		centerPanel.add(textFieldVIN, "cell 0 10");
		textFieldVIN.setColumns(10);

		JLabel lblNewLabelPlate = new JLabel("Plate:");
		centerPanel.add(lblNewLabelPlate, "cell 0 10");



		textFieldExpirationDate = new JTextField();
		textFieldExpirationDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(textFieldExpirationDate.getText().equals("YYYY-MM-DD")){
					textFieldExpirationDate.setText("");
					textFieldExpirationDate.setForeground(new Color(0,0,0));
					textFieldExpirationDate.setHorizontalAlignment(JTextField.LEFT);
				}

			}

		}
				);
		textFieldExpirationDate.setText("YYYY-MM-DD");
		textFieldExpirationDate.setForeground(new Color(210,210,210));
		textFieldExpirationDate.setHorizontalAlignment(JTextField.CENTER);
		centerPanel.add(textFieldExpirationDate, "cell 0 3");
		textFieldExpirationDate.setColumns(10);

		final JRadioButton handicapYes = new JRadioButton("Yes");
		final JRadioButton handicapNo = new JRadioButton("No");

		centerPanel.add(handicapYes, "cell 0 7");
		centerPanel.add(handicapNo, "cell 0 7");

		handicapNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				handicapYes.setSelected(false);
			}
		}
				);
		handicapYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				handicapNo.setSelected(false);
			}
		}
				);


		textFieldLicenseNumber = new JTextField();
		centerPanel.add(textFieldLicenseNumber, "cell 0 8");
		textFieldLicenseNumber.setColumns(10);



		textFieldPlate = new JTextField();
		centerPanel.add(textFieldPlate, "cell 0 10");
		textFieldPlate.setColumns(10);

		textFieldModel = new JTextField();
		centerPanel.add(textFieldModel, "cell 0 11");
		textFieldModel.setColumns(10);

		textFieldColor = new JTextField();
		centerPanel.add(textFieldColor, "cell 0 12");
		textFieldColor.setColumns(10);

		textFieldAFirstName = new JTextField();
		centerPanel.add(textFieldAFirstName, "cell 0 6");
		textFieldAFirstName.setColumns(10);

		JLabel lblApplicantsLastName = new JLabel("Applicant's Last Name:");
		centerPanel.add(lblApplicantsLastName, "cell 0 6");

		textFieldALastName = new JTextField();
		centerPanel.add(textFieldALastName, "cell 0 6");
		textFieldALastName.setColumns(10);


		JPanel editAndRemovePanel = new JPanel();
		editAndRemovePanel.setBackground(new Color(250,250,250));
		centerPanel.add(editAndRemovePanel, "cell 0 13,grow");
		editAndRemovePanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));


		textFieldNotificationDate = new JTextField();

		final JRadioButton rdbtnYes = new JRadioButton("Yes");
		final JRadioButton rdbtnNo = new JRadioButton("No");

		rdbtnYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnNo.setSelected(false);
				textFieldNotificationDate.setEnabled(true);

			}
		}
				);
		centerPanel.add(rdbtnYes, "cell 0 4");

		rdbtnNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnYes.setSelected(false);
				textFieldNotificationDate.setEnabled(false);

			}
		}
				);
		centerPanel.add(rdbtnNo, "cell 0 4");

		JLabel lblDate = new JLabel("             If Yes, Notification Date (YYYY-MM-DD):");
		centerPanel.add(lblDate, "cell 0 4");


		textFieldNotificationDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(textFieldNotificationDate.getText().equals("YYYY-MM-DD")){
					textFieldNotificationDate.setText("");
					textFieldNotificationDate.setForeground(new Color(0,0,0));
					textFieldNotificationDate.setHorizontalAlignment(JTextField.LEFT);
				}
			}

		}
				);
		textFieldNotificationDate.setText("YYYY-MM-DD");
		textFieldNotificationDate.setForeground(new Color(210,210,210));
		textFieldNotificationDate.setHorizontalAlignment(JTextField.CENTER);
		centerPanel.add(textFieldNotificationDate, "cell 0 4");
		textFieldNotificationDate.setColumns(10);

		JLabel lblNewLabelBrand = new JLabel("Brand:");
		centerPanel.add(lblNewLabelBrand, "cell 0 10");

		textFieldBrand = new JTextField();
		centerPanel.add(textFieldBrand, "cell 0 10");
		textFieldBrand.setColumns(10);

		JLabel lblNewLabelCountry = new JLabel("Country:");
		centerPanel.add(lblNewLabelCountry, "cell 0 11");

		DefaultComboBoxModel model = new DefaultComboBoxModel();
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);

			model.addElement(obj.getDisplayCountry());

		}

		final JComboBox countryComboBox = new JComboBox(model);
		countryComboBox.setPreferredSize(new Dimension(200, 27));
		centerPanel.add(countryComboBox, "cell 0 11");


		JLabel lblNewLabelYear = new JLabel("Year:");
		centerPanel.add(lblNewLabelYear, "cell 0 11");

		textFieldYear = new JTextField();
		textFieldYear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textFieldYear.setText("");
				textFieldYear.setForeground(new Color(0,0,0));
				textFieldYear.setHorizontalAlignment(JTextField.LEFT);
			}

		}
				);
		textFieldYear.setText("YYYY");
		textFieldYear.setForeground(new Color(210,210,210));
		textFieldYear.setHorizontalAlignment(JTextField.CENTER);
		centerPanel.add(textFieldYear, "cell 0 11");
		textFieldYear.setColumns(10);

		JLabel lblNewLabelOwnerName = new JLabel("Owner's First Name:");
		centerPanel.add(lblNewLabelOwnerName, "cell 0 12");

		textFieldOwnerFirstName = new JTextField();
		centerPanel.add(textFieldOwnerFirstName, "cell 0 12");
		textFieldOwnerFirstName.setColumns(10);

		JLabel lblOwnersLastName = new JLabel("Owner's Last Name:");
		centerPanel.add(lblOwnersLastName, "cell 0 12");

		textFieldOwnerLastName = new JTextField();
		centerPanel.add(textFieldOwnerLastName, "cell 0 12");
		textFieldOwnerLastName.setColumns(10);

		JLabel lblNewLabel = new JLabel("Authorization Type: ");
		centerPanel.add(lblNewLabel, "cell 0 3");



		DefaultComboBoxModel modelAT = new DefaultComboBoxModel();

		try {
			dbman= new DBManager();
			ArrayList list = dbman.getFromDB("Select * from sisca_authorization");
			list= dbman.getAvailableAuthorizationTypes(list);
			for( int i=0; i<list.size(); i++){
				modelAT.addElement(list.get(i));
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}




		final JComboBox authirizationTypesComboBox = new JComboBox(modelAT);
		authirizationTypesComboBox.setPreferredSize(new Dimension(200, 27));
		centerPanel.add(authirizationTypesComboBox, "cell 0 3");


		JButton editButton = new JButton("Ok");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");
				if(sure==0){


					if (isSpecialPermission==true){


						if(textFieldTagNumber.getText().isEmpty()||textFieldExpirationDate.getText().isEmpty()){
							JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "You must fill all fields.", "", 1);
						}
						else{

							DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
							Calendar cal = Calendar.getInstance();
							String tag_number= "'"+textFieldTagNumber.getText()+"'";
							String expiration= "'"+textFieldExpirationDate.getText()+"'";
							String creationDate= "'"+dateFormat.format(cal.getTime())+"'";
							String createdBy= ""+HKJ_SisCA_MainPage.loggedUsernaneWith+"";						
							String authorizationType= "'"+authirizationTypesComboBox.getSelectedItem()+"'";

							String query1= " INSERT INTO sisca_special_permission (sisca_special_permission_tag_number, sisca_special_permission_expiration_date, sisca_special_permission_authorization_type, "
									+ "sisca_special_permission_active, sisca_special_permission_creationdate, sisca_special_permission_createdby) "
									+ "VALUES("+tag_number+" , "+expiration+" , " + authorizationType+" , 'true', "+ creationDate+ ", "+createdBy+")";

							int special_permission_id=-1;
							try {
								dbman= new DBManager();
								special_permission_id=dbman.insertDB(query1);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "Invalid Tag Number or Date Format!", "", 1);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "ERROR", "", 1);
							}
							isSpecialPermission=false;
							HKJ_SisCA_MainPage.frame.setContentPane(specialPermissionInformationView(textFieldTagNumber.getText(),special_permission_id));
							HKJ_SisCA_MainPage.frame.pack(); 
							HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

						}	

					}
					else{


						if(textFieldVIN.getText().isEmpty()||textFieldPlate.getText().isEmpty()||textFieldBrand.getText().isEmpty()||
								textFieldModel.getText().isEmpty()|| textFieldYear.getText().isEmpty()||textFieldColor.getText().isEmpty()||
								textFieldOwnerFirstName.getText().isEmpty()||textFieldOwnerLastName.getText().isEmpty()||
								textFieldAFirstName.getText().isEmpty()||textFieldALastName.getText().isEmpty()||textFieldPhone.getText().isEmpty()||
								textFieldEmail.getText().isEmpty()||textFieldLicenseNumber.getText().isEmpty()||textFieldTagNumber.getText().isEmpty()||
								textFieldExpirationDate.getText().isEmpty()||textFieldNotificationDate.getText().isEmpty()){


							JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "Invalid or incomplete input data! Please, verify your informtion.", "", 1);



						}
						else{



							try {
								/////////////////////////////////////////////////
								// Query for insert Parking Information
								/////////////////////////////////////////////////

								int vehicle_id=-1;
								int applicant_id=-1;
								int notification_id=-1;
								int permission_id=-1;

								/**
								 * Get the current date YYYY/MMM/DD
								 */
								DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
								Calendar cal = Calendar.getInstance();


								// Vehicle Information
								String vin= "'"+textFieldVIN.getText()+"'";
								String plate= "'"+textFieldPlate.getText()+"'";
								String country= "'"+countryComboBox.getSelectedItem()+"'";
								String brand= "'"+textFieldBrand.getText()+"'";
								String model= "'"+textFieldModel.getText()+"'";
								String year= "'"+textFieldYear.getText()+"'";
								String color= "'"+textFieldColor.getText()+"'";
								String o_first= "'"+textFieldOwnerFirstName.getText()+"'";
								String o_last= "'"+textFieldOwnerLastName.getText()+"'";

								// Applicant Information
								String a_first= "'"+textFieldAFirstName.getText()+"'";
								String a_last= "'"+textFieldALastName.getText()+"'";
								String phone= "'"+textFieldPhone.getText()+"'";
								String email= "'"+textFieldEmail.getText()+"'";
								String license= "'"+textFieldLicenseNumber.getText()+"'";
								String h_status="N/A";

								if(handicapYes.isSelected()){
									h_status= "'true'";
								}
								else{
									h_status= "'false'";
								}
								// Permission Information
								String tag_number= "'"+textFieldTagNumber.getText()+"'";
								//String delivery= "'"+dateFormat.format(cal.getTime())+"'";
								String expiration= "'"+textFieldExpirationDate.getText()+"'";
								String notification= "'"+textFieldNotificationDate.getText()+"'";
								String authorizationName= "'"+authirizationTypesComboBox.getSelectedItem()+"'";

								// Other Information
								String creationDate= "'"+dateFormat.format(cal.getTime())+"'";
								String createdBy= ""+HKJ_SisCA_MainPage.loggedUsernaneWith+"";

								String query1= " INSERT INTO sisca_vehicle (sisca_vehicle_vin, sisca_vehicle_plate, sisca_vehicle_country, sisca_vehicle_brand, sisca_vehicle_model, sisca_vehicle_year, "+
										"sisca_vehicle_color, sisca_vehicle_owner_first_name, sisca_vehicle_owner_last_name, sisca_vehicle_creationdate, sisca_vehicle_createdby, sisca_vehicle_active) VALUES (" +vin + " , "+plate + " , "+country + " , "+brand + " , "+model + " , "+year + " , "+color + " , "+o_first + " , "+o_last + " , "+creationDate + " , "+createdBy +" , 'true' " + " )";

								String query2= " INSERT INTO sisca_applicant (sisca_applicant_first_name, sisca_applicant_last_name, sisca_applicant_phone_number, sisca_applicant_email, sisca_applicant_license_number, sisca_applicant_handicap_status,sisca_applicant_creationdate, sisca_applicant_createdby, sisca_applicant_active) VALUES ("
										+a_first + " , "+a_last + " , "+phone + " , "+email + " , "+license + " , "+h_status + " , "+creationDate + " , "+createdBy +" , 'true' " +" )";

								String query4= " INSERT INTO sisca_notification (sisca_notification_date, sisca_notification_active, sisca_notification_creationdate, sisca_notification_createdby) VALUES ("+notification+", 'true', "+creationDate + " , "+createdBy+")";


								vehicle_id= dbman.insertVehicleDB(query1);
								applicant_id= dbman.insertDB(query2);

								if(rdbtnYes.isSelected()){
									notification_id= dbman.insertDB(query4);
								}



								String query3= " INSERT INTO sisca_permission (sisca_permission_notification_id, sisca_permission_tag_number, sisca_permission_expiration_date, sisca_permission_status, sisca_permission_vehicle_id, sisca_permission_applicant_id, sisca_permission_creationdate, sisca_permission_createdby, sisca_permission_active, sisca_permission_authorization_type) VALUES ("
										+notification_id+", "+tag_number + " , "+expiration + " , 'true', "+vehicle_id + " , "+applicant_id + " , "+creationDate + " , "+createdBy +" , 'true' " + " , "+authorizationName +" )";

								permission_id= dbman.insertDB(query3);

								//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								// SEND TAG TO BE WRITTEN TO ALL SADS
								//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

								String query5= "Select sisca_authorization_id, sisca_authorization_uncondtional_entry from sisca_authorization where sisca_authorization_name ~* "+authorizationName;


								ArrayList result= new ArrayList();
								result= dbman.getNotificationsInformation(query5);

								System.out.println("RESULT: "+result);

								Object num = ((List) result.get(0)).get(0);
								Object num3 = ((List) result.get(0)).get(1);

								String unconditional= (String) num3;
								int authorization_id= Integer.parseInt((String) num);


								Boolean uncoditionalEntry=false;

								if(unconditional.equals("t")){
									uncoditionalEntry=true;
								}

								SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd"); //"mm.dd.yy"
								String expDate = textFieldExpirationDate.getText();
								//Date date = (java.sql.Date) dateFormat1.parse(expDate);


								//	dateFormat1.format(date);
								//	String expDate2 = dateFormat1.format(date);

								//Date date = (Date) dateFormat1.parse(textFieldExpirationDate.getText());

								Authorization authorization= new Authorization(authorization_id, (String)authirizationTypesComboBox.getSelectedItem(),uncoditionalEntry);
								Tag tagToBeSend= new Tag((String) textFieldTagNumber.getText(),authorization, textFieldExpirationDate.getText());
								TagListUpdateContainer tluc = new TagListUpdateContainer(tagToBeSend, TagUpdateType.AddUpdate, TagUpdateListName.NewTagsList);


								String query= "Select sisca_sad_name, sisca_sad_direction, sisca_parking_name from (((sisca_sad natural join sisca_sad_parking_list) natural join sisca_parking) natural join sisca_authorization_parking_list) natural join sisca_authorization where sisca_sad_active='true' and  sisca_sad.sisca_sad_id=sisca_sad_parking_list.sisca_sad_id and sisca_sad_parking_active='true' and sisca_parking.sisca_parking_id= sisca_sad_parking_list.sisca_parking_id and sisca_parking.sisca_parking_id= sisca_authorization_parking_list.sisca_parking_id and sisca_authorization_parking_active= 'true' and sisca_authorization.sisca_authorization_id= sisca_authorization_parking_list.sisca_authorization_id and sisca_authorization_id = '"+authorization_id+"'";

								//System.out.println(query);

								ArrayList sadList= new ArrayList();

								sadList= dbman.getNotificationsInformation(query);
								//System.out.println("SAD LIST: "+ sadList+ " Size: "+sadList.size());

								System.out.println(" TAG INFORMATION \n");
								System.out.println("TagListUpdateContainer: TAG-> Expiration Date["+tluc.getReceivedTag().getExpirationDate()+"] Tag Number["+tluc.getReceivedTag().getTagID()+"] Authorizatio Name: ["+tluc.getReceivedTag().getAuthorizationType().getAuthorizationName()
										+"] \n Update List Name:"+tluc.getTagUpdateListName().toString()+" \n Tag Update Type:"+tluc.getTagUpdateType().toString());

								for(int i=0; i<sadList.size();i++){
									String sadID= ""+((List<Object>) sadList.get(i)).get(0);
									System.out.println("Adding TAG to sad: '"+sadID + "'");
									//HKJ_SisCA_MainPage.cmcas.sendTagListUpdate(sadID,tluc);
								}

								JOptionPane.showMessageDialog(null, "Tag sent to SAD's successfully.");

								HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView(textFieldTagNumber.getText(),permission_id));
								HKJ_SisCA_MainPage.frame.pack(); 
								HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());



							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "Invalid Tag Number or Date Format !", "", 1);
							}	


						}

					}
				}



			}


		});
		editAndRemovePanel.add(editButton, "cell 0 0");

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		editAndRemovePanel.add(btnCancel, "cell 26 0");

		// Special Permission Handler
		rdbtnYesRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isSpecialPermission = true;
				rdbtnNoRadioButton.setSelected(false);
				// Permission
				rdbtnYes.setEnabled(false);
				rdbtnNo.setEnabled(false);
				textFieldNotificationDate.setEnabled(false);

				// Applicant
				textFieldAFirstName.setEnabled(false);
				textFieldALastName.setEnabled(false);
				textFieldPhone.setEnabled(false);
				textFieldEmail.setEnabled(false);
				textFieldLicenseNumber.setEnabled(false);
				handicapYes.setEnabled(false);
				handicapNo.setEnabled(false);

				// Vehicle
				textFieldVIN.setEnabled(false);
				textFieldPlate.setEnabled(false);
				countryComboBox.setEnabled(false);
				textFieldBrand.setEnabled(false);
				textFieldModel.setEnabled(false);
				textFieldYear.setEnabled(false);
				textFieldColor.setEnabled(false);
				textFieldOwnerFirstName.setEnabled(false);
				textFieldOwnerLastName.setEnabled(false);

			}
		});

		rdbtnNoRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				isSpecialPermission=false;
				rdbtnYesRadioButton.setSelected(false);
				// Permission
				rdbtnYes.setEnabled(true);
				rdbtnNo.setEnabled(true);
				textFieldNotificationDate.setEnabled(true);

				// Applicant
				textFieldAFirstName.setEnabled(true);
				textFieldALastName.setEnabled(true);
				textFieldPhone.setEnabled(true);
				textFieldEmail.setEnabled(true);
				textFieldLicenseNumber.setEnabled(true);
				handicapYes.setEnabled(true);
				handicapNo.setEnabled(true);

				// Vehicle
				textFieldVIN.setEnabled(true);
				textFieldPlate.setEnabled(true);
				countryComboBox.setEnabled(true);
				textFieldBrand.setEnabled(true);
				textFieldModel.setEnabled(true);
				textFieldYear.setEnabled(true);
				textFieldColor.setEnabled(true);
				textFieldOwnerFirstName.setEnabled(true);
				textFieldOwnerLastName.setEnabled(true);
			}
		});

		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelAddPermission= new JPanel();
		windowPanelAddPermission.setLayout(new BorderLayout(0, 0));
		windowPanelAddPermission.add(menuPanelAddPermission, BorderLayout.NORTH);
		windowPanelAddPermission.add(leftPanelAddPermission, BorderLayout.WEST);
		windowPanelAddPermission.add(mainPanelAddPermission, BorderLayout.CENTER);


		return windowPanelAddPermission;
	}

	//////////////////////////////////////////////////////////////////
	//   Edit Permission View						                //
	//////////////////////////////////////////////////////////////////


	/** Edit Permission View
	 *  Generates the Edit Permission JPanel
	 * @param currentTagID 
	 *  
	 * 	@return windowPanelEditPermission JPanel 
	 */
	private static JPanel editPermissionView(String crtTagNumber, Integer crtTagID){

		final String currentTagNumber= crtTagNumber;
		final Integer currentTagID= crtTagID;



		final JTextField textFieldUsername;
		final JTextField textFieldPassword;
		final JTextField textFieldSearch;
		final JTextField textFieldTagNumber;
		final JTextField textFieldDeliveryDate;
		final JTextField textFieldNotificationDate;
		final JTextField textFieldExpirationDate;
		final JTextField textFieldPhone;
		final JTextField textFieldEmail;
		final JTextField textFieldLicenseNumber;
		final JTextField textFieldVIN;
		final JTextField textFieldPlate;
		final JTextField textFieldBrand;
		final JTextField textFieldModel;
		final JTextField textFieldCountry;
		final JTextField textFieldYear;
		final JTextField textFieldColor;
		final JTextField textFieldOwnerFirstName;
		final JTextField textFieldOwnerLastName;
		final JTextField textFieldAFirstName;
		final JTextField textFieldALastName;


		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelEditPermission = new JPanel();
		menuPanelEditPermission.setBackground(new Color(255,239,80));
		menuPanelEditPermission.setPreferredSize(new Dimension(10, 30));
		menuPanelEditPermission.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(375, 10));
		menuPanelEditPermission.add(menuOptionsPanel, BorderLayout.WEST);
		menuOptionsPanel.setLayout(null);

		JLabel homeLabel = new JLabel(" Home");
		homeLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		homeLabel.setBounds(0, -11, 68, 53);
		menuOptionsPanel.add(homeLabel);
		homeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(HKJ_SisCA_MainPage.windowPanel); // Wrong Way! =S
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		homeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homeLabel.setForeground((java.awt.Color) null);
		homeLabel.setFont(new Font("Dialog", Font.BOLD, 14));

		JLabel PermissionManagerLabel = new JLabel("Permission");
		PermissionManagerLabel.setPreferredSize(new Dimension(30, 16));
		PermissionManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		PermissionManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		PermissionManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PermissionManagerLabel.setForeground((java.awt.Color) null);
		PermissionManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		PermissionManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		PermissionManagerLabel.setBounds(66, -11, 95, 53);
		menuOptionsPanel.add(PermissionManagerLabel);

		JLabel listPermissionLabel = new JLabel("Edit Permission");
		listPermissionLabel.setOpaque(true);
		listPermissionLabel.setBackground(new Color (220,220,220));
		listPermissionLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView(currentTagNumber));
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		listPermissionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listPermissionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		listPermissionLabel.setForeground((java.awt.Color) null);
		listPermissionLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		listPermissionLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listPermissionLabel.setBounds(159, -11, 174, 53);
		menuOptionsPanel.add(listPermissionLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelEditPermission.add(logOutPanel, BorderLayout.EAST);
		logOutPanel.setLayout(null);

		JLabel logOutLabel = new JLabel("LogOut");
		logOutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.setActiveUsername(null);
				HKJ_SisCA_MainPage.frame.setContentPane(LogInManager.standByView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		logOutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logOutLabel.setBounds(0, -11, 75, 52);
		logOutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logOutLabel.setForeground((java.awt.Color) null);
		logOutLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		logOutLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		logOutPanel.add(logOutLabel);

		JPanel userNamePanel = new JPanel();
		userNamePanel.setPreferredSize(new Dimension(200, 10));
		menuPanelEditPermission.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel(HKJ_SisCA_MainPage.getActiveUsername());
		userNamePanel.add(userNameLabel, BorderLayout.EAST);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground((java.awt.Color) null);
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));

		JLayeredPane liveSystemPanel2 = new JLayeredPane();
		liveSystemPanel2.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));



		/////////////////////////////////////////////////////////
		//Left Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel leftPanelEditPermission = new JPanel();
		leftPanelEditPermission.setBackground(new Color(245,245,245));
		leftPanelEditPermission.setPreferredSize(new Dimension(390, 10));
		leftPanelEditPermission.setLayout(new BorderLayout(0, 0));

		final JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanelEditPermission.add(scrollPane);

		JLayeredPane liveSystemPanel21 = new JLayeredPane();
		scrollPane.setViewportView(liveSystemPanel);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel21.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));



		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px]"));


		//LeftPanel Labels
		JLabel PermissionListLabel = new JLabel("PERMISSIONS LIST");
		liveSystemPanel.add(PermissionListLabel, "cell 0 0,alignx center,aligny top");
		PermissionListLabel.setPreferredSize(new Dimension(100, 50));
		PermissionListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PermissionListLabel.setForeground(java.awt.Color.BLACK);
		PermissionListLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		ArrayList<Object> PermissionUsernameListView = getPermissionList();
		int position = 1;
		for(int i=0; i<PermissionUsernameListView.size() && i<10 ; i++){
			position = position+2;
			final JLabel permissionLabel = new JLabel(PermissionUsernameListView.get(i).toString());
			permissionLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String query1= "Select sisca_permission_id from sisca_permission where sisca_permission_tag_number="+"'"+permissionLabel.getText()+"' and sisca_permission_active='true'";

					String[] list = null;
					try {
						dbman= new DBManager();
						list = ((DBManager) dbman).getPermissionInfoFromDB(query1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView(permissionLabel.getText(), Integer.parseInt(list[0])));
					HKJ_SisCA_MainPage.frame.pack();
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			});
			permissionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			liveSystemPanel.add(permissionLabel, "cell 0 "+ position +" ,alignx left,aligny top");
		}


		//Separators
		JSeparator separator = new JSeparator();
		liveSystemPanel.add(separator, "cell 0 4,growx,aligny top");

		JSeparator separator_1 = new JSeparator();
		liveSystemPanel.add(separator_1, "cell 0 6,growx,aligny top");

		JSeparator separator_2 = new JSeparator();
		liveSystemPanel.add(separator_2, "cell 0 8,growx,aligny top");

		JSeparator separator_3 = new JSeparator();
		liveSystemPanel.add(separator_3, "cell 0 10,growx,aligny top");

		JSeparator separator_4 = new JSeparator();
		liveSystemPanel.add(separator_4, "cell 0 12,growx,aligny top");

		JSeparator separator_5 = new JSeparator();
		liveSystemPanel.add(separator_5, "cell 0 14,growx,aligny top");

		JSeparator separator_7 = new JSeparator();
		liveSystemPanel.add(separator_7, "cell 0 18,growx,aligny top");

		JSeparator separator_6 = new JSeparator();
		liveSystemPanel.add(separator_6, "cell 0 16,growx,aligny top");

		JSeparator separator_8 = new JSeparator();
		liveSystemPanel.add(separator_8, "cell 0 20,growx,aligny top");

		JSeparator separator_9 = new JSeparator();
		liveSystemPanel.add(separator_9, "cell 0 22,growx,aligny top");

		JSeparator separator_10 = new JSeparator();
		liveSystemPanel.add(separator_10, "cell 0 2,growx,aligny top");

		JPanel panel_100 = new JPanel();
		panel_100.setBackground(new Color(245,245,245));
		liveSystemPanel.add(panel_100, "cell 0 1,growx,aligny top");
		panel_100.setLayout(new BoxLayout(panel_100, BoxLayout.X_AXIS));

		JLabel searchLabel = new JLabel("Search:");
		panel_100.add(searchLabel);
		searchLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		final JTextField searchTextField = new JTextField();
		/**
		 * CODIDO DE LA BARRA DE SEARCH
		 */
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		panel_100.add(searchTextField);
		searchTextField.setColumns(10);

		JPanel viewAndAddBynPanel = new JPanel();
		viewAndAddBynPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(viewAndAddBynPanel, "cell 0 23,alignx left,aligny top");
		viewAndAddBynPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton viewAllButton = new JButton("View All");
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButtonPermission = new JButton("Add New Permission");
		addNewButtonPermission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addPermissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(addNewButtonPermission);



		/////////////////////////////////////////////////////////
		//Main Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel mainPanelEditPermission = new JPanel();
		mainPanelEditPermission.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelEditPermission.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelEditPermission.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelEditPermission.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelEditPermission.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelEditPermission.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[849.00px]", "[][12px][][19px][12px][19px][12px][][][][][][][][][][][][][][16.00][][19px][-40.00px]"));


		// Get Current Permission Information

		currentPermissionInfo= new ArrayList<String>();

		try {
			String query2= "Select * from ((sisca_permission natural join sisca_vehicle) natural join sisca_applicant) natural join sisca_notification"+
					" where sisca_permission_vehicle_id= sisca_vehicle_id and sisca_permission_applicant_id=sisca_applicant_id  and sisca_permission_notification_id=sisca_notification_id"
					+ " and sisca_permission_id = "+"'"+currentTagID+"'";

			System.out.println("Printing Query:" + query2);
			dbman= new DBManager();
			currentPermissionInfo= dbman.getAllPermissionInfoFromDB(query2);

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



		JLabel lblNewLabelApplicant = new JLabel("EDIT PERMISSION\n");
		lblNewLabelApplicant.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		centerPanel.add(lblNewLabelApplicant, "cell 0 0,alignx center");

		JLabel lblNewLabelPermissionInformation = new JLabel("PERMISSION INFORMATION");
		lblNewLabelPermissionInformation.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		centerPanel.add(lblNewLabelPermissionInformation, "cell 0 2,alignx left");

		JLabel lblNewLabelTagNumber = new JLabel("Tag Number:");
		centerPanel.add(lblNewLabelTagNumber, "flowx,cell 0 3,alignx left");

		JLabel lblNewLabelExpirationDate = new JLabel("Expiration Date (YYYY-MM-DD):");
		centerPanel.add(lblNewLabelExpirationDate, "flowx,cell 0 5");

		JLabel lblNewLabelApplicantInformation = new JLabel("APLICANT INFORMATION");
		lblNewLabelApplicantInformation.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		centerPanel.add(lblNewLabelApplicantInformation, "cell 0 7,alignx left");

		JLabel lblApplicantsFirstName = new JLabel("Applicant's First Name:");
		centerPanel.add(lblApplicantsFirstName, "flowx,cell 0 9");

		JLabel lblNewLabelPhone = new JLabel("Phone:");
		centerPanel.add(lblNewLabelPhone, "flowx,cell 0 11");

		textFieldPhone = new JTextField(currentPermissionInfo.get(35));
		centerPanel.add(textFieldPhone, "cell 0 11");
		textFieldPhone.setColumns(10);

		JLabel lblNewLabelEmail = new JLabel("Email:");
		centerPanel.add(lblNewLabelEmail, "cell 0 11");

		textFieldEmail = new JTextField(currentPermissionInfo.get(36));
		centerPanel.add(textFieldEmail, "cell 0 11");
		textFieldEmail.setColumns(10);

		JLabel lblNewLabelHandicap = new JLabel("Handicap Permission?");
		centerPanel.add(lblNewLabelHandicap, "cell 0 11");

		final JRadioButton handicapYes = new JRadioButton("Yes");
		final JRadioButton handicapNo = new JRadioButton("No");

		centerPanel.add(handicapYes, "cell 0 11");
		centerPanel.add(handicapNo, "cell 0 11");

		if(currentPermissionInfo.get(38).equals("t")){
			handicapYes.setSelected(true);
		}
		else{
			handicapNo.setSelected(true);
		}

		handicapNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				handicapYes.setSelected(false);
			}
		}
				);
		handicapYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				handicapNo.setSelected(false);
			}
		}
				);

		JLabel lblNewLabelLicenseNumber = new JLabel("License Number:");
		centerPanel.add(lblNewLabelLicenseNumber, "flowx,cell 0 12");

		JLabel lblNewLabelVehicleInformation = new JLabel("VEHICLE INFORMATION");
		lblNewLabelVehicleInformation.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		centerPanel.add(lblNewLabelVehicleInformation, "cell 0 13,alignx left");

		JLabel lblNewLabelVIN = new JLabel("VIN:");
		centerPanel.add(lblNewLabelVIN, "flowx,cell 0 14");

		JLabel lblNewLabelBrand = new JLabel("Brand:");
		centerPanel.add(lblNewLabelBrand, "flowx,cell 0 16");

		JLabel lblNewLabelCountry = new JLabel("Country:");
		centerPanel.add(lblNewLabelCountry, "flowx,cell 0 18");

		JLabel lblNewLabelColor = new JLabel("Color:");
		centerPanel.add(lblNewLabelColor, "flowx,cell 0 20");

		JLabel lblNewLabelOwnerName = new JLabel("Owner's First Name:");
		centerPanel.add(lblNewLabelOwnerName, "flowx,cell 0 21");

		JPanel editAndRemovePanel = new JPanel();
		editAndRemovePanel.setBackground(new Color(250,250,250));
		centerPanel.add(editAndRemovePanel, "cell 0 22,grow");
		editAndRemovePanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));

		textFieldTagNumber = new JTextField(currentPermissionInfo.get(1));
		textFieldTagNumber.setPreferredSize(new Dimension(500, 28));
		centerPanel.add(textFieldTagNumber, "cell 0 3");
		textFieldTagNumber.setColumns(10);

		JLabel lblNewLabelAT= new JLabel("Authorization Type: ");
		centerPanel.add(lblNewLabelAT, "flowx,cell 0 3,alignx left");


		DefaultComboBoxModel modelAT = new DefaultComboBoxModel();

		try {
			dbman= new DBManager();
			ArrayList list = dbman.getFromDB("Select * from sisca_authorization");
			list= dbman.getAvailableAuthorizationTypes(list);
			for( int i=0; i<list.size(); i++){
				modelAT.addElement(list.get(i));
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}




		final JComboBox authirizationTypesComboBox = new JComboBox(modelAT);
		authirizationTypesComboBox.setSelectedItem(currentPermissionInfo.get(14));
		authirizationTypesComboBox.setPreferredSize(new Dimension(200, 27));
		centerPanel.add(authirizationTypesComboBox, "cell 0 3");

		textFieldExpirationDate = new JTextField(currentPermissionInfo.get(2));
		centerPanel.add(textFieldExpirationDate, "cell 0 5");
		textFieldExpirationDate.setColumns(10);

		JLabel lblNewLabelNotificationDate = new JLabel("Notification Date (YYYY-MM-DD):");
		centerPanel.add(lblNewLabelNotificationDate, "cell 0 5");

		textFieldNotificationDate = new JTextField(currentPermissionInfo.get(47));
		centerPanel.add(textFieldNotificationDate, "cell 0 5");
		textFieldNotificationDate.setColumns(10);

		textFieldLicenseNumber = new JTextField(currentPermissionInfo.get(37));
		centerPanel.add(textFieldLicenseNumber, "cell 0 12");
		textFieldLicenseNumber.setColumns(10);

		textFieldVIN = new JTextField(currentPermissionInfo.get(16));
		centerPanel.add(textFieldVIN, "cell 0 14");
		textFieldVIN.setColumns(10);

		JLabel lblNewLabelPlate = new JLabel("Plate:");
		centerPanel.add(lblNewLabelPlate, "cell 0 14");

		textFieldPlate = new JTextField(currentPermissionInfo.get(17));
		centerPanel.add(textFieldPlate, "cell 0 14");
		textFieldPlate.setColumns(10);

		textFieldBrand = new JTextField(currentPermissionInfo.get(19));
		centerPanel.add(textFieldBrand, "cell 0 16");
		textFieldBrand.setColumns(10);

		JLabel lblNewLabelModel = new JLabel("Model:");
		centerPanel.add(lblNewLabelModel, "cell 0 16");

		textFieldModel = new JTextField(currentPermissionInfo.get(20));
		centerPanel.add(textFieldModel, "cell 0 16");
		textFieldModel.setColumns(10);


		DefaultComboBoxModel model = new DefaultComboBoxModel();
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);

			model.addElement(obj.getDisplayCountry());

		}

		final JComboBox countryComboBox = new JComboBox(model);
		countryComboBox.setSelectedItem(currentPermissionInfo.get(18));
		countryComboBox.setPreferredSize(new Dimension(200, 27));
		centerPanel.add(countryComboBox, "cell 0 18");


		JLabel lblNewLabelYear = new JLabel("Year:");
		centerPanel.add(lblNewLabelYear, "cell 0 18");

		textFieldYear = new JTextField(currentPermissionInfo.get(21));
		centerPanel.add(textFieldYear, "cell 0 18");
		textFieldYear.setColumns(10);

		textFieldColor = new JTextField(currentPermissionInfo.get(22));
		centerPanel.add(textFieldColor, "cell 0 20");
		textFieldColor.setColumns(10);

		textFieldOwnerFirstName = new JTextField(currentPermissionInfo.get(23));
		centerPanel.add(textFieldOwnerFirstName, "cell 0 21");
		textFieldOwnerFirstName.setColumns(10);

		JLabel lblOwnersLastName = new JLabel("Owner's Last Name:");
		centerPanel.add(lblOwnersLastName, "cell 0 21");

		textFieldOwnerLastName = new JTextField(currentPermissionInfo.get(24));
		centerPanel.add(textFieldOwnerLastName, "cell 0 21");
		textFieldOwnerLastName.setColumns(10);


		textFieldAFirstName = new JTextField(currentPermissionInfo.get(33));
		centerPanel.add(textFieldAFirstName, "cell 0 9");
		textFieldAFirstName.setColumns(10);

		JLabel lblApplicantsLastName = new JLabel("Applicant's Last Name:");
		centerPanel.add(lblApplicantsLastName, "cell 0 9");

		textFieldALastName = new JTextField(currentPermissionInfo.get(34));
		centerPanel.add(textFieldALastName, "cell 0 9");
		textFieldALastName.setColumns(10);


		// UPDATE PERMISSION
		JButton editButton = new JButton("Ok");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				if(textFieldVIN.getText().isEmpty()||textFieldPlate.getText().isEmpty()||textFieldBrand.getText().isEmpty()||
						textFieldModel.getText().isEmpty()|| textFieldYear.getText().isEmpty()||textFieldColor.getText().isEmpty()||
						textFieldOwnerFirstName.getText().isEmpty()||textFieldOwnerLastName.getText().isEmpty()||
						textFieldAFirstName.getText().isEmpty()||textFieldALastName.getText().isEmpty()||textFieldPhone.getText().isEmpty()||
						textFieldEmail.getText().isEmpty()||textFieldLicenseNumber.getText().isEmpty()||textFieldTagNumber.getText().isEmpty()||
						textFieldExpirationDate.getText().isEmpty()||textFieldNotificationDate.getText().isEmpty()){


					JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "Invalid or incomplete input data! Please, verify your informtion.", "", 1);



				}
				else{
					int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");
					if(sure==0){
						String notificationDate= "'"+textFieldNotificationDate.getText()+"'";
						String h_status= null;
						if(handicapYes.isSelected()){
							h_status= "true";
						}
						else{
							h_status= "false";
						}


						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
						Calendar cal = Calendar.getInstance();

						String creationDate= "'"+dateFormat.format(cal.getTime())+"'";
						String createdBy= ""+HKJ_SisCA_MainPage.loggedUsernaneWith+"";

						String query1= "UPDATE sisca_notification SET sisca_notification_date="+notificationDate + ", sisca_notification_editedby= "+createdBy+" , sisca_notification_editdate="+creationDate
								+" where sisca_notification_id='"+currentPermissionInfo.get(46)+"'";

						String query2= "UPDATE sisca_applicant SET sisca_applicant_first_name="+"'"+textFieldAFirstName.getText()+"' , "
								+ "sisca_applicant_last_name="+"'"+textFieldALastName.getText()+"' , "
								+ "sisca_applicant_phone_number="+"'"+textFieldPhone.getText()+"' , "
								+ "sisca_applicant_email="+"'"+textFieldEmail.getText()+"' , "
								+ "sisca_applicant_license_number="+"'"+textFieldLicenseNumber.getText()+"' , "
								+ "sisca_applicant_handicap_status="+"'"+h_status+"'"+
								", sisca_applicant_editedby= "+createdBy+" , sisca_applicant_editdate="+creationDate
								+ "where sisca_applicant_id='"+currentPermissionInfo.get(32)+"'";

						String query3= "UPDATE sisca_vehicle SET "
								+ "sisca_vehicle_vin="+"'"+textFieldVIN.getText()+"' , "
								+ "sisca_vehicle_plate="+"'"+textFieldPlate.getText()+"' , "
								+ "sisca_vehicle_country="+"'"+(String) countryComboBox.getSelectedItem()+"' , "
								+ "sisca_vehicle_brand="+"'"+textFieldBrand.getText()+"' , "
								+ "sisca_vehicle_model="+"'"+textFieldModel.getText()+"' , "
								+ "sisca_vehicle_year="+"'"+textFieldYear.getText()+"' , "
								+ "sisca_vehicle_color="+"'"+textFieldColor.getText()+"' , "
								+ "sisca_vehicle_owner_first_name="+"'"+textFieldOwnerFirstName.getText()+"' , "
								+ "sisca_vehicle_owner_last_name="+"'"+textFieldOwnerLastName.getText()+"'"+
								", sisca_vehicle_editedby= "+createdBy+" , sisca_vehicle_editdate="+creationDate
								+ "where sisca_vehicle_id='"+currentPermissionInfo.get(15)+"'";

						String query4= "UPDATE sisca_permission SET "
								+ "sisca_permission_tag_number="+"'"+textFieldTagNumber.getText()+"' , "
								+ "sisca_permission_expiration_date="+"'"+textFieldExpirationDate.getText()+"', "
								+ "sisca_permission_authorization_type="+"'"+(String) authirizationTypesComboBox.getSelectedItem()+"' "+
								", sisca_permission_editedby= "+createdBy+" , sisca_permission_editdate="+creationDate
								+ "where sisca_permission_id='"+currentPermissionInfo.get(0)+"'";


						System.out.println("Query:" +query1);
						try {
							dbman= new DBManager();
							dbman.insertDB(query1);
							dbman.insertDB(query2);
							dbman.insertDB(query3);
							dbman.insertDB(query4);


							//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
							// SEND TAG TO BE WRITTEN TO ALL SADS
							//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

							String query5= "Select sisca_authorization_id, sisca_authorization_uncondtional_entry from sisca_authorization where sisca_authorization_name ~* '"+(String) authirizationTypesComboBox.getSelectedItem()+"'";


							ArrayList result= new ArrayList();
							try {
								result= dbman.getNotificationsInformation(query5);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							System.out.println("RESULT: "+result);

							Object num = ((List) result.get(0)).get(0);
							Object num3 = ((List) result.get(0)).get(1);

							String unconditional= (String) num3;
							int authorization_id= Integer.parseInt((String) num);


							Boolean uncoditionalEntry=false;

							if(unconditional.equals("t")){
								uncoditionalEntry=true;
							}

							SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd"); //"mm.dd.yy"
							String expDate = textFieldExpirationDate.getText();
							//Date date = (java.sql.Date) dateFormat1.parse(expDate);


							//	dateFormat1.format(date);
							//	String expDate2 = dateFormat1.format(date);

							//Date date = (Date) dateFormat1.parse(textFieldExpirationDate.getText());

							Authorization authorization= new Authorization(authorization_id, (String)authirizationTypesComboBox.getSelectedItem(),uncoditionalEntry);
							Tag tagToBeSend= new Tag((String) textFieldTagNumber.getText(),authorization, textFieldExpirationDate.getText());
							TagListUpdateContainer tluc = new TagListUpdateContainer(tagToBeSend, TagUpdateType.AddUpdate, TagUpdateListName.NewTagsList);


							String query= "Select sisca_sad_name, sisca_sad_direction, sisca_parking_name from (((sisca_sad natural join sisca_sad_parking_list) natural join sisca_parking) natural join sisca_authorization_parking_list) natural join sisca_authorization where sisca_sad_active='true' and  sisca_sad.sisca_sad_id=sisca_sad_parking_list.sisca_sad_id and sisca_sad_parking_active='true' and sisca_parking.sisca_parking_id= sisca_sad_parking_list.sisca_parking_id and sisca_parking.sisca_parking_id= sisca_authorization_parking_list.sisca_parking_id and sisca_authorization_parking_active= 'true' and sisca_authorization.sisca_authorization_id= sisca_authorization_parking_list.sisca_authorization_id and sisca_authorization_id = '"+authorization_id+"'";

							//System.out.println(query);

							ArrayList sadList= new ArrayList();

							try {
								sadList= dbman.getNotificationsInformation(query);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//System.out.println("SAD LIST: "+ sadList+ " Size: "+sadList.size());

							System.out.println(" TAG INFORMATION \n");
							System.out.println("TagListUpdateContainer: TAG-> Expiration Date["+tluc.getReceivedTag().getExpirationDate()+"] Tag Number["+tluc.getReceivedTag().getTagID()+"] Authorizatio Name: ["+tluc.getReceivedTag().getAuthorizationType().getAuthorizationName()
									+"] \n Update List Name:"+tluc.getTagUpdateListName().toString()+" \n Tag Update Type:"+tluc.getTagUpdateType().toString());

							for(int i=0; i<sadList.size();i++){
								String sadID= ""+((List<Object>) sadList.get(i)).get(0);
								System.out.println("Adding TAG to sad: '"+sadID + "'");
								//HKJ_SisCA_MainPage.cmcas.sendTagListUpdate(sadID,tluc);
							}

							JOptionPane.showMessageDialog(null, "Tag sent to SAD's successfully.");

							HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView(textFieldTagNumber.getText(), currentTagID));
							HKJ_SisCA_MainPage.frame.pack(); 
							HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());


						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "Invalid Tag Number or Date Format!", "", 1);
						}


					}
				}



			}
		});
		editAndRemovePanel.add(editButton, "cell 0 0");

		JButton btnRemove = new JButton("Cancel");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView(currentTagNumber, currentTagID));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		editAndRemovePanel.add(btnRemove, "cell 26 0");


		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelEditPermission= new JPanel();
		windowPanelEditPermission.setLayout(new BorderLayout(0, 0));
		windowPanelEditPermission.add(menuPanelEditPermission, BorderLayout.NORTH);
		windowPanelEditPermission.add(leftPanelEditPermission, BorderLayout.WEST);
		windowPanelEditPermission.add(mainPanelEditPermission, BorderLayout.CENTER);

		return windowPanelEditPermission;
	}

	private static JPanel editSpecialPermissionView(String crtTagNumber, Integer crtTagID) {

		final String currentTagNumber= crtTagNumber;
		final Integer currentTagID= crtTagID;

		final JTextField textFieldTagNumber;
		final JTextField textExpirationDate;

		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelEditSpecialPermission = new JPanel();
		menuPanelEditSpecialPermission.setBackground(new Color(255,239,80));
		menuPanelEditSpecialPermission.setPreferredSize(new Dimension(10, 30));
		menuPanelEditSpecialPermission.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(400, 10));
		menuPanelEditSpecialPermission.add(menuOptionsPanel, BorderLayout.WEST);
		menuOptionsPanel.setLayout(null);

		JLabel homeLabel = new JLabel(" Home");
		homeLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		homeLabel.setBounds(0, -11, 68, 53);
		menuOptionsPanel.add(homeLabel);
		homeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(HKJ_SisCA_MainPage.windowPanel); // Wrong Way! =S
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		homeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homeLabel.setForeground((java.awt.Color) null);
		homeLabel.setFont(new Font("Dialog", Font.BOLD, 14));

		JLabel PermissionManagerLabel = new JLabel("Permission");
		PermissionManagerLabel.setPreferredSize(new Dimension(30, 16));
		PermissionManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		PermissionManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		PermissionManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PermissionManagerLabel.setForeground((java.awt.Color) null);
		PermissionManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		PermissionManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		PermissionManagerLabel.setBounds(66, -11, 95, 53);
		menuOptionsPanel.add(PermissionManagerLabel);

		JLabel editSpecialPermissionLabel = new JLabel("Edit Special Permission");
		editSpecialPermissionLabel.setOpaque(true);
		editSpecialPermissionLabel.setBackground(new Color (220,220,220));
		editSpecialPermissionLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				HKJ_SisCA_MainPage.frame.setContentPane(specialPermissionInformationView(currentTagNumber, currentTagID));
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		editSpecialPermissionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		editSpecialPermissionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editSpecialPermissionLabel.setForeground((java.awt.Color) null);
		editSpecialPermissionLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		editSpecialPermissionLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		editSpecialPermissionLabel.setBounds(159, -11, 210, 53);
		menuOptionsPanel.add(editSpecialPermissionLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelEditSpecialPermission.add(logOutPanel, BorderLayout.EAST);
		logOutPanel.setLayout(null);

		JLabel logOutLabel = new JLabel("LogOut");
		logOutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.setActiveUsername(null);
				HKJ_SisCA_MainPage.frame.setContentPane(LogInManager.standByView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		logOutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logOutLabel.setBounds(0, -11, 75, 52);
		logOutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logOutLabel.setForeground((java.awt.Color) null);
		logOutLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		logOutLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		logOutPanel.add(logOutLabel);

		JPanel userNamePanel = new JPanel();
		userNamePanel.setPreferredSize(new Dimension(200, 10));
		menuPanelEditSpecialPermission.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel(HKJ_SisCA_MainPage.getActiveUsername());
		userNamePanel.add(userNameLabel, BorderLayout.EAST);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground((java.awt.Color) null);
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));

		JLayeredPane liveSystemPanel2 = new JLayeredPane();
		liveSystemPanel2.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));


		/////////////////////////////////////////////////////////
		//Left Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel leftPanelSpecialPermissionInformation = new JPanel();
		leftPanelSpecialPermissionInformation.setBackground(new Color(245,245,245));
		leftPanelSpecialPermissionInformation.setPreferredSize(new Dimension(390, 10));
		leftPanelSpecialPermissionInformation.setLayout(new BorderLayout(0, 0));

		final JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanelSpecialPermissionInformation.add(scrollPane);

		JLayeredPane liveSystemPanel21 = new JLayeredPane();
		scrollPane.setViewportView(liveSystemPanel);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel21.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));



		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px]"));


		//LeftPanel Labels
		JLabel SpecialPermissionListLabel = new JLabel("SPECIAL PERMISSIONS LIST");
		liveSystemPanel.add(SpecialPermissionListLabel, "cell 0 0,alignx center,aligny top");
		SpecialPermissionListLabel.setPreferredSize(new Dimension(100, 50));
		SpecialPermissionListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SpecialPermissionListLabel.setForeground(java.awt.Color.BLACK);
		SpecialPermissionListLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		ArrayList<Object> PermissionUsernameListView = getSpeciaPermissionList();
		int position = 1;
		for(int i=0; i<PermissionUsernameListView.size() && i<10 ; i++){
			position = position+2;
			final JLabel permissionLabel = new JLabel(PermissionUsernameListView.get(i).toString());
			permissionLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String query1= "Select sisca_special_permission_id from sisca_special_permission where sisca_special_permission_tag_number="+"'"+permissionLabel.getText()+"' and sisca_special_permission_active='true'";

					String[] list = null;
					try {
						dbman= new DBManager();
						list = ((DBManager) dbman).getPermissionInfoFromDB(query1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					HKJ_SisCA_MainPage.frame.setContentPane(specialPermissionInformationView(permissionLabel.getText(), Integer.parseInt(list[0])));
					HKJ_SisCA_MainPage.frame.pack();
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			});
			permissionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			liveSystemPanel.add(permissionLabel, "cell 0 "+ position +" ,alignx left,aligny top");
		}


		//Separators
		JSeparator separator = new JSeparator();
		liveSystemPanel.add(separator, "cell 0 4,growx,aligny top");

		JSeparator separator_1 = new JSeparator();
		liveSystemPanel.add(separator_1, "cell 0 6,growx,aligny top");

		JSeparator separator_2 = new JSeparator();
		liveSystemPanel.add(separator_2, "cell 0 8,growx,aligny top");

		JSeparator separator_3 = new JSeparator();
		liveSystemPanel.add(separator_3, "cell 0 10,growx,aligny top");

		JSeparator separator_4 = new JSeparator();
		liveSystemPanel.add(separator_4, "cell 0 12,growx,aligny top");

		JSeparator separator_5 = new JSeparator();
		liveSystemPanel.add(separator_5, "cell 0 14,growx,aligny top");

		JSeparator separator_7 = new JSeparator();
		liveSystemPanel.add(separator_7, "cell 0 18,growx,aligny top");

		JSeparator separator_6 = new JSeparator();
		liveSystemPanel.add(separator_6, "cell 0 16,growx,aligny top");

		JSeparator separator_8 = new JSeparator();
		liveSystemPanel.add(separator_8, "cell 0 20,growx,aligny top");

		JSeparator separator_9 = new JSeparator();
		liveSystemPanel.add(separator_9, "cell 0 22,growx,aligny top");

		JSeparator separator_10 = new JSeparator();
		liveSystemPanel.add(separator_10, "cell 0 2,growx,aligny top");

		JPanel panel_100 = new JPanel();
		panel_100.setBackground(new Color(245,245,245));
		liveSystemPanel.add(panel_100, "cell 0 1,growx,aligny top");
		panel_100.setLayout(new BoxLayout(panel_100, BoxLayout.X_AXIS));

		JLabel searchLabel = new JLabel("Search:");
		panel_100.add(searchLabel);
		searchLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		final JTextField searchTextField = new JTextField();
		/**
		 * CODIDO DE LA BARRA DE SEARCH
		 */
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Update del JList
				updateSpecialJList= false;
				availableSpecialPermissionModelList.clear();

				String searchField="'%"+searchTextField.getText()+"%'";
				String query= "Select * from sisca_special_permission where sisca_special_permission_tag_number ilike"+searchField;
				try {
					dbman= new DBManager();
					availableSpecialPermission= dbman.getFromDB(query);
					availableSpecialPermission= dbman.getAvailableSpecialPermission(availableSpecialPermission);
					for(int i=0; i<availablePermission.size(); i++){
						availableSpecialPermissionModelList.addElement(availableSpecialPermission.get(i));
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		panel_100.add(searchTextField);
		searchTextField.setColumns(10);

		JPanel viewAndAddBynPanel = new JPanel();
		viewAndAddBynPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(viewAndAddBynPanel, "cell 0 23,alignx left,aligny top");
		viewAndAddBynPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton viewAllButton = new JButton("View All");
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButtonPermission = new JButton("Add New Permission");
		addNewButtonPermission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addPermissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(addNewButtonPermission);

		/////////////////////////////////////////////////////////
		//Main Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel mainPanelEditSpecialPermission = new JPanel();
		mainPanelEditSpecialPermission.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelEditSpecialPermission.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelEditSpecialPermission.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelEditSpecialPermission.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelEditSpecialPermission.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelEditSpecialPermission.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[59.00][849.00px]", "[][][19px][][][][][][][16.00][][19px][-40.00px]"));

		/////////////////////////////////////////////////////////
		// Query for Fill Permission Information
		/////////////////////////////////////////////////////////
		String processTagID="'"+currentTagID+"'";

		String query= "Select * from sisca_special_permission where sisca_special_permission_id = "+processTagID;

		System.out.println("Printing Query:"+query );

		ArrayList<String> availablePermission = null ;


		try {
			dbman= new DBManager();
			availablePermission = new ArrayList<String>();
			availablePermission= dbman.getAllPermissionInfoFromDB(query);
			speacial_permission_id=availablePermission.get(1);

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblEditSpecialPermission = new JLabel("Edit Special Permission");
		lblEditSpecialPermission.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		centerPanel.add(lblEditSpecialPermission, "cell 1 0,alignx center");

		JLabel lblTagNumber = new JLabel("Tag Number: ");
		centerPanel.add(lblTagNumber, "flowx,cell 1 3");

		JLabel lblAuthorizationType = new JLabel("Authorization Type: ");
		centerPanel.add(lblAuthorizationType, "flowx,cell 1 5");

		JLabel lblExpirationDate = new JLabel("Expiration Date (YYYY-MM-DD): ");
		centerPanel.add(lblExpirationDate, "flowx,cell 1 7");

		JPanel editAndRemovePanel = new JPanel();
		editAndRemovePanel.setBackground(new Color(250,250,250));
		centerPanel.add(editAndRemovePanel, "cell 1 11,grow");
		editAndRemovePanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));



		DefaultComboBoxModel modelAT = new DefaultComboBoxModel();

		try {
			dbman= new DBManager();
			ArrayList list = dbman.getFromDB("Select * from sisca_authorization");
			list= dbman.getAvailableAuthorizationTypes(list);
			for( int i=0; i<list.size(); i++){
				modelAT.addElement(list.get(i));
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		final JComboBox comboBoxAuthorizationType = new JComboBox(modelAT);
		comboBoxAuthorizationType.setSelectedItem(availablePermission.get(3));
		comboBoxAuthorizationType.setPreferredSize(new Dimension(250, 27));
		centerPanel.add(comboBoxAuthorizationType, "cell 1 5,alignx center");


		textFieldTagNumber = new JTextField(availablePermission.get(1));
		textFieldTagNumber.setPreferredSize(new Dimension(100, 28));
		centerPanel.add(textFieldTagNumber, "cell 1 3,alignx center");
		textFieldTagNumber.setColumns(10);


		textExpirationDate = new JTextField(availablePermission.get(2));
		centerPanel.add(textExpirationDate, "cell 1 7");
		textExpirationDate.setColumns(10);




		//EDIT PERMISSION
		JButton OkButton = new JButton("Ok");
		OkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				if(textExpirationDate.getText().isEmpty()||textFieldTagNumber.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Invalid or incomplete input data! Please, verify your informtion.");
				}
				else{
					int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");
					if(sure==0){

						String expirationnDate= "'"+textExpirationDate.getText()+"'";
						String tagNumber= "'"+textFieldTagNumber.getText()+"'";
						String authorizationType= "'"+comboBoxAuthorizationType.getSelectedItem()+"'";


						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
						Calendar cal = Calendar.getInstance();

						String creationDate= "'"+dateFormat.format(cal.getTime())+"'";
						String createdBy= ""+HKJ_SisCA_MainPage.loggedUsernaneWith+"";

						String query1= "UPDATE sisca_special_permission SET sisca_special_permission_expiration_date="+expirationnDate 
								+ ", sisca_special_permission_tag_number= "+tagNumber
								+ ", sisca_special_permission_authorization_type= "+authorizationType
								+ ", sisca_special_permission_editedby= "+createdBy
								+" , sisca_special_permission_editdate="+creationDate
								+" where sisca_special_permission_id='"+currentTagID+"'";


						try {
							dbman= new DBManager();
							dbman.insertDB(query1);

						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						HKJ_SisCA_MainPage.frame.setContentPane(specialPermissionInformationView(textFieldTagNumber.getText(), currentTagID));
						HKJ_SisCA_MainPage.frame.pack(); 
						HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
					}
				}









			}
		});
		editAndRemovePanel.add(OkButton, "cell 0 0");


		// REMOVE SPECIAL PERMISSION
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				HKJ_SisCA_MainPage.frame.setContentPane(editSpecialPermissionView(currentTagNumber, currentTagID));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		editAndRemovePanel.add(btnCancel, "cell 26 0");








		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelEditSpecialPermission= new JPanel();
		windowPanelEditSpecialPermission.setLayout(new BorderLayout(0, 0));
		windowPanelEditSpecialPermission.add(menuPanelEditSpecialPermission, BorderLayout.NORTH);
		windowPanelEditSpecialPermission.add(leftPanelSpecialPermissionInformation, BorderLayout.WEST);
		windowPanelEditSpecialPermission.add(mainPanelEditSpecialPermission, BorderLayout.CENTER);

		return windowPanelEditSpecialPermission;
	}


	/** Get Permission Auxiliary Method
	 *  Return an Array List of all information of a specific permission
	 *   
	 * 	@return permissionListInformation  Array List of all permission information 
	 */
	private static ArrayList<Object> getPermissionList() {


		ArrayList<Object> permissionListInformation = new ArrayList<Object>();
		try {
			ArrayList<Object> PermissionListFromDB = dbman.getFromDB("select * from sisca_permission where sisca_permission_active='true' order by sisca_permission_tag_number");
			String[] keyValue;
			String userName = null;
			for(int i=0; i<PermissionListFromDB.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) PermissionListFromDB.get(i)).size(); k++){
					//result = 1:A
					Object result = ((List<Object>) PermissionListFromDB.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_permission_tag_number")){
						userName = (String) keyValue[1];
					}
				}
				permissionListInformation.add(userName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return permissionListInformation;
	}


	private static ArrayList<Object> getSpeciaPermissionList() {
		ArrayList<Object> permissionListInformation = new ArrayList<Object>();
		try {
			ArrayList<Object> PermissionListFromDB = dbman.getFromDB("select * from sisca_special_permission where sisca_special_permission_active='true' order by sisca_special_permission_tag_number");
			String[] keyValue;
			String userName = null;
			for(int i=0; i<PermissionListFromDB.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) PermissionListFromDB.get(i)).size(); k++){
					//result = 1:A
					Object result = ((List<Object>) PermissionListFromDB.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_special_permission_tag_number")){
						userName = (String) keyValue[1];
					}
				}
				permissionListInformation.add(userName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return permissionListInformation;
	}

}
