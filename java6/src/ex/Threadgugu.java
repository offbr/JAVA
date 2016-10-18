package ex;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

public class Threadgugu extends Frame implements ActionListener, Runnable {
	private Label lblshow;
	private Thread thread;
	private boolean b = false; // 스레드 종료를 위한 멤버필드 //스레드는 끝내는 방법은 없다

	public Threadgugu() {
		lblshow = new Label("Show GuGuDan", Label.CENTER);
		add("Center", lblshow);
		Button button = new Button("Click");
		button.addActionListener(this); // 버튼을 사용하려면 Listener 장착
		add("South", button);
		
		setTitle("Thread Clock");
		setBounds(200, 200, 300, 300);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				b = true;
				System.exit(0);
			}
		});
		
		lblshow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		thread = new Thread(this);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("라라라라라");
		// showData();
		if (thread.isAlive())
			return;
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			if (b == true)
				break; // 중요하다
			showData();
		}
	}

	private void showData() {
		for (int i = 2; i < 10; i++) {
			for (int j = 1; j < 10; j++) {
				try {
					Thread.sleep(1000);
					if(j == 1){
					int r = (int)(Math.random() * 255);
					int g = (int)(Math.random() * 255);
					int c = (int)(Math.random() * 255);
					lblshow.setBackground(new Color(r, g, c));
					}
					lblshow.setFont(new Font("돋음", Font.BOLD, 25));
					lblshow.setText(i + "*" + j + "= " + ( i * j));
				} catch (Exception e) {
					System.out.println("err: " + e);
				}			
			}
		}
	}

	
	public static void main(String[] args) {
		new Threadgugu();
	}
}
