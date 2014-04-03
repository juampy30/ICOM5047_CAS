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

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;


public class AuthorizationTypeManager {

	AuthorizationTypeManager(){
	}

	//////////////////////////////////////////////////////////////////
	//   Authorization Type View								    //
	//////////////////////////////////////////////////////////////////

	static JPanel authorizationTypeView(){

		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelAuthorizationType = new JPanel();
		menuPanelAuthorizationType.setBackground(new Color(255,239,80));
		menuPanelAuthorizationType.setPreferredSize(new Dimension(10, 30));
		menuPanelAuthorizationType.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(250, 10));
		menuPanelAuthorizationType.add(menuOptionsPanel, BorderLayout.WEST);
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

		JLabel AuthorizationTypeManagerLabel = new JLabel("Authorization Type");
		AuthorizationTypeManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		AuthorizationTypeManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AuthorizationTypeManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AuthorizationTypeManagerLabel.setForeground((java.awt.Color) null);
		AuthorizationTypeManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		AuthorizationTypeManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		AuthorizationTypeManagerLabel.setBounds(66, -11, 161, 53);
		menuOptionsPanel.add(AuthorizationTypeManagerLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelAuthorizationType.add(logOutPanel, BorderLayout.EAST);
		logOutPanel.setLayout(null);

		JLabel logOutLabel = new JLabel("LogOut");
		logOutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		menuPanelAuthorizationType.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel("User Name   ");
		userNamePanel.add(userNameLabel, BorderLayout.EAST);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground((java.awt.Color) null);
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));



		/////////////////////////////////////////////////////////
		//Main Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel mainPanelAuthorizationType = new JPanel();
		mainPanelAuthorizationType.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelAuthorizationType.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelAuthorizationType.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAuthorizationType.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAuthorizationType.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelAuthorizationType.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[1242px]", "[61px][372px]"));

		JPanel searchAndAddPanel = new JPanel();
		searchAndAddPanel.setBackground(new Color(250,250,250));
		centerPanel.add(searchAndAddPanel, "cell 0 0,grow");
		searchAndAddPanel.setLayout(new MigLayout("", "[48px][289px][34.00px][][][][][][][][][][][][][][][][][][][][][][][]", "[29px]"));

		JLabel searchLabel = new JLabel("Search:");
		searchAndAddPanel.add(searchLabel, "cell 0 0,alignx left,aligny center");
		searchLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		JTextField textFieldSearch = new JTextField();
		textFieldSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		searchAndAddPanel.add(textFieldSearch, "cell 1 0,growx,aligny top");
		textFieldSearch.setColumns(10);
		JButton goButton = new JButton("Go");
		goButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		goButton.setPreferredSize(new Dimension(10, 29));
		searchAndAddPanel.add(goButton, "cell 2 0");

		JButton addNewAuthorizationTypeBtn = new JButton("Add New AuthorizationType");
		addNewAuthorizationTypeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addAuthorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		searchAndAddPanel.add(addNewAuthorizationTypeBtn, "cell 25 0,alignx left,aligny top");
		JPanel AuthorizationTypeListPanel = new JPanel();
		AuthorizationTypeListPanel.setBackground(new Color(250,250,250));

		centerPanel.add(AuthorizationTypeListPanel, "cell 0 1,alignx center,growy");
		AuthorizationTypeListPanel.setLayout(new MigLayout("", "[724px]", "[360px]"));
		JScrollPane AuthorizationTypeScrollPane = new JScrollPane();
		AuthorizationTypeListPanel.add(AuthorizationTypeScrollPane, "cell 0 0,grow");
		JList AuthorizationTypeList = new JList();
		AuthorizationTypeList.setSelectionForeground(UIManager.getColor("Button.darkShadow"));
		AuthorizationTypeList.setSelectionBackground(UIManager.getColor("Button.background"));
		AuthorizationTypeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView()); // Wrong Way! =S
					HKJ_SisCA_MainPage.frame.pack();
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			}
		});
		AuthorizationTypeScrollPane.setViewportView(AuthorizationTypeList);
		AuthorizationTypeList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Test 1", "Test 2", "Test 3", "Test 4", "Test 5", "Test 6", "Test 7", "Test 8", "Test 9", "Test 10"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});


		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelAuthorizationType= new JPanel();
		windowPanelAuthorizationType.setLayout(new BorderLayout(0, 0));
		windowPanelAuthorizationType.add(menuPanelAuthorizationType, BorderLayout.NORTH);
		windowPanelAuthorizationType.add(mainPanelAuthorizationType, BorderLayout.CENTER);

		return windowPanelAuthorizationType;
	}

	//////////////////////////////////////////////////////////////////
	//   Authorization Type Information View						//
	//////////////////////////////////////////////////////////////////

	private static JPanel authorizationTypeInformationView(){


		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelAuthorizationTypeInformation = new JPanel();
		menuPanelAuthorizationTypeInformation.setBackground(new Color(255,239,80));
		menuPanelAuthorizationTypeInformation.setPreferredSize(new Dimension(10, 30));
		menuPanelAuthorizationTypeInformation.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(475, 10));
		menuPanelAuthorizationTypeInformation.add(menuOptionsPanel, BorderLayout.WEST);
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

		JLabel AuthorizationTypeManagerLabel = new JLabel("Authorization Type");
		AuthorizationTypeManagerLabel.setPreferredSize(new Dimension(30, 16));
		AuthorizationTypeManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AuthorizationTypeManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AuthorizationTypeManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AuthorizationTypeManagerLabel.setForeground((java.awt.Color) null);
		AuthorizationTypeManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		AuthorizationTypeManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		AuthorizationTypeManagerLabel.setBounds(66, -11, 151, 53);
		menuOptionsPanel.add(AuthorizationTypeManagerLabel);

		JLabel listAuthorizationTypeLabel = new JLabel("Authorization Type Information");
		listAuthorizationTypeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		listAuthorizationTypeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listAuthorizationTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		listAuthorizationTypeLabel.setForeground((java.awt.Color) null);
		listAuthorizationTypeLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		listAuthorizationTypeLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listAuthorizationTypeLabel.setBounds(216, -11, 253, 53);
		menuOptionsPanel.add(listAuthorizationTypeLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelAuthorizationTypeInformation.add(logOutPanel, BorderLayout.EAST);
		logOutPanel.setLayout(null);

		JLabel logOutLabel = new JLabel("LogOut");
		logOutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		menuPanelAuthorizationTypeInformation.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel("User Name   ");
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
		JPanel leftPanelAuthorizationTypeInformation = new JPanel();
		leftPanelAuthorizationTypeInformation.setBackground(new Color(245,245,245));
		leftPanelAuthorizationTypeInformation.setPreferredSize(new Dimension(390, 10));
		leftPanelAuthorizationTypeInformation.setLayout(new BorderLayout(0, 0));

		JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanelAuthorizationTypeInformation.add(scrollPane);

		JLayeredPane liveSystemPanel21 = new JLayeredPane();
		scrollPane.setViewportView(liveSystemPanel);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel21.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));

		//AuthorizationType Name Labels & MouseCliked Event 

		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px][]"));


		//LeftPanel Labels
		JLabel LSystemLabel = new JLabel("AUTHORIZATION TYPES LIST");
		liveSystemPanel.add(LSystemLabel, "cell 0 0,alignx center,aligny top");
		LSystemLabel.setPreferredSize(new Dimension(100, 50));
		LSystemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LSystemLabel.setForeground(java.awt.Color.BLACK);
		LSystemLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		JLabel aTName1 = new JLabel("New label");
		aTName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName1, "cell 0 3,alignx left,aligny top");

		JLabel aTName2 = new JLabel("New label");
		aTName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName2, "cell 0 5,alignx left,aligny top");

		JLabel aTName3 = new JLabel("New label");
		aTName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName3, "cell 0 7,alignx left,aligny top");

		JLabel aTName4 = new JLabel("New label");
		aTName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName4, "cell 0 9,alignx left,aligny top");

		JLabel aTName5 = new JLabel("New label");
		aTName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName5, "cell 0 11,alignx left,aligny top");

		JLabel aTName6 = new JLabel("New label");
		aTName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName6, "cell 0 13,alignx left,aligny top");

		JLabel aTName7 = new JLabel("New label");
		aTName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName7, "cell 0 15,alignx left,aligny top");

		JLabel aTName8 = new JLabel("New label");
		aTName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName8, "cell 0 17,alignx left,aligny top");

		JLabel aTName9 = new JLabel("New label");
		aTName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName9, "cell 0 19,alignx left,aligny top");

		JLabel aTName10 = new JLabel("New label");
		aTName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName10, "cell 0 21,alignx left,aligny top");

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

		JTextField searchTextField = new JTextField();
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView());
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
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New Authorization Type");
		liveSystemPanel.add(addNewButton, "cell 0 24");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addAuthorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});


		/////////////////////////////////////////////////////////
		//Main Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel mainPanelAuthorizationTypeInformation = new JPanel();
		mainPanelAuthorizationTypeInformation.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelAuthorizationTypeInformation.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelAuthorizationTypeInformation.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAuthorizationTypeInformation.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAuthorizationTypeInformation.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelAuthorizationTypeInformation.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[849px]", "[193.00px][65px][12px]"));

		JLabel realName = new JLabel("Real Name");
		realName.setPreferredSize(new Dimension(100, 50));
		realName.setHorizontalAlignment(SwingConstants.CENTER);
		realName.setForeground(java.awt.Color.BLACK);
		realName.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		centerPanel.add(realName, "cell 0 0,alignx center,aligny top");

		JPanel editAndRemovePanel = new JPanel();
		editAndRemovePanel.setBackground(new Color(250,250,250));
		centerPanel.add(editAndRemovePanel, "cell 0 1,grow");
		editAndRemovePanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(editAuthorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		editAndRemovePanel.add(editButton, "cell 0 0");

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO remover
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}

		});
		editAndRemovePanel.add(btnRemove, "cell 26 0");



		/////////////////ì///////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelAuthorizationTypeInformation= new JPanel();
		windowPanelAuthorizationTypeInformation.setLayout(new BorderLayout(0, 0));
		windowPanelAuthorizationTypeInformation.add(menuPanelAuthorizationTypeInformation, BorderLayout.NORTH);
		windowPanelAuthorizationTypeInformation.add(leftPanelAuthorizationTypeInformation, BorderLayout.WEST);
		windowPanelAuthorizationTypeInformation.add(mainPanelAuthorizationTypeInformation, BorderLayout.CENTER);

		return windowPanelAuthorizationTypeInformation;
	}

	//////////////////////////////////////////////////////////////////
	//   Add Authorization Type View						        //
	//////////////////////////////////////////////////////////////////

	private static JPanel addAuthorizationTypeView(){
		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelAddAuthorizationType = new JPanel();
		menuPanelAddAuthorizationType.setBackground(new Color(255,239,80));
		menuPanelAddAuthorizationType.setPreferredSize(new Dimension(10, 30));
		menuPanelAddAuthorizationType.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(425, 10));
		menuPanelAddAuthorizationType.add(menuOptionsPanel, BorderLayout.WEST);
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

		JLabel AuthorizationTypeManagerLabel = new JLabel("Authorization Type");
		AuthorizationTypeManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		AuthorizationTypeManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AuthorizationTypeManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AuthorizationTypeManagerLabel.setForeground((java.awt.Color) null);
		AuthorizationTypeManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		AuthorizationTypeManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		AuthorizationTypeManagerLabel.setBounds(66, -11, 159, 53);
		menuOptionsPanel.add(AuthorizationTypeManagerLabel);

		JLabel AddAuthorizationTypeLabel = new JLabel("Add AuthorizationType");
		AddAuthorizationTypeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(addAuthorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		AddAuthorizationTypeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AddAuthorizationTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AddAuthorizationTypeLabel.setForeground((java.awt.Color) null);
		AddAuthorizationTypeLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		AddAuthorizationTypeLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		AddAuthorizationTypeLabel.setBounds(224, -11, 195, 53);
		menuOptionsPanel.add(AddAuthorizationTypeLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelAddAuthorizationType.add(logOutPanel, BorderLayout.EAST);
		logOutPanel.setLayout(null);

		JLabel logOutLabel = new JLabel("LogOut");
		logOutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		menuPanelAddAuthorizationType.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel("User Name   ");
		userNamePanel.add(userNameLabel, BorderLayout.EAST);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground((java.awt.Color) null);
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));



		/////////////////////////////////////////////////////////
		//Left Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel leftPanelAddccount = new JPanel();
		leftPanelAddccount.setBackground(new Color(245,245,245));
		leftPanelAddccount.setPreferredSize(new Dimension(390, 10));
		leftPanelAddccount.setLayout(new BorderLayout(0, 0));

		JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane leftPanelAddAuthorizationType = new JScrollPane();
		leftPanelAddAuthorizationType.setViewportView(liveSystemPanel);
		leftPanelAddccount.add(leftPanelAddAuthorizationType);

		JLayeredPane liveSystemPanel2 = new JLayeredPane();
		leftPanelAddAuthorizationType.setViewportView(liveSystemPanel);
		leftPanelAddAuthorizationType.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel2.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));

		//AuthorizationType Name Labels & MouseCliked Event 

		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px][]"));


		//LeftPanel Labels
		JLabel LSystemLabel = new JLabel("AUTHORIZATION TYPES LIST");
		liveSystemPanel.add(LSystemLabel, "cell 0 0,alignx center,aligny top");
		LSystemLabel.setPreferredSize(new Dimension(100, 50));
		LSystemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LSystemLabel.setForeground(java.awt.Color.BLACK);
		LSystemLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		JLabel aTName1 = new JLabel("New label");
		aTName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName1, "cell 0 3,alignx left,aligny top");

		JLabel aTName2 = new JLabel("New label");
		aTName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName2, "cell 0 5,alignx left,aligny top");

		JLabel aTName3 = new JLabel("New label");
		aTName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName3, "cell 0 7,alignx left,aligny top");

		JLabel aTName4 = new JLabel("New label");
		aTName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName4, "cell 0 9,alignx left,aligny top");

		JLabel aTName5 = new JLabel("New label");
		aTName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});

		JSeparator separator_3 = new JSeparator();
		liveSystemPanel.add(separator_3, "cell 0 10,growx,aligny top");
		liveSystemPanel.add(aTName5, "cell 0 11,alignx left,aligny top");

		JLabel aTName6 = new JLabel("New label");
		aTName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName6, "cell 0 13,alignx left,aligny top");

		JLabel aTName7 = new JLabel("New label");
		aTName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName7, "cell 0 15,alignx left,aligny top");

		JLabel aTName8 = new JLabel("New label");
		aTName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName8, "cell 0 17,alignx left,aligny top");

		JLabel aTName9 = new JLabel("New label");
		aTName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName9, "cell 0 19,alignx left,aligny top");

		JLabel aTName10 = new JLabel("New label");
		aTName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName10, "cell 0 21,alignx left,aligny top");

		//Separators
		JSeparator separator = new JSeparator();
		liveSystemPanel.add(separator, "cell 0 4,growx,aligny top");

		JSeparator separator_1 = new JSeparator();
		liveSystemPanel.add(separator_1, "cell 0 6,growx,aligny top");

		JSeparator separator_2 = new JSeparator();
		liveSystemPanel.add(separator_2, "cell 0 8,growx,aligny top");

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

		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(searchPanel, "cell 0 1,growx,aligny top");
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

		JLabel searchLabel = new JLabel("Search:");
		searchPanel.add(searchLabel);
		searchLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		JTextField searchTextField = new JTextField();
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		searchPanel.add(searchTextField);
		searchTextField.setColumns(10);

		JPanel viewAndAddBynPanel = new JPanel();
		viewAndAddBynPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(viewAndAddBynPanel, "cell 0 23,alignx left,aligny top");
		viewAndAddBynPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton viewAllButton = new JButton("View All");
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New AuthorizationType");
		liveSystemPanel.add(addNewButton, "cell 0 24");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addAuthorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});




		/////////////////////////////////////////////////////////
		//Main Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel mainPanelAddAuthorizationType = new JPanel();
		mainPanelAddAuthorizationType.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelAddAuthorizationType.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelAddAuthorizationType.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAddAuthorizationType.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAddAuthorizationType.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelAddAuthorizationType.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[854px,grow]", "[37px][60px][65px][84.00,grow]"));

		JLabel AddAuthorizationTypeLabelPanel = new JLabel("Add Authorization Type");
		AddAuthorizationTypeLabelPanel.setPreferredSize(new Dimension(100, 50));
		AddAuthorizationTypeLabelPanel.setHorizontalAlignment(SwingConstants.CENTER);
		AddAuthorizationTypeLabelPanel.setForeground(java.awt.Color.BLACK);
		AddAuthorizationTypeLabelPanel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		centerPanel.add(AddAuthorizationTypeLabelPanel, "cell 0 0,alignx center,growy");

		JPanel AddCancelPanel = new JPanel();
		AddCancelPanel.setBackground(new Color(250,250,250));
		centerPanel.add(AddCancelPanel, "cell 0 2,grow");
		AddCancelPanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));

		JButton AddAuthorizationTypeBtn = new JButton("Add Authorization Type");
		AddAuthorizationTypeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Este add me lleva a ver la iformaci—n del AuthorizationType recien a–adido
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(AddAuthorizationTypeBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Cancel me hace un BACK en el history
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});

		AddCancelPanel.add(cancelBtn, "cell 26 0");

		JPanel AuthorizationTypeIDPanel = new JPanel();
		AuthorizationTypeIDPanel.setBackground((java.awt.Color) null);
		centerPanel.add(AuthorizationTypeIDPanel, "cell 0 1,growx,aligny top");
		AuthorizationTypeIDPanel.setLayout(new MigLayout("", "[57.00px][371.00px]", "[28px]"));

		JLabel nameLabel = new JLabel("Name:");
		AuthorizationTypeIDPanel.add(nameLabel, "cell 0 0,alignx left,aligny center");
		nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JTextField addNameTextField = new JTextField();
		addNameTextField.setPreferredSize(new Dimension(200, 100));
		AuthorizationTypeIDPanel.add(addNameTextField, "cell 1 0,grow");
		addNameTextField.setColumns(10);


		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelAddAuthorizationType= new JPanel();
		windowPanelAddAuthorizationType.setLayout(new BorderLayout(0, 0));
		windowPanelAddAuthorizationType.add(menuPanelAddAuthorizationType, BorderLayout.NORTH);
		windowPanelAddAuthorizationType.add(leftPanelAddccount, BorderLayout.WEST);
		windowPanelAddAuthorizationType.add(mainPanelAddAuthorizationType, BorderLayout.CENTER);
		return windowPanelAddAuthorizationType;
	}

	//////////////////////////////////////////////////////////////////
	//   Edit Authorization Type View						        //
	//////////////////////////////////////////////////////////////////

	private static JPanel editAuthorizationTypeView(){
		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelEditAuthorizationType = new JPanel();
		menuPanelEditAuthorizationType.setBackground(new Color(255,239,80));
		menuPanelEditAuthorizationType.setPreferredSize(new Dimension(10, 30));
		menuPanelEditAuthorizationType.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(425, 10));
		menuPanelEditAuthorizationType.add(menuOptionsPanel, BorderLayout.WEST);
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

		JLabel AuthorizationTypeManagerLabel = new JLabel("Authorization Type");
		AuthorizationTypeManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		AuthorizationTypeManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AuthorizationTypeManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AuthorizationTypeManagerLabel.setForeground((java.awt.Color) null);
		AuthorizationTypeManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		AuthorizationTypeManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		AuthorizationTypeManagerLabel.setBounds(66, -11, 159, 53);
		menuOptionsPanel.add(AuthorizationTypeManagerLabel);

		JLabel editAuthorizationTypeLabel = new JLabel("Edit AuthorizationType");
		editAuthorizationTypeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(addAuthorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		editAuthorizationTypeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		editAuthorizationTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editAuthorizationTypeLabel.setForeground((java.awt.Color) null);
		editAuthorizationTypeLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		editAuthorizationTypeLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		editAuthorizationTypeLabel.setBounds(224, -11, 195, 53);
		menuOptionsPanel.add(editAuthorizationTypeLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelEditAuthorizationType.add(logOutPanel, BorderLayout.EAST);
		logOutPanel.setLayout(null);

		JLabel logOutLabel = new JLabel("LogOut");
		logOutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		menuPanelEditAuthorizationType.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel("User Name   ");
		userNamePanel.add(userNameLabel, BorderLayout.EAST);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground((java.awt.Color) null);
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));



		/////////////////////////////////////////////////////////
		//Left Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel leftPanelEditccount = new JPanel();
		leftPanelEditccount.setBackground(new Color(245,245,245));
		leftPanelEditccount.setPreferredSize(new Dimension(390, 10));
		leftPanelEditccount.setLayout(new BorderLayout(0, 0));

		JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane leftPanelEditAuthorizationType = new JScrollPane();
		leftPanelEditAuthorizationType.setViewportView(liveSystemPanel);
		leftPanelEditccount.add(leftPanelEditAuthorizationType);

		JLayeredPane liveSystemPanel2 = new JLayeredPane();
		leftPanelEditAuthorizationType.setViewportView(liveSystemPanel);
		leftPanelEditAuthorizationType.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel2.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));

		//AuthorizationType Name Labels & MouseCliked Event 

		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px][]"));


		//LeftPanel Labels
		JLabel LSystemLabel = new JLabel("AUTHORIZATION TYPES LIST");
		liveSystemPanel.add(LSystemLabel, "cell 0 0,alignx center,aligny top");
		LSystemLabel.setPreferredSize(new Dimension(100, 50));
		LSystemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LSystemLabel.setForeground(java.awt.Color.BLACK);
		LSystemLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		JLabel aTName1 = new JLabel("New label");
		aTName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName1, "cell 0 3,alignx left,aligny top");

		JLabel aTName2 = new JLabel("New label");
		aTName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName2, "cell 0 5,alignx left,aligny top");

		JLabel aTName3 = new JLabel("New label");
		aTName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName3, "cell 0 7,alignx left,aligny top");

		JLabel aTName4 = new JLabel("New label");
		aTName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName4, "cell 0 9,alignx left,aligny top");

		JLabel aTName5 = new JLabel("New label");
		aTName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});

		JSeparator separator_3 = new JSeparator();
		liveSystemPanel.add(separator_3, "cell 0 10,growx,aligny top");
		liveSystemPanel.add(aTName5, "cell 0 11,alignx left,aligny top");

		JLabel aTName6 = new JLabel("New label");
		aTName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName6, "cell 0 13,alignx left,aligny top");

		JLabel aTName7 = new JLabel("New label");
		aTName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName7, "cell 0 15,alignx left,aligny top");

		JLabel aTName8 = new JLabel("New label");
		aTName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName8, "cell 0 17,alignx left,aligny top");

		JLabel aTName9 = new JLabel("New label");
		aTName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName9, "cell 0 19,alignx left,aligny top");

		JLabel aTName10 = new JLabel("New label");
		aTName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName10, "cell 0 21,alignx left,aligny top");

		//Separators
		JSeparator separator = new JSeparator();
		liveSystemPanel.add(separator, "cell 0 4,growx,aligny top");

		JSeparator separator_1 = new JSeparator();
		liveSystemPanel.add(separator_1, "cell 0 6,growx,aligny top");

		JSeparator separator_2 = new JSeparator();
		liveSystemPanel.add(separator_2, "cell 0 8,growx,aligny top");

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

		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(searchPanel, "cell 0 1,growx,aligny top");
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

		JLabel searchLabel = new JLabel("Search:");
		searchPanel.add(searchLabel);
		searchLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		JTextField searchTextField = new JTextField();
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		searchPanel.add(searchTextField);
		searchTextField.setColumns(10);

		JPanel viewAndAddBynPanel = new JPanel();
		viewAndAddBynPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(viewAndAddBynPanel, "cell 0 23,alignx left,aligny top");
		viewAndAddBynPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton viewAllButton = new JButton("View All");
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New AuthorizationType");
		liveSystemPanel.add(addNewButton, "cell 0 24");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addAuthorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});




		/////////////////////////////////////////////////////////
		//Main Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel mainPanelEditAuthorizationType = new JPanel();
		mainPanelEditAuthorizationType.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelEditAuthorizationType.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelEditAuthorizationType.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelEditAuthorizationType.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelEditAuthorizationType.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelEditAuthorizationType.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[854px,grow]", "[37px][60px][65px][84.00,grow]"));

		JLabel editAuthorizationTypeLabelPanel = new JLabel("Edit Authorization Type");
		editAuthorizationTypeLabelPanel.setPreferredSize(new Dimension(100, 50));
		editAuthorizationTypeLabelPanel.setHorizontalAlignment(SwingConstants.CENTER);
		editAuthorizationTypeLabelPanel.setForeground(java.awt.Color.BLACK);
		editAuthorizationTypeLabelPanel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		centerPanel.add(editAuthorizationTypeLabelPanel, "cell 0 0,alignx center,growy");

		JPanel AddCancelPanel = new JPanel();
		AddCancelPanel.setBackground(new Color(250,250,250));
		centerPanel.add(AddCancelPanel, "cell 0 2,grow");
		AddCancelPanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));

		JButton editAuthorizationTypeBtn = new JButton("Edit Authorization Type");
		editAuthorizationTypeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Este add me lleva a ver la iformaci—n del AuthorizationType recien a–adido
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(editAuthorizationTypeBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Cancel me hace un BACK en el history
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});

		AddCancelPanel.add(cancelBtn, "cell 26 0");

		JPanel AuthorizationTypeIDPanel = new JPanel();
		AuthorizationTypeIDPanel.setBackground((java.awt.Color) null);
		centerPanel.add(AuthorizationTypeIDPanel, "cell 0 1,growx,aligny top");
		AuthorizationTypeIDPanel.setLayout(new MigLayout("", "[57.00px][371.00px]", "[28px]"));

		JLabel nameLabel = new JLabel("Name:");
		AuthorizationTypeIDPanel.add(nameLabel, "cell 0 0,alignx left,aligny center");
		nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JTextField editNameTextField = new JTextField();
		editNameTextField.setPreferredSize(new Dimension(200, 100));
		AuthorizationTypeIDPanel.add(editNameTextField, "cell 1 0,grow");
		editNameTextField.setColumns(10);


		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelEditAuthorizationType= new JPanel();
		windowPanelEditAuthorizationType.setLayout(new BorderLayout(0, 0));
		windowPanelEditAuthorizationType.add(menuPanelEditAuthorizationType, BorderLayout.NORTH);
		windowPanelEditAuthorizationType.add(leftPanelEditccount, BorderLayout.WEST);
		windowPanelEditAuthorizationType.add(mainPanelEditAuthorizationType, BorderLayout.CENTER);
		return windowPanelEditAuthorizationType;
	}
}
