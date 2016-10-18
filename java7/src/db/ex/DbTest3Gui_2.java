package db.ex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DbTest3Gui_2 extends JFrame implements ActionListener {
	JRadioButton rdoAll = new JRadioButton("초기", true);
	JRadioButton rdoAsc = new JRadioButton("오름", false);
	JRadioButton rdoDesc = new JRadioButton("내림", false);
	ButtonGroup buttonGroup = new ButtonGroup();
	JButton btnOk = new JButton("확인");
	JLabel lbl = new JLabel("직급입력 : ");
	JTextField txt = new JTextField("", 5);
	JTextArea txtResult = new JTextArea();	

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public DbTest3Gui_2() {
		setTitle("직급별 검색");

		layInit();
		accDb();

		setBounds(200, 200, 400, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void layInit() {
		JPanel pn1 = new JPanel();
		btnOk.setMnemonic(KeyEvent.VK_ENTER);
		pn1.add(lbl);
		pn1.add(txt);
		pn1.add(btnOk);		
		buttonGroup.add(rdoAll);
		buttonGroup.add(rdoAsc);
		buttonGroup.add(rdoDesc);
		pn1.add(rdoAll);
		pn1.add(rdoAsc);
		pn1.add(rdoDesc);
		
		txtResult.setEditable(false);
		JScrollPane pane = new JScrollPane(txtResult);

		add("North", pn1);
		add("Center", pane);

		btnOk.addActionListener(this);		
		rdoAll.addActionListener(this);
		rdoAsc.addActionListener(this);
		rdoDesc.addActionListener(this);		
	}

	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnOk) {
			if(txt.getText().equals("사원") || txt.getText().equals("대리") || 
			   txt.getText().equals("과장") || txt.getText().equals("부장") || txt.getText().equals("전무") ||
			   txt.getText().equals("계약직") || txt.getText().equals("")){				
				try {
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
					stmt = conn.createStatement();
					String sql;							
					if(txt.getText().equals("")){
						sql = "select sawon_no 사번, sawon_name 직원명, nvl(sawon_jik, '계약직') 직급,"
								+ "case when sawon_gen = '남' then 'M' when sawon_gen = '여' then 'F' end 성별 from sawon";
					}else{
						sql = "select sawon_no 사번, sawon_name 직원명, nvl(sawon_jik, '계약직') 직급,"
								+ "case when sawon_gen = '남' then 'M' when sawon_gen = '여' then 'F' end 성별 from sawon"
								+ " where sawon_jik = '"	+ txt.getText() + "'" ;
					}
									
					if(rdoAll.isSelected() == true){
						
					}else if(rdoAsc.isSelected() == true){
						sql += " order by sawon_name";
					}else if(rdoDesc.isSelected() == true){
						sql += " order by sawon_name desc";
					}
					
					txtResult.setText(null);
					rs = stmt.executeQuery(sql);
					txtResult.setText("사번\t직원명\t직급\t성별\n");
					
					int count = 0;
					int mCount = 0;
					int fCount = 0;
					while(rs.next()){					
						String str = rs.getString("사번") + "\t" + 
								rs.getString("직원명") + "\t" + 
								rs.getString("직급") + "\t" + 
								rs.getString("성별") + "\n";
						if(rs.getString("성별").equals("M"))
							mCount++;
						else
							fCount++;
						count++;
						txtResult.append(str);
					}
					txtResult.append("인원수 : " + count + " 남 : " + mCount + " 여 : " + fCount);		
					txt.requestFocus();
				} catch (Exception e2) {
					System.out.println("DataBase actionPerformed error : " + e);
				} finally {
					try {
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (conn != null)
							conn.close();
					} catch (Exception e2) {

					}
				}				
			} else {
				JOptionPane.showMessageDialog(null, "직급을 정확히 입력하세요!"
						+ "\n모든직원을 검색하려면"
						+ "\n입력란에 '직원' 이라고 입력하세요",
						"경고", JOptionPane.WARNING_MESSAGE);
				txtResult.setText(null);
				txt.requestFocus();
			}		
		}}

	public static void main(String[] args) {
		new DbTest3Gui_2();
	}

}
