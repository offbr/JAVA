package pack;

public class ThreadPackWife extends Thread{
	@Override
	public void run() {
		ThreadTest3Main.myBank.minusMoney(3000);
		System.out.println("아내 출금 후 잔고: " + ThreadTest3Main.myBank.getMoney());
		
	}

}
