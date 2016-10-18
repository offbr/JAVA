package db.ex;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DbTest14Table_test2 extends JFrame implements ActionListener{
	private JComboBox<String> cb_dept = new JComboBox<>();
	private JLabel lbl_cnt = new JLabel("직원수 : ");
	private JLabel lbl_max = new JLabel("최대 : "); 
	private JLabel lbl_avg = new JLabel("평균 : "); 
	private JLabel lbl_min = new JLabel("최소 : ");
	private JLabel label = new JLabel("연봉별 정렬(내림)");
	private JCheckBox ck_sort = new JCheckBox();
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //모니터의 크기
	private String[][] datas = new String[0][5];
	private String []title = {"사번","직원명","연봉","입사년","관리 고객수"};
	private DefaultTableModel model;
	private JTable table;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties properties = new Properties();
	
	public DbTest14Table_test2() {
		setTitle("부서명별 자료 받기");
		setSize(400, 350);
		int x = (int) ((dim.getWidth() - this.getWidth()) / 2); //화면의 중앙
		int y = (int) ((dim.getHeight() - this.getHeight()) / 2); //화면의 중앙
		setLocation(x, y);		

		layinit();
		db_sawon();
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void layinit(){
		JPanel pnl = new JPanel();
		JLabel lbl = new JLabel("부서명 : ");
		cb_dept.addItem("전체");
		db_dept();
		pnl.add(lbl); pnl.add(cb_dept); pnl.add(label); pnl.add(ck_sort);
		
		model = new DefaultTableModel(datas, title);  //table structure 
		table = new JTable(model);
		JScrollPane scrpane = new JScrollPane(table);
		
		JPanel pnl1 = new JPanel();		
		JLabel lbl1 = new JLabel("연봉=> ");
		pnl1.add(lbl_cnt);
		pnl1.add(lbl1);
		pnl1.add(lbl_max); 
		pnl1.add(lbl_avg);
		pnl1.add(lbl_min);
		
		cb_dept.addActionListener(this);
		ck_sort.addActionListener(this);
		
		add("North", pnl);
		add("Center", scrpane);
		add("South", pnl1);			
	}
	
	private void accDB(){
		try {
			properties.load(new FileInputStream("/work/sou/java7/src/db/pack/test.properties"));
			Class.forName(properties.getProperty("driver"));
			conn = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),
					properties.getProperty("passwd"));
		} catch (Exception e) {
			System.out.println("addDB Error : " + e);
		}
	}
	
	private void db_dept(){
		try {
			accDB();
			String sql_dept = "select buser_name from buser";
			pstmt = conn.prepareStatement(sql_dept);
			rs = pstmt.executeQuery();
			while(rs.next()){
				cb_dept.addItem(rs.getString("buser_name"));
			}			
		} catch (Exception e) {
			System.out.println("db_dept err : " + e);
		}finally{
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				System.out.println("db_dept close err : " + e2) ;
			}
		}
	}
	
	private void db_sawon(){
		try {
			accDB();
			String sdept, sqlo;
			String sqls = "select sawon_no, sawon_name, sawon_pay, to_char(sawon_ibsail, 'yyyy') as year, " +
						  "(select count(*) from gogek where gogek_damsano = sawon_no) as gogek " + 
						  "from sawon left outer join buser on buser_no = buser_num ";
			
			if (ck_sort.isSelected()) {
				sqlo = "order by sawon_pay desc";
			}else{
				sqlo = "order by sawon_no";
			}
			
			sdept = (String)cb_dept.getSelectedItem();			
			if (!sdept.equals("전체")) {
				sqls += "where buser_name = ? " + sqlo;
				pstmt = conn.prepareStatement(sqls);
				pstmt.setString(1, sdept);				
			}else{
				pstmt = conn.prepareStatement(sqls + sqlo);
			}

			rs = pstmt.executeQuery();
			int sawon_cnt = 0, tot = 0, max = 0, min = 0;
			while(rs.next()){
				String no = rs.getString("sawon_no");
				String name = rs.getString("sawon_name");
				String pay = rs.getString("sawon_pay");
				String year = rs.getString("year");
				String gogek = rs.getString("gogek");
				String[] imsi = {no, name, pay, year, gogek};
				model.addRow(imsi);
				if (rs.isFirst()) {
					max = rs.getInt("sawon_pay");
					min = max;
				}else{
					if (max < rs.getInt("sawon_pay")) {
						max = rs.getInt("sawon_pay");
					}
					if (min > rs.getInt("sawon_pay")) {
						min = rs.getInt("sawon_pay");
					}					
				}
				tot += rs.getInt("sawon_pay");
				sawon_cnt++;
			}
			lbl_cnt.setText("직원수 : " + sawon_cnt);
			lbl_max.setText("최대 : " + max); 
			lbl_avg.setText("평균 : " + (int)(tot/sawon_cnt)); 
			lbl_min.setText("최소 : " + min);						
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "자료가 없습니다.");
		}finally{
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				System.out.println("db_sawon close err : " + e2) ;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		model.setNumRows(0);
		db_sawon();	
	}
	
	public static void main(String[] args) {
		new DbTest14Table_test2();
	}
}
