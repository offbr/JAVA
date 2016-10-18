package lambdapack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LambdaEvent extends JFrame{ 
	public LambdaEvent() {
		super("람다 연습");		//인터페이스에 추상메소드가 하나일때 쓴다 내부무명 클래스 대신에 람다를 쓴다
		setBounds(200, 200, 300, 300);
		setVisible(true);

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() { //람다로 처리 불가 // 메소드 한개만 가지고 있을 경우 가능.
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		layInit();		
	}
	
	private void layInit(){
		setLayout(null);
		JButton btn = new JButton("버튼1");
		btn.setBounds(10, 20, 100, 50);
		add(btn);
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTitle("안녕하세요");
			}
		});
		
		JButton btn2 = new JButton("버튼1");
		btn2.setBounds(10, 80, 100, 50);
		add(btn2);
		
		btn2.addActionListener(e -> setTitle("반가워")); 
		
		JButton btn3 = new JButton("버튼1");
		btn3.setBounds(10, 140, 100, 50);
		add(btn3);
		
		btn3.addActionListener(m -> setTitle("반가워11")); 
		
	}
	
	
	public static void main(String[] args) {
		new LambdaEvent();
	}
}
