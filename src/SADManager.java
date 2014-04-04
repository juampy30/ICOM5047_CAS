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

import javax.lang.model.element.Element;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import databases.DBManager;
import net.miginfocom.swing.MigLayout;


public class SADManager {

	static DBManager dbman;
	static ArrayList<Object> availableSAD;
	static DefaultListModel availableSADModelList;
	static Boolean updateJList=true;
	private static JList sadList;

	SADManager (){

	}
	//////////////////////////////////////////////////////////////////
	//   SAD View								               	    //
	//////////////////////////////////////////////////////////////////


	static JPanel sadView(){
		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelSAD = new JPanel();
		menuPanelSAD.setBackground(new Color(255,239,80));
		menuPanelSAD.setPreferredSize(new Dimension(10, 30));
		menuPanelSAD.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(250, 10));
		menuPanelSAD.add(menuOptionsPanel, BorderLayout.WEST);
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

		JLabel SADManagerLabel = new JLabel("SAD");
		SADManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		SADManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		SADManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SADManagerLabel.setForeground((java.awt.Color) null);
		SADManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		SADManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		SADManagerLabel.setBounds(66, -11, 109, 53);
		menuOptionsPanel.add(SADManagerLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelSAD.add(logOutPanel, BorderLayout.EAST);
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
		menuPanelSAD.add(userNamePanel, BorderLayout.CENTER);
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
		JPanel mainPanelSAD = new JPanel();
		mainPanelSAD.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelSAD.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelSAD.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelSAD.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelSAD.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelSAD.add(centerPanel, BorderLayout.CENTER);
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
				// Update del JList
				updateJList= false;
				availableSADModelList.clear();

				String searchField="'"+textFieldSearch.getText()+"'";
				String query= "Select * from sisca_sad where sisca_sad_name ~*"+searchField;
				try {
					dbman= new DBManager();
					availableSAD= dbman.getFromDB(query);
					availableSAD= dbman.getAvailableSAD(availableSAD);
					for(int i=0; i<availableSAD.size(); i++){
						availableSADModelList.addElement(availableSAD.get(i));
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
				HKJ_SisCA_MainPage.frame.setContentPane(sadView());
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
				// Update del JList
				updateJList= false;
				availableSADModelList.clear();

				String searchField="'"+textFieldSearch.getText()+"'";
				String query= "Select * from sisca_sad where sisca_sad_name ~*"+searchField;
				try {
					dbman= new DBManager();
					availableSAD= dbman.getFromDB(query);
					availableSAD= dbman.getAvailableSAD(availableSAD);
					for(int i=0; i<availableSAD.size(); i++){
						availableSADModelList.addElement(availableSAD.get(i));
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
				HKJ_SisCA_MainPage.frame.setContentPane(sadView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	


			}
		});
		goButton.setPreferredSize(new Dimension(10, 29));
		searchAndAddPanel.add(goButton, "cell 2 0");

		JButton addNewSADBtn = new JButton("Add New SAD");
		addNewSADBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addSADView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		searchAndAddPanel.add(addNewSADBtn, "cell 25 0,alignx left,aligny top");
		JPanel SADListPanel = new JPanel();
		SADListPanel.setBackground(new Color(250,250,250));

		centerPanel.add(SADListPanel, "cell 0 1,alignx center,growy");
		SADListPanel.setLayout(new MigLayout("", "[724px]", "[360px]"));
		JScrollPane sadScrollPane = new JScrollPane();
		SADListPanel.add(sadScrollPane, "cell 0 0,grow");

		//////////////////////////////////
		// Initialize SAD List
		/////////////////////////////////

		sadList = new JList();
		
		if (updateJList== true){
			availableSAD= new ArrayList<Object>();
			availableSADModelList= new DefaultListModel();
			String query= "Select * from sisca_sad";
			try {
				dbman= new DBManager();
				availableSAD= dbman.getFromDB(query);
				availableSAD= dbman.getAvailableSAD(availableSAD);
				for(int i=0; i<availableSAD.size(); i++){
					availableSADModelList.addElement(availableSAD.get(i));
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
			sadList.setModel(availableSADModelList);
		}
		else{
			updateJList=true;
			sadList.setModel(availableSADModelList);
		}

		sadList.setSelectionForeground(UIManager.getColor("Button.darkShadow"));
		sadList.setSelectionBackground(UIManager.getColor("Button.background"));
		sadList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					//////////////////////////
					// Paso Informaci—n del SAD seleccionado
					/////////////////////////

					String selectedValue=(String) sadList.getSelectedValue();
					String[] selectedValueArray= selectedValue.split(" >>");
					selectedValue= selectedValueArray[0];

					HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(selectedValue)); 
					HKJ_SisCA_MainPage.frame.pack();
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			}
		});
		sadScrollPane.setViewportView(sadList);



		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelSAD= new JPanel();
		windowPanelSAD.setLayout(new BorderLayout(0, 0));
		windowPanelSAD.add(menuPanelSAD, BorderLayout.NORTH);
		windowPanelSAD.add(mainPanelSAD, BorderLayout.CENTER);

		return windowPanelSAD;

	}
	//////////////////////////////////////////////////////////////////
	//   SAD Information View								        //
	//////////////////////////////////////////////////////////////////

	private static JPanel sadInformationView(String s_Name) {

		System.out.println("Printing sName:" + s_Name);
		final String sadName= s_Name;

		/////////////////////////////////////////////////////////
		//Menu Panel
		/////////////////////////////////////////////////////////

		//Configurations 
		JPanel menuPanelSADInformation = new JPanel();
		menuPanelSADInformation.setBackground(new Color(255,239,80));
		menuPanelSADInformation.setPreferredSize(new Dimension(10, 30));
		menuPanelSADInformation.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(375, 10));
		menuPanelSADInformation.add(menuOptionsPanel, BorderLayout.WEST);
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

		JLabel SADManagerLabel = new JLabel("SAD");
		SADManagerLabel.setPreferredSize(new Dimension(30, 16));
		SADManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadView());
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		SADManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		SADManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SADManagerLabel.setForeground((java.awt.Color) null);
		SADManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		SADManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		SADManagerLabel.setBounds(66, -11, 95, 53);
		menuOptionsPanel.add(SADManagerLabel);

		JLabel listSADLabel = new JLabel("SAD Information");
		listSADLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sadName));
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		listSADLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listSADLabel.setHorizontalAlignment(SwingConstants.CENTER);
		listSADLabel.setForeground((java.awt.Color) null);
		listSADLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		listSADLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listSADLabel.setBounds(159, -11, 166, 53);
		menuOptionsPanel.add(listSADLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelSADInformation.add(logOutPanel, BorderLayout.EAST);
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
		menuPanelSADInformation.add(userNamePanel, BorderLayout.CENTER);
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
		JPanel leftPanelSADInformation = new JPanel();
		leftPanelSADInformation.setBackground(new Color(245,245,245));
		leftPanelSADInformation.setPreferredSize(new Dimension(390, 10));
		leftPanelSADInformation.setLayout(new BorderLayout(0, 0));

		JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanelSADInformation.add(scrollPane);

		JLayeredPane liveSystemPanel21 = new JLayeredPane();
		scrollPane.setViewportView(liveSystemPanel);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel21.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));

		//SAD Name Labels & MouseCliked Event 

		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px]"));
		//LeftPanel Labels
		JLabel LSystemLabel = new JLabel("SAD LIST");
		liveSystemPanel.add(LSystemLabel, "cell 0 0,alignx center,aligny top");
		LSystemLabel.setPreferredSize(new Dimension(100, 50));
		LSystemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LSystemLabel.setForeground(java.awt.Color.BLACK);
		LSystemLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));


		////////////////////////////////////////////////////////////////////////////////////////////////
		// Query for get Parking Names for the Labels
		////////////////////////////////////////////////////////////////////////////////////
		ArrayList sNameLabelsArray = new ArrayList() ;
		try {
			dbman= new DBManager();

			sNameLabelsArray=dbman.getFromDB("Select * from sisca_sad ORDER BY sisca_sad_name");
			//System.out.println("Before Test:"+pNameLabelsArray);
			sNameLabelsArray= dbman.getAvailableSADOnly(sNameLabelsArray);


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


		final JLabel sName1 = new JLabel();
		if(sNameLabelsArray.size()>=1){
			sName1.setText((String) sNameLabelsArray.get(0));
		}
		sName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName1.getText()));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName1, "cell 0 3,alignx left,aligny top");

		final JLabel sName2 = new JLabel();
		if(sNameLabelsArray.size()>=2){
			sName2.setText((String) sNameLabelsArray.get(1));
		}
		sName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName2.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName2, "cell 0 5,alignx left,aligny top");

		final JLabel sName3 = new JLabel();
		if(sNameLabelsArray.size()>=3){
			sName3.setText((String) sNameLabelsArray.get(2));
		}
		sName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName3.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName3, "cell 0 7,alignx left,aligny top");

		final JLabel sName4 = new JLabel();
		if(sNameLabelsArray.size()>=4){
			sName4.setText((String) sNameLabelsArray.get(3));
		}
		sName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName4.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName4, "cell 0 9,alignx left,aligny top");

		final JLabel sName5 = new JLabel();
		if(sNameLabelsArray.size()>=5){
			sName5.setText((String) sNameLabelsArray.get(4));
		}
		sName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName5.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName5, "cell 0 11,alignx left,aligny top");

		final JLabel sName6 = new JLabel();
		if(sNameLabelsArray.size()>=6){
			sName6.setText((String) sNameLabelsArray.get(5));
		}
		sName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName6.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName6, "cell 0 13,alignx left,aligny top");

		final JLabel sName7 = new JLabel();
		if(sNameLabelsArray.size()>=7){
			sName7.setText((String) sNameLabelsArray.get(6));
		}
		sName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName7.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName7, "cell 0 15,alignx left,aligny top");

		final JLabel sName8 = new JLabel();
		if(sNameLabelsArray.size()>=8){
			sName8.setText((String) sNameLabelsArray.get(7));
		}
		sName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName8.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName8, "cell 0 17,alignx left,aligny top");

		final JLabel sName9 = new JLabel();
		if(sNameLabelsArray.size()>=9){
			sName9.setText((String) sNameLabelsArray.get(8));
		}
		sName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName9.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName9, "cell 0 19,alignx left,aligny top");

		final JLabel sName10 = new JLabel();
		if(sNameLabelsArray.size()>=10){
			sName10.setText((String) sNameLabelsArray.get(9));
		}
		sName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName10.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName10, "cell 0 21,alignx left,aligny top");

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
				availableSADModelList.clear();

				String searchField="'"+searchTextField.getText()+"'";
				String query= "Select * from sisca_sad where sisca_sad_name ~*"+searchField;
				try {
					dbman= new DBManager();
					availableSAD= dbman.getFromDB(query);
					availableSAD= dbman.getAvailableSAD(availableSAD);
					for(int i=0; i<availableSAD.size(); i++){
						availableSADModelList.addElement(availableSAD.get(i));
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
				HKJ_SisCA_MainPage.frame.setContentPane(sadView());
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
				HKJ_SisCA_MainPage.frame.setContentPane(sadView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New SAD");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addSADView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(addNewButton);




		/////////////////////////////////////////////////////////
		//Main Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel mainPanelSADInformation = new JPanel();
		mainPanelSADInformation.setLayout(new BorderLayout(0, 0));


		//Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelSADInformation.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelSADInformation.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelSADInformation.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelSADInformation.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelSADInformation.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[849.00px]", "[50px][][12px][19px][12px][19px][12px][19px][12px][19px][12px][19px][12px][65px]"));

		// Fill SAD Information



		JLabel SADid = new JLabel(sadName);
		SADid.setPreferredSize(new Dimension(100, 50));
		SADid.setHorizontalAlignment(SwingConstants.CENTER);
		SADid.setForeground(java.awt.Color.BLACK);
		SADid.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		centerPanel.add(SADid, "cell 0 0,alignx center,aligny top");



		JLabel lblDirection = new JLabel("Direction: ");
		lblDirection.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		centerPanel.add(lblDirection, "cell 0 3,alignx left,aligny top");

		JLabel lblActive = new JLabel("Active?: ");
		lblActive.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		centerPanel.add(lblActive, "cell 0 5,alignx left,aligny top");
		
		String savedDirection = null;
		String savedActive = null;


		String query= "Select * from sisca_sad where sisca_sad_name='"+sadName+"'";
		try {
			ArrayList sadInformation= dbman.getFromDB(query);
			//sadInformation= dbman.getAvailableSAD(sadInformation);
			//String direction= (String) sadInformation.get(0);
			//String active= (String) sadInformation.get(0);
			
			//sadSavedInformation[0]=sadName;

			Object result = ((List<Object>) sadInformation.get(0)).get(2);
			Object[] keyValue;
			keyValue = result.toString().split("/");;
			String sadDirection= (String) keyValue[1];
			
			savedDirection=sadDirection;
			

			result = ((List<Object>) sadInformation.get(0)).get(3);
			keyValue = result.toString().split("/");;
			String activeStatus= (String) keyValue[1];
			
			savedActive=activeStatus;

			if(sadDirection.equals("exit")){
				lblDirection.setText("Direction: Exit");
			}
			else{
				lblDirection.setText("Direction: Entry");
			}



			if(activeStatus.equals("t")){
				lblActive.setText("Is Active?: Yes ");
			}
			else{
				lblActive.setText("Is Active?: No ");
			}



		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		//
		JSeparator separator_12 = new JSeparator();
		centerPanel.add(separator_12, "cell 0 4,growx,aligny top");
		//
		JSeparator separator_13 = new JSeparator();
		centerPanel.add(separator_13, "cell 0 6,growx,aligny top");
		//
		//		JLabel days = new JLabel("Operation Days: ");
		//		days.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		//		centerPanel.add(days, "cell 0 7,growx,aligny top");
		//
		//		JSeparator separator_14 = new JSeparator();
		//		centerPanel.add(separator_14, "cell 0 8,growx,aligny top");
		//
		//		JLabel hours = new JLabel("Operation Hours: ");
		//		hours.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		//		centerPanel.add(hours, "cell 0 9,growx,aligny top");
		//
		//		JSeparator separator_15 = new JSeparator();
		//		centerPanel.add(separator_15, "cell 0 10,growx,aligny top");

		JPanel editAndRemovePanel = new JPanel();
		editAndRemovePanel.setBackground(new Color(250,250,250));
		centerPanel.add(editAndRemovePanel, "cell 0 11,grow");
		editAndRemovePanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));

		final String s1= savedDirection;
		final String s2= savedActive;
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(editSADView(sadName,s1,s2));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		editAndRemovePanel.add(editButton, "cell 0 0");

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO remover
				HKJ_SisCA_MainPage.frame.setContentPane(sadView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}

		});
		editAndRemovePanel.add(btnRemove, "cell 26 0");

		JSeparator separator_16 = new JSeparator();
		centerPanel.add(separator_16, "cell 0 12,growx,aligny top");

		JSeparator separator_17 = new JSeparator();
		centerPanel.add(separator_17, "cell 0 2,growx,aligny top");


		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		JPanel windowPanelSADInformation= new JPanel();
		windowPanelSADInformation.setLayout(new BorderLayout(0, 0));
		windowPanelSADInformation.add(menuPanelSADInformation, BorderLayout.NORTH);
		windowPanelSADInformation.add(leftPanelSADInformation, BorderLayout.WEST);
		windowPanelSADInformation.add(mainPanelSADInformation, BorderLayout.CENTER);



		return windowPanelSADInformation;
	}

	//////////////////////////////////////////////////////////////////
	// Add SAD View								               	    //
	//////////////////////////////////////////////////////////////////
	private static JPanel addSADView() {

		/////////////////////////////////////////////////////////
		//           Menu Panel
		/////////////////////////////////////////////////////////

		// Configurations 
		JPanel menuPanelAddSAD = new JPanel();
		menuPanelAddSAD.setBackground(new Color(255,239,80));
		menuPanelAddSAD.setPreferredSize(new Dimension(10, 30));
		menuPanelAddSAD.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(375, 10));
		menuPanelAddSAD.add(menuOptionsPanel, BorderLayout.WEST);
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

		JLabel SADManagerLabel = new JLabel("SADs");
		SADManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		SADManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		SADManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SADManagerLabel.setForeground((java.awt.Color) null);
		SADManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		SADManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		SADManagerLabel.setBounds(66, -11, 84, 53);
		menuOptionsPanel.add(SADManagerLabel);

		JLabel addSADLabel = new JLabel("Add SAD");
		addSADLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(addSADView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		addSADLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addSADLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addSADLabel.setForeground((java.awt.Color) null);
		addSADLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		addSADLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		addSADLabel.setBounds(148, -11, 111, 53);
		menuOptionsPanel.add(addSADLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelAddSAD.add(logOutPanel, BorderLayout.EAST);
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
		menuPanelAddSAD.add(userNamePanel, BorderLayout.CENTER);
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
		JPanel leftPanelSADInformation = new JPanel();
		leftPanelSADInformation.setBackground(new Color(245,245,245));
		leftPanelSADInformation.setPreferredSize(new Dimension(390, 10));
		leftPanelSADInformation.setLayout(new BorderLayout(0, 0));

		JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanelSADInformation.add(scrollPane);

		JLayeredPane liveSystemPanel21 = new JLayeredPane();
		scrollPane.setViewportView(liveSystemPanel);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel21.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));

		//SAD Name Labels & MouseCliked Event 

		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px]"));







		//LeftPanel Labels
		JLabel LSystemLabel = new JLabel("SAD LIST");
		liveSystemPanel.add(LSystemLabel, "cell 0 0,alignx center,aligny top");
		LSystemLabel.setPreferredSize(new Dimension(100, 50));
		LSystemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LSystemLabel.setForeground(java.awt.Color.BLACK);
		LSystemLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));


		////////////////////////////////////////////////////////////////////////////////////////////////
		//Query for get Parking Names for the Labels
		////////////////////////////////////////////////////////////////////////////////////
		ArrayList sNameLabelsArray = new ArrayList() ;
		try {
			dbman= new DBManager();

			sNameLabelsArray=dbman.getFromDB("Select * from sisca_sad ORDER BY sisca_sad_name");
			//System.out.println("Before Test:"+pNameLabelsArray);
			sNameLabelsArray= dbman.getAvailableSADOnly(sNameLabelsArray);


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





		final JLabel sName1 = new JLabel();
		if(sNameLabelsArray.size()>=1){
			sName1.setText((String) sNameLabelsArray.get(0));
		}
		sName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName1.getText()));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName1, "cell 0 3,alignx left,aligny top");

		final JLabel sName2 = new JLabel();
		if(sNameLabelsArray.size()>=2){
			sName2.setText((String) sNameLabelsArray.get(1));
		}
		sName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName2.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName2, "cell 0 5,alignx left,aligny top");

		final JLabel sName3 = new JLabel();
		if(sNameLabelsArray.size()>=3){
			sName3.setText((String) sNameLabelsArray.get(2));
		}
		sName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName3.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName3, "cell 0 7,alignx left,aligny top");

		final JLabel sName4 = new JLabel();
		if(sNameLabelsArray.size()>=4){
			sName4.setText((String) sNameLabelsArray.get(3));
		}
		sName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName4.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName4, "cell 0 9,alignx left,aligny top");

		final JLabel sName5 = new JLabel();
		if(sNameLabelsArray.size()>=5){
			sName5.setText((String) sNameLabelsArray.get(4));
		}
		sName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName5.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName5, "cell 0 11,alignx left,aligny top");

		final JLabel sName6 = new JLabel();
		if(sNameLabelsArray.size()>=6){
			sName6.setText((String) sNameLabelsArray.get(5));
		}
		sName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName6.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName6, "cell 0 13,alignx left,aligny top");

		final JLabel sName7 = new JLabel();
		if(sNameLabelsArray.size()>=7){
			sName7.setText((String) sNameLabelsArray.get(6));
		}
		sName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName7.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName7, "cell 0 15,alignx left,aligny top");

		final JLabel sName8 = new JLabel();
		if(sNameLabelsArray.size()>=8){
			sName8.setText((String) sNameLabelsArray.get(7));
		}
		sName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName8.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName8, "cell 0 17,alignx left,aligny top");

		final JLabel sName9 = new JLabel();
		if(sNameLabelsArray.size()>=9){
			sName9.setText((String) sNameLabelsArray.get(8));
		}
		sName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName9.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName9, "cell 0 19,alignx left,aligny top");

		final JLabel sName10 = new JLabel();
		if(sNameLabelsArray.size()>=10){
			sName10.setText((String) sNameLabelsArray.get(9));
		}
		sName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName10.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName10, "cell 0 21,alignx left,aligny top");

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
				availableSADModelList.clear();

				String searchField="'"+searchTextField.getText()+"'";
				String query= "Select * from sisca_sad where sisca_sad_name ~*"+searchField;
				try {
					dbman= new DBManager();
					availableSAD= dbman.getFromDB(query);
					availableSAD= dbman.getAvailableSAD(availableSAD);
					for(int i=0; i<availableSAD.size(); i++){
						availableSADModelList.addElement(availableSAD.get(i));
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
				HKJ_SisCA_MainPage.frame.setContentPane(sadView());
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
				HKJ_SisCA_MainPage.frame.setContentPane(sadView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New SAD");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addSADView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(addNewButton);





		/////////////////////////////////////////////////////////
		//           Main Panel
		/////////////////////////////////////////////////////////

		// Configurations
		JPanel mainPanelAddSAD = new JPanel();
		mainPanelAddSAD.setLayout(new BorderLayout(0, 0));


		// Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelAddSAD.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelAddSAD.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAddSAD.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelAddSAD.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelAddSAD.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[854px]", "[37px][60px][61px][42.00px][65px]"));

		JLabel addSADLabelPanel = new JLabel("Add New SAD");
		addSADLabelPanel.setPreferredSize(new Dimension(100, 50));
		addSADLabelPanel.setHorizontalAlignment(SwingConstants.CENTER);
		addSADLabelPanel.setForeground(java.awt.Color.BLACK);
		addSADLabelPanel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		centerPanel.add(addSADLabelPanel, "cell 0 0,alignx center,growy");

		JPanel AddCancelPanel = new JPanel();
		AddCancelPanel.setBackground(new Color(250,250,250));
		centerPanel.add(AddCancelPanel, "cell 0 4,grow");
		AddCancelPanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));


		JPanel sadIDPanel = new JPanel();
		sadIDPanel.setBackground((java.awt.Color) null);
		centerPanel.add(sadIDPanel, "cell 0 1,growx,aligny top");
		sadIDPanel.setLayout(new MigLayout("", "[57.00px][371.00px]", "[28px]"));

		JLabel idLabel = new JLabel("SAD ID: ");
		sadIDPanel.add(idLabel, "cell 0 0,alignx left,aligny center");
		idLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		final JTextField textFieldSADName = new JTextField();
		textFieldSADName.setPreferredSize(new Dimension(200, 100));
		sadIDPanel.add(textFieldSADName, "cell 1 0,grow");
		textFieldSADName.setColumns(10);

		JPanel directionPanel = new JPanel();
		directionPanel.setBackground((java.awt.Color) null);
		centerPanel.add(directionPanel, "cell 0 2,grow");
		directionPanel.setLayout(new MigLayout("", "[][][35][70.00][35][70]", "[]"));

		JLabel directionLabel = new JLabel("Direction: ");
		directionPanel.add(directionLabel, "cell 0 0");
		directionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		final JComboBox directionComboBox = new JComboBox();
		directionComboBox.setModel(new DefaultComboBoxModel(new String[] {"entry", "exit"}));
		directionComboBox.setBounds(92, 94, 285, 27);
		directionPanel.add(directionComboBox, "cell 1 0");
	

		JButton addSADBtn = new JButton("Add SAD");
		addSADBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//////////////////////////////////////////////////////
				// Query for Add Values of the new SAD in the DB
				//////////////////////////////////////////////////////
				
				String sadNameFromTextField= "'"+textFieldSADName.getText()+"'";
				String sadDirectionFromComboBox= "'"+directionComboBox.getSelectedItem()+"'";
		
				System.out.println("SAD Name:"+sadNameFromTextField+" Direction:"+sadDirectionFromComboBox );
				
				String query= "Insert into sisca_sad (sisca_sad_name,sisca_sad_direction,sisca_sad_active) VALUES ("+sadNameFromTextField+","+sadDirectionFromComboBox+",'false')";
				try {
					dbman.insertDB(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(textFieldSADName.getText()));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(addSADBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Cancel me hace un BACK en el history
				HKJ_SisCA_MainPage.frame.setContentPane(sadView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(cancelBtn, "cell 26 0");


		//		JPanel parkingPanel = new JPanel();
		//		parkingPanel.setBackground((java.awt.Color) null);
		//		centerPanel.add(parkingPanel, "cell 0 3,grow");
		//		parkingPanel.setLayout(new MigLayout("", "[59.00px][343.00px,grow][118.00][-79.00px][161px]", "[21px][29px]"));
		//
		//		JLabel parkingInfoLabel = new JLabel("Parking:");
		//		parkingInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//		parkingInfoLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		//		parkingPanel.add(parkingInfoLabel, "cell 0 0,alignx left,growy");
		//
		//		JComboBox comboBox = new JComboBox();
		//		parkingPanel.add(comboBox, "cell 1 0,growx,aligny center");


		////////////////////////////////////////////////////////
		//           Window Panel
		/////////////////////////////////////////////////////////

		// Configurations
		JPanel windowPanelAddSAD= new JPanel();
		windowPanelAddSAD.setLayout(new BorderLayout(0, 0));
		windowPanelAddSAD.add(menuPanelAddSAD, BorderLayout.NORTH);
		windowPanelAddSAD.add(leftPanelSADInformation, BorderLayout.WEST);
		windowPanelAddSAD.add(mainPanelAddSAD, BorderLayout.CENTER);

		return windowPanelAddSAD;

	}

	//////////////////////////////////////////////////////////////////
	// Edit SAD View								               	//
	//////////////////////////////////////////////////////////////////

	private static JPanel editSADView( String sName, String direction, String active){
		
		String sadEditName= sName;
		String sadEditDirection= direction;
		String sadEditActive= active;
		
		/////////////////////////////////////////////////////////
		//           Menu Panel
		/////////////////////////////////////////////////////////

		// Configurations 
		JPanel menuPanelEditSAD = new JPanel();
		menuPanelEditSAD.setBackground(new Color(255,239,80));
		menuPanelEditSAD.setPreferredSize(new Dimension(10, 30));
		menuPanelEditSAD.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(375, 10));
		menuPanelEditSAD.add(menuOptionsPanel, BorderLayout.WEST);
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

		JLabel SADManagerLabel = new JLabel("SADs");
		SADManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		SADManagerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		SADManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SADManagerLabel.setForeground((java.awt.Color) null);
		SADManagerLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		SADManagerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		SADManagerLabel.setBounds(66, -11, 84, 53);
		menuOptionsPanel.add(SADManagerLabel);

		JLabel editSADLabel = new JLabel("Edit SAD");
		editSADLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(addSADView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		editSADLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		editSADLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editSADLabel.setForeground((java.awt.Color) null);
		editSADLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		editSADLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		editSADLabel.setBounds(148, -11, 111, 53);
		menuOptionsPanel.add(editSADLabel);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelEditSAD.add(logOutPanel, BorderLayout.EAST);
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
		menuPanelEditSAD.add(userNamePanel, BorderLayout.CENTER);
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
		JPanel leftPanelSADInformation = new JPanel();
		leftPanelSADInformation.setBackground(new Color(245,245,245));
		leftPanelSADInformation.setPreferredSize(new Dimension(390, 10));
		leftPanelSADInformation.setLayout(new BorderLayout(0, 0));

		JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanelSADInformation.add(scrollPane);

		JLayeredPane liveSystemPanel21 = new JLayeredPane();
		scrollPane.setViewportView(liveSystemPanel);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel21.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));

		//SAD Name Labels & MouseCliked Event 

		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px]"));







		//LeftPanel Labels
		JLabel LSystemLabel = new JLabel("SAD LIST");
		liveSystemPanel.add(LSystemLabel, "cell 0 0,alignx center,aligny top");
		LSystemLabel.setPreferredSize(new Dimension(100, 50));
		LSystemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LSystemLabel.setForeground(java.awt.Color.BLACK);
		LSystemLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));


		////////////////////////////////////////////////////////////////////////////////////////////////
		//Query for get Parking Names for the Labels
		////////////////////////////////////////////////////////////////////////////////////
		ArrayList sNameLabelsArray = new ArrayList() ;
		try {
			dbman= new DBManager();

			sNameLabelsArray=dbman.getFromDB("Select * from sisca_sad ORDER BY sisca_sad_name");
			//System.out.println("Before Test:"+pNameLabelsArray);
			sNameLabelsArray= dbman.getAvailableSADOnly(sNameLabelsArray);


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





		final JLabel sName1 = new JLabel();
		if(sNameLabelsArray.size()>=1){
			sName1.setText((String) sNameLabelsArray.get(0));
		}
		sName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName1.getText()));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName1, "cell 0 3,alignx left,aligny top");

		final JLabel sName2 = new JLabel();
		if(sNameLabelsArray.size()>=2){
			sName2.setText((String) sNameLabelsArray.get(1));
		}
		sName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName2.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName2, "cell 0 5,alignx left,aligny top");

		final JLabel sName3 = new JLabel();
		if(sNameLabelsArray.size()>=3){
			sName3.setText((String) sNameLabelsArray.get(2));
		}
		sName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName3.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName3, "cell 0 7,alignx left,aligny top");

		final JLabel sName4 = new JLabel();
		if(sNameLabelsArray.size()>=4){
			sName4.setText((String) sNameLabelsArray.get(3));
		}
		sName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName4.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName4, "cell 0 9,alignx left,aligny top");

		final JLabel sName5 = new JLabel();
		if(sNameLabelsArray.size()>=5){
			sName5.setText((String) sNameLabelsArray.get(4));
		}
		sName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName5.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName5, "cell 0 11,alignx left,aligny top");

		final JLabel sName6 = new JLabel();
		if(sNameLabelsArray.size()>=6){
			sName6.setText((String) sNameLabelsArray.get(5));
		}
		sName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName6.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName6, "cell 0 13,alignx left,aligny top");

		final JLabel sName7 = new JLabel();
		if(sNameLabelsArray.size()>=7){
			sName7.setText((String) sNameLabelsArray.get(6));
		}
		sName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName7.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName7, "cell 0 15,alignx left,aligny top");

		final JLabel sName8 = new JLabel();
		if(sNameLabelsArray.size()>=8){
			sName8.setText((String) sNameLabelsArray.get(7));
		}
		sName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName8.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName8, "cell 0 17,alignx left,aligny top");

		final JLabel sName9 = new JLabel();
		if(sNameLabelsArray.size()>=9){
			sName9.setText((String) sNameLabelsArray.get(8));
		}
		sName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName9.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName9, "cell 0 19,alignx left,aligny top");

		final JLabel sName10 = new JLabel();
		if(sNameLabelsArray.size()>=10){
			sName10.setText((String) sNameLabelsArray.get(9));
		}
		sName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(sName10.getText()));
				HKJ_SisCA_MainPage.frame.pack(); HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName10, "cell 0 21,alignx left,aligny top");
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
				availableSADModelList.clear();

				String searchField="'"+searchTextField.getText()+"'";
				String query= "Select * from sisca_sad where sisca_sad_name ~*"+searchField;
				try {
					dbman= new DBManager();
					availableSAD= dbman.getFromDB(query);
					availableSAD= dbman.getAvailableSAD(availableSAD);
					for(int i=0; i<availableSAD.size(); i++){
						availableSADModelList.addElement(availableSAD.get(i));
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
				HKJ_SisCA_MainPage.frame.setContentPane(sadView());
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
				HKJ_SisCA_MainPage.frame.setContentPane(sadView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New SAD");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(addSADView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(addNewButton);




		/////////////////////////////////////////////////////////
		//           Main Panel
		/////////////////////////////////////////////////////////

		// Configurations
		JPanel mainPanelEditSAD = new JPanel();
		mainPanelEditSAD.setLayout(new BorderLayout(0, 0));


		// Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanelEditSAD.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanelEditSAD.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelEditSAD.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanelEditSAD.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanelEditSAD.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[854px]", "[37px][60px][61px][42.00px][65px]"));

		JLabel editSADLabelPanel = new JLabel("Edit SAD");
		editSADLabelPanel.setPreferredSize(new Dimension(100, 50));
		editSADLabelPanel.setHorizontalAlignment(SwingConstants.CENTER);
		editSADLabelPanel.setForeground(java.awt.Color.BLACK);
		editSADLabelPanel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		centerPanel.add(editSADLabelPanel, "cell 0 0,alignx center,growy");

		JPanel editCancelPanel = new JPanel();
		editCancelPanel.setBackground(new Color(250,250,250));
		centerPanel.add(editCancelPanel, "cell 0 4,grow");
		editCancelPanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));



		JPanel sadIDPanel = new JPanel();
		sadIDPanel.setBackground((java.awt.Color) null);
		centerPanel.add(sadIDPanel, "cell 0 1,growx,aligny top");
		sadIDPanel.setLayout(new MigLayout("", "[57.00px][371.00px]", "[28px]"));

		JLabel idLabel = new JLabel("SAD ID: ");
		sadIDPanel.add(idLabel, "cell 0 0,alignx left,aligny center");
		idLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		final JTextField textFieldSADName = new JTextField(sadEditName);
		textFieldSADName.setPreferredSize(new Dimension(200, 100));
		sadIDPanel.add(textFieldSADName, "cell 1 0,grow");
		textFieldSADName.setColumns(10);

		JPanel directionPanel = new JPanel();
		directionPanel.setBackground((java.awt.Color) null);
		centerPanel.add(directionPanel, "cell 0 2,grow");
		directionPanel.setLayout(new MigLayout("", "[][][35][70.00][35][70]", "[]"));

		JLabel directionLabel = new JLabel("Direction: ");
		directionPanel.add(directionLabel, "cell 0 0");
		directionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Entry", "Exit"}));
		comboBox.setBounds(92, 94, 285, 27);
		System.out.println(sadEditDirection);
		//comboBox.setEditable(true);
		comboBox.setSelectedItem(sadEditDirection);
		directionPanel.add(comboBox, "cell 1 0");

		JButton editSADBtn = new JButton("Edit SAD");
		editSADBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Este add me lleva a ver la iformaci—n del SAD recien a–adido
				HKJ_SisCA_MainPage.frame.setContentPane(sadInformationView(textFieldSADName.getText()));
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		editCancelPanel.add(editSADBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Cancel me hace un BACK en el history
				HKJ_SisCA_MainPage.frame.setContentPane(sadView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});

		editCancelPanel.add(cancelBtn, "cell 26 0");

		//		JPanel parkingPanel = new JPanel();
		//		parkingPanel.setBackground((java.awt.Color) null);
		//		centerPanel.add(parkingPanel, "cell 0 3,grow");
		//		parkingPanel.setLayout(new MigLayout("", "[59.00px][343.00px,grow][118.00][-79.00px][161px]", "[21px][29px]"));
		//
		//		JLabel parkingInfoLabel = new JLabel("Parking:");
		//		parkingInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//		parkingInfoLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		//		parkingPanel.add(parkingInfoLabel, "cell 0 0,alignx left,growy");
		//
		//		JComboBox comboBox = new JComboBox();
		//		parkingPanel.add(comboBox, "cell 1 0,growx,aligny center");


		////////////////////////////////////////////////////////
		//           Window Panel
		/////////////////////////////////////////////////////////

		// Configurations
		JPanel windowPanelEditSAD= new JPanel();
		windowPanelEditSAD.setLayout(new BorderLayout(0, 0));
		windowPanelEditSAD.add(menuPanelEditSAD, BorderLayout.NORTH);
		windowPanelEditSAD.add(leftPanelSADInformation, BorderLayout.WEST);
		windowPanelEditSAD.add(mainPanelEditSAD, BorderLayout.CENTER);

		return windowPanelEditSAD;
	}
}
