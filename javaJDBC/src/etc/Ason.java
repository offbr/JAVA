package etc;

import java.sql.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Ason extends JFrame {

	private JPanel contentPane;
	private JTextField numtxt;
	private JTextField nametxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup1 = new ButtonGroup();
	private JTable table;
	String etc = "출근";
	String etc1 = "정상";
	String num = "";
	String name = "";
	String hap = "";
	String no1 = "";
	String name1 = "";
	String loc1 = "";
	
	int aa = 0, bb = 0, cc=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ason frame = new Ason();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Ason() {
		ArrayList<AsonDto> list = new ArrayList<>();
		AsonDto dto = new AsonDto();
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String columnNames[] =
			{"번호", "이름", "근퇴"};

		Object rowData[][] = {};

		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
		JTable jTable = new JTable(model);
		jTable.setEnabled(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 424, 49);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbl1 = new JLabel("관리");
		lbl1.setFont(new Font("굴림", Font.BOLD, 20));
		lbl1.setBounds(184, 0, 42, 42);
		panel.add(lbl1);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(5, 64, 424, 119);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lbl2 = new JLabel("번호 : ");
		lbl2.setBounds(12, 23, 57, 15);
		panel1.add(lbl2);
		
		JLabel lbl3 = new JLabel("이름 : ");
		lbl3.setBounds(12, 73, 57, 15);
		panel1.add(lbl3);
		
		numtxt = new JTextField();
		numtxt.setBounds(53, 20, 359, 21);
		panel1.add(numtxt);
		numtxt.setColumns(10);
		
		nametxt = new JTextField();
		nametxt.setBounds(53, 70, 359, 21);
		panel1.add(nametxt);
		nametxt.setColumns(10);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(5, 191, 424, 31);
		contentPane.add(panel2);
		panel2.setLayout(null);
		
		JRadioButton btn1 = new JRadioButton("출근");
		btn1.setSelected(true);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etc = "출근";
			}
		});
		buttonGroup.add(btn1);
		btn1.setBounds(68, 5, 61, 23);
		panel2.add(btn1);
		
		JRadioButton btn2 = new JRadioButton("퇴근");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etc = "퇴근";
			}
		});
		buttonGroup.add(btn2);
		btn2.setBounds(149, 5, 61, 23);
		panel2.add(btn2);
		
		JRadioButton btn3 = new JRadioButton("정상");
		btn3.setSelected(true);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etc1 = "정상";
			}
		});
		btn3.setBounds(240, 5, 61, 23);
		panel2.add(btn3);
		buttonGroup1.add(btn3);
		
		JRadioButton btn4 = new JRadioButton("그외");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etc1 = "그 외";
			}
		});
		btn4.setBounds(313, 5, 69, 23);
		panel2.add(btn4);
		buttonGroup1.add(btn4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 232, 424, 40);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btn11 = new JButton("등록");
		btn11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AsonDto dto = new AsonDto();
				num = numtxt.getText();
				name = nametxt.getText();
				hap = etc + " " + etc1;
				dto.setNum(num);
				dto.setName(name);
				dto.setEtc(hap);
				list.add(dto);
				dbc();
			}
		});
		btn11.setBounds(12, 10, 81, 23);
		panel_1.add(btn11);
		
		JButton btn22 = new JButton("삭제");
		btn22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeRow(0);
				list.remove(0);
				
				aa = 0; // 삭제버튼 넘버 초기화
				bb--; //삭제 sql 넘버세팅
			}
		});
		btn22.setBounds(105, 10, 81, 23);
		panel_1.add(btn22);
		
		JButton btn33 = new JButton("수정");
		btn33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTable.setEnabled(true);
			}
		});
		btn33.setBounds(238, 10, 81, 23);
		panel_1.add(btn33);
		
		JButton btn44 = new JButton("확인");
		btn44.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				/*for (int i = 0+aa; i < list.size(); i++) {
					AsonDto dto = new AsonDto();
					dto = list.get(i);
					Object[] rowData = {dto.getNum(), dto.getName(), dto.getEtc()};
					model.addRow(rowData);
				}
				*/
				Object[] rowData = {no1, name1, loc1};
				model.addRow(rowData);
				aa++; //삭제버튼 넘버 세팅
				bb=aa; //삭제sql넘버 세팅
				cc=1;
				jTable.setEnabled(false);
			}
		});
		btn44.setBounds(331, 10, 81, 23);
		panel_1.add(btn44);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(5, 282, 424, 170);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 424, 160);
		panel_2.add(scrollPane);
		
	
		scrollPane.setViewportView(jTable);
	}
	
	public void dbc() {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		String url = null;
		String id = "scott";
		String pw = "tiger";
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //1단계 : JDBC 드라이버로드 
			System.out.println("driver load success"); //OracleDriver 클래스가 있는지 확인.
			try {
				url = "jdbc:oracle:thin:@localhost:1521:orcl"; //ip:127.0.0.1, port1521 접속한다.
				conn = DriverManager.getConnection(url, id, pw); //2단계 : DB Connection
				System.out.println("db connect success"); //DB의 커넥션을 가져온다(url, id, pw 입력)
				//쿼리를 처리하기 위하여 Statement 생성
				stmt = conn.createStatement();
				//다음 쿼리문을 실행하여 결과 집합 가져온다.
				String sql1 = "insert into asondb values('"+numtxt.getText()+"','"+nametxt.getText()+"','"+hap+"')";
				//String sql1 = "insert into asondb values('"+numtxt.getText()+"','"+nametxt.getText()+"','"+hap+"')";
				stmt.executeUpdate(sql1);  //입력한다
				if(cc >= 1){
				String sql2 = "delete from asondb where num = " + bb; //삭제
				rs = stmt.executeQuery(sql2);
				}
				cc=0;
				String sql3 = "select * from asondb";
				rs = stmt.executeQuery(sql3); //꺼내온다
				//레코드를 하나씩 이동.			
				while(rs.next()){ //1,2,3,4 애트리뷰트값을 화면에 출력
					this.no1 = rs.getString(1);
					this.name1 = rs.getString(2);
					this.loc1 = rs.getString(3);
					System.out.println(no1+ "," + name1 + "," + loc1);
				}
				//Statement 와 DB 커넥션을 닫는다 (쿼리를 처리 한 후 반드시 커넥션까지 같이 닫아준다.)
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				System.out.println(e); // DB관련 
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e); // 라이브러리관련.
		}
	}
}

class AsonDto{
	private String num, name, etc;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEtc() {
		return etc;
	}
	
	public void setEtc(String etc) {
		this.etc = etc;
	}
}










