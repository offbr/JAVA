package mypack2;

public class WolfDog extends Dog{
	private String where = "산";
	
	public WolfDog(String name) {
		super(name);
	}
	
	@Override
	public void print() {
		System.out.println(getName() +" 너 어디 사니 ? " + where);
	}
	
	public void display(){
		print();
		this.print();
		super.print();
	}
}
