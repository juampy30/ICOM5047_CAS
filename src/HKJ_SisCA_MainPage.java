import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import databases.DBManager;

import net.miginfocom.swing.MigLayout;


public class HKJ_SisCA_MainPage {

	static JFrame frame;
	static JPanel menuPanel;
	static JPanel leftPanel;
	static JPanel mainPanel;
	static JPanel windowPanel;
	private DBManager dbman;
	private ArrayList<Object> availableSAD;
	private ArrayList<Object> availableAtzType;
	private String[] availableSadList;
	private String[] availableAtzTypeList;


	// HKJ_SisCA_MainPage Constructor
	public HKJ_SisCA_MainPage(){
		initializae();
	}

	private void initializae() {
		frame= new JFrame();

		// Image Icons
		ImageIcon img1=  new ImageIcon("/Users/Jeancarlo/Desktop/Documents/GitHub/ICOM5047_CAS/Icons/i1.png");
		ImageIcon img2=  new ImageIcon("/Users/Jeancarlo/Desktop/Documents/GitHub/ICOM5047_CAS/Icons/i2.png");
		ImageIcon img3=  new ImageIcon("/Users/Jeancarlo/Desktop/Documents/GitHub/ICOM5047_CAS/Icons/i3.png");
		ImageIcon img4=  new ImageIcon("/Users/Jeancarlo/Desktop/Documents/GitHub/ICOM5047_CAS/Icons/i4.png");
		ImageIcon img5=  new ImageIcon("/Users/Jeancarlo/Desktop/Documents/GitHub/ICOM5047_CAS/Icons/i5.png");
		ImageIcon img6=  new ImageIcon("/Users/Jeancarlo/Desktop/Documents/GitHub/ICOM5047_CAS/Icons/i6.png");


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

		JLabel userNameLabel = new JLabel("User Name   ");
		userNamePanel.add(userNameLabel, BorderLayout.EAST);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground((java.awt.Color) null);
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));


		/////////////////////////////////////////////////////////
		//           Left Panel
		/////////////////////////////////////////////////////////

		// Configurations 
		leftPanel = new JPanel();
		leftPanel.setBackground(new Color(245,245,245));
		leftPanel.setPreferredSize(new Dimension(390, 10));
		leftPanel.setLayout(new BorderLayout(0, 0));

		JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanel.add(scrollPane);

		JLayeredPane liveSystemPanel2 = new JLayeredPane();
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setViewportView(liveSystemPanel);
		liveSystemPanel2.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));
		liveSystemPanel.setLayout(new MigLayout("", "[188.00px][69.00px][84px]", "[][50px][17px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][]"));


		// LeftPanel Labels
		JLabel LSystemLabel = new JLabel("LIVE SYSTEM");
		liveSystemPanel.add(LSystemLabel, "cell 0 1 3 1,alignx center,aligny top");
		LSystemLabel.setPreferredSize(new Dimension(100, 50));
		LSystemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LSystemLabel.setForeground(java.awt.Color.BLACK);
		LSystemLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		JLabel pNameLabel = new JLabel("Parking Name");
		pNameLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		liveSystemPanel.add(pNameLabel, "cell 0 2,alignx left,aligny top");

		JLabel capacityLabel = new JLabel("Capacity");
		capacityLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		liveSystemPanel.add(capacityLabel, "cell 1 2,alignx center,aligny top");

		JLabel availabilityLabel = new JLabel("Availability");
		availabilityLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		availabilityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		availabilityLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		liveSystemPanel.add(availabilityLabel, "cell 2 2,growx,aligny top");

		// Parking Name Labels & MouseCliked Event 
		// getParkingName()
		JLabel pName1 = new JLabel("New label");
		pName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}

		});
		pName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName1, "cell 0 4,alignx left,aligny top");

		JLabel pName2 = new JLabel("New label");
		pName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName2, "cell 0 6,alignx left,aligny top");

		JLabel pName3 = new JLabel("New label");
		pName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName3, "cell 0 8,alignx left,aligny top");

		JLabel pName4 = new JLabel("New label");
		pName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName4, "cell 0 10,alignx left,aligny top");

		JLabel pName5 = new JLabel("New label");
		pName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName5, "cell 0 12,alignx left,aligny top");

		JLabel pName6 = new JLabel("New label");
		pName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName6, "cell 0 14,alignx left,aligny top");

		JLabel pName7 = new JLabel("New label");
		pName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName7, "cell 0 16,alignx left,aligny top");

		JLabel pName8 = new JLabel("New label");
		pName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName8, "cell 0 18,alignx left,aligny top");

		JLabel pName9 = new JLabel("New label");
		pName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName9, "cell 0 20,alignx left,aligny top");

		JLabel pName10 = new JLabel("New label");
		pName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName10, "cell 0 22,alignx left,aligny top");

		// Capacity Labels
		// getParkingCapacity()
		JLabel cap1 = new JLabel("New label");
		liveSystemPanel.add(cap1, "cell 1 4,alignx center,aligny top");

		JLabel cap2 = new JLabel("New label");
		liveSystemPanel.add(cap2, "cell 1 6,alignx center,aligny top");

		JLabel cap3 = new JLabel("New label");
		liveSystemPanel.add(cap3, "cell 1 8,alignx center,aligny top");

		JLabel cap4 = new JLabel("New label");
		liveSystemPanel.add(cap4, "cell 1 10,alignx center,aligny top");

		JLabel cap5 = new JLabel("New label");
		liveSystemPanel.add(cap5, "cell 1 12,alignx center,aligny top");

		JLabel cap6 = new JLabel("New label");
		liveSystemPanel.add(cap6, "cell 1 14,alignx center,aligny top");

		JLabel cap7 = new JLabel("New label");
		liveSystemPanel.add(cap7, "cell 1 16,alignx center,aligny top");

		JLabel cap8 = new JLabel("New label");
		liveSystemPanel.add(cap8, "cell 1 18,alignx center,aligny top");

		JLabel cap9 = new JLabel("New label");
		liveSystemPanel.add(cap9, "cell 1 20,alignx center,aligny top");

		JLabel cap10 = new JLabel("New label");
		liveSystemPanel.add(cap10, "cell 1 22,alignx center,aligny top");

		// Availability Labels
		// getParkingAvailability()
		JLabel ava1 = new JLabel("New label");
		liveSystemPanel.add(ava1, "cell 2 4,alignx center,aligny top");

		JLabel ava2 = new JLabel("New label");
		liveSystemPanel.add(ava2, "cell 2 6,alignx center,aligny top");

		JLabel ava3 = new JLabel("New label");
		liveSystemPanel.add(ava3, "cell 2 8,alignx center,aligny baseline");

		JLabel ava4 = new JLabel("New label");
		liveSystemPanel.add(ava4, "cell 2 10,alignx center,aligny top");

		JLabel ava5 = new JLabel("New label");
		liveSystemPanel.add(ava5, "cell 2 12,alignx center,aligny top");

		JLabel ava6 = new JLabel("New label");
		liveSystemPanel.add(ava6, "cell 2 14,alignx center,aligny top");

		JLabel ava7 = new JLabel("New label");
		liveSystemPanel.add(ava7, "cell 2 16,alignx center,aligny top");

		JLabel ava8 = new JLabel("New label");
		liveSystemPanel.add(ava8, "cell 2 18,alignx center,aligny top");

		JLabel ava9 = new JLabel("New label");
		liveSystemPanel.add(ava9, "cell 2 20,alignx center,aligny top");

		JLabel ava10 = new JLabel("New label");
		liveSystemPanel.add(ava10, "cell 2 22,alignx center,aligny top");

		// Separators
		JSeparator separator = new JSeparator();
		liveSystemPanel.add(separator, "cell 0 5 3 1,growx,aligny top");

		JSeparator separator_1 = new JSeparator();
		liveSystemPanel.add(separator_1, "cell 0 7 3 1,growx,aligny top");

		JSeparator separator_2 = new JSeparator();
		liveSystemPanel.add(separator_2, "cell 0 9 3 1,growx,aligny top");

		JSeparator separator_3 = new JSeparator();
		liveSystemPanel.add(separator_3, "cell 0 11 3 1,growx,aligny top");

		JSeparator separator_4 = new JSeparator();
		liveSystemPanel.add(separator_4, "cell 0 13 3 1,growx,aligny top");

		JSeparator separator_5 = new JSeparator();
		liveSystemPanel.add(separator_5, "cell 0 15 3 1,growx,aligny top");

		JSeparator separator_7 = new JSeparator();
		liveSystemPanel.add(separator_7, "cell 0 19 3 1,growx,aligny top");

		JSeparator separator_6 = new JSeparator();
		liveSystemPanel.add(separator_6, "cell 0 17 3 1,growx,aligny top");

		JSeparator separator_8 = new JSeparator();
		liveSystemPanel.add(separator_8, "cell 0 21 3 1,growx,aligny top");

		JSeparator separator_9 = new JSeparator();
		liveSystemPanel.add(separator_9, "cell 0 23 3 1,growx,aligny top");

		JSeparator separator_10 = new JSeparator();
		liveSystemPanel.add(separator_10, "cell 0 3 3 1,growx,aligny top");

		JButton viewAllBtn = new JButton("View All");
		viewAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(parkingView());
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(viewAllBtn, "cell 0 24 3 1,alignx right");


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
				frame.setContentPane(parkingView());
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
				frame.setContentPane(sadView());
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
				frame.setContentPane(permissionView());
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
				frame.setContentPane(accountView());
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
				frame.setContentPane(authorizationTypeView());
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
		windowPanel.add(leftPanel, BorderLayout.WEST);
		windowPanel.add(mainPanel, BorderLayout.CENTER);

		////////////////////////////////////////////////////////
		//           Frame
		/////////////////////////////////////////////////////////

		// Configurations
		frame.getContentPane().add(windowPanel);
		frame.setResizable(true);
		frame.setTitle("SisCA");
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	private void setVisible() {
		this.frame.setVisible(true);

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	//           																					 //
	//                              SisCA Viewer Pages                                               //
	//																								 //
	///////////////////////////////////////////////////////////////////////////////////////////////////





	//////////////////////////////////////////////////////////////////
	//   Parking Information View								    //
	//////////////////////////////////////////////////////////////////

	private JPanel parkingInformationView() {

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
				frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(parkingView());
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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
				frame.setContentPane(parkingInformationView());
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

		JLabel pName1 = new JLabel("New label");
		pName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName1, "cell 0 3,alignx left,aligny top");

		JLabel pName2 = new JLabel("New label");
		pName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName2, "cell 0 5,alignx left,aligny top");

		JLabel pName3 = new JLabel("New label");
		pName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName3, "cell 0 7,alignx left,aligny top");

		JLabel pName4 = new JLabel("New label");
		pName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName4, "cell 0 9,alignx left,aligny top");

		JLabel pName5 = new JLabel("New label");
		pName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName5, "cell 0 11,alignx left,aligny top");

		JLabel pName6 = new JLabel("New label");
		pName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName6, "cell 0 13,alignx left,aligny top");

		JLabel pName7 = new JLabel("New label");
		pName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName7, "cell 0 15,alignx left,aligny top");

		JLabel pName8 = new JLabel("New label");
		pName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName8, "cell 0 17,alignx left,aligny top");

		JLabel pName9 = new JLabel("New label");
		pName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName9, "cell 0 19,alignx left,aligny top");

		JLabel pName10 = new JLabel("New label");
		pName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(parkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
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
				frame.setContentPane(parkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New Parking");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//

				frame.setContentPane(addParkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(editParkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		editAndRemovePanel.add(editButton, "cell 0 0");

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO remover
				frame.setContentPane(parkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

	private JPanel addParkingView(){

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
				frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(parkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(addParkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

		JLabel pName1 = new JLabel("New label");
		pName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName1, "cell 0 3,alignx left,aligny top");

		JLabel pName2 = new JLabel("New label");
		pName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName2, "cell 0 5,alignx left,aligny top");

		JLabel pName3 = new JLabel("New label");
		pName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName3, "cell 0 7,alignx left,aligny top");

		JLabel pName4 = new JLabel("New label");
		pName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName4, "cell 0 9,alignx left,aligny top");

		JLabel pName5 = new JLabel("New label");
		pName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName5, "cell 0 11,alignx left,aligny top");

		JLabel pName6 = new JLabel("New label");
		pName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName6, "cell 0 13,alignx left,aligny top");

		JLabel pName7 = new JLabel("New label");
		pName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName7, "cell 0 15,alignx left,aligny top");

		JLabel pName8 = new JLabel("New label");
		pName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName8, "cell 0 17,alignx left,aligny top");

		JLabel pName9 = new JLabel("New label");
		pName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName9, "cell 0 19,alignx left,aligny top");

		JLabel pName10 = new JLabel("New label");
		pName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(parkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
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
				frame.setContentPane(parkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New Parking");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(addParkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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

		JButton addParkingBtn = new JButton("Add Parking");
		addParkingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Este add me lleva a ver la iformaci—n del parking recien a–adido
				frame.setContentPane(parkingInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(addParkingBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Cancel me hace un BACK en el history
				frame.setContentPane(parkingInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});

		AddCancelPanel.add(cancelBtn, "cell 26 0");

		JPanel pNamePanel = new JPanel();
		pNamePanel.setBackground((java.awt.Color) null);
		centerPanel.add(pNamePanel, "cell 0 1,growx,aligny top");
		pNamePanel.setLayout(new MigLayout("", "[109px][269.00px]", "[28px]"));

		JLabel nameLabel = new JLabel("Parking Name: ");
		pNamePanel.add(nameLabel, "cell 0 0,alignx left,aligny center");
		nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JTextField textFieldName = new JTextField();
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

		JPanel sadsAndATypesPanel = new JPanel();
		sadsAndATypesPanel.setBackground((java.awt.Color) null);
		centerPanel.add(sadsAndATypesPanel, "cell 0 5,grow");
		sadsAndATypesPanel.setLayout(new MigLayout("", "[161px][161px][159.00][161px][161px]", "[21px][83px][29px]"));

		JScrollPane scrollPaneSADs = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneSADs, "cell 0 1,grow");
////////////
		try {
			dbman = new DBManager();
			availableSAD = dbman.getFromDB("select * from sisca_sad where sisca_sad_active='false'");
			availableSAD = dbman.getAvailableSAD(availableSAD);
			availableAtzType = dbman.getFromDB("select * from sisca_authorization");
			availableAtzType = dbman.getAvailableAuthorizationTypes(availableAtzType);
			availableSadList = new String[availableSAD.size()];
			for(int i=0; i<availableSAD.size(); i++){
				String myString = (String) availableSAD.get(i);
				availableSadList[i]=myString.toUpperCase();
				System.out.println(myString);
			}
			availableAtzTypeList = new String[availableAtzType.size()];
			for(int i=0; i<availableAtzType.size(); i++){
				String myString = (String) availableAtzType.get(i);
				availableAtzTypeList[i]=myString.toUpperCase();
				System.out.println(myString);
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
////////////
		JList<String> SADList = new JList<String>(availableSadList);
		scrollPaneSADs.setViewportView(SADList);

		JScrollPane scrollPaneCurrentSADs = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneCurrentSADs, "cell 1 1,grow");

		JList<String> currentSADList = new JList<String>();
		scrollPaneCurrentSADs.setViewportView(currentSADList);

		JScrollPane scrollPaneATypes = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneATypes, "cell 3 1,grow");

		JList<String> ATypesList = new JList<String>(availableAtzTypeList);
		scrollPaneATypes.setViewportView(ATypesList);

		JScrollPane scrollPaneCurrentATypes = new JScrollPane();
		sadsAndATypesPanel.add(scrollPaneCurrentATypes, "cell 4 1,grow");

		JList currentATypesList = new JList();
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

	private JPanel editParkingView(){


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
				//frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				//frame.setContentPane(parkingView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

		JLabel pName1 = new JLabel("New label");
		pName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName1, "cell 0 3,alignx left,aligny top");

		JLabel pName2 = new JLabel("New label");
		pName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName2, "cell 0 5,alignx left,aligny top");

		JLabel pName3 = new JLabel("New label");
		pName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName3, "cell 0 7,alignx left,aligny top");

		JLabel pName4 = new JLabel("New label");
		pName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName4, "cell 0 9,alignx left,aligny top");

		JLabel pName5 = new JLabel("New label");
		pName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName5, "cell 0 11,alignx left,aligny top");

		JLabel pName6 = new JLabel("New label");
		pName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName6, "cell 0 13,alignx left,aligny top");

		JLabel pName7 = new JLabel("New label");
		pName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName7, "cell 0 15,alignx left,aligny top");

		JLabel pName8 = new JLabel("New label");
		pName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName8, "cell 0 17,alignx left,aligny top");

		JLabel pName9 = new JLabel("New label");
		pName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName9, "cell 0 19,alignx left,aligny top");

		JLabel pName10 = new JLabel("New label");
		pName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(parkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
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
				// TODO Cancel me hace un BACK en el history
				frame.setContentPane(parkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New Parking");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Cancel me hace un BACK en el history
				frame.setContentPane(addParkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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

		JButton editParkingBtn = new JButton("Edit Parking");
		editParkingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO edit
				frame.setContentPane(parkingInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(editParkingBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Cancel me hace un BACK en el history
				frame.setContentPane(parkingInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(cancelBtn, "cell 26 0");

		JPanel pNamePanel = new JPanel();
		pNamePanel.setBackground((java.awt.Color) null);
		centerPanel.add(pNamePanel, "cell 0 1,growx,aligny top");
		pNamePanel.setLayout(new MigLayout("", "[109px][269.00px]", "[28px]"));

		JLabel nameLabel = new JLabel("Parking Name: ");
		pNamePanel.add(nameLabel, "cell 0 0,alignx left,aligny center");
		nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JTextField textFieldName = new JTextField();
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

	//////////////////////////////////////////////////////////////////
	//   Parking View								            	//
	//////////////////////////////////////////////////////////////////


	private JPanel parkingView(){
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
				frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(parkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

		JTextField textFieldSearch = new JTextField();
		textFieldSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(parkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		searchAndAddPanel.add(textFieldSearch, "cell 1 0,growx,aligny top");
		textFieldSearch.setColumns(10);
		JButton goButton = new JButton("Go");
		goButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(parkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		goButton.setPreferredSize(new Dimension(10, 29));
		searchAndAddPanel.add(goButton, "cell 2 0");

		JButton addNewParkingBtn = new JButton("Add New Parking");
		addNewParkingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(addParkingView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		searchAndAddPanel.add(addNewParkingBtn, "cell 25 0,alignx left,aligny top");
		JPanel parkingListPanel = new JPanel();
		parkingListPanel.setBackground(new Color(250,250,250));

		centerPanel.add(parkingListPanel, "cell 0 1,alignx center,growy");
		parkingListPanel.setLayout(new MigLayout("", "[724px]", "[360px]"));
		JScrollPane scrollPane = new JScrollPane();
		parkingListPanel.add(scrollPane, "cell 0 0,grow");
		JList list = new JList();
		list.setSelectionForeground(UIManager.getColor("Button.darkShadow"));
		list.setSelectionBackground(UIManager.getColor("Button.background"));
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					frame.setContentPane(parkingInformationView()); // Wrong Way! =S
					frame.pack();
					frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			}
		});
		scrollPane.setViewportView(list);
		list.setModel(new AbstractListModel() {
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
		JPanel windowPanelParking= new JPanel();
		windowPanelParking.setLayout(new BorderLayout(0, 0));
		windowPanelParking.add(menuPanelParking, BorderLayout.NORTH);
		windowPanelParking.add(mainPanelParking, BorderLayout.CENTER);

		return windowPanelParking;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// SAD
	///////////////////////////


	//////////////////////////////////////////////////////////////////
	//   SAD View								               	    //
	//////////////////////////////////////////////////////////////////


	private JPanel sadView(){
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
				frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(sadView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

		JTextField textFieldSearch = new JTextField();
		textFieldSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(sadView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		searchAndAddPanel.add(textFieldSearch, "cell 1 0,growx,aligny top");
		textFieldSearch.setColumns(10);
		JButton goButton = new JButton("Go");
		goButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		goButton.setPreferredSize(new Dimension(10, 29));
		searchAndAddPanel.add(goButton, "cell 2 0");

		JButton addNewSADBtn = new JButton("Add New SAD");
		addNewSADBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(addSADView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		searchAndAddPanel.add(addNewSADBtn, "cell 25 0,alignx left,aligny top");
		JPanel SADListPanel = new JPanel();
		SADListPanel.setBackground(new Color(250,250,250));

		centerPanel.add(SADListPanel, "cell 0 1,alignx center,growy");
		SADListPanel.setLayout(new MigLayout("", "[724px]", "[360px]"));
		JScrollPane sadScrollPane = new JScrollPane();
		SADListPanel.add(sadScrollPane, "cell 0 0,grow");
		JList sadList = new JList();
		sadList.setSelectionForeground(UIManager.getColor("Button.darkShadow"));
		sadList.setSelectionBackground(UIManager.getColor("Button.background"));
		sadList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					frame.setContentPane(sadInformationView()); // Wrong Way! =S
					frame.pack();
					frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			}
		});
		sadScrollPane.setViewportView(sadList);
		sadList.setModel(new AbstractListModel() {
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
		JPanel windowPanelSAD= new JPanel();
		windowPanelSAD.setLayout(new BorderLayout(0, 0));
		windowPanelSAD.add(menuPanelSAD, BorderLayout.NORTH);
		windowPanelSAD.add(mainPanelSAD, BorderLayout.CENTER);

		return windowPanelSAD;

	}
	//////////////////////////////////////////////////////////////////
	//   SAD Information View								        //
	//////////////////////////////////////////////////////////////////

	private JPanel sadInformationView() {

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
				frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(sadView());
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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
				frame.setContentPane(sadInformationView());
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

		JLabel pName1 = new JLabel("New label");
		pName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName1, "cell 0 3,alignx left,aligny top");

		JLabel pName2 = new JLabel("New label");
		pName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName2, "cell 0 5,alignx left,aligny top");

		JLabel pName3 = new JLabel("New label");
		pName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName3, "cell 0 7,alignx left,aligny top");

		JLabel pName4 = new JLabel("New label");
		pName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName4, "cell 0 9,alignx left,aligny top");

		JLabel pName5 = new JLabel("New label");
		pName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName5, "cell 0 11,alignx left,aligny top");

		JLabel pName6 = new JLabel("New label");
		pName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName6, "cell 0 13,alignx left,aligny top");

		JLabel pName7 = new JLabel("New label");
		pName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		pName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(pName7, "cell 0 15,alignx left,aligny top");

		JLabel pName8 = new JLabel("New label");
		pName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName8, "cell 0 17,alignx left,aligny top");

		JLabel pName9 = new JLabel("New label");
		pName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(pName9, "cell 0 19,alignx left,aligny top");

		JLabel pName10 = new JLabel("New label");
		pName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(sadView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
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
				frame.setContentPane(sadView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New SAD");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(addSADView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

		JLabel SADid = new JLabel("SAD ID");
		SADid.setPreferredSize(new Dimension(100, 50));
		SADid.setHorizontalAlignment(SwingConstants.CENTER);
		SADid.setForeground(java.awt.Color.BLACK);
		SADid.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		centerPanel.add(SADid, "cell 0 0,alignx center,aligny top");

		JLabel pCapacity = new JLabel("Parking Capacity: ");
		pCapacity.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		centerPanel.add(pCapacity, "cell 0 1");

		JLabel lblDirection = new JLabel("Direction: ");
		lblDirection.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		centerPanel.add(lblDirection, "cell 0 3,alignx left,aligny top");

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

		JPanel editAndRemovePanel = new JPanel();
		editAndRemovePanel.setBackground(new Color(250,250,250));
		centerPanel.add(editAndRemovePanel, "cell 0 11,grow");
		editAndRemovePanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(editSADView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		editAndRemovePanel.add(editButton, "cell 0 0");

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO remover
				frame.setContentPane(sadView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

	private JPanel addSADView() {

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
				frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(sadView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(addSADView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
		//           Left Panel
		/////////////////////////////////////////////////////////

		// Configurations 
		JPanel leftPanelAddSAD = new JPanel();
		leftPanelAddSAD.setBackground(new Color(245,245,245));
		leftPanelAddSAD.setPreferredSize(new Dimension(390, 10));
		leftPanelAddSAD.setLayout(new BorderLayout(0, 0));

		JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanelAddSAD.add(scrollPane);

		JLayeredPane liveSystemPanel2 = new JLayeredPane();
		scrollPane.setViewportView(liveSystemPanel);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel2.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));

		// SAD Name Labels & MouseCliked Event 

		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px]"));


		// LeftPanel Labels
		JLabel LSystemLabel = new JLabel("SAD LIST");
		liveSystemPanel.add(LSystemLabel, "cell 0 0,alignx center,aligny top");
		LSystemLabel.setPreferredSize(new Dimension(100, 50));
		LSystemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LSystemLabel.setForeground(java.awt.Color.BLACK);
		LSystemLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		JLabel sName1 = new JLabel("New label");
		sName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName1, "cell 0 3,alignx left,aligny top");

		JLabel sName2 = new JLabel("New label");
		sName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName2, "cell 0 5,alignx left,aligny top");

		JLabel sName3 = new JLabel("New label");
		sName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName3, "cell 0 7,alignx left,aligny top");

		JLabel sName4 = new JLabel("New label");
		sName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName4, "cell 0 9,alignx left,aligny top");

		JLabel sName5 = new JLabel("New label");
		sName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});

		JSeparator separator_3 = new JSeparator();
		liveSystemPanel.add(separator_3, "cell 0 11,growx,aligny top");
		liveSystemPanel.add(sName5, "cell 0 12,alignx left,aligny top");

		JLabel sName6 = new JLabel("New label");
		sName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName6, "cell 0 14,alignx left,aligny top");

		JLabel sName7 = new JLabel("New label");
		sName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName7, "cell 0 16,alignx left,aligny top");

		JLabel sName8 = new JLabel("New label");
		sName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName8, "cell 0 18,alignx left,aligny top");

		JLabel sName9 = new JLabel("New label");
		sName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName9, "cell 0 20,alignx left,aligny top");

		JLabel sName10 = new JLabel("New label");
		sName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName10, "cell 0 22,alignx left,aligny top");

		// Separators
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
				frame.setContentPane(sadView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
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
				frame.setContentPane(sadView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New SAD");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(addSADView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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

		JButton addSADBtn = new JButton("Add SAD");
		addSADBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Este add me lleva a ver la iformaci—n del SAD recien a–adido
				frame.setContentPane(sadInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(addSADBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Cancel me hace un BACK en el history
				frame.setContentPane(sadInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});

		AddCancelPanel.add(cancelBtn, "cell 26 0");

		JPanel sadIDPanel = new JPanel();
		sadIDPanel.setBackground((java.awt.Color) null);
		centerPanel.add(sadIDPanel, "cell 0 1,growx,aligny top");
		sadIDPanel.setLayout(new MigLayout("", "[57.00px][371.00px]", "[28px]"));

		JLabel idLabel = new JLabel("SAD ID: ");
		sadIDPanel.add(idLabel, "cell 0 0,alignx left,aligny center");
		idLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JTextField textFieldName = new JTextField();
		textFieldName.setPreferredSize(new Dimension(200, 100));
		sadIDPanel.add(textFieldName, "cell 1 0,grow");
		textFieldName.setColumns(10);

		JPanel directionPanel = new JPanel();
		directionPanel.setBackground((java.awt.Color) null);
		centerPanel.add(directionPanel, "cell 0 2,grow");
		directionPanel.setLayout(new MigLayout("", "[][][35][70.00][35][70]", "[]"));

		JLabel directionLabel = new JLabel("Direction: ");
		directionPanel.add(directionLabel, "cell 0 0");
		directionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JRadioButton rdbtnEntry = new JRadioButton("Entry");
		directionPanel.add(rdbtnEntry, "cell 2 0");

		JRadioButton rdbtnExit = new JRadioButton("Exit");
		directionPanel.add(rdbtnExit, "cell 3 0");

		JPanel parkingPanel = new JPanel();
		parkingPanel.setBackground((java.awt.Color) null);
		centerPanel.add(parkingPanel, "cell 0 3,grow");
		parkingPanel.setLayout(new MigLayout("", "[59.00px][343.00px,grow][118.00][-79.00px][161px]", "[21px][29px]"));

		JLabel parkingInfoLabel = new JLabel("Parking:");
		parkingInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		parkingInfoLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		parkingPanel.add(parkingInfoLabel, "cell 0 0,alignx left,growy");

		JComboBox comboBox = new JComboBox();
		parkingPanel.add(comboBox, "cell 1 0,growx,aligny center");


		////////////////////////////////////////////////////////
		//           Window Panel
		/////////////////////////////////////////////////////////

		// Configurations
		JPanel windowPanelAddSAD= new JPanel();
		windowPanelAddSAD.setLayout(new BorderLayout(0, 0));
		windowPanelAddSAD.add(menuPanelAddSAD, BorderLayout.NORTH);
		windowPanelAddSAD.add(leftPanelAddSAD, BorderLayout.WEST);
		windowPanelAddSAD.add(mainPanelAddSAD, BorderLayout.CENTER);

		return windowPanelAddSAD;

	}

	private JPanel editSADView(){
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
				frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(sadView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(addSADView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
		//           Left Panel
		/////////////////////////////////////////////////////////

		// Configurations 
		JPanel leftPanelEditSAD = new JPanel();
		leftPanelEditSAD.setBackground(new Color(245,245,245));
		leftPanelEditSAD.setPreferredSize(new Dimension(390, 10));
		leftPanelEditSAD.setLayout(new BorderLayout(0, 0));

		JPanel liveSystemPanel = new JPanel();
		liveSystemPanel.setBackground(new Color(245,245,245));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveSystemPanel);
		leftPanelEditSAD.add(scrollPane);

		JLayeredPane liveSystemPanel2 = new JLayeredPane();
		scrollPane.setViewportView(liveSystemPanel);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		liveSystemPanel2.setLayout(new MigLayout("", "[101px][4px][1px][20px][69px][90px][8px][75px]", "[26px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][29px][12px][1px][29px]"));

		// SAD Name Labels & MouseCliked Event 

		liveSystemPanel.setLayout(new MigLayout("", "[347.00px]", "[50px][28px][12px][16px][12px][16px][12px][16px][12px][16px][12px][][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][16px][12px][29px]"));


		// LeftPanel Labels
		JLabel LSystemLabel = new JLabel("SAD LIST");
		liveSystemPanel.add(LSystemLabel, "cell 0 0,alignx center,aligny top");
		LSystemLabel.setPreferredSize(new Dimension(100, 50));
		LSystemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LSystemLabel.setForeground(java.awt.Color.BLACK);
		LSystemLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		JLabel sName1 = new JLabel("New label");
		sName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName1, "cell 0 3,alignx left,aligny top");

		JLabel sName2 = new JLabel("New label");
		sName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName2, "cell 0 5,alignx left,aligny top");

		JLabel sName3 = new JLabel("New label");
		sName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName3, "cell 0 7,alignx left,aligny top");

		JLabel sName4 = new JLabel("New label");
		sName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName4, "cell 0 9,alignx left,aligny top");

		JLabel sName5 = new JLabel("New label");
		sName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});

		JSeparator separator_3 = new JSeparator();
		liveSystemPanel.add(separator_3, "cell 0 11,growx,aligny top");
		liveSystemPanel.add(sName5, "cell 0 12,alignx left,aligny top");

		JLabel sName6 = new JLabel("New label");
		sName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName6, "cell 0 14,alignx left,aligny top");

		JLabel sName7 = new JLabel("New label");
		sName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName7, "cell 0 16,alignx left,aligny top");

		JLabel sName8 = new JLabel("New label");
		sName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName8, "cell 0 18,alignx left,aligny top");

		JLabel sName9 = new JLabel("New label");
		sName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName9, "cell 0 20,alignx left,aligny top");

		JLabel sName10 = new JLabel("New label");
		sName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(sadInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName10, "cell 0 22,alignx left,aligny top");

		// Separators
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
				frame.setContentPane(sadView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
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
				frame.setContentPane(sadView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New SAD");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(addSADView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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

		JButton editSADBtn = new JButton("Edit SAD");
		editSADBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Este add me lleva a ver la iformaci—n del SAD recien a–adido
				frame.setContentPane(sadInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		editCancelPanel.add(editSADBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Cancel me hace un BACK en el history
				frame.setContentPane(sadInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});

		editCancelPanel.add(cancelBtn, "cell 26 0");

		JPanel sadIDPanel = new JPanel();
		sadIDPanel.setBackground((java.awt.Color) null);
		centerPanel.add(sadIDPanel, "cell 0 1,growx,aligny top");
		sadIDPanel.setLayout(new MigLayout("", "[57.00px][371.00px]", "[28px]"));

		JLabel idLabel = new JLabel("SAD ID: ");
		sadIDPanel.add(idLabel, "cell 0 0,alignx left,aligny center");
		idLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JTextField textFieldName = new JTextField();
		textFieldName.setPreferredSize(new Dimension(200, 100));
		sadIDPanel.add(textFieldName, "cell 1 0,grow");
		textFieldName.setColumns(10);

		JPanel directionPanel = new JPanel();
		directionPanel.setBackground((java.awt.Color) null);
		centerPanel.add(directionPanel, "cell 0 2,grow");
		directionPanel.setLayout(new MigLayout("", "[][][35][70.00][35][70]", "[]"));

		JLabel directionLabel = new JLabel("Direction: ");
		directionPanel.add(directionLabel, "cell 0 0");
		directionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JRadioButton rdbtnEntry = new JRadioButton("Entry");
		directionPanel.add(rdbtnEntry, "cell 2 0");

		JRadioButton rdbtnExit = new JRadioButton("Exit");
		directionPanel.add(rdbtnExit, "cell 3 0");

		JPanel parkingPanel = new JPanel();
		parkingPanel.setBackground((java.awt.Color) null);
		centerPanel.add(parkingPanel, "cell 0 3,grow");
		parkingPanel.setLayout(new MigLayout("", "[59.00px][343.00px,grow][118.00][-79.00px][161px]", "[21px][29px]"));

		JLabel parkingInfoLabel = new JLabel("Parking:");
		parkingInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		parkingInfoLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		parkingPanel.add(parkingInfoLabel, "cell 0 0,alignx left,growy");

		JComboBox comboBox = new JComboBox();
		parkingPanel.add(comboBox, "cell 1 0,growx,aligny center");


		////////////////////////////////////////////////////////
		//           Window Panel
		/////////////////////////////////////////////////////////

		// Configurations
		JPanel windowPanelEditSAD= new JPanel();
		windowPanelEditSAD.setLayout(new BorderLayout(0, 0));
		windowPanelEditSAD.add(menuPanelEditSAD, BorderLayout.NORTH);
		windowPanelEditSAD.add(leftPanelEditSAD, BorderLayout.WEST);
		windowPanelEditSAD.add(mainPanelEditSAD, BorderLayout.CENTER);

		return windowPanelEditSAD;
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ACCOUNT
	///////////////////////////

	//////////////////////////////////////////////////////////////////
	//   Account View								            	//
	//////////////////////////////////////////////////////////////////


	private JPanel accountView(){


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
				frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(accountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

		JLabel userNameLabel = new JLabel("User Name   ");
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

		JTextField textFieldSearch = new JTextField();
		textFieldSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(accountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		searchAndAddPanel.add(textFieldSearch, "cell 1 0,growx,aligny top");
		textFieldSearch.setColumns(10);
		JButton goButton = new JButton("Go");
		goButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		goButton.setPreferredSize(new Dimension(10, 29));
		searchAndAddPanel.add(goButton, "cell 2 0");

		JButton addNewAccountBtn = new JButton("Add New Account");
		addNewAccountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(addAccountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		searchAndAddPanel.add(addNewAccountBtn, "cell 25 0,alignx left,aligny top");
		JPanel AccountListPanel = new JPanel();
		AccountListPanel.setBackground(new Color(250,250,250));

		centerPanel.add(AccountListPanel, "cell 0 1,alignx center,growy");
		AccountListPanel.setLayout(new MigLayout("", "[724px]", "[360px]"));
		JScrollPane scrollPane = new JScrollPane();
		AccountListPanel.add(scrollPane, "cell 0 0,grow");
		JList list = new JList();
		list.setSelectionForeground(UIManager.getColor("Button.darkShadow"));
		list.setSelectionBackground(UIManager.getColor("Button.background"));
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					frame.setContentPane(accountInformationView()); // Wrong Way! =S
					frame.pack();
					frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			}
		});
		scrollPane.setViewportView(list);
		list.setModel(new AbstractListModel() {
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
		JPanel windowPanelAccount= new JPanel();
		windowPanelAccount.setLayout(new BorderLayout(0, 0));
		windowPanelAccount.add(menuPanelAccount, BorderLayout.NORTH);
		windowPanelAccount.add(mainPanelAccount, BorderLayout.CENTER);




		return windowPanelAccount;

	}

	//////////////////////////////////////////////////////////////////
	//   Account Information View								    //
	//////////////////////////////////////////////////////////////////


	private JPanel accountInformationView(){

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
				frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(accountView());
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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
				frame.setContentPane(accountInformationView());
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
		JPanel leftPanelAccountInformation = new JPanel();
		leftPanelAccountInformation.setBackground(new Color(245,245,245));
		leftPanelAccountInformation.setPreferredSize(new Dimension(390, 10));
		leftPanelAccountInformation.setLayout(new BorderLayout(0, 0));

		JPanel liveSystemPanel = new JPanel();
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

		JLabel uName1 = new JLabel("New label");
		uName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		uName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(uName1, "cell 0 3,alignx left,aligny top");

		JLabel uName2 = new JLabel("New label");
		uName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		uName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(uName2, "cell 0 5,alignx left,aligny top");

		JLabel uName3 = new JLabel("New label");
		uName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		uName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(uName3, "cell 0 7,alignx left,aligny top");

		JLabel uName4 = new JLabel("New label");
		uName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		uName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(uName4, "cell 0 9,alignx left,aligny top");

		JLabel uName5 = new JLabel("New label");
		uName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		uName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(uName5, "cell 0 11,alignx left,aligny top");

		JLabel uName6 = new JLabel("New label");
		uName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		uName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(uName6, "cell 0 13,alignx left,aligny top");

		JLabel uName7 = new JLabel("New label");
		uName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		uName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(uName7, "cell 0 15,alignx left,aligny top");

		JLabel uName8 = new JLabel("New label");
		uName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		uName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(uName8, "cell 0 17,alignx left,aligny top");

		JLabel uName9 = new JLabel("New label");
		uName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		uName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(uName9, "cell 0 19,alignx left,aligny top");

		JLabel uName10 = new JLabel("New label");
		uName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		uName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(uName10, "cell 0 21,alignx left,aligny top");

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
				frame.setContentPane(accountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
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
				frame.setContentPane(accountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New Account");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(addAccountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

		JLabel realName = new JLabel("Real Name");
		realName.setPreferredSize(new Dimension(100, 50));
		realName.setHorizontalAlignment(SwingConstants.CENTER);
		realName.setForeground(java.awt.Color.BLACK);
		realName.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		centerPanel.add(realName, "cell 0 0,alignx center,aligny top");

		JLabel username = new JLabel("Username: ");
		username.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		centerPanel.add(username, "cell 0 1");

		JLabel accountType = new JLabel("Account Type:");
		accountType.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		centerPanel.add(accountType, "cell 0 3,alignx left,aligny top");

		JLabel password = new JLabel("Password: ");
		password.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		centerPanel.add(password, "cell 0 5,alignx left,aligny baseline");

		JSeparator separator_12 = new JSeparator();
		centerPanel.add(separator_12, "cell 0 4,growx,aligny top");

		JSeparator separator_13 = new JSeparator();
		centerPanel.add(separator_13, "cell 0 6,growx,aligny top");

		JPanel editAndRemovePanel = new JPanel();
		editAndRemovePanel.setBackground(new Color(250,250,250));
		centerPanel.add(editAndRemovePanel, "cell 0 7,grow");
		editAndRemovePanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(editAccountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		editAndRemovePanel.add(editButton, "cell 0 0");

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO remover
				frame.setContentPane(accountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(accountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(addAccountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

		JLabel userNameLabel = new JLabel("User Name   ");
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

		JLabel sName1 = new JLabel("New label");
		sName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName1, "cell 0 3,alignx left,aligny top");

		JLabel sName2 = new JLabel("New label");
		sName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName2, "cell 0 5,alignx left,aligny top");

		JLabel sName3 = new JLabel("New label");
		sName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName3, "cell 0 7,alignx left,aligny top");

		JLabel sName4 = new JLabel("New label");
		sName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName4, "cell 0 9,alignx left,aligny top");

		JLabel sName5 = new JLabel("New label");
		sName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});

		JSeparator separator_3 = new JSeparator();
		liveSystemPanel.add(separator_3, "cell 0 11,growx,aligny top");
		liveSystemPanel.add(sName5, "cell 0 12,alignx left,aligny top");

		JLabel sName6 = new JLabel("New label");
		sName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName6, "cell 0 14,alignx left,aligny top");

		JLabel sName7 = new JLabel("New label");
		sName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName7, "cell 0 16,alignx left,aligny top");

		JLabel sName8 = new JLabel("New label");
		sName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName8, "cell 0 18,alignx left,aligny top");

		JLabel sName9 = new JLabel("New label");
		sName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(sName9, "cell 0 20,alignx left,aligny top");

		JLabel sName10 = new JLabel("New label");
		sName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		sName10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(sName10, "cell 0 22,alignx left,aligny top");

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
				frame.setContentPane(accountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
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
				frame.setContentPane(accountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New Account");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(addAccountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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

		JButton addAccountBtn = new JButton("Add Account");
		addAccountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Este add me lleva a ver la iformaci—n del Account recien a–adido
				frame.setContentPane(accountInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(addAccountBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Cancel me hace un BACK en el history
				frame.setContentPane(accountInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});

		AddCancelPanel.add(cancelBtn, "cell 26 0");

		JPanel AccountIDPanel = new JPanel();
		AccountIDPanel.setBackground((java.awt.Color) null);
		centerPanel.add(AccountIDPanel, "cell 0 1,growx,aligny top");
		AccountIDPanel.setLayout(new MigLayout("", "[57.00px][371.00px]", "[28px]"));

		JLabel relaNameLabel = new JLabel("Real Name:");
		AccountIDPanel.add(relaNameLabel, "cell 0 0,alignx left,aligny center");
		relaNameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JTextField textFieldName = new JTextField();
		textFieldName.setPreferredSize(new Dimension(200, 100));
		AccountIDPanel.add(textFieldName, "cell 1 0,grow");
		textFieldName.setColumns(10);

		JPanel directionPanel = new JPanel();
		directionPanel.setBackground((java.awt.Color) null);
		centerPanel.add(directionPanel, "cell 0 2,grow");
		directionPanel.setLayout(new MigLayout("", "[81px][292.00px][][][212.00,grow]", "[28px]"));

		JLabel usernameLabel = new JLabel("Username: ");
		directionPanel.add(usernameLabel, "cell 0 0,alignx left,aligny center");
		usernameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(200, 100));
		textField.setColumns(10);
		directionPanel.add(textField, "cell 1 0,grow");
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		directionPanel.add(passwordLabel, "cell 3 0,alignx trailing");
		JPasswordField passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(500, 28));
		directionPanel.add(passwordField, "cell 4 0,growx");

		JPanel AccountPanel = new JPanel();
		AccountPanel.setBackground((java.awt.Color) null);
		centerPanel.add(AccountPanel, "cell 0 3,grow");
		AccountPanel.setLayout(new MigLayout("", "[59.00px][209.00px,grow][118.00][-79.00px][161px]", "[21px][29px]"));

		JLabel AccountInfoLabel = new JLabel("Account Type:");
		AccountInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AccountInfoLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		AccountPanel.add(AccountInfoLabel, "cell 0 0,alignx left,growy");

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"regular", "administrator"}));
		AccountPanel.add(comboBox, "cell 1 0,alignx left,aligny center");


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
				frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(accountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(addAccountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

		JLabel aName1 = new JLabel("New label");
		aName1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aName1, "cell 0 3,alignx left,aligny top");

		JLabel aName2 = new JLabel("New label");
		aName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aName2, "cell 0 5,alignx left,aligny top");

		JLabel aName3 = new JLabel("New label");
		aName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aName3, "cell 0 7,alignx left,aligny top");

		JLabel aName4 = new JLabel("New label");
		aName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aName4, "cell 0 9,alignx left,aligny top");

		JLabel aName5 = new JLabel("New label");
		aName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aName6, "cell 0 14,alignx left,aligny top");

		JLabel aName7 = new JLabel("New label");
		aName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aName7, "cell 0 16,alignx left,aligny top");

		JLabel aName8 = new JLabel("New label");
		aName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aName8, "cell 0 18,alignx left,aligny top");

		JLabel aName9 = new JLabel("New label");
		aName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aName9, "cell 0 20,alignx left,aligny top");

		JLabel aName10 = new JLabel("New label");
		aName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(accountInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(accountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
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
				frame.setContentPane(accountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New Account");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(addAccountView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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

		JButton editAccountBtn = new JButton("Edit Account");
		editAccountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Este add me lleva a ver la iformaci—n del Account recien a–adido
				frame.setContentPane(accountInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(editAccountBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Cancel me hace un BACK en el history
				frame.setContentPane(accountInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});

		AddCancelPanel.add(cancelBtn, "cell 26 0");

		JPanel AccountIDPanel = new JPanel();
		AccountIDPanel.setBackground((java.awt.Color) null);
		centerPanel.add(AccountIDPanel, "cell 0 1,growx,aligny top");
		AccountIDPanel.setLayout(new MigLayout("", "[57.00px][371.00px]", "[28px]"));

		JLabel relaNameLabel = new JLabel("Real Name:");
		AccountIDPanel.add(relaNameLabel, "cell 0 0,alignx left,aligny center");
		relaNameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JTextField textFieldName = new JTextField();
		textFieldName.setPreferredSize(new Dimension(200, 100));
		AccountIDPanel.add(textFieldName, "cell 1 0,grow");
		textFieldName.setColumns(10);

		JPanel directionPanel = new JPanel();
		directionPanel.setBackground((java.awt.Color) null);
		centerPanel.add(directionPanel, "cell 0 2,grow");
		directionPanel.setLayout(new MigLayout("", "[81px][292.00px][][][212.00,grow]", "[28px]"));

		JLabel usernameLabel = new JLabel("Username: ");
		directionPanel.add(usernameLabel, "cell 0 0,alignx left,aligny center");
		usernameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(200, 100));
		textField.setColumns(10);
		directionPanel.add(textField, "cell 1 0,grow");
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		directionPanel.add(passwordLabel, "cell 3 0,alignx trailing");
		JPasswordField passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(500, 28));
		directionPanel.add(passwordField, "cell 4 0,growx");

		JPanel AccountPanel = new JPanel();
		AccountPanel.setBackground((java.awt.Color) null);
		centerPanel.add(AccountPanel, "cell 0 3,grow");
		AccountPanel.setLayout(new MigLayout("", "[59.00px][209.00px,grow][118.00][-79.00px][161px]", "[21px][29px]"));

		JLabel AccountInfoLabel = new JLabel("Account Type:");
		AccountInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AccountInfoLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		AccountPanel.add(AccountInfoLabel, "cell 0 0,alignx left,growy");

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"regular", "administrator"}));
		AccountPanel.add(comboBox, "cell 1 0,alignx left,aligny center");


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

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// AUTHORIZATION TYPE
	///////////////////////////

	//////////////////////////////////////////////////////////////////
	//   Authorization Type View								    //
	//////////////////////////////////////////////////////////////////

	private JPanel authorizationTypeView(){

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
				frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(authorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(authorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		searchAndAddPanel.add(textFieldSearch, "cell 1 0,growx,aligny top");
		textFieldSearch.setColumns(10);
		JButton goButton = new JButton("Go");
		goButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		goButton.setPreferredSize(new Dimension(10, 29));
		searchAndAddPanel.add(goButton, "cell 2 0");

		JButton addNewAuthorizationTypeBtn = new JButton("Add New AuthorizationType");
		addNewAuthorizationTypeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(addAuthorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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
					frame.setContentPane(authorizationTypeInformationView()); // Wrong Way! =S
					frame.pack();
					frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

	private JPanel authorizationTypeInformationView(){


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
				frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(authorizationTypeView());
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack();
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName1, "cell 0 3,alignx left,aligny top");

		JLabel aTName2 = new JLabel("New label");
		aTName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName2, "cell 0 5,alignx left,aligny top");

		JLabel aTName3 = new JLabel("New label");
		aTName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName3, "cell 0 7,alignx left,aligny top");

		JLabel aTName4 = new JLabel("New label");
		aTName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName4, "cell 0 9,alignx left,aligny top");

		JLabel aTName5 = new JLabel("New label");
		aTName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName5, "cell 0 11,alignx left,aligny top");

		JLabel aTName6 = new JLabel("New label");
		aTName6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName6, "cell 0 13,alignx left,aligny top");

		JLabel aTName7 = new JLabel("New label");
		aTName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName7, "cell 0 15,alignx left,aligny top");

		JLabel aTName8 = new JLabel("New label");
		aTName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName8, "cell 0 17,alignx left,aligny top");

		JLabel aTName9 = new JLabel("New label");
		aTName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName9, "cell 0 19,alignx left,aligny top");

		JLabel aTName10 = new JLabel("New label");
		aTName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(authorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
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
				frame.setContentPane(authorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New Authorization Type");
		liveSystemPanel.add(addNewButton, "cell 0 24");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(addAuthorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(editAuthorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		editAndRemovePanel.add(editButton, "cell 0 0");

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO remover
				frame.setContentPane(authorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

	private JPanel addAuthorizationTypeView(){
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
				frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(authorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(addAuthorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName1, "cell 0 3,alignx left,aligny top");

		JLabel aTName2 = new JLabel("New label");
		aTName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName2, "cell 0 5,alignx left,aligny top");

		JLabel aTName3 = new JLabel("New label");
		aTName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName3, "cell 0 7,alignx left,aligny top");

		JLabel aTName4 = new JLabel("New label");
		aTName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName4, "cell 0 9,alignx left,aligny top");

		JLabel aTName5 = new JLabel("New label");
		aTName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName6, "cell 0 13,alignx left,aligny top");

		JLabel aTName7 = new JLabel("New label");
		aTName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName7, "cell 0 15,alignx left,aligny top");

		JLabel aTName8 = new JLabel("New label");
		aTName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName8, "cell 0 17,alignx left,aligny top");

		JLabel aTName9 = new JLabel("New label");
		aTName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName9, "cell 0 19,alignx left,aligny top");

		JLabel aTName10 = new JLabel("New label");
		aTName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(authorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
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
				frame.setContentPane(authorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New AuthorizationType");
		liveSystemPanel.add(addNewButton, "cell 0 24");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(addAuthorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(AddAuthorizationTypeBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Cancel me hace un BACK en el history
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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

	private JPanel editAuthorizationTypeView(){
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
				frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(authorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(addAuthorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName1, "cell 0 3,alignx left,aligny top");

		JLabel aTName2 = new JLabel("New label");
		aTName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName2, "cell 0 5,alignx left,aligny top");

		JLabel aTName3 = new JLabel("New label");
		aTName3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName3, "cell 0 7,alignx left,aligny top");

		JLabel aTName4 = new JLabel("New label");
		aTName4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName4, "cell 0 9,alignx left,aligny top");

		JLabel aTName5 = new JLabel("New label");
		aTName5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName6, "cell 0 13,alignx left,aligny top");

		JLabel aTName7 = new JLabel("New label");
		aTName7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		aTName7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		liveSystemPanel.add(aTName7, "cell 0 15,alignx left,aligny top");

		JLabel aTName8 = new JLabel("New label");
		aTName8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName8, "cell 0 17,alignx left,aligny top");

		JLabel aTName9 = new JLabel("New label");
		aTName9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aTName9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		liveSystemPanel.add(aTName9, "cell 0 19,alignx left,aligny top");

		JLabel aTName10 = new JLabel("New label");
		aTName10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(authorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
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
				frame.setContentPane(authorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		viewAndAddBynPanel.add(viewAllButton);

		JButton addNewButton = new JButton("Add New AuthorizationType");
		liveSystemPanel.add(addNewButton, "cell 0 24");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(addAuthorizationTypeView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		AddCancelPanel.add(editAuthorizationTypeBtn, "cell 0 0");

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Cancel me hace un BACK en el history
				frame.setContentPane(authorizationTypeInformationView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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

	//////////////////////////////////////////////////////////////////
	//   Permission View						        //
	//////////////////////////////////////////////////////////////////

	private JPanel permissionView(){
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
				frame.setContentPane(windowPanel); // Wrong Way! =S
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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
				frame.setContentPane(permissionView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

		JLabel userNameLabel = new JLabel("User Name   ");
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

		JTextField textFieldSearch = new JTextField();
		textFieldSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(permissionView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		searchAndAddPanel.add(textFieldSearch, "cell 1 0,growx,aligny top");
		textFieldSearch.setColumns(10);
		JButton goButton = new JButton("Go");
		goButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(permissionView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
			}
		});
		goButton.setPreferredSize(new Dimension(10, 29));
		searchAndAddPanel.add(goButton, "cell 2 0");

		JButton addNewPermissionBtn = new JButton("Add New Permission");
		addNewPermissionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(addPermissionView());
				frame.pack(); 
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}
		});
		searchAndAddPanel.add(addNewPermissionBtn, "cell 25 0,alignx left,aligny top");
		JPanel PermissionListPanel = new JPanel();
		PermissionListPanel.setBackground(new Color(250,250,250));

		centerPanel.add(PermissionListPanel, "cell 0 1,alignx center,growy");
		PermissionListPanel.setLayout(new MigLayout("", "[724px]", "[360px]"));
		JScrollPane PermissionScrollPane = new JScrollPane();
		PermissionListPanel.add(PermissionScrollPane, "cell 0 0,grow");
		JList PermissionList = new JList();
		PermissionList.setSelectionForeground(UIManager.getColor("Button.darkShadow"));
		PermissionList.setSelectionBackground(UIManager.getColor("Button.background"));
		PermissionList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					frame.setContentPane(permissionInformationView()); // Wrong Way! =S
					frame.pack();
					frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
			}
		});
		PermissionScrollPane.setViewportView(PermissionList);
		PermissionList.setModel(new AbstractListModel() {
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
		JPanel windowPanelPermission= new JPanel();
		windowPanelPermission.setLayout(new BorderLayout(0, 0));
		windowPanelPermission.add(menuPanelPermission, BorderLayout.NORTH);
		windowPanelPermission.add(mainPanelPermission, BorderLayout.CENTER);


		return windowPanelPermission;
	}
	//////////////////////////////////////////////////////////////////
	// Permission Information View						            //
	//////////////////////////////////////////////////////////////////

	private JPanel permissionInformationView(){
		return null;
	}

	//////////////////////////////////////////////////////////////////
	//   Add Permission View						        //
	//////////////////////////////////////////////////////////////////

	private JPanel addPermissionView(){

		return null;
	}

	//////////////////////////////////////////////////////////////////
	//   Edit Permission View						        //
	//////////////////////////////////////////////////////////////////

	private JPanel editPermissionView(){
		return null;

	}


	public static void main (String[] args) {
		HKJ_SisCA_MainPage  window = new HKJ_SisCA_MainPage();
		window.setVisible();

	}


}
