package pack;

public class ThreadPack extends Thread{
	@Override
	public void run() {
		ThreadTest3Main.myBank.savedMoney(5000);
		System.out.println("남편 입금 후 잔고: " + ThreadTest3Main.myBank.getMoney());
	}

}
