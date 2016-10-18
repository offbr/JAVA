package exs;
class c1{
	static void class_method(){
		
	}
}
public class PolyMain {

	public static void main(String[] args) {
		PolyLand land = new PolyLand();
		land.disp();
		System.out.println(land.food);
		PolySea sea = new PolySea();
		sea.disp();
		System.out.println(sea.food);
		c1.class_method();
		c1 c = new c1();
		c.class_method();
		
		
	}

}
