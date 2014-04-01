import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class SisCA_SADConfiguration extends JPanel {

	
	JPanel SADConfigurationMainPanel;
	/**
	 * Create the panel.
	 */
	public SisCA_SADConfiguration() {
		SADConfigurationMainPanel =new JPanel();
		SADConfigurationMainPanel.setBorder(null);
		SADConfigurationMainPanel.setBounds(172, 31, 422, 149);
		SADConfigurationMainPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel label= new JLabel("BAAAAAAAAHHH");
		SADConfigurationMainPanel.add(label);
		

	}

}
