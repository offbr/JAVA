package pack;

import java.io.FileReader;

public class TryTest {
	public void test1(){
		try {
			int[] arr = {1,2,3};
			System.out.println(arr[0]);
			//System.out.println(arr[5]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("test1 메소드에서 에러: " + e);	
		}
	}
	public static void main(String[] args) throws Exception{ //예외처리 throws Exception //데이터와 관련된 것은 반드시 예외처리
		//데이터베이스 연동과 네트워크와 키보드, 파일처리등.
		//예외처리 하지 않을 경우 에러. //에러와 관련된건 Exception 
		//좋은 방법이 아니다 //잡아주자 (Try 사용)
		//FileReader fr = new FileReader("c:\\work\\abc.txt"); // \n
		int a = 10, b = 3;
		try{
			int re = a / b;
			System.out.println("re: " + re);			
			
			//TryTest test = null;
			TryTest test = new TryTest();
			test.test1();
		}catch (ArithmeticException e){
			System.out.println("에러: 0 으로 나누지마세요 ");
		}catch (NullPointerException e){
			System.out.println("참조오류: " + e.getMessage());
			System.out.println("오류: " + e);
			//e.printStackTrace();
		}catch (ArrayIndexOutOfBoundsException e){
			System.out.println("범위 오류: " + e);
		}catch (Exception e){//Exception이 첫번째에 있을경우 다 잡아버리므로 제일 나중에 있어야 한다
			System.out.println("나머지 모든 에러 감지: " + e);
		}finally {
			System.out.println("에러유무와 상관없이 반드시 수행");
		}
		
		System.out.println("종료");
	}
}
