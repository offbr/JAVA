package db.pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DbTest3Gui extends JFrame implements ActionListener{
	private JButton btnA = new JButton("전체");
	private JButton btnM = new JButton("남자");
	private JButton btnF = new JButton("여자");
	private JTextArea txtResult = new JTextArea(); 
	
	private Connection conn; //DB 연결 객체
	private Statement stmt; //SQL문을 실행
	private ResultSet rs; //select의 결과를 처리
	
	public DbTest3Gui() {
		setTitle("고객 출력");
	
		laytInit(); //레이아웃
		accdb(); //데이터처리
		
		setBounds(200, 200, 300, 250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void laytInit() {
		JPanel pane1 = new JPanel();
		pane1.add(btnA); pane1.add(btnM); pane1.add(btnF);
		
		txtResult.setEditable(false);
		JScrollPane pane = new JScrollPane(txtResult);
		
		add("Center", pane);
		add("North", pane1);
		
		btnA.addActionListener(this);
		btnM.addActionListener(this);
		btnF.addActionListener(this);
	}
	
	private void accdb(){ //드라이버 로딩만 한다
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
			
			String sql = "select gogek_no, gogek_name, gogek_jumin from gogek";
		
			if(e.getSource() == btnA){
				
			}else if(e.getSource() == btnM){ //문자열 더하기는 버퍼나 빌더.
				sql += " where gogek_jumin like '%-1%'";
				//sql += " where gogek_jumin like '_______1%'";
				//sql += " where substr(gogek_jumin,8,1) = 1";
			}else if(e.getSource() == btnF){
				sql += " where gogek_jumin like '%-2%'";
			}
			txtResult.setText(null);
			rs = stmt.executeQuery(sql);
			int count = 0;
			txtResult.setText("고객번호\t고객명\t주민번호\n");
			while(rs.next()){
				String str = rs.getString("gogek_no") + "\t" +
							rs.getString("gogek_name") + "\t" +
							rs.getString("gogek_jumin") + "\n";
				txtResult.append(str);
				count++;
			}
			txtResult.append("인원수: " + count);	
			
		} catch (Exception e2) {
			System.out.println("actionPerformed err : " + e2);
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("close err : " + e2);
			}
		}
	}
	
	
	public static void main(String[] args) {
		new DbTest3Gui();
	}
}






























