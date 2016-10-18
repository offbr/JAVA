package db.pack;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DbTest15CRD extends JFrame implements ActionListener{
	private JButton btnInsert, btnDelete, btnExit;
	private String [][] datas = new String[0][4];
	private String [] title = {"코드", "품명", "수량", "단가"};
	private DefaultTableModel model = new DefaultTableModel(datas, title);
	private JTable table = new JTable(model);
	private JLabel lblcou = new JLabel("건수: ");
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DbTest15CRD() {
		btnInsert = new JButton("추가");
		btnDelete = new JButton("삭제");
		btnExit = new JButton("종료");
		btnInsert.addActionListener(this);
		btnDelete.addActionListener(this);
		btnExit.addActionListener(this);
		JPanel panel = new JPanel();
		panel.add(btnInsert);
		panel.add(btnDelete);
		panel.add(btnExit);
		add("North", panel);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(30); //열폭 조정
		JScrollPane scrollPane = new JScrollPane(table);
		add("Center", scrollPane);
		add("South", lblcou);
		
		setTitle("상품 처리");
		setBounds(700, 400, 300, 250);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int re = JOptionPane.showConfirmDialog(DbTest15CRD.this, "정말 종료할까요", "종료", JOptionPane.OK_CANCEL_OPTION);
				if(re == JOptionPane.OK_OPTION){
					try{
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
						if(conn != null) conn.close();
					}catch (Exception e2) {
						System.out.println("closing err: " + e2);
					}
					System.exit(0);
				}else{
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // 없어도 상관 없다.
				}
			}
		});
		
		accDb();
		
		//테이블 자료 클릭시 값 얻기
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table = (JTable) e.getSource(); //마우스 클릭 대상 객체 얻기
				model = (DefaultTableModel) table.getModel();
				
				//System.out.println("행/열번호: " + table.getSelectedRow() + "/" + table.getSelectedColumn());
				//System.out.println("열이름: " + table.getColumnName(table.getSelectedColumn()));
				//System.out.println("선택 값: " + model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
				System.out.println("선택 값: " + model.getValueAt(table.getSelectedRow(), 1)); //열번호 고정
			}
		});
	}
	
	private void accDb(){
		try { //DB는 필요할때마다 연결하고 끊는다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("accDb err: " + e);
			return;
		}
		dispData();
	}
	
	private void dispData(){
		model.setNumRows(0);
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			String sql = "select * from sangdata order by code desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int count = 0;
			while(rs.next()){
				String[] imsi = {
					rs.getString("code"),
					rs.getString("sang"),
					rs.getString("su"),
					rs.getString("dan"),
				};
				model.addRow(imsi);
				count++;
			}
			lblcou.setText("건수: " + count);
		} catch (Exception e) {
			System.out.println("dispData err: " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				System.out.println("dispDataClose err: " + e2);
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnInsert){
			InsFrom insFrom = new InsFrom(this);
			dispData(); //추가후 자료보기
		}else if(e.getSource() == btnDelete){
			
		}else if(e.getSource() == btnExit){
			int re = JOptionPane.showConfirmDialog(DbTest15CRD.this, "정말 종료할까요", "종료", JOptionPane.OK_CANCEL_OPTION);
			if(re == JOptionPane.OK_OPTION){
				try{
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				}catch (Exception e2) {
					System.out.println("closing err: " + e2);
				}
				System.exit(0);
			}else{
				setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // 없어도 상관 없다.
			}
		}
	}
	
	//추가용 내부 클래스
	class InsFrom extends JDialog implements ActionListener{
		private JTextField txtSang = new JTextField();
		private JTextField txtSu = new JTextField();
		private JTextField txtDan = new JTextField();
		private JButton btnOk = new JButton("등록");
		private JButton btnCancel = new JButton("지움");
		
		public InsFrom(Frame frame) {
			super(frame, "상품 추가");
			setModal(true);
			
			JPanel pn1 = new JPanel(new GridLayout(4, 2));
			pn1.add(new JLabel("품명"));
			pn1.add(txtSang);
			pn1.add(new JLabel("수량"));
			pn1.add(txtSu);
			pn1.add(new JLabel("단가"));
			pn1.add(txtDan);
			
			pn1.add(btnOk);
			pn1.add(btnCancel);
			
			btnOk.addActionListener(this);
			btnCancel.addActionListener(this);
			
			add("North", new JLabel("        --- 자료 입력 ---"));
			add("Center", pn1);
			
			setBounds(770, 450, 150, 150);
			setVisible(true);
			
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnOk){ //상품 추가
				// 입력자료 검사 : 자료 유무 판단, 숫자 입력 여부
				//생략
				
				//등록 가능한 상태
				try {
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
					//새상품 코드 구하기
					int new_code = 0;
					String sql = "select max(code) from sangdata";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(rs.next()){
						new_code = rs.getInt(1);
					}
					System.out.println(new_code);
					sql = "insert into sangdata values(?,?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, new_code + 1);
					pstmt.setString(2, txtSang.getText().trim()); // trim 앞뒤 공백을 잘린다
					pstmt.setString(3, txtSu.getText());
					pstmt.setString(4, txtDan.getText());
					if(pstmt.executeUpdate() > 0){
						JOptionPane.showMessageDialog(this, "등록 성공");
						dispose();
					}else{
						JOptionPane.showMessageDialog(this, "등록 실패");
					}
				} catch (Exception e2) {
					System.out.println("ins fail: " + e2);
				} finally {
					/*try {
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
						if(conn != null) conn.close();
					} catch (Exception e2) {
						System.out.println("close err : " + e2);
					}*/
				}				
			}else{ //입력자료 초기화
				txtSang.setText(null);
				txtSu.setText(null);
				txtDan.setText(null);
				txtSang.requestFocus();
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		new DbTest15CRD();

	}
}




































