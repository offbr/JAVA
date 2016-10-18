package mypack2;

public class Dog {
	//개과의 동물들이 공통적으로 갖는 멤버 선언한 원형 클래스
	private String name = "개";
	//private 에 값을 줄땐 생성자로 주던지 set get으로 줄수있다
	public Dog() {
		
	}
	
	public Dog(String name) {
		this.name = name;
	}
	
	public String getName() { 
		return name;
	}
	
	public String callName(){
		return "종류는 " + name;
	}
	
	public void print(){
		System.out.println(name + "는(은) 지구상에 산다.");
	}
	
	
	
	
}
