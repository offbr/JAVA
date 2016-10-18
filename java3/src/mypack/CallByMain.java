package mypack;

public class CallByMain {

	public static void main(String[] args) {
		//메소드 호출 시 인자 전달 (기본형-call by value, 참조형-call by reference)
		CallBy1 my = new CallBy1();
		CallBy2 your = new CallBy2();
		System.out.println("원래 a: " + my.a + ", b: " + my.b);
		your.ex(my.a, my.b);
		System.out.println("1. 수행후 a: " + my.a + ", b: " + my.b);
		
		System.out.println();
		your.ex(my); // your.ex(data); data 객체타입 값을 줘야하며 주소를 넘긴다 
		System.out.println("2. 수행후 a: " + my.a + ", b: " + my.b); //주소를 넘겼기때문에 CallBy1의 값도 바뀐다.
		
		System.out.println();
		your.ex(my.c); //배열의 대표명은 곧 주소 주소값이 넘어갔다.
		System.out.println("2. 수행후 my.c[0]: " + my.c[0] + ", my.c[1]: " + my.c[1]); //주소를 넘겼기때문에 CallBy1의 값도 바뀐다.
		
	}

}
