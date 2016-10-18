package db.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class BookBannap extends JPanel implements ActionListener{
	JTextField txtBbun,txtBjemok,txtBdaeil,txtBdaebun,  txtJemok;
	static JTextField txtBbanil;
	JButton btnBbun,btnChange,btnBannap,btnNew,btnClose;
	DefaultTableModel mod;
	static JFrame calFrame;
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs1, rs2;
	String sql;
	
	public BookBannap(){
		design();
		addListener();
		accDb();
	}
	public void design(){

		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//도서정보 패널========================
		JPanel bookPn = new JPanel(new GridLayout(3,1));
		bookPn.setBorder(new TitledBorder(new TitledBorder("도서 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		JPanel bPn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		txtBbun = new JTextField("",5);
		txtBjemok = new JTextField("",20);
		txtBdaeil = new JTextField("",10);
		txtBbanil = new JTextField("",10);
		txtBdaebun = new JTextField("",5);
		txtJemok = new JTextField("",25);  //반납되는 도서 제목을 고객메모에서 제거하기 위함
		
		btnBbun = new JButton("확인");
		btnBbun.setMargin(new Insets(0, 3, 0, 3));
		btnChange = new JButton("변경");
		btnChange.setMargin(new Insets(0, 3, 0, 3));

		bPn1.add(new JLabel("번호:"));
		bPn1.add(txtBbun);
		bPn1.add(btnBbun);
		
		bPn2.add(new JLabel("제목:"));
		bPn2.add(txtBjemok);
		txtBjemok.setEditable(false);
		
		bPn3.add(new JLabel("대여일:"));
		bPn3.add(txtBdaeil);	
		txtBdaeil.setEditable(false);
		bPn3.add(new JLabel("      반납일:"));
		bPn3.add(txtBbanil);
		bPn3.add(btnChange);	
		
		bPn4.add(new JLabel("대여자 번호:"));
		bPn4.add(txtBdaebun);
		bPn4.add(txtJemok);   //고객메모란의 대여도서 제목중 반납되는 도서 제목만 제거하기 위해 사용  
		txtJemok.setVisible(true); //숨긴다.
		txtBdaebun.setEditable(false);
		
		JPanel bottomPn = new JPanel();
		bottomPn.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		btnBannap=new JButton("반납 확인");
		btnNew=new JButton("새로 입력");
		btnClose=new JButton(" 닫 기 ");
		
		bottomPn.add(btnBannap);
		bottomPn.add(btnNew);
		JLabel lbl=new JLabel("    "); 
		bottomPn.add(lbl);
		bottomPn.add(btnClose);
		
		bookPn.add(bPn1);  bookPn.add(bPn2); 	bookPn.add(bPn3);	
		
		this.add(bookPn);
		this.add(bPn4);
		this.add(bottomPn);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//도서 목록 테이블 삽입
		String[][]data = new String[0][4];
		String []cols = {"번호","제목","대번","이름","대여일"};
		mod = new DefaultTableModel(data,cols){ //테이블 내용 수정 불가
			    public boolean isCellEditable(int rowIndex, int mColIndex) {
				   return false;
				}
			   };
		JTable tab = new JTable(mod);
		tab.getColumnModel().getColumn(0).setPreferredWidth(20);
		tab.getColumnModel().getColumn(1).setPreferredWidth(150);
		tab.getColumnModel().getColumn(2).setPreferredWidth(20);
		tab.getColumnModel().getColumn(3).setPreferredWidth(30);
		tab.setSelectionBackground(Color.green);
		JScrollPane pa = new JScrollPane(tab);
		this.add(pa);
		
		btnBannap.setEnabled(false);  //반납 버튼 비활성화
		btnNew.setEnabled(false);     
	}
	
	private void addListener(){
		btnBbun.addActionListener(this);
		btnChange.addActionListener(this);
		btnBannap.addActionListener(this);
		btnNew.addActionListener(this);
		btnClose.addActionListener(this);
	}
	
	private void accDb(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("Db err: " + e);
		}
		
		daeDisplay(); // 대여 중인 도서 자료 테이블에 출력
	}
	
	private void daeDisplay(){
		mod.setNumRows(0); //표 초기화
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			sql = "select b_bun, b_jemok, c_bun, c_irum, b_daeil from book inner join customer on b_daebun = c_bun order by b_daeil desc, b_bun asc";
			pstmt = conn.prepareStatement(sql);
			rs1 = pstmt.executeQuery();
			int count = 0;
			while(rs1.next()){
				String[] imsi = {
						rs1.getString("b_bun"),
						rs1.getString("b_jemok"),
						rs1.getString("c_bun"),
						rs1.getString("c_irum"),
						rs1.getString("b_daeil").substring(0, 10)	
				};
				mod.addRow(imsi);
				count++;
			}
			String[] imsi2 = {null, "전체 건수 : " + count};
			mod.addRow(imsi2);
			rs1.close();
		} catch (Exception e) {
			System.out.println("daeDisplay err" + e);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnBbun)){ //도서번호확인
			if (txtBbun.getText().equals("")){
				JOptionPane.showMessageDialog(this, "도서번호를 입력하시오");
				txtBbun.setText(null);
				txtBbun.requestFocus();
			}
			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
					sql = "select * from book where b_bun=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, txtBbun.getText());
					rs1 = pstmt.executeQuery();
					if(!rs1.next()){
						JOptionPane.showMessageDialog(this, "등록된 도서가 아닙니다");
						txtBbun.setText(null);
						txtBjemok.setText(null);
						txtBbanil.setText(null);
						txtBdaeil.setText(null);
						txtBdaebun.setText(null);
						txtJemok.setText(null);
						txtBbun.requestFocus();
						rs1.close();
						return;
					}
					txtBjemok.setText(rs1.getString("b_jemok")); // 대여 책 제목
					
					if (rs1.getString("b_daeyn").equals("n")){
						JOptionPane.showMessageDialog(this, "대여된 도서가 아	닙니다");
						txtBbun.setText("");
						txtBjemok.setText("");
						txtBbanil.setText("");
						txtBdaeil.setText("");
						txtBdaebun.setText("");
						txtJemok.setText("");
						txtBbun.requestFocus();
						rs1.close();
						return;
					}	
						txtBdaeil.setText(rs1.getString("b_daeil").substring(0, 10)); // 대여일
						// 반납일
						Calendar calendar = Calendar.getInstance();
						txtBbanil.setText(calendar.get(calendar.YEAR) + "-" + (calendar.get(calendar.MONTH) + 1) + "-" + calendar.get(calendar.DAY_OF_MONTH));
						txtBdaebun.setText(rs1.getString("b_daebun"));
						
						//반납되는 도서제목을 customer의 메모에서 제거 작업 --------------------------------------------
						sql = "select * from customer where c_bun=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, txtBdaebun.getText());
						rs2 = pstmt.executeQuery();
						rs2.next();
						txtJemok.setText(rs2.getString("c_memo"));		
						
						String ban_jemok = txtJemok.getText();
						int start = ban_jemok.indexOf(txtBjemok.getText());
						int end = txtBjemok.getText().length();
			//			System.out.println(start + " " + end);
						txtJemok.setSelectionStart(start); //반납도서 제목 반전 
						txtJemok.setSelectionEnd(start + end + 1);
						txtJemok.requestFocus();
						txtJemok.replaceSelection(""); //반전자료를 삭제
						//마지막 콤마 제거 작업
						try {
							String ss = txtJemok.getText();
							int a = ss.length() - 1;
							String sss = ss.substring(a);
							if(sss.equals(",")) ss = ss.substring(0 , a); 
							txtJemok.setText(ss);
						} catch (Exception e2) {
							
						}
						//-----------------------------------------------------------------------
						btnBannap.setEnabled(true); // 반납확인버튼 활성화
			} catch (Exception e2) {
				System.out.println("도서번호 확인 에러: " + e2);
			}
		}else if(e.getSource().equals(btnChange)){ //변경
			BookCal bookCal = new BookCal();
			calFrame = new JFrame("반납일 변경");
			calFrame.add(bookCal);
			calFrame.setBounds(300, 300, 250, 250);
			calFrame.setVisible(true);
		}else if(e.getSource().equals(btnBannap)){ //반납
			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
				//customer (c_memo)
				sql = "update customer set c_memo = ? where c_bun = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, txtJemok.getText());
				pstmt.setString(2, txtBdaebun.getText());
				pstmt.executeUpdate();
				
				//book 
				sql = "update book set b_daeyn = 'n', b_daebun = 0, b_daeil = null, b_banil = ? where b_bun = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, txtBbanil.getText());
				pstmt.setString(2, txtBbun.getText());
				pstmt.executeUpdate();
				
				btnBannap.setEnabled(false); //반납버튼 비활성화
				btnNew.setEnabled(true);
				
				daeDisplay(); //갱신 후 대여 도서 자료 보기
			} catch (Exception e2) {
				System.out.println("반납 오류: " + e2);
			} finally {
				try {
					if (rs1 != null) rs1.close();
					if (rs2 != null) rs2.close();
					if (pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				} catch (Exception e2) {
					System.out.println("닫기 오류 : " + e2);
				}
			}
		}else if(e.getSource().equals(btnNew)){ //새로입력
			txtBbun.setText(null);
			txtBjemok.setText(null);
			txtBdaeil.setText(null);
			txtBbanil.setText(null);
			txtBdaebun.setText(null);
			txtJemok.setText(null);
			txtBbun.requestFocus();
			btnNew.setEnabled(false);
		}else if(e.getSource().equals(btnClose)){
			try {
				if (rs1 != null) rs1.close();
				if (rs2 != null) rs2.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("닫기 오류 : " + e2);
			} finally {
				BookMain.book_ban.setEnabled(true); //메인화면에 대여메뉴를 활성화
				BookMain.childWinBan.dispose(); //메인에 있는 대여창이 닫힘
			}
		}
	}
	
	public static void main(String[] args) {
		BookBannap bookBannap = new BookBannap();
		JFrame frame = new JFrame("반납 창");
		frame.getContentPane().add(bookBannap);
		frame.setResizable(false);
		frame.setBounds(200,200,500,400);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}