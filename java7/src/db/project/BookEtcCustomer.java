package db.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class BookEtcCustomer extends JPanel implements ActionListener{
	private JButton btnCall,btnCdae,btnCjuyo;
	private DefaultTableModel mod;
	private Boolean juyomode = false; 
	//주요 모드 = 대여횟수 5회 이상.. 고객..
	private Properties properties = new Properties(); //드라이버 가져오기
	private	Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	private String gogek = "";
	
	public BookEtcCustomer(){
		design();
	}
	
	public void design(){
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder(new TitledBorder("고객 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		JPanel cPn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		btnCall = new JButton("전체고객");
		btnCdae = new JButton("대여중인 고객");
		btnCjuyo = new JButton("주요 고객");
	
		cPn1.add(new JLabel("고객 자료 보기"));
		cPn1.add(btnCall);		
		cPn1.add(btnCdae);
		cPn1.add(btnCjuyo);
		this.add("North",cPn1);
		
		//비디오 목록 테이블 삽입
		String[][]data = new String[0][4];
		String []cols = {"번호","이름","전화","대여횟수","메모"};
		mod = new DefaultTableModel(data,cols);
		JTable tab = new JTable(mod);
		tab.getColumnModel().getColumn(0).setPreferredWidth(20);
		tab.getColumnModel().getColumn(1).setPreferredWidth(26);
		tab.getColumnModel().getColumn(3).setPreferredWidth(20);
		tab.getColumnModel().getColumn(4).setPreferredWidth(50);
		tab.setPreferredScrollableViewportSize(new Dimension(10,120)); 

		tab.setSelectionBackground(Color.green);
		JScrollPane scroll = new JScrollPane(tab);
		this.add("Center",scroll);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		btnCall.addActionListener(this);
		btnCdae.addActionListener(this);
		btnCjuyo.addActionListener(this);
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
	
	private void display(){
		accDb();
		mod.setNumRows(0);
		try {
			sql = "select c_bun, c_irum, c_junhwa, c_daesu, c_memo from customer " + gogek + " order by c_bun";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int count = 0;
			while(rs.next()){
				String rowData[] = {
						rs.getString("c_bun"),
						rs.getString("c_irum"),
						rs.getString("c_junhwa"),
						rs.getString("c_daesu"),
						rs.getString("c_memo")};
				mod.addRow(rowData);
				count++;
			}
			String dataCount[] = {null,"--- 총 인원: " + count + " ---", null, null, null};
			mod.addRow(dataCount);
		} catch (Exception e) {
			System.out.println("display err" + e);
		} finally{
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				System.out.println("display close err : " + e2) ;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCall){ //전체 고객
			gogek = "";
			display();
		}else if(e.getSource() == btnCdae){ //대여중인 고객
			gogek = " where c_memo is not null";
			display();
		}else if(e.getSource() == btnCjuyo){ //주요 고객
			gogek = " where c_daesu >= 3";
			display();
		}
		
	}
	public static void main(String[] args) {
		BookEtcCustomer bookEtcCustomer =new BookEtcCustomer();
		JFrame frame = new JFrame();
		frame.getContentPane().add(bookEtcCustomer);
		frame.setBounds(200,200,580,400);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
