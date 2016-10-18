package etc;

import java.sql.*; // import sql

public class Ex {
	
	public static void main(String[] args) {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		String url = null;
		String id = "scott";
		String pw = "tiger";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //1단계 : JDBC 드라이버로드 
			System.out.println("driver load success"); //OracleDriver 클래스가 있는지 확인.
			try {
				url = "jdbc:oracle:thin:@localhost:1521:orcl"; //ip:127.0.0.1, port1521 접속한다.
				conn = DriverManager.getConnection(url, id, pw); //2단계 : DB Connection
				System.out.println("db connect success"); //DB의 커넥션을 가져온다(url, id, pw 입력)
				//쿼리를 처리하기 위하여 Statement 생성
				stmt = conn.createStatement();
				//다음 쿼리문을 실행하여 결과 집합 가져온다.
				String sql1 = "select * from buse";
				rs = stmt.executeQuery(sql1);
				//레코드를 하나씩 이동.
				System.out.println("부서"+",이름 "+",주소"+",전화번호");
				while(rs.next()){ //1,2,3,4 애트리뷰트값을 화면에 출력
					String no = rs.getString(1);
					String name = rs.getString(2);
					String loc = rs.getString(3);
					String tel = rs.getString(4);
					System.out.println(no+ "," + name + "," + loc + "," + tel);
				}
				//Statement 와 DB 커넥션을 닫는다 (쿼리를 처리 한 후 반드시 커넥션까지 같이 닫아준다.)
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				System.out.println(e); // DB관련 
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e); // 라이브러리관련.
		}
		
	}
}
