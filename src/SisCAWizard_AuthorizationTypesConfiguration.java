import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.GridLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.Dimension;

import javax.swing.JEditorPane;

import java.awt.Color;

import javax.swing.UIManager;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.JTextArea;


public class SisCAWizard_AuthorizationTypesConfiguration{

	private JFrame frame;
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SisCAWizard_AuthorizationTypesConfiguration window = new SisCAWizard_AuthorizationTypesConfiguration();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SisCAWizard_AuthorizationTypesConfiguration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ImageIcon image1=  new ImageIcon("/Users/JuanPablo/Desktop/logo2.png");
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 245, 245));
		frame.setBounds(100, 100, 600, 450);
		frame.setResizable(false);
		frame.setBackground(new Color(242,242,242));
		frame.setTitle("SisCA Setup Wizard");
		
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBounds(477, 393, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Next");
		btnNewButton_1.setBounds(348, 393, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
		panel.setBorder(null);
		panel.setBounds(172, 31, 422, 103);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Authorization Types Configuration");
		lblNewLabel.setBackground(new Color(245, 245, 245));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
	

		
		JEditorPane dtrpnTheInstallationWizard = new JEditorPane();
		panel.add(dtrpnTheInstallationWizard, BorderLayout.CENTER);
		dtrpnTheInstallationWizard.setForeground(new Color(102, 102, 102));
		dtrpnTheInstallationWizard.setBackground(new Color(245, 245, 245));
		dtrpnTheInstallationWizard.setEditable(false);
		dtrpnTheInstallationWizard.setText("\n     \r\n     Configure different types of parking authorization.\r\n");
		panel_1.setBorder(null);
		
		panel_1.setBackground(new Color(220, 220, 220));
		panel_1.setBounds(0, 0, 167, 428);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JEditorPane dtrpnWelcome = new JEditorPane();
		dtrpnWelcome.setText("Welcome");
		dtrpnWelcome.setForeground(new Color(102, 102, 102));
		dtrpnWelcome.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		dtrpnWelcome.setEditable(false);
		dtrpnWelcome.setBorder(null);
		dtrpnWelcome.setBackground(new Color(220, 220, 220));
		dtrpnWelcome.setBounds(6, 27, 155, 17);
		panel_1.add(dtrpnWelcome);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(6, 304, 155, 118);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(image1);
		lblNewLabel_3.setBackground(new Color(0, 0, 153));
		
		JEditorPane dtrpnConfiguration = new JEditorPane();
		dtrpnConfiguration.setText("Configure");
		dtrpnConfiguration.setForeground(new Color(102, 102, 102));
		dtrpnConfiguration.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		dtrpnConfiguration.setEditable(false);
		dtrpnConfiguration.setBorder(null);
		dtrpnConfiguration.setBackground(new Color(220, 220, 220));
		dtrpnConfiguration.setBounds(6, 40, 155, 17);
		panel_1.add(dtrpnConfiguration);
		
		JEditorPane dtrpnSads = new JEditorPane();
		dtrpnSads.setText("   Server IP Address");
		dtrpnSads.setForeground(new Color(102, 102, 102));
		dtrpnSads.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		dtrpnSads.setEditable(false);
		dtrpnSads.setBorder(null);
		dtrpnSads.setBackground(new Color(220, 220, 220));
		dtrpnSads.setBounds(6, 54, 155, 17);
		panel_1.add(dtrpnSads);
		
		JEditorPane dtrpnAuthorizationTypes = new JEditorPane();
		dtrpnAuthorizationTypes.setText("   Authorization Types");
		dtrpnAuthorizationTypes.setForeground(new Color(102, 102, 102));
		dtrpnAuthorizationTypes.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		dtrpnAuthorizationTypes.setEditable(false);
		dtrpnAuthorizationTypes.setBorder(null);
		dtrpnAuthorizationTypes.setBackground(new Color(220, 220, 220));
		dtrpnAuthorizationTypes.setBounds(6, 69, 155, 17);
		panel_1.add(dtrpnAuthorizationTypes);
		
		JEditorPane dtrpnParking = new JEditorPane();
		dtrpnParking.setText("   Parking");
		dtrpnParking.setForeground(new Color(102, 102, 102));
		dtrpnParking.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		dtrpnParking.setEditable(false);
		dtrpnParking.setBorder(null);
		dtrpnParking.setBackground(new Color(220, 220, 220));
		dtrpnParking.setBounds(6, 83, 155, 17);
		panel_1.add(dtrpnParking);
		
		JEditorPane dtrpnAccounts = new JEditorPane();
		dtrpnAccounts.setText("   Accounts");
		dtrpnAccounts.setForeground(new Color(102, 102, 102));
		dtrpnAccounts.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		dtrpnAccounts.setEditable(false);
		dtrpnAccounts.setBorder(null);
		dtrpnAccounts.setBackground(new Color(220, 220, 220));
		dtrpnAccounts.setBounds(6, 98, 155, 17);
		panel_1.add(dtrpnAccounts);
		
		JEditorPane dtrpnInstalling = new JEditorPane();
		dtrpnInstalling.setText("Installing");
		dtrpnInstalling.setForeground(new Color(102, 102, 102));
		dtrpnInstalling.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		dtrpnInstalling.setEditable(false);
		dtrpnInstalling.setBorder(null);
		dtrpnInstalling.setBackground(new Color(220, 220, 220));
		dtrpnInstalling.setBounds(6, 112, 155, 17);
		panel_1.add(dtrpnInstalling);
		
		JEditorPane dtrpnCompleteSetup = new JEditorPane();
		dtrpnCompleteSetup.setText("Complete Setup");
		dtrpnCompleteSetup.setForeground(new Color(102, 102, 102));
		dtrpnCompleteSetup.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		dtrpnCompleteSetup.setEditable(false);
		dtrpnCompleteSetup.setBorder(null);
		dtrpnCompleteSetup.setBackground(new Color(220, 220, 220));
		dtrpnCompleteSetup.setBounds(6, 127, 155, 17);
		panel_1.add(dtrpnCompleteSetup);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBounds(205, 139, 140, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(255, 133, 210, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(219, 393, 117, 29);
		frame.getContentPane().add(btnBack);
		
		JButton btnAddMore = new JButton("Add More");
		btnAddMore.setBounds(477, 134, 117, 29);
		frame.getContentPane().add(btnAddMore);
	}
}
