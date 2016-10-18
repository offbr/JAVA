package db.ex;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DbTest_14_ex1 extends JFrame implements ItemListener{
	private JComboBox<String> combo;
	private JCheckBox cbox;
	private JLabel lbl_show, lbl_pay;
	private String [][] datas = new String[0][5];
	private String [] title = {"사번", "직원명", "연봉", "입사년도", "관리고객수"};
	private DefaultTableModel model; //내부적인 데이터
	private JTable table; //내부적인 데이터 모델을 가지고 테이블뷰로 보여준다
	
	private Connection conn; //DB 연결 객체
	private PreparedStatement pstmt; //SQL문을 실행
	private ResultSet rs; //select의 결과를 처리
	
	private String sql ,sql_txt;
	//private boolean all_disp = true;
	//private String where_buser = ""; 
	private int sort = 0;
	private int buser;
	public DbTest_14_ex1() {
		setTitle("부서명별 자료보기");
		
		layInit();
		combo_name();
		//disp();
		setVisible(true);
		setBounds(600, 400, 400, 365);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void layInit(){
		JPanel panel1 = new JPanel();
		combo = new JComboBox<String>();
		cbox = new JCheckBox();
		panel1.add(new JLabel("부서명: "));
		panel1.add(combo);
		panel1.add(new JLabel("연봉별 정렬(내림)"));
		panel1.add(cbox);
			
		add("North", panel1);
		
		model = new DefaultTableModel(datas, title);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		add("Center", scrollPane);
		
		JPanel panel2 = new JPanel();
		//panel2.setLayout(new GridLayout(1, 2));
		lbl_show = new JLabel("직원 수: ");
		lbl_pay = new JLabel("연봉 => 최대:   평균:   최소: ");
		panel2.add(lbl_show);
		panel2.add(lbl_pay);
		add("South", panel2);
		
		combo.addItemListener(this);
		cbox.addItemListener(this);	
	}
	//기본 DB연결
	private void accDb(){ 
		try { //DB는 필요할때마다 연결하고 끊는다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패: " + e);
		}
	}
	//콤보에 목록주기
	private void combo_name(){ 
		try {
			accDb();
			combo.addItem("전체");
			
			sql = "select buser_name from buser";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
			combo.addItem(rs.getString("buser_name"));
			}
			
			//disp_txt();
			
		} catch (Exception e) {
			System.out.println("콤보목록 실패: " + e);
		}	

	}
	// 메인 하단 라벨 텍스트
	private void disp_txt(){
		try {
			accDb();
			if(buser == 0){
				sql_txt = "select nvl(max(sawon_pay), 0) as sawon_max, nvl(round(avg(sawon_pay)), 0) as sawon_avg, nvl(min(sawon_pay), 0) as sawon_min" +
							 " from sawon";
			}else{
				sql_txt = "select nvl(max(sawon_pay), 0) as sawon_max, nvl(round(avg(sawon_pay)), 0) as sawon_avg, nvl(min(sawon_pay), 0) as sawon_min" +
						 " from sawon where buser_num = ?";
			}
			
			pstmt = conn.prepareStatement(sql_txt);
			
			if(buser == 10){
				pstmt.setString(1, "10");
			}else if(buser == 20){
				pstmt.setString(1, "20");
			}else if(buser == 30){
				pstmt.setString(1, "30");
			}else if(buser == 40){
				pstmt.setString(1, "40");
			}else if(buser == 50){
				pstmt.setString(1, "50");
			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				String max_pay = rs.getString("sawon_max");
				String avg_pay = rs.getString("sawon_avg");
				String min_pay = rs.getString("sawon_min");
				lbl_pay.setText("연봉 => 최대:"+max_pay+" 평균:"+avg_pay+" 최소:"+min_pay+"");
			}
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패: " + e);
		} 		
	}
	//메인 뷰
	private void disp(){
		try { //DB는 필요할때마다 연결하고 끊는다.
			accDb();
			if(buser == 0){
				sql = "select sawon_no, sawon_name, sawon_pay, to_char(sawon_ibsail, 'yyyy') as sawon_ibsail, count(gogek_damsano) as gogek_su from sawon"
						+ " left join buser on buser_no = buser_num"
						+ " left join gogek on sawon_no = gogek_damsano group by sawon_no, sawon_name, sawon_pay, sawon_ibsail, gogek_damsano";
			}else{ 
				sql = "select sawon_no, sawon_name, sawon_pay, to_char(sawon_ibsail, 'yyyy') as sawon_ibsail, count(gogek_damsano) as gogek_su from sawon"
						+ " left join buser on buser_no = buser_num" + " left join gogek on sawon_no = gogek_damsano"
						+ " where buser_num = ?"
						+ " group by sawon_no, sawon_name, sawon_pay, sawon_ibsail, gogek_damsano";
			}
			/*
			if (sort%2==0) sql += " order by sawon_no";
			else sql += " order by sawon_pay desc";
			*/
			/*
			if (all_disp == true)
				sql += " order by sawon_no";
			else
				sql += " order by sawon_pay desc";
			*/
			if(!cbox.isSelected()) sql += " order by sawon_no";
			else sql += " order by sawon_pay desc";
				
			pstmt = conn.prepareStatement(sql);
			
			if(buser == 10){
				pstmt.setString(1, "10");
			}else if(buser == 20){
				pstmt.setString(1, "20");
			}else if(buser == 30){
				pstmt.setString(1, "30");
			}else if(buser == 40){
				pstmt.setString(1, "40");
			}else if(buser == 50){
				pstmt.setString(1, "50");
			}
			
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()){
				String sa_no = rs.getString("sawon_no");
				String sa_name = rs.getString("sawon_name");
				String sa_pay = rs.getString("sawon_pay");
				String sa_ibsail = rs.getString("sawon_ibsail");
				String sa_gogek = rs.getString("gogek_su");
				String[] rowData = {sa_no, sa_name, sa_pay, sa_ibsail, sa_gogek};
				model.addRow(rowData);
				count++;
			}
			lbl_show.setText("직원 수: "+ count );
			//disp_txt();
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패: " + e);
		} /*finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("close err : " + e2);
			}
		}		*/
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
			model.setNumRows(0);
			if (e.getItem().equals("전체")) {
				buser = 0;				
				disp();
			}
			if (e.getItem().equals("총무부")) {
				buser = 10;
				disp();
			}
			if (e.getItem().equals("영업부")) {
				buser = 20;
				disp();
			}
			if (e.getItem().equals("전산부")) {
				buser = 30;
				disp();
			}
			if (e.getItem().equals("관리부")) {
				buser = 40;
				disp();
			}
			if (e.getItem().equals("비서실")) {
				buser = 50;
				disp();
			}
			if (e.getSource() == cbox) {
			//	all_disp = false;
				sort++;
				disp();
			}
	}
	public static void main(String[] args) {
		new DbTest_14_ex1();	
	}	
}