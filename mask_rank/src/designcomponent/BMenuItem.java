package designcomponent;

import javax.swing.JMenuItem;

public class BMenuItem extends JMenuItem implements Cinfo{

	public BMenuItem() {
		// TODO Auto-generated constructor stub
		setBackground(BUTTON_COLOR);
		setForeground(HIGHLIGHT_COLOR);
	}
	public BMenuItem(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		setBackground(BUTTON_COLOR);
		setForeground(HIGHLIGHT_COLOR);
	}

}
