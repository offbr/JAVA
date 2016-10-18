package lambdapack;

public class ThreadClass {

	public void sendData(String ss){
		System.out.println(ss + "자료 전송");
	}
	
	public ThreadClass() {
		m1();
		System.out.println();
		m2();
		System.out.println();
		m3();
		System.out.println();
		m4();
	}
	
	void m1(){
		new Thread(new Runnable() {
			public void run() {
				sendData("m1");
			}
		}).start();
	}
	
	void m2(){
		Thread thread = new Thread(() -> sendData("m2")); //람다
		thread.start();
	}
	
	void m3(){
		new Thread(() -> sendData("m3")).start(); //람다
	}
	
	void m4(){
		Runnable runnable = () -> sendData("m4");
		runnable.run();
	}
	
	public static void main(String[] args) {
		new ThreadClass();
	}
}
