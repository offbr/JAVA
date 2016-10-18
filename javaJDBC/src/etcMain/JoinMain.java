package etcMain;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class JoinMain extends JFrame implements ActionListener{
	private JTextField idtxt;
	private JTextField pwtxt;
	private JTextField nametxt;
	private JTextField birthtxt;
	private JTextField phonetxt;
	UserDTO dto = null;
	
	static Connection conn = null;
	static ResultSet rs = null;
	static Statement stmt = null;
	static PreparedStatement ppst = null;
	
	public JoinMain() {
		accdb();
		
		getContentPane().setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(110, 10, 174, 45);
		getContentPane().add(panel1);
		
		JLabel mainlbl = new JLabel("회원가입");
		mainlbl.setFont(new Font("굴림", Font.BOLD, 25));
		panel1.add(mainlbl);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(12, 65, 360, 263);
		getContentPane().add(panel2);
		panel2.setLayout(null);
		
		JLabel idlbl = new JLabel("ID : ");
		idlbl.setBounds(44, 23, 37, 15);
		panel2.add(idlbl);
		
		JLabel pwlbl = new JLabel("PW : ");
		pwlbl.setBounds(44, 73, 37, 15);
		panel2.add(pwlbl);
		
		JLabel namelbl = new JLabel("이름 : ");
		namelbl.setBounds(44, 123, 57, 15);
		panel2.add(namelbl);
		
		JLabel birthlbl = new JLabel("생년월일 : ");
		birthlbl.setBounds(44, 170, 74, 15);
		panel2.add(birthlbl);
		
		JLabel phonelbl = new JLabel("연락처 : ");
		phonelbl.setBounds(44, 217, 57, 15);
		panel2.add(phonelbl);
		
		idtxt = new JTextField();
		idtxt.setBounds(113, 20, 201, 20);
		panel2.add(idtxt);
		idtxt.setColumns(10);
		
		pwtxt = new JPasswordField();
		pwtxt.setColumns(10);
		pwtxt.setBounds(113, 70, 201, 20);
		panel2.add(pwtxt);
		
		nametxt = new JTextField();
		nametxt.setColumns(10);
		nametxt.setBounds(113, 120, 201, 20);
		panel2.add(nametxt);
		
		birthtxt = new JTextField();
		birthtxt.setColumns(10);
		birthtxt.setBounds(113, 167, 201, 20);
		panel2.add(birthtxt);
		
		phonetxt = new JTextField();
		phonetxt.setColumns(10);
		phonetxt.setBounds(113, 214, 201, 20);
		panel2.add(phonetxt);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 338, 360, 33);
		getContentPane().add(panel);
		
		JButton button = new JButton("완료");
		panel.add(button);
		button.addActionListener(this);
		
		JButton btnNewButton = new JButton("취소");
		panel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		setBounds(500, 200, 400, 420);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void accdb(){
		try { //DB는 필요할때마다 연결하고 끊는다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패: " + e);
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("완료");
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "manager", "123");
			stmt = conn.createStatement();
			
			if(e.getActionCommand().equals("완료")){ //회원가입
				
				try { //생일 예외처리
					int birth = Integer.parseInt(birthtxt.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(birthtxt, "생일은 숫자만 입력가능합니다 \t 예)880101");
					birthtxt.requestFocus();
					return;
				}

				try { //연락처 예외처리
					int phone = Integer.parseInt(phonetxt.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(phonetxt, "연락처는 숫자만 입력가능합니다 \t 예)01012341234");
					phonetxt.requestFocus();
					return;
				}
				
			String sql = "insert into test values('" + idtxt.getText() + "','" + pwtxt.getText() + "','"
					+ nametxt.getText() + "','" + birthtxt.getText() + "','" + phonetxt.getText() + "')";	
			stmt.executeUpdate(sql);
			}
			
			setVisible(false);
			new LoginMain();
			
		} catch (Exception e2) {
			System.out.println("actionPerformed err : " + e2);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				System.out.println("close err : " + e2);
			}
		}
	}
	
	public static void main(String[] args) {
		new JoinMain();

	}
}
