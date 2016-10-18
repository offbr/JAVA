package ex;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExFrame extends Frame implements Runnable, ActionListener {
	private Label label;
	private Thread thread;
	boolean choice = false;

	public ExFrame() {
		label = new Label("dan", Label.CENTER);
		add("Center", label);
		Button btn1 = new Button("click");
		btn1.addActionListener(this);
		add("South", btn1);

		setTitle("구구단");
		setBounds(200, 200, 300, 300);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				choice = true;
				System.exit(0);
			}
		});

		thread = new Thread(this);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("만나");
		if (thread.isAlive())
			return;
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			if (choice == true)
				break; // 중요하다
			showdisp();
		}
	}

	private void showdisp() {
		for (int i = 2; i < 10; i++) {
			for (int j = 1; j < 10; j++) {
				try {
					Thread.sleep(1000);
					label.setText(i + "*" + j + "= " + (i * j));
					label.setFont(new Font("돋음", Font.BOLD, 50));
					if(j == 1){
						int r = (int)(Math.random() * 255);
						int g = (int)(Math.random() * 255);
						int c = (int)(Math.random() * 255);
						label.setBackground(new Color(r, g, c));
						}
				} catch (Exception e) {
					System.out.println("err: " + e);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new ExFrame();
	}
}
