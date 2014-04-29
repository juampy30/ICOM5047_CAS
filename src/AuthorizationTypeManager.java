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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

import net.miginfocom.swing.MigLayout;
import databases.DBManager;
/**
 * 
 * @author Keishla Ortiz
 *
 */

public class AuthorizationTypeManager {

	private DBManager dbman;
	private String authorizationSelectedByUserFromAccountView;
	private String authorizationNameEdit;
	private int authorizationIDEdit;

	private String currentUser;
	/**
	 * Get the current date YYYY/MMM/DD
	 */
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	Calendar cal = Calendar.getInstance();
	String currentDate = dateFormat.format(cal.getTime());

	AuthorizationTypeManager(){
	}

	//////////////////////////////////////////////////////////////////
	//   Authorization Type View								    //
	//////////////////////////////////////////////////////////////////

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JPanel authorizationTypeView(){

		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////
		currentUser = HKJ_SisCA_MainPage.loggedUsernaneWith;
		
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
		AuthorizationTypeManagerLabel.setBackground(new Color(220,220,220));
		AuthorizationTypeManagerLabel.setOpaque(true);
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
		menuPanelAuthorizationType.add(userNamePanel, BorderLayout.CENTER);
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

		JLabel searchLabel = new JLabel("Search: ");
		searchAndAddPanel.add(searchLabel, "cell 0 0,alignx left,aligny center");
		searchLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		final DefaultListModel authorizationNameListView = new DefaultListModel();

		final JTextField textFieldSearch = new JTextField();
		textFieldSearch.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				/**
				 * searchTextField : element to search
				 */
				authorizationNameListView.clear();
				String searchTextField = textFieldSearch.getText();
				String searchElement = "select * from sisca_authorization where sisca_authorization_name ~*'"+searchTextField+"' and sisca_authorization_active='true' order by sisca_authorization_name";
				try {
					ArrayList<Object> searchResultDB = dbman.getFromDB(searchElement);
					//ArrayList<Object> searchResult = new ArrayList<Object>();
					String [] keyValue;
					String authorizationName = null;
					//[{1:A},{2:B},{3:C}]
					for(int i=0; i<searchResultDB.size(); i++){
						//obtener el elemento i del elemento 1 (el array del array) 
						for(int k=0 ; k<((List<Object>) searchResultDB.get(i)).size(); k++){
							//result = 1:A
							Object result = ((List<Object>) searchResultDB.get(i)).get(k);
							//keyValue -> {1,A}
							keyValue = result.toString().split("/");
							if(keyValue[0].equals("sisca_authorization_name")){
								authorizationName = (String) keyValue[1];
							}
						}
						authorizationNameListView.addElement(authorizationName.toUpperCase());
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				} catch (ParseException e1) {

					e1.printStackTrace();
				}	
			}
		});
		searchAndAddPanel.add(textFieldSearch, "cell 1 0,growx,aligny center");
		textFieldSearch.setColumns(10);
		JButton goButton = new JButton("Go");
		goButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent e) {
				/**
				 * searchTextField : element to search
				 */
				authorizationNameListView.clear();
				String searchTextField = textFieldSearch.getText();
				String searchElement = "select * from sisca_authorization where sisca_authorization_name ~*'"+searchTextField+"' and sisca_authorization_active='true' order by sisca_authorization_name";
				try {
					ArrayList<Object> searchResultDB = dbman.getFromDB(searchElement);
					//ArrayList<Object> searchResult = new ArrayList<Object>();
					String [] keyValue;
					String authorizationName = null;
					//[{1:A},{2:B},{3:C}]
					for(int i=0; i<searchResultDB.size(); i++){
						//obtener el elemento i del elemento 1 (el array del array) 
						for(int k=0 ; k<((List<Object>) searchResultDB.get(i)).size(); k++){
							//result = 1:A
							Object result = ((List<Object>) searchResultDB.get(i)).get(k);
							//keyValue -> {1,A}
							keyValue = result.toString().split("/");
							if(keyValue[0].equals("sisca_authorization_name")){
								authorizationName = (String) keyValue[1];
							}
						}
						authorizationNameListView.addElement(authorizationName.toUpperCase());
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		goButton.setPreferredSize(new Dimension(10, 29));
		searchAndAddPanel.add(goButton, "cell 2 0");

		JButton addNewAuthorizationTypeBtn = new JButton("Add New AuthorizationType");
		addNewAuthorizationTypeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (HKJ_SisCA_MainPage.getCanView()==true){
					HKJ_SisCA_MainPage.frame.setContentPane(addAuthorizationTypeView());
					HKJ_SisCA_MainPage.frame.pack(); 
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
				else{
					JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "You have no permission to Add a New Authorization Type");
				}
			}
		});
		searchAndAddPanel.add(addNewAuthorizationTypeBtn, "cell 25 0,alignx left,aligny top");
		JPanel AuthorizationTypeListPanel = new JPanel();
		AuthorizationTypeListPanel.setBackground(new Color(250,250,250));

		centerPanel.add(AuthorizationTypeListPanel, "cell 0 1,alignx center,growy");
		AuthorizationTypeListPanel.setLayout(new MigLayout("", "[724px]", "[360px]"));
		JScrollPane AuthorizationTypeScrollPane = new JScrollPane();
		AuthorizationTypeListPanel.add(AuthorizationTypeScrollPane, "cell 0 0,grow");

		/**
		 * Fill the List of Authorization types
		 * dbman : connect to DBManager to run the required querys 
		 */
		try {
			dbman = new DBManager();
			ArrayList<Object> authorizationTypeArrayListQuery = dbman.getFromDB("select * from sisca_authorization where sisca_authorization_active='true' order by sisca_authorization_name");
			/**
			 * userActiveListView : 
			 */
			String[] keyValue;
			String authorizationName = null;
			//[{1:A},{2:B},{3:C}]
			for(int i=0; i< authorizationTypeArrayListQuery.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) authorizationTypeArrayListQuery.get(i)).size(); k++){
					//result = 1:A 
					Object result = ((List<Object>) authorizationTypeArrayListQuery.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_authorization_name")){
						authorizationName = (String) keyValue[1];
					}
				}
				authorizationNameListView.addElement(authorizationName.toUpperCase());
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

		final JList AuthorizationTypeList = new JList(authorizationNameListView);

		AuthorizationTypeList.setSelectionForeground(UIManager.getColor("Button.darkShadow"));
		AuthorizationTypeList.setSelectionBackground(UIManager.getColor("Button.background"));
		AuthorizationTypeList.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					authorizationSelectedByUserFromAccountView = (String) AuthorizationTypeList.getSelectedValue();
					HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView(authorizationSelectedByUserFromAccountView)); // Wrong Way! =S
					HKJ_SisCA_MainPage.frame.pack();
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			}
		});
		AuthorizationTypeScrollPane.setViewportView(AuthorizationTypeList);

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

	public JPanel authorizationTypeInformationView(String authorization){


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
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView(null));
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		listAuthorizationTypeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listAuthorizationTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		listAuthorizationTypeLabel.setForeground((java.awt.Color) null);
		listAuthorizationTypeLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		listAuthorizationTypeLabel.setBackground(new Color(220,220,220));
		listAuthorizationTypeLabel.setOpaque(true);
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
		menuPanelAuthorizationTypeInformation.add(userNamePanel, BorderLayout.CENTER);
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

		/**
		 * Left Panel
		 */
		ArrayList<Object> authorizationNameListView = getAccountList();
		int position = 1;
		for(int i=0; i<authorizationNameListView.size() && i<10 ; i++){
			position = position+2;
			final JLabel userName = new JLabel(authorizationNameListView.get(i).toString().toUpperCase());
			userName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView(userName.getText()));
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

		JLabel searchLabel = new JLabel("Search: ");
		panel_100.add(searchLabel);
		searchLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		final JTextField searchTextField = new JTextField();
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String text = searchTextField.getText();

				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView(text));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		panel_100.add(searchTextField);
		searchTextField.setColumns(10);

		JPanel viewAndAddBynPanel = new JPanel();
		viewAndAddBynPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(viewAndAddBynPanel, "cell 0 23,alignx left,aligny center");
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
				
				if (HKJ_SisCA_MainPage.getCanView()==true){
					HKJ_SisCA_MainPage.frame.setContentPane(addAuthorizationTypeView());
					HKJ_SisCA_MainPage.frame.pack(); 
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
				else{
					JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "You have no permission to Add a New Authorization Type");
				}
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


		String createDate=null, createBy=null, editBy=null, editDate=null;
		/**
		 * Get the other information about authorization 
		 */
		try {
			ArrayList<Object> otherInfo = dbman.getFromDB("Select * from sisca_authorization where sisca_authorization_name ~*'"+authorization+"'");
			String[] keyValue;

			//[{1:A},{2:B},{3:C}]
			for(int i=0; i<otherInfo.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) otherInfo.get(i)).size(); k++){
					//result = 1:A
					Object result = ((List<Object>) otherInfo.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_authorization_creationdate")){
						createDate = (String) keyValue[1];
					}
					if(keyValue[0].equals("sisca_authorization_createdby")){
						createBy = (String) keyValue[1];
					}
					if(keyValue[0].equals("sisca_authorization_editdate")){
						editDate = (String) keyValue[1];
						if(editDate.equals("null")){
							editDate="Not Edit";
						}
					}
					if(keyValue[0].equals("sisca_authorization_editedby")){
						editBy = (String) keyValue[1];
						if(editBy.equals("null")){
							editBy="Not Edit";
						}
					}

				}
			}

		} catch (SQLException e2) {
			// do nothing
			e2.printStackTrace();
		} catch (ParseException e1) {
			// do nothing
			e1.printStackTrace();
		}	


		authorizationNameEdit = authorization;

		JLabel authorizationTypeName = new JLabel(authorization.toUpperCase());
		authorizationTypeName.setPreferredSize(new Dimension(100, 50));
		authorizationTypeName.setHorizontalAlignment(SwingConstants.CENTER);
		authorizationTypeName.setForeground(java.awt.Color.BLACK);
		authorizationTypeName.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		centerPanel.add(authorizationTypeName, "cell 0 0,alignx center,aligny top");
		
		JLabel otherInfo3 = new JLabel("    Created: " + createDate);
		otherInfo3.setPreferredSize(new Dimension(100, 100));
		otherInfo3.setHorizontalAlignment(SwingConstants.CENTER);
		otherInfo3.setForeground(java.awt.Color.BLACK);
		otherInfo3.setFont(new Font("Lucida Grande", Font.BOLD, 11));
		centerPanel.add(otherInfo3, "cell 0 1,alignx left,aligny bottom");

		JLabel otherInfo4 = new JLabel("    Created by: " + createBy );
		otherInfo4.setPreferredSize(new Dimension(100, 100));
		otherInfo4.setHorizontalAlignment(SwingConstants.CENTER);
		otherInfo4.setForeground(java.awt.Color.BLACK);
		otherInfo4.setFont(new Font("Lucida Grande", Font.BOLD, 11));
		centerPanel.add(otherInfo4, "cell 0 1,alignx left,aligny bottom");

		JLabel otherInfo1 = new JLabel("    Last Edited: " + editDate );
		otherInfo1.setPreferredSize(new Dimension(100, 100));
		otherInfo1.setHorizontalAlignment(SwingConstants.CENTER);
		otherInfo1.setForeground(java.awt.Color.BLACK);
		otherInfo1.setFont(new Font("Lucida Grande", Font.BOLD, 11));
		centerPanel.add(otherInfo1, "cell 0 1,alignx left,aligny bottom");

		JLabel otherInfo2 = new JLabel("    Edited by: " + editBy );
		otherInfo2.setPreferredSize(new Dimension(100, 100));
		otherInfo2.setHorizontalAlignment(SwingConstants.CENTER);
		otherInfo2.setForeground(java.awt.Color.BLACK);
		otherInfo2.setFont(new Font("Lucida Grande", Font.BOLD, 11));
		centerPanel.add(otherInfo2, "cell 0 1,alignx left,aligny bottom");

		JPanel editAndRemovePanel = new JPanel();
		editAndRemovePanel.setBackground(new Color(250,250,250));
		centerPanel.add(editAndRemovePanel, "cell 0 2,grow");
		editAndRemovePanel.setLayout(new MigLayout("", "[][][][][][][][]", "[][]"));

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (HKJ_SisCA_MainPage.getCanView()==true){
					HKJ_SisCA_MainPage.frame.setContentPane(editAuthorizationTypeView(authorizationIDEdit));
					HKJ_SisCA_MainPage.frame.pack(); 
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
				else{
					JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "You have no permission to Edit a authorization");
				}
			}
		});
		editAndRemovePanel.add(editButton, "cell 0 0");

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if (HKJ_SisCA_MainPage.getCanView()==true){
					int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are you sure you want to remove this authorization?", "REMOVE", 1);
					String authorizationToRemove = authorizationSelectedByUserFromAccountView;
					if(sure==0){
						try {
							int removedAthorizationID = dbman.getIndex("select sisca_authorization_id from sisca_authorization where sisca_authorization_name~*'"+authorizationToRemove+"'");
							dbman.updatetDB("update sisca_authorization set sisca_authorization_name='"+authorizationToRemove+"', sisca_authorization_active='false', sisca_authorization_deleteDate='"+currentDate+"', sisca_authorization_deletedBy="+currentUser+" where sisca_authorization_name~*'" +authorizationToRemove+"'");
							dbman.updatetDB("update sisca_authorization_parking_list set sisca_authorization_parking_active='false' where sisca_authorization_id="+removedAthorizationID); 
						} catch (SQLException e) {

							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView());
						HKJ_SisCA_MainPage.frame.pack(); 
						HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
					}
					else{
						HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView(authorizationSelectedByUserFromAccountView));
						HKJ_SisCA_MainPage.frame.pack(); 
						HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
					}
				}
				else{
					JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "You have no permission to Edit a parking");
				}

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

	public JPanel addAuthorizationTypeView(){
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
		AddAuthorizationTypeLabel.setBackground(new Color(220,220,220));
		AddAuthorizationTypeLabel.setOpaque(true);
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
		menuPanelAddAuthorizationType.add(userNamePanel, BorderLayout.CENTER);
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

		ArrayList<Object> authorizationNameListView = getAccountList();
		int position = 1;
		for(int i=0; i<authorizationNameListView.size() && i<10 ; i++){
			position = position+2;
			final JLabel userName = new JLabel(authorizationNameListView.get(i).toString().toUpperCase());
			userName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView(userName.getText()));
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

		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(searchPanel, "cell 0 1,growx,aligny center");
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

		JLabel searchLabel = new JLabel("Search: ");
		searchPanel.add(searchLabel);
		searchLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		final JTextField searchTextField = new JTextField();
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = searchTextField.getText();

				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView(text));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());				}
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

		JPanel AuthorizationTypeIDPanel = new JPanel();
		AuthorizationTypeIDPanel.setBackground((java.awt.Color) null);
		centerPanel.add(AuthorizationTypeIDPanel, "cell 0 1,growx,aligny top");
		AuthorizationTypeIDPanel.setLayout(new MigLayout("", "[57.00px][371.00px]", "[28px]"));

		JLabel nameLabel = new JLabel("New Authorization Name: ");
		AuthorizationTypeIDPanel.add(nameLabel, "cell 0 0,alignx left,aligny center");
		nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel warningLabel = new JLabel("Characters ! @ # $ % ^ & * ( ) are not valid!");
		AuthorizationTypeIDPanel.add(warningLabel, "cell 2 0,alignx left,aligny center");
		warningLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		final JCheckBox unconditionalCheckBox = new JCheckBox("Unconditional Entry");
		AuthorizationTypeIDPanel.add(unconditionalCheckBox, "cell 0 1,alignx left,aligny top");
		unconditionalCheckBox.setBackground(new Color(250,250,250));
		unconditionalCheckBox.setOpaque(true);

		final JTextField addNameTextField = new JTextField();
		addNameTextField.setPreferredSize(new Dimension(200, 100));
		AuthorizationTypeIDPanel.add(addNameTextField, "cell 1 0,grow");
		addNameTextField.setColumns(10);

		JButton AddAuthorizationTypeBtn = new JButton("OK");
		AddAuthorizationTypeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				/**
				 * Adding a new Authorization type Name
				 */
				String createAuthorizationType = addNameTextField.getText();
				/**
				 * Verificar que el permiso no exista
				 */
			
				ArrayList<Object> authorizationTypeExistDB;
				boolean alreadyExist=false;
				String authorizationName = null;
				
				
				
				
				int authorizationID = -1;
				try {
					authorizationTypeExistDB = dbman.getFromDB("select * from sisca_authorization ");
					ArrayList<Object> authorizationTypeExist = new ArrayList<Object>();
					String[] keyValue;

					for(int i=0; i<authorizationTypeExistDB.size(); i++){
						//obtener el elemento i del elemento 1 (el array del array) 
						for(int k=0 ; k<((List<Object>) authorizationTypeExistDB.get(i)).size(); k++){
							//result = 1:A
							Object result = ((List<Object>) authorizationTypeExistDB.get(i)).get(k);
							//keyValue -> {1,A}
							keyValue = result.toString().split("/");
							if(keyValue[0].equals("sisca_authorization_name")){
								authorizationName = (String) keyValue[1];
							}	
							if(keyValue[0].equals("sisca_authorization_id")){
								authorizationID = Integer.valueOf(keyValue[1]);
							}	
						}
						authorizationTypeExist.add(authorizationName);
					}
					/**
					 * Verificar permisos
					 */
					for(int i=0 ; i<authorizationTypeExist.size(); i++){
						if(createAuthorizationType.toLowerCase().equals(authorizationTypeExist.get(i).toString().toLowerCase())){
							alreadyExist=true;
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/**
				 * UnconditionEntry
				 */
				boolean unconditional = false;
				if(unconditionalCheckBox.isSelected()){
					unconditional = true;
				}
				/**
				 * 
				 */
				if(alreadyExist){
					JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "Invalid authorization name.", "ERROR", 0);
				}
				else if(createAuthorizationType.isEmpty()){
					JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "Authorization Type Name in empty.");
				}
				else{
					try {
						
						dbman.updatetDB("insert into sisca_authorization(sisca_authorization_name, sisca_authorization_active, sisca_authorization_creationDate, sisca_authorization_createdBy, sisca_authorization_uncondtional_entry) values('"+createAuthorizationType+"' , 'true', '"+currentDate+"',"+currentUser+", '"+unconditional+"')");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};

					HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView(createAuthorizationType));
					HKJ_SisCA_MainPage.frame.pack(); 
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

				}
			}
		});
		AddCancelPanel.add(AddAuthorizationTypeBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Cancel me hace un BACK en el history
				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});

		AddCancelPanel.add(cancelBtn, "cell 26 0");

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

	public JPanel editAuthorizationTypeView(int ID){
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
		editAuthorizationTypeLabel.setBackground(new Color(220,220,220));
		editAuthorizationTypeLabel.setOpaque(true);
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
		menuPanelEditAuthorizationType.add(userNamePanel, BorderLayout.CENTER);
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

		ArrayList<Object> authorizationNameListView = getAccountList();
		int position = 1;
		for(int i=0; i<authorizationNameListView.size() && i<10 ; i++){
			position = position+2;
			final JLabel userName = new JLabel(authorizationNameListView.get(i).toString().toUpperCase());
			userName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView(userName.getText()));
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

		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(searchPanel, "cell 0 1,growx,aligny top");
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

		JLabel searchLabel = new JLabel("Search: ");
		searchPanel.add(searchLabel);
		searchLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		final JTextField searchTextField = new JTextField();
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = searchTextField.getText();

				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeView(text));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		searchPanel.add(searchTextField);
		searchTextField.setColumns(10);

		JPanel viewAndAddBynPanel = new JPanel();
		viewAndAddBynPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(viewAndAddBynPanel, "cell 0 23,alignx left,aligny center");
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

		JPanel AuthorizationTypeIDPanel = new JPanel();
		AuthorizationTypeIDPanel.setBackground((java.awt.Color) null);
		centerPanel.add(AuthorizationTypeIDPanel, "cell 0 1,growx,aligny top");
		AuthorizationTypeIDPanel.setLayout(new MigLayout("", "[57.00px][371.00px]", "[28px]"));

		/**
		 * authorizationNameEdit: the authorization name selected to edit
		 */		
		JLabel nameLabel = new JLabel("Authorization New Name:");
		AuthorizationTypeIDPanel.add(nameLabel, "cell 0 0,alignx left,aligny center");
		nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		final JTextField editNameTextField = new JTextField(authorizationNameEdit);
		editNameTextField.setPreferredSize(new Dimension(200, 100));
		AuthorizationTypeIDPanel.add(editNameTextField, "cell 1 0,grow");
		editNameTextField.setColumns(10);
		
		JLabel warningLabel = new JLabel("Characters ! @ # $ % ^ & * ( ) are not valid!");
		AuthorizationTypeIDPanel.add(warningLabel, "cell 1 0,alignx left,aligny center");
		warningLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		final JCheckBox unconditionalCheckBox = new JCheckBox("Unconditional Entry");
		AuthorizationTypeIDPanel.add(unconditionalCheckBox, "cell 0 1,alignx left,aligny top");
		unconditionalCheckBox.setBackground(new Color(250,250,250));
		unconditionalCheckBox.setOpaque(true);
		
		try {
			ArrayList result = new ArrayList();
			result = dbman.getNotificationsInformation("select * from sisca_authorization where sisca_authorization_name~*'"+authorizationNameEdit+"'");
			String unconditional = null;
			Object bool =  ((List) result.get(0)).get(9);
			unconditional = (String) bool;
			if(unconditional.equals("t")){
				unconditionalCheckBox.doClick();
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		JPanel AddCancelPanel = new JPanel();
		AddCancelPanel.setBackground(new Color(250,250,250));
		centerPanel.add(AddCancelPanel, "cell 0 2,grow");
		AddCancelPanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));

		JButton editAuthorizationTypeBtn = new JButton("OK");
		editAuthorizationTypeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String newAuthorizationType = editNameTextField.getText();
				boolean alreadyExist=false;
				boolean dontChange = false;
				
				if(newAuthorizationType.toLowerCase().equals(authorizationNameEdit.toLowerCase())){
					dontChange = true;
				}

				ArrayList<Object> unavailableAuthorizationNamesDB;
				try {
					unavailableAuthorizationNamesDB = dbman.getFromDB("select * from sisca_authorization");
					ArrayList<Object> unavailableAuthorizationNames = new ArrayList<Object>();
					String[] keyValue;
					String authorizationName=null;
					int authorizationID = -1;

					for(int i=0; i<unavailableAuthorizationNamesDB.size(); i++){
						//obtener el elemento i del elemento 1 (el array del array) 
						for(int k=0 ; k<((List<Object>) unavailableAuthorizationNamesDB.get(i)).size(); k++){
							//result = 1:A
							Object result = ((List<Object>) unavailableAuthorizationNamesDB.get(i)).get(k);
							//keyValue -> {1,A}
							keyValue = result.toString().split("/");
							if(keyValue[0].equals("sisca_authorization_name")){
								authorizationName = (String) keyValue[1];
							}
							
						}
						unavailableAuthorizationNames.add(authorizationName.toLowerCase());
					}
					for(int i=0; i<unavailableAuthorizationNames.size();i++){
						if(newAuthorizationType.toLowerCase().equals(((String) unavailableAuthorizationNames.get(i)).toLowerCase())){
							alreadyExist = true;
						}
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/**
				 * Unconditional Entry
				 */
				boolean unconditional = false;
				if(unconditionalCheckBox.isSelected()){
					unconditional = true;
				}

				if(alreadyExist && !dontChange){
					JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "Invalid Authorization Type Name.", "ERROR", 0);
				
				}
				else if(newAuthorizationType.isEmpty()){
					JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "Empty field...", "ERROR", 0);		
				}
				else
				{
					try {
						dbman.updatetDB("update sisca_authorization set sisca_authorization_name='"+newAuthorizationType.toUpperCase()+"', sisca_authorization_editDate='"+currentDate+"', sisca_authorization_editedBy="+ currentUser+", sisca_authorization_uncondtional_entry='"+unconditional+"' where sisca_authorization_name~*'"+authorizationNameEdit.toUpperCase()+"'");
						HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView(newAuthorizationType));
						HKJ_SisCA_MainPage.frame.pack(); 
						HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/*
					HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView(newAuthorizationType));
					HKJ_SisCA_MainPage.frame.pack(); 
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
					 */
				}

			}
		});
		AddCancelPanel.add(editAuthorizationTypeBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView(authorizationNameEdit));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});

		AddCancelPanel.add(cancelBtn, "cell 26 0");


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

	/**
	 * 
	 * @return
	 */
	private ArrayList<Object> getAccountList() {
		ArrayList<Object> authorizationNameFromDB = new ArrayList<Object>();
		try {
			ArrayList<Object> authorizationNameListFromDB = dbman.getFromDB("select * from sisca_authorization where sisca_authorization_active='true' order by sisca_authorization_name");
			String[] keyValue;
			String authorizationName = null;
			for(int i=0; i<authorizationNameListFromDB.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) authorizationNameListFromDB.get(i)).size(); k++){
					//result = 1:A
					Object result = ((List<Object>) authorizationNameListFromDB.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_authorization_name")){
						authorizationName = (String) keyValue[1];
					}
				}
				authorizationNameFromDB.add(authorizationName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authorizationNameFromDB;
	}

	public JPanel authorizationTypeView(String athorizationSearch){

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
		AuthorizationTypeManagerLabel.setBackground(new Color(220,220,220));
		AuthorizationTypeManagerLabel.setOpaque(true);
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
		menuPanelAuthorizationType.add(userNamePanel, BorderLayout.CENTER);
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

		JLabel searchLabel = new JLabel("Search: ");
		searchAndAddPanel.add(searchLabel, "cell 0 0,alignx left,aligny center");
		searchLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		final DefaultListModel authorizationNameListView = new DefaultListModel();

		final JTextField textFieldSearch = new JTextField();
		textFieldSearch.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				/**
				 * searchTextField : element to search
				 */
				authorizationNameListView.clear();
				String searchTextField = textFieldSearch.getText();
				String searchElement = "select * from sisca_authorization where sisca_authorization_name ~*'"+searchTextField+"' and sisca_authorization_active='true' order by sisca_authorization_name";
				try {
					ArrayList<Object> searchResultDB = dbman.getFromDB(searchElement);
					//ArrayList<Object> searchResult = new ArrayList<Object>();
					String [] keyValue;
					String authorizationName = null;
					//[{1:A},{2:B},{3:C}]
					for(int i=0; i<searchResultDB.size(); i++){
						//obtener el elemento i del elemento 1 (el array del array) 
						for(int k=0 ; k<((List<Object>) searchResultDB.get(i)).size(); k++){
							//result = 1:A
							Object result = ((List<Object>) searchResultDB.get(i)).get(k);
							//keyValue -> {1,A}
							keyValue = result.toString().split("/");
							if(keyValue[0].equals("sisca_authorization_name")){
								authorizationName = (String) keyValue[1];
							}
						}
						authorizationNameListView.addElement(authorizationName.toUpperCase());
					}

				} catch (SQLException e1) {

					e1.printStackTrace();
				} catch (ParseException e1) {

					e1.printStackTrace();
				}	
			}
		});
		searchAndAddPanel.add(textFieldSearch, "cell 1 0,growx,aligny center");
		textFieldSearch.setColumns(10);
		JButton goButton = new JButton("Go");
		goButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent e) {
				/**
				 * searchTextField : element to search
				 */
				authorizationNameListView.clear();
				String searchTextField = textFieldSearch.getText();
				String searchElement = "select * from sisca_authorization where sisca_authorization_name ~*'"+searchTextField+"' and sisca_authorization_active='true' order by sisca_authorization_name";
				try {
					ArrayList<Object> searchResultDB = dbman.getFromDB(searchElement);
					//ArrayList<Object> searchResult = new ArrayList<Object>();
					String [] keyValue;
					String authorizationName = null;
					//[{1:A},{2:B},{3:C}]
					for(int i=0; i<searchResultDB.size(); i++){
						//obtener el elemento i del elemento 1 (el array del array) 
						for(int k=0 ; k<((List<Object>) searchResultDB.get(i)).size(); k++){
							//result = 1:A
							Object result = ((List<Object>) searchResultDB.get(i)).get(k);
							//keyValue -> {1,A}
							keyValue = result.toString().split("/");
							if(keyValue[0].equals("sisca_authorization_name")){
								authorizationName = (String) keyValue[1];
							}
						}
						authorizationNameListView.addElement(authorizationName.toUpperCase());
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				} catch (ParseException e1) {

					e1.printStackTrace();
				}
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

		/**
		 * Fill the List of Authorization types
		 * dbman : connect to DBManager to run the required querys 
		 */
		try {
			dbman = new DBManager();
			ArrayList<Object> authorizationTypeArrayListQuery = dbman.getFromDB("select * from sisca_authorization where sisca_authorization_active='true' and sisca_authorization_name ~*'"+athorizationSearch+"' order by sisca_authorization_name");
			/**
			 * userActiveListView : 
			 */
			String[] keyValue;
			String authorizationName = null;
			//[{1:A},{2:B},{3:C}]
			for(int i=0; i< authorizationTypeArrayListQuery.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) authorizationTypeArrayListQuery.get(i)).size(); k++){
					//result = 1:A 
					Object result = ((List<Object>) authorizationTypeArrayListQuery.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_authorization_name")){
						authorizationName = (String) keyValue[1];
					}
				}
				authorizationNameListView .addElement(authorizationName.toUpperCase());
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

		final JList AuthorizationTypeList = new JList(authorizationNameListView);

		AuthorizationTypeList.setSelectionForeground(UIManager.getColor("Button.darkShadow"));
		AuthorizationTypeList.setSelectionBackground(UIManager.getColor("Button.background"));
		AuthorizationTypeList.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					authorizationSelectedByUserFromAccountView = (String) AuthorizationTypeList.getSelectedValue();
					HKJ_SisCA_MainPage.frame.setContentPane(authorizationTypeInformationView(authorizationSelectedByUserFromAccountView)); // Wrong Way! =S
					HKJ_SisCA_MainPage.frame.pack();
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			}
		});
		AuthorizationTypeScrollPane.setViewportView(AuthorizationTypeList);

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

}
