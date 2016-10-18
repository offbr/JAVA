package db.pack;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.internal.OracleCallableStatement;
import oracle.jdbc.internal.OracleTypes;
import oracle.jdbc.oracore.OracleType;

public class DbTest12 {
	Connection conn;
	CallableStatement cstmt;
	OracleCallableStatement ocstmt;
	ResultSet rs;
	
	
	public DbTest12() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.6:1521:orcl", "scott", "tiger");
			
			cstmt = conn.prepareCall("{call pro_sel2(?,?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.setInt(2, 7);
			cstmt.execute();
			
			ocstmt = (OracleCallableStatement)cstmt;
			rs = ocstmt.getCursor(1);
			while(rs.next()){
				System.out.println(rs.getString("sawon_no") + " " + 
					rs.getString("sawon_name"));
			}
		} catch (Exception e) {
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (cstmt != null) cstmt.close();
				if (ocstmt != null) ocstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("close err : " + e2);
			}
		}

	}
	
	public static void main(String[] args) {
		new DbTest12();
	}
}
