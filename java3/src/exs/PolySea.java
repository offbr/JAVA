package exs;

public class PolySea extends PolyAnimal{
	protected String food = "물고기";
	
	public PolySea() {
		System.out.println("해상동물 생성자");
	}
	
	@Override
	public void disp() {
		System.out.println("물고기를 먹자");
		
	}
}
