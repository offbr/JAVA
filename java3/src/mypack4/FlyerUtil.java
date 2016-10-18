package mypack4;

public class FlyerUtil {
	public static void showData(Flyer f){
		f.fly();
		System.out.println("동물인가요?" + f.isAnimal());
	}
}
