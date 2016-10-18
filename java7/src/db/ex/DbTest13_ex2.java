package db.ex;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DbTest13_ex2 extends JFrame implements ActionListener{
	private JTextField txt_code, txt_sang, txt_su, txt_dan;
	private JButton btn, btn_delete;

	private String junggu = "^[0-9]*$";
	
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane js_pane;
	
	String columnNames[] =
		{"코드", "상품", "수량", "단가"};

	Object rowData[][] = {};
	
	int count = 0;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DbTest13_ex2() {
		setTitle("상품 추가");
		
		laytInit();
		accDb();
		disp();
		
		setBounds(200, 200, 500, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void laytInit(){
		JPanel panel1 = new JPanel();
		txt_code = new JTextField(2);
		txt_sang = new JTextField(15);
		panel1.add(new JLabel("코드: "));
		panel1.add(txt_code);
		panel1.add(new JLabel("상품명: "));
		panel1.add(txt_sang);
		add("North", panel1);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		
		JPanel panel2_1 = new JPanel();
		txt_su = new JTextField(2);
		txt_dan = new JTextField(5);
		btn = new JButton("추가");
		btn_delete = new JButton("삭제");
		panel2_1.add(new JLabel("수량: "));
		panel2_1.add(txt_su);
		panel2_1.add(new JLabel("단가: "));
		panel2_1.add(txt_dan);
		panel2_1.add(btn);
		panel2_1.add(btn_delete);
		panel2.add("North", panel2_1);

		JPanel panel2_2 = new JPanel();
		model = new DefaultTableModel(rowData, columnNames);
		table = new JTable(model);
		
		js_pane = new JScrollPane(table);
		panel2_2.add(js_pane);
		panel2.add("Center", panel2_2);
		
		add("Center", panel2);
		
		
		table.setEnabled(false);
		btn.addActionListener(this);
		btn_delete.addActionListener(this);
		btn.setMnemonic(KeyEvent.VK_ENTER);
	}
	
	private void accDb(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.6:1521:orcl", "scott", "tiger");
		} catch (Exception e) {
			System.out.println("Db err: " + e);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {
			accDb();
			if (!(txt_code.getText().matches(junggu))||
					!(txt_su.getText().matches(junggu)) ||
					!(txt_dan.getText().matches(junggu))) {
					JOptionPane.showMessageDialog(this, "숫자만 가능합니다");
			}
			if(txt_code.getText().equals("") || txt_su.getText().equals("") || txt_dan.getText().equals("")){
				JOptionPane.showMessageDialog(this, "값을 입력하세요");
			} else {
				try {
					String codesql = "select code from sangdata";
					pstmt = conn.prepareStatement(codesql);
					rs = pstmt.executeQuery();
					int cou = 0;
					while (rs.next()) {
						if (rs.getString("code").equals(txt_code.getText())) {
							JOptionPane.showMessageDialog(this, "코드가 중복됩니다. \n다시입력하세요");
							txt_code.requestFocus();
							txt_sang.setText("");
							txt_su.setText("");
							txt_dan.setText("");
							cou++;
						}	
					}
					System.out.println(cou);
					if(cou == 0) insertDb();
					
				} catch (Exception e2) {
					System.out.println("찾기 에러: " + e2);
					txt_code.setText("");
				}
			}
		}
			
		if(e.getSource() == btn_delete){
			accDb();
			try {
				String deletesql = "delete from sangdata where code = ?";
				pstmt = conn.prepareStatement(deletesql);
				pstmt.setString(1, txt_code.getText());
				int re = pstmt.executeUpdate();
				if (re == 1) {
					JOptionPane.showMessageDialog(this, "삭제 성공");
				} else {
					JOptionPane.showMessageDialog(this, "삭제 실패");
				}
				txt_code.setText("");
				disp();
			} catch (Exception e2) {
				System.out.println("삭제 에러: " + e2);
			}finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					System.out.println("close err : " + e2);
				}
			}
		}
	}
	
	private void insertDb(){
		try {
			String isql = "insert into sangdata values(?,?,?,?)";
			pstmt = conn.prepareStatement(isql);
			pstmt.setInt(1, Integer.valueOf(txt_code.getText()));
			pstmt.setString(2, txt_sang.getText());
			pstmt.setInt(3, Integer.valueOf(txt_su.getText()));
			pstmt.setInt(4, Integer.valueOf(txt_dan.getText()));
			int re = pstmt.executeUpdate();
			if (re == 1) {
				JOptionPane.showMessageDialog(this, "추가 성공");
			} else {
				JOptionPane.showMessageDialog(this, "추가 실패");
			}
			disp();
		} catch (Exception e2) {
			System.out.println("추가 에러: " + e2);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				txt_code.setText("");
				txt_sang.setText(null);
				txt_su.setText("");
				txt_dan.setText("");
			} catch (Exception e2) {
				System.out.println("close err : " + e2);
			}
		}
	}

	private void disp(){
		try {
			String sql = "select * from sangdata order by code";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//txt_all.setText("코드\t상품명\t수량\t단가\n");
			model.setNumRows(0);
			while(rs.next()){
				/*String str = rs.getString("code") + "\t" +
						rs.getString("sang") + "\t" +
						rs.getString("su") + "\t" +
						rs.getString("dan") + "\n";*/
				//if(rs.getInt("code") >= count){
					Object[] rowData = {rs.getString("code"),
							rs.getString("sang"),
							rs.getString("su"),
							rs.getString("dan")}; 
					model.addRow(rowData);		
			//	}
			}
			
		} catch (Exception e2) {
			System.out.println("disp 에러: " + e2);	
		}
	}

	public static void main(String[] args) {
		new DbTest13_ex2();
	}
}

















