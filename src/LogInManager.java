import java.awt.BorderLayout;
import java.awt.Color;
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
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import databases.DBManager;
import net.miginfocom.swing.MigLayout;


/** LogIn Manager
 * 	Manage LogIn View and Stand By View
 *  @author Juan Pablo Bermœdez Reyes
 *  Last Modified: April 6, 2014
 */
public class LogInManager {
	
	/**
	 * Fields
	 */
	static DBManager dbman;
	static ArrayList<Object> result;
	
	/**
	 *  Constructor
	 */
	LogInManager(){

	}
	
	/** LogIn View
	 *  Generates the LogIn View JPanel 
	 *  @return windowPanelLogInView JPanel 
	 */
	static JPanel logInView(){


		JPanel windowPanelLogInView= new JPanel();
		JPanel menuPanelAddLogIn = new JPanel();
		JPanel mainPanel = new JPanel();
		final JTextField textFieldUsername;
		final JPasswordField textFieldPassword;
	

		/////////////////////////////////////////////////////////
		// Menu Panel
		/////////////////////////////////////////////////////////
		//Configurations 

		menuPanelAddLogIn.setBackground(new Color(255,239,80));
		menuPanelAddLogIn.setPreferredSize(new Dimension(10, 30));
		menuPanelAddLogIn.setLayout(new BorderLayout(0, 0));

		JPanel userNamePanel = new JPanel();
		userNamePanel.setPreferredSize(new Dimension(200, 10));
		menuPanelAddLogIn.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));
		
		/////////////////////////////////////////////////////////
		// Main Panel
		/////////////////////////////////////////////////////////
		
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mainPanel.setBackground(new Color(250,250,250));
		windowPanelLogInView.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new MigLayout("", "[1238.00px]", "[][][][][][][56px][56px][56px]"));
		
		JPanel usernameTextField = new JPanel();
		usernameTextField.setBackground(new Color(250,250,250));
		usernameTextField.setLayout(new MigLayout("", "[][238.00]", "[]"));
		
		JLabel lblNewLabel = new JLabel("Username:");
		usernameTextField.add(lblNewLabel, "cell 0 0");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		textFieldUsername = new JTextField();
		usernameTextField.add(textFieldUsername, "cell 1 0,growx");
		textFieldUsername.setPreferredSize(new Dimension(48, 28));
		textFieldUsername.setColumns(10);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.setBackground(new Color(250,250,250));
		passwordPanel.setLayout(new MigLayout("", "[][240.00]", "[]"));
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordPanel.add(passwordLabel, "cell 0 0,alignx center");
		passwordLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		textFieldPassword = new JPasswordField();
		passwordPanel.add(textFieldPassword, "cell 1 0,growx");
		textFieldPassword.setColumns(10);
		
		JPanel logInAdnCancelPanel = new JPanel();
		logInAdnCancelPanel.setBackground(new Color(250,250,250));
		logInAdnCancelPanel.setLayout(new MigLayout("", "[][]", "[]"));
		
		
		////////////////////////////////////////////
		// Querry for LogIn
		////////////////////////////////////////////

		
		JButton logInButton = new JButton("LogIn");
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username= "'"+textFieldUsername.getText()+"'";
				String password= "'"+textFieldPassword.getText()+"'";
				
				System.out.println("Username:"+ username+ "  Password:"+password);
				
				
				try {
					String query= "Select count(*) from sisca_account where sisca_account_username="+username +"and sisca_account_password="+password;
					System.out.println("Querry:"+query);
					
					dbman= new DBManager();
					
					int count=Integer.parseInt(dbman.getFromDBCount(query));
					
					if (count==1){
						
						HKJ_SisCA_MainPage.setActiveUsername(username);
						
						HKJ_SisCA_MainPage.frame.setContentPane(HKJ_SisCA_MainPage.homeView());
						HKJ_SisCA_MainPage.frame.pack();
						HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
						
					}
					else{
						JOptionPane.showMessageDialog(HKJ_SisCA_MainPage.frame, "Incorrect Username or Password.");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		logInAdnCancelPanel.add(logInButton, "cell 0 0");
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				HKJ_SisCA_MainPage.frame.setContentPane(standByView());
				HKJ_SisCA_MainPage.frame.pack();
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				
			}
		});
	
		logInAdnCancelPanel.add(cancelButton, "cell 1 0");
		
	
		mainPanel.add(usernameTextField, "cell 0 12,alignx center,growy");
		mainPanel.add(passwordPanel, "cell 0 13,alignx center,growy");
		mainPanel.add(logInAdnCancelPanel, "cell 0 14,alignx center,growy");

		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		windowPanelLogInView.setLayout(new BorderLayout(0, 0));
		windowPanelLogInView.add(menuPanelAddLogIn, BorderLayout.NORTH);
		windowPanelLogInView.add(mainPanel, BorderLayout.CENTER);
		
		return windowPanelLogInView;


	}

	/** Stand By View
	 *  Generates the Stand By View JPanel 
	 *  @return windowPanelStandBy JPanel 
	 */
	static JPanel standByView(){
		
		JPanel windowPanelStandBy= new JPanel();
		JPanel menuStandBy = new JPanel();
		JPanel mainPanel = new JPanel();

		/////////////////////////////////////////////////////////
		// Menu Panel
		/////////////////////////////////////////////////////////
		//Configurations 

		menuStandBy.setBackground(new Color(255,239,80));
		menuStandBy.setPreferredSize(new Dimension(10, 30));
		menuStandBy.setLayout(new BorderLayout(0, 0));

		JPanel userNamePanel = new JPanel();
		userNamePanel.setPreferredSize(new Dimension(200, 10));
		menuStandBy.add(userNamePanel, BorderLayout.CENTER);
		userNamePanel.setLayout(new BorderLayout(0, 0));
		
		/////////////////////////////////////////////////////////
		// Main Panel
		/////////////////////////////////////////////////////////
		
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mainPanel.setBackground(new Color(250,250,250));
		windowPanelStandBy.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new MigLayout("", "[1280.00px]", "[121.00][363px][65px]"));
		
		JPanel logoPanel = new JPanel();
		logoPanel.setBackground(new Color(250,250,250));
		mainPanel.add(logoPanel, "cell 0 1,alignx center,aligny top");
		logoPanel.setLayout(new MigLayout("", "[]", "[]"));
		
		JLabel logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon("/Users/JuanPablo/Desktop/i12.png"));
		logoPanel.add(logoLabel, "cell 0 0,alignx center");
		logoLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JPanel logInPanel = new JPanel();
		logInPanel.setBackground(new Color(250,250,250));
		mainPanel.add(logInPanel, "cell 0 2,alignx center,aligny top");
		logInPanel.setLayout(new MigLayout("", "[428.00px]", "[33px]"));
		
		JButton logInButton = new JButton("Log In");
		logInButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HKJ_SisCA_MainPage.frame.setContentPane(logInView());
				HKJ_SisCA_MainPage.frame.pack(); 
				HKJ_SisCA_MainPage.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

			}

		});
		logInButton.setFocusPainted(false);
		logInButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		logInPanel.add(logInButton, "cell 0 0,growx,aligny top");

		////////////////////////////////////////////////////////
		//Window Panel
		/////////////////////////////////////////////////////////

		//Configurations
		windowPanelStandBy.setLayout(new BorderLayout(0, 0));
		windowPanelStandBy.add(menuStandBy, BorderLayout.NORTH);
		windowPanelStandBy.add(mainPanel, BorderLayout.CENTER);
		
		return windowPanelStandBy;
	}

}
