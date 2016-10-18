package mypack2;

public class PolyTaxi extends PolyCar{
	private int passenger = 2;
	
	public PolyTaxi() {
		System.out.println("난 택시라고 해");
	}
	
	@Override
	public void dispData() {
		System.out.println("택시의 승객은: " + passenger);
	}
}
