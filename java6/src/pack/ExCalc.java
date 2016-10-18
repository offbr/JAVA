package pack;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ExCalc extends JPanel implements ActionListener{
	private JTextField txt1, txt2;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private ButtonGroup buttonGroup1 = new ButtonGroup();
	private JRadioButton op1, op2, op3, op4;
	private JButton hap1, reset1, close1;
	private JLabel relabel;
	private int hap;
	private String op;
	public ExCalc() {
		//숫자 텍스트1
		setLayout(new GridLayout(5, 1));
		txt1 = new JTextField("", 5);
		JPanel pn1 = new JPanel();
		pn1.add(new JLabel("숫자 : "));
		pn1.add(txt1);
		add(pn1);
		
		//숫자 텍스트2
		txt2 = new JTextField("", 5);
		JPanel pn2 = new JPanel();
		pn2.add(new JLabel("숫자 : "));
		pn2.add(txt2);
		add(pn2);
		
		//연산 버튼
		op1 = new JRadioButton("+", true);
		op2 = new JRadioButton("-", false);
		op3 = new JRadioButton("*", false);
		op4 = new JRadioButton("/", false);
		buttonGroup.add(op1);
		buttonGroup.add(op2);
		buttonGroup.add(op3);
		buttonGroup.add(op4);
		JPanel pn3 = new JPanel();
		pn3.add(new JLabel("연산선택: "));
		pn3.add(op1);
		pn3.add(op2);
		pn3.add(op3);
		pn3.add(op4);
		add(pn3);
		op1.addActionListener(this);
		op2.addActionListener(this);
		op3.addActionListener(this);
		op4.addActionListener(this);
		
		//버튼 이벤트리스너 장착

		//결과출력
		relabel = new JLabel("결과: ");
		add(relabel);
		
		//
		hap1 = new JButton("계산");
		reset1 = new JButton("초기화");
		close1 = new JButton("종료");	
		JPanel pn4 = new JPanel();
		//buttonGroup.add(hap1);
		//buttonGroup.add(reset1);
		//buttonGroup.add(close1);
		pn4.add(hap1);
		pn4.add(reset1);
		pn4.add(close1);
		add(pn4);
		//버튼 이벤트 리스너 장착
		hap1.addActionListener(this);
		reset1.addActionListener(this);
		close1.addActionListener(this);
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(close1)){
			int re = JOptionPane.showConfirmDialog(this, "종료 하시겠습니까?", "Exit", JOptionPane.YES_NO_OPTION);
			if(re == JOptionPane.YES_NO_OPTION) System.exit(0);
		}else if(e.getSource() == reset1){
			txt1.setText("");
			txt2.setText("");
			relabel.setText("결과: ");
			op1.doClick();
		}
		
		
		if(txt1.getText().equals("")){
			relabel.setText("숫자를 입력하시오");
			txt1.requestFocus();
			return;
		}
		if(txt2.getText().equals("")){
			relabel.setText("숫자를 입력하시오");
			txt2.requestFocus();
			return;
		}

		try {
			Integer.parseInt(txt1.getText());
			Integer.parseInt(txt2.getText());
		} catch (Exception e2) {
			relabel.setText("숫자만 입력가능합니다");
			txt1.requestFocus();
			txt2.requestFocus();
			return;	
		}
		
		
		if(e.getSource() == hap1){
			calc();
		}
	}
	
	public void calc(){
		int t1 = Integer.parseInt(txt1.getText());
		int t2 = Integer.parseInt(txt2.getText());
		
		if(op1.isSelected()){			
			hap = t1 + t2;					////////////////e.getSource가 아닌 isSelected !!!
		}else if(op2.isSelected()){		
			hap = t1 - t2;				
		}else if(op3.isSelected()){		
			hap = t1 * t2;				
		}else if(op4.isSelected()){
			if(t2 == 0){
				relabel.setText("0으로 나눌수 없습니다");
				txt2.requestFocus();
				return;
			}
			hap = t1 / t2;
		}
		relabel.setText(String.valueOf(hap));
			
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("미니계산기");
		
		ExCalc ex = new ExCalc();
		frame.getContentPane().add(ex, "West");
		frame.setBounds(200, 200, 250, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //종료
	}

}
