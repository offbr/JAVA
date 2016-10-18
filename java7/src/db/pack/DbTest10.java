package db.pack;
//oracle의 procedure 수행

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbTest10 {
	Connection conn;
	CallableStatement cstmt; //procedure 처리용
	
	public DbTest10() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.6:1521:orcl", "scott", "tiger");
			
			process();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void process(){
		try { //수정도 가능
			/*cstmt = conn.prepareCall("{call pro_del(?)}"); // call = exec
			cstmt.setInt(1, 10); //첫번째 물음표에 3을 준다 //인트값.
			cstmt.executeUpdate();
			*/
			cstmt = conn.prepareCall("{call pro_up(?,?)}"); // call = exec
			cstmt.setInt(1, 1); //첫번째 물음표에 인트값.
			cstmt.setString(2, "전무");
			cstmt.executeUpdate();
			System.out.println("삭제완료");
		} catch (Exception e) {
			System.out.println("process err: " + e);
		} finally {
			try {
				if (cstmt != null) cstmt.close();
			} catch (Exception e2) {
				System.out.println("close err" + e2);
			}
		}
		
	}
	
	public static void main(String[] args) {
		new DbTest10();
	}

}
