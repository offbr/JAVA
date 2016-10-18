package db.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class BookCustomer extends JPanel implements ActionListener{
	JTextField txtCbun,txtCirum,txtCjunhwa,txtCjuso,txtCdaesu;
	JTextArea taCmemo;
	JButton btnInsert,btnOk,btnUpdate,btnDel,btnFind,btnOption,btnClose;
	JButton btnF,btnP,btnN,btnL;
	JLabel lblRec;
	
	int iTotal = 0;	// 자료의 갯수
	int iLast = 0; 	// 마지막 레코드 번호
	boolean isInsert = false;	// Insert 버튼 눌림 여부
	boolean isUpdate = false;	// Update 버튼 눌림 여부
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;
	
	
	public BookCustomer(){ // 생성자
		design();
		addListener();
		accDb();
		
		init();
		display();
	}
	
	public void design(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//고객정보 패널========================
		JPanel customerPn = new JPanel(new GridLayout(4,1));
		customerPn.setBorder(new TitledBorder(new TitledBorder("고객 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		JPanel cPn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		txtCbun = new JTextField("",5);
		txtCirum = new JTextField("",10);
		txtCjunhwa = new JTextField("",15);
		txtCjuso = new JTextField("",28);
		txtCdaesu = new JTextField("",5);
		taCmemo = new JTextArea(2,28);
		JScrollPane scroll = new JScrollPane(taCmemo);
		taCmemo.setBackground(Color.lightGray);
		
		txtCbun.setEditable(false);
		txtCirum.setEditable(false);
		txtCjunhwa.setEditable(false);
		txtCjuso.setEditable(false);
		txtCdaesu.setEditable(false);
		taCmemo.setEditable(false);
		
		btnInsert = new JButton("신규");
		btnOk = new JButton("확인");
		btnUpdate = new JButton("수정");
		btnDel = new JButton("삭제");
		btnFind = new JButton("검색");
		btnOption = new JButton("옵션");
		btnClose = new JButton("닫기");
		btnF = new JButton(" <<= ");
		btnP = new JButton("  <= ");
		btnN = new JButton(" =>  ");
		btnL = new JButton(" =>> ");
		lblRec = new JLabel(" 0 / 0 ",JLabel.CENTER);
		cPn1.add(new JLabel("번호 :"));
		cPn1.add(txtCbun);
		cPn1.add(new JLabel("       이름 :"));
		cPn1.add(txtCirum);
		
		cPn2.add(new JLabel("전화 :"));
		cPn2.add(txtCjunhwa);	
		cPn2.add(new JLabel("      대여횟수 :"));
		cPn2.add(txtCdaesu);
		
		cPn3.add(new JLabel("주소 :"));
		cPn3.add(txtCjuso);
		
		cPn4.add(new JLabel("메모 :"));
		cPn4.add(scroll);

		customerPn.add(cPn1);  customerPn.add(cPn2); 	customerPn.add(cPn3);	customerPn.add(cPn4);
		this.add(customerPn);
		
		btnOk.setEnabled(false);
		
		//레코드 이동 패널========================
		JPanel movePn = new JPanel();
		movePn.setBorder(new TitledBorder(new TitledBorder("레코드 이동"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		movePn.add(btnF);
		movePn.add(btnP);
		movePn.add(lblRec);
		movePn.add(btnN);
		movePn.add(btnL);
		
		this.add(movePn);
		
        //명령 버튼 패널========================
		JPanel bottomPn1 = new JPanel();
		bottomPn1.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		bottomPn1.add(btnInsert);
		bottomPn1.add(btnOk);
		JLabel lbl1 = new JLabel("    "); 
		bottomPn1.add(lbl1);
		bottomPn1.add(btnUpdate);
		bottomPn1.add(btnDel);
		
		JPanel bottomPn2 = new JPanel();
		bottomPn2.add(btnFind);
		bottomPn2.add(btnOption);
		JLabel lbl2 = new JLabel("                          "); 
		bottomPn2.add(lbl2);
		bottomPn2.add(btnClose);
		
		this.add(bottomPn1);
		this.add(bottomPn2);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	
	private void addListener(){
		btnF.addActionListener(this);
		btnP.addActionListener(this);
		btnN.addActionListener(this);
		btnL.addActionListener(this);
		
		btnInsert.addActionListener(this);
		btnOk.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDel.addActionListener(this);
		btnFind.addActionListener(this);
		btnOption.addActionListener(this);
		btnClose.addActionListener(this);
	}
	
	private void accDb(){ //이번 고객관리작업은 DB를 열고 작업해야한다. (레코드 이동)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		} catch (Exception e) {
			System.out.println("Db err: " + e);
		}
	}
	
	private void init(){
		try {
			sql = "select * from customer order by c_bun asc";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			rs.last();
			iTotal = rs.getRow(); //전체 자료 수 얻기
			iLast = rs.getInt("c_bun"); //마지막 고객번호 얻기
			//System.out.println(iTotal + " " + iLast);
			rs.first();
		} catch (Exception e) {
			System.out.println("init err: " + e);
		}
	}
	
	private void display(){
		try {
			txtCbun.setText(rs.getString("c_bun"));
			txtCirum.setText(rs.getString("c_irum"));
			txtCjunhwa.setText(rs.getString("c_junhwa"));
			txtCdaesu.setText(rs.getString("c_daesu"));
			txtCjuso.setText(rs.getString("c_juso"));
			taCmemo.setText(rs.getString("c_memo"));
			
			lblRec.setText(rs.getRow() + " / " + iTotal); // 레코드 1 / 10
		} catch (Exception e) {
			System.out.println("display err: " + e);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getSource().equals(btnF)){ //처음
				rs.first(); display();
			}else if(e.getSource().equals(btnP)){ // 이전
				if(rs.isFirst()) return; //처음이면 되돌아가기
				rs.previous(); display();
			}else if(e.getSource().equals(btnN)){ //다음
				if(rs.isLast()) return; //마지막이면 되돌아가기
				rs.next(); display();
			}else if(e.getSource().equals(btnL)){ //마지막
				rs.last(); display();
			}
		} catch (Exception e2) {
			System.out.println("레코드 이동 오류: " + e2);
		}
		
		if(e.getSource().equals(btnInsert)){ //신규
			if(isInsert == false){
				btnInsert.setText("취소");
				btnOk.setEnabled(true);
				isInsert = true;
				
				txtCirum.setEditable(true);
				txtCjunhwa.setEditable(true);
				txtCjuso.setEditable(true);
				txtCbun.setText(String.valueOf(iLast + 1)); //신규고객번호
				txtCirum.setText(null);
				txtCjunhwa.setText(null);
				txtCjuso.setText(null);
				txtCdaesu.setText(null);
				taCmemo.setText(null);
				txtCirum.requestFocus();
			}else{
				btnInsert.setText("신규");
				btnOk.setEnabled(false);
				isInsert = false;
				
				txtCirum.setEditable(false);
				txtCjunhwa.setEditable(false);
				txtCjuso.setEditable(false);
				display();
			}
		}else if(e.getSource().equals(btnOk)){ //확인
			try {
				//입력자료 검사 필요. 생략 //정말수정할까요? 묻는게 좋다.
				sql = "insert into customer values(?,?,?,?,0,'')";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, txtCbun.getText().trim());
				pstmt.setString(2, txtCirum.getText().trim());
				pstmt.setString(3, txtCjunhwa.getText().trim());
				pstmt.setString(4, txtCjuso.getText().trim());
				pstmt.executeUpdate(); //등록 수행 //re 확인필요
				
				init(); //전체 건수 출력
				rs.last(); //추가 후 마지막으로 레코드 이동
				display(); //보여주기.
				
				txtCirum.setEditable(false);
				txtCjunhwa.setEditable(false);
				txtCjuso.setEditable(false);
				btnInsert.setText("신규");
				btnOk.setEnabled(false);
			} catch (Exception e2) {
				System.out.println("신규 확인 에러 : " + e2); 
			}
		}else if(e.getSource().equals(btnUpdate)){ //수정
			if(isUpdate == false){
				btnUpdate.setText("완료");
				isUpdate = true;
				
				txtCirum.setEditable(true);
				txtCjunhwa.setEditable(true);
				txtCjuso.setEditable(true);
				
			}else{
				btnUpdate.setText("수정");
				isUpdate = false;
				
				//수정 처리 ---------------
				try {
					sql = "update customer set c_irum=?, c_junhwa=?, c_juso=? where c_bun=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, txtCirum.getText().trim());
					pstmt.setString(2, txtCjunhwa.getText().trim());
					pstmt.setString(3, txtCjuso.getText().trim());
					pstmt.setString(4, txtCbun.getText().trim());
					pstmt.executeUpdate(); //업데이트 수행후 결과값 리턴받아야한다 . 생략.
					
					int currentRow = rs.getRow(); //현재 레코드 포인터 기억 -------------------
					init();
					rs.absolute(currentRow); //해당 포인터로 이동 ------------------
					
				} catch (Exception e2) {
					System.out.println("수정오류 : " + e2);
				}
				
				txtCirum.setEditable(false);
				txtCjunhwa.setEditable(false);
				txtCjuso.setEditable(false);
			}			
		}else if(e.getSource().equals(btnDel)){ //삭제 
			int re = JOptionPane.showConfirmDialog(this, "정말 삭제할까요?", "삭제" , JOptionPane.YES_NO_OPTION);	
			if(re == JOptionPane.YES_OPTION){ //정말 삭제할까요? 묻는게 좋다.
				try {
					if(rs.getRow() == 0){ //레코드 수 확인
						JOptionPane.showMessageDialog(this, "삭제할 자료가 없습니다");
						return;
					}
					//현재 대여 중인 고객은 삭제 불가
					if(taCmemo.getText().equals("")){
						int currentRow = rs.getRow(); //현재 레코드 번호 
						sql = "delete from customer where c_bun = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, txtCbun.getText());
						pstmt.executeUpdate();
						init();
						if(currentRow == 1){
							//1이면 그냥 있는다
						}else{
							rs.absolute(currentRow - 1); //전 번호 레코드 로 돌아간다
						}
						display();
					}else{
						JOptionPane.showMessageDialog(this, "도서 반납 후 삭제 가능합니다");
					}
				} catch (Exception e2) {
					System.out.println("삭제 오류 : " + e2);
				}
			}
		}else if(e.getSource().equals(btnFind)){ //검색
			String fbun = JOptionPane.showInputDialog(this, "몇 번 고객을 검색할까요?");
			if(fbun == null) return;
			try {
				int currentRow = rs.getRow(); //검색 결과가 없을 시 돌아오기 위함
				rs.beforeFirst(); //현재 레코드에서 무조건 처음으로 간다.
				int couFind = 0;
				while (rs.next()){
					String str = rs.getString("c_bun");
					if(fbun.equals(str)){
						display();
						couFind++;
						break;
					}
				}
				if(couFind == 0){
					JOptionPane.showMessageDialog(this, "검색 결과가 없습니다");
					rs.absolute(currentRow);
				}
			} catch (Exception e2) {
				System.out.println("고객번호 검색 실패 : " + e2);
			}
		}else if(e.getSource().equals(btnOption)){ //옵션
			//상세 자료 처리 창 호출
			JDialog jd = new JDialog(new JFrame(), "상세검색", true);
			jd.add(new FindWin());
			jd.setBounds(215, 300, 400, 300);
			jd.setVisible(true);
		}else if(e.getSource().equals(btnClose)){
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("닫기 오류 : " + e2);
			} finally {
				BookMain.book_customer.setEnabled(true); //메인화면에 대여메뉴를 활성화
				BookMain.childWinCustomer.dispose(); //메인에 있는 대여창이 닫힘
			}
		}
	}
	
	//상세 검색용
	class FindWin extends JPanel implements ActionListener{
		private String[][] datas = new String[0][4];
		private String[] title = { "고객번호", "고객명", "전화", "대여수" };
		private DefaultTableModel model = new DefaultTableModel(datas, title); // 내부적인 데이터
		private JTable table = new JTable(model); // 내부적인 데이터 모델을 가지고 테이블뷰로 보여준다
		private JScrollPane scrollPane = new JScrollPane(table);
		private JTextField txt_F = new JTextField(5);
		private JButton btn = new JButton("확인");
	
		public FindWin() {
			findlayInit();
			findaccDb();
			finddisplay();
		}

		private void findlayInit() {
			setLayout(new BorderLayout());

			add("Center", scrollPane);
			
			JPanel panel = new JPanel();
			panel.add(new JLabel("이름 검색"));
			panel.add(txt_F);
			panel.add(btn);
			add("South", panel);
			btn.addActionListener(this);
			btn.setMnemonic(KeyEvent.VK_ENTER);
		}

		private void findaccDb() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (Exception e) {
				System.out.println("Db err: " + e);
			}
		}

		private void finddisplay() {
			try {
				if(txt_F.getText().equals("")){
					sql = "select c_bun, c_irum, c_junhwa, c_daesu from customer order by c_bun";
					pstmt = conn.prepareStatement(sql);		
				}else{
					sql = "select c_bun, c_irum, c_junhwa, c_daesu from customer where c_irum = ? order by c_bun";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, txt_F.getText());
				}
				rs = pstmt.executeQuery();
				int count = 0;
				while(rs.next()){
					String rowData[] = {
							rs.getString("c_bun"),
							rs.getString("c_irum"),
							rs.getString("c_junhwa"),	
							rs.getString("c_daesu")
							};
					model.addRow(rowData);
					count++;					
				}
				String[] countData = {null, "전체 건수 : " + count};
				model.addRow(countData);
				if(count == 0) JOptionPane.showMessageDialog(this, "검색하신 고객자료는 없습니다");
				
				txt_F.setText("");
				txt_F.requestFocus();
			} catch (Exception e) {
				System.out.println("옵션 검색 에러 : " + e);
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setNumRows(0);
			if(e.getSource() == btn){
				finddisplay();
			}
		}
	}
	public static void main(String[] args) {
		BookCustomer bookCustomer = new BookCustomer();
		JFrame frame = new JFrame("고객 창");
		frame.getContentPane().add(bookCustomer);
		frame.setBounds(200,200,430,450);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
