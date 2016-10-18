package ex;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExCalc extends JFrame implements ActionListener{
	private JLabel lab1, lab2;
	private JPanel pn1, pn2;
	private JButton btn1, op1, op2, op3, op4;
	private JTextField txt1, txt2;
	
	public ExCalc() {
		lab1 = new JLabel("숫자: ");
		txt1 = new JTextField("", 10); //키보드로 자료 입력용 클래스(컴포넌트)
		btn1 = new JButton("click");
		pn1 = new JPanel();
		pn2 = new JPanel();
		//panel.add(lab1);
		pn2.add(lab1);
		pn2.add(txt1);
		pn1.add(btn1);
		add(pn1);
		add("West", pn2);
		setTitle("계산기");
		setBounds(300, 300, 300, 300);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //윈도우 종료
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	public static void main(String[] args) {
		new ExCalc();
	}
}
