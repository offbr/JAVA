package designcomponent;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class BButton extends JButton implements Cinfo{
	public BButton(String name) {
		// TODO Auto-generated constructor stub
		super(name);
		setBackground(BUTTON_COLOR);
		setBorderPainted(false);
		setForeground(TEXT_COLOR);
		setFocusPainted(false);
		setFont(new Font("Sanserif", Font.BOLD, 12));
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(isEnabled()==true){
				setBackground(TEXT_COLOR);
				setForeground(FRAME_COLOR);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setBackground(BUTTON_COLOR);
				setForeground(TEXT_COLOR);
			}
		});
		
	}
	
	
}
