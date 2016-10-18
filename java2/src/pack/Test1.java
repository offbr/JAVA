package pack;

public class Test1 {
	public static void main(String[] ar){
		System.out.println("성공");
		System.out.println("단축키를 연습하자");
		System.out.println();
		System.out.print("자바");
		System.out.print("변수"); // 커서가 해당라인에
		System.out.println("연습"); // ln커서를 다음라인으로
		
		//한 줄 주석
		System.out.println("한 줄"); //여기서부터 행의 끝까지 주석 	ctrl+/
		/* 
		 여러 줄 
		 주석 	ctrl+shift+/	
		 */
		
		//상수를 기억하고자 기억장소를 확보하고 기억장소에 이름을 부여 : 변수
		//기본형 bcbsilfd - 상수를 기억하는 자료형 //기본형!!!! 정수는 int, 실수는 double
		//String : 문자열 기억장소 : 기본형 처럼 사용하나, 사실은 객체 변수 //특별
		//참조형 : 객체 변수
		
		byte var1; //변수 선언형식 : 형(type) 변수명 // -128 ~ 127  까지 //1바이트
		var1 = 10;
		var1 = 5;
		
		System.out.println(var1);
		
		short var2 = 32767; // -32768 ~ 32767까지 //2바이트
		System.out.println(var2);
		
		int var3 = 2147483647; // -2147483648 ~ 2147483647 //4바이트
		System.out.println(var3);
		
		long var4 = 2147483648L; // 8바이트 // long형으로 선언후 L이 붙지 않으면 int형 으로 됨 4바이트  
		long var5 = 10L;
		System.out.println(var4);
		
		//promotion : 자동 형변환, casting : 강제 형변환 //중요
		byte b1 = (byte)128;
		System.out.println(b1);
		byte b2 = 12; //promotion
		byte b3 = b2;
		System.out.println(b3);
		
		short s1 = 10; // s1 = (short)10;
		int i1 = s1; //promotion 자동 형변환 //작은장소에서 큰장소에 줄때
		System.out.println(i1);
		short s2 = (short)i1; //casting 강제 형변환 //큰장소에서 작은장소에 줄때
		System.out.println(s1);
		
		System.out.println("실수 처리 ------------------");
		double d1 = 10.0;
		d1 = 5; //정수는 실수형 기억장소에 promotion 자동형변환 됨
		System.out.println(d1);
		d1 = 56.7;
		
		float f1 = 3.5F; // float f1 = 3.5; 실수는 기본적으로 double// double 8바이트에서 float 4바이트에 넣으면 에러. 강제형변환해야함
		f1 = (float)3.5;
		System.out.println(f1);
		
		int i2 = (int)3.7; // 정수만 취함, 버림, 강제형변환
		System.out.println(i2);
		
		//연산시 큰 타입으로 자동 변환됨
		//double result = 4.5 * 10; //45
		//double result = (int)4.5 * 10; //40 int는 소수점 버림
		//int result = 4.5 * 10; // 안됨
		//int result = (int)4.5 * 10; //40
		double dd = 5.5;
		int result = (int)4.5 * (int)dd; // 소수점 버림 4 * 5 = 20
		System.out.println("result : " + result);
		
		
		System.out.println();
		boolean bu1 = true;
		bu1 = false;
		System.out.println(bu1);
		
		System.out.println();
		char c1 = 'a';
//		c1 = 'abc'; 문자만 허용, 문자열은 안됨 
//		c1 = "a" 문자는 작은따옴표만 가능 문자열이 큰따옴표
		System.out.println(c1 + " " + (int)c1 + " " + (char)97); // 소문자 a는 아스키코드 97
		
		System.out.println("문자열 처리 : String 객체 - 참조형");
		String irum = "홍길동"; //문자열은 기본형은 아님
		System.out.println(irum);
		
		System.out.println(5/ 0.0);
	}
}
