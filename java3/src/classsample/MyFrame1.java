package classsample;

import java.awt.Frame;

public class MyFrame1 {
	Frame frame;
	
	public MyFrame1() {
		frame = new Frame("포함관계");
		
		display();
	}
	
	public void display(){
		frame.setSize(500, 300);
		frame.setLocation(200, 150);
		frame.setVisible(true);
	}	
	
	public static void main(String[] args) {
		//MyFrame1 frame1 = new MyFrame1();
		//frame1.display();
		new MyFrame1();
	}

}
