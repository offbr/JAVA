package mypack;

public class PohamCar {
	//각종 부품 클래스를 조립해서 완성차 제작을 위해 설계도(클래스)
	//자원의 재활용
	int speed = 0;
	String ownerName, turnShow;
	//handle = new PohamHandle(); //생성자와 관계없이 객체 생성
	PohamHandle handle; //클래스를 멤버필드(클래스변수, 객체변수)로 선언 //이렇게 선언 할때는 생성자에서 객체만들어야함
	//생성자를 만들때 필요시 선택적으로 객체를 만들수 있다

	public PohamCar() {
		handle = new PohamHandle(); //클래스의 포함 관계 //생성자를 만들때 필요시 선택적으로 객체를 만들수 있다
		turnShow = "정지";
	}
	
	public PohamCar(String name) {
		ownerName = name;
		handle = new PohamHandle(); //클래스의 포함 관계
		turnShow = "정지";
	}
	
	public void turnHandle(int quantity){ //회전량을 받아서 클래스메소드 안에서 처리
		if(quantity > 0) turnShow = handle.rightTurn(quantity); //양수 우회전
		if(quantity < 0) turnShow = handle.leftTurn(quantity); //음수 좌회전
		if(quantity == 0) turnShow = handle.straight(quantity); //0 직진
		if(quantity == -100) turnShow = handle.parking(quantity); // -100 주차
	}
	
}
