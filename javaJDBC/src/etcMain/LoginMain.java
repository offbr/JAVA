package etcMain;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginMain extends JFrame implements ActionListener{
	JPanel pn1 = new JPanel();
	JPanel pn2 = new JPanel();
	JPanel pn3 = new JPanel();
	JPanel pn4 = new JPanel();
	JPanel pn5 = new JPanel();
	JLabel la3 = new JLabel();
	JButton btn, btn1, btn2, btn3;
	JTextField id1 = null;
	JTextField pw1 = null;
	CardLayout card = new CardLayout();
	
	static Connection conn = null;
	static ResultSet rs = null;
	static Statement stmt = null;
	static PreparedStatement ppst = null;
	
	static String url = null;
	static String idon = "manager";
	static String pwon = "123";
	
	static String getpw = null;
	       
	public LoginMain() {
		accdb();
		laytInit();
		setBounds(500, 300, 300, 130);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void accdb(){ //드라이버 로딩만 한다
		try { //DB는 필요할때마다 연결하고 끊는다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패: " + e);
		}
		
	}

	private void laytInit() {
		//setLayout(new GridBagLayout());
		pn1 = new JPanel();
		//JLabel la = new JLabel("관리\n");
		JLabel la1 = new JLabel(" ID: ");
		id1 = new JTextField("", 20); 
		JLabel la2 = new JLabel("PW: ");
		pw1 = new JPasswordField("", 20);
		btn = new JButton("  join  ");
		btn1 = new JButton("Login");
		//pn1.add(la);
		pn1.add(la1);
		pn1.add(id1);
		pn1.add(la2);
		pn1.add(pw1);
		pn1.add(btn1); //로그인
		pn1.add(btn); //조인
		btn.addActionListener(this);
		btn1.addActionListener(this); 
		
		pn2 = new JPanel();
		pn3 = new JPanel();
		pn2.setLayout(new BorderLayout());
		la3 = new JLabel(" ", JLabel.CENTER);		
		btn2 = new JButton("Logout");
		btn3 = new JButton("Go");
		pn2.add("North", la3);
		pn3.add(btn2);
		pn2.add("South",btn3);
		pn2.add("Center", pn3);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		
		pn4 = new JPanel();
		pn4.setLayout(card);
		pn4.add("a",pn1);
		pn4.add("b",pn2);
		add(pn4);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", idon, pwon);
			stmt = conn.createStatement();
			if (e.getActionCommand().equals("Login")) {
				String sql = "select pw from test where id=" + id1.getText();
				rs = stmt.executeQuery(sql);
			
				while (rs.next()) { // 1,2,3,4 애트리뷰트값을 화면에 출력
					getpw = rs.getString(1);
					if (getpw.equals(pw1.getText())) {
						System.out.println("로그인성공");
						card.show(pn4, "b"); // 로그인 패널전환
						la3.setText("반갑습니다 "+id1.getText()+ " 님");
					} else {
						JOptionPane.showMessageDialog(this, "로그인에 실패했습니다");
						id1.setText("");
						pw1.setText("");
					}
				}
			}
			
			if(e.getSource().equals(btn2)){ //로그아웃 패널전환
				card.show(pn4, "a");
			}
			
			if(e.getActionCommand().equals("  join  ")){ //로그아웃 패널전환
				setVisible(false);
				new JoinMain();
			}
			
			if(e.getActionCommand().equals("Go")){
				//bb.setVisible(true);
			}
	
		} catch (Exception e2) {
			System.out.println("actionPerformed err : " + e2);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				System.out.println("close err : " + e2);
			}
		}
	}

	public static void main(String[] args) {
		new LoginMain();
	}
}

