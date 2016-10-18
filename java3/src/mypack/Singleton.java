package mypack;

public class Singleton {
	int kor = 10;
	
	//singleton pattern
	private static Singleton singleton = new Singleton();
	public static Singleton getInstance(){ //메소드 이름은 정해져있지않다 위에서 쓰는 메소드 이름.
		return singleton; //주소값 복수개의 변수에 하나의 주소값만 리턴한다. //return = new Singleton();
	}
}
