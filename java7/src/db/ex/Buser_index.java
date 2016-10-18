package db.ex;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class Buser_index extends JFrame{
	String buser_num = "", sawon_name = "";
	String[][] datas = new String[0][4];
	String[] title = { "부서번호", "부서명", "부서전화", "부서위치" };
	DefaultTableModel model = new DefaultTableModel(datas, title);
	JTable table = new JTable(model);
	
	JLabel lblname = new JLabel("직원명 : ");
	private JTextArea textArea2 = new JTextArea();
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs, rs2;

	public Buser_index() {
		layInit();

		setTitle("부서별 직원정보");
		setBounds(200, 200, 500, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		accDB();

		// 테이블 자료 클릭시 값 얻기
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table = (JTable) e.getComponent(); // 마우스 클릭 대상 객체 얻기
				model = (DefaultTableModel) table.getModel();
				
				//System.out.println("선택 값 : " + model.getValueAt(table.getSelectedRow(), 0));
				buser_num = (String)model.getValueAt(table.getSelectedRow(), 0);
				sawon();
			}
		});
	}

	private void layInit() {
		getContentPane().setLayout(new GridLayout(2, 1));

		JScrollPane jScrollPane = new JScrollPane(table);
		add("North", jScrollPane);
		JScrollPane jScrollPane2 = new JScrollPane(textArea2);
		add("South", jScrollPane2);
	}

	private void accDB() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("accDB err" + e);
			return;
		}
		disData();
	}

	private void disData() {
		model.setNumRows(0);
	
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			String sql = "select buser_no, buser_name, buser_tel, buser_loc from buser";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String imsi[] = { rs.getString("buser_no"), rs.getString("buser_name"), rs.getString("buser_tel"),
						rs.getString("buser_loc") };
				model.addRow(imsi);
			}
		} catch (Exception e) {
			System.out.println("disData err  :" + e);
		} finally {
			try {
				if (rs != null)	rs.close();
				if (rs2 != null) rs2.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {

			}
		}
	}
	
	private void sawon() {
		try {
			textArea2.setText(null);
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			String sql2 = "select sawon_name from sawon where buser_num = ?";
			String sql3 = "select nvl(gogek_name, '없음') as name, nvl(gogek_tel, '없음') as tel, nvl(gogek_jumin, '없음') as jumin from sawon left outer join gogek on sawon_no = GOGEK_DAMSANO where buser_num = ? and sawon_name = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, buser_num);
			rs = pstmt.executeQuery();
			while(rs.next()){
				sawon_name = rs.getString("sawon_name");
				String str =  "직원명 : " + sawon_name + "\n" + "관리고객은\n" + "고객명\t" + "고객전화\t" + "고객주민\n";
				textArea2.append(str);
	
				pstmt = conn.prepareStatement(sql3);
				pstmt.setString(1, buser_num);
				pstmt.setString(2, sawon_name);
				//System.out.println(sawon_name);
				rs2 = pstmt.executeQuery();
				while(rs2.next()){
					String str2 = rs2.getString("name") + "\t" +rs2.getString("tel") + "\t" +rs2.getString("jumin") + "\n\n";
					textArea2.append(str2);
				}
			}
		} catch (Exception e) {
			System.out.println("sawon err : " + e);
		}
	}

	public static void main(String[] args) {
		new Buser_index();
	}
}
