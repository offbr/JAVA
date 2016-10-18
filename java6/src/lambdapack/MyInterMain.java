package lambdapack;

public class MyInterMain {

	public static void main(String[] args) {
		//ex1 : legacy 전통적인 방식
		MyInterNoArg interNoArg = new MyInterNoArg() { //오버라이드하기 위한 형식 //추상클래스는 new 할수없다
			@Override //내부무명클래스 
			public void abc() {
				System.out.println("인자가 없는 추상 메소드 오버라이드");
			}
		};
		interNoArg.abc();
		
		System.out.println();
		//ex1 : lambda
		MyInterNoArg interNoArg2 = () -> {System.out.println("인자 없는 메소드 람다로 구현");}; 
		interNoArg2.abc(); //argument없을경우 ()
		
		System.out.println();
		//ex1 : lambda 2
		MyInterNoArg interNoArg3 =  () -> { 
			System.out.println("람다식으로 표현");
			System.out.println("복수의 문장 수행");
			
		};
		interNoArg3.abc();
		
		System.out.println("\n인자가 있는 ....");
		//ex2 : legacy
		MyInterArg InterArg = new MyInterArg() {
			@Override
			public void def(int a, int b) {
				System.out.println("합은 " + (a + b));
			}
		}; 
		InterArg.def(5, 6);

		System.out.println();
		//ex2 : lambda 
		MyInterArg interArg2 = (a, b) -> System.out.println("합은 결과는 " + (a + b));
		interArg2.def(5, 6);
		/* 참고
		 MyInterArg interArg2 = (a) -> System.out.println(a); // 표현식의 {} 생략
		 MyInterArg interArg2 = a -> System.out.println(a); //인자의 갯수가 1이면 ()생략
 		 */
		
		System.out.println("\n인자가 있고 반환값이 있는 ....");
		//ex3 : legacy
		MyInterArgReturn interArgReturn = new MyInterArgReturn() {
			@Override
			public int def(int a, int b) {
				return a + b;
			}
		};
		int res = interArgReturn.def(3, 4);
		System.out.println(res);
		
		//ex3 : lambda
		//MyInterArgReturn interArgReturn2 = (a, b) -> {return a + b;};
		MyInterArgReturn interArgReturn2 = (a, b) -> {
			int k = 10;
			//...
			return a + b;
		};
		System.out.println(interArgReturn.def(3, 4));
		
		System.out.println();
		
		//MyInterArgReturn interArgReturn3 = (a, b) -> {return a - b;};
		MyInterArgReturn interArgReturn3 = (a, b) -> a - b;
		System.out.println(interArgReturn3.def(6, 4));
	}

}



























