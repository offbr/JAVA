package pack;

public class ThreadTest3Main {
	//스레드 동기화 : 하나의 자원을 여러 스레드가 공유 가능
	public static ThreadBank myBank = new ThreadBank();
	
	public static void main(String[] args) {
		System.out.println("원금: " + myBank.getMoney());
		
		ThreadPack park = new ThreadPack();
		ThreadPackWife wife = new ThreadPackWife();
		
		park.start(); 
		park.setPriority(10);
		wife.start(); // synchronized를 안쓰면 자원공유를 안하고 따로 쓰기때문에 10000 + 5000;
	}
	
}
