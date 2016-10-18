package db.ex;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DbTest6_Test extends JFrame implements ActionListener{
	private JTextField txt_no, txt_name, txt_jik, txt_buser, txt_busertel;
	private JTextArea txt_all;
	private JButton btn_login, btn_logout;
	JPanel panel1_1 = null;
	private CardLayout card = new CardLayout();
	
	private Connection conn; //DB 연결 객체
	private Statement stmt; //SQL문을 실행
	private ResultSet rs; //select의 결과를 처리
	
	
	public DbTest6_Test() {	
		setTitle("로그인");
	
		laytInit(); //레이아웃
		accdb(); //데이터처리
		
		setBounds(200, 200, 500, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	private void laytInit(){
		// 사번 이름 검색창
		JPanel panel1 = new JPanel();
		txt_no = new JTextField(3);
		txt_name = new JTextField(5);
		panel1.add(new JLabel("사번: "));
		panel1.add(txt_no);
		panel1.add(new JLabel("이름: "));
		panel1.add(txt_name);
		
		//버튼 카드레이아웃
		panel1_1 = new JPanel();
		panel1_1.setLayout(card);

		btn_login = new JButton("로그인");
		btn_logout = new JButton("로그아웃");
		
		panel1_1.add("aa", btn_login);
		panel1_1.add("bb", btn_logout);
		panel1.add(panel1_1);
		
		add("North", panel1);
		
		//버튼 액션 장착
		btn_login.addActionListener(this);
		btn_login.setMnemonic(KeyEvent.VK_ENTER);
		btn_logout.addActionListener(this);
		btn_login.setMnemonic(KeyEvent.VK_ENTER);
		
		//부서명
		JPanel panel2 = new JPanel();
		txt_buser = new JTextField(5);
		txt_busertel = new JTextField(12);
		txt_jik = new JTextField(3);
		panel2.add(new JLabel("부서명: "));
		panel2.add(txt_buser);
		panel2.add(new JLabel("부서전화: "));
		panel2.add(txt_busertel);
		panel2.add(new JLabel("직급: "));
		panel2.add(txt_jik);
		
		txt_all = new JTextArea("");
		panel2.add(txt_all);

		add(panel2);
		// 부서명, 부서번호, 직급 수정X
		txt_buser.setEnabled(false);
		txt_busertel.setEditable(false);
		txt_jik.setEditable(false);
	}
	
	private void accdb(){
		try { //DB는 필요할때마다 연결하고 끊는다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패: " + e);
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			stmt = conn.createStatement();
			//쿼리문
			String sql = "select buser_name, buser_tel,"
					+ " sawon_jik,"
					+ " gogek_name, gogek_tel, gogek_jumin from sawon"
					+ " left join buser on buser_no = buser_num"
					+ " inner join gogek on sawon_no = gogek_damsano where";
			//로그인
			if(e.getSource() == btn_login){
				if(!txt_no.getText().equals("") && !txt_name.getText().equals("")){
					sql += " sawon_no="+txt_no.getText()+"";
					sql += " and sawon_name='"+txt_name.getText()+"'";
					card.show(panel1_1, "bb");
					txt_no.setEditable(false);
					txt_name.setEditable(false);
				}else{
					JOptionPane.showMessageDialog(this, "정확히 입력하세요");
				}
				rs = stmt.executeQuery(sql);
				
				txt_all.setText("고객명\t고객전화\t주민번호\n");
				//쿼리문 꺼내오기
				while(rs.next()){
					String str1 = rs.getString("buser_name"); 
					String str2 = rs.getString("buser_tel");
					String str3 = rs.getString("sawon_jik");
					
					String str4 = rs.getString("gogek_name") + "\t" +
							rs.getString("gogek_tel") + "\t" +
							rs.getString("gogek_jumin") + "\n";
					
					txt_buser.setText(str1);
					txt_busertel.setText(str2);
					txt_jik.setText(str3);
					
					txt_all.append(str4);
				}
			}
			//로그아웃
			if(e.getSource() == btn_logout){
				card.show(panel1_1, "aa");
				txt_no.setEditable(true);
				txt_name.setEditable(true);
				txt_no.setText("");
				txt_name.setText("");
				txt_buser.setText("");
				txt_busertel.setText("");
				txt_jik.setText("");
				txt_all.setText("");
			}
		} catch (Exception e2) {
			System.out.println("actionPerformed err : " + e2);
		}finally {
			try { // 닫기
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("close err : " + e2);
			}
		}
	}
		
	public static void main(String[] args) {
		new DbTest6_Test();
	}
}






















