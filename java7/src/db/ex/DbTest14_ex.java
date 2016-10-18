package db.ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DbTest14_ex extends JFrame{
	private JTextField txt_no, txt_name;
	private JLabel lblcount;
	private String [][] datas = new String[0][4];
	private String [] title = {"사번", "이름", "성별", "연봉", "세금", "실수령액"};
	private DefaultTableModel model; //내부적인 데이터
	private JTable table; //내부적인 데이터 모델을 가지고 테이블뷰로 보여준다
	
	private Connection conn; //DB 연결 객체
	private PreparedStatement pstmt; //SQL문을 실행
	private ResultSet rs; //select의 결과를 처리
	
	public DbTest14_ex() {
		setTitle("JTable 직원");
		
		layInit();
		accDb();
	
		setBounds(700, 400, 500, 320);
		setVisible(true);
		new DbTest14_ex_modal(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if(DbTest14_ex_modal.log_switch == true){ 
			alogin();
		}else{
			System.exit(0);
		}
	}
	
	private void accDb(){
		try { //DB는 필요할때마다 연결하고 끊는다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패: " + e);
		}
	}
	
	private void layInit(){
		model = new DefaultTableModel(datas, title);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		add("Center", scrollPane);
		lblcount = new JLabel("");
		add("South", lblcount);
	}
	
	public void alogin(){
		//int login = JOptionPane.showConfirmDialog(this,	panel1, "로그인", JOptionPane.YES_NO_OPTION);
		try {
			accDb();
			String sql ="select sawon_no, sawon_name, sawon_gen, to_char((sawon_pay * 100000), '999,999,999') as sawon_pay,"+
						" case"+
						" when sawon_pay >= 4000 then to_char((round(sawon_pay * 0.05)*10000), '999,999,999')"+
						" when sawon_pay >= 3500 then to_char((round(sawon_pay * 0.04)*10000), '999,999,999')"+
						" when sawon_pay >= 3000 then to_char((round(sawon_pay * 0.03)*10000), '999,999,999')"+
						" when sawon_pay < 3000 then to_char((round(sawon_pay * 0.02)*10000), '999,999,999')"+
						" end as sawon_tax,"+
						" case"+
						" when sawon_pay >= 4000 then to_char((round(sawon_pay - sawon_pay * 0.05)*10000), '999,999,999')"+
						" when sawon_pay >= 3500 then to_char((round(sawon_pay - sawon_pay * 0.05)*10000), '999,999,999')"+
						" when sawon_pay >= 3000 then to_char((round(sawon_pay - sawon_pay * 0.05)*10000), '999,999,999')"+
						" when sawon_pay < 3000 then to_char((round(sawon_pay - sawon_pay * 0.05)*10000), '999,999,999')"+
						" end as sawon_hap"+
						" from sawon";
						
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			int count = 1;
			while(rs.next()){
				String no = rs.getString("sawon_no");
				String name = rs.getString("sawon_name");
				String gen = rs.getString("sawon_gen");
				String pay = rs.getString("sawon_pay");
				String tax = rs.getString("sawon_tax");
				String hap = rs.getString("sawon_hap");
				String[] rowData = {no, name, gen, pay, tax, hap};
				model.addRow(rowData);
				count++;
			}
			lblcount.setText("직원 수:" + count);
		} catch (Exception e) {
			System.out.println("쿼리문 에러: " + e);
		}finally {
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
		new DbTest14_ex();
	}
}


















