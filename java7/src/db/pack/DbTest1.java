package db.pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest1 {
	private Connection conn; //DB 연결 객체
	private Statement stmt; //SQL문을 실행
	private ResultSet rs; //select의 결과를 처리
	
	public DbTest1() {
		try {
			//1. Driver loading
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("로딩 성공");
		} catch (Exception e) {
			System.out.println("로딩 실패: " + e);
		}
		
		try {
			//2. DB와 연결
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "tiger");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.6:1521:orcl", "scott", "tiger");
			System.out.println("DB 연결 성공");
		} catch (Exception e) {
			System.out.println("DB 연결 실패: " + e);
		}
		try {
			//3. SQL문 수행 : 자료 읽기
			stmt = conn.createStatement();
			/*
			rs = stmt.executeQuery("select * from sangdata");
			boolean b = rs.next(); //Record pointer 이동
			System.out.println("b는 " + b);
			String code = rs.getString("code");
			String sang = rs.getString("sang");
			String su = rs.getString("su");
			String dan = rs.getString("dan");
			System.out.println(code + " " + sang + " " + su + " " + dan);
			*/
			
			//모든 자료 읽기
			rs = stmt.executeQuery("select * from sangdata");
			int count = 0;
			while(rs.next()){
				String code = rs.getString("code");
				String sang = rs.getString("sang");
				String su = rs.getString("su");
				String dan = rs.getString("dan");
				System.out.println(code + " " + sang + " " + su + " " + dan);
				count++;
			}
			System.out.println("건수 : " + count);
			
			System.out.println();
			String sql = "select count(*) as cou from sangdata";
			rs = stmt.executeQuery(sql);
			rs.next();
			System.out.println("건수 : " + rs.getString("cou"));
			
			System.out.println();
			rs = stmt.executeQuery("select code as bun,sang,dan,su from sangdata");
			while(rs.next()){
				//String code = rs.getString("bun");
				String code = rs.getString("bun"); //as 는 as 로 불러들인다
				String sang = rs.getString(2); // 순서값을 줄 수 있지만 칼럼명을 주는게 빠르다.
				int su = rs.getInt(4);
				int dan = rs.getInt("dan");  // 타입은 맞는 타입중에 원하는 타입으로 불러들인다.
				System.out.println(code + " " + sang + " " + su + " " + dan);
			}
		} catch (Exception e) {
			System.out.println("처리 오류 : " + e);
		} finally {
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
		new DbTest1();
	}
}






























