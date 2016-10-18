package etc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Aa extends JFrame implements ActionListener{
	
	public Aa() {
		JLabel jlabel = new JLabel("헬로, 자바", JLabel.CENTER);
		add("Center" , jlabel);
		setTitle("헬로 자바 프로그램");
		setBounds(200, 200, 500, 200);
		setVisible(true);
		 // JFrame 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton btn = new JButton("");
		//JColorChooser.createDialog(c, title, modal, chooserPane, okListener, cancelListener)
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new Aa();
	}	
}



