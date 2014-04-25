import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;
import databases.DBManager;


public class HKJ_SisCAWizard {

	private JFrame frame;
	private JPanel mainPanel;
	private JPanel setUpStatusPanel;
	private JPanel mainWindow;
	private DBManager dbman;

	JPanel serverConfigurationWindow;
	JPanel authorizationTypesWindow;
	JPanel parkingAndSADConfigurationWindow;
	JPanel accountADMINConfigurationWindow;
	JPanel accountConfigurationWindow;
	ImageIcon image1;

	String serverIPAddress;
	String productKey;
	String companyName;


	public static void main(String[] args) {
		HKJ_SisCAWizard window = new HKJ_SisCAWizard();
		window.setVisible();
	}

	public HKJ_SisCAWizard(){
		intialize();
	}


	public void setVisible() {
		this.frame.setVisible(true);
	}

	private void intialize() {


		// Variables Initialization
		frame= new JFrame();
		mainPanel= new JPanel();
		setUpStatusPanel= new JPanel();
		JTextPane txtpnTheInstallationWizard = new JTextPane();
		final JTextField textFieldCompanyName ;
		final JTextField textFieldProductKey;
		image1=  new ImageIcon("/Users/JuanPablo/Documents/HKJ_SisCA/GUI/Icons/logo5.png");
		mainWindow= new JPanel();

		// Main Window Configuration

		mainWindow.setBackground(new Color(245, 245, 245));
		mainWindow.setBounds(0, 0, 600, 450);
		mainWindow.setLayout(null);

		// Main Panel Configuration
		mainPanel.setBackground(new Color(242,242,242));
		mainPanel.setBounds(161, 0, 439, 428);
		mainPanel.setLayout(null);
		mainPanel.setBorder(null);


		// Main Panel Content
		JLabel lblCompanyName = new JLabel("     Company Name:");
		lblCompanyName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompanyName.setHorizontalTextPosition(SwingConstants.LEADING);
		lblCompanyName.setBounds(6, 125, 135, 16);
		mainPanel.add(lblCompanyName);
		JLabel lblNewLabel = new JLabel("Welcome to the installation wizard for SisCA ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 34, 427, 16);
		mainPanel.add(lblNewLabel);
		txtpnTheInstallationWizard.setBackground(new Color(242, 242, 242));
		txtpnTheInstallationWizard.setEditable(false);
		txtpnTheInstallationWizard.setText("     The installation wizard will install the Central Administrator\n     Software on your computer. To continue, please, \n     fill the blanks and click NEXT. \r\n");
		txtpnTheInstallationWizard.setBounds(6, 62, 427, 51);
		mainPanel.add(txtpnTheInstallationWizard);

		JLabel lblProductKey = new JLabel("     Product Key:");
		lblProductKey.setHorizontalTextPosition(SwingConstants.LEADING);
		lblProductKey.setHorizontalAlignment(SwingConstants.LEFT);
		lblProductKey.setBounds(6, 153, 135, 16);
		mainPanel.add(lblProductKey);

		textFieldCompanyName = new JTextField(companyName);
		textFieldCompanyName.setBounds(136, 119, 210, 28);
		mainPanel.add(textFieldCompanyName);
		textFieldCompanyName.setColumns(10);

		textFieldProductKey = new JTextField(productKey);
		textFieldProductKey.setBounds(116, 147, 230, 28);
		mainPanel.add(textFieldProductKey);
		textFieldProductKey.setColumns(10);

		JButton btnCancelButton = new JButton("Cancel");
		btnCancelButton.setBounds(316, 393, 117, 29);
		btnCancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");
				if(sure==0){
					frame.setVisible(false);
				}

			}
		});
		mainPanel.add(btnCancelButton);

		JButton btnNextButton_1 = new JButton("Next");
		btnNextButton_1.setBounds(187, 393, 117, 29);
		btnNextButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(textFieldCompanyName.getText().isEmpty()||textFieldProductKey.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Invalid or incomplete input data! Please, verify your informtion.");
				}
				else{
					companyName= textFieldCompanyName.getText();
					productKey= textFieldProductKey.getText();
					frame.setContentPane(serverConfigurationWindow());
				}

			}
		});
		mainPanel.add(btnNextButton_1);


		// SetUp Status Panel Configuration
		setUpStatusPanel.setBackground(new Color(232,232,232));
		setUpStatusPanel.setBounds(0, 0, 163, 428);
		setUpStatusPanel.setBorder(null);
		setUpStatusPanel.setLayout(new MigLayout("", "[136px]", "[110.00][125][125][125][400]"));

		JLabel lblNewLabel_1 = new JLabel("Welcome");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		setUpStatusPanel.add(lblNewLabel_1, "cell 0 1,growx,aligny center");

		JLabel lblNewLabel_2 = new JLabel("Configuration");
		setUpStatusPanel.add(lblNewLabel_2, "cell 0 2,growx,aligny center");

		JLabel lblNewLabel_4 = new JLabel("Complete");

		setUpStatusPanel.add(lblNewLabel_4, "cell 0 3,growx,aligny center");

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(image1);
		setUpStatusPanel.add(lblNewLabel_5, "cell 0 4,growx,aligny bottom");

		// Main Window Add's
		mainWindow.add(mainPanel);
		mainWindow.add(setUpStatusPanel);



		// Frame Add's
		frame.getContentPane().add(mainWindow);
		//frame.getContentPane().add(setUpStatusPanel);


		// Frame configuration
		frame.setBounds(100, 100, 600, 450);
		frame.setResizable(false);
		frame.setBackground(new Color(242,242,242));
		frame.setTitle("SisCA Setup Wizard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

	}



	protected JPanel serverConfigurationWindow() {

		// Variables
		serverConfigurationWindow= new JPanel();
		JPanel serverConfigurationMainPanel= new JPanel();
		JPanel serverConfigurationSetupPanel= new JPanel();
		JTextPane serverConfigurationTextPane = new JTextPane();
		final JTextField textFieldServerIPAddress;

		// Window Panel Configuration
		serverConfigurationWindow.setBackground(new Color(245, 245, 245));
		serverConfigurationWindow.setBounds(0, 0, 600, 450);
		serverConfigurationWindow.setLayout(null);


		// Server Configuration Main Panel

		// Configuration
		serverConfigurationMainPanel.setBackground(new Color(242,242,242));
		serverConfigurationMainPanel.setBounds(161, 0, 439, 428);
		serverConfigurationMainPanel.setLayout(null);
		serverConfigurationMainPanel.setBorder(null);

		// Content
		JLabel lblCompanyName = new JLabel("     Server IP Address:");
		lblCompanyName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompanyName.setHorizontalTextPosition(SwingConstants.LEADING);
		lblCompanyName.setBounds(6, 111, 135, 16);
		serverConfigurationMainPanel.add(lblCompanyName);
		JLabel lblNewLabel = new JLabel("Server IP Address Configuration ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 34, 427, 16);
		serverConfigurationMainPanel.add(lblNewLabel);
		serverConfigurationTextPane.setBackground(new Color(242, 242, 242));
		serverConfigurationTextPane.setEditable(false);
		serverConfigurationTextPane.setText("     Set the Server IP Address for the devices (SADÕs).");
		serverConfigurationTextPane.setBounds(6, 62, 427, 28);
		serverConfigurationMainPanel.add(serverConfigurationTextPane);

		textFieldServerIPAddress = new JTextField(serverIPAddress);
		textFieldServerIPAddress.setBounds(153, 105, 257, 28);
		serverConfigurationMainPanel.add(textFieldServerIPAddress);
		textFieldServerIPAddress.setColumns(10);

		JButton btnCancelButton = new JButton("Cancel");
		btnCancelButton.setBounds(316, 393, 117, 29);
		btnCancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");
				if(sure==0){
					frame.setVisible(false);
				}
			}
		});
		serverConfigurationMainPanel.add(btnCancelButton);

		JButton btnNextButton_1 = new JButton("Next");
		btnNextButton_1.setBounds(187, 393, 117, 29);
		btnNextButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				if(textFieldServerIPAddress.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Invalid or incomplete input data! Please, verify your informtion.");
				}
				else{
					serverIPAddress= textFieldServerIPAddress.getText();
					frame.setContentPane(accountADMINConfigurationWindow());
				}

			}
		});
		serverConfigurationMainPanel.add(btnNextButton_1);

		JButton btnBackButton_2 = new JButton("Back");
		btnBackButton_2.setBounds(58, 393, 117, 29);
		btnBackButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				serverIPAddress= textFieldServerIPAddress.getText();
				frame.setContentPane(mainWindow);
			}
		});
		serverConfigurationMainPanel.add(btnBackButton_2);

		// SetUp Status Panel Configuration
		JPanel setUpStatusPanelServer = new JPanel();
		setUpStatusPanelServer.setBackground(new Color(232,232,232));
		setUpStatusPanelServer.setBounds(0, 0, 163, 428);
		setUpStatusPanelServer.setBorder(null);
		setUpStatusPanelServer.setLayout(new MigLayout("", "[136px]", "[110.00][125][125][125][400]"));

		JLabel lblNewLabel_11 = new JLabel("Welcome");
		setUpStatusPanelServer.add(lblNewLabel_11, "cell 0 1,growx,aligny center");

		JLabel lblNewLabel_21 = new JLabel("Configuration");
		lblNewLabel_21.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		setUpStatusPanelServer.add(lblNewLabel_21, "cell 0 2,growx,aligny center");

		JLabel lblNewLabel_41 = new JLabel("Complete");
		setUpStatusPanelServer.add(lblNewLabel_41, "cell 0 3,growx,aligny center");

		JLabel lblNewLabel_51 = new JLabel("");
		lblNewLabel_51.setIcon(image1);
		setUpStatusPanelServer.add(lblNewLabel_51, "cell 0 4,growx,aligny bottom");

		// Window Panel Add's
		serverConfigurationWindow.add(serverConfigurationMainPanel);
		serverConfigurationWindow.add(setUpStatusPanelServer);


		return serverConfigurationWindow;

	}

	//	protected JPanel authorizationTypesWindow() {
	//
	//		authorizationTypesWindow= new JPanel();
	//		JPanel authorizationTypesConfigurationMainPanel= new JPanel();
	//		JPanel authorizationTypesConfigurationSetupPanel= new JPanel();
	//		JTextPane authorizationTypesConfigurationTextPane = new JTextPane();
	//		final JTextField textFieldAuthorizationTypes;
	//
	//
	//		// Window Configuration
	//
	//		authorizationTypesWindow.setBackground(new Color(245, 245, 245));
	//		authorizationTypesWindow.setBounds(0, 0, 600, 450);
	//		authorizationTypesWindow.setLayout(null);
	//
	//		// Authorization Types Panel Configuration
	//		authorizationTypesConfigurationMainPanel.setBackground(new Color(242,242,242));
	//		authorizationTypesConfigurationMainPanel.setBounds(161, 0, 439, 428);
	//		authorizationTypesConfigurationMainPanel.setLayout(null);
	//		authorizationTypesConfigurationMainPanel.setBorder(null);
	//
	//		// Authorization Types Panel Content
	//		JLabel lblCompanyName = new JLabel("     Name:");
	//		lblCompanyName.setHorizontalAlignment(SwingConstants.LEFT);
	//		lblCompanyName.setHorizontalTextPosition(SwingConstants.LEADING);
	//		lblCompanyName.setBounds(6, 111, 135, 16);
	//		authorizationTypesConfigurationMainPanel.add(lblCompanyName);
	//		JLabel lblNewLabel = new JLabel("Authorization Types Configuration\r\n");
	//		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
	//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	//		lblNewLabel.setBounds(6, 34, 427, 16);
	//		authorizationTypesConfigurationMainPanel.add(lblNewLabel);
	//		authorizationTypesConfigurationTextPane.setBackground(new Color(242, 242, 242));
	//		authorizationTypesConfigurationTextPane.setEditable(false);
	//		authorizationTypesConfigurationTextPane.setText("     Configure different types of parking authorizations.\r\n");
	//		authorizationTypesConfigurationTextPane.setBounds(6, 62, 427, 28);
	//		authorizationTypesConfigurationMainPanel.add(authorizationTypesConfigurationTextPane);
	//
	//		textFieldAuthorizationTypes = new JTextField();
	//		textFieldAuthorizationTypes.setBounds(78, 105, 232, 28);
	//		authorizationTypesConfigurationMainPanel.add(textFieldAuthorizationTypes);
	//		textFieldAuthorizationTypes.setColumns(10);
	//
	//		JButton btnCancelButton = new JButton("Cancel");
	//		btnCancelButton.setBounds(334, 393, 99, 29);
	//		btnCancelButton.addActionListener(new ActionListener() {
	//			public void actionPerformed(ActionEvent arg0) {
	//				frame.setVisible(false); // OJO: Es as’?
	//			}
	//		});
	//		authorizationTypesConfigurationMainPanel.add(btnCancelButton);
	//
	//		JButton btnNextButton_1 = new JButton("Next");
	//		btnNextButton_1.setBounds(235, 393, 99, 29);
	//		btnNextButton_1.addActionListener(new ActionListener() {
	//			public void actionPerformed(ActionEvent arg0) {
	//				if(textFieldAuthorizationTypes.getText().isEmpty()){
	//					JOptionPane.showMessageDialog(null,"Invalid or incomplete input data! Please, verify your informtion or complete installation.");
	//				}
	//				else{
	//					try {
	//						dbman= new DBManager();
	//						
	//						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	//						Calendar cal = Calendar.getInstance();
	//						String authorizationName= "'"+ textFieldAuthorizationTypes.getText()+ "'";
	//
	//						String creationDate= "'"+dateFormat.format(cal.getTime())+"'";
	//						String createdBy= "'INITIAL CONFIGURATION'";
	//						
	//						String query= "INSERT INTO sisca_authorization (sisca_authorization_name, sisca_authorization_active, sisca_authorization_creationdate, sisca_authorization_createdby) "
	//								+ "VALUES( "+ authorizationName+", 'true', "+creationDate+", "+createdBy+")";
	//						dbman.insertDB(query);
	//						
	//					} catch (ClassNotFoundException e) {
	//						// TODO Auto-generated catch block
	//						e.printStackTrace();
	//					} catch (SQLException e) {
	//						// TODO Auto-generated catch block
	//						e.printStackTrace();
	//					}
	//	
	//					frame.setContentPane(parkingAndSADConfigurationWindow());
	//				}
	//				
	//			}
	//		});
	//		authorizationTypesConfigurationMainPanel.add(btnNextButton_1);
	//
	//		JButton btnBackButton_2 = new JButton("Back");
	//		btnBackButton_2.setBounds(131, 393, 99, 29);
	//		btnBackButton_2.addActionListener(new ActionListener() {
	//			public void actionPerformed(ActionEvent arg0) {
	//				frame.setContentPane(serverConfigurationWindow);
	//			}
	//		});
	//		authorizationTypesConfigurationMainPanel.add(btnBackButton_2);
	//
	//		JButton btnNewButton = new JButton("Add More");
	//		btnNewButton.setBounds(316, 106, 117, 29);
	//		btnNewButton.addActionListener(new ActionListener() {
	//			public void actionPerformed(ActionEvent arg0) {
	//				try {
	//					dbman= new DBManager();
	//					
	//					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	//					Calendar cal = Calendar.getInstance();
	//					String authorizationName= "'"+ textFieldAuthorizationTypes.getText()+ "'";
	//
	//					String creationDate= "'"+dateFormat.format(cal.getTime())+"'";
	//					String createdBy= "'INITIAL CONFIGURATION'";
	//					
	//					String query= "INSERT INTO sisca_authorization (sisca_authorization_name, sisca_authorization_active, sisca_authorization_creationdate, sisca_authorization_createdby) "
	//							+ "VALUES( "+ authorizationName+", 'true', "+creationDate+", "+createdBy+")";
	//					dbman.insertDB(query);
	//					
	//				} catch (ClassNotFoundException e) {
	//					// TODO Auto-generated catch block
	//					e.printStackTrace();
	//				} catch (SQLException e) {
	//					// TODO Auto-generated catch block
	//					e.printStackTrace();
	//				}
	//				textFieldAuthorizationTypes.setText("");
	//				frame.setContentPane(authorizationTypesWindow);
	//			}
	//		});
	//		authorizationTypesConfigurationMainPanel.add(btnNewButton);
	//		
	//		JButton btnAddMore = new JButton("Complete");
	//		btnAddMore.setBounds(0, 393, 132, 29);
	//		btnAddMore.addActionListener(new ActionListener() {
	//			public void actionPerformed(ActionEvent arg0) {
	//				frame.setContentPane(completeConfigurationWindow());
	//			}
	//		});
	//		authorizationTypesConfigurationMainPanel.add(btnAddMore);
	//		
	//
	//		// SetUp Status Panel Configuration
	//		authorizationTypesConfigurationSetupPanel.setBackground(new Color(232,232,232));
	//		authorizationTypesConfigurationSetupPanel.setBounds(0, 0, 163, 428);
	//		authorizationTypesConfigurationSetupPanel.setLayout(null);
	//		authorizationTypesConfigurationSetupPanel.setBorder(null);
	//
	//		JLabel lblNewLabel_1 = new JLabel("Welcome");
	//		lblNewLabel_1.setBounds(8, 43, 136, 16);
	//		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	//		authorizationTypesConfigurationSetupPanel.add(lblNewLabel_1);
	//
	//		JLabel lblNewLabel_2 = new JLabel("Configuration");
	//		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
	//		lblNewLabel_2.setBounds(8, 64, 136, 16);
	//		authorizationTypesConfigurationSetupPanel.add(lblNewLabel_2);
	//
	//		JLabel lblNewLabel_3 = new JLabel("Installing");
	//		lblNewLabel_3.setBounds(8, 85, 136, 16);
	//		authorizationTypesConfigurationSetupPanel.add(lblNewLabel_3);
	//
	//		JLabel lblNewLabel_4 = new JLabel("Complete Setup");
	//		lblNewLabel_4.setBounds(8, 106, 136, 16);
	//		authorizationTypesConfigurationSetupPanel.add(lblNewLabel_4);
	//
	//		JLabel lblNewLabel_5 = new JLabel("");
	//		lblNewLabel_5.setBounds(8, 298, 136, 116);
	//		lblNewLabel_5.setIcon(image1);
	//		authorizationTypesConfigurationSetupPanel.add(lblNewLabel_5);
	//
	//		// Main Window Add's
	//		authorizationTypesWindow.add(authorizationTypesConfigurationMainPanel);
	//		authorizationTypesWindow.add(authorizationTypesConfigurationSetupPanel);
	//
	//		return authorizationTypesWindow;
	//	}
	//
	//	protected Container parkingAndSADConfigurationWindow() {
	//		
	//		JPanel parkingAndSADConfigurationWindow= new JPanel();
	//		JPanel parkingAndSADConfigurationMainPanel= new JPanel();
	//		JPanel parkingAndSADConfigurationSetupPanel= new JPanel();
	//		JTextPane parkingAndSADConfigurationTextPane = new JTextPane();
	//		JTextField textField= new JTextField();
	//		JTextField textField_1;
	//		JButton btnNewButton = new JButton("Add Selected");
	//		JCheckBox chckbxEntry = new JCheckBox("Entry");
	//		JList list = new JList();
	//		JTextField textField_2;
	//		JTextField textField_3;
	//
	//
	//		// Window Configuration
	//
	//		parkingAndSADConfigurationWindow.setBackground(new Color(245, 245, 245));
	//		parkingAndSADConfigurationWindow.setBounds(0, 0, 600, 428);
	//		parkingAndSADConfigurationWindow.setLayout(null);
	//
	//		// Parking and SAD Panel Configuration
	//		parkingAndSADConfigurationMainPanel.setBackground(new Color(242,242,242));
	//		parkingAndSADConfigurationMainPanel.setBounds(161, 0, 439, 428);
	//		parkingAndSADConfigurationMainPanel.setLayout(null);
	//		parkingAndSADConfigurationMainPanel.setBorder(null);
	//
	//		// Parking and SAD Panel Content
	//		JLabel lblCompanyName = new JLabel("Name:");
	//		lblCompanyName.setHorizontalAlignment(SwingConstants.LEFT);
	//		lblCompanyName.setHorizontalTextPosition(SwingConstants.LEADING);
	//		lblCompanyName.setBounds(16, 94, 69, 16);
	//		parkingAndSADConfigurationMainPanel.add(lblCompanyName);
	//		JLabel lblNewLabel = new JLabel("Parking and SAD's Configuration\r\n");
	//		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
	//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	//		lblNewLabel.setBounds(6, 34, 427, 16);
	//		parkingAndSADConfigurationMainPanel.add(lblNewLabel);
	//		parkingAndSADConfigurationTextPane.setBackground(new Color(242, 242, 242));
	//		parkingAndSADConfigurationTextPane.setEditable(false);
	//		parkingAndSADConfigurationTextPane.setText("     Configure different parking and SAD's.\r\n");
	//		parkingAndSADConfigurationTextPane.setBounds(6, 62, 427, 28);
	//		parkingAndSADConfigurationMainPanel.add(parkingAndSADConfigurationTextPane);
	//
	//		textField = new JTextField();
	//		textField.setBounds(64, 88, 161, 28);
	//		parkingAndSADConfigurationMainPanel.add(textField);
	//		textField.setColumns(10);
	//
	//		JButton btnCancelButton = new JButton("Cancel");
	//		btnCancelButton.setBounds(334, 393, 99, 29);
	//		btnCancelButton.addActionListener(new ActionListener() {
	//			public void actionPerformed(ActionEvent arg0) {
	//				frame.setVisible(false); // OJO: Es as’?
	//			}
	//		});
	//		parkingAndSADConfigurationMainPanel.add(btnCancelButton);
	//
	//		JButton btnNextButton_1 = new JButton("Next");
	//		btnNextButton_1.setBounds(235, 393, 99, 29);
	//		btnNextButton_1.addActionListener(new ActionListener() {
	//			public void actionPerformed(ActionEvent arg0) {
	//				frame.setContentPane(accountADMINConfigurationWindow());
	//			}
	//		});
	//		parkingAndSADConfigurationMainPanel.add(btnNextButton_1);
	//
	//		JButton btnBackButton_2 = new JButton("Back");
	//		btnBackButton_2.setBounds(131, 393, 99, 29);
	//		btnBackButton_2.addActionListener(new ActionListener() {
	//			public void actionPerformed(ActionEvent arg0) {
	//				frame.setContentPane(authorizationTypesWindow());
	//			}
	//		});
	//		parkingAndSADConfigurationMainPanel.add(btnBackButton_2);
	//		
	//		JButton btnAddMore = new JButton("Save & Add More");
	//		btnAddMore.setBounds(0, 393, 132, 29);
	//		parkingAndSADConfigurationMainPanel.add(btnAddMore);
	//		btnAddMore.addActionListener(new ActionListener() {
	//			public void actionPerformed(ActionEvent arg0) {
	//				frame.setContentPane(parkingAndSADConfigurationWindow());
	//			}
	//		});
	//		parkingAndSADConfigurationWindow.add(parkingAndSADConfigurationSetupPanel);
	//		
	//		JLabel lblCapacity = new JLabel("Capacity:");
	//		lblCapacity.setHorizontalTextPosition(SwingConstants.LEADING);
	//		lblCapacity.setHorizontalAlignment(SwingConstants.LEFT);
	//		lblCapacity.setBounds(235, 94, 69, 16);
	//		parkingAndSADConfigurationMainPanel.add(lblCapacity);
	//
	//		textField_1 = new JTextField();
	//		textField_1.setColumns(10);
	//		textField_1.setBounds(299, 88, 134, 28);
	//		parkingAndSADConfigurationMainPanel.add(textField_1);
	//
	//		JLabel lblAuthorizationTypes = new JLabel("Authorization Types(s):");
	//		lblAuthorizationTypes.setHorizontalTextPosition(SwingConstants.LEADING);
	//		lblAuthorizationTypes.setHorizontalAlignment(SwingConstants.CENTER);
	//		lblAuthorizationTypes.setBounds(16, 122, 192, 16);
	//		parkingAndSADConfigurationMainPanel.add(lblAuthorizationTypes);
	//		btnNewButton.setBounds(42, 194, 133, 29);
	//		parkingAndSADConfigurationMainPanel.add(btnNewButton);
	//
	//		JButton btnRemoveSelected = new JButton("Remove Selected");
	//		btnRemoveSelected.setBounds(41, 270, 134, 29);
	//		parkingAndSADConfigurationMainPanel.add(btnRemoveSelected);
	//
	//		JLabel lblSadsList = new JLabel("SAD's:");
	//		lblSadsList.setHorizontalTextPosition(SwingConstants.LEADING);
	//		lblSadsList.setHorizontalAlignment(SwingConstants.CENTER);
	//		lblSadsList.setBounds(235, 122, 186, 16);
	//		parkingAndSADConfigurationMainPanel.add(lblSadsList);
	//
	//		JButton btnAddSadConfiguration = new JButton("Add");
	//		btnAddSadConfiguration.setBounds(346, 194, 75, 29);
	//		parkingAndSADConfigurationMainPanel.add(btnAddSadConfiguration);
	//
	//		JButton button_1 = new JButton("Remove Selected");
	//		button_1.setBounds(261, 270, 134, 29);
	//		parkingAndSADConfigurationMainPanel.add(button_1);
	//		chckbxEntry.setBounds(235, 195, 64, 23);
	//		parkingAndSADConfigurationMainPanel.add(chckbxEntry);
	//
	//		JCheckBox chckbxExit = new JCheckBox("Exit");
	//		chckbxExit.setBounds(291, 195, 56, 23);
	//		parkingAndSADConfigurationMainPanel.add(chckbxExit);
	//
	//		JScrollPane scrollPane = new JScrollPane();
	//		scrollPane.setBounds(42, 145, 133, 47);
	//		parkingAndSADConfigurationMainPanel.add(scrollPane);
	//		scrollPane.setViewportView(list);
	//		list.setModel(new AbstractListModel() {
	//			String[] values = new String[] {"Authorization 1", "Authorization 2", "Authorization 3", "Authorization 4", "Authorization 5", "Authorization 6", "Authorization 7"};
	//			public int getSize() {
	//				return values.length;
	//			}
	//			public Object getElementAt(int index) {
	//				return values[index];
	//			}
	//		});
	//		list.setToolTipText("Authorization 1\nAuthorization 2\nAuthorization 3\nAuthorization 4\nAuthorization 5\nAuthorization 6\nAuthorization 7");
	//
	//		JScrollPane scrollPane_1 = new JScrollPane();
	//		scrollPane_1.setBounds(42, 222, 133, 47);
	//		parkingAndSADConfigurationMainPanel.add(scrollPane_1);
	//
	//		JList list_1 = new JList();
	//		scrollPane_1.setViewportView(list_1);
	//		list_1.setModel(new AbstractListModel() {
	//			String[] values = new String[] {"Authorization 1", "Authorization 2", "Authorization 3"};
	//			public int getSize() {
	//				return values.length;
	//			}
	//			public Object getElementAt(int index) {
	//				return values[index];
	//			}
	//		});
	//
	//		JScrollPane scrollPane_2 = new JScrollPane();
	//		scrollPane_2.setBounds(262, 145, 133, 47);
	//		parkingAndSADConfigurationMainPanel.add(scrollPane_2);
	//
	//		JList list_2 = new JList();
	//		scrollPane_2.setViewportView(list_2);
	//		list_2.setModel(new AbstractListModel() {
	//			String[] values = new String[] {"SAD 1", "SAD 2", "SAD 3", "SAD 4", "SAD 5", "SAD 6", "SAD 7"};
	//			public int getSize() {
	//				return values.length;
	//			}
	//			public Object getElementAt(int index) {
	//				return values[index];
	//			}
	//		});
	//
	//		JScrollPane scrollPane_3 = new JScrollPane();
	//		scrollPane_3.setBounds(261, 222, 133, 47);
	//		parkingAndSADConfigurationMainPanel.add(scrollPane_3);
	//
	//		JList list_3 = new JList();
	//		scrollPane_3.setViewportView(list_3);
	//		list_3.setModel(new AbstractListModel() {
	//			String[] values = new String[] {"SAD 1- Entry", "SAD 2- Exit"};
	//			public int getSize() {
	//				return values.length;
	//			}
	//			public Object getElementAt(int index) {
	//				return values[index];
	//			}
	//		});
	//
	//		JLabel lblOperationDays = new JLabel("Operation Days:");
	//		lblOperationDays.setHorizontalTextPosition(SwingConstants.LEADING);
	//		lblOperationDays.setHorizontalAlignment(SwingConstants.CENTER);
	//		lblOperationDays.setBounds(42, 301, 133, 16);
	//		parkingAndSADConfigurationMainPanel.add(lblOperationDays);
	//
	//		JLabel lblOperationHours = new JLabel("Operation Hours:");
	//		lblOperationHours.setHorizontalTextPosition(SwingConstants.LEADING);
	//		lblOperationHours.setHorizontalAlignment(SwingConstants.CENTER);
	//		lblOperationHours.setBounds(262, 301, 133, 16);
	//		parkingAndSADConfigurationMainPanel.add(lblOperationHours);
	//
	//		JLabel lblStart = new JLabel("Start:");
	//		lblStart.setHorizontalTextPosition(SwingConstants.LEADING);
	//		lblStart.setHorizontalAlignment(SwingConstants.LEFT);
	//		lblStart.setBounds(272, 329, 35, 16);
	//		parkingAndSADConfigurationMainPanel.add(lblStart);
	//
	//		JLabel lblEnd = new JLabel("End:");
	//		lblEnd.setHorizontalTextPosition(SwingConstants.LEADING);
	//		lblEnd.setHorizontalAlignment(SwingConstants.LEFT);
	//		lblEnd.setBounds(272, 357, 35, 16);
	//		parkingAndSADConfigurationMainPanel.add(lblEnd);
	//
	//		textField_2 = new JTextField();
	//		textField_2.setColumns(10);
	//		textField_2.setBounds(309, 323, 75, 28);
	//		//textField_2.setText("HH:MM:YYYY");
	//		parkingAndSADConfigurationMainPanel.add(textField_2);
	//
	//		textField_3 = new JTextField();
	//		textField_3.setColumns(10);
	//		//textField_3.setText("HH:MM:YYYY");
	//		textField_3.setBounds(310, 353, 75, 28);
	//		parkingAndSADConfigurationMainPanel.add(textField_3);
	//
	//		JCheckBox chckbxMon = new JCheckBox("Mon");
	//		chckbxMon.setBounds(33, 329, 53, 23);
	//		parkingAndSADConfigurationMainPanel.add(chckbxMon);
	//		chckbxMon.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
	//
	//		JCheckBox chckbxTue = new JCheckBox("Tue");
	//		chckbxTue.setBounds(33, 353, 50, 23);
	//		parkingAndSADConfigurationMainPanel.add(chckbxTue);
	//		chckbxTue.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
	//
	//		JCheckBox chckbxThu = new JCheckBox("Wen");
	//		chckbxThu.setBounds(81, 329, 53, 23);
	//		parkingAndSADConfigurationMainPanel.add(chckbxThu);
	//		chckbxThu.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
	//
	//		JCheckBox chckbxFri = new JCheckBox("Fri");
	//		chckbxFri.setBounds(131, 329, 47, 23);
	//		parkingAndSADConfigurationMainPanel.add(chckbxFri);
	//		chckbxFri.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
	//
	//		JCheckBox chckbxSun = new JCheckBox("Sun");
	//		chckbxSun.setBounds(176, 329, 49, 23);
	//		parkingAndSADConfigurationMainPanel.add(chckbxSun);
	//		chckbxSun.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
	//
	//		JCheckBox chckbxSat = new JCheckBox("Sat");
	//		chckbxSat.setBounds(131, 353, 47, 23);
	//		parkingAndSADConfigurationMainPanel.add(chckbxSat);
	//		chckbxSat.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
	//
	//		JCheckBox chckbxThu_1 = new JCheckBox("Thu");
	//		chckbxThu_1.setBounds(81, 353, 50, 23);
	//		parkingAndSADConfigurationMainPanel.add(chckbxThu_1);
	//		chckbxThu_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
	//		parkingAndSADConfigurationWindow.add(parkingAndSADConfigurationSetupPanel);
	//
	//
	//		// SetUp Status Panel Configuration
	//		parkingAndSADConfigurationSetupPanel.setBackground(new Color(232,232,232));
	//		parkingAndSADConfigurationSetupPanel.setBounds(0, 0, 163, 428);
	//		parkingAndSADConfigurationSetupPanel.setLayout(null);
	//		parkingAndSADConfigurationSetupPanel.setBorder(null);
	//
	//		JLabel lblNewLabel_1 = new JLabel("Welcome");
	//		lblNewLabel_1.setBounds(8, 43, 136, 16);
	//		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	//		parkingAndSADConfigurationSetupPanel.add(lblNewLabel_1);
	//
	//		JLabel lblNewLabel_2 = new JLabel("Configuration");
	//		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
	//		lblNewLabel_2.setBounds(8, 64, 136, 16);
	//		parkingAndSADConfigurationSetupPanel.add(lblNewLabel_2);
	//
	//		JLabel lblNewLabel_3 = new JLabel("Installing");
	//		lblNewLabel_3.setBounds(8, 85, 136, 16);
	//		parkingAndSADConfigurationSetupPanel.add(lblNewLabel_3);
	//
	//		JLabel lblNewLabel_4 = new JLabel("Complete Setup");
	//		lblNewLabel_4.setBounds(8, 106, 136, 16);
	//		parkingAndSADConfigurationSetupPanel.add(lblNewLabel_4);
	//
	//		JLabel lblNewLabel_5 = new JLabel("");
	//		lblNewLabel_5.setBounds(8, 298, 136, 116);
	//		lblNewLabel_5.setIcon(image1);
	//		parkingAndSADConfigurationSetupPanel.add(lblNewLabel_5);
	//
	//		// Main Window Add's
	//		parkingAndSADConfigurationWindow.add(parkingAndSADConfigurationMainPanel);
	//
	//		return parkingAndSADConfigurationWindow;
	//
	//	}

	protected Container accountADMINConfigurationWindow() {
		accountADMINConfigurationWindow= new JPanel();
		JPanel accountADMINConfigurationMainPanel= new JPanel();
		JPanel accountADMINConfigurationSetupPanel= new JPanel();
		JTextPane accountADMINConfigurationTextPane = new JTextPane();
		final JTextField textFieldFirstName;
		final JTextField textFieldLastName;
		final JTextField textFieldUsername;
		final JPasswordField passwordField;


		// Window Configuration

		accountADMINConfigurationWindow.setBackground(new Color(245, 245, 245));
		accountADMINConfigurationWindow.setBounds(0, 0, 600, 428);
		accountADMINConfigurationWindow.setLayout(null);

		// Authorization Types Panel Configuration
		accountADMINConfigurationMainPanel.setBackground(new Color(242,242,242));
		accountADMINConfigurationMainPanel.setBounds(161, 0, 439, 428);
		accountADMINConfigurationMainPanel.setLayout(null);
		accountADMINConfigurationMainPanel.setBorder(null);

		// Authorization Types Panel Content
		JLabel lblCompanyName = new JLabel("First Name:");
		lblCompanyName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompanyName.setHorizontalTextPosition(SwingConstants.LEADING);
		lblCompanyName.setBounds(28, 111, 85, 16);
		accountADMINConfigurationMainPanel.add(lblCompanyName);
		JLabel lblNewLabel = new JLabel("Administrator Account Configuration\r\n");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 34, 427, 16);
		accountADMINConfigurationMainPanel.add(lblNewLabel);
		accountADMINConfigurationTextPane.setBackground(new Color(242, 242, 242));
		accountADMINConfigurationTextPane.setEditable(false);
		accountADMINConfigurationTextPane.setText("     Fills the blanks with the account information\n     for the administrator.\r\n\r\n");
		accountADMINConfigurationTextPane.setBounds(6, 62, 427, 37);
		accountADMINConfigurationMainPanel.add(accountADMINConfigurationTextPane);


		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalTextPosition(SwingConstants.LEADING);
		lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
		lblLastName.setBounds(28, 139, 85, 16);
		accountADMINConfigurationMainPanel.add(lblLastName);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalTextPosition(SwingConstants.LEADING);
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setBounds(28, 167, 85, 16);
		accountADMINConfigurationMainPanel.add(lblUsername);

		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(112, 133, 161, 28);
		accountADMINConfigurationMainPanel.add(textFieldLastName);

		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(112, 161, 161, 28);
		accountADMINConfigurationMainPanel.add(textFieldUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalTextPosition(SwingConstants.LEADING);
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setBounds(28, 195, 85, 16);
		accountADMINConfigurationMainPanel.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(112, 189, 161, 28);
		accountADMINConfigurationMainPanel.add(passwordField);

		JTextPane txtpnImportantIfYou = new JTextPane();
		txtpnImportantIfYou.setText("    IMPORTANT: If you want to save administrator account \n    and create new accounts accounts (administrators and \n    regular users), click NEXT. If not, click COMPLETE to save \n    administrator account and end with the configuration. \r\n\r\n\r\n");
		txtpnImportantIfYou.setEditable(false);
		txtpnImportantIfYou.setBackground(new Color(242, 242, 242));
		txtpnImportantIfYou.setBounds(6, 250, 427, 81);
		accountADMINConfigurationMainPanel.add(txtpnImportantIfYou);

		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(112, 105, 161, 28);
		accountADMINConfigurationMainPanel.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);

		JButton btnAddMore = new JButton("Complete");
		btnAddMore.setBounds(0, 393, 132, 29);
		btnAddMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldFirstName.getText().isEmpty()||textFieldLastName.getText().isEmpty()||textFieldUsername.getText().isEmpty()||passwordField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Invalid or incomplete input data! Please, verify your informtion or complete installation.");
				}
				else{
					try {
						dbman= new DBManager();

						String firstName= "'"+ textFieldFirstName.getText()+ "'";
						String lastName= "'"+ textFieldLastName.getText()+ "'";
						String username= "'"+ textFieldUsername.getText()+ "'";
						String password= "'"+ passwordField.getText()+ "'";

						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
						Calendar cal = Calendar.getInstance();
						String creationDate= "'"+dateFormat.format(cal.getTime())+"'";
						String createdBy= "'INITIAL CONFIGURATION'";

						String query= "INSERT INTO sisca_account (sisca_account_first_name, "
								+ "sisca_account_last_name, "
								+ "sisca_account_username, "
								+ "sisca_account_password, "
								+ "sisca_account_type, "
								+ "sisca_account_creationdate, "
								+ "sisca_account_createdby, "
								+ "sisca_account_active) "
								+ "VALUES( "+ firstName
								+", "+lastName
								+", "+username
								+", "+password
								+", 'administrator' "
								+", "+creationDate
								+", "+createdBy
								+", 'true')";
						dbman.insertDB(query);

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.setContentPane(completeConfigurationWindow());
				}



			}
		});
		accountADMINConfigurationMainPanel.add(btnAddMore);

		JButton btnCancelButton = new JButton("Cancel");
		btnCancelButton.setBounds(334, 393, 99, 29);
		btnCancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");
				if(sure==0){
					try {
						dbman= new DBManager();
						String query= "DELETE FROM sisca_account where sisca_account_id>=0";
						dbman.insertDB(query);

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.setVisible(false);
				}
			}
		});
		accountADMINConfigurationMainPanel.add(btnCancelButton);

		JButton btnNextButton_1 = new JButton("Next");
		btnNextButton_1.setBounds(235, 393, 99, 29);
		btnNextButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldFirstName.getText().isEmpty()||textFieldLastName.getText().isEmpty()||textFieldUsername.getText().isEmpty()||passwordField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Invalid or incomplete input data! Please, verify your informtion or complete installation.");
				}
				else{

					try {
						dbman= new DBManager();

						String firstName= "'"+ textFieldFirstName.getText()+ "'";
						String lastName= "'"+ textFieldLastName.getText()+ "'";
						String username= "'"+ textFieldUsername.getText()+ "'";
						String password= "'"+ passwordField.getText()+ "'";

						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
						Calendar cal = Calendar.getInstance();
						String creationDate= "'"+dateFormat.format(cal.getTime())+"'";
						String createdBy= "'INITIAL CONFIGURATION'";

						String query= "INSERT INTO sisca_account (sisca_account_first_name, "
								+ "sisca_account_last_name, "
								+ "sisca_account_username, "
								+ "sisca_account_password, "
								+ "sisca_account_type, "
								+ "sisca_account_creationdate, "
								+ "sisca_account_createdby, "
								+ "sisca_account_active) "
								+ "VALUES( "+ firstName
								+", "+lastName
								+", "+username
								+", "+password
								+", 'administrator' "
								+", "+creationDate
								+", "+createdBy
								+", 'true')";
						dbman.insertDB(query);

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


					frame.setContentPane(accountConfigurationWindow());
				}

			}
		});
		accountADMINConfigurationMainPanel.add(btnNextButton_1);

		JButton btnBackButton_2 = new JButton("Back");
		btnBackButton_2.setBounds(131, 393, 99, 29);
		btnBackButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(serverConfigurationWindow());
			}
		});
		accountADMINConfigurationMainPanel.add(btnBackButton_2);


		// SetUp Status Panel Configuration
		JPanel setUpStatusPaneldAminAccount = new JPanel();
		setUpStatusPaneldAminAccount.setBackground(new Color(232,232,232));
		setUpStatusPaneldAminAccount.setBounds(0, 0, 163, 428);
		setUpStatusPaneldAminAccount.setBorder(null);
		setUpStatusPaneldAminAccount.setLayout(new MigLayout("", "[136px]", "[110.00][125][125][125][400]"));

		JLabel lblNewLabel_11 = new JLabel("Welcome");
		setUpStatusPaneldAminAccount.add(lblNewLabel_11, "cell 0 1,growx,aligny center");

		JLabel lblNewLabel_21 = new JLabel("Configuration");
		lblNewLabel_21.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		setUpStatusPaneldAminAccount.add(lblNewLabel_21, "cell 0 2,growx,aligny center");

		JLabel lblNewLabel_41 = new JLabel("Complete");
		setUpStatusPaneldAminAccount.add(lblNewLabel_41, "cell 0 3,growx,aligny center");

		JLabel lblNewLabel_51 = new JLabel("");
		lblNewLabel_51.setIcon(image1);
		setUpStatusPaneldAminAccount.add(lblNewLabel_51, "cell 0 4,growx,aligny bottom");

		// Main Window Add's
		accountADMINConfigurationWindow.add(accountADMINConfigurationMainPanel);
		accountADMINConfigurationWindow.add(setUpStatusPaneldAminAccount);

		return accountADMINConfigurationWindow;


	}

	protected JPanel completeConfigurationWindow() {
		JPanel mainPanel= new JPanel();
		JPanel setUpStatusPanel= new JPanel();
		JTextPane txtpnTheInstallationWizard = new JTextPane();
		ImageIcon image1=  new ImageIcon("/Users/JuanPablo/Documents/HKJ_SisCA/GUI/Icons/logo5.png");
		JPanel mainWindow= new JPanel();

		// Main Window Configuration

		mainWindow.setBackground(new Color(245, 245, 245));
		mainWindow.setBounds(0, 0, 600, 450);
		mainWindow.setLayout(null);

		// Main Panel Configuration
		mainPanel.setBackground(new Color(242,242,242));
		mainPanel.setBounds(161, 0, 439, 428);
		mainPanel.setLayout(null);
		mainPanel.setBorder(null);
		JLabel lblNewLabel = new JLabel("SisCA installation wizard completed! ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 34, 427, 16);
		mainPanel.add(lblNewLabel);
		txtpnTheInstallationWizard.setBackground(new Color(242, 242, 242));
		txtpnTheInstallationWizard.setEditable(false);
		txtpnTheInstallationWizard.setText("     The installation wizard for SisCA has succesfully  installed.\n     Click finish to exit the wizard.");
		txtpnTheInstallationWizard.setBounds(6, 75, 427, 51);
		mainPanel.add(txtpnTheInstallationWizard);

		JButton btnCancelButton = new JButton("Finish");
		btnCancelButton.setBounds(316, 393, 117, 29);
		btnCancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					dbman= new DBManager();

					String cName= "'"+ companyName+ "'";
					String pKey= "'"+ productKey+ "'";
					String serverIP= "'"+ serverIPAddress+ "'";


					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Calendar cal = Calendar.getInstance();
					String creationDate= "'"+dateFormat.format(cal.getTime())+"'";

					String query= "INSERT INTO sisca_configuration_information (sisca_configuration_information_company_name, "
							+ " sisca_configuration_informatione_product_key, "
							+ "sisca_configuration_information_server_ip_address, "
							+ "sisca_configuration_information_active, "
							+ "sisca_configuration_information_installed_date)  "

							+ "VALUES( "+ cName
							+", "+pKey
							+", "+serverIP
							+", 'true' "
							+", "+creationDate+")";
					dbman.insertDB(query);
					frame.setVisible(false);

					HKJ_SisCA_MainPage  window = new HKJ_SisCA_MainPage();
					window.initializae();

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		});
		mainPanel.add(btnCancelButton);


		// SetUp Status Panel Configuration
		JPanel setUpStatusPaneldComplete = new JPanel();
		setUpStatusPaneldComplete.setBackground(new Color(232,232,232));
		setUpStatusPaneldComplete.setBounds(0, 0, 163, 428);
		setUpStatusPaneldComplete.setBorder(null);
		setUpStatusPaneldComplete.setLayout(new MigLayout("", "[136px]", "[110.00][125][125][125][400]"));

		JLabel lblNewLabel_11 = new JLabel("Welcome");
		setUpStatusPaneldComplete.add(lblNewLabel_11, "cell 0 1,growx,aligny center");

		JLabel lblNewLabel_21 = new JLabel("Configuration");
		setUpStatusPaneldComplete.add(lblNewLabel_21, "cell 0 2,growx,aligny center");

		JLabel lblNewLabel_41 = new JLabel("Complete");
		lblNewLabel_41.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		setUpStatusPaneldComplete.add(lblNewLabel_41, "cell 0 3,growx,aligny center");

		JLabel lblNewLabel_51 = new JLabel("");
		lblNewLabel_51.setIcon(image1);
		setUpStatusPaneldComplete.add(lblNewLabel_51, "cell 0 4,growx,aligny bottom");

		// Main Window Add's
		mainWindow.add(mainPanel);
		mainWindow.add(setUpStatusPaneldComplete);
		return mainWindow;
	}

	protected JPanel accountConfigurationWindow() {

		JPanel accountConfigurationWindow= new JPanel();
		JPanel accountConfigurationMainPanel= new JPanel();
		JPanel accountConfigurationSetupPanel= new JPanel();
		JTextPane accountConfigurationTextPane = new JTextPane();
		final JTextField textFieldFirstName;
		final JTextField textFieldLastName;
		final JTextField textFieldUsername;
		final JPasswordField passwordField;


		// Window Configuration

		accountConfigurationWindow.setBackground(new Color(245, 245, 245));
		accountConfigurationWindow.setBounds(0, 0, 600, 428);
		accountConfigurationWindow.setLayout(null);

		// Authorization Types Panel Configuration
		accountConfigurationMainPanel.setBackground(new Color(242,242,242));
		accountConfigurationMainPanel.setBounds(161, 0, 439, 428);
		accountConfigurationMainPanel.setLayout(null);
		accountConfigurationMainPanel.setBorder(null);

		// Authorization Types Panel Content
		JLabel lblCompanyName = new JLabel("First Name:");
		lblCompanyName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompanyName.setHorizontalTextPosition(SwingConstants.LEADING);
		lblCompanyName.setBounds(28, 111, 85, 16);
		accountConfigurationMainPanel.add(lblCompanyName);
		JLabel lblNewLabel = new JLabel("Account Configuration\r\n");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 34, 427, 16);
		accountConfigurationMainPanel.add(lblNewLabel);
		accountConfigurationTextPane.setBackground(new Color(242, 242, 242));
		accountConfigurationTextPane.setEditable(false);
		accountConfigurationTextPane.setText("     Fills the blanks with the account information.\r\n\r\n\r\n");
		accountConfigurationTextPane.setBounds(6, 62, 427, 37);
		accountConfigurationMainPanel.add(accountConfigurationTextPane);


		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalTextPosition(SwingConstants.LEADING);
		lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
		lblLastName.setBounds(28, 139, 85, 16);
		accountConfigurationMainPanel.add(lblLastName);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalTextPosition(SwingConstants.LEADING);
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setBounds(28, 167, 85, 16);
		accountConfigurationMainPanel.add(lblUsername);

		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(112, 133, 161, 28);
		accountConfigurationMainPanel.add(textFieldLastName);

		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(112, 161, 161, 28);
		accountConfigurationMainPanel.add(textFieldUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalTextPosition(SwingConstants.LEADING);
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setBounds(28, 195, 85, 16);
		accountConfigurationMainPanel.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(112, 189, 161, 28);
		accountConfigurationMainPanel.add(passwordField);

		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(112, 105, 161, 28);
		accountConfigurationMainPanel.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);

		JButton btnCancelButton = new JButton("Cancel");
		btnCancelButton.setBounds(334, 393, 99, 29);
		btnCancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sure = JOptionPane.showConfirmDialog(HKJ_SisCA_MainPage.frame, "Are You Sure?");
				if(sure==0){
					try {
						dbman= new DBManager();
						String query= "DELETE FROM sisca_account where sisca_account_id>=0";
						dbman.insertDB(query);

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.setVisible(false);
				}
			}
		});
		accountConfigurationMainPanel.add(btnCancelButton);

		JButton btnNextButton_1 = new JButton("Complete");
		btnNextButton_1.setBounds(235, 393, 99, 29);
		btnNextButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldFirstName.getText().isEmpty()||textFieldLastName.getText().isEmpty()||textFieldUsername.getText().isEmpty()||passwordField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Invalid or incomplete input data! Please, verify your informtion or complete installation.");
				}
				else{
					try {
						dbman= new DBManager();

						String firstName= "'"+ textFieldFirstName.getText()+ "'";
						String lastName= "'"+ textFieldLastName.getText()+ "'";
						String username= "'"+ textFieldUsername.getText()+ "'";
						String password= "'"+ passwordField.getText()+ "'";

						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
						Calendar cal = Calendar.getInstance();
						String creationDate= "'"+dateFormat.format(cal.getTime())+"'";
						String createdBy= "'INITIAL CONFIGURATION'";

						String query= "INSERT INTO sisca_account (sisca_account_first_name, "
								+ "sisca_account_last_name, "
								+ "sisca_account_username, "
								+ "sisca_account_password, "
								+ "sisca_account_type, "
								+ "sisca_account_creationdate, "
								+ "sisca_account_createdby, "
								+ "sisca_account_active) "
								+ "VALUES( "+ firstName
								+", "+lastName
								+", "+username
								+", "+password
								+", 'administrator' "
								+", "+creationDate
								+", "+createdBy
								+", 'true')";
						dbman.insertDB(query);

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				frame.setContentPane(completeConfigurationWindow());
			}
		});
		accountConfigurationMainPanel.add(btnNextButton_1);


		JComboBox comboBoxAccountType = new JComboBox();
		comboBoxAccountType.setModel(new DefaultComboBoxModel(new String[] {"administrator", "regular"}));
		comboBoxAccountType.setBounds(122, 219, 151, 27);
		accountConfigurationMainPanel.add(comboBoxAccountType);

		JButton btnAddMore = new JButton("Save and Add More");
		btnAddMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldFirstName.getText().isEmpty()||textFieldLastName.getText().isEmpty()||textFieldUsername.getText().isEmpty()||passwordField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Invalid or incomplete input data! Please, verify your informtion or complete installation.");
				}
				else{
					try {
						dbman= new DBManager();

						String firstName= "'"+ textFieldFirstName.getText()+ "'";
						String lastName= "'"+ textFieldLastName.getText()+ "'";
						String username= "'"+ textFieldUsername.getText()+ "'";
						String password= "'"+ passwordField.getText()+ "'";

						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
						Calendar cal = Calendar.getInstance();
						String creationDate= "'"+dateFormat.format(cal.getTime())+"'";
						String createdBy= "'INITIAL CONFIGURATION'";

						String query= "INSERT INTO sisca_account (sisca_account_first_name, "
								+ "sisca_account_last_name, "
								+ "sisca_account_username, "
								+ "sisca_account_password, "
								+ "sisca_account_type, "
								+ "sisca_account_creationdate, "
								+ "sisca_account_createdby, "
								+ "sisca_account_active) "
								+ "VALUES( "+ firstName
								+", "+lastName
								+", "+username
								+", "+password
								+", 'administrator' "
								+", "+creationDate
								+", "+createdBy
								+", 'true')";
						dbman.insertDB(query);

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				frame.setContentPane(accountConfigurationWindow());
			}
		});
		btnAddMore.setBounds(60, 393, 170, 29);
		accountConfigurationMainPanel.add(btnAddMore);


		// SetUp Status Panel Configuration
		accountConfigurationSetupPanel.setBackground(new Color(232,232,232));
		accountConfigurationSetupPanel.setBounds(0, 0, 163, 428);
		accountConfigurationSetupPanel.setLayout(null);
		accountConfigurationSetupPanel.setBorder(null);

		JLabel lblNewLabel_1 = new JLabel("Welcome");
		lblNewLabel_1.setBounds(8, 43, 136, 16);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		accountConfigurationSetupPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Configuration");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel_2.setBounds(8, 64, 136, 16);
		accountConfigurationSetupPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Installing");
		lblNewLabel_3.setBounds(8, 85, 136, 16);
		accountConfigurationSetupPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Complete Setup");
		lblNewLabel_4.setBounds(8, 106, 136, 16);
		accountConfigurationSetupPanel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(8, 298, 136, 116);
		lblNewLabel_5.setIcon(image1);
		accountConfigurationSetupPanel.add(lblNewLabel_5);

		JLabel lblAccountType = new JLabel("Account Type:");
		lblAccountType.setHorizontalTextPosition(SwingConstants.LEADING);
		lblAccountType.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountType.setBounds(28, 223, 104, 16);
		accountConfigurationMainPanel.add(lblAccountType);

		// SetUp Status Panel Configuration
		JPanel setUpStatusPaneldAccount = new JPanel();
		setUpStatusPaneldAccount.setBackground(new Color(232,232,232));
		setUpStatusPaneldAccount.setBounds(0, 0, 163, 428);
		setUpStatusPaneldAccount.setBorder(null);
		setUpStatusPaneldAccount.setLayout(new MigLayout("", "[136px]", "[110.00][125][125][125][400]"));

		JLabel lblNewLabel_11 = new JLabel("Welcome");
		setUpStatusPaneldAccount.add(lblNewLabel_11, "cell 0 1,growx,aligny center");

		JLabel lblNewLabel_21 = new JLabel("Configuration");
		lblNewLabel_21.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		setUpStatusPaneldAccount.add(lblNewLabel_21, "cell 0 2,growx,aligny center");

		JLabel lblNewLabel_41 = new JLabel("Complete");
		setUpStatusPaneldAccount.add(lblNewLabel_41, "cell 0 3,growx,aligny center");

		JLabel lblNewLabel_51 = new JLabel("");
		lblNewLabel_51.setIcon(image1);
		setUpStatusPaneldAccount.add(lblNewLabel_51, "cell 0 4,growx,aligny bottom");



		// Main Window Add's
		accountConfigurationWindow.add(accountConfigurationMainPanel);
		accountConfigurationWindow.add(setUpStatusPaneldAccount);

		return accountConfigurationWindow;
	}





}
