import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.eclipse.swt.graphics.Color;

import java.awt.GridLayout;
import java.awt.GridBagLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.FlowLayout;

import javax.swing.SpringLayout;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import javax.swing.JPopupMenu;

import java.awt.Component;

import javax.swing.JMenuItem;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;

import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLayeredPane;

import java.awt.Canvas;

import javax.swing.Box;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.Cursor;

import javax.swing.border.TitledBorder;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import java.awt.List;

import javax.swing.DropMode;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import javax.swing.AbstractListModel;


public class FrameTest extends JFrame {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=1331,51
	 */
	private final JPanel panel = new JPanel();
	private final JLabel lblNewLabel_4 = new JLabel("");
	private JTextField searchTextField;
	private JTable AuthorizationTypeTable;
	private JTable table_2;
	private JTextField textFieldSearch;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTest frame = new FrameTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameTest() {
		final JFrame frame= new JFrame();



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

JLabel editAuthorizationTypeLabelPanel = new JLabel("Edit AuthorizationType");
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

JTextField textFieldName = new JTextField();
textFieldName.setPreferredSize(new Dimension(200, 100));
AuthorizationTypeIDPanel.add(textFieldName, "cell 1 0,grow");
textFieldName.setColumns(10);


////////////////////////////////////////////////////////
//Window Panel
/////////////////////////////////////////////////////////

//Configurations
JPanel windowPanelEditAuthorizationType= new JPanel();
windowPanelEditAuthorizationType.setLayout(new BorderLayout(0, 0));
windowPanelEditAuthorizationType.add(menuPanelEditAuthorizationType, BorderLayout.NORTH);
windowPanelEditAuthorizationType.add(leftPanelEditccount, BorderLayout.WEST);
windowPanelEditAuthorizationType.add(mainPanelEditAuthorizationType, BorderLayout.CENTER);





		////////////////////////////////////////////////////////
		//           Frame
		/////////////////////////////////////////////////////////

		// Configurations
		frame.getContentPane().add(windowPanelEditAuthorizationType);
		frame.setResizable(true);
		frame.setTitle("SisCA");
		//frame.setSize(new Dimension(1000,800));
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
