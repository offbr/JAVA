﻿package db.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Year;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.*;

public class BookDaeyeo extends JPanel implements ActionListener{
	JTextField txtCbun,txtCirum,txtCjunhwa,txtCjuso,txtBbun,txtBjemok,txtBdaeil;
	JTextArea taCmemo;
	JButton btnCbun,btnCirum,btnCjunhwa,btnBbun,btnBjemok,btnDaeyeo,btnNew,btnClose;
	
	boolean bcus=false, bbook=false;  //대여확인 버튼 활성화 여부
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs1, rs2;
	String sql;
	
	public BookDaeyeo(){
		design();
		addListener();
		accDb();
	}
	
	public void design(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//고객정보 패널========================
		JPanel customerPn = new JPanel(new GridLayout(3,2));
		customerPn.setBorder(new TitledBorder(new TitledBorder("고객 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		JPanel cPn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn4 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		txtCbun = new JTextField("",5);
		txtCirum = new JTextField("",10);
		txtCjunhwa = new JTextField("",10);
		txtCjuso = new JTextField("",20);
		txtCjuso.setEditable(false);
		
		taCmemo = new JTextArea(2,20);
		JScrollPane scroll = new JScrollPane(taCmemo,	ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		taCmemo.setEditable(false);
		taCmemo.setBackground(Color.lightGray);
		
		btnCbun = new JButton("확인1");
		btnCbun.setMargin(new Insets(0, 3, 0, 3));
		btnCirum = new JButton("확인2");
		btnCirum.setMargin(new Insets(0, 3, 0, 3));
		btnCjunhwa = new JButton("확인3");
		btnCjunhwa.setMargin(new Insets(0, 3, 0, 3));

		cPn1.add(new JLabel("번호:"));
		cPn1.add(txtCbun);
		cPn1.add(btnCbun);
		
		cPn2.add(new JLabel("이름:"));
		cPn2.add(txtCirum);	
		cPn2.add(btnCirum);
		
		cPn3.add(new JLabel("전화:"));
		cPn3.add(txtCjunhwa);
		cPn3.add(btnCjunhwa);
		
		cPn4.add(new JLabel("주소:"));
		cPn4.add(txtCjuso);
		
		customerPn.add(cPn1); customerPn.add(cPn2); customerPn.add(cPn3);
		customerPn.add(cPn4); 
		customerPn.add(new JLabel("고객이 대여한 도서  ☞   ",JLabel.RIGHT));
		customerPn.add(scroll); 
		scroll.setBorder(BorderFactory.createEmptyBorder(1, 1, 5, 5));
		this.add(customerPn);
				
		//도서정보 패널========================
		JPanel bookPn = new JPanel(new GridLayout(3,1));
		bookPn.setBorder(new TitledBorder(new TitledBorder("도서 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		
		JPanel bPn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		txtBbun = new JTextField("",5);
		txtBjemok = new JTextField("",20);
		txtBdaeil = new JTextField("",10);
		btnBbun = new JButton("확인1");
		btnBbun.setMargin(new Insets(0, 3, 0, 3));
		btnBjemok = new JButton("확인2");
		btnBjemok.setMargin(new Insets(0, 3, 0, 3));

		bPn1.add(new JLabel("번호:"));
		bPn1.add(txtBbun);
		bPn1.add(btnBbun);
		
		bPn2.add(new JLabel("제목:"));
		bPn2.add(txtBjemok);
		bPn2.add(btnBjemok);
		
		bPn3.add(new JLabel("대여일:"));
		bPn3.add(txtBdaeil);	
		
		JPanel bottomPn = new JPanel();
		bottomPn.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		btnDaeyeo = new JButton("대여 확인");
		btnNew = new JButton("새로 입력");
		btnClose = new JButton(" 닫 기 ");
		
		bottomPn.add(btnDaeyeo);
		bottomPn.add(btnNew);
		JLabel lbl = new JLabel("    ");  //버튼 사이에 공백 부여
		bottomPn.add(lbl);
		bottomPn.add(btnClose);
		
		bookPn.add(bPn1);  bookPn.add(bPn2); 	bookPn.add(bPn3);	
		
		this.add(customerPn);
		this.add(bookPn);
		this.add(bottomPn);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		btnDaeyeo.setEnabled(false); //대여 버튼 비활성화
		btnNew.setEnabled(false);
	}

	private void addListener(){
		btnCbun.addActionListener(this);
		btnCirum.addActionListener(this);
		btnCjunhwa.addActionListener(this);
		btnBbun.addActionListener(this);
		btnBjemok.addActionListener(this);
		btnDaeyeo.addActionListener(this);
		btnNew.addActionListener(this);
		btnClose.addActionListener(this);
	}
	
	private void accDb(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("Db err: " + e);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnCbun)){ //고객번호 ---------------------------------------------------------
			if(txtCbun.getText().equals("")){
				txtCbun.requestFocus();
				JOptionPane.showMessageDialog(this, "고객정보를 입력하시오");
				return;
			}
			sql = "select * from customer where c_bun=?";
			processCustomer(txtCbun.getText().trim());
		}else if(e.getSource().equals(btnCirum)){ //고객이름
			if(txtCirum.getText().equals("")){
				txtCirum.requestFocus();
				JOptionPane.showMessageDialog(this, "고객정보를 입력하시오");
				return;
			}
			sql = "select * from customer where c_irum=?";
			processCustomer(txtCirum.getText().trim());
		}else if(e.getSource().equals(btnCjunhwa)){ //고객전화
			if(txtCjunhwa.getText().equals("")){
				txtCjunhwa.requestFocus();
				JOptionPane.showMessageDialog(this, "고객정보를 입력하시오");
				return;
			}
			sql = "select * from customer where c_junhwa=?";
			processCustomer(txtCjunhwa.getText().trim());
		}else if(e.getSource().equals(btnBbun)){ //도서번호  ---------------------------------------------------------
			if(txtBbun.getText().equals("")){
				txtBbun.requestFocus();
				JOptionPane.showMessageDialog(this, "도서정보를 입력하시오");
				return;
			}
			sql = "select * from book where b_bun=?";
			processBook(txtBbun.getText().trim());
		}else if(e.getSource().equals(btnBjemok)){ //도서제목
			if(txtBjemok.getText().equals("")){
				txtBjemok.requestFocus();
				JOptionPane.showMessageDialog(this, "도서정보를 입력하시오");
				return;
			}
			sql = "select * from book where b_jemok=?";
			processBook(txtBjemok.getText().trim());
		}else if(e.getSource().equals(btnDaeyeo)){ //대여확인
			//대여도서제목을 메모란에 표시 후 반납에 참여시킬 예정
			if(taCmemo.getText().equals("")){ //첫번째 도서
				taCmemo.setText(txtBjemok.getText().trim());
			}else{ //이미 대여 도서가 있는 상태
				taCmemo.setText(taCmemo.getText() + "," + txtBjemok.getText().trim());
			}
			try { //동시에 실행된다면 트랜젝션
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
				
				//customer
				sql = "update customer set c_daesu=c_daesu + 1, c_memo=? where c_bun=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, taCmemo.getText());
				pstmt.setString(2, txtCbun.getText());
				pstmt.executeUpdate();
				bcus = false;
				
				//book
				sql = "update book set b_daesu=b_daesu + 1, b_daeyn='y', b_daebun=?, b_daeil=? where b_bun=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, txtCbun.getText());
				pstmt.setString(2, txtBdaeil.getText());
				pstmt.setString(3, txtBbun.getText());
				pstmt.executeUpdate();
				bbook = false;
				
				btnDaeyeo.setEnabled(false);
				btnNew.setEnabled(true);
			} catch (Exception e2) {
				System.out.println("update err: " + e);
			} finally {
				try {
					if (rs1 != null) rs1.close();
					if (rs2 != null) rs2.close();
					if (pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				} catch (Exception e2) {
					System.out.println("대여확인 닫기 오류 : " + e2);
				}
			}		
		}else if(e.getSource().equals(btnNew)){ //새로입력
			txtCbun.setText(null);
			txtCirum.setText(null);
			txtCjunhwa.setText(null);
			txtCjuso.setText(null);
			taCmemo.setText(null);
			
			txtBbun.setText(null);
			txtBjemok.setText(null);
			txtBdaeil.setText(null);
			
			btnNew.setEnabled(false);
		}else if(e.getSource().equals(btnClose)){ //닫기
			try {
				if (rs1 != null) rs1.close();
				if (rs2 != null) rs2.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("닫기 오류 : " + e2);
			} finally {
				BookMain.book_dae.setEnabled(true); //메인화면에 대여메뉴를 활성화
				BookMain.childWinDae.dispose(); //메인에 있는 대여창이 닫힘				
			}
		}
	}
	
	private void processCustomer(String findData){
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, findData);
			rs1 = pstmt.executeQuery();
			if(rs1.next()){
				txtCbun.setText(rs1.getString("c_bun")); //고객이름
				txtCirum.setText(rs1.getString("c_irum")); //고객이름
				txtCjunhwa.setText(rs1.getString("c_junhwa")); //고객전화
				txtCjuso.setText(rs1.getString("c_juso")); //고객주소
				taCmemo.setText(rs1.getString("c_memo")); //고객메모
				
				bcus = true; // 고객 만족 
				btnDaeyeo.setEnabled(bcus & bbook); //둘다 참이면 true
			}else{
				JOptionPane.showMessageDialog(this, "등록자료가 없습니다.");
				txtCbun.setText(null);
				txtCirum.setText(null);
				txtCjunhwa.setText(null);
				txtCjuso.setText(null);
				taCmemo.setText(null);
				
				bcus = false; // 고객 만족 X 
				btnDaeyeo.setEnabled(false); //둘다 참이면 true
			}
		} catch (Exception e) {
			System.out.println("processCustomer err: " + e);
		} finally {
			try {
				if (rs1 != null) rs1.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("processCustomer 닫기 오류 : " + e2);
			}	
		}
	}
	
	private void processBook(String findData){
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, findData);
			rs2 = pstmt.executeQuery();
			if(rs2.next()){
				txtBbun.setText(rs2.getString("b_bun")); //도서번호
				txtBjemok.setText(rs2.getString("b_jemok")); //도서이름
				
				//대여 가능여부 판단 
				if(rs2.getString("b_daeyn").equals("y")){
					JOptionPane.showMessageDialog(this, "현재 대여중인 도서입니다.");
					txtBbun.setText(null);
					txtBjemok.setText(null);
					return;
				}
				
				//대여일
				Calendar calendar = Calendar.getInstance();
				txtBdaeil.setText(calendar.get(calendar.YEAR) + "-" +(calendar.get(calendar.MONTH) + 1) + "-" + calendar.get(calendar.DAY_OF_MONTH));
				
				bbook = true; // 고객 만족 
				btnDaeyeo.setEnabled(bcus & bbook); //둘다 참이면 true
			}else{
				JOptionPane.showMessageDialog(this, "도서자료가 없습니다.");
				txtBbun.setText(null);
				txtBjemok.setText(null);
				txtBdaeil.setText(null);
				
				bbook = false; // 고객 만족 X 
				btnDaeyeo.setEnabled(false); //둘다 참이면 true
			}
		} catch (Exception e) {
			System.out.println("processBook err: " + e);
		} finally {
			try {
				if (rs2 != null) rs2.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("processBook 닫기 오류 : " + e2);
			}
		}
	}

	public static void main(String[] args) {
		BookDaeyeo daeyeo = new BookDaeyeo();
		JFrame frame = new JFrame("대여 창");
		frame.getContentPane().add(daeyeo);
		//frame.setResizable(false);
		frame.setBounds(200,200,580,400);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
