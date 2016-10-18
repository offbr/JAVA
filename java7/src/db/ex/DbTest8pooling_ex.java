package db.ex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import db.pack.DBConnectionMgr;

public class DbTest8pooling_ex extends JFrame implements ActionListener{
	private JTextField txt_name, txt_jumin1, txt_jumin2;
	private JTextArea txt_all;
	private JButton btn;
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private DBConnectionMgr mgr;
	
	
	public DbTest8pooling_ex() {
		setTitle("Pooling Test");
		
		try {
			mgr = DBConnectionMgr.getInstance(); //싱글톤 패턴
			//System.out.println("연결성공");
		} catch (Exception e) {
			System.out.println("연결실패: " + e);
			return;
		}
		
		laytInit();
		
		setBounds(200, 200, 500, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void laytInit(){
		JPanel panel1 = new JPanel();
		txt_name = new JTextField("김혜순", 4);
		txt_jumin1 = new JTextField("700101", 6);
		txt_jumin2 = new JTextField("1054225", 7);
		btn = new JButton("확인");
		panel1.add(new JLabel("고객명: "));
		panel1.add(txt_name);
		panel1.add(new JLabel("주민번호: "));
		panel1.add(txt_jumin1);
		panel1.add(new JLabel("-"));
		panel1.add(txt_jumin2);
		panel1.add(btn);
		add("North", panel1);
		
		JPanel panel2 = new JPanel();
		txt_all = new JTextArea("사번\t이름\t부서명\t전화\t직급");
		panel2.add(txt_all);
		add("Center",panel2);
		
		btn.addActionListener(this);
		btn.setMnemonic(KeyEvent.VK_ENTER);
	}
		
	private void accDb(){
		try {
			conn = mgr.getConnection(); // 연결 한다
		}catch (Exception e){
			System.out.println("accDb err: " +e);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {
			if (!txt_name.getText().equals("") && !txt_jumin1.getText().equals("") && !txt_jumin2.equals("")) {
				accDb();
				try {
					stmt = conn.createStatement();

					String sql = "select sawon_no, sawon_name, buser_name, sawon_jik, buser_tel from sawon"
							+ " left join buser on buser_no = buser_num"
							+ " left join gogek on sawon_no = gogek_damsano" + " where gogek_name = '"
							+ txt_name.getText() + "' and substr(gogek_jumin, 1, 6)= '" + txt_jumin1.getText() + "' and"
							+ "	substr(gogek_jumin, 8, 7) = '" + txt_jumin2.getText() + "'";

					rs = stmt.executeQuery(sql);
					txt_all.setText("사번\t이름\t부서명\t전화\t직급\n");
					while (rs.next()) {
						String str = rs.getString("sawon_no") + "\t" + rs.getString("sawon_name") + "\t"
								+ rs.getString("buser_name") + "\t" + rs.getString("sawon_jik") + "\t"
								+ rs.getString("buser_tel") + "\n";
						txt_all.append(str);
					}
				} catch (Exception e1) {
					System.out.println(" err: " + e1);
				} finally {
					mgr.freeConnection(conn,stmt, rs); // 연결 닫아준다
				}
			}else{
				JOptionPane.showMessageDialog(this, "값을 정확하게 입력하세요");
			}
		}
	}
	
	public static void main(String[] args) {
		new DbTest8pooling_ex();
	}
}
