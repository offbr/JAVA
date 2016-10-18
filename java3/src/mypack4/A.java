package mypack4;

public class A { //바깥쪽 클래스
	int field1; //캡슐화 쓸 수 있으면 최대한 써줘야한다. private //멤버필드
	
	public A() { //생성자
		System.out.println("A 객체 생성***");
	}

	void method1(){ //멤버 메소드 (인스턴스메소드)
		System.out.println("method1 수행: " + field1);
	}
	
	//인스턴스 멤버 클래스
	class B{ //중첩 클래스 / 내부 클래스
		public int field2;
		
		public B() {
			System.out.println("B Object 생성***");
		}
		
		void method2(){ //멤버 메소드 (인스턴스메소드)
			System.out.println("method2 수행: " + field2);
		}	
	}
	
	//정적 멤버 클래스
	static class C{
		int field3;
		
		public C() {
			System.out.println("C Object 생성***");
		}
		
		void method3(){ //멤버 메소드 (인스턴스메소드)
			System.out.println("method3 처리: " + field3);
		}
	}
	
	void a_method4(){ //A 클래스의 메소드
		System.out.println("method4가 진행 됩니다");

		int a = 11; // local variable
		//local class 로컬 클래스
		class D{
			int field4;
			
			public D() {
				System.out.println("D Object 생성***");
			}
			
			void method4(){ //멤버 메소드 (인스턴스메소드)
				System.out.println("method3 처리: " + field4);
			}
		}
		D d = new D(); //메소드 내에서의 객체생성
		d.field4 = 4;
		d.method4();
	}
	
	//허용범위 ------------------------------
	B b2 = new B(); //A클래스의 멤버필드로 객체생성 
	C c2 = new C();
	//D d2 = new D(); err 메소드안에 클래스라 메소드4가 수행될때만 객체생성가능

	void test1(){ //메소드내에서 객체생성
		B b3 = new B(); //non static //일반
		C c3 = new C(); //static
	}
	
	static C c4 = new C();
	
	static void test2(){
		//B b4 = new B(); //non static //일반 //err : 일반
		C c5 = new C(); //static
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("바깥쪽 클래스 객체 생성 -------------------");
		A a = new A();
		a.field1 = 1;
		a.method1();
		
		System.out.println("\n인스턴스 멤버 클래스 객체 생성 ------------");
		//B b = new B(); err : B에 접근할 수 없다 내부클래스.
		A.B b = a.new B();
		b.field2 = 2;
		b.method2();
		
		System.out.println("\n정적 멤버 클래스 객체 생성 ----------------");
		//C c = new C();
		A.C c = new A.C();
		c.field3 = 3;
		c.method3();
		System.out.println();
		C c2 = new C();
		c2.field3 = 4;
		c2.method3();
	
		System.out.println("\n로컬 클래스 객체 생성 ---------------------");
		a.a_method4(); //메소드내의 메소드 실행
		
		System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		a.test1();
		A.test2();
		
		a.b2.method2();
		c2.method3();
		
		
	}	

}


















