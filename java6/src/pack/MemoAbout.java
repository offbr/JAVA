package pack;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MemoAbout extends JDialog implements ActionListener{
	
	public MemoAbout(JFrame frame) {
		super(frame);
		
		setTitle("메모장이란");
		setModal(true); //포커스 넘어가지 않는다 = false : Modeless // true : Modal
		JLabel lbl = new JLabel("미니 메모장 ver 1.0");
		JButton btn = new JButton("확인");
		btn.addActionListener(this);
		add("Center", lbl);
		add("South", btn);
		
		setBackground(Color.BLUE);
		setBounds(350, 250, 200, 150);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose(); // 대화상자 닫기
	}
}
