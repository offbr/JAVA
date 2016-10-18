package mypack3;

public abstract class Anidog extends Animal{
	@Override //추상화 해서 new 할수없으며 수퍼클래스로서 활용만 가능하다
	public String name() {
		return "개";
	}
}
