package mypack;

public class PohamHandle {
	//회전을 위한 객체
	int quantity;  //회전량 
	
	public PohamHandle(){
		quantity = 0;
	}
	
	public String leftTurn(int quantity){
		this.quantity = quantity;
		return "좌회전";
	}
	
	public String rightTurn(int quantity){
		this.quantity = quantity;
		return "우회전";
	}
	
	public String straight(int quantity){
		this.quantity = quantity;
		return "직진";
	}
	
	public String parking(int quantity){
		this.quantity = quantity;
		return "없음, 주차";
	}
	
}














