package mypack3;

public class AnidogWolf extends Anidog{
	@Override //필수
	public String eat() {
		return null;
	}
	
	@Override //필수
	public String action() {
		return null;
	}
	
	@Override //option 해도되고 안해도 되고
	public void print() {
		super.print();
	}
}
