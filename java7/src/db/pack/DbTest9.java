package db.pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest9 {

	private Connection conn; //DB 연결 객체
	private Statement stmt; //SQL문을 실행
	private ResultSet rs; //select의 결과를 처리
	
	public DbTest9() {
		try {
			// 1. Driver loading
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.6:1521:orcl", "scott", "tiger");
			
			process();
			
			conn.close();
		} catch (Exception e) {
			System.out.println("로딩 실패: " + e);
		}
	}

	private void process(){
		try {
			stmt = conn.createStatement();
			stmt.execute("select * from sangdata");
				rs = stmt.getResultSet();
				while (rs.next()){
					System.out.println(rs.getString(1) + " " + rs.getString(2));
				}	
		} catch (Exception e) {
			System.out.println("process err: " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
			} catch (Exception e2) {
				
			}
		}
	}
	
	public static void main(String[] args) {
		new DbTest9();

	}
}


























