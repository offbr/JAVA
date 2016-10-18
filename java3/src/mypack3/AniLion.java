package mypack3;

public class AniLion extends Animal{
	public AniLion() {

	}
	
	@Override
	public String name() {
		return "사자";
	}
	
	@Override
	public String eat() {
		return "고기";
	}
	
	@Override
	public String action() {
		return "밤";
	}
	
	@Override
	public void print() {
		System.out.println("사자라서 행복해!");
	}
	
	public void eatOther(){
		System.out.println("라이언 고유 메소드");
	}
}




















