package db.ex;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class DbTest15CRD_ex extends JFrame{
	private String [][] datas = new String[0][4];
	private String [] title = {"부서번호", "부서명", "부서전화", "부서위치"};
	private DefaultTableModel model;
	private JTable table;
	private JTextArea txt_all;
	
	private Connection conn;
	private PreparedStatement pstmt, pstmt1;
	private ResultSet rs, rs1;
	private Properties properties = new Properties();
	
	private String sql = "", sql1 = "";
	private int buser = 0;
	public DbTest15CRD_ex() {
		setTitle("부서별 직원정보");
		
		layInit();
		disp();
		
		setBounds(900, 300, 500, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void layInit(){
		JPanel panel1 = new JPanel();
		model = new DefaultTableModel(datas, title);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(400, 80));
		JScrollPane scrollPane = new JScrollPane(table);
		panel1.add(scrollPane);
		add("North", panel1);
		
		JPanel panel2 = new JPanel();
		txt_all = new JTextArea();
		JScrollPane scrollPane2 = new JScrollPane(txt_all);
		//panel2.add(scrollPane2);
		add("Center", scrollPane2);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table = (JTable) e.getSource(); //마우스 클릭 대상 객체 얻기
				model = (DefaultTableModel) table.getModel();
				
				//System.out.println("선택 값: " + model.getValueAt(table.getSelectedRow(), 0)); //열번호 고정
				buser = Integer.valueOf((String)model.getValueAt(table.getSelectedRow(), 0));
				dispsawon();
			}
		});
	}
	
	private void accDb(){
		try {
			properties.load(new FileInputStream("/work/sou/java7/src/db/pack/test.properties"));
			Class.forName(properties.getProperty("driver"));
			conn = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),
					properties.getProperty("passwd"));
		} catch (Exception e) {
			System.out.println("addDB Error : " + e);
		}
	}
	
	private void disp(){
		accDb();
		try {
			sql = "select * from buser";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String rowData[] ={
						rs.getString("buser_no"),
						rs.getString("buser_name"),
						rs.getString("buser_tel"),
						rs.getString("buser_loc")};
				model.addRow(rowData);
			}
		} catch (Exception e) {
			System.out.println("disp 에러: " + e);
		}
	}
	
	private void dispsawon(){
		accDb();
		//String sawon_no = "";
		txt_all.setText("");

		try {
			sql = "select sawon_no, sawon_name from sawon where buser_num = ? order by sawon_no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, buser);

			sql1 = "select nvl(gogek_name, '없음') as gogek_name, gogek_tel, RPAD(substr(gogek_jumin, 1,7), 14, '*')as gogek_jumin from gogek"
					+ " where gogek_damsano = ? order by gogek_damsano";
			pstmt1 = conn.prepareStatement(sql1);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				rs.getString("sawon_no");
				String str = rs.getString("sawon_name");
				txt_all.append("\n직원명 : " + str);
				txt_all.append("\n관리 고객\n" + "고객명\t고객전화\t고객주민번호\n");

				pstmt1.setString(1, rs.getString("sawon_no"));
				rs1 = pstmt1.executeQuery();
				while (rs1.next()) {
					String gogek_name = rs1.getString("gogek_name");
					String gogek_tel = rs1.getString("gogek_tel");
					String gogek_jumin = rs1.getString("gogek_jumin");
					if(!rs1.getString("gogek_name").equals(null)){
						txt_all.append(gogek_name + "\t" + gogek_tel + "\t" + gogek_jumin + "\n");
					}else if(rs1.getString("gogek_name").equals(null)){
						txt_all.append("없다 고객\n");
					}
				}
			}

		} catch (Exception e) {
			System.out.println("dispsawon 에러: " + e);
		}
	}
	
	public static void main(String[] args) {
		new DbTest15CRD_ex();
	}
}






















