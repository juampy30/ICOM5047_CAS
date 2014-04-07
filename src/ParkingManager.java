import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
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

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
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

	private DBManager dbman;
	private String mouseSelectParkingViewList;
	//private ArrayList<Object> infoParkingSADsList; //Contiene los SADs q se desplegan durante el parking Info view
	//private Component infoParkingSadsList;

	public ParkingManager(){

	}

	//////////////////////////////////////////////////////////////////
	//   Parking View								            	//
	//////////////////////////////////////////////////////////////////

	public JPanel parkingView(){
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

		JLabel userNameLabel = new JLabel("User Name   ");
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

		final DefaultListModel registerParkingListView = new DefaultListModel();

		/**
		 * dbman : connect to DBManager to run the required querys 
		 */
		try {
			dbman = new DBManager();
			ArrayList<Object> registerParkingArrayListQuery = dbman.getFromDB("select * from sisca_parking order by sisca_parking_name");
			/**
			 * userActiveListView : 
			 */
			String[] keyValue;
			String registerParkingName = null;
			//[{1:A},{2:B},{3:C}]
			for(int i=0; i< registerParkingArrayListQuery.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) registerParkingArrayListQuery.get(i)).size(); k++){
					//result = 1:A 
					Object result = ((List<Object>) registerParkingArrayListQuery.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_parking_name")){
						registerParkingName = (String) keyValue[1];
					}
				}
				registerParkingListView.addElement(registerParkingName.toUpperCase());
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

		/**
		 * parkingViewList : Display the list of all account register in the system
		 */
		final JList parkingViewList = new JList(registerParkingListView);

		////////////////
		parkingViewList.setSelectionForeground(UIManager.getColor("Button.darkShadow"));
		parkingViewList.setSelectionBackground(UIManager.getColor("Button.background"));
		parkingViewList.addMouseListener(new MouseAdapter() {


			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					mouseSelectParkingViewList = (String) parkingViewList.getSelectedValue();
					String[] keyValue = mouseSelectParkingViewList.split(" >>");
					mouseSelectParkingViewList = keyValue[0];
					HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(mouseSelectParkingViewList)); 
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

	public JPanel parkingInformationView(final String SelectedParkingName) {
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
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
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

		JLabel userNameLabel = new JLabel("User Name   ");
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



//		JLabel pName1 = new JLabel((String) pNameLabelsArray.get(0));
//
//		pName1.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName1, "cell 0 3,alignx left,aligny top");
//
//		JLabel pName2 = new JLabel((String) pNameLabelsArray.get(1));
//		pName2.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName2, "cell 0 5,alignx left,aligny top");
//
//		JLabel pName3 = new JLabel((String) pNameLabelsArray.get(2));
//		pName3.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName3, "cell 0 7,alignx left,aligny top");
//
//		JLabel pName4 = new JLabel((String) pNameLabelsArray.get(3));
//		pName4.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName4, "cell 0 9,alignx left,aligny top");
//
//		JLabel pName5 = new JLabel((String) pNameLabelsArray.get(4));
//		pName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		pName5.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		liveSystemPanel.add(pName5, "cell 0 11,alignx left,aligny top");
//
//		JLabel pName6 = new JLabel((String) pNameLabelsArray.get(5));
//		pName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		pName6.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		liveSystemPanel.add(pName6, "cell 0 13,alignx left,aligny top");
//
//		JLabel pName7 = new JLabel((String) pNameLabelsArray.get(6));
//		pName7.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName7, "cell 0 15,alignx left,aligny top");
//
//		JLabel pName8 = new JLabel((String) pNameLabelsArray.get(7));
//		pName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		pName8.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		liveSystemPanel.add(pName8, "cell 0 17,alignx left,aligny top");
//
//		JLabel pName9 = new JLabel((String) pNameLabelsArray.get(8));
//		pName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		pName9.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		liveSystemPanel.add(pName9, "cell 0 19,alignx left,aligny top");
//
//		JLabel pName10 = new JLabel((String) pNameLabelsArray.get(9));
//		pName10.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName10, "cell 0 21,alignx left,aligny top");

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
		/*
		if(!fromParkingList){
			// Parking selected from the parking list view (Format: ParkingName >> Hours)
			// Split this String to get the parking name (String[0]= ParkingName String[1]= Hours) (Format: ParkingName)
			parkingSelectedFromParkingViewList = mouseSelectParkingViewList.split(" >>");
			strParkingName = parkingSelectedFromParkingViewList[0];
			fromParkingList=true;
		}
		else if(!fromAddNew){
			strParkingName = pName;
			fromAddNew = true;
		}
		else{
			strParkingName = parkingNameEdit;
		}
		//Get from the parking name from the result of the split (parkingSelectedFromParkingViewList[0] = parkingName)
		//final String strParkingName = parkingSelectedFromParkingViewList[0];

		//Default List Models
		parkingInfoAuthorizationsListView = new DefaultListModel();
		parkingInfoOperDayListView = new DefaultListModel();
		parkingInfoSADListView = new DefaultListModel();

		try {
			//Select the parking iformation from sisca_parking table
			fillParkingInformation = dbman.getFromDB("select * from sisca_parking where sisca_parking_name ilike '%"+ strParkingName +"'");
			//Select the parking SADs from sisca_sad_parking_list
			fillParkingInformationSads = dbman.getFromDB("select * from sisca_sad_parking_list where sisca_parking_name ilike '%" + strParkingName + "'");
			//Select the operation days for the selected parking from sisca_parking_operations_days_list
			fillParkingInformationOperationsDays = dbman.getFromDB("select * from sisca_parking_operation_days_list where sisca_parking_name ilike '%" + strParkingName + "'");
			//Select the authorization types for the selected parking from sisca_authorization_parking_list
			fillParkingInformationAuthorizations = dbman.getFromDB("select * from sisca_authorization_parking_list where sisca_parking_name ilike '%" + strParkingName + "'");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//PROCESS THE fillParkingInformation to Get the corresponding Values
		String[] keyValue;
		for(int i=0; i<fillParkingInformation.size(); i++){
			//obtener el elemento i del elemento 1 (el array del array) 
			for(int k=0 ; k<(((java.util.List<Object>) fillParkingInformation.get(i)).size()); k++){
				//result = 1:A
				Object result = ((java.util.List<Object>) fillParkingInformation.get(i)).get(k);
				//keyValue -> {1,A}
				keyValue = result.toString().split("/");
				if(keyValue[0].equals("sisca_parking_id")){
					parkingInfoID = Integer.valueOf((String)keyValue[1]); //EL ID DE JUAMPY
				}
				if(keyValue[0].equals("sisca_parking_name")){
					parkingInfoName = (String) keyValue[1];
				}
				if(keyValue[0].equals("sisca_parking_capacity")){
					parkingInfoCapacity = (String) keyValue[1];
				}
				if(keyValue[0].equals("sisca_parking_starthour")){
					parkingInfoStartHour = (String) keyValue[1];
				}
				if(keyValue[0].equals("sisca_parking_endhour")){
					parkingInfoEndHour = (String) keyValue[1];
				}

			}

		}
		for(int i=0; i<fillParkingInformationSads.size(); i++){
			//obtener el elemento i del elemento 1 (el array del array) 
			for(int k=0 ; k<(((java.util.List<Object>) fillParkingInformationSads.get(i)).size()); k++){
				//result = 1:A
				Object result = ((java.util.List<Object>) fillParkingInformationSads.get(i)).get(k);
				//keyValue -> {1,A}
				keyValue = result.toString().split("/");
				if(keyValue[0].equals("sisca_sad_name")){
					parkingInfoSadName = (String) keyValue[1];
				}					
			}
			parkingInfoSADListView.addElement(parkingInfoSadName);
			//System.out.println("Parking Sad List: " + parkingInfoSadName);
		}
		for(int i=0; i<fillParkingInformationOperationsDays.size(); i++){
			//obtener el elemento i del elemento 1 (el array del array) 
			for(int k=0 ; k<(((java.util.List<Object>) fillParkingInformationOperationsDays.get(i)).size()); k++){
				//result = 1:A
				Object result = ((java.util.List<Object>) fillParkingInformationOperationsDays.get(i)).get(k);
				//keyValue -> {1,A}
				keyValue = result.toString().split("/");
				if(keyValue[0].equals("sisca_operations_day")){
					parkingInfoOperationsDay = (String) keyValue[1];
				}					
			}
			parkingInfoOperDayListView.addElement(parkingInfoOperationsDay);
			//System.out.println("Parking Sad List: " + parkingInfoSadName);
		}
		for(int i=0; i<fillParkingInformationAuthorizations.size(); i++){
			//obtener el elemento i del elemento 1 (el array del array) 
			for(int k=0 ; k<(((java.util.List<Object>) fillParkingInformationAuthorizations.get(i)).size()); k++){
				//result = 1:A
				Object result = ((java.util.List<Object>) fillParkingInformationAuthorizations.get(i)).get(k);
				//keyValue -> {1,A}
				keyValue = result.toString().split("/");
				if(keyValue[0].equals("sisca_authorization_name")){
					parkingInfoAuthorizations= (String) keyValue[1];
				}					
			}
			parkingInfoAuthorizationsListView.addElement(parkingInfoAuthorizations);

		}
		 */

		final DefaultListModel selectRegisterParking = new DefaultListModel();		

		ArrayList<Object> infoParkingSADsList = new ArrayList<Object>(); //Contiene los SADs q se desplegan durante el parking Info view
		ArrayList<Object> infoOperationsDaysList = new ArrayList<Object>(); //Contiene los Dias que este Parking trabaja
		ArrayList<Object> infoAuthorizationTypeList = new ArrayList<Object>(); //Contiene los tipos de autorizacion para este estacionamiento
		/**
		 * Get all the parking information from the Database
		 */
		String selectParkingName = null, selectParkingEndhour=null, selectParkingStarhour=null;
		int selectParkingCapacity=99999;
		String[] keyValue;
		try {
			dbman = new DBManager();
			/**
			 * Get all parking information form the DB (Parking Name, Capacity and Operating Hours)
			 */
			ArrayList<Object> registerParkingArrayListQuery = dbman.getFromDB("select * from sisca_parking where sisca_parking_name ~*'"+SelectedParkingName+"'");
			for(int i=0; i< registerParkingArrayListQuery.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) registerParkingArrayListQuery.get(i)).size(); k++){
					//result = 1:A 
					Object result = ((List<Object>) registerParkingArrayListQuery.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_parking_name")){
						selectParkingName = (String) keyValue[1];
					}
					if(keyValue[0].equals("sisca_parking_capacity")){
						selectParkingCapacity = Integer.valueOf((String) keyValue[1]);
					}
					if(keyValue[0].equals("sisca_parking_starthour")){
						selectParkingStarhour = (String) keyValue[1];
					}
					if(keyValue[0].equals("sisca_parking_endhour")){
						selectParkingEndhour = (String) keyValue[1];
					}
				}
				selectRegisterParking.addElement(selectParkingName.toUpperCase());
			}
			/**
			 * Get all SADs for the selected parking
			 */
			String infoParkingSADs=null;
			ArrayList<Object> parkingSadListQuery = dbman.getFromDB("select * from sisca_sad_parking_list where sisca_parking_name ~*'"+SelectedParkingName+"'");
			for(int i=0; i< parkingSadListQuery.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) parkingSadListQuery.get(i)).size(); k++){
					//result = 1:A 
					Object result = ((List<Object>) parkingSadListQuery.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_sad_name")){
						infoParkingSADs = (String) keyValue[1];
					}
				}
				infoParkingSADsList.add(infoParkingSADs);
			}
			/**
			 * Get all operations days for the selected parking 
			 */
			String infoOperationDays=null;
			ArrayList<Object> parkingOperatingDays = dbman.getFromDB("select * from sisca_parking_operation_days_list where sisca_parking_name ~*'"+SelectedParkingName+"'");
			for(int i=0; i< parkingOperatingDays.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) parkingOperatingDays.get(i)).size(); k++){
					//result = 1:A 
					Object result = ((List<Object>) parkingOperatingDays.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_operations_day")){
						infoOperationDays = (String) keyValue[1];
					}
				}
				infoOperationsDaysList.add(infoOperationDays);
			}
			/**
			 * Get all Authorization Types for the selected parking
			 */
			String infoAuthorizationType=null;
			ArrayList<Object> parkingAuthorizationTypes = dbman.getFromDB("select * from sisca_authorization_parking_list where sisca_parking_name ~*'"+SelectedParkingName+"'");
			for(int i=0; i< parkingAuthorizationTypes.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) parkingAuthorizationTypes.get(i)).size(); k++){
					//result = 1:A 
					Object result = ((List<Object>) parkingAuthorizationTypes.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_authorization_name")){
						infoAuthorizationType = (String) keyValue[1];
					}
				}
				infoAuthorizationTypeList.add(infoAuthorizationType);
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

		JLabel parkingName = new JLabel(selectParkingName);
		parkingName.setPreferredSize(new Dimension(100, 50));
		parkingName.setHorizontalAlignment(SwingConstants.CENTER);
		parkingName.setForeground(java.awt.Color.BLACK);
		parkingName.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		centerPanel.add(parkingName, "cell 0 0,alignx center,aligny top");

		JLabel lblAvailableCapacity = new JLabel("Capacity: " + selectParkingCapacity);
		lblAvailableCapacity.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		centerPanel.add(lblAvailableCapacity, "cell 0 3,alignx left,aligny top");

		String parkingAuthorizations = "";
		for(int i=0; i<infoAuthorizationTypeList.size(); i++){
			parkingAuthorizations = parkingAuthorizations + "   " +  infoAuthorizationTypeList.get(i);
		}

		JLabel authorizations = new JLabel("Authorization Type(s): " + parkingAuthorizations);
		authorizations.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		centerPanel.add(authorizations, "cell 0 5,alignx left,aligny top");

		JSeparator separator_12 = new JSeparator();
		centerPanel.add(separator_12, "cell 0 4,growx,aligny top");

		JSeparator separator_13 = new JSeparator();
		centerPanel.add(separator_13, "cell 0 6,growx,aligny top");


		String operationsDays = "";
		for(int i=0; i<infoOperationsDaysList.size(); i++){
			operationsDays = operationsDays + "   " +  infoOperationsDaysList.get(i);
		}

		JLabel days = new JLabel("Operation Days: " + operationsDays);
		days.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		centerPanel.add(days, "cell 0 7,growx,aligny top");

		JSeparator separator_14 = new JSeparator();
		centerPanel.add(separator_14, "cell 0 8,growx,aligny top");

		JLabel hours = new JLabel("Operation Hours:  "+ selectParkingStarhour + " - " + selectParkingEndhour );
		hours.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		centerPanel.add(hours, "cell 0 9,growx,aligny top");

		JSeparator separator_15 = new JSeparator();
		centerPanel.add(separator_15, "cell 0 10,growx,aligny top");

		/**
		 * Concatenar los SADs para formar un string 
		 */
		String activeSads = "";
		for(int i=0; i<infoParkingSADsList.size(); i++){
			activeSads = activeSads + "   " +  infoParkingSADsList.get(i);
		}

		JLabel sads = new JLabel("SAD's: " + activeSads);
		sads.setFont(new Font("Lucida Grande", Font.BOLD, 15));
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
				HKJ_SisCA_MainPage.frame.setContentPane(editParkingView(SelectedParkingName));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		editAndRemovePanel.add(editButton, "cell 0 0");

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String parkingToRemove = SelectedParkingName;
				int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are you sure?", "WARNING", 1, 3);
				System.out.println("@@@ " + parkingToRemove);
				if(sure==0){
					try {
						dbman.updatetDB("delete from sisca_parking where sisca_parking_name ~*'"+parkingToRemove+"'");
						dbman.updatetDB("delete from sisca_sad_parking_list where sisca_parking_name ~*'"+parkingToRemove+"'");
						dbman.updatetDB("delete from sisca_authorization_parking_list where sisca_parking_name ~*'"+parkingToRemove+"'");
						dbman.updatetDB("delete from sisca_parking_operation_days_list where sisca_parking_name ~*'"+parkingToRemove+"'");
						HKJ_SisCA_MainPage.frame.setContentPane(parkingView());
						HKJ_SisCA_MainPage.frame.pack(); 
						HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					HKJ_SisCA_MainPage.frame.setContentPane(parkingView());
					HKJ_SisCA_MainPage.frame.pack(); 
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
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

	JPanel addParkingView(){

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

		JLabel userNameLabel = new JLabel("User Name   ");
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



//		JLabel pName1 = new JLabel((String) pNameLabelsArray.get(0));
//
//		pName1.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName1, "cell 0 3,alignx left,aligny top");
//
//		JLabel pName2 = new JLabel((String) pNameLabelsArray.get(1));
//		pName2.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName2, "cell 0 5,alignx left,aligny top");
//
//		JLabel pName3 = new JLabel((String) pNameLabelsArray.get(2));
//		pName3.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName3, "cell 0 7,alignx left,aligny top");
//
//		JLabel pName4 = new JLabel((String) pNameLabelsArray.get(3));
//		pName4.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName4, "cell 0 9,alignx left,aligny top");
//
//		JLabel pName5 = new JLabel((String) pNameLabelsArray.get(4));
//		pName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		pName5.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		liveSystemPanel.add(pName5, "cell 0 11,alignx left,aligny top");
//
//		JLabel pName6 = new JLabel((String) pNameLabelsArray.get(5));
//		pName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		pName6.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		liveSystemPanel.add(pName6, "cell 0 13,alignx left,aligny top");
//
//		JLabel pName7 = new JLabel((String) pNameLabelsArray.get(6));
//		pName7.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName7, "cell 0 15,alignx left,aligny top");
//
//		JLabel pName8 = new JLabel((String) pNameLabelsArray.get(7));
//		pName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		pName8.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		liveSystemPanel.add(pName8, "cell 0 17,alignx left,aligny top");
//
//		JLabel pName9 = new JLabel((String) pNameLabelsArray.get(8));
//		pName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		pName9.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		liveSystemPanel.add(pName9, "cell 0 19,alignx left,aligny top");
//
//		JLabel pName10 = new JLabel((String) pNameLabelsArray.get(9));
//		pName10.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName10, "cell 0 21,alignx left,aligny top");

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

		final JRadioButton wedRadioBtn = new JRadioButton("Wed");
		daysPanel.add(wedRadioBtn, "cell 2 0,alignx left,aligny top");

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


		final DefaultListModel availableSADModelList= new DefaultListModel();
		final DefaultListModel availableAtzTypesModelList= new DefaultListModel();
		final DefaultListModel selectedSadModelList = new DefaultListModel();
		final DefaultListModel selectedAtzModelList = new DefaultListModel();
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Query for showing list of Available SADs and Authorization Types in Add New Parking Page
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		try {
			dbman = new DBManager();
			ArrayList<Object> availableSAD = dbman.getFromDB("select * from sisca_sad where sisca_sad_active='false'");
			availableSAD = dbman.getAvailableSAD(availableSAD);
			ArrayList<Object> availableAtzType = dbman.getFromDB("select * from sisca_authorization");
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


		final JList availableSADList = new JList(availableSADModelList);
		scrollPaneSADs.setViewportView(availableSADList);

		JScrollPane scrollPaneCurrentSADs = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneCurrentSADs, "cell 1 1,grow");

		final JList selectedSADList = new JList();
		scrollPaneCurrentSADs.setViewportView(selectedSADList);

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


		// SAD And ATypes JList Button
		JButton addSADBtn = new JButton("Add");
		addSADBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String sadSelected = availableSADList.getSelectedValue().toString().toUpperCase();
				selectedSadModelList.addElement(sadSelected);
				availableSADModelList.removeElement(sadSelected);
				availableSADList.setModel(availableSADModelList);
				selectedSADList.setModel(selectedSadModelList);


			}
		});
		sadsAndATypesPanel.add(addSADBtn, "cell 0 2,growx,aligny top");

		JButton removeSADBtn = new JButton("Remove");
		removeSADBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sadSelected = selectedSADList.getSelectedValue().toString().toUpperCase();
				selectedSadModelList.removeElement(sadSelected);
				availableSADModelList.addElement(sadSelected);
				availableSADList.setModel(availableSADModelList);
				selectedSADList.setModel(selectedSadModelList);
			}
		});
		sadsAndATypesPanel.add(removeSADBtn, "cell 1 2,growx,aligny top");

		JButton addATypeBtn = new JButton("Add");
		addATypeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String authorizationSelected = ATypesList.getSelectedValue().toString().toUpperCase();				
				availableAtzTypesModelList.removeElement(authorizationSelected);
				selectedAtzModelList.addElement(authorizationSelected);
				ATypesList.setModel(availableAtzTypesModelList);
				currentATypesList.setModel(selectedAtzModelList);
			}
		});
		sadsAndATypesPanel.add(addATypeBtn, "cell 3 2,growx,aligny top");

		JButton removeATypeBtn = new JButton("Remove");
		removeATypeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String authorizationSelected = currentATypesList.getSelectedValue().toString().toUpperCase();				
				availableAtzTypesModelList.addElement(authorizationSelected);
				selectedAtzModelList.removeElement(authorizationSelected);
				ATypesList.setModel(availableAtzTypesModelList);
				currentATypesList.setModel(selectedAtzModelList);
			}
		});
		sadsAndATypesPanel.add(removeATypeBtn, "cell 4 2,growx,aligny top");



		////////////////////////
		// Query for Add a New Parking to the DB
		/////////////////////////////////

		JButton addParkingBtn = new JButton("Add Parking");
		addParkingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//TODO
				//Verificar duplicados ... verificar campos llenos ...
				
				// Fields from text fiedls
				String pName = textFieldName.getText();
				String pCapacity= "'"+textFieldCapacity.getText()+"'";
				String pSHours= "'"+textFieldStart.getText()+"'";
				String pEHours= "'"+textFieldEnd.getText()+"'";


				// Insert Parking Name, Capacity, Operation Start Hour and Operation End Hour to the DB
				String stm1 = "INSERT INTO sisca_parking (sisca_parking_name,sisca_parking_capacity,"
						+ "sisca_parking_starthour, sisca_parking_endhour) "
						+ "VALUES('"+ pName+"',"+ pCapacity+","+ pSHours+","+ pEHours+")";

				try {
					dbman.insertDB(stm1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Fields from Jlist
				ArrayList<String> optDays = new ArrayList<String>();
				String day=null;
				String addNewDays = null;
				if(monRadioBtn.isSelected()){
					optDays.add("monday");
				}
				if(tueRadioBtn.isSelected()){
					optDays.add("tuesday");
				}
				if(wedRadioBtn.isSelected()){
					optDays.add("wednesday");
				}
				if(thuRadioBtn.isSelected()){
					optDays.add("thrusday");
					
				}
				if(friRadioBtn.isSelected()){
					optDays.add("friday");
				}
				if(satRadioBtn.isSelected()){
					optDays.add("saturday");
				}
				if(sunRadioBtn.isSelected()){
					optDays.add("sunday");
				}

				for(int i=0; i<optDays.size(); i++){
					day = (String) optDays.get(i);
					addNewDays = "insert into sisca_parking_operation_days_list(sisca_parking_name, sisca_operations_day) values('"+pName+"','"+day.toUpperCase()+"')";
					try {
						dbman.updatetDB(addNewDays);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				/**
				 * Update new sad list
				 */
				String atzName=null;
				String addAtz=null;
				for(int i=0; i<selectedAtzModelList.size(); i++){
					atzName = (String) selectedAtzModelList.get(i);
					addAtz = "insert into sisca_authorization_parking_list(sisca_authorization_name, sisca_parking_name) values('"+atzName+"','"+pName+"')";
					try {
						dbman.updatetDB(addAtz);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				/**
				 * Update new sad list
				 */
				String sadName=null;
				String addNewSads=null;
				String updateSAD = null;
				String[] keyValue;
				for(int i=0; i<selectedSadModelList.size(); i++){
					sadName = (String) selectedSadModelList.get(i);
					keyValue = sadName.split(" >>");
					sadName = keyValue[0];
					addNewSads = "insert into sisca_sad_parking_list(sisca_sad_name, sisca_parking_name) values('"+sadName+"','"+pName+"')";
					
					updateSAD = "update sisca_sad set sisca_sad_active='true' where sisca_sad_name='"+sadName+"'";
					System.out.println(updateSAD);
					try {
						dbman.updatetDB(addNewSads);
						dbman.updatetDB(updateSAD);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(pName));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(addParkingBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Cancel me hace un BACK en el history
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
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

	private JPanel editParkingView(final String parkingToEdit){

		// Current Parking Information
		/*int parkingIDEdit = parkingInfoID;
		parkingCapacityEdit = parkingInfoCapacity;
		parkingNameEdit= parkingInfoName;
		parkingStartHourEdit = parkingInfoStartHour;
		parkingEndHourEdit = parkingInfoEndHour;

		DefaultListModel parkingSADs= parkingInfoSADListView;
		DefaultListModel parkingAuthorizationTypes= parkingInfoAuthorizationsListView;
		String parkingOperationDays= parkingInfoOperationsDay;*/

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

		JLabel userNameLabel = new JLabel("User Name   ");
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



//		JLabel pName1 = new JLabel((String) pNameLabelsArray.get(0));
//
//		pName1.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName1, "cell 0 3,alignx left,aligny top");
//
//		JLabel pName2 = new JLabel((String) pNameLabelsArray.get(1));
//		pName2.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName2, "cell 0 5,alignx left,aligny top");
//
//		JLabel pName3 = new JLabel((String) pNameLabelsArray.get(2));
//		pName3.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName3, "cell 0 7,alignx left,aligny top");
//
//		JLabel pName4 = new JLabel((String) pNameLabelsArray.get(3));
//		pName4.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName4, "cell 0 9,alignx left,aligny top");
//
//		JLabel pName5 = new JLabel((String) pNameLabelsArray.get(4));
//		pName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		pName5.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		liveSystemPanel.add(pName5, "cell 0 11,alignx left,aligny top");
//
//		JLabel pName6 = new JLabel((String) pNameLabelsArray.get(5));
//		pName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		pName6.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		liveSystemPanel.add(pName6, "cell 0 13,alignx left,aligny top");
//
//		JLabel pName7 = new JLabel((String) pNameLabelsArray.get(6));
//		pName7.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName7, "cell 0 15,alignx left,aligny top");
//
//		JLabel pName8 = new JLabel((String) pNameLabelsArray.get(7));
//		pName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		pName8.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		liveSystemPanel.add(pName8, "cell 0 17,alignx left,aligny top");
//
//		JLabel pName9 = new JLabel((String) pNameLabelsArray.get(8));
//		pName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		pName9.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		liveSystemPanel.add(pName9, "cell 0 19,alignx left,aligny top");
//
//		JLabel pName10 = new JLabel((String) pNameLabelsArray.get(9));
//		pName10.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(null));
//				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//			}
//		});
//		pName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		liveSystemPanel.add(pName10, "cell 0 21,alignx left,aligny top");

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


		/**
		 * Get from DB the information to fill the fields that can will be edited
		 */
		final DefaultListModel infoParkingSADsListToEdit = new DefaultListModel(); //Contiene los SADs q se desplegan durante el parking Info view
		final DefaultListModel availableParkingSADsList = new DefaultListModel();
		final DefaultListModel availableAuthorizationTypeList = new DefaultListModel();
		final DefaultListModel infoAuthorizationTypeListToEdit = new DefaultListModel(); //Contiene los tipos de autorizacion para este estacionamiento

		final ArrayList<Object> infoOperationsDaysListToEdit = new ArrayList<Object>(); //Contiene los Dias que este Parking trabaja

		/**
		 *	Important Variables
		 */
		String parkingNameToEdit = null, parkingEndhourToEdit=null, parkingStarhourToEdit=null;
		int parkingCapacityToEdit=99999;
		String[] keyValue;
		try {
			dbman = new DBManager();
			/**
			 * Get all parking information form the DB (Parking Name, Capacity and Operating Hours)
			 */
			ArrayList<Object> parkingToEditDB = dbman.getFromDB("select * from sisca_parking where sisca_parking_name ~*'"+parkingToEdit+"'");
			for(int i=0; i< parkingToEditDB.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) parkingToEditDB.get(i)).size(); k++){
					//result = 1:A 
					Object result = ((List<Object>) parkingToEditDB.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_parking_name")){
						parkingNameToEdit = (String) keyValue[1];
					}
					if(keyValue[0].equals("sisca_parking_capacity")){
						parkingCapacityToEdit = Integer.valueOf((String) keyValue[1]);
					}
					if(keyValue[0].equals("sisca_parking_starthour")){
						parkingStarhourToEdit = (String) keyValue[1];
					}
					if(keyValue[0].equals("sisca_parking_endhour")){
						parkingEndhourToEdit = (String) keyValue[1];
					}
				}
			}
			/**
			 * Get all operations days for the selected parking 
			 */
			String infoOperationDaysToEdit=null;
			ArrayList<Object> parkingOperatingDays = dbman.getFromDB("select * from sisca_parking_operation_days_list where sisca_parking_name ~*'"+parkingToEdit+"'");
			for(int i=0; i< parkingOperatingDays.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) parkingOperatingDays.get(i)).size(); k++){
					//result = 1:A 
					Object result = ((List<Object>) parkingOperatingDays.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_operations_day")){
						infoOperationDaysToEdit = (String) keyValue[1];
					}
				}
				infoOperationsDaysListToEdit.add(infoOperationDaysToEdit);
			}
			/**
			 * Get all SADs available 
			 */
			String availableParkingSADs=null;
			ArrayList<Object> availableParkingSadListQuery = dbman.getFromDB("select * from sisca_sad where sisca_sad_active=false");
			for(int i=0; i< availableParkingSadListQuery.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) availableParkingSadListQuery.get(i)).size(); k++){
					//result = 1:A 
					Object result = ((List<Object>) availableParkingSadListQuery.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_sad_name")){
						availableParkingSADs = (String) keyValue[1];
					}
				}
				availableParkingSADsList.addElement(availableParkingSADs);
			}
			/**
			 * Get all SADs for the selected parking
			 */
			String infoParkingSADsToEdit=null;
			ArrayList<Object> parkingSadListQuery = dbman.getFromDB("select * from sisca_sad_parking_list where sisca_parking_name ~*'"+parkingToEdit+"'");
			for(int i=0; i< parkingSadListQuery.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) parkingSadListQuery.get(i)).size(); k++){
					//result = 1:A 
					Object result = ((List<Object>) parkingSadListQuery.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_sad_name")){
						infoParkingSADsToEdit = (String) keyValue[1];
					}
				}
				infoParkingSADsListToEdit.addElement(infoParkingSADsToEdit);
			}
			/**
			 * Get all Authorization Types for the selected parking
			 */
			String infoAuthorizationTypeToEdit=null;
			ArrayList<Object> parkingAuthorizationTypes = dbman.getFromDB("select * from sisca_authorization_parking_list where sisca_parking_name ~*'"+parkingToEdit+"'");
			for(int i=0; i< parkingAuthorizationTypes.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) parkingAuthorizationTypes.get(i)).size(); k++){
					//result = 1:A 
					Object result = ((List<Object>) parkingAuthorizationTypes.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_authorization_name")){
						infoAuthorizationTypeToEdit = (String) keyValue[1];
					}
				}
				infoAuthorizationTypeListToEdit.addElement(infoAuthorizationTypeToEdit.toUpperCase());
			}

			/**
			 * Get all available Authorization Types 
			 */
			String availableAuthorizationType=null;
			ArrayList<Object> availableAuthorizationTypes = dbman.getFromDB("select * from sisca_authorization");
			for(int i=0; i< availableAuthorizationTypes.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) availableAuthorizationTypes.get(i)).size(); k++){
					//result = 1:A 
					Object result = ((List<Object>) availableAuthorizationTypes.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_authorization_name")){
						availableAuthorizationType = (String) keyValue[1];
					}
				}
				availableAuthorizationTypeList.addElement(availableAuthorizationType.toUpperCase());
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



		JPanel pNamePanel = new JPanel();
		pNamePanel.setBackground((java.awt.Color) null);
		centerPanel.add(pNamePanel, "cell 0 1,growx,aligny top");
		pNamePanel.setLayout(new MigLayout("", "[109px][269.00px]", "[28px]"));

		// Parking Name
		JLabel nameLabel = new JLabel("Parking Name:");
		pNamePanel.add(nameLabel, "cell 0 0,alignx left,aligny center");
		nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		final JTextField textFieldName = new JTextField();
		textFieldName.setPreferredSize(new Dimension(200, 100));
		pNamePanel.add(textFieldName, "cell 1 0,grow");
		textFieldName.setColumns(10);
		textFieldName.setEditable(true);
		textFieldName.setText(parkingNameToEdit);

		// Parking Capacity
		JPanel capacityPanel = new JPanel();
		capacityPanel.setBackground((java.awt.Color) null);
		centerPanel.add(capacityPanel, "cell 0 2,growx,aligny top");
		capacityPanel.setLayout(new MigLayout("", "[66.00px][]", "[28px]"));

		JLabel capacityLabel = new JLabel("Capacity: ");
		capacityPanel.add(capacityLabel, "cell 0 0,alignx left,aligny center");
		capacityLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		final JTextField textFieldCapacity = new JTextField();
		capacityPanel.add(textFieldCapacity, "cell 1 0,alignx right,aligny top");
		textFieldCapacity.setColumns(10);
		textFieldCapacity.setText(String.valueOf((Integer)parkingCapacityToEdit));

		// Parking Operation Days
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

		final JRadioButton wedRadioBtn = new JRadioButton("Wed");
		daysPanel.add(wedRadioBtn, "cell 2 0,alignx left,aligny top");

		final JRadioButton thuRadioBtn = new JRadioButton("Thu");
		daysPanel.add(thuRadioBtn, "cell 3 0,alignx left,aligny top");

		final JRadioButton friRadioBtn = new JRadioButton("Fri");
		daysPanel.add(friRadioBtn, "cell 4 0,growx,aligny top");

		final JRadioButton satRadioBtn = new JRadioButton("Sat");
		daysPanel.add(satRadioBtn, "cell 5 0,alignx left,aligny top");

		final JRadioButton sunRadioBtn = new JRadioButton("Sun");
		daysPanel.add(sunRadioBtn, "cell 6 0,alignx left,aligny top");

		for(int i=0; i<infoOperationsDaysListToEdit.size(); i++){

			String day = (String) infoOperationsDaysListToEdit.get(i);

			if(day.toLowerCase().equals(("monday")))
			{
				monRadioBtn.doClick();
			}
			if(day.toLowerCase().equals("tuesday"))
			{
				tueRadioBtn.doClick();
			}
			if(day.toLowerCase().equals("wednesday"))
			{
				wedRadioBtn.doClick();
			}
			if(day.toLowerCase().equals("thursday"))
			{
				thuRadioBtn.doClick();
			}
			if(day.toLowerCase().equals("friday"))
			{
				friRadioBtn.doClick();
			}
			if(day.toLowerCase().equals("saturday"))
			{
				satRadioBtn.doClick();
			}
			if(day.toLowerCase().equals("sunday"))
			{
				sunRadioBtn.doClick();
			}

		}

		// Parking Operation Hours
		JPanel hoursPanel = new JPanel();
		hoursPanel.setBackground((java.awt.Color) null);
		centerPanel.add(hoursPanel, "cell 0 4,grow");
		hoursPanel.setLayout(new MigLayout("", "[][][35][70.00][35][70]", "[]"));

		JLabel hoursLabel = new JLabel("Operation Hours: ");
		hoursPanel.add(hoursLabel, "cell 0 0");
		hoursLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel startLabel = new JLabel("Start: " );
		startLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		hoursPanel.add(startLabel, "cell 2 0,alignx trailing");

		final JTextField textFieldStart = new JTextField(parkingStarhourToEdit);
		hoursPanel.add(textFieldStart, "cell 3 0");
		textFieldStart.setColumns(10);

		JLabel endLabel = new JLabel("End: ");
		endLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		hoursPanel.add(endLabel, "flowx,cell 4 0");

		final JTextField textFieldEnd = new JTextField(parkingEndhourToEdit);
		textFieldEnd.setColumns(10);
		hoursPanel.add(textFieldEnd, "cell 5 0");

		// Parking SADs & Authorization Types
		JPanel sadsAndATypesPanel = new JPanel();
		sadsAndATypesPanel.setBackground((java.awt.Color) null);
		centerPanel.add(sadsAndATypesPanel, "cell 0 5,grow");
		sadsAndATypesPanel.setLayout(new MigLayout("", "[161px][161px][159.00][161px][161px]", "[21px][83px][29px]"));

		JScrollPane scrollPaneSADs = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneSADs, "cell 0 1,grow");

		/**
		 * Available Parking Sad List View
		 */
		final JList SADList = new JList(availableParkingSADsList);
		scrollPaneSADs.setViewportView(SADList);

		JScrollPane scrollPaneCurrentSADs = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneCurrentSADs, "cell 1 1,grow");

		/**
		 * Current Sads for the parking in the edit process
		 */
		final JList currentSADList = new JList(infoParkingSADsListToEdit);
		scrollPaneCurrentSADs.setViewportView(currentSADList);

		JScrollPane scrollPaneATypes = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneATypes, "cell 3 1,grow");


		/**
		 * Find the repeated elements
		 */
		for(int i=0; i<infoAuthorizationTypeListToEdit.size(); i++){
			Object current   = infoAuthorizationTypeListToEdit.get(i);
			System.out.println("current  " + current);
			if(availableAuthorizationTypeList.contains(current)){
				availableAuthorizationTypeList.removeElement(current);
			}
		}		

		/**
		 * Available Authorization Type List
		 */
		final JList ATypesList = new JList(availableAuthorizationTypeList);
		scrollPaneATypes.setViewportView(ATypesList);

		JScrollPane scrollPaneCurrentATypes = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneCurrentATypes, "cell 4 1,grow");

		/**
		 * Current Authorization Type List 
		 */
		final JList currentATypesList = new JList(infoAuthorizationTypeListToEdit);
		scrollPaneCurrentATypes.setViewportView(currentATypesList);

		JLabel availableSads = new JLabel("Available SADs: ");
		availableSads.setHorizontalAlignment(SwingConstants.CENTER);
		sadsAndATypesPanel.add(availableSads, "cell 0 0 2 1,growx,aligny top");
		availableSads.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel currentSads = new JLabel("Current SADs: ");
		currentSads.setHorizontalAlignment(SwingConstants.CENTER);
		sadsAndATypesPanel.add(currentSads, "cell 0 0 2 1,growx,aligny top");
		currentSads.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel availableAuthorizations = new JLabel("Available Authorizations: ");
		availableAuthorizations.setHorizontalAlignment(SwingConstants.CENTER);
		availableAuthorizations.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		sadsAndATypesPanel.add(availableAuthorizations, "cell 3 0 2 1,growx,aligny bottom");

		JLabel currentAuthorizations = new JLabel("Current Authorizations: ");
		currentAuthorizations.setHorizontalAlignment(SwingConstants.CENTER);
		currentAuthorizations.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		sadsAndATypesPanel.add(currentAuthorizations, "cell 3 0 2 1,growx,aligny bottom");

		/**
		 * New Sad chosen for the parking edited
		 */

		// Manage SAD and Authorization Lists
		JButton addSADBtn = new JButton("Add");
		addSADBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String sadSelected = SADList.getSelectedValue().toString().toUpperCase();
				infoParkingSADsListToEdit.addElement(sadSelected);
				availableParkingSADsList.removeElement(sadSelected);
				SADList.setModel(availableParkingSADsList);
				currentSADList.setModel(infoParkingSADsListToEdit);
			}
		});
		sadsAndATypesPanel.add(addSADBtn, "cell 0 2,growx,aligny top");


		JButton removeSADBtn = new JButton("Remove");
		removeSADBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sadSelected = currentSADList.getSelectedValue().toString().toUpperCase();
				infoParkingSADsListToEdit.removeElement(sadSelected);
				availableParkingSADsList.addElement(sadSelected);
				SADList.setModel(availableParkingSADsList);
				currentSADList.setModel(infoParkingSADsListToEdit);

			}
		});
		sadsAndATypesPanel.add(removeSADBtn, "cell 1 2,growx,aligny top");

		JButton addATypeBtn = new JButton("Add");
		addATypeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String authorizationSelected = ATypesList.getSelectedValue().toString().toUpperCase();				
				infoAuthorizationTypeListToEdit.addElement(authorizationSelected);
				availableAuthorizationTypeList.removeElement(authorizationSelected);
				ATypesList.setModel(availableAuthorizationTypeList);
				currentATypesList.setModel(infoAuthorizationTypeListToEdit);
			}
		});
		sadsAndATypesPanel.add(addATypeBtn, "cell 3 2,growx,aligny top");

		JButton removeATypeBtn = new JButton("Remove");
		removeATypeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String authorizationSelected = currentATypesList.getSelectedValue().toString().toUpperCase();
				infoAuthorizationTypeListToEdit.removeElement(authorizationSelected);
				availableAuthorizationTypeList.addElement(authorizationSelected);
				ATypesList.setModel(availableAuthorizationTypeList);
				currentATypesList.setModel(infoAuthorizationTypeListToEdit);
			}
		});
		sadsAndATypesPanel.add(removeATypeBtn, "cell 4 2,growx,aligny top");

		///////////////////////////////////////////////////////////////////////////
		// Query for Edit a Parking to the DB
		///////////////////////////////////////////////////////////////////////////
		JButton editParkingBtn = new JButton("OK");
		editParkingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				/**
				 * Get the new information from the test fields
				 */
				String parkingNameEdit = textFieldName.getText();
				String parkingCapacityEdit = textFieldCapacity.getText();
				String parkingStartHourEdit = textFieldStart.getText();
				String parkingEndHourEdit = textFieldEnd.getText();

				/**
				 * Delete current sads &  current operations days
				 */
				String deleteCurrentSad = "delete from sisca_sad_parking_list where sisca_parking_name ~* '"+parkingToEdit+"'";
				String deleteCurrentOperationDays = "delete from sisca_parking_operation_days_list where sisca_parking_name ~* '"+parkingToEdit+"'";
				try {
					System.out.println(deleteCurrentSad);
					dbman.updatetDB(deleteCurrentSad);
					dbman.updatetDB(deleteCurrentOperationDays);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String addNewSads = null;
				String sadName = null;
				String day = null;
				String addNewDays = null;

				/**
				 * Update new sad list
				 */
				for(int i=0; i<infoParkingSADsListToEdit.size(); i++){
					sadName = (String) infoParkingSADsListToEdit.get(i);
					addNewSads = "insert into sisca_sad_parking_list(sisca_sad_name, sisca_parking_name) values('"+sadName+"','"+parkingNameEdit+"')";
					try {
						dbman.insertDB(addNewSads);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				/**
				 * Update Operation Days
				 * Get selected Radio buttom
				 */
				ArrayList<String> days = new ArrayList<String>();
				if(monRadioBtn.isSelected()){
					days.add("monday");
				}
				if(tueRadioBtn.isSelected()){
					days.add("tuesday");
				}
				if(wedRadioBtn.isSelected()){
					days.add("wednesday");
				}
				if(thuRadioBtn.isSelected()){
					days.add("thrusday");
				}
				if(friRadioBtn.isSelected()){
					days.add("friday");
				}
				if(satRadioBtn.isSelected()){
					days.add("saturday");
				}
				if(sunRadioBtn.isSelected()){
					days.add("sunday");
				}

				for(int i=0; i<days.size(); i++){
					day = (String) days.get(i);
					addNewDays = "insert into sisca_parking_operation_days_list(sisca_parking_name, sisca_operations_day) values('"+parkingNameEdit+"','"+day.toUpperCase()+"')";
					try {
						dbman.updatetDB(addNewDays);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(parkingNameEdit));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(editParkingBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Cancel me hace un BACK en el history
				HKJ_SisCA_MainPage.frame.setContentPane(parkingInformationView(parkingToEdit));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(cancelBtn, "cell 26 0");

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
