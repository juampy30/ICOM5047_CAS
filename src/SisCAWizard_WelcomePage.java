import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class SisCAWizard_WelcomePage {

	private JFrame frame;
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();

	private JTextField textField;
	private JTextField textField_1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println("Hi there");
		SisCAWizard_WelcomePage window = new SisCAWizard_WelcomePage();
		window.setVisible();
	}

	/**
	 * Create the application.
	 */
	public SisCAWizard_WelcomePage() {
		initialize();
	}

	public void setVisible() {
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ImageIcon image1=  new ImageIcon("/Users/JuanPablo/Desktop/logo2.png");
		frame = new JFrame();

		//		JButton btnNewButton = new JButton("Cancel");
		//		btnNewButton.setBounds(477, 393, 117, 29);
		//		frame.getContentPane().add(btnNewButton);
		//		
		//		JButton btnNewButton_1 = new JButton("Next");
		//		/*btnNewButton_1.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				SisCA_SADConfiguration SADConfigurationMainPanel= new SisCA_SADConfiguration();
		//				frame.setContentPane(SADConfigurationMainPanel);
		//				frame.repaint();
		//			}
		//		});*/
		//		btnNewButton_1.setBounds(348, 393, 117, 29);
		//		frame.getContentPane().add(btnNewButton_1);
		//		panel.setBorder(null);
		//		panel.setBounds(172, 31, 422, 149);
		//		frame.getContentPane().add(panel);
		//		panel.setLayout(new BorderLayout(0, 0));
		//		
		//		JLabel lblNewLabel = new JLabel("Welcome to the installation wizard for SisCA ");
		//		lblNewLabel.setBackground(new Color(245, 245, 245));
		//		lblNewLabel.setForeground(new Color(0, 0, 0));
		//		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		//		panel.add(lblNewLabel, BorderLayout.NORTH);
		//		
		//		JEditorPane dtrpnTheInstallationWizard = new JEditorPane();
		//		dtrpnTheInstallationWizard.setForeground(new Color(102, 102, 102));
		//		dtrpnTheInstallationWizard.setBackground(new Color(245, 245, 245));
		//		dtrpnTheInstallationWizard.setEditable(false);
		//		dtrpnTheInstallationWizard.setText("\n  \n\r\n     The installation wizard will install the Central Administrator \n     Software on your computer. To continue, please, \n     fill the blanks and click NEXT. \r\n");
		//		panel.add(dtrpnTheInstallationWizard, BorderLayout.CENTER);
		//		panel_1.setBorder(null);
		//		
		//		panel_1.setBackground(new Color(220, 220, 220));
		//		panel_1.setBounds(0, 0, 167, 428);
		//		frame.getContentPane().add(panel_1);
		//		// OJO
		//		//panel_1.setLayout(null);
		//		
		//		// Panelsito
		//		JEditorPane dtrpnWelcome = new JEditorPane();
		//		dtrpnWelcome.setText("Welcome");
		//		dtrpnWelcome.setForeground(new Color(102, 102, 102));
		//		dtrpnWelcome.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		//		dtrpnWelcome.setEditable(false);
		//		dtrpnWelcome.setBorder(null);
		//		dtrpnWelcome.setBackground(new Color(220, 220, 220));
		//		dtrpnWelcome.setBounds(6, 27, 155, 17);
		//		panel_1.add(dtrpnWelcome);
		//		
		//		JLabel lblNewLabel_3 = new JLabel("");
		//		lblNewLabel_3.setBounds(6, 304, 155, 118);
		//		panel_1.add(lblNewLabel_3);
		//		lblNewLabel_3.setIcon(image1);
		//		lblNewLabel_3.setBackground(new Color(0, 0, 153));
		//		
		//		JEditorPane dtrpnConfiguration = new JEditorPane();
		//		dtrpnConfiguration.setText("Configure");
		//		dtrpnConfiguration.setForeground(new Color(102, 102, 102));
		//		dtrpnConfiguration.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		//		dtrpnConfiguration.setEditable(false);
		//		dtrpnConfiguration.setBorder(null);
		//		dtrpnConfiguration.setBackground(new Color(220, 220, 220));
		//		dtrpnConfiguration.setBounds(6, 40, 155, 17);
		//		panel_1.add(dtrpnConfiguration);
		//		
		//		JEditorPane dtrpnSads = new JEditorPane();
		//		dtrpnSads.setText("   SAD's");
		//		dtrpnSads.setForeground(new Color(102, 102, 102));
		//		dtrpnSads.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		//		dtrpnSads.setEditable(false);
		//		dtrpnSads.setBorder(null);
		//		dtrpnSads.setBackground(new Color(220, 220, 220));
		//		dtrpnSads.setBounds(6, 54, 155, 17);
		//		panel_1.add(dtrpnSads);
		//		
		//		JEditorPane dtrpnAuthorizationTypes = new JEditorPane();
		//		dtrpnAuthorizationTypes.setText("   Authorization Types");
		//		dtrpnAuthorizationTypes.setForeground(new Color(102, 102, 102));
		//		dtrpnAuthorizationTypes.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		//		dtrpnAuthorizationTypes.setEditable(false);
		//		dtrpnAuthorizationTypes.setBorder(null);
		//		dtrpnAuthorizationTypes.setBackground(new Color(220, 220, 220));
		//		dtrpnAuthorizationTypes.setBounds(6, 69, 155, 17);
		//		panel_1.add(dtrpnAuthorizationTypes);
		//		
		//		JEditorPane dtrpnParking = new JEditorPane();
		//		dtrpnParking.setText("   Parking");
		//		dtrpnParking.setForeground(new Color(102, 102, 102));
		//		dtrpnParking.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		//		dtrpnParking.setEditable(false);
		//		dtrpnParking.setBorder(null);
		//		dtrpnParking.setBackground(new Color(220, 220, 220));
		//		dtrpnParking.setBounds(6, 83, 155, 17);
		//		panel_1.add(dtrpnParking);
		//		
		//		JEditorPane dtrpnAccounts = new JEditorPane();
		//		dtrpnAccounts.setText("   Accounts");
		//		dtrpnAccounts.setForeground(new Color(102, 102, 102));
		//		dtrpnAccounts.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		//		dtrpnAccounts.setEditable(false);
		//		dtrpnAccounts.setBorder(null);
		//		dtrpnAccounts.setBackground(new Color(220, 220, 220));
		//		dtrpnAccounts.setBounds(6, 98, 155, 17);
		//		panel_1.add(dtrpnAccounts);
		//		
		//		JEditorPane dtrpnInstalling = new JEditorPane();
		//		dtrpnInstalling.setText("Installing");
		//		dtrpnInstalling.setForeground(new Color(102, 102, 102));
		//		dtrpnInstalling.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		//		dtrpnInstalling.setEditable(false);
		//		dtrpnInstalling.setBorder(null);
		//		dtrpnInstalling.setBackground(new Color(220, 220, 220));
		//		dtrpnInstalling.setBounds(6, 112, 155, 17);
		//		panel_1.add(dtrpnInstalling);
		//		
		//		JEditorPane dtrpnCompleteSetup = new JEditorPane();
		//		dtrpnCompleteSetup.setText("Complete Setup");
		//		dtrpnCompleteSetup.setForeground(new Color(102, 102, 102));
		//		dtrpnCompleteSetup.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		//		dtrpnCompleteSetup.setEditable(false);
		//		dtrpnCompleteSetup.setBorder(null);
		//		dtrpnCompleteSetup.setBackground(new Color(220, 220, 220));
		//		dtrpnCompleteSetup.setBounds(6, 127, 155, 17);
		//		panel_1.add(dtrpnCompleteSetup);
		//		
		//		// Window content
		//		JLabel lblNewLabel_1 = new JLabel("Company Name:");
		//		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		//		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		//		lblNewLabel_1.setBounds(204, 192, 140, 16);
		//		frame.getContentPane().add(lblNewLabel_1);
		//		
		//		JLabel lblNewLabel_2 = new JLabel("Product Key:");
		//		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		//		lblNewLabel_2.setBounds(204, 220, 140, 16);
		//		frame.getContentPane().add(lblNewLabel_2);
		//		
		//		textField = new JTextField();
		//		textField.setBounds(318, 186, 191, 28);
		//		frame.getContentPane().add(textField);
		//		textField.setColumns(10);
		//		
		//		textField_1 = new JTextField();
		//		textField_1.setColumns(10);
		//		textField_1.setBounds(295, 214, 214, 28);
		//		frame.getContentPane().add(textField_1);

		// Frame configuration
		//frame.getContentPane().setBackground(new Color(245, 245, 245));
		//frame.setBounds(100, 100, 600, 450);
		//frame.setResizable(false);
		//frame.setBackground(new Color(242,242,242));
		frame.setTitle("SisCA Setup Wizard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// OJO
		//frame.getContentPane().setLayout(null);


	}
}
