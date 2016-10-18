package db.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import db.project.BookCustomer.FindWin;

import java.io.*;
import java.sql.*;
import java.util.Calendar;

public class BookBook extends JPanel implements ActionListener{
	JTextField txtBbun,txtBjemok,txtBjang,txtBkuil,txtBdaesu,txtBdaeyn,txtBdaebun,txtBdaeil,txtBbanil;
	JTextArea taBmemo;
	JButton btnInsert,btnUpdate,btnDel,btnFind,btnOption,btnClose;
	JButton btnF,btnP,btnN,btnL,btnin,btnsu;
	JLabel lblRec,lblPic;


	String sql, imgPath; //--- 그림 파일 경로 변수
	int iTotal = 0;	// 자료의 갯수
	int iLast = 0; 	// 마지막 레코드 번호
	JPanel picPn;
	File file;  //--- 이미지 로딩하기 위한 변수
	boolean isInsert = false;	// Insert 버튼 눌림 여부
	boolean isUpdate = false;	// Update 버튼 눌림 여부
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public BookBook(){
		design();
		addListener();
		accDb();
		
		init();
		display();
	}
	
	public void design(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//도서정보 패널========================
		JPanel bookPn = new JPanel(new GridLayout(6,1));
		bookPn.setBorder(new TitledBorder(new TitledBorder("도서 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		JPanel bPn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		txtBbun = new JTextField("",5);
		txtBjemok = new JTextField("",30);
		txtBjang = new JTextField("",10);
		txtBkuil = new JTextField("",10);
		txtBdaesu = new JTextField("",5);
		txtBdaeyn = new JTextField("",5);
		txtBdaebun = new JTextField("",5);
		txtBdaeil = new JTextField("",10);
		txtBbanil = new JTextField("",10);
		taBmemo = new JTextArea(2,30);
		JScrollPane scroll = new JScrollPane(taBmemo);
		taBmemo.setBackground(Color.lightGray);
		
		txtBbun.setEditable(false);
		txtBjemok.setEditable(false);
		txtBjang.setEditable(false);
		txtBkuil.setEditable(false);
		txtBdaesu.setEditable(false);
		txtBdaeyn.setEditable(false);
		txtBdaebun.setEditable(false);
		txtBdaeil.setEditable(false);
		txtBbanil.setEditable(false);
		taBmemo.setEditable(false);
				
		btnInsert = new JButton("신규");
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
		bPn1.add(new JLabel("번호 :"));
		bPn1.add(txtBbun);
		bPn1.add(new JLabel("                              대여표시 :"));
		bPn1.add(txtBdaeyn);
		
		bPn2.add(new JLabel("제목 :"));
		bPn2.add(txtBjemok);	

		bPn3.add(new JLabel("장르 :"));
		bPn3.add(txtBjang);
		bPn3.add(new JLabel("                구입일 :"));
		bPn3.add(txtBkuil);
		
		bPn4.add(new JLabel("대여횟수 :"));
		bPn4.add(txtBdaesu);
		bPn4.add(new JLabel("                 대여자번호 :"));
		bPn4.add(txtBdaebun);
		
		bPn5.add(new JLabel("대여일 :"));
		bPn5.add(txtBdaeil);
		bPn5.add(new JLabel("            반납일 :"));
		bPn5.add(txtBbanil);

		bPn6.add(new JLabel("메모 :"));
		bPn6.add(scroll);
		
		bookPn.add(bPn1);  bookPn.add(bPn2); 	bookPn.add(bPn3);
		bookPn.add(bPn4);  bookPn.add(bPn5); 	bookPn.add(bPn6);
		this.add(bookPn);
		
		//이미지 패널----------------------------------------------------
		picPn = new JPanel(new BorderLayout());
		lblPic = new JLabel();
		picPn.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 50));
		//lblPic.setPreferredSize(new Dimension(1000, 300));
		picPn.add(lblPic);

		this.add(picPn);

		//레코드 이동 패널------------------------------------------------
		JPanel movePn = new JPanel();
		movePn.setBorder(new TitledBorder(new TitledBorder("레코드 이동"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		movePn.add(btnF);
		movePn.add(btnP);
		movePn.add(lblRec);
		movePn.add(btnN);
		movePn.add(btnL);
		
		this.add(movePn);
		
        //명령 버튼 패널--------------------------------------------------
		JPanel bottomPn1 = new JPanel();
		bottomPn1.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		bottomPn1.add(btnInsert);
		bottomPn1.add(btnUpdate);
		bottomPn1.add(btnDel);
		bottomPn1.add(btnFind);
		bottomPn1.add(btnOption);
		bottomPn1.add(btnClose);
		
		this.add(bottomPn1);
		
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		btnsu=new JButton("그림 수정");

	}
	
	private void addListener(){
		btnF.addActionListener(this);
		btnP.addActionListener(this);
		btnN.addActionListener(this);
		btnL.addActionListener(this);
		
		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDel.addActionListener(this);
		btnFind.addActionListener(this);
		btnOption.addActionListener(this);
		btnClose.addActionListener(this);
	}
	
	private void accDb(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		} catch (Exception e) {
			System.out.println("Db err: " + e);
		}
	}
	
	private void init(){
		try {
			sql = "select * from book order by b_bun asc";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			rs.last();
			iTotal = rs.getRow(); //전체 자료 수 얻기
			iLast = rs.getInt("b_bun"); //마지막 도서번호 얻기
			//System.out.println(iTotal + " " + iLast);
			rs.first();
		} catch (Exception e) {
			System.out.println("init err: " + e);
		}
	}
	
	private void display(){
		try {
			txtBbun.setText(rs.getString("b_bun"));
			//txtBdaeyn.setText(rs.getString("b_daeyn"));
			txtBjemok.setText(rs.getString("b_jemok"));
			txtBjang.setText(rs.getString("b_jang"));
			//txtBkuil.setText(rs.getString("b_kuipil"));
			txtBdaesu.setText(rs.getString("b_daesu"));
			txtBdaebun.setText(rs.getString("b_daebun"));
			
			if(rs.getString("b_kuipil") == null){
				txtBkuil.setText("");
			}else{
				txtBkuil.setText(rs.getString("b_kuipil").substring(0, 10));
			}
			
			if(rs.getString("b_daeil") == null){
				txtBdaeil.setText("");
			}else{
				txtBdaeil.setText(rs.getString("b_daeil").substring(0, 10));
			}
			
			if(rs.getString("b_banil") == null){
				txtBbanil.setText("");
			}else{
				txtBbanil.setText(rs.getString("b_banil").substring(0, 10));
			}
			
			if(rs.getString("b_daeyn").equals("y")){
				txtBdaeyn.setText("대여중");
				txtBdaeyn.setForeground(Color.red);
			}else{
				txtBdaeyn.setText("비치중");
				txtBdaeyn.setForeground(Color.blue);
			}
			
			taBmemo.setText(rs.getString("b_memo"));
			
			lblRec.setText(rs.getRow() + " / " + iTotal); // 레코드 1 / 9
			
			//이미지 출력 
			imgPath = "C:/work/sou/java7/src/image/" + rs.getString("b_image");
			//System.out.println(imgPath);
			displayImage();
		} catch (Exception e) {
			System.out.println("display err: " + e);
		}	
	}
	
	private void displayImage(){ //이미지 표시
		if(imgPath != null){
			ImageIcon icon = new ImageIcon(imgPath);
			lblPic.setIcon(icon);
			lblPic.setToolTipText("경로는 " + imgPath.toLowerCase()); //툴팁 표시
			if(icon != null){
				lblPic.setText(null);
			}else{
				lblPic.setText("그림없음");
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getSource().equals(btnF)){ //처음
				rs.first();
				display();
			}else if(e.getSource().equals(btnP)){ //이전
				if(rs.isFirst()) return;
				rs.previous();
				display();
			}else if(e.getSource().equals(btnN)){ //다음
				if(rs.isLast()) return;
				rs.next();
				display();
			}else if(e.getSource().equals(btnL)){ //마지막
				rs.last();
				display();
			}			
		} catch (Exception e2) {
			System.out.println("레코드 이동 에러: " + e2);
		}
		
		if(e.getSource().equals(btnInsert)){ //신규
			if(isInsert == false){
				btnInsert.setText("확인");
				isInsert = true;
				
				txtBbun.setText(iLast + 1 + ""); //문자가 된다
				txtBjemok.setEditable(true);
				txtBjang.setEditable(true);
				txtBkuil.setEditable(true);
				taBmemo.setEditable(true);
				taBmemo.setBackground(Color.WHITE);
				
				txtBjemok.setText(null);
				txtBjang.setText(null);
				taBmemo.setText(null);
				imgPath = null; //도서 이미지 경로 초기화
				
				Calendar cal = Calendar.getInstance();
				String imsi = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + cal.get(cal.DATE);
				txtBkuil.setText(imsi);
				txtBdaeyn.setText("n");
				txtBdaesu.setText("0");
				txtBdaebun.setText(null);
				txtBdaeil.setText(null);
				txtBbanil.setText(null);
				
				//이미지 삽입 버튼으로 화면 변경
				picPn.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
				btnin = new JButton("그림 선택");
				btnin.addActionListener(this);
				lblPic.setVisible(false);
				picPn.add("Center", btnin);
				
				txtBjemok.requestFocus();
			}else{
				btnInsert.setText("신규");
				isInsert = false;
				
				txtBjemok.setEditable(false);
				txtBjang.setEditable(false);
				txtBkuil.setEditable(false);
				taBmemo.setEditable(false);
				taBmemo.setBackground(Color.LIGHT_GRAY);
				
				//신규 도서 등록 작업 ---------------------------------------------
				try {
					sql = "insert into book values(?,?,?,?,0,'n',0,null,null,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, txtBbun.getText());
					pstmt.setString(2, txtBjemok.getText());
					pstmt.setString(3, txtBjang.getText());
					pstmt.setString(4, txtBkuil.getText());
					pstmt.setString(5, taBmemo.getText());
					pstmt.setString(6, file.getName()); //파일 이름만 얻기
					pstmt.executeUpdate(); // 결과 re로 확인 생략.
					
					init();
					rs.last();
					display();
				} catch (Exception e2) {
					System.out.println("신규 도서 추가 오류: " + e2);
				}
			}
		}else if(e.getSource().equals(btnin)){ //이미지 삽입
				JFileChooser chooser = new JFileChooser("C:/work/sou/java7/src/image/");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); //파일만 본다
				int result = chooser.showOpenDialog(this);
				
				if(result == JFileChooser.CANCEL_OPTION){
					file = null;
				}else{
					file = chooser.getSelectedFile();
					imgPath = file.getPath().replace("\\", "/");
					//System.out.println(imgPath);
					displayImage();
				
					lblPic.setVisible(true);
					btnin.setVisible(false);
				}
		}else if(e.getSource().equals(btnUpdate)){ //수정
			if(isUpdate == false){
				btnUpdate.setText("완료");
				isUpdate = true;
				
				txtBjemok.setEditable(true);
				txtBjang.setEditable(true);
				txtBkuil.setEditable(true);
			}else{
				int re = JOptionPane.showConfirmDialog(this, "정말 수정할까요?", "수정" , JOptionPane.YES_NO_OPTION);	//정말 삭제할까요? 묻는게 좋다.
				if(re == JOptionPane.YES_OPTION){
				btnUpdate.setText("수정");
				isUpdate = false;
				//book 수정 처리
					try {
						sql = "update book set b_jemok=?, b_jang=?, b_kuipil=? where b_bun=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, txtBjemok.getText().trim());
						pstmt.setString(2, txtBjang.getText().trim());
						pstmt.setString(3, txtBkuil.getText().trim());
						pstmt.setString(4, txtBbun.getText().trim());
						pstmt.executeUpdate(); // 업데이트 수행후 결과값 리턴받아야한다 . 생략.

						int currentRow = rs.getRow(); // 현재 레코드 포인터 기억// -------------------------------------------------
						init();
						rs.absolute(currentRow); // 해당 포인터로 이동// ----------------------------------------------------------
						display();
					} catch (Exception e2) {
						System.out.println("수정오류 : " + e2);
					}
					txtBjemok.setEditable(false);
					txtBjang.setEditable(false);
					txtBkuil.setEditable(false);
				}
			}
		}else if(e.getSource().equals(btnDel)){ //삭제
			int re = JOptionPane.showConfirmDialog(this, "정말 삭제할까요?", "삭제" , JOptionPane.YES_NO_OPTION);	//정말 삭제할까요? 묻는게 좋다.
			if(re == JOptionPane.YES_OPTION){
				try {
					if(rs.getRow() == 0){ //레코드 수 확인
						JOptionPane.showMessageDialog(this, "삭제할 자료가 없습니다");
						return;
					}
					//현재 대여 중인 고객은 삭제 불가
					if(txtBdaeyn.getText().equals("비치중")){
						System.out.println(txtBdaeyn.getText());
						int currentRow = rs.getRow(); //현재 레코드 번호 
						sql = "delete from book where b_bun = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, txtBbun.getText());
						pstmt.executeUpdate();
						init();
						if(currentRow == 1){
							//1이면 그냥 있는다
						}else{
							rs.absolute(currentRow - 1); //전 번호 레코드 로 돌아간다
						}
						display();
					}else{
						JOptionPane.showMessageDialog(this, "도서가 대여중입니다, \n반납 후 삭제 가능합니다.");
					}
				} catch (Exception e2) {
					System.out.println("삭제 오류 : " + e2);
				}
			}
		}else if(e.getSource().equals(btnFind)){ //검색
			String fjemok = JOptionPane.showInputDialog(this, "찾으시는 도서의 제목을 입력하세요");
			if(fjemok == null) return;
			try {
				int currentRow = rs.getRow(); //검색 결과가 없을 시 돌아오기 위함
				rs.beforeFirst(); //현재 레코드에서 무조건 처음으로 간다.
				int couFind = 0;
				while (rs.next()){
					String str = rs.getString("b_jemok");
					if(fjemok.equals(str)){
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
				System.out.println("도서 검색 실패 : " + e2);
			}
		}else if(e.getSource().equals(btnOption)){ //옵션
			//상세 자료 처리 창 호출
			JDialog jd = new JDialog(new JFrame(), "도서 목록", true);
			jd.add(new FindBook());
			jd.setBounds(350, 300, 600, 300);
			jd.setVisible(true);
		}else if(e.getSource().equals(btnClose)){
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("닫기 오류 : " + e2);
			}finally{
				BookMain.book_book.setEnabled(true); //메인화면에 대여메뉴를 활성화
				BookMain.childWinBook.dispose(); //메인에 있는 대여창이 닫힘				
			}
		}
	}
	class FindBook extends JPanel implements ActionListener{
		private String[][] datas = new String[0][6];
		private String[] title = { "도서번호", "도서제목", "대여여부", "대여자이름", "대여자전화", "총 대여수"};
		private DefaultTableModel model = new DefaultTableModel(datas, title); // 내부적인 데이터
		private JTable table = new JTable(model); // 내부적인 데이터 모델을 가지고 테이블뷰로 보여준다
		private JScrollPane scrollPane = new JScrollPane(table);
		
		private JRadioButton btn_all = new JRadioButton("전체", true);
		private JRadioButton btn_y = new JRadioButton("대여중");
		private JRadioButton btn_n = new JRadioButton("비치중");
		private ButtonGroup buttonGroup = new ButtonGroup();
		
		private String book_who = "";
		
		public FindBook() {
			findlayInit();
			findaccDb();
			finddisplay();
		}
		
		private void findlayInit(){
			setLayout(new BorderLayout());
			table.getColumn("도서번호").setPreferredWidth(65);
			table.getColumn("도서제목").setPreferredWidth(190);
			table.getColumn("대여여부").setPreferredWidth(70);
			table.getColumn("대여자이름").setPreferredWidth(80);
			table.getColumn("대여자전화").setPreferredWidth(115);
			table.getColumn("총 대여수").setPreferredWidth(70);
			add("Center",scrollPane);
			
			JPanel panel1 = new JPanel();
			buttonGroup.add(btn_all);
			buttonGroup.add(btn_y);
			buttonGroup.add(btn_n);
			panel1.add(btn_all);
			panel1.add(btn_y);
			panel1.add(btn_n);
			add("South", panel1);			
			
			btn_all.addActionListener(this);
			btn_y.addActionListener(this);
			btn_n.addActionListener(this);
		}

		private void findaccDb() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (Exception e) {
				System.out.println("Db err: " + e);
			}
		}

		private void finddisplay() {
			model.setNumRows(0);
			try {
				sql = "select b_bun, b_jemok, b_daeyn, c_irum, c_junhwa, b_daesu from book"
						+ " left join customer on b_daebun = c_bun" + book_who + " order by b_bun";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				int count = 0;
				String dae_yn = "";
				while(rs.next()){
					if(rs.getString("b_daeyn").equals("y")){
						dae_yn = "대여중···";
					}else{
						dae_yn = "비치중";					
					}
					String rowData[] = {
							rs.getString("b_bun"),
							rs.getString("b_jemok"),
							dae_yn,
							rs.getString("c_irum"),
							rs.getString("c_junhwa"),
							rs.getString("b_daesu")};
					model.addRow(rowData);
					count++;
				}
				String rowCount[] = {null,"--- 총 권수:" + count + " ---", null, null, null, null};
				model.addRow(rowCount);
			} catch (Exception e) {
				System.out.println("finddisplay" + e);
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btn_all){
				book_who = "";
			}else if(e.getSource() == btn_y){
				book_who = " where b_daeyn = 'y'";
			}else if(e.getSource() == btn_n){
				book_who = " where b_daeyn = 'n'";
			}
			finddisplay();
		}
	}
	
	public static void main(String[] args){
		BookBook bookBook = new BookBook();
		
		JFrame frame = new JFrame("도서 창");
		frame.getContentPane().add(bookBook);
		frame.setBounds(440,110,430,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

















