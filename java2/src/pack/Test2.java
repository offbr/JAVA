package pack;

public class Test2 {

	public static void main(String[] args) {
		//기본 boolean //정수 int //실수 double //문자열 String 
		// 산술 연산자 
		int a = 5; // = 치환 연산자, 대입 연산자
		int b = 3; 
		int c = a + b;
		
		System.out.println(a + b);
		System.out.println(a - b);
		System.out.println(a * b);
		System.out.println(a / b); // 몫
		System.out.println(a % b); // 나머지
		System.out.println(a / (double)b); //몫과 나머지
		
		System.out.println(a / 0.0); //Infinity무한대
		System.out.println(a % 0.0); //NaN
		
		System.out.println(3 + 4 * 5); // 산술연산자 우선순위 // * / 가 + - 보다 우선순위 높다  // 같을 경우 왼쪽부터
		System.out.println((3 + 4) * 5); // ()둘러주면 취우선 순위가 된다
		
		String ss1 = "대한";
		String ss2 = "민국";
		String ss3 = ss1 + ss2; //문자열 더하기 // 두가지 기능 (숫자 , 문자)
		System.out.println(ss3);
		System.out.println(ss3 + " " + 82); //숫자는 문자화 //문자열과 합하면 숫자는 문자가 된다
		System.out.println(ss3 + " " + (80 + 2)); //() 를 둘러줘서 우선순위를 주면 문자열로 바뀌기전에 연산한다
		String ss4 = "5" + 6;
		System.out.println(ss4);
		int ia = Integer.parseInt("5") + 6; // Integer.parseInt 문자를 숫자화
		System.out.println(ia);
		String ss5 = Integer.toString(5) + 6;// Integer.toString 숫자를 문자화
		System.out.println(ss5);
	
		//누적
		System.out.println();
		int no = 1;
		no = no + 1;
		no += 1;  // 권장
		no++; //후위 증감연산자  --
		++no; //전위
		System.out.println(no);
	
		//증감 연산자에 관하여 //전위 후위 //증감 연산자는 다른연산자와 같이 쓰지 않는다.
		System.out.println();
		int imsi = 5;
		int result = ++imsi + 1; //전위는 늘어나고 연산 //다른 연산자를 만났을때
		System.out.println(imsi);
		System.out.println(result);
		
		int imsi2 = 5; 
		int result2 = imsi2++ + 1; //후위는 연산 하고 늘어남 //다른 연산자를 만났을때
		System.out.println(imsi2);
		System.out.println(result2);
		
		//부호에 관하여
		System.out.println();
		int imsi3 = 3;
		System.out.println(imsi3);
		System.out.println(-imsi3); // -를 주거나 -1을 주면 부호가 바뀐다
		System.out.println(imsi3 * - 1);
		
		System.err.println();
		
		int i = 1;
		int j = 1;
		for(i = 2; i < 10; i++){
			for(j = 1; j < 10; j++)
			System.out.println(i + "*" + j + " = " + (i*j));
		}
								
	}

}
