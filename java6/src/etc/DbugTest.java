package etc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DbugTest extends JFrame implements ActionListener{
	int cou = 0;
	int tot = 0;
	JButton button = new JButton("클릭");
//	JTextField txtA = null;  //java.lang.NullPointException
	JTextField txtA = new JTextField();
	
	public DbugTest() {
		setBounds(200, 200, 300, 300);
		setVisible(true);
		
		add("North", txtA);
		add("Center", button);
		button.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		aa();
//		JOptionPane.showMessageDialog(this, cou);  //대화상자로 변수 값 확인
		txtA.setText(Integer.toString(cou) + ", 합은 " +tot);
		
	}
	
	private void aa(){
		for (int i = 0; i < 5; i++) {
			++cou;
			System.out.println(cou);  //변수 값 콘솔창으로 확인하기
			tot += cou;
			
			bb();
		}
	}
	
	private void bb(){
		int kbs = 9;
		kbs++;
		System.out.println("kbs:" + kbs);
	}
	public static void main(String[] args) {
		new DbugTest();

	}

}
