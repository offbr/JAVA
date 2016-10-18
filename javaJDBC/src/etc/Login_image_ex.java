package etc;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login_image_ex extends JFrame implements ActionListener{
	private ImageIcon icon;
	
	private JTextField jtfid = new JTextField();
	private JTextField jtfpw = new JPasswordField();
	private JButton btnLogin = new JButton("Login");
	private JButton btnRigister = new JButton("Register");
	private JButton btnPassword = new JButton("Password");
	
	private Connection conn; //DB 연결 객체
	private Statement stmt; //SQL문을 실행
	private ResultSet rs; //select의 결과를 처리
	
	
	public Login_image_ex() {
		accdb(); //데이터처리
		laytInit(); //레이아웃
			
		setBounds(1200, 0, 640, 1000);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
	}
	
	public void accdb(){
		try { //DB는 필요할때마다 연결하고 끊는다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패: " + e);
		}
	}
	
	public void laytInit(){
		icon = new ImageIcon("C:/work/login.jpg");
		JPanel panel = new JPanel() {
	            public void paintComponent(Graphics g) {
	                g.drawImage(icon.getImage(), 0, 0, null);
	                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
	                super.paintComponent(g);
	            }
	        };
	     panel.setBounds(0, 0, 640, 1000);
	     add(panel);
	     //jtfid.setBounds(300, 200, 104, 48);
	     //jtfid.setOpaque(false);
	     add(jtfid);
	     //add(jtfpw);
	}
	 
	@Override
	public void actionPerformed(ActionEvent e) {
	
	}
	
	public static void main(String[] args) {
		new Login_image_ex();
	
	}
}
