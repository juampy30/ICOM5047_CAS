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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import databases.DBManager;
import net.miginfocom.swing.MigLayout;


/** LIVE SYSTEM MANAGER
 * 	Manage Live System View 
 *  @author Juan Pablo Bermœdez Reyes
 *  Last Modified: April 6, 2014
 */
public class LiveSystemManager {
	
	/**
	 * Fields
	 */
	private static String currentCapacity;
	private static String currentParking;
	private static JList parkingList;

	
	/**
	 * Constructor
	 */
	LiveSystemManager(){
		
	}
	
	
	
	/** Live System View
	 *  Generates the Live System View JPanel 
	 *  @return windowPanelLiveSystem JPanel 
	 */
	static JPanel liveSystemView(){
		JPanel windowPanelLiveSystem= new JPanel();
		JPanel menuPanelLiveSystem = new JPanel();
		JPanel mainPanelLiveSystem = new JPanel();
		JTextField textFieldUsername;
		JTextField textFieldPassword;
		final JTextField textFieldSearch;
		

		/////////////////////////////////////////////////////////
		//           Menu Panel
		/////////////////////////////////////////////////////////

		// Configurations 
		menuPanelLiveSystem = new JPanel();
		menuPanelLiveSystem.setBackground(new Color(255,239,80));
		menuPanelLiveSystem.setPreferredSize(new Dimension(10, 30));
		menuPanelLiveSystem.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(250, 10));
		menuPanelLiveSystem.add(menuOptionsPanel, BorderLayout.WEST);
		menuOptionsPanel.setLayout(null);

		JLabel homeLabel = new JLabel(" Home");
		homeLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		homeLabel.setBounds(0, -11, 68, 53);
		menuOptionsPanel.add(homeLabel);
		homeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(HKJ_SisCA_MainPage.windowPanel);
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		homeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homeLabel.setForeground((java.awt.Color) null);
		homeLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblLiveSystem = new JLabel("Live System");
		lblLiveSystem.setOpaque(true);
		lblLiveSystem.setBackground(new Color (220,220,220));
		lblLiveSystem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLiveSystem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(liveSystemView());
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		lblLiveSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblLiveSystem.setForeground((Color) null);
		lblLiveSystem.setFont(new Font("Dialog", Font.BOLD, 14));
		lblLiveSystem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblLiveSystem.setBounds(66, -11, 162, 53);
		menuOptionsPanel.add(lblLiveSystem);

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanelLiveSystem.add(logOutPanel, BorderLayout.EAST);
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
		menuPanelLiveSystem.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel(HKJ_SisCA_MainPage.getActiveUsername());
		userNamePanel.add(userNameLabel, BorderLayout.EAST);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground((java.awt.Color) null);
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));


		/////////////////////////////////////////////////////////
		// Main Panel
		/////////////////////////////////////////////////////////

		mainPanelLiveSystem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		windowPanelLiveSystem.add(mainPanelLiveSystem, BorderLayout.CENTER);
		mainPanelLiveSystem.setBackground(new Color(250,250,250));

		mainPanelLiveSystem.setLayout(new MigLayout("", "[67.00][410.00][][629.00]", "[105.00][544.00]"));

		JPanel panelSearch = new JPanel();
		panelSearch.setBackground(new Color(250,250,250));
		mainPanelLiveSystem.add(panelSearch, "cell 1 0,alignx center,aligny bottom");
		panelSearch.setLayout(new MigLayout("", "[][254.00][44.00]", "[][]"));

		JLabel lblNewLabel = new JLabel("Search:");
		panelSearch.add(lblNewLabel, "cell 0 1");

		textFieldSearch = new JTextField();
		textFieldSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String parkingName= textFieldSearch.getText();
				DefaultListModel parkingModelList= new DefaultListModel();
				
				ArrayList<Object> availableParking= new ArrayList<Object>();
				DBManager dbman2;
				String query= "Select * from sisca_parking where sisca_parking_name ~*'"+parkingName+"'";
				try {
					dbman2= new DBManager();
					availableParking=dbman2.getFromDB(query);
			
					availableParking= dbman2.getAvailableParkingNamesFromDB(availableParking);
					
					for (int i=0; i<availableParking.size();i++){
						parkingModelList.addElement(availableParking.get(i));
						
					}
					parkingList.setModel(parkingModelList);
					
					
					
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
			}
		});
		
		textFieldSearch.setPreferredSize(new Dimension(40, 28));
		panelSearch.add(textFieldSearch, "cell 1 1,growx,aligny bottom");
		textFieldSearch.setColumns(10);

		JButton goButton = new JButton("Go");
		goButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String parkingName= textFieldSearch.getText();
				DefaultListModel parkingModelList= new DefaultListModel();
				
				ArrayList<Object> availableParking= new ArrayList<Object>();
				DBManager dbman2;
				String query= "Select * from sisca_parking where sisca_parking_name ~*'"+parkingName+"'";
				try {
					dbman2= new DBManager();
					availableParking=dbman2.getFromDB(query);
			
					availableParking= dbman2.getAvailableParkingNamesFromDB(availableParking);
					
					for (int i=0; i<availableParking.size();i++){
						parkingModelList.addElement(availableParking.get(i));
						
					}
					parkingList.setModel(parkingModelList);
					
					
					
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
			}
		});
		panelSearch.add(goButton, "cell 2 1");

		JPanel listPanel = new JPanel();
		listPanel.setBackground(new Color(250,250,250));
		listPanel.setPreferredSize(new Dimension(500, 800));
		mainPanelLiveSystem.add(listPanel, "cell 1 1,growx");
		listPanel.setLayout(new MigLayout("", "[375px]", "[534px]"));

		JScrollPane scrollPane = new JScrollPane();
		listPanel.add(scrollPane, "cell 0 0,grow");
		JPanel panelActions = new JPanel();
		panelActions.setBackground(new Color(240,240,240));
		panelActions.setPreferredSize(new Dimension(800, 500));
		panelActions.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mainPanelLiveSystem.add(panelActions, "cell 3 1,alignx center");
		panelActions.setLayout(new MigLayout("", "[624.00,grow][]", "[][][][][][][][][56.00][][][][][]"));
		

		JLabel lblCapacity = new JLabel("Capacity\n");
		lblCapacity.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		panelActions.add(lblCapacity, "cell 0 1,alignx center");

		final JLabel labelCapacity = new JLabel("Quantity");
		labelCapacity.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panelActions.add(labelCapacity, "cell 0 2,alignx center");

		JLabel lblAbailableLots = new JLabel("Abailable Lots");
		lblAbailableLots.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		panelActions.add(lblAbailableLots, "cell 0 4,alignx center");

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panelActions.add(lblQuantity, "cell 0 5,alignx center");

		JLabel lblEntryGate = new JLabel("Entry Gate");
		lblEntryGate.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		panelActions.add(lblEntryGate, "cell 0 8,alignx center");

		JPanel entryGatePanel = new JPanel();
		entryGatePanel.setBackground(new Color(240,240,240));
		panelActions.add(entryGatePanel, "cell 0 9,alignx center,growy");
		entryGatePanel.setLayout(new MigLayout("", "[][]", "[]"));

		JButton openEntryGateButton = new JButton("Open");
		openEntryGateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");
				if(sure==0){
					// Open gate
				}
			}
		});
		entryGatePanel.add(openEntryGateButton, "cell 0 0");

		JButton closeEntryGateButton = new JButton("Close");
		closeEntryGateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");
				if(sure==0){
					// Open gate
				}
			}
		});
		entryGatePanel.add(closeEntryGateButton, "cell 1 0");

		JLabel lblExitGate = new JLabel("Exit Gate");
		lblExitGate.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		panelActions.add(lblExitGate, "cell 0 10,alignx center");

		JPanel exitGatePanel = new JPanel();
		exitGatePanel.setBackground(new Color(240,240,240));
		panelActions.add(exitGatePanel, "cell 0 11,alignx center,growy");
		exitGatePanel.setLayout(new MigLayout("", "[][]", "[]"));

		JButton openExitGateButton = new JButton("Open");
		openExitGateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");
				if(sure==0){
					// TODO Open gate
				}
			}
		});
		exitGatePanel.add(openExitGateButton, "cell 0 0");

		JButton closeExitGateButton = new JButton("Close");
		closeExitGateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");
				if(sure==0){
					// TODO Open gate
				}
			}
		});
		exitGatePanel.add(closeExitGateButton, "cell 1 0");

		JButton btnOpenAllGates = new JButton("Open All Gates");
		btnOpenAllGates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");
				if(sure==0){
					// TODO Open gate
				}
			}
		});
		btnOpenAllGates.setBackground(Color.BLACK);
		panelActions.add(btnOpenAllGates, "cell 0 16,alignx center");
		
	

		/////////////////////////////////////////////
		// Query for fill the JList
		/////////////////////////////////////////
		
		DefaultListModel parkingModelList= new DefaultListModel();
		ArrayList<Object> availableParking= new ArrayList<Object>();
		final DBManager dbman;
		
		String query= "Select * from sisca_parking where sisca_parking_active='true'";
		try {
			dbman= new DBManager();
			availableParking=dbman.getFromDB(query);
			availableParking= dbman.getAvailableParkingNamesFromDB(availableParking);
			currentParking= (String) availableParking.get(0);
			
			for (int i=0; i<availableParking.size();i++){
				parkingModelList.addElement(availableParking.get(i));
				
			}
			
			availableParking=dbman.getFromDB(query);
			availableParking= dbman.getAvailableParkingCapacityFromDB(availableParking);
			currentCapacity= (String) availableParking.get(0);
			
			
			
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
		
		
		labelCapacity.setText(currentCapacity);
		
		
		parkingList = new JList();
		parkingList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getClickCount()==2){
					
					currentParking=(String) parkingList.getSelectedValue();
					ArrayList<Object> availableParking= new ArrayList<Object>();
					DBManager dbman2;
					String query= "Select * from sisca_parking where sisca_parking_name='"+currentParking+"'";
					try {
						dbman2= new DBManager();
						availableParking=dbman2.getFromDB(query);
				
						availableParking=dbman2.getFromDB(query);
						availableParking= dbman2.getAvailableParkingCapacityFromDB(availableParking);
						currentCapacity= (String) availableParking.get(0);
						labelCapacity.setText(currentCapacity);
						
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
					
					
				}
			}

		});
		parkingList.setModel(parkingModelList);
		scrollPane.setViewportView(parkingList);

		
		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		windowPanelLiveSystem.setLayout(new BorderLayout(0, 0));
		windowPanelLiveSystem.add(menuPanelLiveSystem, BorderLayout.NORTH);
		windowPanelLiveSystem.add(mainPanelLiveSystem, BorderLayout.CENTER);
		
		return windowPanelLiveSystem;
	}

}
