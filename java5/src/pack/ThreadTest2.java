package pack;

public class ThreadTest2 implements Runnable{
	
	public ThreadTest2() {
		
	}
	
	public ThreadTest2(String name) {
		Thread tt = new Thread(this, name);
		tt.start();
	}
	
	@Override
	public void run() {
		for (int i = 0; i <= 50; i++) {
			System.out.println(i+ " " + Thread.currentThread().getName());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("err: " + e);
			}
		}
	}
	
	public static void main(String[] args) {
		/*
		ThreadTest2 test1 = new ThreadTest2();
		ThreadTest2 test2 = new ThreadTest2(); // Runnable 을 구현 했을때
		Thread t1 = new Thread(test1); //Thread를 생성시켜줘야 한다. (start 를 Thread가 가지고 있음)
		Thread t2 = new Thread(test2);
		t1.start();
		t2.start();
		*/
		new ThreadTest2("하나");
		new ThreadTest2("두울");
		System.out.println("프로그램 종료");
	}
}
