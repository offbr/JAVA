package classsample;

import java.awt.Frame;

public class MyFrame2 extends Frame{

	public MyFrame2() {
		super("상속 사용");
		display();
	}
	
	void display(){
		//setSize(300, 200);
		//setLocation(200, 150);
		setBounds(200, 150, 300, 200);
		setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		/*
		MyFrame2 frame2 = new MyFrame2();
		frame2.display();
		*/
		new MyFrame2();
	}

}
