import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import databases.DBManager;
import net.miginfocom.swing.MigLayout;


public class PermissionManager {
	
	static DBManager dbman;
	static ArrayList<Object> availablePermission;
	static DefaultListModel availablePermissionModelList;
	static Boolean updateJList=true;
	private static JList PermissionList;

	PermissionManager(){

	}

	//////////////////////////////////////////////////////////////////
	//   Permission View						        //
	//////////////////////////////////////////////////////////////////

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
					HKJ_SisCA_MainPage.frame.setContentPane(permissionInformationView()); // Wrong Way! =S
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

	private static JPanel permissionInformationView(){
		return null;
	}

	//////////////////////////////////////////////////////////////////
	//   Add Permission View						        //
	//////////////////////////////////////////////////////////////////

	private static JPanel addPermissionView(){

		return null;
	}

	//////////////////////////////////////////////////////////////////
	//   Edit Permission View						        //
	//////////////////////////////////////////////////////////////////

	private JPanel editPermissionView(){
		return null;

	}

}
