package mypack4;

public class FlyerBird implements Flyer{
	public FlyerBird() {
		System.out.println("새~~~~");
	}
	
	@Override
	public void fly() {
		System.out.println("날개를 휘 저으며 날아감");
	}
	
	@Override
	public boolean isAnimal() {
		return true;
	}

}
