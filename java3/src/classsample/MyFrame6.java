package classsample;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.*;

public class MyFrame6 extends Frame{
	WindowAdapter aa;
	public MyFrame6() {
		super("내부무명 클래스");
		setBounds(200, 200, 400, 300);
		setVisible(true);
		
		//aa = new WindowAdapter();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setBackground(new Color(0, 0, 0));
			}
		});	
	}
	
	public static void main(String[] args) {
		new MyFrame6();
	}
}
