package pack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SwingTest2 extends JPanel implements ActionListener{
	JButton btnR, btnG, btnB;
	JTextArea txtArea = new JTextArea("", 10, 50);
	
	public SwingTest2() {
		setLayout(new BorderLayout());
		btnR = new JButton("빨강");
		btnG = new JButton("초록");
		btnB = new JButton("파랑");
		btnR.addActionListener(this);
		btnG.addActionListener(this);
		btnB.addActionListener(this);
		
		JPanel panel = new JPanel(); //플로우 레이아웃
		panel.add(btnR);
		panel.add(btnG);
		panel.add(btnB);
		
		add("South", panel);
		add("Center", txtArea);
		
		txtArea.setFont(new Font("돋음", Font.BOLD, 30));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnR){
			txtArea.setBackground(Color.red);
		}else if (e.getSource() == btnG){
			txtArea.setBackground(Color.green);
		}else if (e.getSource() == btnB){
			txtArea.setBackground(Color.blue);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("대화상자 연습");
		
		SwingTest2 test2 = new SwingTest2(); //JPanel
		
		frame.getContentPane().add(test2, "Center"); //센터에 패널을 하나 넣어줌
		frame.setBounds(200, 200, 300, 300);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
