package mypack4;

public class FlyerAirplane implements Flyer{
	public FlyerAirplane() {
		System.out.println("비행기!!!");
	}
	
	@Override
	public void fly() {
		System.out.println("힘찬 엔진소리와 함꼐 구름속으로 사라짐");
	}
	
	@Override
	public boolean isAnimal() {
		return false;
	}
}
