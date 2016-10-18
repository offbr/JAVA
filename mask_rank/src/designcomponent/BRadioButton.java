package designcomponent;

import javax.swing.JRadioButton;

public class BRadioButton extends JRadioButton implements Cinfo{

	public BRadioButton(String title) {
		// TODO Auto-generated constructor stub
	
		super(title);
		setBackground(PANEL_COLOR);
		setForeground(HIGHLIGHT_COLOR);
	}

	
}
