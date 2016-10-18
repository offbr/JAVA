package mypack;

import java.util.concurrent.SynchronousQueue;

public class BankMain { //객체는 오브젝트를 참조

	public static void main(String[] args) {
		//Bank와 같은 package
		Bank tom = new Bank();
		//System.out.println(tom.imsi);
		//System.out.println(tom.imsi2);
		System.out.println("tom 잔고: " + tom.getMoney());
		tom.deposit(5000); //입금
		tom.withDraw(3000); //출금
		System.out.println("tom 잔고: " + tom.getMoney());
		
		System.out.println(); 
		
		Bank oscar = new Bank(2000);
		System.out.println("oscar 잔고: " + oscar.getMoney());
		oscar.withDraw(1000); //출금
		oscar.deposit(6000); //입금
		System.out.println("oscar 잔고: " + oscar.getMoney());	
		// tom, oscar는 타입(멤버필드,멤버메소드)은 같으나 상태는 다르다. (다른 기억장소)
		
		System.out.println("\n객체의 주소 관련 이야기 ----------------");
		System.out.println("tom 의 주소: " + tom); //인스턴스 주소(타입과 주소(헥사값 16진수))//실제주소는 아님
		System.out.println("tom 의 주소: " + tom.hashCode()); //헥사값을 10진수로
		System.out.println("oscar 의 주소: " + oscar); //인스턴스 주소(타입과 주소(헥사값 16진수))//실제주소는 아님
		System.out.println("oscar 의 주소: " + oscar.hashCode()); //헥사값을 10진수로
		// tom, oscar는 타입(멤버필드,멤버메소드)은 같으나 상태는 다르다. (다른 기억장소)
		
		Bank james = null; 
		System.out.println("james 의 주소: " + james); //null 상태 참조할수없다.
		//System.out.println("james 잔고: " + james.getMoney());//NullPointerException 객체(인스턴스)참조하지 못함//Null
		int a = 5; int b = a; int c= 10;//값 전달 // 타입이 같아야 치환할수있다.
		james = oscar; //oscar의 주소를 james에 치환; // oscar의 주소를 james가 참조. 같은 주소를 두 타입이 참조.
		//oscar = tom; //tom의 객체(인스턴스) 주소를 oscar도 참조; 
		System.out.println("james 의 주소: " + james); //같은 객체를 가리킨다.
		System.out.println("oscar 의 주소: " + oscar); //같은 객체를 가리킨다.
		System.out.println("james 잔고: " + james.getMoney());
		
		//if(a == b)
		if(a == c)  //기본형 //기본형값 비교는 == 를 쓴다.
			System.out.println("같은 값");
		else
			System.out.println("다른 값");
			
		//if(james == oscar)
		if(james == tom) //참조형
			System.out.println("같은 주소");
		else
			System.out.println("다른 주소");
	
		if(james.equals(oscar)) //참조형 주소를 비교할땐 == 써도 되지만 equals(obj) 를 추천. // 
		//if(james.equals(tom)) //참조형값 주소 비교는 equals(obj) 쓴다. //String은 무조건 equlas() heap안에 영역이 다르다. 
			System.out.println("같은 주소2");
		else
			System.out.println("다른 주소2");
		
		System.out.println("\nString 클래스의 비교에 대해여 ----------------");
		//String class 참조형이지만 기본형처럼 쓴다. C는 없다.
		//문자열을 위해 나온 특별한 클래스
		String ss1 = "kor"; 
		String ss2 = new String();  //ss2는 new를 썼지만 ss2에 따로 kor를 따로 대입을 해줬기때문에 ss1과 동일
		ss2 = "kor";
		String ss3 = new String("kor"); // new연산자 오버로딩 따른 객체 생성처럼 heap 영역에 생성되서 참조한다
		// new 연산자를 안쓰고 쓸경우 heap영역안에 literal Pool영역에 생성된다. new연산자 오버로딩 주소와는 다른 주소;. 
		System.out.println(ss1 + " " + ss2 + " " + ss3);
		
		if(ss1 == ss2)
			System.out.println("문자열 같다");
		else
			System.out.println("문자열 다르다");
		
		if(ss1 == ss3)
			System.out.println("문자열 같다");
		else
			System.out.println("문자열 다르다");
		
		if(ss1.equals(ss2)) //문자열 비교는 무조건 equals() 쓴다 주소비교는 불가능.
			System.out.println("문자열 같다");
		else
			System.out.println("문자열 다르다");
		
		if(ss1.equals(ss3)) //문자열 비교는 무조건 equals() 쓴다 주소비교는 불가능.
			System.out.println("문자열 같다");
		else
			System.out.println("문자열 다르다");
		
		System.out.println("ss1 의 주소: " + ss1.hashCode());
		System.out.println("ss2 의 주소: " + ss2.hashCode());
		System.out.println("ss3 의 주소: " + ss3.hashCode());
		
	/*		
  		최초 문자열 생성시에, 문자열을 constant pool(상수풀)에 보관을 합니다. 이 상수풀에 보관된 문자열을
		다음에 스트링 인스턴스를 생성하기 전에 JVM에서 비교해 주는 것이지요~

		여기서 중요한건 생성자를 이용하여 생성할시에는 constant pool에 보관을 안하고, 리터럴을 이용하여 스트링 생성시에 
		constant pool에 보관되는 효과를 보여준다는 점입니다.. // literal Pool영역에 보관.
	*/
	
		System.out.println("\n배열 관리 -----------------------");
		int[] ar = {1,2,3}; //객체 ar = 객체(참조)변수 //1차원 배열
		System.out.println(ar); //헥사값 16진수 주소 //객체의 주소를 가지고 있다
		System.out.println(ar[0] + " " + ar[1] + " " +ar[2]);
		
		System.out.println();
		int[][] ar2 = {{1,2,3},{4,5,6,7}}; //객체 //2차원배열  
		System.out.println(ar2); //헥사값 16진수 주소 //객체의 주소를 가지고 있다 
		System.out.println(ar2[0]);
		System.out.println(ar2[1]);
		System.out.println(ar2[0][0]);
		System.out.println(ar2[1][0]);
		
	}
}

/*
	UI = UserInterface 사용자와 컴퓨터 사용자와 프로그램 소통(Interface)
		UI 1.하드웨어적인 측면
			컴퓨터의 파워, 스피커 등등
			
		UI 2.소프트웨어적인 측면
			프로그램의 메뉴, 버튼, 체크박스 등등
	
	API (Application Programming Interface)
		응용프로그램 프로그래밍 인터페이스 
			개발자를 위한 Interface(설명서)
	
	예: JavaAPI 

*/






















