package mypack2;

public class PolyCar {
	protected int speed = 50;
	
	public PolyCar() {
		System.out.println("나는 자동차야~~~~~~~~~~~~");
	}
	
	public void dispData() {
		System.out.println("속도: " + speed); 
	}
	
	public int getSpeed() {
		return speed;
	}
}
