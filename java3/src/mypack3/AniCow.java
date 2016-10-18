package mypack3;

public class AniCow extends Animal{
	@Override
	public String name() {
		return "소";
	}
	
	@Override
	public String eat() {
		return "여물";
	}
	
	@Override
	public String action() {
		return "낮";
	}
}
