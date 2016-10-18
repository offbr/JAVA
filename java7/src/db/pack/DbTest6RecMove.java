package db.pack;

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
import javax.swing.JTextField;

public class DbTest6RecMove extends JFrame implements ActionListener{
	private JButton btnF, btnP, btnN, btnL;
	private JTextField txtNo, txtName;
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public DbTest6RecMove() {
		setTitle("레코드 이동");
		
		layInit();
		accDb();

		setBounds(200, 200, 300, 200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void layInit(){
		txtNo = new JTextField("", 5);
		txtName = new JTextField("", 10);
		JPanel panel = new JPanel();
		panel.add(new JLabel("직원 자료: "));
		panel.add(txtNo);
		panel.add(txtName);
		add("North", panel);
		
		btnF = new JButton("|<<");
		btnP = new JButton("<");
		btnN = new JButton(">");
		btnL = new JButton(">>|");
		JPanel panel2 = new JPanel();
		panel2.add(btnF); panel2.add(btnP); panel2.add(btnN); panel2.add(btnL);
		add("Center", panel2);
		
		btnF.addActionListener(this);
		btnP.addActionListener(this);
		btnN.addActionListener(this);
		btnL.addActionListener(this);
	}
	
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); //역방향 //수정도 가능하게 할거면 sensitive/update
			rs = stmt.executeQuery("select sawon_no, sawon_name from sawon order by sawon_no");
			
			rs.next();
			display();
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		}
	}
	
	private void display(){
		try {
			txtNo.setText(rs.getString(1));
			txtName.setText(rs.getString(2)); //숫자를 줄수도 있지만 가독성이 떨어진다.			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "처음 또는 마지막 자료");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try { //포인트 방향 이동 (스테이트먼트 생성할때 Resultset 설정값 지정)
			if(e.getSource() == btnF) rs.first();
			else if(e.getSource() == btnP) rs.previous();
			else if(e.getSource() == btnN) rs.next();
			else if(e.getSource() == btnL) rs.last();
			display();
		} catch (Exception e2) {
			
		}
	}
	
	public static void main(String[] args) {
		new DbTest6RecMove();
	}
}

























