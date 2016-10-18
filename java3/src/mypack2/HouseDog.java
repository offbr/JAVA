package mypack2;

public class HouseDog extends Dog{
	private String where = "집";
	public HouseDog(String name) {
		super(name);
	}
	
	public void show(){
		System.out.println("사는 곳 : " + where);
	}
	
	@Override
	public void print() { //재정의
		System.out.println(getName() + ":" + where + "에 산다");
		System.out.println("House Dog에서 override한 print 수행됨");
	}
}
