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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;
import javax.swing.JTextPane;


public class SisCAWizard_SADConfiguration {

	private JFrame frame;
	private final JTextPane txtpnTheInstallationWizard = new JTextPane();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SisCAWizard_SADConfiguration window = new SisCAWizard_SADConfiguration();
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
	public SisCAWizard_SADConfiguration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		ImageIcon image1=  new ImageIcon("/Users/JuanPablo/Desktop/logo2.png");
		
		// Main Panel 
		JPanel panel = new JPanel();
		panel.setBounds(161, 0, 439, 428);
		panel.setBackground(new Color(242,242,242));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		// Content
		JLabel lblCompanyName = new JLabel("     Company Name:");
		lblCompanyName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompanyName.setHorizontalTextPosition(SwingConstants.LEADING);
		lblCompanyName.setBounds(6, 125, 135, 16);
		panel.add(lblCompanyName);
		JLabel lblNewLabel = new JLabel("Welcome to the installation wizard for SisCA ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 34, 427, 16);
		panel.add(lblNewLabel);
		txtpnTheInstallationWizard.setBackground(new Color(242, 242, 242));
		txtpnTheInstallationWizard.setEditable(false);
		txtpnTheInstallationWizard.setText("     The installation wizard will install the Central Administrator\n     Software on your computer. To continue, please, \n     fill the blanks and click NEXT. \r\n");
		txtpnTheInstallationWizard.setBounds(6, 62, 427, 51);
		panel.add(txtpnTheInstallationWizard);
		
		JLabel lblProductKey = new JLabel("     Product Key:");
		lblProductKey.setHorizontalTextPosition(SwingConstants.LEADING);
		lblProductKey.setHorizontalAlignment(SwingConstants.LEFT);
		lblProductKey.setBounds(6, 153, 135, 16);
		panel.add(lblProductKey);
		
		textField = new JTextField();
		textField.setBounds(136, 119, 210, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(116, 147, 230, 28);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBounds(316, 393, 117, 29);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Next");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(187, 393, 117, 29);
		panel.add(btnNewButton_1);
		
		
		// SetUp Status Panel
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 163, 428);
		panel_1.setBackground(new Color(232,232,232));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome");
		lblNewLabel_1.setBounds(8, 43, 136, 16);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Configuration");
		lblNewLabel_2.setBounds(8, 64, 136, 16);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Installing");
		lblNewLabel_3.setBounds(8, 85, 136, 16);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Complete SetUp");
		lblNewLabel_4.setBounds(8, 106, 136, 16);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(8, 298, 136, 116);
		lblNewLabel_5.setIcon(image1);
		panel_1.add(lblNewLabel_5);
		
		// Content
		
		
		
		
		
		
		
		
		
		
		frame.setBounds(100, 100, 600, 450);
		frame.setResizable(false);
		frame.setBackground(new Color(242,242,242));
		frame.setTitle("SisCA Setup Wizard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
	}
}
