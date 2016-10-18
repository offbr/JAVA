package pack;

public class ThreadTest1 extends Thread{
	
	public ThreadTest1() {
		
	}
	
	public ThreadTest1(String name) {
		super(name);
	}
	
	public void run(){
		for (int i = 0; i <= 50; i++) {
			System.out.println(i + " " + super.getName());
		}
	}
	
	public static void main(String[] args) {
		try {
			//process 단위의 실행
			/*
			Process p1 = Runtime.getRuntime().exec("calc.exe");
			Process p2 = Runtime.getRuntime().exec("notepad.exe");
			p1.waitFor(); //모든내용을 다 함께 닫는다.
			p2.destroy(); //본인 것만 닫는다.
			System.out.println("p1: " + p1.exitValue());//정상종료는 0 //비정상종료는 1 
			System.out.println("p2: " + p2.exitValue());
			*/
			//Thread를 사용하지 않은 경우 
			/*
			ThreadTest1 t1 = new ThreadTest1();
			ThreadTest1 t2 = new ThreadTest1();
			t1.run();
			System.out.println();
			t2.run();
			*/
			
			//Thread를 사용한 경우 //random
			
			//ThreadTest1 t1 = new ThreadTest1();
			//ThreadTest1 t2 = new ThreadTest1();
			ThreadTest1 t1 = new ThreadTest1("one");
			ThreadTest1 t2 = new ThreadTest1("two");
			t1.start();
			t2.start();
			//t2.setPriority(8);
			t2.setPriority(MAX_PRIORITY); // setPriority 우선순위
			t1.join(); // join 사용자 정의 스레드가 종료 될 때까지 메인 스레드 대기 
			t2.join();
			Thread.yield(); // 대기
		} catch (Exception e) {
			System.out.println("err: " + e);
		}
		
		System.out.println("프로그램 종료");

	}

}
