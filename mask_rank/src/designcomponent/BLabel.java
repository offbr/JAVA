package designcomponent;

import javax.swing.JLabel;

public class BLabel extends JLabel implements Cinfo{
	public BLabel(String label) {
		// TODO Auto-generated constructor stub
		super(label);
		setForeground(HIGHLIGHT_COLOR);
	}
	public BLabel(String label,int horizontal) {
		// TODO Auto-generated constructor stub
		super(label,horizontal);
		setForeground(HIGHLIGHT_COLOR);
	}
	public BLabel() {
		// TODO Auto-generated constructor stub
		
		setForeground(HIGHLIGHT_COLOR);
	}
	
}
