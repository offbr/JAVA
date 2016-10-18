package db.ex;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class DbTest3Gui_ex extends JFrame implements ActionListener {
	private	JRadioButton jraA = new JRadioButton("전체", true);
	private	JRadioButton jra_10 = new JRadioButton("총무부");
	private	JRadioButton jra_20 = new JRadioButton("영업부");
	private	JRadioButton jra_30 = new JRadioButton("전산부");
	private	JRadioButton jra_40 = new JRadioButton("관리부");
	private	JRadioButton jra_50 = new JRadioButton("기타");
	private	ButtonGroup btnGroup = new ButtonGroup();
	private JTextArea jta = null;
	private	JLabel jtfName = null;
	private JLabel jtfTel = null;
	
	private Connection conn; //DB 연결 객체
	private Statement stmt; //SQL문을 실행
	private ResultSet rs; //select의 결과를 처리
	
	public DbTest3Gui_ex() {
		setTitle("관리 프로그램");
		
		laytInit(); //레이아웃
		accdb(); //데이터처리
		
		setBounds(200, 200, 500, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void accdb(){ //드라이버 로딩만 한다
		try { //DB는 필요할때마다 연결하고 끊는다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패: " + e);
		}	
	}
	
	private void laytInit(){
		//버튼 출력
		JPanel pane1 = new JPanel(); 
		btnGroup.add(jraA);
		btnGroup.add(jra_10);
		btnGroup.add(jra_20);
		btnGroup.add(jra_30);
		btnGroup.add(jra_40);
		btnGroup.add(jra_50);
		pane1.add(jraA, true);
		pane1.add(jra_10);
		pane1.add(jra_20);
		pane1.add(jra_30);
		pane1.add(jra_40);
		pane1.add(jra_50);
		add("North", pane1);
		
		// 버튼 액션 장착
		jraA.addActionListener(this);
		jra_10.addActionListener(this);
		jra_20.addActionListener(this);
		jra_30.addActionListener(this);
		jra_40.addActionListener(this);
		jra_50.addActionListener(this);
		
		//부서 텍스트 출력
		JPanel pane2 = new JPanel();
		JLabel lblName = new JLabel("부서명 : ");
		JLabel lblTel = new JLabel("부서전화 : ");
		jtfName = new JLabel();
		jtfTel = new JLabel();
		pane2.add(lblName);
		pane2.add(jtfName);
		pane2.add(lblTel);
		pane2.add(jtfTel);
		
		JPanel pane2_2 = new JPanel();
		pane2_2.setLayout(new BorderLayout());
		pane2_2.add("East",pane2);
		
		//사원 텍스트 출력
		JPanel pane3 = new JPanel();
		jta = new JTextArea("사번\t직원명\t직급\t연봉\n");
		pane3.add(jta);
		jta.setEditable(false);
		//부서 텍스트 사원 텍스트 레이아웃
		JPanel pane4 = new JPanel();
		pane4.setLayout(new BorderLayout());
		pane4.add("North", pane2_2);
		pane4.add("Center", pane3);
		add("Center",pane4);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		jta.setText(null);
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			stmt = conn.createStatement();
			//쿼리문 
			String sql = "select sawon_no, sawon_name, sawon_jik, "
							+ "	case"+
							" when sawon_pay >= 9000 then '9천대'"+
							" when sawon_pay >= 8000 then '8천대'"+
							" when sawon_pay >= 7000 then '7천대'"+
							" when sawon_pay >= 6000 then '6천대'"+
							" when sawon_pay >= 5000 then '5천대'"+
							" when sawon_pay >= 4000 then '4천대'"+
							" when sawon_pay >= 3000 then '3천대'"+
							" when sawon_pay >= 2000 then '2천대'"+
							" else '2천대 이하' end as sawon_pay,"
							+ "buser_name, buser_tel from sawon left outer join buser on buser_num=buser_no";
			
			if(e.getActionCommand().equals("총무부")){
				sql += " where buser_num = 10";
			}else if(e.getActionCommand().equals("영업부")){
				sql += " where buser_num = 20";
			}else if(e.getActionCommand().equals("전산부")){
				sql += " where buser_num = 30";
			}else if(e.getActionCommand().equals("관리부")){
				sql += " where buser_num = 40";
			}else if(e.getActionCommand().equals("기타")){
				sql += " where buser_num is null";
			}
			sql+= " order by sawon_no";
					
			rs = stmt.executeQuery(sql);
			//DB에서 꺼내오기
			jta.setText("사번\t직원명\t직급\t연봉\n");
			while(rs.next()){
				//부서명 부서전화 출력
				String buserName = rs.getString("buser_name"); 
				String buserTel = rs.getString("buser_tel");
				//전체사원 출력	
				String str = rs.getString("sawon_no") + "\t"+ 
						rs.getString("sawon_name") + "\t"+
						rs.getString("sawon_jik") + "\t"+
						rs.getString("sawon_pay") + "\n";
				jta.append(str);
				
				jtfName.setText(buserName);
				jtfTel.setText(buserTel);
			}
			//전체버튼 부서명 부서전화 초기화
			if(e.getSource() == jraA){
				jtfName.setText(null);
				jtfTel.setText(null);
			}
		} catch (Exception e2) {
			System.out.println("actionPerformed err : " + e2);
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("close err : " + e2);
			}
		}
	}
	
	public static void main(String[] args) {
		new DbTest3Gui_ex();
	}
}


