package etc;

class Fruits {
	public static final Fruits APPLE = new Fruits();
	public static final Fruits ORANGE = new Fruits();
	public static final Fruits BANANA = new Fruits();
}

enum Fruits2 { // 위와 같은 의미
	// APPLE, ORANGE, BANANA;
	APPLE("빨강"), ORANGE("오렌지"), BANANA("노랑");

	// private Fruits2(){
	// System.out.println("enum 생성자");
	// System.out.println(this);
	// }
	public String color;

	private Fruits2(String color) {
		this.color = color;
	}
	
	private String color2;
	
	public String getColor2() {
		return color2;
	}
	
	public void setColor2(String color2) {
		this.color2 = color2;
	}
}

public class EnumTest {

	public static void main(String[] args) {
		// enum class : final static 객체를 상수처럼 열거한 클래스
		Fruits type = Fruits.APPLE;

		if (type == Fruits.APPLE)
			System.out.println("사과야");
		else if (type == Fruits.ORANGE)
			System.out.println("오랜지야");
		else if (type == Fruits.BANANA)
			System.out.println("바나나야");

		System.out.println();
		Fruits2 type2 = Fruits2.APPLE;

		if (type2 == Fruits2.APPLE)
			System.out.println("사과야");
		else if (type2 == Fruits2.ORANGE)
			System.out.println("오랜지야");
		else if (type2 == Fruits2.BANANA)
			System.out.println("바나나야");

		System.out.println();
		Fruits2 type3 = Fruits2.APPLE;
		switch (type3) {
		case APPLE:
			System.out.println("사과야");
			break;
		case ORANGE:
			System.out.println("오렌지야");
		case BANANA:
			System.out.println("바나나야");
		default:
			break;
		}

		System.out.println();
		System.out.println(Fruits2.APPLE.color);
		System.out.println(Fruits2.ORANGE.color);
		System.out.println(Fruits2.BANANA.color);
		
		System.out.println();
		
		Fruits2.APPLE.setColor2("red");
		Fruits2.ORANGE.setColor2("orange");
		Fruits2.BANANA.setColor2("yellow");
		System.out.println(Fruits2.APPLE.getColor2());
		System.out.println(Fruits2.ORANGE.getColor2());
		System.out.println(Fruits2.BANANA.getColor2());
		
		System.out.println();
		for(Fruits2 f:Fruits2.values()){
			System.out.println(f);
		}
	}

}
