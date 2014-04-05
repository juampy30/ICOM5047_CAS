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

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;
import databases.DBManager;


public class ParkingManager {

	static DefaultListModel registerParkingsList;
	static ArrayList<Object> registerParkings;
	static DBManager dbman;
	static DefaultListModel availableSADModelList;
	static DefaultListModel availableAtzTypesModelList;
	static ArrayList<Object> availableSAD;
	static ArrayList<Object> availableAtzType;
	static DefaultListModel chosenSADModelList;
	static DefaultListModel chosenAtzTypesModelList;


	public ParkingManager(){

	}

	//////////////////////////////////////////////////////////////////
	//   Parking View								            	//
	//////////////////////////////////////////////////////////////////


	static JPanel parkingView(){
		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelParking = new JPanel();
		menuPanelParking.setBackground(new Color(255,239,80));
		menuPanelParking.setPreferredSize(new Dimension(10, 30));
		menuPanelParking.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(250, 10));
		menuPanelParking.add(menuOptionsPanel, BorderLayout.WEST);
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

		JLabel parkingManagerLabel = new JLabel("Parking");
		parkingManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		parkingManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		parkingManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		parkingManagerLabel.setForeground((java.awt.Color) null);
		parkingManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		parkingManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		parkingManagerLabel.setBounds(66, -11, 109, 53);
		menuOptionsPanel.add(parkingManagerLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelParking.add(logOutPanel, BorderLayout.EAST);
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
		menuPanelParking.add(userNamePanel, BorderLayout.CENTER);
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
		JPanel mainPanelParking = new JPanel();
		mainPanelParking.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelParking.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelParking.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelParking.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelParking.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelParking.add(centerPanel, BorderLayout.CENTER);
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
				HKJ_SisCA_MainPage.frame.setContentPane(parkingView());
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
				String myString = textFieldSearch.getSelectedText();
				System.out.println("Hola >> " + myString);
				HKJ_SisCA_MainPage.frame.setContentPane(parkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		goButton.setPreferredSize(new Dimension(10, 29));
		searchAndAddPanel.add(goButton, "cell 2 0");

		JButton addNewParkingBtn = new JButton("Add New Parking");
		addNewParkingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addParkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		searchAndAddPanel.add(addNewParkingBtn, "cell 25 0,alignx left,aligny top");
		JPanel parkingListPanel = new JPanel();
		parkingListPanel.setBackground(new Color(250,250,250));

		centerPanel.add(parkingListPanel, "cell 0 1,alignx center,growy");
		parkingListPanel.setLayout(new MigLayout("", "[724px]", "[360px]"));
		JScrollPane scrollPane = new JScrollPane();
		parkingListPanel.add(scrollPane, "cell 0 0,grow");


		////////////////
		registerParkingsList = new DefaultListModel();
		registerParkings = new ArrayList<Object>();

		try {
			DBManager dbman2 = new DBManager();
			registerParkings = dbman2 .getFromDB("select sisca_parking_name, sisca_parking_starthour, sisca_parking_endhour from sisca_parking");
			registerParkings = dbman2.getRegisterParkings(registerParkings);
			for(int i=0; i<registerParkings.size(); i++){
				System.out.println(((String) registerParkings.get(i)).toUpperCase());
				registerParkingsList.addElement(((String) registerParkings.get(i)).toUpperCase());
			}
		} catch (SQLException  e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JList parkingViewList = new JList(registerParkingsList);
		////////////////
		parkingViewList.setSelectionForeground(UIManager.getColor("Button.darkShadow"));
		parkingViewList.setSelectionBackground(UIManager.getColor("Button.background"));
		parkingViewList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView()); // Wrong Way! =S
					HKJ_SisCA_MainPage.frame.pack();
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			}
		});
		scrollPane.setViewportView(parkingViewList);

		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelParking= new JPanel();
		windowPanelParking.setLayout(new BorderLayout(0, 0));
		windowPanelParking.add(menuPanelParking, BorderLayout.NORTH);
		windowPanelParking.add(mainPanelParking, BorderLayout.CENTER);

		return windowPanelParking;
	}

	//////////////////////////////////////////////////////////////////
	//   Parking Information View								    //
	//////////////////////////////////////////////////////////////////

	static JPanel parkingInformationView() {

		/////////////////////////////////////////////////////////
		//           Menu Panel
		/////////////////////////////////////////////////////////

		// Configurations 
		JPanel menuPanelParkingInformation = new JPanel();
		menuPanelParkingInformation.setBackground(new Color(255,239,80));
		menuPanelParkingInformation.setPreferredSize(new Dimension(10, 30));
		menuPanelParkingInformation.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(375, 10));
		menuPanelParkingInformation.add(menuOptionsPanel, BorderLayout.WEST);
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

		JLabel parkingManagerLabel = new JLabel("Parking");
		parkingManagerLabel.setPreferredSize(new Dimension(30, 16));
		parkingManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingView());
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		parkingManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		parkingManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		parkingManagerLabel.setForeground((java.awt.Color) null);
		parkingManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		parkingManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		parkingManagerLabel.setBounds(66, -11, 112, 53);
		menuOptionsPanel.add(parkingManagerLabel);

		JLabel listParkingLabel = new JLabel("Parking Information");
		listParkingLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		listParkingLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listParkingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		listParkingLabel.setForeground((java.awt.Color) null);
		listParkingLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		listParkingLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listParkingLabel.setBounds(176, -11, 195, 53);
		menuOptionsPanel.add(listParkingLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelParkingInformation.add(logOutPanel, BorderLayout.EAST);
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
		menuPanelParkingInformation.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel(HKJ_SisCA_MainPage.getActiveUsername());
		userNamePanel.add(userNameLabel, BorderLayout.EAST);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground((java.awt.Color) null);
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));

		JLayeredPane liveSystemPanel2 = new JLayeredPane();
		liveSystemPanel2.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));


		/////////////////////////////////////////////////////////
		//           Left Panel
		/////////////////////////////////////////////////////////

		// Configurations 
		JPanel leftPanelParkingInformation = new JPanel();
		leftPanelParkingInformation.setBackground(new Color(245,245,245));
		leftPanelParkingInformation.setPreferredSize(new Dimension(390, 10));
		leftPanelParkingInformation.setLayout(new BorderLayout(0, 0));

		JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanelParkingInformation.add(scrollPane);

		JLayeredPane liveSystemPanel21 = new JLayeredPane();
		scrollPane.setViewportView(liveSystemPanel);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel21.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));

		// Parking Name Labels & MouseCliked Event 

		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px]"));


		// LeftPanel Labels
		JLabel LSystemLabel = new JLabel("PARKING LIST");
		liveSystemPanel.add(LSystemLabel, "cell 0 0,alignx center,aligny top");
		LSystemLabel.setPreferredSize(new Dimension(100, 50));
		LSystemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LSystemLabel.setForeground(java.awt.Color.BLACK);
		LSystemLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));



		////////////////////////////////////////////////////////////////////////////////////////////////
		//Query for get Parking Names for the Labels
		////////////////////////////////////////////////////////////////////////////////////
		ArrayList pNameLabelsArray = new ArrayList() ;
		try {
			dbman= new DBManager();

			pNameLabelsArray=dbman.getFromDB("Select * from sisca_parking ORDER BY sisca_parking_name");
			//System.out.println("Before Test:"+pNameLabelsArray);
			pNameLabelsArray= dbman.getAvailableParkingNamesFromDB(pNameLabelsArray);
			//System.out.println("After Test:"+pNameLabelsArray.toString());


		} catch (ClassNotFoundException e2) {
			//TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			//TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ParseException e1) {
			//TODO Auto-generated catch block
			e1.printStackTrace();
		}



		JLabel pName1 = new JLabel((String) pNameLabelsArray.get(0));

		pName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName1, "cell 0 3,alignx left,aligny top");

		JLabel pName2 = new JLabel((String) pNameLabelsArray.get(1));
		pName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName2, "cell 0 5,alignx left,aligny top");

		JLabel pName3 = new JLabel((String) pNameLabelsArray.get(2));
		pName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName3, "cell 0 7,alignx left,aligny top");

		JLabel pName4 = new JLabel((String) pNameLabelsArray.get(3));
		pName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName4, "cell 0 9,alignx left,aligny top");

		JLabel pName5 = new JLabel((String) pNameLabelsArray.get(4));
		pName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName5, "cell 0 11,alignx left,aligny top");

		JLabel pName6 = new JLabel((String) pNameLabelsArray.get(5));
		pName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName6, "cell 0 13,alignx left,aligny top");

		JLabel pName7 = new JLabel((String) pNameLabelsArray.get(6));
		pName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName7, "cell 0 15,alignx left,aligny top");

		JLabel pName8 = new JLabel((String) pNameLabelsArray.get(7));
		pName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName8, "cell 0 17,alignx left,aligny top");

		JLabel pName9 = new JLabel((String) pNameLabelsArray.get(8));
		pName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName9, "cell 0 19,alignx left,aligny top");

		JLabel pName10 = new JLabel((String) pNameLabelsArray.get(9));
		pName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName10, "cell 0 21,alignx left,aligny top");

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
		panel_100.add(searchTextField);
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		searchTextField.setColumns(10);

		JPanel viewAndAddBynPanel = new JPanel();
		viewAndAddBynPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(viewAndAddBynPanel, "cell 0 23,alignx left,aligny top");
		viewAndAddBynPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton viewAllButton = new JButton("View All");
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New Parking");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addParkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(addNewButton);




		JButton addNewButton1 = new JButton("Add New Parking");
		addNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//

				HKJ_SisCA_MainPage.frame.setContentPane(addParkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(addNewButton1);




		/////////////////////////////////////////////////////////
		//           Main Panel
		/////////////////////////////////////////////////////////

		// Configurations
		JPanel mainPanelParkingInformation = new JPanel();
		mainPanelParkingInformation.setLayout(new BorderLayout(0, 0));


		// Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelParkingInformation.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelParkingInformation.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelParkingInformation.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelParkingInformation.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelParkingInformation.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[849.00px]", "[50px][][12px][19px][12px][19px][12px][19px][12px][19px][12px][19px][12px][65px]"));

		JLabel parkingName = new JLabel("Parking Name");
		parkingName.setPreferredSize(new Dimension(100, 50));
		parkingName.setHorizontalAlignment(SwingConstants.CENTER);
		parkingName.setForeground(java.awt.Color.BLACK);
		parkingName.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		centerPanel.add(parkingName, "cell 0 0,alignx center,aligny top");

		JLabel capacity = new JLabel("Capacity: ");
		capacity.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		centerPanel.add(capacity, "cell 0 1");

		JLabel lblAvailableCapacity = new JLabel("Available Capacity: ");
		lblAvailableCapacity.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		centerPanel.add(lblAvailableCapacity, "cell 0 3,alignx left,aligny top");

		JLabel authorizations = new JLabel("Authorization Type(s): ");
		authorizations.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		centerPanel.add(authorizations, "cell 0 5,alignx left,aligny top");

		JSeparator separator_12 = new JSeparator();
		centerPanel.add(separator_12, "cell 0 4,growx,aligny top");

		JSeparator separator_13 = new JSeparator();
		centerPanel.add(separator_13, "cell 0 6,growx,aligny top");

		JLabel days = new JLabel("Operation Days: ");
		days.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		centerPanel.add(days, "cell 0 7,growx,aligny top");

		JSeparator separator_14 = new JSeparator();
		centerPanel.add(separator_14, "cell 0 8,growx,aligny top");

		JLabel hours = new JLabel("Operation Hours: ");
		hours.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		centerPanel.add(hours, "cell 0 9,growx,aligny top");

		JSeparator separator_15 = new JSeparator();
		centerPanel.add(separator_15, "cell 0 10,growx,aligny top");

		JLabel sads = new JLabel("SAD's:");
		sads.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		centerPanel.add(sads, "cell 0 11,growx,aligny top");

		JSeparator separator_16 = new JSeparator();
		centerPanel.add(separator_16, "cell 0 12,growx,aligny top");

		JSeparator separator_17 = new JSeparator();
		centerPanel.add(separator_17, "cell 0 2,growx,aligny top");

		JPanel editAndRemovePanel = new JPanel();
		editAndRemovePanel.setBackground(new Color(250,250,250));
		centerPanel.add(editAndRemovePanel, "cell 0 13,grow");
		editAndRemovePanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//HKJ_SisCA_MainPage.frame.setContentPane(editParkingView());
				//HKJ_SisCA_MainPage.frame.pack(); 
				//HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		editAndRemovePanel.add(editButton, "cell 0 0");

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO remover
				HKJ_SisCA_MainPage.frame.setContentPane(parkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}

		});
		editAndRemovePanel.add(btnRemove, "cell 26 0");


		////////////////////////////////////////////////////////
		//           Window Panel
		/////////////////////////////////////////////////////////

		// Configurations
		JPanel windowPanelParkingInformation= new JPanel();
		windowPanelParkingInformation.setLayout(new BorderLayout(0, 0));
		windowPanelParkingInformation.add(menuPanelParkingInformation, BorderLayout.NORTH);
		windowPanelParkingInformation.add(leftPanelParkingInformation, BorderLayout.WEST);
		windowPanelParkingInformation.add(mainPanelParkingInformation, BorderLayout.CENTER);



		return windowPanelParkingInformation;
	}

	//////////////////////////////////////////////////////////////////
	//   Add Parking View				      			         	//
	//////////////////////////////////////////////////////////////////

	static JPanel addParkingView(){

		/////////////////////////////////////////////////////////
		//           Menu Panel
		/////////////////////////////////////////////////////////

		// Configurations 
		JPanel menuPanelAddParking = new JPanel();
		menuPanelAddParking.setBackground(new Color(255,239,80));
		menuPanelAddParking.setPreferredSize(new Dimension(10, 30));
		menuPanelAddParking.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(375, 10));
		menuPanelAddParking.add(menuOptionsPanel, BorderLayout.WEST);
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

		JLabel parkingManagerLabel = new JLabel("Parking");
		parkingManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		parkingManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		parkingManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		parkingManagerLabel.setForeground((java.awt.Color) null);
		parkingManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		parkingManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		parkingManagerLabel.setBounds(66, -11, 112, 53);
		menuOptionsPanel.add(parkingManagerLabel);

		JLabel addParkingLabel = new JLabel("Add Parking");
		addParkingLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(addParkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		addParkingLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addParkingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addParkingLabel.setForeground((java.awt.Color) null);
		addParkingLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		addParkingLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		addParkingLabel.setBounds(177, -11, 129, 53);
		menuOptionsPanel.add(addParkingLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelAddParking.add(logOutPanel, BorderLayout.EAST);
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
		menuPanelAddParking.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel(HKJ_SisCA_MainPage.getActiveUsername());
		userNamePanel.add(userNameLabel, BorderLayout.EAST);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground((java.awt.Color) null);
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));



		/////////////////////////////////////////////////////////
		//           Left Panel
		/////////////////////////////////////////////////////////

		// Configurations 
		JPanel leftPanelAddParking = new JPanel();
		leftPanelAddParking.setBackground(new Color(245,245,245));
		leftPanelAddParking.setPreferredSize(new Dimension(390, 10));
		leftPanelAddParking.setLayout(new BorderLayout(0, 0));

		JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanelAddParking.add(scrollPane);

		JLayeredPane liveSystemPanel2 = new JLayeredPane();
		scrollPane.setViewportView(liveSystemPanel);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel2.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));

		// Parking Name Labels & MouseCliked Event 

		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px]"));


		// LeftPanel Labels
		JLabel LSystemLabel = new JLabel("PARKING LIST");
		liveSystemPanel.add(LSystemLabel, "cell 0 0,alignx center,aligny top");
		LSystemLabel.setPreferredSize(new Dimension(100, 50));
		LSystemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LSystemLabel.setForeground(java.awt.Color.BLACK);
		LSystemLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		////////////////////////////////////////////////////////////////////////////////////////////////
		// Query for get Parking Names for the Labels
		////////////////////////////////////////////////////////////////////////////////////
		ArrayList pNameLabelsArray = new ArrayList() ;
		try {
			dbman= new DBManager();

			pNameLabelsArray=dbman.getFromDB("Select * from sisca_parking ORDER BY sisca_parking_name");
			//System.out.println("Before Test:"+pNameLabelsArray);
			pNameLabelsArray= dbman.getAvailableParkingNamesFromDB(pNameLabelsArray);
			//System.out.println("After Test:"+pNameLabelsArray.toString());


		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



		JLabel pName1 = new JLabel();
		if(pNameLabelsArray.size()>=1){
			pName1.setText((String) pNameLabelsArray.get(0));
		}
		pName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName1, "cell 0 3,alignx left,aligny top");

		JLabel pName2 = new JLabel();
		if(pNameLabelsArray.size()>=2){
			pName2.setText((String) pNameLabelsArray.get(1));
		}
		pName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName2, "cell 0 5,alignx left,aligny top");

		JLabel pName3 = new JLabel();
		if(pNameLabelsArray.size()>=3){
			pName3.setText((String) pNameLabelsArray.get(2));
		}
		pName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName3, "cell 0 7,alignx left,aligny top");

		JLabel pName4 = new JLabel();
		if(pNameLabelsArray.size()>=4){
			pName4.setText((String) pNameLabelsArray.get(3));
		}
		pName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName4, "cell 0 9,alignx left,aligny top");

		JLabel pName5 = new JLabel();
		if(pNameLabelsArray.size()>=5){
			pName5.setText((String) pNameLabelsArray.get(4));
		}
		pName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName5, "cell 0 11,alignx left,aligny top");

		JLabel pName6 = new JLabel();
		if(pNameLabelsArray.size()>=6){
			pName6.setText((String) pNameLabelsArray.get(5));
		}
		pName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName6, "cell 0 13,alignx left,aligny top");

		JLabel pName7 = new JLabel();
		if(pNameLabelsArray.size()>=7){
			pName7.setText((String) pNameLabelsArray.get(6));
		}
		pName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName7, "cell 0 15,alignx left,aligny top");

		JLabel pName8 = new JLabel();
		if(pNameLabelsArray.size()>=8){
			pName8.setText((String) pNameLabelsArray.get(7));
		}
		pName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName8, "cell 0 17,alignx left,aligny top");

		JLabel pName9 = new JLabel();
		if(pNameLabelsArray.size()>=9){
			pName9.setText((String) pNameLabelsArray.get(8));
		}
		pName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName9, "cell 0 19,alignx left,aligny top");

		JLabel pName10 = new JLabel();
		if(pNameLabelsArray.size()>=10){
			pName10.setText((String) pNameLabelsArray.get(9));
		}
		pName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName10, "cell 0 21,alignx left,aligny top");

		// Separators
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
		panel_100.add(searchTextField);
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		searchTextField.setColumns(10);

		JPanel viewAndAddBynPanel = new JPanel();
		viewAndAddBynPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(viewAndAddBynPanel, "cell 0 23,alignx left,aligny top");
		viewAndAddBynPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton viewAllButton = new JButton("View All");
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New Parking");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addParkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(addNewButton);




		/////////////////////////////////////////////////////////
		//           Main Panel
		/////////////////////////////////////////////////////////

		// Configurations
		JPanel mainPanelAddParking = new JPanel();
		mainPanelAddParking.setLayout(new BorderLayout(0, 0));


		// Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelAddParking.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelAddParking.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAddParking.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAddParking.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelAddParking.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[854px]", "[37px][60px][60px][55px][61px][150px][65px]"));

		JLabel addParkingLabelPanel = new JLabel("Add New Parking");
		addParkingLabelPanel.setPreferredSize(new Dimension(100, 50));
		addParkingLabelPanel.setHorizontalAlignment(SwingConstants.CENTER);
		addParkingLabelPanel.setForeground(java.awt.Color.BLACK);
		addParkingLabelPanel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		centerPanel.add(addParkingLabelPanel, "cell 0 0,alignx center,growy");

		JPanel AddCancelPanel = new JPanel();
		AddCancelPanel.setBackground(new Color(250,250,250));
		centerPanel.add(AddCancelPanel, "cell 0 6,grow");
		AddCancelPanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));

		JPanel pNamePanel = new JPanel();
		pNamePanel.setBackground((java.awt.Color) null);
		centerPanel.add(pNamePanel, "cell 0 1,growx,aligny top");
		pNamePanel.setLayout(new MigLayout("", "[109px][269.00px]", "[28px]"));

		JLabel nameLabel = new JLabel("Parking Name: ");
		pNamePanel.add(nameLabel, "cell 0 0,alignx left,aligny center");
		nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		final JTextField textFieldName = new JTextField();
		textFieldName.setPreferredSize(new Dimension(200, 100));
		pNamePanel.add(textFieldName, "cell 1 0,grow");
		textFieldName.setColumns(10);

		JPanel capacityPanel = new JPanel();
		capacityPanel.setBackground((java.awt.Color) null);
		centerPanel.add(capacityPanel, "cell 0 2,growx,aligny top");
		capacityPanel.setLayout(new MigLayout("", "[66.00px][]", "[28px]"));

		JLabel capacityLabel = new JLabel("Capacity: ");
		capacityPanel.add(capacityLabel, "cell 0 0,alignx left,aligny center");
		capacityLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		final JTextField textFieldCapacity = new JTextField();
		capacityPanel.add( textFieldCapacity, "cell 1 0,alignx right,aligny top");
		textFieldCapacity.setColumns(10);

		JPanel daysPanel = new JPanel();
		daysPanel.setBackground((java.awt.Color) null);
		centerPanel.add(daysPanel, "cell 0 3,growx,aligny top");
		daysPanel.setLayout(new MigLayout("", "[183px][55px][58px][56px][59px][51px][55px]", "[23px]"));

		JLabel daysLabel = new JLabel("Operation Days: ");
		daysPanel.add(daysLabel, "cell 0 0,growx,aligny top");
		daysLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		final JRadioButton monRadioBtn = new JRadioButton("Mon");
		daysPanel.add(monRadioBtn, "cell 0 0,alignx right,aligny top");

		final JRadioButton tueRadioBtn = new JRadioButton("Tue");
		daysPanel.add(tueRadioBtn, "cell 1 0,alignx left,aligny top");

		final JRadioButton wenRadioBtn = new JRadioButton("Wen");
		daysPanel.add(wenRadioBtn, "cell 2 0,alignx left,aligny top");

		final JRadioButton thuRadioBtn = new JRadioButton("Thu");
		daysPanel.add(thuRadioBtn, "cell 3 0,alignx left,aligny top");

		final JRadioButton friRadioBtn = new JRadioButton("Fri");
		daysPanel.add(friRadioBtn, "cell 4 0,growx,aligny top");

		final JRadioButton satRadioBtn = new JRadioButton("Sat");
		daysPanel.add(satRadioBtn, "cell 5 0,alignx left,aligny top");

		final JRadioButton sunRadioBtn = new JRadioButton("Sun");
		daysPanel.add(sunRadioBtn, "cell 6 0,alignx left,aligny top");

		JPanel hoursPanel = new JPanel();
		hoursPanel.setBackground((java.awt.Color) null);
		centerPanel.add(hoursPanel, "cell 0 4,grow");
		hoursPanel.setLayout(new MigLayout("", "[][][35][70.00][35][70]", "[]"));

		JLabel hoursLabel = new JLabel("Operation Hours: ");
		hoursPanel.add(hoursLabel, "cell 0 0");
		hoursLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel startLabel = new JLabel("Start: ");
		startLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		hoursPanel.add(startLabel, "cell 2 0,alignx trailing");

		final JTextField textFieldStart = new JTextField();
		hoursPanel.add(textFieldStart, "cell 3 0");
		textFieldStart.setColumns(10);

		JLabel endLabel = new JLabel("End: ");
		endLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		hoursPanel.add(endLabel, "flowx,cell 4 0");

		final JTextField textFieldEnd = new JTextField();
		textFieldEnd.setColumns(10);
		hoursPanel.add(textFieldEnd, "cell 5 0");

		JPanel sadsAndATypesPanel = new JPanel();
		sadsAndATypesPanel.setBackground((java.awt.Color) null);
		centerPanel.add(sadsAndATypesPanel, "cell 0 5,grow");
		sadsAndATypesPanel.setLayout(new MigLayout("", "[161px][161px][159.00][161px][161px]", "[21px][83px][29px]"));

		JScrollPane scrollPaneSADs = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneSADs, "cell 0 1,grow");


		availableSADModelList= new DefaultListModel();
		availableAtzTypesModelList= new DefaultListModel();



		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Query for showing list of Available SADs and Authorization Types in Add New Parking Page
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		try {
			dbman = new DBManager();
			availableSAD = dbman.getFromDB("select * from sisca_sad where sisca_sad_active='false'");
			availableSAD = dbman.getAvailableSAD(availableSAD);
			availableAtzType = dbman.getFromDB("select * from sisca_authorization");
			availableAtzType = dbman.getAvailableAuthorizationTypes(availableAtzType);



			for(int i=0; i<availableAtzType.size(); i++){
				availableAtzTypesModelList.addElement(((String) availableAtzType.get(i)).toUpperCase());
			}

			for(int i=0; i<availableSAD.size(); i++){
				availableSADModelList.addElement(((String) availableSAD.get(i)).toUpperCase());
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

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


		final JList SADList = new JList(availableSADModelList);
		scrollPaneSADs.setViewportView(SADList);

		JScrollPane scrollPaneCurrentSADs = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneCurrentSADs, "cell 1 1,grow");


		final JList currentSADList = new JList();
		scrollPaneCurrentSADs.setViewportView(currentSADList);

		JScrollPane scrollPaneATypes = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneATypes, "cell 3 1,grow");


		final JList ATypesList = new JList(availableAtzTypesModelList);
		scrollPaneATypes.setViewportView(ATypesList);

		JScrollPane scrollPaneCurrentATypes = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneCurrentATypes, "cell 4 1,grow");

		final JList currentATypesList = new JList();
		scrollPaneCurrentATypes.setViewportView(currentATypesList);

		JLabel sads = new JLabel("SAD's:");
		sads.setHorizontalAlignment(SwingConstants.CENTER);
		sadsAndATypesPanel.add(sads, "cell 0 0 2 1,growx,aligny top");
		sads.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel authorizations = new JLabel("Authorization Type(s):");
		authorizations.setHorizontalAlignment(SwingConstants.CENTER);
		authorizations.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		sadsAndATypesPanel.add(authorizations, "cell 3 0 2 1,growx,aligny bottom");

		// Default List Initializations
		chosenSADModelList= new DefaultListModel();
		chosenAtzTypesModelList = new DefaultListModel();

		// SAD And ATypes JList Button
		JButton addSADBtn = new JButton("Add");
		addSADBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chosenSADModelList.addElement(SADList.getSelectedValue().toString());
				availableSADModelList.removeElement(SADList.getSelectedValue().toString());

				SADList.setModel(availableSADModelList);
				currentSADList.setModel(chosenSADModelList);
			}
		});
		sadsAndATypesPanel.add(addSADBtn, "cell 0 2,growx,aligny top");

		JButton removeSADBtn = new JButton("Remove");
		removeSADBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				availableSADModelList.addElement(currentSADList.getSelectedValue().toString());
				chosenSADModelList.removeElement(currentSADList.getSelectedValue().toString());

				SADList.setModel(availableSADModelList);
				currentSADList.setModel(chosenSADModelList);
			}
		});
		sadsAndATypesPanel.add(removeSADBtn, "cell 1 2,growx,aligny top");

		JButton addATypeBtn = new JButton("Add");
		addATypeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				chosenAtzTypesModelList.addElement(ATypesList.getSelectedValue().toString());
				availableAtzTypesModelList.removeElement(ATypesList.getSelectedValue().toString());

				ATypesList.setModel(availableAtzTypesModelList);
				currentATypesList.setModel(chosenAtzTypesModelList);
			}
		});
		sadsAndATypesPanel.add(addATypeBtn, "cell 3 2,growx,aligny top");

		JButton removeATypeBtn = new JButton("Remove");
		removeATypeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				availableAtzTypesModelList.addElement(currentATypesList.getSelectedValue().toString());
				chosenAtzTypesModelList.removeElement(currentATypesList.getSelectedValue().toString());

				ATypesList.setModel(availableAtzTypesModelList);
				currentATypesList.setModel(chosenAtzTypesModelList); 
			}
		});
		sadsAndATypesPanel.add(removeATypeBtn, "cell 4 2,growx,aligny top");



		////////////////////////
		// Query for Add a New Parking to the DB
		/////////////////////////////////

		JButton addParkingBtn = new JButton("Add Parking");
		addParkingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Fields from text fiedls
				String pName= "'"+textFieldName.getText()+"'";
				String pCapacity= "'"+textFieldCapacity.getText()+"'";
				String pSHours= "'"+textFieldStart.getText()+"'";
				String pEHours= "'"+textFieldEnd.getText()+"'";


				// Insert Parking Name, Capacity, Operation Start Hour and Operation End Hour to the DB
				String stm1 = "INSERT INTO sisca_parking (sisca_parking_name,sisca_parking_capacity,"
						+ "sisca_parking_starthour, sisca_parking_endhour) "
						+ "VALUES("+ pName+","+ pCapacity+","+ pSHours+","+ pEHours+")";

				try {
					dbman.insertDB(stm1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Insert Parking SADList to the DB

				// Fields from Jlist
				ListModel curretnSADListModel=currentSADList.getModel();
				String[] currentSADStringArray= new String[curretnSADListModel.getSize()];

				for (int i=0; i<curretnSADListModel.getSize();i++){
					String s= (String) curretnSADListModel.getElementAt(i);
					String[] arrayTest= s.split(" >>");
					currentSADStringArray[i]="'"+ arrayTest[0]+"'";

				}

				for(String sName: currentSADStringArray){
					//System.out.println("SAD Name:\n"+sName);
					String stm2 = "INSERT INTO sisca_sad_parking_list (sisca_sad_name,sisca_parking_name)"
							+ "VALUES("+ sName+","+ pName+")";
					String stm3= "update sisca_sad SET sisca_sad_active='true' where sisca_sad_name="+sName;


					try {
						dbman.insertDB(stm2);
						dbman.updatetDB(stm3);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				// Insert Parking AuthorizationTypesList to the DB

				// Fields from Jlist
				ListModel curretnAtzTypesListModel=currentATypesList.getModel();
				String[] currentAtzTypestringArray= new String[curretnAtzTypesListModel.getSize()];

				for (int i=0; i<curretnAtzTypesListModel.getSize();i++){
					currentAtzTypestringArray[i]="'"+ curretnAtzTypesListModel.getElementAt(i)+"'";
				}

				for(String aTName: currentAtzTypestringArray){
					String stm2 = "INSERT INTO sisca_authorization_parking_list (sisca_parking_name,sisca_authorization_name)"
							+ "VALUES("+ pName+","+ aTName+")";
					try {
						dbman.insertDB(stm2);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}

				// Insert Parking Operation Days to the DB

				// Fields from Jlist
				String[] operationDays= new String[7];
				int j=0;
				if(monRadioBtn.isSelected()){
					operationDays[j]="'Monday'";
					j++;
				}
				if(tueRadioBtn.isSelected()){
					operationDays[j]="'Tuesday'";
					j++;
				}
				if(wenRadioBtn.isSelected()){
					operationDays[j]="'Wenesday'";
					j++;
				}
				if(thuRadioBtn.isSelected()){
					operationDays[j]="'Thursday'";
					j++;
				}
				if(friRadioBtn.isSelected()){
					operationDays[j]="'Friday'";
					j++;
				}
				if(satRadioBtn.isSelected()){
					operationDays[j]="'Saturday'";
					j++;
				}
				if(sunRadioBtn.isSelected()){
					operationDays[j]="'Sunday'";
					j++;
				}
				int j2=0;
				for(String oDay: operationDays){
					String stm2 = "INSERT INTO sisca_parking_operation_days_list (sisca_parking_name,sisca_operations_day)"
							+ "VALUES("+ pName+","+ oDay+")";
					if(j2<j){
						try {
							dbman.insertDB(stm2);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
						j2++;
					}

				}
				// Clear  Chosen List
				chosenSADModelList.clear();
				chosenAtzTypesModelList.clear();

				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(addParkingBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Cancel me hace un BACK en el history
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(cancelBtn, "cell 26 0");


		////////////////////////////////////////////////////////
		//           Window Panel
		/////////////////////////////////////////////////////////

		// Configurations
		JPanel windowPanelAddParking= new JPanel();
		windowPanelAddParking.setLayout(new BorderLayout(0, 0));
		windowPanelAddParking.add(menuPanelAddParking, BorderLayout.NORTH);
		windowPanelAddParking.add(leftPanelAddParking, BorderLayout.WEST);
		windowPanelAddParking.add(mainPanelAddParking, BorderLayout.CENTER);

		return windowPanelAddParking;
	}


	//////////////////////////////////////////////////////////////////
	//   Edit Parking View								         	//
	//////////////////////////////////////////////////////////////////

	private JPanel editParkingView(String parkingName, int parkingID, int parkingCapacity, String parkingSHour, String parkingEHour, 
			String[] sads, String[] authorizationTypes, String[] operationDays){

		// Current Parking Information
		int parkingIDEdit= parkingID;

		String parkingNameEdit= parkingName;
		String parkingStartHour= parkingSHour;
		String parkingEndHour= parkingEHour;

		String[] parkingSADs= sads;
		String[] parkingAuthorizationTypes= authorizationTypes;
		String[] parkingOperationDays= operationDays;

		/////////////////////////////////////////////////////////
		//           Menu Panel
		/////////////////////////////////////////////////////////


		// Configurations 
		JPanel menuPanelEditParking = new JPanel();
		menuPanelEditParking.setBackground(new Color(255,239,80));
		menuPanelEditParking.setPreferredSize(new Dimension(10, 30));
		menuPanelEditParking.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(375, 10));
		menuPanelEditParking.add(menuOptionsPanel, BorderLayout.WEST);
		menuOptionsPanel.setLayout(null);

		JLabel homeLabel = new JLabel(" Home");
		homeLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		homeLabel.setBounds(0, -11, 68, 53);
		menuOptionsPanel.add(homeLabel);
		homeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(HKJ_SisCA_MainPage.windowPanel); // Wrong Way! =S
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		homeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homeLabel.setForeground((java.awt.Color) null);
		homeLabel.setFont(new Font("Dialog", Font.BOLD, 14));

		JLabel parkingManagerLabel = new JLabel("Parking");
		parkingManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		parkingManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		parkingManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		parkingManagerLabel.setForeground((java.awt.Color) null);
		parkingManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		parkingManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		parkingManagerLabel.setBounds(66, -11, 106, 53);
		menuOptionsPanel.add(parkingManagerLabel);

		JLabel editParkingLabel = new JLabel("Edit Parking");
		editParkingLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//HKJ_SisCA_MainPage.frame.setContentPane(editParkingView());
				//HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		editParkingLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		editParkingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editParkingLabel.setForeground((java.awt.Color) null);
		editParkingLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		editParkingLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		editParkingLabel.setBounds(170, -11, 129, 53);
		menuOptionsPanel.add(editParkingLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelEditParking.add(logOutPanel, BorderLayout.EAST);
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
		menuPanelEditParking.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel(HKJ_SisCA_MainPage.getActiveUsername());
		userNamePanel.add(userNameLabel, BorderLayout.EAST);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground((java.awt.Color) null);
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));

		/////////////////////////////////////////////////////////
		//           Left Panel
		/////////////////////////////////////////////////////////

		// Configurations 
		JPanel leftPanelEditParking = new JPanel();
		leftPanelEditParking.setBackground(new Color(245,245,245));
		leftPanelEditParking.setPreferredSize(new Dimension(390, 10));
		leftPanelEditParking.setLayout(new BorderLayout(0, 0));

		JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanelEditParking.add(scrollPane);

		JLayeredPane liveSystemPanel2 = new JLayeredPane();
		scrollPane.setViewportView(liveSystemPanel);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel2.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));

		// Parking Name Labels & MouseCliked Event 

		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px]"));


		// LeftPanel Labels
		JLabel LSystemLabel = new JLabel("PARKING LIST");
		liveSystemPanel.add(LSystemLabel, "cell 0 0,alignx center,aligny top");
		LSystemLabel.setPreferredSize(new Dimension(100, 50));
		LSystemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LSystemLabel.setForeground(java.awt.Color.BLACK);
		LSystemLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		////////////////////////////////////////////////////////////////////////////////////////////////
		//Query for get Parking Names for the Labels
		////////////////////////////////////////////////////////////////////////////////////
		ArrayList pNameLabelsArray = new ArrayList() ;
		try {
			dbman= new DBManager();

			pNameLabelsArray=dbman.getFromDB("Select * from sisca_parking ORDER BY sisca_parking_name");
			//System.out.println("Before Test:"+pNameLabelsArray);
			pNameLabelsArray= dbman.getAvailableParkingNamesFromDB(pNameLabelsArray);
			//System.out.println("After Test:"+pNameLabelsArray.toString());


		} catch (ClassNotFoundException e2) {
			//TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			//TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ParseException e1) {
			//TODO Auto-generated catch block
			e1.printStackTrace();
		}



		JLabel pName1 = new JLabel((String) pNameLabelsArray.get(0));

		pName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName1, "cell 0 3,alignx left,aligny top");

		JLabel pName2 = new JLabel((String) pNameLabelsArray.get(1));
		pName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName2, "cell 0 5,alignx left,aligny top");

		JLabel pName3 = new JLabel((String) pNameLabelsArray.get(2));
		pName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName3, "cell 0 7,alignx left,aligny top");

		JLabel pName4 = new JLabel((String) pNameLabelsArray.get(3));
		pName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName4, "cell 0 9,alignx left,aligny top");

		JLabel pName5 = new JLabel((String) pNameLabelsArray.get(4));
		pName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName5, "cell 0 11,alignx left,aligny top");

		JLabel pName6 = new JLabel((String) pNameLabelsArray.get(5));
		pName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName6, "cell 0 13,alignx left,aligny top");

		JLabel pName7 = new JLabel((String) pNameLabelsArray.get(6));
		pName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName7, "cell 0 15,alignx left,aligny top");

		JLabel pName8 = new JLabel((String) pNameLabelsArray.get(7));
		pName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName8, "cell 0 17,alignx left,aligny top");

		JLabel pName9 = new JLabel((String) pNameLabelsArray.get(8));
		pName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName9, "cell 0 19,alignx left,aligny top");

		JLabel pName10 = new JLabel((String) pNameLabelsArray.get(9));
		pName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName10, "cell 0 21,alignx left,aligny top");

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
		panel_100.add(searchTextField);
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		searchTextField.setColumns(10);

		JPanel viewAndAddBynPanel = new JPanel();
		viewAndAddBynPanel.setBackground(new Color(245,245,245));
		liveSystemPanel.add(viewAndAddBynPanel, "cell 0 23,alignx left,aligny top");
		viewAndAddBynPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton viewAllButton = new JButton("View All");
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(parkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New Parking");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addParkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(addNewButton);




		/////////////////////////////////////////////////////////
		//           Main Panel
		/////////////////////////////////////////////////////////

		// Configurations
		JPanel mainPanelEditParking = new JPanel();
		mainPanelEditParking.setLayout(new BorderLayout(0, 0));


		// Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelEditParking.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelEditParking.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelEditParking.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelEditParking.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelEditParking.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[854px]", "[37px][60px][60px][55px][61px][150px][65px]"));

		JLabel editParkingLabelPanel = new JLabel("Edit Parking");
		editParkingLabelPanel.setPreferredSize(new Dimension(100, 50));
		editParkingLabelPanel.setHorizontalAlignment(SwingConstants.CENTER);
		editParkingLabelPanel.setForeground(java.awt.Color.BLACK);
		editParkingLabelPanel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		centerPanel.add(editParkingLabelPanel, "cell 0 0,alignx center,growy");

		JPanel AddCancelPanel = new JPanel();
		AddCancelPanel.setBackground(new Color(250,250,250));
		centerPanel.add(AddCancelPanel, "cell 0 6,grow");
		AddCancelPanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));

		///////////////////////////////////////////////////////////////////////////
		// Query for Edit a Parking to the DB
		///////////////////////////////////////////////////////////////////////////
		JButton editParkingBtn = new JButton("Edit Parking");
		editParkingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO edit
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(editParkingBtn, "cell 0 0");
		///////////////////////////////////////////////////////////////////////////
		// 
		///////////////////////////////////////////////////////////////////////////


		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Cancel me hace un BACK en el history
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(cancelBtn, "cell 26 0");

		JPanel pNamePanel = new JPanel();
		pNamePanel.setBackground((java.awt.Color) null);
		centerPanel.add(pNamePanel, "cell 0 1,growx,aligny top");
		pNamePanel.setLayout(new MigLayout("", "[109px][269.00px]", "[28px]"));

		// Parking Name
		JLabel nameLabel = new JLabel("Parking Name: ");
		pNamePanel.add(nameLabel, "cell 0 0,alignx left,aligny center");
		nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JTextField textFieldName = new JTextField();
		textFieldName.setPreferredSize(new Dimension(200, 100));
		pNamePanel.add(textFieldName, "cell 1 0,grow");
		textFieldName.setColumns(10);

		// Parking Capacity
		JPanel capacityPanel = new JPanel();
		capacityPanel.setBackground((java.awt.Color) null);
		centerPanel.add(capacityPanel, "cell 0 2,growx,aligny top");
		capacityPanel.setLayout(new MigLayout("", "[66.00px][]", "[28px]"));

		JLabel capacityLabel = new JLabel("Capacity: ");
		capacityPanel.add(capacityLabel, "cell 0 0,alignx left,aligny center");
		capacityLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		// Parking Operation Days
		JTextField textField = new JTextField();
		capacityPanel.add(textField, "cell 1 0,alignx right,aligny top");
		textField.setColumns(10);

		JPanel daysPanel = new JPanel();
		daysPanel.setBackground((java.awt.Color) null);
		centerPanel.add(daysPanel, "cell 0 3,growx,aligny top");
		daysPanel.setLayout(new MigLayout("", "[183px][55px][58px][56px][59px][51px][55px]", "[23px]"));

		JLabel daysLabel = new JLabel("Operation Days: ");
		daysPanel.add(daysLabel, "cell 0 0,growx,aligny top");
		daysLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JRadioButton monRadioBtn = new JRadioButton("Mon");
		daysPanel.add(monRadioBtn, "cell 0 0,alignx right,aligny top");

		JRadioButton tueRadioBtn = new JRadioButton("Tue");
		daysPanel.add(tueRadioBtn, "cell 1 0,alignx left,aligny top");

		JRadioButton wenRadioBtn = new JRadioButton("Wen");
		daysPanel.add(wenRadioBtn, "cell 2 0,alignx left,aligny top");

		JRadioButton thuRadioBtn = new JRadioButton("Thu");
		daysPanel.add(thuRadioBtn, "cell 3 0,alignx left,aligny top");

		JRadioButton friRadioBtn = new JRadioButton("Fri");
		daysPanel.add(friRadioBtn, "cell 4 0,growx,aligny top");

		JRadioButton satRadioBtn = new JRadioButton("Sat");
		daysPanel.add(satRadioBtn, "cell 5 0,alignx left,aligny top");

		JRadioButton sunRadioBtn = new JRadioButton("Sun");
		daysPanel.add(sunRadioBtn, "cell 6 0,alignx left,aligny top");

		// Parking Operation Hours
		JPanel hoursPanel = new JPanel();
		hoursPanel.setBackground((java.awt.Color) null);
		centerPanel.add(hoursPanel, "cell 0 4,grow");
		hoursPanel.setLayout(new MigLayout("", "[][][35][70.00][35][70]", "[]"));

		JLabel hoursLabel = new JLabel("Operation Hours: ");
		hoursPanel.add(hoursLabel, "cell 0 0");
		hoursLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel startLabel = new JLabel("Start: ");
		startLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		hoursPanel.add(startLabel, "cell 2 0,alignx trailing");

		JTextField textFieldStart = new JTextField();
		hoursPanel.add(textFieldStart, "cell 3 0");
		textFieldStart.setColumns(10);

		JLabel endLabel = new JLabel("End: ");
		endLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		hoursPanel.add(endLabel, "flowx,cell 4 0");

		JTextField textFieldEnd = new JTextField();
		textFieldEnd.setColumns(10);
		hoursPanel.add(textFieldEnd, "cell 5 0");

		// Parking SADs & Authorization Types
		JPanel sadsAndATypesPanel = new JPanel();
		sadsAndATypesPanel.setBackground((java.awt.Color) null);
		centerPanel.add(sadsAndATypesPanel, "cell 0 5,grow");
		sadsAndATypesPanel.setLayout(new MigLayout("", "[161px][161px][159.00][161px][161px]", "[21px][83px][29px]"));

		JScrollPane scrollPaneSADs = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneSADs, "cell 0 1,grow");

		JList SADList = new JList();
		scrollPaneSADs.setViewportView(SADList);

		JScrollPane scrollPaneCurrentSADs = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneCurrentSADs, "cell 1 1,grow");

		JList currentSADList = new JList();
		scrollPaneCurrentSADs.setViewportView(currentSADList);

		JScrollPane scrollPaneATypes = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneATypes, "cell 3 1,grow");

		JList ATypesList = new JList();
		scrollPaneATypes.setViewportView(ATypesList);

		JScrollPane scrollPaneCurrentATypes = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneCurrentATypes, "cell 4 1,grow");

		JList currentATypesList = new JList();
		scrollPaneCurrentATypes.setViewportView(currentATypesList);

		JLabel sads1 = new JLabel("SAD's:");
		sads1.setHorizontalAlignment(SwingConstants.CENTER);
		sadsAndATypesPanel.add(sads1, "cell 0 0 2 1,growx,aligny top");
		sads1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel authorizations = new JLabel("Authorization Type(s):");
		authorizations.setHorizontalAlignment(SwingConstants.CENTER);
		authorizations.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		sadsAndATypesPanel.add(authorizations, "cell 3 0 2 1,growx,aligny bottom");

		// Manage SAD and Authorization Lists
		JButton addSADBtn = new JButton("Add");
		addSADBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 


			}
		});
		sadsAndATypesPanel.add(addSADBtn, "cell 0 2,growx,aligny top");

		JButton removeSADBtn = new JButton("Remove");
		removeSADBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 


			}
		});
		sadsAndATypesPanel.add(removeSADBtn, "cell 1 2,growx,aligny top");

		JButton addATypeBtn = new JButton("Add");
		addATypeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 


			}
		});
		sadsAndATypesPanel.add(addATypeBtn, "cell 3 2,growx,aligny top");

		JButton removeATypeBtn = new JButton("Remove");
		removeATypeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 


			}
		});
		sadsAndATypesPanel.add(removeATypeBtn, "cell 4 2,growx,aligny top");


		////////////////////////////////////////////////////////
		//           Window Panel
		/////////////////////////////////////////////////////////

		// Configurations
		JPanel windowPanelEditParking= new JPanel();
		windowPanelEditParking.setLayout(new BorderLayout(0, 0));
		windowPanelEditParking.add(menuPanelEditParking, BorderLayout.NORTH);
		windowPanelEditParking.add(leftPanelEditParking, BorderLayout.WEST);
		windowPanelEditParking.add(mainPanelEditParking, BorderLayout.CENTER);

		return windowPanelEditParking;
	}


}
