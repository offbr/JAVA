package mypack;

public class Bank {
	private int money = 1000; //Bank내에서만 참조가능
	int imsi = 1;  // 같은 package내에서만 public 참조가능  
	public int imsi2 = 2; // 같은 package내에서는 참조가능 //project만 같으면 어디서든 호출가능
	
	public Bank(){
		
	}
	
	public Bank(int money){ 
		// 생성자 오버로딩 했을때 오버로딩생성자만 호출하면 에러안남 
		// 그러나 기본생성자(argument없는)를 호출하면 에러 
		// 그럴땐 기본생성자를 직접 만들어야한다.
		this.money += money;
	}
	
	public void deposit(int amount){ //입금
		if(amount > 0) money += amount;
	}

	public void withDraw(int amount){ //출금
		if((amount > 0) && (money - amount >= 0)){
			money -= amount;
		}else{
			System.out.println("출금액이 너무 많습니다");
		}
	}
	
	public int getMoney(){
		return money;
	}
	
	
}










//