package pack;

import java.awt.Button;
import java.awt.Event;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EventTest extends Frame implements ActionListener{
	private Button  btn1 = new Button("button1");
	private Button  btn2 = new Button("button2");
	private Button  btn3 = new Button("button3");
	private Button  btn4 = new Button("button4");
	private Button  btn5 = new Button("button5");
	
	public EventTest() {
		this.setTitle("Event Test");
		this.setBounds(200, 200, 400, 300);
		this.setVisible(true);
		
		addWindowListener(new WindowAdapter() { //내부무명클래스를 사용하여 이벤트 처리 
			@Override
			public void windowClosing(WindowEvent e) { 
				System.exit(0);
			}
		});
		
		addComponent();
	}
	
	private void addComponent(){
		add("East", btn1);
		add("West", btn2);
		add("Center", btn3);
		add("South", btn4);
		add("North", btn5);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(new MyEvent()); //내부클래스 사용1
		btn4.addMouseListener(new YourEvent()); //내부클래스 사용2
		btn5.addMouseListener(new MouseAdapter() { //내부 무명클래스 사용
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { 
		// implements 리스너 한후 오버라이드하여 이벤트 처리 
		//System.out.println(e.getActionCommand());
		/*
		if(e.getActionCommand().equals("button1")){ //label로 구분
			setTitle("버튼1 클릭");
		}else if (e.getActionCommand().equals("button2")){
			setTitle("버튼2 누름");
		}
		*/
		if(e.getSource() == btn1){ //기본형 비교
			this.setTitle("버튼1 클릭");
		}else if(e.getSource().equals(btn2)){ //참조형 비교
			this.setTitle("버튼2 누름");
		}
	}
	
	//내부 클래스를 사용 - button3
	class MyEvent implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			EventTest.this.setTitle("버튼3 누르다");
			
		}
	}
	
	//내부 클래스를 사용 - button4
	class YourEvent extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			EventTest.this.setTitle("버튼4: 마우스 이벤트");
		}
	}
	
	
	public static void main(String[] args) {
		new EventTest();
	}
}




















