package db.pack;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {
		// 중요정보는 소스 내에 기술하는 것이 아니라 별도의 파일 (***.properties)에 // properties에는 정보가 저장 되어 있다.
		// 저장해 두고 읽어야 한다.
		// Secure coding에 근거해서 소스 작성하자.
		Properties properties = new Properties();
		
		//read
		
		try {
			properties.load(new FileInputStream("c:/work/ex1.properties"));
			System.out.println(properties.getProperty("mes1"));
			System.out.println(properties.getProperty("mes2")); //key로 value를 불러온다.
		} catch (Exception e) {
			
		}
		
		//write
	/*
		try {
			properties.setProperty("mes1", "so good"); //key , value 형식으로 저장
			properties.setProperty("mes2", "홍길동");
			properties.store(new FileOutputStream("c:/work/ex1.properties"), null);
			System.out.println("저장 성공");
		} catch (Exception e) {
			System.out.println("저장 실패 :" + e);
		}
	*/

	}

}
