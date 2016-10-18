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

public class SwingTest3 extends JFrame implements ActionListener{
	JTextField txtName, txtAge;
	ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton rdoM, rdoF;
	JLabel lblResilt;
	
	public SwingTest3() {
		super("스윙 연습");
		
		layoutInit();
		
		setBounds(200, 200, 300, 300);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void layoutInit(){
		getContentPane().setLayout(new GridLayout(5, 1));
		
		//1행
		JLabel lbl1 = new JLabel("이름 : ");
		txtName = new JTextField("", 20);
		JPanel pn1 = new JPanel();
		pn1.add(lbl1);
		pn1.add(txtName);
		add(pn1);
		//2행
		txtAge = new JTextField("", 20);
		JPanel pn2 = new JPanel();
		pn2.add(new JLabel("나이 : "));
		pn2.add(txtAge);
		add(pn2);
		//3행
		rdoM = new JRadioButton("남자", true);
		rdoF = new JRadioButton("여자", false);
		buttonGroup.add(rdoM);
		buttonGroup.add(rdoF);
		JPanel pn3 = new JPanel();
		pn3.add(rdoM);
		pn3.add(rdoF);
		add(pn3);
		//4행
		JButton btnOk = new JButton("확인");
		btnOk.addActionListener(this);
		JPanel pn4 = new JPanel();
		pn4.add(btnOk);
		add(pn4);
		//5행
		lblResilt = new JLabel("결과", JLabel.CENTER);
		add(lblResilt);
		
		txtAge.requestFocus();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//입력자료 유효성 검사
		if(txtName.getText().equals("")){
			lblResilt.setText("이름을 입력하시오");
			txtName.requestFocus();
			return;
		}
		if(txtAge.getText().equals("")){
			lblResilt.setText("나이를 입력하시오");
			txtAge.requestFocus();
			return;
		}
		//나이에 대한 숫자 여부 판단 : Ascii 코드 48~ 57, 정규표현식, 에러(try catch)를 이용..
		try {
			int nai = Integer.parseInt(txtAge.getText());		
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "나이는 숫자만 입력가능");
			txtAge.requestFocus();
			return;
		}
		String gender = "";
		if(rdoM.isSelected()){
			gender = "남";
		}else{
			gender = "여";
		}
		String ss = "결과: " + txtName.getText() +"님의 나이는 "+ txtAge.getText()+ "살, 성별은 " + gender;
		lblResilt.setText(ss);
		//System.out.println(rdoM.isSelected()+ " " +rdoF.isSelected());
		//lblResilt.setText(txtName.getText() + txtAge.getText() + rdoM.getActionCommand());
		
	}
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		new SwingTest3();
	}
}
