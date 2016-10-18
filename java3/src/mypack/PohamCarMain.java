package mypack;

public class PohamCarMain {

	public static void main(String[] args) {
		//클래스의 포함관계 / 자원의 재활용.
		//PohamCar tom = new PohamCar();
		PohamCar tom = new PohamCar("tom");
		System.out.println(tom.turnShow);
		
		System.out.println();
		tom.turnHandle(20);
		System.out.println(tom.ownerName + "의 회전량은 " + tom.turnShow + " " + 
							tom.handle.quantity); // tom.handle.quantity(handle의 멤버). .두개면 클래스의 포함관계
	
		System.out.println();
		tom.turnHandle(-100);
		System.out.println(tom.ownerName + "의 회전량은 " + tom.turnShow + " " + 
				tom.handle.quantity); // tom.handle.quantity(handle의 멤버). .두개면 클래스의 포함관계
		// tom이 handle , handle이 quantity
		
		System.out.println();
		tom.turnHandle(-10);
		System.out.println(tom.ownerName + "의 회전량은 " + tom.turnShow + " " + 
				tom.handle.quantity);
		
		System.out.println();
		PohamCar james = new PohamCar("james");
		System.out.println(james.turnShow);
		james.turnHandle(0);
		System.out.println(james.ownerName + "의 회전량은 " + james.turnShow + " " + 
				james.handle.quantity);

	}
}
