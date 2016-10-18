package db.pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest1_1 {
	private Connection conn; //DB 연결 객체
	private Statement stmt; //SQL문을 실행
	private ResultSet rs; //select의 결과를 처리
	
	public DbTest1_1() {
		try {
			// 1. Driver loading
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("로딩 성공");
		} catch (Exception e) {
			System.out.println("로딩 실패: " + e);
		}

		try {
			// 2. DB와 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.6:1521:orcl", "scott", "tiger");
			System.out.println("DB 연결 성공");
		} catch (Exception e) {
			System.out.println("DB 연결 실패: " + e);
		}
		try {
			// 3. SQL문 수행 : 자료 읽기
			stmt = conn.createStatement();
			// 모든 자료 읽기
			rs = stmt.executeQuery("select * from sangdata");
			String spl1 = "select sawon_no, sawon_name, buser_name, buser_tel, sawon_jik, sawon_gen from sawon "
					+ "left outer join buser on buser_num = buser_no where sawon_gen = '남' order by sawon_no";
			rs = stmt.executeQuery(spl1);
			System.out.println("사번" + "\t이름" + "\t부서명" + "\t부서전화" + "\t\t직급" + "\t성별");
			while (rs.next()) {
				String sawon_no = rs.getString("sawon_no");
				String sawon_name = rs.getString("sawon_name");
				String buser_name = rs.getString("buser_name");
				String buser_tel = rs.getString("buser_tel");
				String sawon_jik = rs.getString("sawon_jik");
				String sawon_gen = rs.getString("sawon_gen");
				System.out.println(sawon_no + "\t" + sawon_name + "\t" + buser_name + "\t" + buser_tel + "\t"
						+ sawon_jik + "\t" + sawon_gen);
			}
		} catch (Exception e) {
			System.out.println("처리 오류 : " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("close err : " + e2);
			}
		}
	}
	
	public static void main(String[] args) {
		new DbTest1_1();
	}

}
