import hkj.sisca.auxiliary.Tag;
import hkj.sisca.cas.communication.manager.CommunicationManagerCAS;
import hkj.sisca.cas.communication.manager.CommunicationManagerCAS.TagListUpdateContainer;
import hkj.sisca.cas.communication.manager.CommunicationManagerCAS.TagUpdateListName;
import hkj.sisca.cas.communication.manager.CommunicationManagerCAS.TagUpdateType;
import hkj.sisca.cas.communication.manager.CommunicationManagerConstants.ClientType;

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
import java.net.Socket;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.mail.MessagingException;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;
import databases.DBManager;

/** HKJ_SisCA_MainPage
 * 	Manage Home View and StandBy View
 *  @author Juan Pablo Bermœdez Reyes
 *  Last Modified: April 6, 2014
 */
/**
 * @author JuanPablo
 *
 */
public class HKJ_SisCA_MainPage {

	static ParkingManager pmanager;

	public static class CMRunnable implements Runnable {

		private boolean isAlive;


		public CMRunnable() {
			isAlive = true;
		}

		@Override
		public void run() {
			while (isAlive) {
				
				System.out.println("Waiting for communication...");
				boolean hasCommunicationArrived = false;
				do {
					try {
						System.out.println("CMThread: HasComm: Trying to acquire semaphore...");
						semaphore.acquire();
						System.out.println("CMThread: HasComm: Acquired semaphore...");
						hasCommunicationArrived = cmcas.hasCommunicationArrived();
						semaphore.release();
						System.out.println("CMThread: HasComm: Released semaphore...");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				while (!hasCommunicationArrived);
			
				Tag receivedTag = null;
				try {
					System.out.println("CMThread: ManageTag: Trying to acquire semaphore...");
					semaphore.acquire();
					System.out.println("CMThread: ManageTag: Acquired semaphore...");
					receivedTag = cmcas.manageReceivedTagInfo();
					semaphore.release();
					System.out.println("CMThread: ManageTag: Released semaphore...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(receivedTag);
				System.out.println("Last connected SAD ID: " + cmcas.getLastConnectedSADID());

				String parkingQueryString = "Select sisca_sad_name, sisca_sad_direction, sisca_parking_name "
						+ "from (sisca_sad natural join sisca_sad_parking_list) natural join sisca_parking "
						+ "where sisca_sad_active='true' " 
						+ "and sisca_sad_parking_list.sisca_sad_id=sisca_sad.sisca_sad_id " 
						+ "and sisca_sad_parking_active='true' "
						+ "and sisca_parking.sisca_parking_id= sisca_sad_parking_list.sisca_parking_id "
						+ "and sisca_sad_name='" + cmcas.getLastConnectedSADID() + "';";

				boolean isSendingSADEntry = true;

				ArrayList<Object> parkingQuery = new ArrayList<Object>();
				try {
					parkingQuery = dbman.getNotificationsInformation(parkingQueryString);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				String parkingName = (String) ((List<Object>) parkingQuery.get(0)).get(2);
				if (((String) ((List<Object>) parkingQuery.get(0)).get(1)).equals("exit"))
					isSendingSADEntry = false;
				System.out.println("Parking name: " + parkingName);

				String sadListQueryString = "Select sisca_sad_name, sisca_sad_direction, sisca_parking_name "
						+ "from (sisca_sad natural join sisca_sad_parking_list) natural join sisca_parking "
						+ "where sisca_sad_active='true' " 
						+ "and sisca_sad_parking_list.sisca_sad_id=sisca_sad.sisca_sad_id " 
						+ "and sisca_sad_parking_active='true' "
						+ "and sisca_parking.sisca_parking_id= sisca_sad_parking_list.sisca_parking_id "
						+ "and sisca_parking_name~*'" + parkingName + "';";

				ArrayList<Object> sadListQuery = new ArrayList<Object>();
				try {
					sadListQuery = dbman.getNotificationsInformation(sadListQueryString);
				} catch (SQLException e) {}

				System.out.println(sadListQuery.size());
				for (int i = 0; i < sadListQuery.size(); i++) {
					String sadID = (String) ((List<Object>) sadListQuery.get(i)).get(0);
					if (isSendingSADEntry) {
						if (((String) ((List<Object>) sadListQuery.get(i)).get(1)).equals("exit")) {
							TagListUpdateContainer container = new TagListUpdateContainer(receivedTag, TagUpdateType.AddUpdate, TagUpdateListName.EntryTagsList);
							System.out.println("Adding to SAD " + sadID + " as exit at parking " + parkingName);
							cmcas.sendTagListUpdate(sadID, container);
						}
					}
					else {
						if (((String) ((List<Object>) sadListQuery.get(i)).get(1)).equals("entry")) {
							TagListUpdateContainer container = new TagListUpdateContainer(receivedTag, TagUpdateType.RemoveUpdate, TagUpdateListName.EntryTagsList);
							System.out.println("Removing to SAD " + sadID + " as entry at parking " + parkingName);
							cmcas.sendTagListUpdate(sadID, container);
						}
					}
				}
			}

		}

	}

	public static class NMRunnable implements Runnable {

		private Date tempDate;
		private boolean isAlive;

		public NMRunnable() {
			this.tempDate = new Date(System.currentTimeMillis());
			this.isAlive = false;
		}

		@Override
		public void run() {

			while (isAlive) {
				Date currentTime = new Date(System.currentTimeMillis());
				if (currentTime.after(tempDate)) {
					tempDate = currentTime;
					notificationsManager.setNotifications();
					try {
						notificationsManager.sendNotifications();
					} catch (MessagingException e) {
						System.out.println("Error sending notifications.");
						e.printStackTrace();
					}
				}
				else {
					try {
						// Sleep for one hour
						Thread.sleep(3600000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}


	}


	/**
	 * Fields
	 */
	static JFrame frame;
	static JPanel menuPanel;
	static JPanel leftPanel;
	static JPanel mainPanel;
	static JPanel windowPanel;
	static String username;
	private static DBManager dbman;
	private ArrayList<Object> availableSAD;
	private ArrayList<Object> availableAtzType;

	private DefaultListModel availableSADModelList;
	private DefaultListModel availableAtzTypesModelList;
	private DefaultListModel chosenSADModelList;
	private DefaultListModel chosenAtzTypesModelList;

	static Socket clientSocket;
	public static CommunicationManagerCAS cmcas;
	public static Semaphore semaphore = new Semaphore(1);

	private static NotificationManager notificationsManager;

	static String loggedUsernane;
	static String loggedUsernaneWith;
	static Boolean canView;

	private static AccountManager accountManager= new AccountManager();
	static AuthorizationTypeManager authorizationTypeManager = new AuthorizationTypeManager();
	private static ParkingManager parkingManager= new ParkingManager();

	private ArrayList<Object> registerParkings;

	private String[] availableSadList;
	private String[] availableAtzTypeList;

	private DefaultListModel registerParkingsList;


	// HKJ_SisCA_MainPage Constructor
	/**
	 * Constructor
	 */
	public HKJ_SisCA_MainPage(){

	}

	/** Method to set the Active Username
	 * @param username Variable that store the recent logged user
	 */
	static void setActiveUsername(String username){


		String query= "Select sisca_account_first_name, sisca_account_last_name from sisca_account where sisca_account_username="+username;
		String result=null;

		try {
			dbman= new DBManager();
			result= dbman.getUserRealNameFromDB(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loggedUsernaneWith=username;


		System.out.println("Real Name:"+result);
		loggedUsernane= result+"\t\t";
	}

	/** Method to get Actice Username
	 * @return loggedUsername variables that store the recent logged user
	 */
	public static String getActiveUsername(){
		return loggedUsernane;
	}

	/** Method that decides if a user can view a page or not
	 * @return canView variable that defines if a user can view or not a page
	 */
	public static Boolean getCanView(){

		Boolean canView= false;
		String query= "Select * from sisca_account where sisca_account_username="+loggedUsernaneWith;
		String result=null;

		try {
			dbman= new DBManager();
			result= dbman.getAccountTypeFromDB(query);
			result.toUpperCase();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(loggedUsernaneWith+"Result Account Type"+ result);
		System.out.println("result==ADMINISTRATOR"+ (result=="ADMINISTRATOR"));

		if(result.toUpperCase().equals("ADMINISTRATOR")){
			canView= true;
		}


		return canView;
	}


	/**
	 * Initialize the Main Frame
	 */
	static void initializae() {
		frame= new JFrame();

		frame.getContentPane().add(LogInManager.standByView());
		frame.setResizable(true);
		frame.setTitle("SisCA");
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	/** Home View
	 *  Generates the Home View JPanel 
	 *  @return windowPanel JPanel 
	 */
	public static JPanel homeView(){

		pmanager= new ParkingManager();

		System.out.println("Can View?"+getCanView());

		System.out.println("Active User:"+loggedUsernane);
		// Image Icons
		ImageIcon img1=  new ImageIcon("/Users/JuanPablo/git/ICOM5047_CAS/Icons/i1.png");
		ImageIcon img2=  new ImageIcon("/Users/JuanPablo/git/ICOM5047_CAS/Icons/i2.png");
		ImageIcon img3=  new ImageIcon("/Users/JuanPablo/git/ICOM5047_CAS/Icons/i3.png");
		ImageIcon img4=  new ImageIcon("/Users/JuanPablo/git/ICOM5047_CAS/Icons/i4.png");
		ImageIcon img5=  new ImageIcon("/Users/JuanPablo/git/ICOM5047_CAS/Icons/i5.png");
		ImageIcon img6=  new ImageIcon("/Users/JuanPablo/git/ICOM5047_CAS/Icons/i6.png");


		/////////////////////////////////////////////////////////
		//           Menu Panel
		/////////////////////////////////////////////////////////

		// Configurations 
		menuPanel = new JPanel();
		menuPanel.setBackground(new Color(255,239,80));
		menuPanel.setPreferredSize(new Dimension(10, 30));
		menuPanel.setLayout(new BorderLayout(0, 0));

		JPanel menuOptionsPanel = new JPanel();
		menuOptionsPanel.setPreferredSize(new Dimension(250, 10));
		menuPanel.add(menuOptionsPanel, BorderLayout.WEST);
		menuOptionsPanel.setLayout(null);

		JLabel homeLabel = new JLabel(" Home");
		homeLabel.setOpaque(true);
		homeLabel.setBackground(new Color (220,220,220));
		homeLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		homeLabel.setBounds(0, -11, 68, 53);
		menuOptionsPanel.add(homeLabel);
		homeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(windowPanel);
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		homeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homeLabel.setForeground((java.awt.Color) null);
		homeLabel.setFont(new Font("Dialog", Font.BOLD, 14));

		JPanel logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(75, 10));
		menuPanel.add(logOutPanel, BorderLayout.EAST);
		logOutPanel.setLayout(null);

		JLabel logOutLabel = new JLabel("LogOut");
		logOutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				username=null;
				frame.setContentPane(LogInManager.standByView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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
		menuPanel.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));

		JLabel userNameLabel = new JLabel(loggedUsernane);
		userNamePanel.add(userNameLabel, BorderLayout.EAST);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground((java.awt.Color) null);
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));

		/////////////////////////////////////////////////////////
		//           Left Panel
		/////////////////////////////////////////////////////////

		// Configurations 
		JPanel leftPanelParkingInformation = new JPanel();
		leftPanelParkingInformation.setBackground(new Color(245,245,245));
		leftPanelParkingInformation.setPreferredSize(new Dimension(390, 10));
		leftPanelParkingInformation.setLayout(new BorderLayout(0, 0));

		final JPanel liveSystemPanel = new JPanel();
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

		/**
		 * Get the parking names to fill the left labels (Parking Information View)		
		 */
		ArrayList<Object> parkingNameListView = getParkingList();		
		int position = 1;
		for(int i=0; i<parkingNameListView.size() && i<10 ; i++){
			position = position+2;
			final JLabel userName = new JLabel(parkingNameListView.get(i).toString().toUpperCase());
			userName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					HKJ_SisCA_MainPage.frame.setContentPane(pmanager.parkingInformationView(userName.getText()));
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
		panel_100.add(searchTextField);
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = searchTextField.getText();

				HKJ_SisCA_MainPage.frame.setContentPane(pmanager.parkingView(text));
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
				HKJ_SisCA_MainPage.frame.setContentPane(pmanager.parkingView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New Parking");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (HKJ_SisCA_MainPage.getCanView()==true){
					HKJ_SisCA_MainPage.frame.setContentPane(pmanager.addParkingView());
					HKJ_SisCA_MainPage.frame.pack(); 
					HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
				else{
					JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "You have no permission to Add New Parking");
				}

			}
		});
		viewAndAddBynPanel.add(addNewButton);		


















		/////////////////////////////////////////////////////////
		//           Main Panel
		/////////////////////////////////////////////////////////

		// Configurations
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));


		// Main Panel Layout Division
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(new Color(247,247,247));
		mainPanel.add(northPanel, BorderLayout.NORTH);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(10, 100));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.setBackground(new Color(247,247,247));
		mainPanel.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(0, 10));
		mainPanel.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(0, 10));
		mainPanel.add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(250,250,250));
		centerPanel.setPreferredSize(new Dimension(1000, 1000));
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setLayout(new MigLayout("", "[500][500][500]", "[17.00][][100px][]"));

		// Icons
		JLabel parkingManagerIcon = new JLabel("");
		parkingManagerIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingManager.parkingView());
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		parkingManagerIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		centerPanel.add(parkingManagerIcon, "cell 0 2,alignx center");
		parkingManagerIcon.setPreferredSize(new Dimension(10,10));
		parkingManagerIcon.setBounds(161, 96, 141, 188);
		parkingManagerIcon.setIcon(img1);

		JLabel sadManagerIcon = new JLabel("");
		sadManagerIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(SADManager.sadView());
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sadManagerIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		centerPanel.add(sadManagerIcon, "flowx,cell 1 2,alignx center");
		sadManagerIcon.setPreferredSize(new Dimension(10,10));
		sadManagerIcon.setBounds(161, 96, 141, 188);
		sadManagerIcon.setIcon(img2);

		JLabel permissionManagerIcon = new JLabel("");
		permissionManagerIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(PermissionManager.permissionView());
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		permissionManagerIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		centerPanel.add(permissionManagerIcon, "cell 2 2,alignx center");
		permissionManagerIcon.setPreferredSize(new Dimension(10,10));
		permissionManagerIcon.setBounds(161, 96, 141, 188);
		permissionManagerIcon.setIcon(img3);

		JLabel userAccontSettingsIcon = new JLabel("");
		userAccontSettingsIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountManager.accountView());
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		userAccontSettingsIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		centerPanel.add(userAccontSettingsIcon, "cell 0 4,alignx center");
		userAccontSettingsIcon.setPreferredSize(new Dimension(10,10));
		userAccontSettingsIcon.setBounds(161, 96, 141, 188);
		userAccontSettingsIcon.setIcon(img4);

		JLabel authorizationTypeManagerIcon = new JLabel("");
		authorizationTypeManagerIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeManager.authorizationTypeView());
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		authorizationTypeManagerIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		centerPanel.add(authorizationTypeManagerIcon, "cell 1 4,alignx center");
		authorizationTypeManagerIcon.setPreferredSize(new Dimension(10,10));
		authorizationTypeManagerIcon.setBounds(161, 96, 141, 188);
		authorizationTypeManagerIcon.setIcon(img5);

		JLabel liveSystemIcon = new JLabel("");
		liveSystemIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					frame.setContentPane(LiveSystemManager.liveSystemView());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		centerPanel.add(liveSystemIcon, "cell 2 4,alignx center");
		liveSystemIcon.setPreferredSize(new Dimension(10,10));
		liveSystemIcon.setBounds(161, 96, 141, 188);
		liveSystemIcon.setIcon(img6);


		////////////////////////////////////////////////////////
		//           Window Panel
		/////////////////////////////////////////////////////////

		// Configurations
		windowPanel= new JPanel();
		windowPanel.setLayout(new BorderLayout(0, 0));
		windowPanel.add(menuPanel, BorderLayout.NORTH);
		windowPanel.add(leftPanelParkingInformation, BorderLayout.WEST);
		windowPanel.add(mainPanel, BorderLayout.CENTER);
		return windowPanel;
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//   																													    //
	//                                                   MAIN                                                                   //
	//  																														//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Main Program
	 * @param args
	 * @throws MessagingException 
	 */
	public static void main (String[] args) throws MessagingException {
		Boolean empty= false;
		ArrayList result= new ArrayList();
		try {
			dbman= new DBManager();
			String query= "Select * from  sisca_configuration_information";
			result= dbman.getAllPermissionInfoFromDB(query);

			if (result.isEmpty()){
				empty= true;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		if(empty.equals(true)){

			HKJ_SisCAWizard wizardWindow = new HKJ_SisCAWizard();
			wizardWindow.setVisible();
		}
		else{

			HKJ_SisCA_MainPage  mainWindow = new HKJ_SisCA_MainPage();

			// Initialize communication manager
			String ipAddress= "136.145.59.225";
			int port= 5000;

			String query6= "Select * from sisca_configuration_information";
			ArrayList result2= new ArrayList();

			try {
				result2= dbman.getNotificationsInformation(query6);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			Object num2 = ((List<Object>) result2.get(0)).get(6);
			String casID= (String) num2;

			try {
				clientSocket= new Socket(ipAddress, port);
				cmcas= new CommunicationManagerCAS(clientSocket,ClientType.CAS,casID);
				
				try {
					System.out.println("Main: Register: Trying to acquire semaphore...");
					semaphore.acquire();
					System.out.println("Main: Register: Acquired semaphore...");
					cmcas.performASRegistration();
					semaphore.release();
					System.out.println("Main: Register: Released semaphore...");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			

			Thread communicationManagerThread = new Thread(new CMRunnable());
			communicationManagerThread.start();

			// Initialize Notifications Manager
			notificationsManager = new NotificationManager();

			Thread notificationManagerThread = new Thread(new NMRunnable());
			notificationManagerThread.start();

			initializae();
		}

	}


	private static ArrayList<Object> getParkingList() {
		ArrayList<Object> parkingNameFromDB = new ArrayList<Object>();
		try {
			ArrayList<Object> parkingNameListFromDB = dbman.getFromDB("select * from sisca_parking where sisca_parking_active='true' order by sisca_parking_name");
			String[] keyValue;
			String parkingName = null;
			for(int i=0; i<parkingNameListFromDB.size(); i++){
				//obtener el elemento i del elemento 1 (el array del array) 
				for(int k=0 ; k<((List<Object>) parkingNameListFromDB.get(i)).size(); k++){
					//result = 1:A
					Object result = ((List<Object>) parkingNameListFromDB.get(i)).get(k);
					//keyValue -> {1,A}
					keyValue = result.toString().split("/");
					if(keyValue[0].equals("sisca_parking_name")){
						parkingName = (String) keyValue[1];
					}
				}
				parkingNameFromDB.add(parkingName);
			}
		} catch (SQLException e) {
			// do nothing
			e.printStackTrace();
		} catch (ParseException e) {
			// do nothing
			e.printStackTrace();
		}
		return parkingNameFromDB;
	}
}
