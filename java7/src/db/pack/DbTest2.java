package db.pack;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DbTest2 {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private Properties properties = new Properties();
	
	public DbTest2() {
		try {
			properties.load(new FileInputStream("/work/sou/java7/src/db/pack/test.properties"));//c: 를 빼면 자원재활용
			
			Class.forName(properties.getProperty("driver"));
			conn = DriverManager.getConnection(
					properties.getProperty("url"),
					properties.getProperty("user"),
					properties.getProperty("passwd"));
			stmt = conn.createStatement();
			
			//insert : 기본은 auto commit
			//String spl = "insert into sangdata values(4, '우산', 1, 5000)";
			//stmt.executeUpdate(spl); //select문 이외의 실행은 executeUpdate
			   //conn.rollback(); 이미 커밋이 먹었기때문에 롤백 안먹는다
			
			
			
			//transaction 트랜잭션 처리 -- 주로 은행 계좌 이체 따위의 작업에 효과적
			/*
			conn.setAutoCommit(false); //tr 트랜잭션 start  //원본데이터가 자동으로 갱신되지 않는다
			String spl = "insert into sangdata values(5, '장화', 3, 95000)";
			stmt.executeUpdate(spl);
			//conn.rollback();
			conn.commit();
			conn.setAutoCommit(true);  //tr 트랜잭션 end
			//----------------------------------------------------------------------
			*/
			
			//update
			String spl2 = "update sangdata set sang='향수', su=7 where code=4";
			int re = stmt.executeUpdate(spl2);
			if(re > 0) //쿼리문 의 반환값은 true 1이상 아니면 false 0 (1행이 추가되었습니다)//insert update delete//////////////////////
				System.out.println("수정 성공");
			else
				System.out.println("수정 실패");
			
			//delete 
			String sql3 = "delete from sangdata where code = 4";
			re = stmt.executeUpdate(sql3);
			if(re > 0) //쿼리문 의 반환값은 true 1이상 아니면 false 0 (1행이 추가되었습니다)//insert update delete//////////////////////
						//예외처리는 예외만 잡는다 신텍스에러 런타임에러.
				System.out.println("삭제 성공");
			else
				System.out.println("삭제 실패");			
			
			//select 
			rs = stmt.executeQuery("select * from sangdata order by code desc");
			int cou = 0;
			while(rs.next()){
				System.out.println(
						rs.getString("code") + " " +
						rs.getString("sang") + " " +
						rs.getString("su") + " " +
						rs.getString("dan") + " " 
						);
				cou++;
			}
			System.out.println("건수 : " + cou);
			
		} catch (Exception e) {
			System.out.println("process err:" + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
			}
		}
	}

	public static void main(String[] args) {
		new DbTest2();

	}

}



