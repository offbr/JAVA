package pack;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwingTest extends JFrame implements ActionListener{ //JFrame
	JLabel lblShow;
	int count = 0;
	
	public SwingTest() {
		setTitle("스윙");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
	
		JButton btn = new JButton("클릭(C)");
		btn.addActionListener(this);
		btn.setMnemonic(KeyEvent.VK_C); //alt + c
		panel.add(btn);
		
		lblShow = new JLabel("클릭 수: 0");
		panel.add(lblShow);
		
		setBounds(200, 200, 300, 300); 
		setVisible(true);
		
		//getContentPane().add(panel);
		add(panel); // JFrame는 getContentPane()이 생략
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //윈도우 종료
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		count++;
		lblShow.setText("클릭 수: " + count);
		
	}
	
	public static void main(String[] args) {
		new SwingTest();
	}
}
