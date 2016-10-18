package classsample;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MyFrame3 extends Frame implements WindowListener, MouseListener{
	
	public MyFrame3() { 
		//super("인터페이스 연습");
		//1. visual object 작성
		setTitle("인터페이스 연습");
		setBounds(200, 150, 300, 200);
		setVisible(true); // 실행
		
		//2. Listener를 장착 // WindowListener 구현
		addWindowListener(this);
		addMouseListener(this);
		//3. 해당 이벤트핸들러메소드에 프로그래밍
	}
	
	//윈도우 관련 메소드
	
	@Override
	public void windowActivated(WindowEvent e) {
		
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		//System.out.println("종료");
		System.exit(0);
	}
	
	@Override
	public void windowDeactivated(WindowEvent e) {
				
	}
	
	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("윈도우 복원");
	}
	
	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("아이콘화 됨");
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		
	}
	
	//마우스 관련 메소드
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//setBackground(new Color(0, 0, 0));
		//System.out.println((int)(Math.random()* 255));
		int r = (int)(Math.random()* 255);
		int g = (int)(Math.random()* 255);
		int b = (int)(Math.random()* 255);
		System.out.println(r + " " + g + " " + b);
		setBackground(new Color(r, g, b));
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
			
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
			
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		new MyFrame3();
	}
}
































