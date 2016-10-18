package ex;

import designcomponent.*;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mask_Login extends JFrame implements ActionListener{
	private BPanel contentPane;
	private BTextField txt_Id, txt_Pwd;
	private BButton btn_Join, btn_Login;
	Mask_Infomation infomation = new Mask_Infomation();
	
	public Mask_Login() {
		setTitle("Mask Rank");
		layInit();
		setVisible(true);
		setLocationRelativeTo(null); // 가운데 위치
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void layInit(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new BPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BPanel panel = new BPanel();
		panel.setBounds(12, 10, 410, 242);
		contentPane.add(panel);
		panel.setLayout(null);
		
		BLabel lbl_id = new BLabel(" ID");
		lbl_id.setBounds(119, 95, 57, 15);
		panel.add(lbl_id);
		
		BLabel lbl_pwd = new BLabel("Password");
		lbl_pwd.setBounds(101, 141, 81, 15);
		panel.add(lbl_pwd);
		
		BLabel lbl_Mask = new BLabel("Mask Rank");
		lbl_Mask.setFont(new Font("Sanserif", Font.BOLD, 40));
		lbl_Mask.setBounds(101, 10, 227, 58);
		panel.add(lbl_Mask);
		
		txt_Id = new BTextField();
		txt_Id.setBounds(200, 92, 116, 21);
		panel.add(txt_Id);
		txt_Id.setColumns(10);
		
		txt_Pwd = new BTextField();
		txt_Pwd.setBounds(200, 138, 116, 21);
		panel.add(txt_Pwd);
		txt_Pwd.setColumns(10);
		
		btn_Join = new BButton("Join");
		btn_Join.setBounds(95, 189, 97, 23);
		panel.add(btn_Join);
		
		btn_Login = new BButton("Login");
		btn_Login.setBounds(219, 189, 97, 23);
		panel.add(btn_Login);
		
		btn_Join.addActionListener(this);
		btn_Login.addActionListener(this);
		txt_Pwd.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_Login || e.getSource() == txt_Pwd) {
			try {
				Mask_MDI.getInstance().dataout.writeUTF("l," + txt_Id.getText() + "," + txt_Pwd.getText());
			} catch (Exception e2) {
				System.out.println("로그인정보 전송실패" + e2);
			}
		} else if (e.getSource() == btn_Join) {
			infomation.setVisible(true);
			setVisible(false);
		}
	}
}

