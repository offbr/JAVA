package mypack4;

public class Outer {
	private int a;
	Inner inner;
	
	public Outer() {
		a = 10;
		inner = new Inner(); // 내부 클래스의 객체 생성
	}
	
	public void aa() {
		System.out.println("외부에 있는 aa 메소드");
		bb();
		System.out.println("a: " + a);
		//System.out.println( "Outer에 있는 a: " + a + ", Inner에 있는 ia: " + ia);
		System.out.println( "Outer에 있는 a: " + a + ", Inner에 있는 ia: " + inner.ia);
		//cc();
		inner.cc();
	}
	
	private void bb(){
		System.out.println("외부에 있는 bb 메소드");
	}
	
	class Inner{ //내부(중첩) 클래스
		private int ia;
		
		public Inner() {
			ia = 20;
		}
		
		public void cc(){
			System.out.println("내부에 있는 cc 메소드");
			System.out.println("내부에 있는 ia: " + ia + ", 외부에 있는 a: " + a);
			bb(); //내부 클래스는 외부클래스의 멤버를 자유롭게 쓸수있다 반대의 경우는 객체생성후 사용 가능 
		}
		
		class InnerInner{ //내부(중첩) 클래스는 다시 내부(중첩)클래스를 가질 수 있다 //흔하지 않다
			
		}
	}
	
	public static void main(String[] args) {
		Outer outer = new Outer();
		outer.aa();
		System.out.println();
		//Inner inner = new Inner(); err
		/* 비현실적.
		Outer.Inner inner = outer.new Inner();
		inner.cc();
		
		Outer.Inner.InnerInner inner2 = inner.new InnerInner();
		*/
		
	}
}
