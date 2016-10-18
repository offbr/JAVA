package db.pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DbTest14Table extends JFrame {
	String [][] datas = new String[0][4];
	String [] title = {"코드", "품명", "수량", "단가"};
	DefaultTableModel model; //내부적인 데이터
	JTable table; //내부적인 데이터 모델을 가지고 테이블뷰로 보여준다
	JLabel lblcount;
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public DbTest14Table() {
		setTitle("테이블 연습");
		
		layInit();
		accDb();
		
		
		setBounds(800, 400, 300, 250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void layInit(){
		model = new DefaultTableModel(datas, title);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		
		lblcount = new JLabel("건수 : 0"); 
		add("Center", scrollPane);
		add("South", lblcount);
	}
	
	private void accDb(){
		try { //DB는 필요할때마다 연결하고 끊는다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			pstmt = conn.prepareStatement("select * from sangdata order by code");
			rs = pstmt.executeQuery();
			int cou = 0;
			while(rs.next()){
				String code = rs.getString("code");
				String sang = rs.getString("sang");
				String su = rs.getString("su");
				String dan = rs.getString("dan");
				String[] imsi = {code, sang, su, dan};			
				model.addRow(imsi);
				cou++;
			}
			lblcount.setText("건수 : " + cou);
		} catch (Exception e) {
			System.out.println("accDb err: " + e);
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
	
	public static void main(String[] args) {
		new DbTest14Table();
	}
}











