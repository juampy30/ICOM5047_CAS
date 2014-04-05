import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
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
import javax.swing.ListModel;
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
	static String username;
	private static DBManager dbman;
	private ArrayList<Object> availableSAD;
	private ArrayList<Object> availableAtzType;

	private DefaultListModel availableSADModelList;
	private DefaultListModel availableAtzTypesModelList;
	private DefaultListModel chosenSADModelList;
	private DefaultListModel chosenAtzTypesModelList;


	static String loggedUsernane;
	static String loggedUsernaneWith;
	static Boolean canView;




	private ArrayList<Object> registerParkings;

	private String[] availableSadList;
	private String[] availableAtzTypeList;

	private DefaultListModel registerParkingsList;






	// HKJ_SisCA_MainPage Constructor
	public HKJ_SisCA_MainPage(){

	}

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

	public static String getActiveUsername(){
		return loggedUsernane;
	}

	public static Boolean getCanView(){

		Boolean canView= false;
		String query= "Select * from sisca_account where sisca_account_username="+loggedUsernaneWith;
		String result=null;

		try {
			dbman= new DBManager();
			result= dbman.getAccountTypeFromDB(query);
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(loggedUsernaneWith+"Result Account Type"+ result);
		System.out.println("result==administrator"+ (result=="administrator"));
		
		if(result.equals("administrator")){
			canView= true;
		}


		return canView;
	}


	static void initializae() {
		frame= new JFrame();

		frame.getContentPane().add(LogInManager.standByView());
		frame.setResizable(true);
		frame.setTitle("SisCA");
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public static JPanel homeView(){

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
// Query for get Parking Names for the Labels
////////////////////////////////////////////////////////////////////////////////////
ArrayList pNameLabelsArray = new ArrayList() ;
try {
dbman= new DBManager();

pNameLabelsArray=dbman.getFromDB("Select * from sisca_parking ORDER BY sisca_parking_name");
//System.out.println("Before Test:"+pNameLabelsArray);
pNameLabelsArray= dbman.getRegisterParkingsLiveSystem(pNameLabelsArray);
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
HKJ_SisCA_MainPage.frame.setContentPane(ParkingManager.parkingInformationView());
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
HKJ_SisCA_MainPage.frame.setContentPane(ParkingManager.parkingInformationView());
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
HKJ_SisCA_MainPage.frame.setContentPane(ParkingManager.parkingInformationView());
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
HKJ_SisCA_MainPage.frame.setContentPane(ParkingManager.parkingInformationView());
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
HKJ_SisCA_MainPage.frame.setContentPane(ParkingManager.parkingInformationView());
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
HKJ_SisCA_MainPage.frame.setContentPane(ParkingManager.parkingInformationView());
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
HKJ_SisCA_MainPage.frame.setContentPane(ParkingManager.parkingInformationView());
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
HKJ_SisCA_MainPage.frame.setContentPane(ParkingManager.parkingInformationView());
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
HKJ_SisCA_MainPage.frame.setContentPane(ParkingManager.parkingInformationView());
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
HKJ_SisCA_MainPage.frame.setContentPane(ParkingManager.parkingInformationView());
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
HKJ_SisCA_MainPage.frame.setContentPane(ParkingManager.parkingView());
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
HKJ_SisCA_MainPage.frame.setContentPane(ParkingManager.parkingView());
HKJ_SisCA_MainPage.frame.pack(); 
HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

}
});
viewAndAddBynPanel.add(viewAllButton);

JButton addNewButton = new JButton("Add New Parking");
addNewButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent arg0) {
HKJ_SisCA_MainPage.frame.setContentPane(ParkingManager.addParkingView());
HKJ_SisCA_MainPage.frame.pack(); 
HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

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
				frame.setContentPane(ParkingManager.parkingView());
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
				frame.setContentPane(AccountManager.accountView());
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
				frame.setContentPane(AuthorizationTypeManager.authorizationTypeView());
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
		windowPanel.add(leftPanelParkingInformation, BorderLayout.WEST);
		windowPanel.add(mainPanel, BorderLayout.CENTER);
		return windowPanel;
	}







	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//   																													    //
	//                                                   MAIN                                                                   //
	//  																														//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main (String[] args) {
		HKJ_SisCA_MainPage  window = new HKJ_SisCA_MainPage();
		window.initializae();
		

	}




}
