package db.pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbTest13 {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public DbTest13() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.6:1521:orcl", "scott", "tiger");
			
			//자료 추가
			/*
			String isql = "insert into sangdata values(?,?,?,?)";
			pstmt = conn.prepareStatement(isql);
			pstmt.setString(1, "10");
			pstmt.setString(2, "안경");
			pstmt.setInt(3, 15);
			pstmt.setString(4, "56000");
			int re = pstmt.executeUpdate();
			if (re == 1){
				System.out.println("추가 성공");
			}else{
				System.out.println("추가 실패");
			}
			*/
			
			//자료 수정
			/*
			String usql = "update sangdata set sang=?, su=? where code=?";
			pstmt = conn.prepareStatement(usql);
			pstmt.setString(1, "물안경");
			pstmt.setInt(2, 13);
			pstmt.setInt(3, 10);
			int re = pstmt.executeUpdate();
			if (re >= 1){
				System.out.println("수정 성공");
			}else{
				System.out.println("수정 실패");
			}
			*/
			
			//자료 삭제
			
			String dsql = "delete from sangdata where code=?";
			pstmt = conn.prepareStatement(dsql);
			pstmt.setInt(1, 10);
			int re = pstmt.executeUpdate();
			if (re >= 1){
				System.out.println("삭제 성공");
			}else{
				System.out.println("삭제 실패");
			}
			
			
			//전체 자료 읽기
			String sql  = "select * from sangdata order by code";
			pstmt = conn.prepareStatement(sql); //선처리 방식 // 스테이트먼트를 만들때 sql을 준다
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getString("code") + " " +
						rs.getString("sang") + " " +
						rs.getString("su") + " " +
						rs.getString("dan"));
			}
			
			System.out.println();
			//부분 자료 읽기
			String co = "1";
			//sql  = "select * from sangdata where code = " + co; // 비권장
			sql  = "select * from sangdata where code = ?"; // 권장
			pstmt = conn.prepareStatement(sql); //선처리 방식 // 스테이트먼트를 만들때 sql을 준다
			pstmt.setString(1, co);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				System.out.println(rs.getString("code") + " " +
						rs.getString("sang") + " " +
						rs.getString("su") + " " +
						rs.getString("dan"));
			}else{
				System.out.println("그런 자료는 없어요");
			}
		} catch (Exception e) {
			System.out.println("처리실패: " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				System.out.println("close err : " + e2);
			}
		}	
	}
	public static void main(String[] args) {
		new DbTest13();
	}

}
