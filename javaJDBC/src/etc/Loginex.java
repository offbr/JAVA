package etc;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Loginex extends JFrame implements ActionListener{
	JPanel pn1 = new JPanel();
	JPanel pn2 = new JPanel();
	JPanel pn3 = new JPanel();
	JPanel pn4 = new JPanel();
	JPanel pn5 = new JPanel();
	JButton btn1, btn2, btn3;
	JTextField id1 = null;
	JTextField pw1 = null;
	CardLayout card = new CardLayout();
	
	static Connection conn = null;
	static ResultSet rs = null;
	static Statement stmt = null;
	
	static String url = null;
	static String id = null;
	static String pw = null;
	
	public static void Jdb() {
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver"); //1단계 : JDBC 드라이버로드 
		System.out.println("driver load success"); //OracleDriver 클래스가 있는지 확인.
		try {
			url = "jdbc:oracle:thin:@localhost:1521:orcl"; //ip:127.0.0.1, port1521 접속한다.
			conn = DriverManager.getConnection(url, id, pw); //2단계 : DB Connection
			System.out.println("db connect success"); //DB의 커넥션을 가져온다(url, id, pw 입력)
			//쿼리를 처리하기 위하여 Statement 생성
			stmt = conn.createStatement();
			//다음 쿼리문을 실행하여 결과 집합 가져온다.
			//레코드를 하나씩 이동.
			//Statement 와 DB 커넥션을 닫는다 (쿼리를 처리 한 후 반드시 커넥션까지 같이 닫아준다.)
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println(e); // DB관련 
		}
	} catch (ClassNotFoundException e) {
		System.out.println(e); // 라이브러리관련.
	}
}

	public Loginex() {
		//setLayout(new GridBagLayout());
		pn1 = new JPanel();
		//JLabel la = new JLabel("관리\n");
		JLabel la1 = new JLabel(" ID: ");
		id1 = new JTextField("scott", 20); 
		JLabel la2 = new JLabel("PW: ");
		pw1 = new JPasswordField("tiger", 20);
		btn1 = new JButton(" Login ");
		//pn1.add(la);
		pn1.add(la1);
		pn1.add(id1);
		pn1.add(la2);
		pn1.add(pw1);
		pn1.add(btn1);
		btn1.addActionListener(this); 
		
		pn2 = new JPanel();
		pn3 = new JPanel();
		pn2.setLayout(new BorderLayout());
		JLabel la3 = new JLabel("반갑습니다 "+id1.getText()+ "님", JLabel.CENTER);		
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
		
		setBounds(200, 200, 300, 130);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		id = id1.getText();
		pw = pw1.getText();
		
		if(id.equals("scott")){
			if(pw.equals("tiger")){ 
				card.show(pn4,"b"); //로그인 패널전환
				Loginex.Jdb(); //로그인 실행				
			}
		}else{
			JOptionPane.showMessageDialog(this, "로그인에 실패했습니다");
			id1.setText(""); pw1.setText("");
		}
		
		if(e.getActionCommand().equals("Logout")){ //로그아웃 패널전환
			card.show(pn4, "a");
		}
		
		if(e.getActionCommand().equals("Go")){
			//bb.setVisible(true);
		}
	}	
	
	public static void main(String[] args) {
		new Loginex();
	}
}

