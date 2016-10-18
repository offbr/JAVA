package pack;

public class Test4 {
	public static void main(String[] args){
		//논리 연산자 처리 시 주의사항
		boolean a = false, b = false, c;
		c = a || b; //하나만 참이면 참
		System.out.println(c);
		c = a && b; //둘다 참이면 참
		System.out.println(c);
		
		System.out.println();
		boolean b1, b2;
		b1 = aa();
		System.out.println(b1);
		System.out.println(bb());
		
		System.out.println("\n======주의 할 점=======");
		//b2 = aa() || bb(); // 앞에서 참이면 뒤에 쪽은 참이든 거짓이든 에러든 수행하지 않는다.
		b2 = bb() || aa();
		System.out.println(b2);
		
		System.out.println();
		//b2 = aa() && bb();
		b2 = bb() && aa(); // 앞에서 거짓이면 뒤에 쪽은 참,거짓,에러 수행하지않는다.
		System.out.println(b2);
		
		System.out.println("\n======주의 점 해결 방법? (모두 수행을 원할시)======");
		b2 = aa() | bb();
		System.out.println(b2);
		b2 = bb() | aa();
		System.out.println(b2);
		
		System.out.println();
		b2 = aa() & bb();
		System.out.println(b2);
		b2 = bb() & aa();
		System.out.println(b2);
		
		
	}
	
	public static boolean aa(){ //return값  반환 타입이 같아야 한다.
		System.out.println("aa 출력");
		return true;
	}
	
	public static boolean bb(){
		System.out.println("bb 처리");
		return false;
	}
}