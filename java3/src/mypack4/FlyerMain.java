package mypack4;

public class FlyerMain {
	
	public static void main(String[] args) {
		System.out.println("속도는 " + Flyer.FAST);
		FlyerBird bird = new FlyerBird();
		
		System.out.println();
		System.out.println("속도는 " + Flyer.FAST);
		FlyerAirplane airplane = new FlyerAirplane();
		
		System.out.println();
		FlyerUtil.showData(bird);
		System.out.println();
		FlyerUtil.showData(airplane);
		
		System.out.println();
		FlyerBall ball = new FlyerBall();
		ball.fly();
		
		          
	}
}
