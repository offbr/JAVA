package pack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SwingTest2_1 extends JPanel implements ActionListener{
	JButton btnR, btnG, btnB;
	JTextArea txtArea = new JTextArea("", 10, 50);
	JMenuBar mBar;
	JMenuItem mnuMes, mnuOk, mnuInput;
	public SwingTest2_1() {
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
		
		txtArea.setFont(new Font("궁서", Font.BOLD, 30));
		menuProcess();
	}
	private void menuProcess(){
		mBar = new JMenuBar();
		JMenu menu = new JMenu("대화상자");
		mnuMes = new JMenuItem("메시지창");
		mnuOk = new JMenuItem("확인창");
		mnuInput = new JMenuItem("입력창");
		menu.add(mnuMes);
		menu.add(mnuOk);
		menu.add(mnuInput);
		mBar.add(menu);

		JMenu menu2 = new JMenu("도움말");
		
		JMenuItem mnuA = new JMenuItem("이 프로그램은");
		JMenuItem mnuB = new JMenuItem("환영");
		menu2.add(mnuA);
		menu2.add(mnuB);
		mBar.add(menu2);
		
		mnuMes.addActionListener(this);
		mnuOk.addActionListener(this);
		mnuInput.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnR){
			txtArea.setBackground(Color.red);
		}else if (e.getSource() == btnG){
			txtArea.setBackground(Color.green);
		}else if (e.getSource() == btnB){
			txtArea.setBackground(Color.blue);
		}else if (e.getSource() == mnuMes){
			JOptionPane.showMessageDialog(this, "안녕하세요",
					"제목: 메시지창", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("창 닫히면 수행");
		}else if (e.getSource() == mnuOk){
			int re = JOptionPane.showConfirmDialog(this, "선택하시오",
					"선택", JOptionPane.YES_NO_CANCEL_OPTION);
			//System.out.println(re);
			//if(re==0)
			switch (re) {
			case JOptionPane.YES_OPTION:
				txtArea.append("yes를 선택\n");
				break;
			case JOptionPane.NO_OPTION:
				txtArea.append("no를 선택\n");
				break;
			case JOptionPane.CANCEL_OPTION:
				txtArea.append("cancel를 선택\n");
				break;
			}
		}else if (e.getSource() == mnuInput){
			String str = JOptionPane.showInputDialog(this, "자료입력");
			txtArea.append(str + "\n");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("대화상자 연습");
		
		SwingTest2_1 test2 = new SwingTest2_1(); //JPanel
		
		frame.getContentPane().add(test2, "Center"); //센터에 패널을 하나 넣어줌
		frame.setJMenuBar(test2.mBar);
		frame.setBounds(200, 200, 300, 300);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
