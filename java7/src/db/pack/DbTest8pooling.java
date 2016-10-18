package db.pack;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest8pooling {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	DBConnectionMgr mgr;
	
	public DbTest8pooling() {
		try {
			mgr = DBConnectionMgr.getInstance(); //싱글톤 패턴
		} catch (Exception e) {
			System.out.println("연결실패: " + e);
			return;
		}
		
		accDb();
	}
	
	private void accDb(){
		//System.out.println("success");
		try {
			conn = mgr.getConnection(); // 연결 한다
			String sql = "select * from sangdata";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}
		} catch (Exception e) {
			System.out.println("accDb err: " + e);
		} finally {
			mgr.freeConnection(conn, stmt, rs); //연결 닫아준다
		}
	}
	
	
	public static void main(String[] args) {
		new DbTest8pooling();
	}

}





























