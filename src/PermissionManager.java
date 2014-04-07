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
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import databases.DBManager;
import net.miginfocom.swing.MigLayout;


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
		searchAndAddPanel.setLayout(new MigLayout("", "[48px][289px][34.00px][][][][][][][][][][][][][][][][][][][][][][][]", "[29px]"));

		JLabel searchLabel = new JLabel("Search:");
		searchAndAddPanel.add(searchLabel, "cell 0 0,alignx left,aligny center");
		searchLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));


		////////////////////
		// Update JList by textField and Go Button
		////////////////////
		
		
		final JTextField textFieldSearch = new JTextField();
		textFieldSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				availablePermission= new ArrayList<Object>();
				availablePermissionModelList= new DefaultListModel(); 
				
				// Update del JList
				updateJList= false;
				availablePermissionModelList.clear();
				System.out.println("Hola");
				String searchField="'"+textFieldSearch.getText()+"'";
				
				System.out.println("text field"+textFieldSearch.getText());
				String query= "Select * from sisca_permission where sisca_permission_tag_number ~*"+searchField;
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
				
				// Update del JList
				updateJList= false;
				availablePermissionModelList.clear();
				System.out.println("Hola");
				String searchField="'"+textFieldSearch.getText()+"'";
				
				System.out.println("text field"+textFieldSearch.getText());
				String query= "Select * from sisca_permission where sisca_permission_tag_number ~*"+searchField;
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
		searchAndAddPanel.add(addNewPermissionBtn, "cell 25 0,alignx left,aligny top");
		
		JPanel PermissionListPanel = new JPanel();
		PermissionListPanel.setBackground(new Color(250,250,250));

		centerPanel.add(PermissionListPanel, "cell 0 1,alignx center,growy");
		PermissionListPanel.setLayout(new MigLayout("", "[724px]", "[360px]"));
		JScrollPane PermissionScrollPane = new JScrollPane();
		PermissionListPanel.add(PermissionScrollPane, "cell 0 0,grow");
		
		
		PermissionList= new JList();
		
		if (updateJList== true){
			
			System.out.println("holaaaaaaa");
			availablePermission= new ArrayList<Object>();
			availablePermissionModelList= new DefaultListModel();
			String query= "Select * from sisca_permission ORDER BY sisca_permission_tag_number";
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
			PermissionList.setModel(availablePermissionModelList);
		}
		else{
			updateJList=true;
			PermissionList.setModel(availablePermissionModelList);
		}
		
		
		PermissionList.setSelectionForeground(UIManager.getColor("Button.darkShadow"));
		PermissionList.setSelectionBackground(UIManager.getColor("Button.background"));
		PermissionList.addMouseListener(new MouseAdapter() {
			

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					
					//System.out.println("Selected Tag:"+PermissionList.getSelectedValue());
					HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView((String)PermissionList.getSelectedValue())); // Wrong Way! =S
					HKJ_SisCA_MainPage.frame.pack();
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			}
		});
		PermissionScrollPane.setViewportView(PermissionList);
		


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
	private static JPanel permissionInformationView( String tagNumber){
		
		final String currentTagNumber = tagNumber;

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
		listPermissionLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView(currentTagNumber));
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
					HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView(permissionLabel.getText()));
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
				String query= "Select * from sisca_permission where sisca_permission_tag_number ~*"+searchField;
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
		centerPanel.setLayout(new MigLayout("", "[849.00px]", "[][12px][][19px][12px][19px][12px][][][][][][][][][][][][][16.00][][19px][-40.00px]"));

		/////////////////////////////////////////////////////////
		// Query for Fill Permission Information
		/////////////////////////////////////////////////////////
		String processTagNumber="'"+currentTagNumber+"'";
		
		String query= "Select * from (sisca_permission natural join sisca_vehicle) natural join sisca_applicant"+
				" where sisca_permission_vehicle_id= sisca_vehicle_id and sisca_permission_applicant_id=sisca_applicant_id "
				+ " and sisca_permission_tag_number = "+processTagNumber;
		
		System.out.println("Printing Query:"+query );
		String[] availablePermission = null;
		
		try {
			dbman= new DBManager();
			availablePermission=null ;
						
			availablePermission= dbman.getAllPermissionInfoFromDB(query);
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JLabel lblNewLabelApplicant = new JLabel(availablePermission[17]+"\t"+availablePermission[18]);
		lblNewLabelApplicant.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		centerPanel.add(lblNewLabelApplicant, "cell 0 0,alignx center");
		
		JLabel lblNewLabelPermissionInformation = new JLabel("PERMISSION INFORMATION");
		centerPanel.add(lblNewLabelPermissionInformation, "cell 0 2,alignx center");
		
		JLabel lblNewLabelTagNumber = new JLabel("Tag Number: "+ availablePermission[1]);
		centerPanel.add(lblNewLabelTagNumber, "cell 0 3");
		
		JLabel lblNewLabelDeliveryDate = new JLabel("Delivery Date: "+availablePermission[2] );
		centerPanel.add(lblNewLabelDeliveryDate, "cell 0 4");
		
		JLabel lblNewLabelExpirationDate = new JLabel("Expiration Date: " +availablePermission[3]);
		centerPanel.add(lblNewLabelExpirationDate, "cell 0 5");
		
		JLabel lblNewLabelNotificationDate = new JLabel("Notification Date: " +availablePermission[4]);
		centerPanel.add(lblNewLabelNotificationDate, "cell 0 6");
		
		JLabel lblNewLabelApplicantInformation = new JLabel("APLICANT INFORMATION");
		centerPanel.add(lblNewLabelApplicantInformation, "cell 0 7,alignx center");
		
		JLabel lblNewLabelPhone = new JLabel("Phone: " +availablePermission[19]);
		centerPanel.add(lblNewLabelPhone, "cell 0 8");
		
		JLabel lblNewLabelEmail = new JLabel("Email: " +availablePermission[20]);
		centerPanel.add(lblNewLabelEmail, "cell 0 9");
		
		if(availablePermission[22]== "t"){
			JLabel lblNewLabelHandicap = new JLabel("Handicap Permission? Yes");
			centerPanel.add(lblNewLabelHandicap, "cell 0 10");
		}
		else{
			JLabel lblNewLabelHandicap = new JLabel("Handicap Permission? No");
			centerPanel.add(lblNewLabelHandicap, "cell 0 10");
		}

		
		JLabel lblNewLabelLicenseNumber = new JLabel("License Number: "+ availablePermission[21]);
		centerPanel.add(lblNewLabelLicenseNumber, "cell 0 11");
		
		JLabel lblNewLabelVehicleInformation = new JLabel("VEHICLE INFORMATION");
		centerPanel.add(lblNewLabelVehicleInformation, "cell 0 12,alignx center");
		
		JLabel lblNewLabelVIN = new JLabel("VIN: "+availablePermission[8] );
		centerPanel.add(lblNewLabelVIN, "cell 0 13");
		
		JLabel lblNewLabelPlate = new JLabel("Plate: " +availablePermission[9]);
		centerPanel.add(lblNewLabelPlate, "cell 0 14");
		
		JLabel lblNewLabelBrand = new JLabel("Brand: "+availablePermission[12]);
		centerPanel.add(lblNewLabelBrand, "cell 0 15");
		
		JLabel lblNewLabelModel = new JLabel("Model: "+availablePermission[11]);
		centerPanel.add(lblNewLabelModel, "cell 0 16");
		
		JLabel lblNewLabelCountry = new JLabel("Country: "+availablePermission[10]);
		centerPanel.add(lblNewLabelCountry, "cell 0 17");
		
		JLabel lblNewLabelYear = new JLabel("Year: "+availablePermission[13]);
		centerPanel.add(lblNewLabelYear, "cell 0 18");
		
		JLabel lblNewLabelColor = new JLabel("Color: "+availablePermission[14]);
		centerPanel.add(lblNewLabelColor, "cell 0 19");
		
		JLabel lblNewLabelOwnerName = new JLabel("Owner's Name: "+availablePermission[15]+"\t"+availablePermission[16]);
		centerPanel.add(lblNewLabelOwnerName, "cell 0 20");

		JPanel editAndRemovePanel = new JPanel();
		editAndRemovePanel.setBackground(new Color(250,250,250));
		centerPanel.add(editAndRemovePanel, "cell 0 21,grow");
		editAndRemovePanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));

		
		//////////////////////////////////////////
		// Edit and Remove Buttons
		//////////////////////////////////////
		
		
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(editPermissionView(currentTagNumber));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		editAndRemovePanel.add(editButton, "cell 0 0");

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");
				
				
				if(sure==0){
					
					
					String query2= "Select * from (sisca_permission natural join sisca_vehicle) natural join sisca_applicant"+
							" where sisca_permission_vehicle_id= sisca_vehicle_id and sisca_permission_applicant_id=sisca_applicant_id "
							+ " and sisca_permission_tag_number = "+"'"+currentTagNumber+"'";
					
					String[] availablePermission = null;
					
					try {
						dbman= new DBManager();
						availablePermission=null ;
						availablePermission= dbman.getAllPermissionInfoFromDB(query2);
						
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					int v_id=Integer.parseInt(availablePermission[6]);
					int a_id=Integer.parseInt(availablePermission[7]);
					
					
					String queryA= "Delete from sisca_permission where sisca_permission_tag_number="+"'"+currentTagNumber+"'";
					String queryB= "Delete from sisca_vehicle where sisca_vehicle_id="+"'"+v_id+"'";
					String queryC= "Delete from sisca_applicant where sisca_applicant_id="+"'"+a_id+"'";
					
					try {
						dbman= new DBManager();
						
						dbman.updatetDB(queryA);
						dbman.updatetDB(queryB);
						dbman.updatetDB(queryC);
						
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
					HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView(currentTagNumber));
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
		final  JTextField textFieldOwnerFirstName;
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
					HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView(permissionLabel.getText()));
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
				String query= "Select * from sisca_permission where sisca_permission_tag_number ~*"+searchField;
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
		centerPanel.setLayout(new MigLayout("", "[849.00px]", "[][12px][][19px][12px][19px][12px][][][][][][][][][][][][][][16.00][][19px][-40.00px]"));


		
		JLabel lblNewLabelApplicant = new JLabel("ADD NEW PERMISSION\n");
		lblNewLabelApplicant.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		centerPanel.add(lblNewLabelApplicant, "cell 0 0,alignx center");
		
		JLabel lblNewLabelPermissionInformation = new JLabel("PERMISSION INFORMATION");
		lblNewLabelPermissionInformation.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		centerPanel.add(lblNewLabelPermissionInformation, "cell 0 2,alignx left");
		
		JLabel lblNewLabelTagNumber = new JLabel("Tag Number:");
		centerPanel.add(lblNewLabelTagNumber, "flowx,cell 0 3,alignx left");
		
		JLabel lblNewLabelExpirationDate = new JLabel("Expiration Date:");
		centerPanel.add(lblNewLabelExpirationDate, "flowx,cell 0 5");
		
		JLabel lblNewLabelApplicantInformation = new JLabel("APLICANT INFORMATION");
		lblNewLabelApplicantInformation.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		centerPanel.add(lblNewLabelApplicantInformation, "cell 0 7,alignx left");
		
		JLabel lblApplicantsFirstName = new JLabel("Applicant's First Name:");
		centerPanel.add(lblApplicantsFirstName, "flowx,cell 0 9");
		
		JLabel lblNewLabelPhone = new JLabel("Phone:");
		centerPanel.add(lblNewLabelPhone, "flowx,cell 0 11");
		
		textFieldPhone = new JTextField();
		centerPanel.add(textFieldPhone, "cell 0 11");
		textFieldPhone.setColumns(10);
		
		JLabel lblNewLabelEmail = new JLabel("Email:");
		centerPanel.add(lblNewLabelEmail, "cell 0 11");
		
		textFieldEmail = new JTextField();
		centerPanel.add(textFieldEmail, "cell 0 11");
		textFieldEmail.setColumns(10);
		
		JLabel lblNewLabelHandicap = new JLabel("Handicap Permission?");
		centerPanel.add(lblNewLabelHandicap, "cell 0 11");
		
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
		
		textFieldTagNumber = new JTextField();
		textFieldTagNumber.setPreferredSize(new Dimension(500, 28));
		centerPanel.add(textFieldTagNumber, "cell 0 3");
		textFieldTagNumber.setColumns(10);
		
		JLabel lblNewLabelDeliveryDate = new JLabel("Delivery Date:");
		centerPanel.add(lblNewLabelDeliveryDate, "cell 0 3");
		
		textFieldDeliveryDate = new JTextField();
		centerPanel.add(textFieldDeliveryDate, "cell 0 3");
		textFieldDeliveryDate.setColumns(10);
		
		textFieldExpirationDate = new JTextField();
		centerPanel.add(textFieldExpirationDate, "cell 0 5");
		textFieldExpirationDate.setColumns(10);
		
		JLabel lblNewLabelNotificationDate = new JLabel("Notification Date:");
		centerPanel.add(lblNewLabelNotificationDate, "cell 0 5");
		
		final JCheckBox handicapYes = new JCheckBox("Yes");
		centerPanel.add(handicapYes, "cell 0 11");
		
		JCheckBox handicapNo = new JCheckBox("No");
		centerPanel.add(handicapNo, "cell 0 11");
		
		textFieldLicenseNumber = new JTextField();
		centerPanel.add(textFieldLicenseNumber, "cell 0 12");
		textFieldLicenseNumber.setColumns(10);
		
		textFieldVIN = new JTextField();
		centerPanel.add(textFieldVIN, "cell 0 14");
		textFieldVIN.setColumns(10);
		
		JLabel lblNewLabelPlate = new JLabel("Plate:");
		centerPanel.add(lblNewLabelPlate, "cell 0 14");
		
		textFieldPlate = new JTextField();
		centerPanel.add(textFieldPlate, "cell 0 14");
		textFieldPlate.setColumns(10);
		
		textFieldBrand = new JTextField();
		centerPanel.add(textFieldBrand, "cell 0 16");
		textFieldBrand.setColumns(10);
		
		JLabel lblNewLabelModel = new JLabel("Model:");
		centerPanel.add(lblNewLabelModel, "cell 0 16");
		
		textFieldModel = new JTextField();
		centerPanel.add(textFieldModel, "cell 0 16");
		textFieldModel.setColumns(10);
		
		textFieldCountry = new JTextField();
		centerPanel.add(textFieldCountry, "cell 0 18");
		textFieldCountry.setColumns(10);
		
		JLabel lblNewLabelYear = new JLabel("Year:");
		centerPanel.add(lblNewLabelYear, "cell 0 18");
		
		textFieldYear = new JTextField();
		centerPanel.add(textFieldYear, "cell 0 18");
		textFieldYear.setColumns(10);
		
		textFieldColor = new JTextField();
		centerPanel.add(textFieldColor, "cell 0 20");
		textFieldColor.setColumns(10);
		
		textFieldOwnerFirstName = new JTextField();
		centerPanel.add(textFieldOwnerFirstName, "cell 0 21");
		textFieldOwnerFirstName.setColumns(10);
		
		JLabel lblOwnersLastName = new JLabel("Owner's Last Name:");
		centerPanel.add(lblOwnersLastName, "cell 0 21");
		
		textFieldOwnerLastName = new JTextField();
		centerPanel.add(textFieldOwnerLastName, "cell 0 21");
		textFieldOwnerLastName.setColumns(10);
		
		textFieldNotificationDate = new JTextField();
		centerPanel.add(textFieldNotificationDate, "cell 0 5");
		textFieldNotificationDate.setColumns(10);
		
		textFieldAFirstName = new JTextField();
		centerPanel.add(textFieldAFirstName, "cell 0 9");
		textFieldAFirstName.setColumns(10);
		
		JLabel lblApplicantsLastName = new JLabel("Applicant's Last Name:");
		centerPanel.add(lblApplicantsLastName, "cell 0 9");
		
		textFieldALastName = new JTextField();
		centerPanel.add(textFieldALastName, "cell 0 9");
		textFieldALastName.setColumns(10);


		JPanel editAndRemovePanel = new JPanel();
		editAndRemovePanel.setBackground(new Color(250,250,250));
		centerPanel.add(editAndRemovePanel, "cell 0 22,grow");
		editAndRemovePanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));

		JButton editButton = new JButton("Ok");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");
				if(sure==0){
					/////////////////////////////////////////////////
					// Query for insert Parking Information
					/////////////////////////////////////////////////

					int vehicle_id=-1;
					int applicant_id=-1;

					// Vehicle Information
					String vin= "'"+textFieldVIN.getText()+"'";
					String plate= "'"+textFieldPlate.getText()+"'";
					String country= "'"+textFieldCountry.getText()+"'";
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
					String delivery= "'"+textFieldDeliveryDate.getText()+"'";
					String expiration= "'"+textFieldExpirationDate.getText()+"'";
					String notification= "'"+textFieldNotificationDate.getText()+"'";

					try {
						String query1= " INSERT INTO sisca_vehicle (sisca_vehicle_vin, sisca_vehicle_plate, sisca_vehicle_country, sisca_vehicle_brand, sisca_vehicle_model, sisca_vehicle_year, "+
								"sisca_vehicle_color, sisca_vehicle_owner_first_name, sisca_vehicle_owner_last_name) VALUES (" +vin + " , "+plate + " , "+country + " , "+brand + " , "+model + " , "+year + " , "+color + " , "+o_first + " , "+o_last + " )";

						String query2= " INSERT INTO sisca_applicant (sisca_applicant_first_name, sisca_applicant_last_name, sisca_applicant_phone_number, sisca_applicant_email, sisca_applicant_license_number, sisca_applicant_handicap_status) VALUES ("
								+a_first + " , "+a_last + " , "+phone + " , "+email + " , "+license + " , "+h_status + " )";

						vehicle_id= dbman.insertVehicleDB(query1);
						applicant_id= dbman.insertDB(query2);

						String query3= " INSERT INTO sisca_permission (sisca_permission_tag_number, sisca_permission_delivery_date, sisca_permission_expiration_date, sisca_permission_notification_date, sisca_permission_status, sisca_permission_vehicle_id, sisca_permission_applicant_id) VALUES ("
								+tag_number + " , "+delivery + " , "+expiration + " , "+notification + " , 'true', "+vehicle_id + " , "+applicant_id + " )";

						dbman= new DBManager();

						dbman.insertDB(query3);

						System.out.println("Vehicle Query: " +query1);
						System.out.println("Applicant Query: " +query2);
						System.out.println("Permission Query: " +query3);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println("Inserted Vehicle ID:"+vehicle_id);
					HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView(textFieldTagNumber.getText()));
					HKJ_SisCA_MainPage.frame.pack(); 
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

				}

				
				
			}
		});
		editAndRemovePanel.add(editButton, "cell 0 0");

		JButton btnRemove = new JButton("Cancel");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(permissionView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			
			}
		});
		editAndRemovePanel.add(btnRemove, "cell 26 0");
		

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
	 *  
	 * 	@return menuPanelEditPermission JPanel 
	 */
	private static JPanel editPermissionView(String currentTagNumber){
		
		
		JTextField textFieldUsername;
		JTextField textFieldPassword;
		JTextField textFieldSearch;
		JTextField textFieldTagNumber;
		JTextField textFieldDeliveryDate;
		JTextField textFieldNotificationDate;
		JTextField textFieldExpirationDate;
		JTextField textFieldPhone;
		JTextField textFieldEmail;
		JTextField textFieldLicenseNumber;
		JTextField textFieldVIN;
		JTextField textFieldPlate;
		JTextField textFieldBrand;
		JTextField textFieldModel;
		JTextField textFieldCountry;
		JTextField textFieldYear;
		JTextField textFieldColor;
		JTextField textFieldOwnerFirstName;
		JTextField textFieldOwnerLastName;
		JTextField textFieldAFirstName;
		JTextField textFieldALastName;
		
		
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
					HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView(permissionLabel.getText()));
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


		
		JLabel lblNewLabelApplicant = new JLabel("EDIT PERMISSION\n");
		lblNewLabelApplicant.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		centerPanel.add(lblNewLabelApplicant, "cell 0 0,alignx center");
		
		JLabel lblNewLabelPermissionInformation = new JLabel("PERMISSION INFORMATION");
		lblNewLabelPermissionInformation.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		centerPanel.add(lblNewLabelPermissionInformation, "cell 0 2,alignx left");
		
		JLabel lblNewLabelTagNumber = new JLabel("Tag Number:");
		centerPanel.add(lblNewLabelTagNumber, "flowx,cell 0 3,alignx left");
		
		JLabel lblNewLabelExpirationDate = new JLabel("Expiration Date:");
		centerPanel.add(lblNewLabelExpirationDate, "flowx,cell 0 5");
		
		JLabel lblNewLabelApplicantInformation = new JLabel("APLICANT INFORMATION");
		lblNewLabelApplicantInformation.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		centerPanel.add(lblNewLabelApplicantInformation, "cell 0 7,alignx left");
		
		JLabel lblApplicantsFirstName = new JLabel("Applicant's First Name:");
		centerPanel.add(lblApplicantsFirstName, "flowx,cell 0 9");
		
		JLabel lblNewLabelPhone = new JLabel("Phone:");
		centerPanel.add(lblNewLabelPhone, "flowx,cell 0 11");
		
		textFieldPhone = new JTextField();
		centerPanel.add(textFieldPhone, "cell 0 11");
		textFieldPhone.setColumns(10);
		
		JLabel lblNewLabelEmail = new JLabel("Email:");
		centerPanel.add(lblNewLabelEmail, "cell 0 11");
		
		textFieldEmail = new JTextField();
		centerPanel.add(textFieldEmail, "cell 0 11");
		textFieldEmail.setColumns(10);
		
		JLabel lblNewLabelHandicap = new JLabel("Handicap Permission?");
		centerPanel.add(lblNewLabelHandicap, "cell 0 11");
		
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

		JButton editButton = new JButton("Ok");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		editAndRemovePanel.add(editButton, "cell 0 0");

		JButton btnRemove = new JButton("Cancel");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			
			}
		});
		editAndRemovePanel.add(btnRemove, "cell 26 0");
		
		textFieldTagNumber = new JTextField();
		textFieldTagNumber.setPreferredSize(new Dimension(500, 28));
		centerPanel.add(textFieldTagNumber, "cell 0 3");
		textFieldTagNumber.setColumns(10);
		
		JLabel lblNewLabelDeliveryDate = new JLabel("Delivery Date:");
		centerPanel.add(lblNewLabelDeliveryDate, "cell 0 3");
		
		textFieldDeliveryDate = new JTextField();
		centerPanel.add(textFieldDeliveryDate, "cell 0 3");
		textFieldDeliveryDate.setColumns(10);
		
		textFieldExpirationDate = new JTextField();
		centerPanel.add(textFieldExpirationDate, "cell 0 5");
		textFieldExpirationDate.setColumns(10);
		
		JLabel lblNewLabelNotificationDate = new JLabel("Notification Date:");
		centerPanel.add(lblNewLabelNotificationDate, "cell 0 5");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Yes");
		centerPanel.add(chckbxNewCheckBox, "cell 0 11");
		
		JCheckBox chckbxNo = new JCheckBox("No");
		centerPanel.add(chckbxNo, "cell 0 11");
		
		textFieldLicenseNumber = new JTextField();
		centerPanel.add(textFieldLicenseNumber, "cell 0 12");
		textFieldLicenseNumber.setColumns(10);
		
		textFieldVIN = new JTextField();
		centerPanel.add(textFieldVIN, "cell 0 14");
		textFieldVIN.setColumns(10);
		
		JLabel lblNewLabelPlate = new JLabel("Plate:");
		centerPanel.add(lblNewLabelPlate, "cell 0 14");
		
		textFieldPlate = new JTextField();
		centerPanel.add(textFieldPlate, "cell 0 14");
		textFieldPlate.setColumns(10);
		
		textFieldBrand = new JTextField();
		centerPanel.add(textFieldBrand, "cell 0 16");
		textFieldBrand.setColumns(10);
		
		JLabel lblNewLabelModel = new JLabel("Model:");
		centerPanel.add(lblNewLabelModel, "cell 0 16");
		
		textFieldModel = new JTextField();
		centerPanel.add(textFieldModel, "cell 0 16");
		textFieldModel.setColumns(10);
		
		textFieldCountry = new JTextField();
		centerPanel.add(textFieldCountry, "cell 0 18");
		textFieldCountry.setColumns(10);
		
		JLabel lblNewLabelYear = new JLabel("Year:");
		centerPanel.add(lblNewLabelYear, "cell 0 18");
		
		textFieldYear = new JTextField();
		centerPanel.add(textFieldYear, "cell 0 18");
		textFieldYear.setColumns(10);
		
		textFieldColor = new JTextField();
		centerPanel.add(textFieldColor, "cell 0 20");
		textFieldColor.setColumns(10);
		
		textFieldOwnerFirstName = new JTextField();
		centerPanel.add(textFieldOwnerFirstName, "cell 0 21");
		textFieldOwnerFirstName.setColumns(10);
		
		JLabel lblOwnersLastName = new JLabel("Owner's Last Name:");
		centerPanel.add(lblOwnersLastName, "cell 0 21");
		
		textFieldOwnerLastName = new JTextField();
		centerPanel.add(textFieldOwnerLastName, "cell 0 21");
		textFieldOwnerLastName.setColumns(10);
		
		textFieldNotificationDate = new JTextField();
		centerPanel.add(textFieldNotificationDate, "cell 0 5");
		textFieldNotificationDate.setColumns(10);
		
		textFieldAFirstName = new JTextField();
		centerPanel.add(textFieldAFirstName, "cell 0 9");
		textFieldAFirstName.setColumns(10);
		
		JLabel lblApplicantsLastName = new JLabel("Applicant's Last Name:");
		centerPanel.add(lblApplicantsLastName, "cell 0 9");
		
		textFieldALastName = new JTextField();
		centerPanel.add(textFieldALastName, "cell 0 9");
		textFieldALastName.setColumns(10);


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
	
	
	/** Get Permission Auxiliary Method
	 *  Return an Array List of all information of a specific permission
	 *   
	 * 	@return permissionListInformation  Array List of all permission information 
	 */
	private static ArrayList<Object> getPermissionList() {
		
		
		ArrayList<Object> permissionListInformation = new ArrayList<Object>();
		try {
			ArrayList<Object> PermissionListFromDB = dbman.getFromDB("select * from sisca_permission order by sisca_permission_tag_number");
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
	
}
