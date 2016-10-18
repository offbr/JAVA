package db.ex;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DbTest3Gui_Test extends JFrame implements ActionListener{
	private JTextField jtf = new JTextField();
	private JTextArea jta = new JTextArea();
	private JButton btn = new JButton("확인");
	private JRadioButton jrbst = new JRadioButton("초기", true);
	private JRadioButton jrbAst = new JRadioButton("오름");
	private JRadioButton jrbDst = new JRadioButton("내림");
	//private JRadioButton jrbAll = new JRadioButton("전체");
	private JRadioButton jrbReset = new JRadioButton("리셋");
	private ButtonGroup btnGroup = new ButtonGroup();
	
	private Connection conn; //DB 연결 객체
	private Statement stmt; //SQL문을 실행
	private ResultSet rs; //select의 결과를 처리
	
	public DbTest3Gui_Test() {
	setTitle("직급별 검색 프로그램");
	
		laytInit(); //레이아웃
		accdb(); //데이터처리
		
		setBounds(200, 200, 450, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void accdb() {
		try { //DB는 필요할때마다 연결하고 끊는다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패: " + e);
		}	
	}	

	public void laytInit() {
		JPanel pane1 = new JPanel();
		JLabel lbl = new JLabel("직급 입력 : ");
		jtf = new JTextField(5);
		btn = new JButton("확인");
		btnGroup.add(jrbst);
		btnGroup.add(jrbAst);
		btnGroup.add(jrbDst);
		//btnGroup.add(jrbAll);
		btnGroup.add(jrbReset);
		pane1.add(lbl);
		pane1.add(jtf);
		pane1.add(btn);
		pane1.add(jrbst);
		pane1.add(jrbAst);
		pane1.add(jrbDst);
		//pane1.add(jrbAll);
		pane1.add(jrbReset);
		
		// 패널에 담아서 왼쪽정렬 후 프레임에 북쪽.
		JPanel pane1_1 = new JPanel();
		pane1_1.setLayout(new BorderLayout());
		pane1_1.add("West",pane1);
		add("North", pane1_1);
		
		//버튼에 이벤트장착
		btn.addActionListener(this);
		jrbst.addActionListener(this);
		jrbAst.addActionListener(this);
		jrbDst.addActionListener(this);
		//jrbAll.addActionListener(this);
		jrbReset.addActionListener(this);
		//btn.setMnemonic(KeyEvent.VK_ENTER);
		jtf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) btn.doClick();
			}
		});
		// 패널에 결과값 출력용 에어리어
		JPanel pane2 = new JPanel();
		jta = new JTextArea("사번\t직원명\t직급\t성별");
		pane2.add(jta);
		add("Center", pane2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			stmt = conn.createStatement(); //스테이트먼트 생성
			
			//쿼리문			
			String sql = "select sawon_no, sawon_name, sawon_jik,"
					+ "decode(sawon_gen, '남', 'M', '여', 'F') sawon_gen from sawon";
 
			if (!jtf.getText().equals("")) sql += " where sawon_jik ='" + jtf.getText()+"'";
		
			if (e.getActionCommand().equals("오름")) {
				sql += " order by sawon_name asc";
			} else if (e.getActionCommand().equals("내림")) {
				sql += " order by sawon_name desc";
			}
		
			rs = stmt.executeQuery(sql);
			
			int count = 0; //인원 카운터
			int mcou = 0; //남자 카운터
			int fcou = 0; //여자 카운터
			
			jta.setText("사번\t직원명\t직급\t성별\n");
			while(rs.next()){
				String str = rs.getString("sawon_no")+ "\t" +
						rs.getString("sawon_name")+ "\t" +
						rs.getString("sawon_jik")+ "\t" +
						rs.getString("sawon_gen")+ "\n";
				jta.append(str);
				count++;
				if (rs.getString("sawon_gen").equals("M")){
					mcou++;
				}else if(rs.getString("sawon_gen").equals("F")){
					fcou++;
				}
			}
			jta.append("\n인원수 : " + count + "\t남: " + mcou + "\t여: " + fcou);
			
			if(e.getActionCommand().equals("리셋")){
				jta.setText("리셋!!!!!!!!!!!!!!!!!!!꺄오!!!!!!!!!!!!!!!!!");
				jtf.setText("");
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
		new DbTest3Gui_Test();
		
	}
}
	




