package mypack;

import java.util.Calendar;

public class SingletonMain {

	public static void main(String[] args) { //Singleton 패턴 하나의 객체에 복수개의 변수를 담는다. 
		Singleton s1 = new Singleton(); //new 하면 객체가 생성
		Singleton s2 = new Singleton();
		System.out.println(s1 + " " + s2); //8.9line toString은 생략되어있을뿐 같은뜻.
		System.out.println(s1.toString() + " " + s2.toString());//8.9line 같은뜻.
		System.out.println("kor:" + s1.kor);
		
		System.out.println();
		Singleton s3 = Singleton.getInstance();
		Singleton s4 = Singleton.getInstance();
		System.out.println(s3 + " " + s4);
		System.out.println("kor:" + s3.kor); //같은 인스턴스의 kor을 찍는다
		System.out.println("kor:" + s4.kor); //같은 인스턴스의 kor을 찍는다
		s3.kor=20; //인스턴스변수 kor의 값을 바꾼다.
		System.out.println("kor:" + s3.kor); //같은 인스턴스의 kor을 찍는다 //같은객체 주소값동일
		System.out.println("kor:" + s4.kor); //같은 인스턴스의 kor을 찍는다 //같은객체 주소값동일
		System.out.println("kor:" + s1.kor); //다른 인스턴스의 kor을 찍는다 // 다른객체다. 주소값다름
		
		System.out.println(); //java내부에 singleton 패턴
		//Calendar calendar = new Calendar(); 이렇게 하지 않는다
		Calendar calendar = Calendar.getInstance(); //위와 동일하지만 이 방식으로 객체를 생성한다.
		int y = calendar.get(calendar.YEAR); // year는 대문자 누워있으므로 final static
		System.out.println("년도는 " + y + "년");
		Calendar calendar2 = Calendar.getInstance(); //singlenton은 방식으로 객체를 생성한다.
		int m = calendar2.get(calendar2.MONTH) + 1; // MONTH는 대문자 누워있으므로 final static 
		System.out.println("년도는 " + m + "월");
		
		//System.out.println(calendar + " " + calendar2.toString()); //주소는 동일하다.
	}

}
