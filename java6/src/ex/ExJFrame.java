package ex;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ExJFrame extends JFrame implements Runnable, ActionListener{
	private JLabel jlabel;
	private Thread thread;
	
	
	public ExJFrame() {
		jlabel = new JLabel("구구단", JLabel.CENTER);
		add("Center" , jlabel);
		JButton btn1 = new JButton("click");
		btn1.addActionListener(this);
		add("South", btn1);
		setTitle("구구단");
		setBounds(200, 200, 300, 300);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame 종료
		
		thread = new Thread(this);
	}
	
	@Override
	public void run() {
		showdisp();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(thread.isAlive()) return;
		thread.start();
	}
	
	public void showdisp(){
		try {
			for (int i = 2; i < 10; i++) {
				for (int j = 1; j < 10; j++) {
					Thread.sleep(1000);
					jlabel.setText(i + "*" + j + "= " + ( i * j));
					jlabel.setFont(new Font("돋음", Font.BOLD, 50));
					if(j == 1){
						int r = (int)(Math.random() * 255);
						int g = (int)(Math.random() * 255);
						int c = (int)(Math.random() * 255);
						jlabel.setBackground(new Color(r, g, c));
						}
				}
			}	
		} catch (Exception e) {
			System.out.println("err: "+ e);
		}
	}
	
	public static void main(String[] args) {
		new ExJFrame();
	}
}
