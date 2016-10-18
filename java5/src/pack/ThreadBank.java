package pack;

public class ThreadBank { //추상화
	private int money = 10000; //공유 자원
	
	public void setMoney(int money) { //캡슐화
		this.money = money;
	}
	
	public int getMoney() {
		return money;
	}
	
	public synchronized void savedMoney(int mon){ //입금
		int m = getMoney();
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("err: " + e);
		} 
		setMoney(m + mon);
	}
	// synchronized = 자원의 공유 (안할경우 자원이 따로 따로 ..) 
	public synchronized void minusMoney(int mon){ //출금 
		int m = getMoney();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println("err: " + e);
		} 
		setMoney(m - mon);
	}
}
