package mypack;

public class ExCoinIn {
	private int coin, cupCount, jandon; 

	/*	
  	public int calc(int coin, int cupCount){
		jandon = coin-cupCount*200;
		return jandon;
	}
	*/
	
	public void setCoin(int coin){ //setter 자동화 메소드명 약속.
		this.coin = coin; //private에 접근할 수 있다
		if(coin < 200){
			System.out.println("커피1잔에 200원입니다");
			System.exit(1);
		}	
		//calc();
	}
	
	public void setCupCount(int cupCount){ //setter 자동화 메소드명 약속.
		this.cupCount = cupCount; //private에 접근할 수 있다
		//calc();
	}

	
	public void calc(){
		jandon = coin-cupCount*200;
		if(coin/100-cupCount >= cupCount){
			System.out.println("커피" + cupCount + "잔과 잔돈" + jandon + "원");
		}else{
			System.out.println("다시 입력하세요");
		}
	}
	
}


















