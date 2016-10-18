package mypack;

public class CarMain {

	public static void main(String[] args) {
		//응용 프로그램 시작을 위한 클래스
		int a = 10;
		System.out.println(a);
		
		System.out.println();
		Car sonata = new Car(); //객체가 생성(instance 인스턴스화) //설계도에 의해 오브젝트(객체)를 만듬(메모리확보)
		//sonata : 객체변수, 참조변수, 인스턴스변수 ... 
		//Car sonata2 = new Car();
		sonata.abc();
		System.out.println();
		
		//멤버필드 출력
		System.out.println(sonata.wheel);
		//System.out.println(sonata.airBag);
		sonata.setAirBag(8, 122); // setter : private 멤버에 값 추가
		sonata.abc();
		System.out.println(sonata.kor("리턴"));
		System.out.println(sonata.getAirBag());// getter : private 멤버에 값 얻기
		
	}

}
