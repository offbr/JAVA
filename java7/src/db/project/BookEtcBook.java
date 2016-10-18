package db.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class BookEtcBook extends JPanel implements ActionListener{
	
	private JButton btnAllcustomer,btnDae,btnBan;
	private JComboBox btnJang;
	private DefaultTableModel mod;	
	private int selectedMode; 
	// 1 = all customer, 2 = 대여중인 도서, 3 = 비치된 도서, 0 = default
	private Properties properties = new Properties(); //드라이버 가져오기
	private	Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	private String book_yn = "";
	private String book_jang = "";
	
	
	public BookEtcBook() {
		design();
		accDb();
		accItem();
	}
	private void design(){
		this.setLayout(new BorderLayout());
		//도서정보 패널========================
		this.setBorder(new TitledBorder(new TitledBorder("고객 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		JPanel bPn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		btnAllcustomer = new JButton("전체 도서");
		btnDae = new JButton("대여중");
		btnBan = new JButton("비치중");
		btnJang = new JComboBox();
		btnJang.addItem("장르별");
		
		bPn1.add(new JLabel("도서 자료 보기"));
		bPn1.add(btnAllcustomer);		
		bPn1.add(btnDae);
		bPn1.add(btnBan);
		bPn1.add(btnJang);
		this.add("North",bPn1);
		
		//도서 목록 테이블 삽입
		String[][]data = new String[0][4];
		String []cols = {"번호","제목","장르","대여횟수","메모"};
		mod = new DefaultTableModel(data,cols);
		JTable tab = new JTable(mod);
		
		tab.getColumnModel().getColumn(0).setPreferredWidth(7);
		tab.getColumnModel().getColumn(1).setPreferredWidth(60);
		tab.getColumnModel().getColumn(2).setPreferredWidth(7);
		tab.getColumnModel().getColumn(3).setPreferredWidth(7);
		tab.getColumnModel().getColumn(4).setPreferredWidth(15);
		tab.setPreferredScrollableViewportSize(new Dimension(10,120));
		tab.setSelectionBackground(Color.green);
		JScrollPane scroll = new JScrollPane(tab);
		this.add("Center",scroll);
		
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		btnAllcustomer.addActionListener(this);
		btnDae.addActionListener(this);
		btnBan.addActionListener(this);
		btnJang.addActionListener(this);
	}
	
	private void accDb(){
		try {
			properties.load(new FileInputStream("C:/work/sou/java7/src/db/project/test.properties"));
			Class.forName(properties.getProperty("driver"));
			conn = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),properties.getProperty("passwd"));
		} catch (Exception e) {
			System.out.println("Db err: " + e);
		} 
	}

	private void accItem(){
		try {
			sql = "select distinct b_jang, count(b_jang) from book group by b_jang order by count(b_jang) desc ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				btnJang.addItem(rs.getString("b_jang"));
			}
		} catch (Exception e) {
			System.out.println("accItem err: " + e);
		} finally{
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();				
			} catch (Exception e2) {
				System.out.println("db_dept close err : " + e2) ;
			}
		}
	}
	
	private void display(){
		mod.setNumRows(0);
		try {
			sql = "select b_bun, b_jemok, b_jang, b_daesu, b_memo from book" + book_yn + book_jang + " order by b_bun";			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int count = 0;
			while(rs.next()){
				String rowData[] = {
					rs.getString("b_bun"),
					rs.getString("b_jemok"),
					rs.getString("b_jang"),
					rs.getString("b_daesu"),
					rs.getString("b_memo")};
				mod.addRow(rowData);
				count++;
			}
			String dataCount[] = {null,"--- 총 권수 : " + count + " ---", null, null, null};
			mod.addRow(dataCount);
		} catch (Exception e) {
			System.out.println("display err: " + e);
		} finally{
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();				
			} catch (Exception e2) {
				System.out.println("db_dept close err : " + e2) ;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAllcustomer){ //전체도서
			btnJang.setSelectedItem("장르별");
			book_yn = "";
			book_jang = "";
		}else if(e.getSource() == btnDae){ //대여중
			btnJang.setSelectedItem("장르별");
			book_yn = " where b_daeyn = 'y'";
			if(!btnJang.getSelectedItem().equals("장르별"))book_jang = " and b_jang = '" + btnJang.getSelectedItem() + "'";
		}else if(e.getSource() == btnBan){ //비치중
			btnJang.setSelectedItem("장르별");
			book_yn = " where b_daeyn = 'n'";
			if(!btnJang.getSelectedItem().equals("장르별"))book_jang = " and b_jang = '" + btnJang.getSelectedItem() + "'";
		}
		// 콤보박스 리스트------------------------------------------------
		if(e.getSource() == btnJang){
			if(btnJang.getSelectedItem().equals("장르별")){
				book_yn = "";
				book_jang = "";
			}else if(book_yn.equals("")){
				book_jang = " where b_jang = '" + btnJang.getSelectedItem() + "'";
			}else{
				book_jang = " and b_jang = '" + btnJang.getSelectedItem() + "'";
			}			
		}
		
		display();
	}

	public static void main(String[] args) {
		BookEtcBook bookEtcBook = new BookEtcBook();
		JFrame frame = new JFrame();
		frame.getContentPane().add(bookEtcBook);
		frame.setBounds(200,200,580,400);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}



