package classsample;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.*;

public class MyFrame5 extends Frame{ //추상메소드가 두개 이상이면 어댑터가 있다.
	private Wevent wevent;
	//private Mevent mevent;
	private int x = 100, y = 100;
	public MyFrame5() {
		super("내부클래스 사용");
		setBounds(200, 200, 400, 300); //Frame의 x좌표 , y좌표 , 넓이, 높이)
		setVisible(true); //Frame(실행)
		
		wevent = new Wevent(); //WindowEvent
		//mevent = new Mevent(); //MouseEvent
		//addWindowListener(l); (해당 객체변수, 인터페이스 객체를 바로 담는다)
	 	addWindowListener(wevent);	//WindowEventHandler // addWindowListener(해당 객체변수) 
		addMouseListener(new Mevent()); //MouseEventHandler // addMouseListener(해당 인터페이스 객체를 바로 담는다)
	}
	
	class Wevent extends WindowAdapter { //내부 클래스
		@Override
		public void windowClosing(WindowEvent e) { 
			System.exit(0);
		}
	}

	class Mevent extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			int r = (int)(Math.random()* 255);
			int g = (int)(Math.random()* 255);
			int b = (int)(Math.random()* 255);
			System.out.println(r + " " + g + " " + b);
			setBackground(new Color(r, g, b));	//Frame이 가지고 있는 멤버 내부클래스는 외부클래스를 마음대로 쓸 수 있다.
			//int x = e.getX();
			//int y = e.getY();
			x = e.getX();
			y = e.getY();
			
			setTitle("x: " + x + " y: " + y);
			repaint(); // paint()를 호출
		}
	}

		@Override
		public void paint(Graphics g) {  //맨 처음에 1회 시스템에 의해 호출
			//Frame이 가지고 멤버메소드 // Frame에 도형을 그릴 때 사용하는 메소드 
			String a[] = {"한국인", "미국인", "중국인", "일본인", "우주인"};
			int m = (int)(Math.random() * 5);
			g.setColor(new Color((int)(Math.random()* 255), (int)(Math.random()* 255), (int)(Math.random()* 255)));
			g.setFont(new Font("궁서", Font.BOLD, (int)(Math.random()* 50)+8));
			g.drawString(a[m], x, y);
		}
	
	public static void main(String[] args) {
		new MyFrame5();
	}
}
