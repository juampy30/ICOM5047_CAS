import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class FrameTest extends JFrame {
	public FrameTest() {
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
JFrame frame= new JFrame();
		
		final JPanel mainPanel= new JPanel();
		ImageIcon image1=  new ImageIcon("/Users/JuanPablo/Desktop/img1.png");
		JLabel imgLabel1= new JLabel();
		imgLabel1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mainPanel.setBackground(new Color(0,192,192));
			}
		});
		imgLabel1.setBounds(271, -22, 258, 435);
		
		imgLabel1.setIcon(image1);
		mainPanel.add(imgLabel1);

	
		

		// Main Panel Configuration
	
		mainPanel.setBackground(new Color(142,242,242));
		mainPanel.setBounds(0, 0, 1024,768);
		mainPanel.setLayout(null);
		mainPanel.setBorder(null);
		
		mainPanel.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		frame.getContentPane().add(mainPanel);

		// Frame configuration
		frame.setBounds(0,0, 1024, 768);
		frame.setResizable(true);
		frame.setBackground(new Color(242,242,242));
		frame.setTitle("SisCA");
		frame.setVisible(true);
		//frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(null);
	}

}
