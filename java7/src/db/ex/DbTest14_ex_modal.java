package db.ex;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DbTest14_ex_modal extends JDialog implements ActionListener{
	private JTextField txt_no, txt_name;
	private JButton btn;
	
	private Connection conn; //DB 연결 객체
	private PreparedStatement pstmt; //SQL문을 실행
	private ResultSet rs; //select의 결과를 처리
	private int loginCount = 0;
	static boolean log_switch;
	
	public DbTest14_ex_modal(JFrame frame) {
		super(frame);
		setTitle("로그인");

		layInit();
		
		setBounds(850, 500, 200, 130);
		setModal(true);
		setVisible(true);
	}
	
	private void layInit(){
		JPanel panel1 = new JPanel();
		txt_no = new JTextField(10);
		panel1.add(new JLabel("사번 :"));
		panel1.add(txt_no);
		
		add("North",panel1);
		
		JPanel panel2 = new JPanel();
		txt_name = new JTextField(10);
		btn = new JButton("확인");
		panel2.add(new JLabel("이름 :"));
		panel2.add(txt_name);
		panel2.add(btn);
		
		add("Center", panel2);
		
		btn.addActionListener(this);
		btn.setMnemonic(KeyEvent.VK_ENTER);
	}
	
	private void accDb(){
		try { //DB는 필요할때마다 연결하고 끊는다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패: " + e);
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			accDb();
			String sql = "select * from sawon where sawon_no = ? and sawon_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, txt_no.getText());
			pstmt.setString(2, txt_name.getText());
			rs = pstmt.executeQuery();
			
			if(rs.next()){ 
				dispose();
				log_switch = true;
			}else{
				loginCount++;	
				JOptionPane.showMessageDialog(this, "로그인실패");
				txt_no.setText("");
				txt_name.setText("");
			}
			
			if (loginCount == 2) {
				log_switch = false;
				dispose();
			}
		} catch (Exception e2) {
			System.out.println("쿼리문 에러: " + e2);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("close err : " + e2);
			}
		}		
	}
}
