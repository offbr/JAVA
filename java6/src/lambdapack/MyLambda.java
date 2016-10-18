package lambdapack;

public class MyLambda {
	class Inner1 implements MyInter{
		@Override
		public int addData(int a, int b) {
			return a+b;
		}
	}
	static class Inner implements MyInter{
		@Override
		public int addData(int a, int b) {
			return a+b;
		
		}
	}
	public static void main(String[] args) {
		Inner inner = new Inner(); //전통적 방식 (static)
		System.out.println(inner.addData(3, 4));
		
		MyLambda lam = new MyLambda(); // 인스턴스
		MyLambda.Inner1 aa = lam.new Inner1();
		aa.addData(3, 4);
		System.out.println(aa.addData(3, 4));
		
		System.out.println("Lambda"); 
		MyInter myInter = (a, b) -> a + b; //람다 방식 
		System.out.println(myInter.addData(3, 4));
		
		System.out.println();
		MyInter myInter2 = (x, y) -> x * y; 
		System.out.println(myInter2.addData(3, 4));
	}
}
