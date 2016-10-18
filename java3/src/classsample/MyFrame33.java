package classsample;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame33 extends WindowAdapter{
	Frame frame;
	Aa aa;
	public MyFrame33() { 
		frame = new Frame();
		aa = new Aa();
		frame.setTitle("인터페이스 연습");
		frame.setBounds(200, 150, 300, 200);
		frame.setVisible(true); // 실행
		
		
		frame.addWindowListener(this);
		//frame.addMouseListener(this);
	
		/*
		  	this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = (int)(Math.random()* 255);
				int g = (int)(Math.random()* 255);
				int b = (int)(Math.random()* 255);
				System.out.println(r + " " + g + " " + b);
				setBackground(new Color(r, g, b));
			}
		});
		*/
		
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	
	class Aa extends MouseAdapter{
		public Aa() {
			frame.addMouseListener(this);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int r = (int)(Math.random()* 255);
			int g = (int)(Math.random()* 255);
			int b = (int)(Math.random()* 255);
			System.out.println(r + " " + g + " " + b);
			frame.setBackground(new Color(r, g, b));
		}
	}
	
	public static void main(String[] args) {
		MyFrame33 frame= new MyFrame33();
		//frame.new Aa();
		
	}
}
































