package pack;

public class Test3 {

	public static void main(String[] args) {
		// 관계 연산자, 논리 연산자, 기타
		int a= 5;
		System.out.println(a > 3);
		System.out.println(a <= 3);
		System.out.println(a == 3);
		System.out.println(a != 3);
		
		System.out.println();
		// 논리 연산자
		int b = 10;
		System.out.println(a > 3 && b <= 10);
		System.out.println(a >= 3 && b == 5); // 둘다 true여야 true
		System.out.println(a > 6 || b < 10); // 하나라도 true면 true
		System.out.println(a > 6 || b < 20);
		System.out.println(a > 3 || b < 5); // 우선순위  산술 > 관계 > 논리순으로 계산
		
		System.out.println();
		// 시프트 연산자
		int ii = 8, ij; 
//		System.out.println(ii + " " + ij); // 지역변수는 초기화 필요 //메소드내에서 선언한 변수는 초기화 필요 
		System.out.println("ii: " + ii + " " + Integer.toBinaryString(ii)); //숫자와 문자열이 + 연산하면 숫자가 자동으로 문자화 한다 //10진수를 2진수 문자열로 표현
		
		ij = ii << 1; //좌측으로 1비트. 남는 우측 1bit는 0으로 채움
		System.out.println("ij: " + ij + " " + Integer.toBinaryString(ij));

		ij = ii >>> 2; //우측으로 2비트. 남는 좌측 0으로 채움 //좌측 <<<은 없다
		System.out.println("ij: " + ij + " " + Integer.toBinaryString(ij));
		
		System.out.println();
		//삼항 연산자
		int result = (ii <= 5)?100 : 100+50; // 변수 = (조건)?true:false 표기
		System.out.println(result);
		
		int x,y,z;
		x = y = z = 5;
		System.out.println(x + " " + y + " " + z);
		System.out.println();
		
		aa(); //메소드 호출
		System.out.println();
		bb(5);
		System.out.println();
		aa();
		System.out.println("처리 종료");
	} // main() 메소드 처리가 끝나면 프로그램이 종료

	public static void aa(){
		System.out.println("aa 메소드 수행");
	}
	public static void bb(int arg){ //()안에 인수, 인자, argument, parameter
		System.out.println("bb 메소드 수행");
		System.out.println(arg);
	}
	
}
