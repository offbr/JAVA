package db.ex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DbTest6_ex extends JFrame implements ActionListener{
	private JTextField txt_No, txt_Name, txt_Jik, txt_Buser, txt_BuserTel;
	private JTextArea txt_All;
	private JButton btnF, btnP, btnN, btnL;
	
	private Connection conn;
	private Statement stmt, stmt1;
	private ResultSet rs;
	private ResultSet rs1;
	
	public DbTest6_ex() {
		setTitle("레코드 이동 연습");
		
		layInit();
		accDb();

		setBounds(200, 200, 500, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void layInit(){
		// 사원정보
		JPanel panel1 = new JPanel();
		txt_No = new JTextField("", 3);
		txt_Name = new JTextField("", 5);
		txt_Jik = new JTextField("", 3);
		panel1.add(new JLabel("사번: "));
		panel1.add(txt_No);
		panel1.add(new JLabel("이름: "));
		panel1.add(txt_Name);
		panel1.add(new JLabel("직급: "));
		panel1.add(txt_Jik);
		add("North", panel1);
		
		// 부서정보
		JPanel panel2 = new JPanel();
		txt_Buser = new JTextField("", 5);
		txt_BuserTel = new JTextField("", 10);
		panel2.add(new JLabel("부서명: "));
		panel2.add(txt_Buser);
		panel2.add(new JLabel("부서전화: "));
		panel2.add(txt_BuserTel);
		add("Center", panel2);
		
		// 버튼 
		JPanel panel3 = new JPanel();
		//panel3.setLayout(new BorderLayout());
		btnF = new JButton("처음");
		btnP = new JButton("이전");
		btnN = new JButton("다음");
		btnL = new JButton("마지막");
		panel3.add(btnF); panel3.add(btnP); panel3.add(btnN); panel3.add(btnL);
		panel2.add(panel3);
		btnF.addActionListener(this);
		btnP.addActionListener(this);
		btnN.addActionListener(this);
		btnL.addActionListener(this);
		
		//결과값 출력
		JPanel panel4 = new JPanel();
		txt_All = new JTextArea("");
		panel4.add(txt_All);
		panel3.add("Center",panel4);
		add("South", panel4);
	}
	
	private void accDb(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); //역방향 //수정도 가능하게 할거면 sensitive/update
			stmt1 = conn.createStatement();
			rs = stmt.executeQuery("select sawon_no, sawon_name, sawon_jik, buser_name, buser_tel"
					+ " from sawon"
					+ " left join buser on buser_no = buser_num"
					+ " order by sawon_no");
			rs.next();
						
			display();
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		}
	}
	
	private void display(){
		try {
			txt_No.setText(rs.getString("sawon_no"));
			txt_Name.setText(rs.getString("sawon_name")); //숫자를 줄수도 있지만 가독성이 떨어진다.
			txt_Jik.setText(rs.getString("sawon_jik"));
			txt_Buser.setText(rs.getString("buser_name"));
			txt_BuserTel.setText(rs.getString("buser_tel"));
			
			rs1 = stmt1.executeQuery("select gogek_no, gogek_name, decode(substr(gogek_jumin, 8, 1), 1,'남',2,'여') gogek_gen,"
					+ " to_char(sysdate, 'yyyy') - to_char(to_date(substr(gogek_jumin, 1, 6)), 'yyyy') as gogek_nai, gogek_tel from gogek"
					+ " where gogek_damsano='"+txt_No.getText()+"'");
			
			txt_All.setText("고객번호\t고객명\t성별\t나이\t전화\n");
			int count = 0;
			while (rs1.next()){
			String str = rs1.getString("gogek_no") + "\t" +
					rs1.getString("gogek_name") + "\t" +
					rs1.getString("gogek_gen") + "\t" +
					rs1.getString("gogek_nai") + "\t" +
					rs1.getString("gogek_tel") + "\n";
			count++;
			txt_All.append(str);
			}
			if(count > 0) txt_All.append("인원수: " + count);
			else txt_All.setText("고객이 없습니다");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "처음 또는 마지막 자료");
			System.out.println("에러" + e);
		}finally {
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("11234");
		try { //포인트 방향 이동 (스테이트먼트 생성할때 Resultset 설정값 지정)
			if(e.getSource() == btnF) rs.first();
			else if(e.getSource() == btnP) rs.previous();
			else if(e.getSource() == btnN) rs.next();
			else if(e.getSource() == btnL) rs.last();
			
			display();
		} catch (Exception e2) {
			System.out.println();
		}
	}
	public static void main(String[] args) {
		new DbTest6_ex();

	}

}
