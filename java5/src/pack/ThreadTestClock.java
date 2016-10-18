package pack;

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

public class ThreadTestClock extends Frame implements ActionListener, Runnable {
	private Label lblshow;
	private Thread thread;
	private boolean b = false; // 스레드 종료를 위한 멤버필드 //스레드는 끝내는 방법은 없다
	private Frame frame;

	public ThreadTestClock() {
		lblshow = new Label("Show Time", Label.CENTER);
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
				// TODO Auto-generated method stub
				System.out.println("aaaa");
				lblshow.setBackground(Color.YELLOW);
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
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("err: " + e);
			}
		}
	}

	private void showData() {
		Calendar calendar = Calendar.getInstance();
		int y = calendar.get(calendar.YEAR);
		int m = calendar.get(calendar.MONTH) + 1;
		int d = calendar.get(calendar.DATE);
		int h = calendar.get(calendar.HOUR_OF_DAY);
		int mi = calendar.get(calendar.MINUTE);
		int s = calendar.get(calendar.SECOND);
		lblshow.setText(y + "-" + m + "-" + d + "-\n" + h + ":" + mi + ":" + s);
		
		lblshow.setFont(new Font("돋음", Font.BOLD, 25));
	}

	public static void main(String[] args) {
		new ThreadTestClock();
	}
}
