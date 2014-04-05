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
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import databases.DBManager;
import net.miginfocom.swing.MigLayout;


public class AccountManager {

	private DBManager dbman;
	private String accountSelectedByUserFromAccountView;
	private ArrayList<Object> userInformationToEdit;
	private String userNameToRemove;


	AccountManager(){

	}

	//////////////////////////////////////////////////////////////////
	//   Account View								            	//
	//////////////////////////////////////////////////////////////////
	public JPanel accountView(){
		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelAccount = new JPanel();
		menuPanelAccount.setBackground(new Color(255,239,80));
		menuPanelAccount.setPreferredSize(new Dimension(10, 30));
		menuPanelAccount.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(250, 10));
		menuPanelAccount.add(menuOptionsPanel, BorderLayout.WEST);
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

		JLabel AccountManagerLabel = new JLabel("Account");
		AccountManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		AccountManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AccountManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AccountManagerLabel.setForeground((java.awt.Color) null);
		AccountManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		AccountManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		AccountManagerLabel.setBounds(66, -11, 109, 53);
		menuOptionsPanel.add(AccountManagerLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelAccount.add(logOutPanel, BorderLayout.EAST);
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
		menuPanelAccount.add(userNamePanel, BorderLayout.CENTER);
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
		JPanel mainPanelAccount = new JPanel();
		mainPanelAccount.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelAccount.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelAccount.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAccount.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAccount.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelAccount.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[1242px]", "[61px][372px]"));

		JPanel searchAndAddPanel = new JPanel();
		searchAndAddPanel.setBackground(new Color(250,250,250));
		centerPanel.add(searchAndAddPanel, "cell 0 0,grow");
		searchAndAddPanel.setLayout(new MigLayout("", "[48px][289px][34.00px][][][][][][][][][][][][][][][][][][][][][][][]", "[29px]"));

		JLabel searchLabel = new JLabel("Search:");
		searchAndAddPanel.add(searchLabel, "cell 0 0,alignx left,aligny center");
		searchLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		final JTextField textFieldSearch = new JTextField();
		textFieldSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});



		final DefaultListModel userActiveListView = new DefaultListModel();

		searchAndAddPanel.add(textFieldSearch, "cell 1 0,growx,aligny top");
		textFieldSearch.setColumns(10);
		JButton goButton = new JButton("GO");
		goButton.addMouseListener(new MouseAdapter() {
			private DefaultListModel searchUserActiveListView = new DefaultListModel();

			@Override
			public void mouseClicked(MouseEvent e) {

				String searchUsername = textFieldSearch.getText();
				HKJ_SisCA_MainPage.frame.setContentPane(accountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		goButton.setPreferredSize(new Dimension(10, 29));
		searchAndAddPanel.add(goButton, "cell 2 0");

		JButton addNewAccountBtn = new JButton("Add New Account");
		addNewAccountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addAccountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		searchAndAddPanel.add(addNewAccountBtn, "cell 25 0,alignx left,aligny top");
		JPanel AccountListPanel = new JPanel();
		AccountListPanel.setBackground(new Color(250,250,250));

		centerPanel.add(AccountListPanel, "cell 0 1,alignx center,growy");
		AccountListPanel.setLayout(new MigLayout("", "[724px]", "[360px]"));
		JScrollPane scrollPane = new JScrollPane();
		AccountListPanel.add(scrollPane, "cell 0 0,grow");

		/**
		 * dbman : connect to DBManager to run the required querys 
		 */
		try {
			dbman = new DBManager();
			ArrayList<Object> activeAccountArrayListQuery = dbman.getFromDB("select * from sisca_account order by sisca_account_type");
			/**
			 * userActiveListView : 
			 */
			String[] keyValue;
			String username = null;
			String accountType = null;

			//[{1:A},{2:B},{3:C}]
			for(int i=0; i< activeAccountArrayListQuery.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) activeAccountArrayListQuery.get(i)).size(); k++){
					//result = 1:A 
					Object result = ((List<Object>) activeAccountArrayListQuery.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_account_username")){
						username = (String) keyValue[1];
					}
					if(keyValue[0].equals("sisca_account_type")){
						accountType = (String) keyValue[1];
					}
				}
				userActiveListView.addElement(username +  " >> " + accountType.toUpperCase());
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

		final JList activeAccountList = new JList(userActiveListView); 
		/**
		 * activeAccountList : Display the list of all account register in the system
		 */
		//	JList activeAccountList = new JList(userActiveListView); 

		activeAccountList.setSelectionForeground(UIManager.getColor("Button.darkShadow"));
		activeAccountList.setSelectionBackground(UIManager.getColor("Button.background"));
		activeAccountList.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){

					accountSelectedByUserFromAccountView = (String) activeAccountList.getSelectedValue();

					HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(accountSelectedByUserFromAccountView)); // Wrong Way! =S
					HKJ_SisCA_MainPage.frame.pack();
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			}
		});
		scrollPane.setViewportView(activeAccountList);




		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelAccount= new JPanel();
		windowPanelAccount.setLayout(new BorderLayout(0, 0));
		windowPanelAccount.add(menuPanelAccount, BorderLayout.NORTH);
		windowPanelAccount.add(mainPanelAccount, BorderLayout.CENTER);




		return windowPanelAccount;

	}

	//////////////////////////////////////////////////////////////////
	//   Account Information View								    //
	//////////////////////////////////////////////////////////////////


	private JPanel accountInformationView(String Username){

		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelAccountInformation = new JPanel();
		menuPanelAccountInformation.setBackground(new Color(255,239,80));
		menuPanelAccountInformation.setPreferredSize(new Dimension(10, 30));
		menuPanelAccountInformation.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(375, 10));
		menuPanelAccountInformation.add(menuOptionsPanel, BorderLayout.WEST);
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

		JLabel AccountManagerLabel = new JLabel("Account");
		AccountManagerLabel.setPreferredSize(new Dimension(30, 16));
		AccountManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountView());
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AccountManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AccountManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AccountManagerLabel.setForeground((java.awt.Color) null);
		AccountManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		AccountManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		AccountManagerLabel.setBounds(66, -11, 95, 53);
		menuOptionsPanel.add(AccountManagerLabel);

		JLabel listAccountLabel = new JLabel("Account Information");
		listAccountLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(null));
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		listAccountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listAccountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		listAccountLabel.setForeground((java.awt.Color) null);
		listAccountLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		listAccountLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listAccountLabel.setBounds(159, -11, 166, 53);
		menuOptionsPanel.add(listAccountLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelAccountInformation.add(logOutPanel, BorderLayout.EAST);
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
		menuPanelAccountInformation.add(userNamePanel, BorderLayout.CENTER);
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
		JPanel leftPanelAccountInformation = new JPanel();
		leftPanelAccountInformation.setBackground(new Color(245,245,245));
		leftPanelAccountInformation.setPreferredSize(new Dimension(390, 10));
		leftPanelAccountInformation.setLayout(new BorderLayout(0, 0));

		final JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanelAccountInformation.add(scrollPane);

		JLayeredPane liveSystemPanel21 = new JLayeredPane();
		scrollPane.setViewportView(liveSystemPanel);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel21.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));

		//Account Name Labels & MouseCliked Event 

		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px]"));


		//LeftPanel Labels
		JLabel LSystemLabel = new JLabel("ACCOUNTS LIST");
		liveSystemPanel.add(LSystemLabel, "cell 0 0,alignx center,aligny top");
		LSystemLabel.setPreferredSize(new Dimension(100, 50));
		LSystemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LSystemLabel.setForeground(java.awt.Color.BLACK);
		LSystemLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		/**
		 * 
		 */
		ArrayList<Object> accountUsernameListView = getAccountList();
		int position = 1;
		for(int i=0; i<accountUsernameListView.size() && i<10 ; i++){
			position = position+2;
			final JLabel userName = new JLabel(accountUsernameListView.get(i).toString());
			userName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(userName.getText()));
					HKJ_SisCA_MainPage.frame.pack(); 
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			});
			userName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			liveSystemPanel.add(userName, "cell 0 "+ position +" ,alignx left,aligny top");
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
				HKJ_SisCA_MainPage.frame.setContentPane(accountView());
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
				HKJ_SisCA_MainPage.frame.setContentPane(accountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New Account");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addAccountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(addNewButton);




		/////////////////////////////////////////////////////////
		//Main Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel mainPanelAccountInformation = new JPanel();
		mainPanelAccountInformation.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelAccountInformation.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelAccountInformation.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAccountInformation.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAccountInformation.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelAccountInformation.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[849.00px]", "[50px][][12px][19px][12px][19px][12px][19px][12px][65px]"));

		/**
		 * Get real Name 
		 */

		final String[] userName = Username.split(" >>"); //userName[0] : username
		String[] keyValue;
		String accountFirstnameDB = null, accountLastnameDB = null;
		String accountUsernameDB=null;
		String accountTypeDB=null;
		int accountIdDB = 999999999;

		try {
			ArrayList<Object> userInformation = dbman.getFromDB("select * from sisca_account where sisca_account_username='"+ userName[0]+ "'");
			userInformationToEdit = dbman.getFromDB("select * from sisca_account where sisca_account_username='"+ userName[0]+ "'");
			for(int i=0; i<userInformation.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) userInformation.get(i)).size(); k++){
					//result = 1:A
					Object result = ((List<Object>) userInformation.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_account_id")){
						accountIdDB = Integer.valueOf((String)keyValue[1]);
					}
					if(keyValue[0].equals("sisca_account_first_name")){
						accountFirstnameDB = (String) keyValue[1];

					}
					if(keyValue[0].equals("sisca_account_last_name")){
						accountLastnameDB = (String) keyValue[1];
					}
					if(keyValue[0].equals("sisca_account_username")){
						accountUsernameDB = (String) keyValue[1];
						userNameToRemove = accountUsernameDB;

					}
					if(keyValue[0].equals("sisca_account_type")){
						accountTypeDB = (String) keyValue[1];
					}

				}
			}
			//	System.out.println(indexID);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel realName = new JLabel(accountFirstnameDB + " " + accountLastnameDB);
		realName.setPreferredSize(new Dimension(100, 50));
		realName.setHorizontalAlignment(SwingConstants.CENTER);
		realName.setForeground(java.awt.Color.BLACK);
		realName.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		centerPanel.add(realName, "cell 0 0,alignx center,aligny top");

		JLabel username = new JLabel("Username:  " + accountUsernameDB);
		username.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		centerPanel.add(username, "cell 0 1");

		JLabel accountType = new JLabel("Account Type:  " + accountTypeDB.toUpperCase());
		accountType.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		centerPanel.add(accountType, "cell 0 3,alignx left,aligny top");

		/*	JLabel password = new JLabel("Password: ");
		password.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		centerPanel.add(password, "cell 0 5,alignx left,aligny baseline");

		JSeparator separator_12 = new JSeparator();
		centerPanel.add(separator_12, "cell 0 4,growx,aligny top"); 

		JSeparator separator_13 = new JSeparator();
		centerPanel.add(separator_13, "cell 0 6,growx,aligny top"); */

		JPanel editAndRemovePanel = new JPanel();
		editAndRemovePanel.setBackground(new Color(250,250,250));
		centerPanel.add(editAndRemovePanel, "cell 0 7,grow");
		editAndRemovePanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(editAccountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		editAndRemovePanel.add(editButton, "cell 0 0");

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");
				//System.out.println("SURE: " + sure);
				String accountUsername = userNameToRemove;
				if(sure==0){
					/**
					 * 
					 */
					String[] keyValue;
					//String accountUsername = userNameToRemove;
					ArrayList<Object> userInformationRemove = null;
					try {
						userInformationRemove = dbman.getFromDB("select * from sisca_account where sisca_account_username='"+ accountUsername + "'");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//TODO ARE YOU SURE YOU WANT TO REMOVE?
					for(int i=0; i<userInformationRemove.size(); i++){
						//obtener el elemento i del elemento 1 (el array del array) 
						for(int k=0 ; k<((List<Object>) userInformationRemove.get(i)).size(); k++){
							//result = 1:A
							Object result = ((List<Object>) userInformationRemove.get(i)).get(k);
							//keyValue -> {1,A}
							keyValue = result.toString().split("/");
							if(keyValue[0].equals("sisca_account_username")){
								accountUsername = (String) keyValue[1];
							}
						}
					}

					try {
						dbman.updatetDB("delete from sisca_account where sisca_account_username='" +accountUsername+"'");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					HKJ_SisCA_MainPage.frame.setContentPane(accountView());
					HKJ_SisCA_MainPage.frame.pack(); 
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
				else{
					HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(accountUsername));
					HKJ_SisCA_MainPage.frame.pack(); 
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			}
		});
		editAndRemovePanel.add(btnRemove, "cell 26 0");

		JSeparator separator_16 = new JSeparator();
		centerPanel.add(separator_16, "cell 0 8,growx,aligny top");

		JSeparator separator_17 = new JSeparator();
		centerPanel.add(separator_17, "cell 0 2,growx,aligny top");


		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelAccountInformation= new JPanel();
		windowPanelAccountInformation.setLayout(new BorderLayout(0, 0));
		windowPanelAccountInformation.add(menuPanelAccountInformation, BorderLayout.NORTH);
		windowPanelAccountInformation.add(leftPanelAccountInformation, BorderLayout.WEST);
		windowPanelAccountInformation.add(mainPanelAccountInformation, BorderLayout.CENTER);



		return windowPanelAccountInformation;

	}

	//////////////////////////////////////////////////////////////////
	//   Add Account View								            //
	//////////////////////////////////////////////////////////////////

	/**
	 * 
	 * @return
	 */
	private ArrayList<Object> getAccountList() {
		ArrayList<Object> usernameActiveFromDB = new ArrayList<Object>();
		try {
			ArrayList<Object> accountListFromDB = dbman.getFromDB("select * from sisca_account order by sisca_account_username");
			String[] keyValue;
			String userName = null;
			for(int i=0; i<accountListFromDB.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) accountListFromDB.get(i)).size(); k++){
					//result = 1:A
					Object result = ((List<Object>) accountListFromDB.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_account_username")){
						userName = (String) keyValue[1];
					}
				}
				usernameActiveFromDB.add(userName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usernameActiveFromDB;
	}

	private JPanel addAccountView(){

		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelAddAccount = new JPanel();
		menuPanelAddAccount.setBackground(new Color(255,239,80));
		menuPanelAddAccount.setPreferredSize(new Dimension(10, 30));
		menuPanelAddAccount.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(375, 10));
		menuPanelAddAccount.add(menuOptionsPanel, BorderLayout.WEST);
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

		JLabel AccountManagerLabel = new JLabel("Account");
		AccountManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		AccountManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AccountManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AccountManagerLabel.setForeground((java.awt.Color) null);
		AccountManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		AccountManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		AccountManagerLabel.setBounds(66, -11, 84, 53);
		menuOptionsPanel.add(AccountManagerLabel);

		JLabel addAccountLabel = new JLabel("Add Account");
		addAccountLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(addAccountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		addAccountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addAccountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addAccountLabel.setForeground((java.awt.Color) null);
		addAccountLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		addAccountLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		addAccountLabel.setBounds(148, -11, 111, 53);
		menuOptionsPanel.add(addAccountLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelAddAccount.add(logOutPanel, BorderLayout.EAST);
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
		menuPanelAddAccount.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel(HKJ_SisCA_MainPage.getActiveUsername());
		userNamePanel.add(userNameLabel, BorderLayout.EAST);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground((java.awt.Color) null);
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));



		/////////////////////////////////////////////////////////
		//Left Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel leftPanelAddAccount = new JPanel();
		leftPanelAddAccount.setBackground(new Color(245,245,245));
		leftPanelAddAccount.setPreferredSize(new Dimension(390, 10));
		leftPanelAddAccount.setLayout(new BorderLayout(0, 0));

		JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanelAddAccount.add(scrollPane);

		JLayeredPane liveSystemPanel2 = new JLayeredPane();
		scrollPane.setViewportView(liveSystemPanel);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel2.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));

		//Account Name Labels & MouseCliked Event 

		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px]"));


		//LeftPanel Labels
		JLabel LSystemLabel = new JLabel("ACCOUNTS LIST");
		liveSystemPanel.add(LSystemLabel, "cell 0 0,alignx center,aligny top");
		LSystemLabel.setPreferredSize(new Dimension(100, 50));
		LSystemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LSystemLabel.setForeground(java.awt.Color.BLACK);
		LSystemLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		/**
		 * 
		 */
		ArrayList<Object> accountUsernameListView = getAccountList();
		int position = 1;
		for(int i=0; i<accountUsernameListView.size() && i<10 ; i++){
			position = position+2;
			final JLabel userName = new JLabel(accountUsernameListView.get(i).toString());
			userName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(userName.getText()));
					HKJ_SisCA_MainPage.frame.pack(); 
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			});
			userName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			liveSystemPanel.add(userName, "cell 0 "+ position +" ,alignx left,aligny top");
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

		JSeparator separator_6 = new JSeparator();
		liveSystemPanel.add(separator_6, "cell 0 16,growx,aligny top");

		JSeparator separator_7 = new JSeparator();
		liveSystemPanel.add(separator_7, "cell 0 18,growx,aligny top");

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
				HKJ_SisCA_MainPage.frame.setContentPane(accountView());
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
				HKJ_SisCA_MainPage.frame.setContentPane(accountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New Account");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addAccountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(addNewButton);




		/////////////////////////////////////////////////////////
		//Main Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel mainPanelAddAccount = new JPanel();
		mainPanelAddAccount.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelAddAccount.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelAddAccount.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAddAccount.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAddAccount.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelAddAccount.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[854px,grow]", "[37px][60px][61px][42.00px][65px][84.00,grow]"));

		JLabel addAccountLabelPanel = new JLabel("Add New Account");
		addAccountLabelPanel.setPreferredSize(new Dimension(100, 50));
		addAccountLabelPanel.setHorizontalAlignment(SwingConstants.CENTER);
		addAccountLabelPanel.setForeground(java.awt.Color.BLACK);
		addAccountLabelPanel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		centerPanel.add(addAccountLabelPanel, "cell 0 0,alignx center,growy");

		JPanel AddCancelPanel = new JPanel();
		AddCancelPanel.setBackground(new Color(250,250,250));
		centerPanel.add(AddCancelPanel, "cell 0 4,grow");
		AddCancelPanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));

		JPanel AccountIDPanel = new JPanel();
		AccountIDPanel.setBackground((java.awt.Color) null);
		centerPanel.add(AccountIDPanel, "cell 0 1,growx,aligny top");
		AccountIDPanel.setLayout(new MigLayout("", "[57.00px][371.00px]", "[28px]"));

		JLabel firstNameLabel = new JLabel("First Name:");
		AccountIDPanel.add(firstNameLabel, "cell 0 0,alignx left,aligny center");
		firstNameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		final JTextField textFieldFirstName = new JTextField();
		textFieldFirstName.setPreferredSize(new Dimension(200, 100));
		AccountIDPanel.add(textFieldFirstName, "cell 1 0,grow");
		textFieldFirstName.setColumns(10);

		JLabel lastNameLabel = new JLabel("Last Name:");
		AccountIDPanel.add(lastNameLabel, "cell 2 0,alignx left,aligny center");
		lastNameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		final JTextField textFieldLastName = new JTextField();
		textFieldLastName.setPreferredSize(new Dimension(200, 100));
		AccountIDPanel.add(textFieldLastName, "cell 2 0,grow");
		textFieldLastName.setColumns(10);

		JPanel directionPanel = new JPanel();
		directionPanel.setBackground((java.awt.Color) null);
		centerPanel.add(directionPanel, "cell 0 2,grow");
		directionPanel.setLayout(new MigLayout("", "[81px][292.00px][][][212.00,grow]", "[28px]"));

		JLabel usernameLabel = new JLabel("Username:");
		directionPanel.add(usernameLabel, "cell 0 0,alignx left,aligny center");
		usernameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		final JTextField textFieldUsername = new JTextField();
		textFieldUsername.setPreferredSize(new Dimension(200, 100));
		textFieldUsername.setColumns(10);
		directionPanel.add(textFieldUsername, "cell 1 0,grow");

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		directionPanel.add(passwordLabel, "cell 3 0,alignx trailing");
		final JPasswordField passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(500, 28));
		directionPanel.add(passwordField, "cell 4 0,growx");

		JLabel repeatPasswordLabel = new JLabel("Repeat Password:");
		repeatPasswordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		directionPanel.add(repeatPasswordLabel, "cell 5 0,alignx trailing");
		final JPasswordField repeatPasswordField = new JPasswordField();
		repeatPasswordField.setPreferredSize(new Dimension(500, 28));
		directionPanel.add(repeatPasswordField, "cell 5 0,growx");

		JPanel AccountPanel = new JPanel();
		AccountPanel.setBackground((java.awt.Color) null);
		centerPanel.add(AccountPanel, "cell 0 3,grow");
		AccountPanel.setLayout(new MigLayout("", "[59.00px][209.00px,grow][118.00][-79.00px][161px]", "[21px][29px]"));

		JLabel AccountInfoLabel = new JLabel("Account Type:");
		AccountInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AccountInfoLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		AccountPanel.add(AccountInfoLabel, "cell 0 0,alignx left,growy");

		final JComboBox createAccountTypeSelector = new JComboBox();
		createAccountTypeSelector.setModel(new DefaultComboBoxModel(new String[] {"regular", "administrator"}));
		AccountPanel.add(createAccountTypeSelector, "cell 1 0,alignx left,aligny center");

		JButton addAccountBtn = new JButton("Add Account");
		addAccountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO THE USERNAME IS ALREADY USED!
				String createAccountFirstname = textFieldFirstName.getText();
				String createAccountLastname = textFieldLastName.getText();
				String createAccountPassword = passwordField.getText();
				String createAccountUsername = textFieldUsername.getText();
				String createRepeatPassword = repeatPasswordField.getText();
				String createAccountType = (String) createAccountTypeSelector.getSelectedItem();

				/**
				 * Verificar que el userName no exista
				 */
				ArrayList<Object> unavailableUsernamesDB;
				boolean alreadyExist=false;
				boolean passwordConflict = false;
				try {
					unavailableUsernamesDB = dbman.getFromDB("select sisca_account_username from sisca_account");
					ArrayList<Object> unavailableUsernames = new ArrayList<Object>();
					String[] keyValue;
					String username = null,password = null;

					for(int i=0; i<unavailableUsernamesDB.size(); i++){
						//obtener el elemento i del elemento 1 (el array del array) 
						for(int k=0 ; k<((List<Object>) unavailableUsernamesDB.get(i)).size(); k++){
							//result = 1:A
							Object result = ((List<Object>) unavailableUsernamesDB.get(i)).get(k);
							//keyValue -> {1,A}
							keyValue = result.toString().split("/");
							if(keyValue[0].equals("sisca_account_username")){
								username = (String) keyValue[1];
							}
							if(keyValue[0].equals("sisca_account_password")){
								password = (String) keyValue[1];
							}			
						}
						unavailableUsernames.add(username);
					}
					/**
					 * Verificar UserName
					 */
					for(int i=0 ; i<unavailableUsernames.size(); i++){
						if(createAccountUsername.toLowerCase().equals(unavailableUsernames.get(i).toString().toLowerCase())){
							alreadyExist=true;
						}
					}
					if(!createAccountPassword.equals(createRepeatPassword)){
						passwordConflict = true;
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/**
				 * 
				 */
				if(alreadyExist){
					JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "The USERNAME already exist.");
				}
				else if(passwordConflict){
					JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "Password Conflict");
				}
				else{
					try {
						dbman.updatetDB("insert into sisca_account (sisca_account_first_name,sisca_account_last_name,sisca_account_username,sisca_account_password,sisca_account_type) values('"+createAccountFirstname+"','"+createAccountLastname+"','"+createAccountUsername+"','"+createAccountPassword+"','"+createAccountType+"')");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};

					HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(createAccountUsername));
					HKJ_SisCA_MainPage.frame.pack(); 
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

				}
			}


		});
		AddCancelPanel.add(addAccountBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Cancel me hace un BACK en el history
				HKJ_SisCA_MainPage.frame.setContentPane(accountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});

		AddCancelPanel.add(cancelBtn, "cell 26 0");


		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelAddAccount= new JPanel();
		windowPanelAddAccount.setLayout(new BorderLayout(0, 0));
		windowPanelAddAccount.add(menuPanelAddAccount, BorderLayout.NORTH);
		windowPanelAddAccount.add(leftPanelAddAccount, BorderLayout.WEST);
		windowPanelAddAccount.add(mainPanelAddAccount, BorderLayout.CENTER);

		return windowPanelAddAccount;

	}

	//////////////////////////////////////////////////////////////////
	//   Edit Account View								            //
	//////////////////////////////////////////////////////////////////


	private JPanel editAccountView(){

		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelEditAccount = new JPanel();
		menuPanelEditAccount.setBackground(new Color(255,239,80));
		menuPanelEditAccount.setPreferredSize(new Dimension(10, 30));
		menuPanelEditAccount.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(375, 10));
		menuPanelEditAccount.add(menuOptionsPanel, BorderLayout.WEST);
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

		JLabel AccountManagerLabel = new JLabel("Account");
		AccountManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		AccountManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AccountManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AccountManagerLabel.setForeground((java.awt.Color) null);
		AccountManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		AccountManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		AccountManagerLabel.setBounds(66, -11, 84, 53);
		menuOptionsPanel.add(AccountManagerLabel);

		JLabel editAccountLabel = new JLabel("Edit Account");
		editAccountLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(addAccountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		editAccountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		editAccountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editAccountLabel.setForeground((java.awt.Color) null);
		editAccountLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		editAccountLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		editAccountLabel.setBounds(148, -11, 111, 53);
		menuOptionsPanel.add(editAccountLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelEditAccount.add(logOutPanel, BorderLayout.EAST);
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
		menuPanelEditAccount.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel(HKJ_SisCA_MainPage.getActiveUsername());
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

		JScrollPane leftPanelEditAccount = new JScrollPane();
		leftPanelEditAccount.setViewportView(liveSystemPanel);
		leftPanelEditccount.add(leftPanelEditAccount);

		JLayeredPane liveSystemPanel2 = new JLayeredPane();
		leftPanelEditAccount.setViewportView(liveSystemPanel);
		leftPanelEditAccount.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel2.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));

		//Account Name Labels & MouseCliked Event 

		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px]"));


		//LeftPanel Labels
		JLabel LSystemLabel = new JLabel("ACCOUNTS LIST");
		liveSystemPanel.add(LSystemLabel, "cell 0 0,alignx center,aligny top");
		LSystemLabel.setPreferredSize(new Dimension(100, 50));
		LSystemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LSystemLabel.setForeground(java.awt.Color.BLACK);
		LSystemLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		/*JLabel aName1 = new JLabel("New label");
		aName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(null));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aName1, "cell 0 3,alignx left,aligny top");

		JLabel aName2 = new JLabel("New label");
		aName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(null));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aName2, "cell 0 5,alignx left,aligny top");

		JLabel aName3 = new JLabel("New label");
		aName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(null));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aName3, "cell 0 7,alignx left,aligny top");

		JLabel aName4 = new JLabel("New label");
		aName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(null));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aName4, "cell 0 9,alignx left,aligny top");

		JLabel aName5 = new JLabel("New label");
		aName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(null));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});

		JSeparator separator_3 = new JSeparator();
		liveSystemPanel.add(separator_3, "cell 0 11,growx,aligny top");
		liveSystemPanel.add(aName5, "cell 0 12,alignx left,aligny top");

		JLabel aName6 = new JLabel("New label");
		aName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(null));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aName6, "cell 0 14,alignx left,aligny top");

		JLabel aName7 = new JLabel("New label");
		aName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(null));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aName7, "cell 0 16,alignx left,aligny top");

		JLabel aName8 = new JLabel("New label");
		aName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(null));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aName8, "cell 0 18,alignx left,aligny top");

		JLabel aName9 = new JLabel("New label");
		aName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(null));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aName9, "cell 0 20,alignx left,aligny top");

		JLabel aName10 = new JLabel("New label");
		aName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(null));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aName10, "cell 0 22,alignx left,aligny top");

		//Separators
		JSeparator separator = new JSeparator();
		liveSystemPanel.add(separator, "cell 0 4,growx,aligny top");

		JSeparator separator_1 = new JSeparator();
		liveSystemPanel.add(separator_1, "cell 0 6,growx,aligny top");

		JSeparator separator_2 = new JSeparator();
		liveSystemPanel.add(separator_2, "cell 0 8,growx,aligny top");

		JSeparator separator_4 = new JSeparator();
		liveSystemPanel.add(separator_4, "cell 0 13,growx,aligny top");

		JSeparator separator_5 = new JSeparator();
		liveSystemPanel.add(separator_5, "cell 0 15,growx,aligny top");

		JSeparator separator_7 = new JSeparator();
		liveSystemPanel.add(separator_7, "cell 0 19,growx,aligny top");

		JSeparator separator_6 = new JSeparator();
		liveSystemPanel.add(separator_6, "cell 0 17,growx,aligny top");

		JSeparator separator_8 = new JSeparator();
		liveSystemPanel.add(separator_8, "cell 0 21,growx,aligny top");

		JSeparator separator_9 = new JSeparator();
		liveSystemPanel.add(separator_9, "cell 0 23,growx,aligny top");

		JSeparator separator_10 = new JSeparator();
		liveSystemPanel.add(separator_10, "cell 0 2,growx,aligny top");*/

		/**
		 * 
		 */
		ArrayList<Object> accountUsernameListView = getAccountList();
		int position = 1;
		for(int i=0; i<accountUsernameListView.size() && i<10 ; i++){
			position = position+2;
			final JLabel userName = new JLabel(accountUsernameListView.get(i).toString());
			userName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(userName.getText()));
					HKJ_SisCA_MainPage.frame.pack(); 
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			});
			userName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			liveSystemPanel.add(userName, "cell 0 "+ position +" ,alignx left,aligny top");
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

		JSeparator separator_6 = new JSeparator();
		liveSystemPanel.add(separator_6, "cell 0 16,growx,aligny top");

		JSeparator separator_7 = new JSeparator();
		liveSystemPanel.add(separator_7, "cell 0 18,growx,aligny top");

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
				HKJ_SisCA_MainPage.frame.setContentPane(accountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		searchPanel.add(searchTextField);
		searchTextField.setColumns(10);

		JPanel viewAndAddBynPanel = new JPanel();
		viewAndAddBynPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(viewAndAddBynPanel, "cell 0 24,alignx left,aligny top");
		viewAndAddBynPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton viewAllButton = new JButton("View All");
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(accountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New Account");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addAccountView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(addNewButton);




		/////////////////////////////////////////////////////////
		//Main Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel mainPanelEditAccount = new JPanel();
		mainPanelEditAccount.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelEditAccount.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelEditAccount.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelEditAccount.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelEditAccount.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelEditAccount.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[854px,grow]", "[37px][60px][61px][42.00px][65px][84.00,grow]"));

		JLabel editAccountLabelPanel = new JLabel("Edit Account");
		editAccountLabelPanel.setPreferredSize(new Dimension(100, 50));
		editAccountLabelPanel.setHorizontalAlignment(SwingConstants.CENTER);
		editAccountLabelPanel.setForeground(java.awt.Color.BLACK);
		editAccountLabelPanel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		centerPanel.add(editAccountLabelPanel, "cell 0 0,alignx center,growy");

		JPanel AddCancelPanel = new JPanel();
		AddCancelPanel.setBackground(new Color(250,250,250));
		centerPanel.add(AddCancelPanel, "cell 0 4,grow");
		AddCancelPanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));



		JPanel AccountIDPanel = new JPanel();
		AccountIDPanel.setBackground((java.awt.Color) null);
		centerPanel.add(AccountIDPanel, "cell 0 1,growx,aligny top");
		AccountIDPanel.setLayout(new MigLayout("", "[57.00px][371.00px]", "[28px]"));

		/**
		 * 
		 */
		String[] keyValue;
		String accountFirstnameToEdit=null, accountLastnameToEdit=null, 
				accountUsernameToEdit=null, accountTypeToEdit=null,
				accountPasswordToEdit=null, accountRepeatPassword=null;
		int accountIdToEdit=99999999;
		for(int i=0; i<userInformationToEdit.size(); i++){
			//obtener el elemento i del elemento 1 (el array del array) 
			for(int k=0 ; k<((List<Object>) userInformationToEdit.get(i)).size(); k++){
				//result = 1:A
				Object result = ((List<Object>) userInformationToEdit.get(i)).get(k);
				//keyValue -> {1,A}
				keyValue = result.toString().split("/");
				if(keyValue[0].equals("sisca_account_id")){
					accountIdToEdit = Integer.valueOf((String)keyValue[1]);
				}
				if(keyValue[0].equals("sisca_account_first_name")){
					accountFirstnameToEdit = (String) keyValue[1];
				}
				if(keyValue[0].equals("sisca_account_last_name")){
					accountLastnameToEdit = (String) keyValue[1];
				}
				if(keyValue[0].equals("sisca_account_username")){
					accountUsernameToEdit = (String) keyValue[1];
				}
				if(keyValue[0].equals("sisca_account_password")){
					accountPasswordToEdit = (String) keyValue[1];
				}
				if(keyValue[0].equals("sisca_account_type")){
					accountTypeToEdit = (String) keyValue[1];
				}
			}
			//indexID.add(id);
		}
		//System.out.println(indexID);

		JLabel firstNameLabel = new JLabel("First Name:");
		AccountIDPanel.add(firstNameLabel, "cell 0 0,alignx left,aligny center");
		firstNameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		final JTextField textFieldFirstname = new JTextField(accountFirstnameToEdit);
		textFieldFirstname.setPreferredSize(new Dimension(200, 100));
		AccountIDPanel.add(textFieldFirstname, "cell 1 0,grow");
		textFieldFirstname.setColumns(10);

		JLabel lastNameLabel = new JLabel("Last Name: ");
		AccountIDPanel.add(lastNameLabel, "cell 2 0,alignx left,aligny center");
		lastNameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		final JTextField textFieldLastname = new JTextField(accountLastnameToEdit);
		textFieldLastname.setPreferredSize(new Dimension(200, 100));
		AccountIDPanel.add(textFieldLastname, "cell 2 0,grow");
		textFieldLastname.setColumns(10);

		JPanel directionPanel = new JPanel();
		directionPanel.setBackground((java.awt.Color) null);
		centerPanel.add(directionPanel, "cell 0 2,grow");
		directionPanel.setLayout(new MigLayout("", "[81px][292.00px][][][212.00,grow]", "[28px]"));
		/**
		 * 
		 */
		JLabel usernameLabel = new JLabel("Username: ");
		directionPanel.add(usernameLabel, "cell 0 0,alignx left,aligny center");
		usernameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		final JTextField textFieldUsername = new JTextField(accountUsernameToEdit);
		textFieldUsername.setPreferredSize(new Dimension(200, 100));
		textFieldUsername.setColumns(10);
		textFieldUsername.disable();
		directionPanel.add(textFieldUsername, "cell 1 0,grow");

		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		directionPanel.add(passwordLabel, "cell 3 0,alignx trailing");

		final JPasswordField passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(500, 28));
		directionPanel.add(passwordField, "cell 4 0,growx");

		JLabel repeatPasswordLabel = new JLabel("Repeat Password: ");
		repeatPasswordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		directionPanel.add(repeatPasswordLabel, "cell 5 0,alignx trailing");

		JPasswordField repeatPasswordField = new JPasswordField();
		repeatPasswordField.setPreferredSize(new Dimension(500, 28));
		directionPanel.add(repeatPasswordField, "cell 6 0,growx");


		JPanel AccountPanel = new JPanel();
		AccountPanel.setBackground((java.awt.Color) null);
		centerPanel.add(AccountPanel, "cell 0 3,grow");
		AccountPanel.setLayout(new MigLayout("", "[59.00px][209.00px,grow][118.00][-79.00px][161px]", "[21px][29px]"));

		JLabel AccountInfoLabel = new JLabel("Account Type:");
		AccountInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AccountInfoLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		AccountPanel.add(AccountInfoLabel, "cell 0 0,alignx left,growy");

		final JComboBox accountTypeSelector = new JComboBox();		
		accountTypeSelector.setModel(new DefaultComboBoxModel(new String[] {"regular", "administrator"}));
		AccountPanel.add(accountTypeSelector, "cell 1 0,alignx left,aligny center");




		JButton editAccountBtn = new JButton("OK");
		editAccountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String newAccountFirstname = textFieldFirstname.getText().toString();
				String newAccountLastname =  textFieldLastname.getText();
				String newAccountUsername = textFieldUsername.getText();
				String newAccountPassword = passwordField.getText();
				String newAccountType = (String) accountTypeSelector.getSelectedItem();

				try {
					dbman.insertDB("update sisca_account set sisca_account_first_name='"+newAccountFirstname+ "', sisca_account_last_name='" +newAccountLastname+ "', sisca_account_password='"+ newAccountPassword+"', sisca_account_type='"+newAccountType +"' where sisca_account_username='"+newAccountUsername+"'" );
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(newAccountUsername));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(editAccountBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Cancel me hace un BACK en el history
				String newAccountUsername = textFieldUsername.getText();
				HKJ_SisCA_MainPage.frame.setContentPane(accountInformationView(newAccountUsername));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});

		AddCancelPanel.add(cancelBtn, "cell 26 0");

		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelEditAccount= new JPanel();
		windowPanelEditAccount.setLayout(new BorderLayout(0, 0));
		windowPanelEditAccount.add(menuPanelEditAccount, BorderLayout.NORTH);
		windowPanelEditAccount.add(leftPanelEditccount, BorderLayout.WEST);
		windowPanelEditAccount.add(mainPanelEditAccount, BorderLayout.CENTER);

		return windowPanelEditAccount;

	}
}
