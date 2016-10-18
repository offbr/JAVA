package exs;

public class PolyLand extends PolyAnimal{
	protected String food = "고기";
	
	public PolyLand() {
		System.out.println("육지동물 생성자");
	}
	
	@Override
	public void disp() {
		System.out.println("고기를 먹자");
	}
}
