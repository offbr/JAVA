package classsample;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MyFrame333 extends WindowAdapter{
	Frame frame;
	Bb bb;
	public MyFrame333() {
		frame = new Frame("내부클래스로 실행");
		frame.setBounds(200, 300, 200, 300);
		frame.setVisible(true);
		frame.addWindowListener(this);
		bb = new Bb();
		
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	
	class Bb extends MouseAdapter{		
		public Bb() {
			frame.addMouseListener(this);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			frame.setBackground(new Color(0, 0, 0));
		}
	}
	
	public static void main(String[] args) {
		//MyFrame333 frame = new MyFrame333();
		new MyFrame333();
	}

}
