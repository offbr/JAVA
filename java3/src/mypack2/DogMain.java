package mypack2;

public class DogMain {

	public static void main(String[] args) {
		/*
		Dog dog = new Dog();
		dog.print();
		System.out.println(dog.callName());
		//원형 클래스의 성격상 스스로는 객체를 만들어 사용하지 않는 것이 일반적. (공통사항 공유클래스)
		*/
		
		HouseDog hd = new HouseDog("집개");
		hd.show();
		hd.print();
		System.out.println(hd.callName());
		
		System.out.println("---------------");
		WolfDog wd = new WolfDog("늑대");
		wd.print();
		wd.display();
		System.out.println(wd.callName());
		
		System.out.println();
		//WolfDog bushdog = hd; // err: type mismatch // 타입이 다르다. 울프타입에 하우스타입 치환은 안된다.
		WolfDog bushdog = wd; //주소값 전달 //type이 같으므로 주소 치환(대입)
		bushdog.print();
		bushdog.display();
		System.out.println(bushdog.callName());
		
		System.out.println("=================================================");
		Dog dog1 = new Dog(); //원형클래스라 할 필요 없지만 원리를 보여주려고 만듬..
		dog1.print();
		
		System.out.println(); //자식의 주소를 부모의 주소에게 대입하는건 가능, 반대의경우 강제캐스팅.
		//hd = dog1; //err: type mismatch //반대의경우라 불가 . 자식의 객체변수에 부모의 객체변수를 치환 불가. 
		dog1 = hd;  //부모 객체변수에 자식의 객체변수 주소치환 가능 (promotion)
		dog1.print(); //부모타입의 객체변수이름으로 자식의 메소드를 호출할수있다 단 overriding 된 메소드만 호출 가능.
		//dog1.show(); //자식 고유의 메소드는 호출 불가. "불간섭의 원칙"
		
		System.out.println();
		dog1 = wd; //부모객체변수에 자식객체변수 주소 치환
		dog1.print();
		//dog1.display(); //부모타입의 객체변수이름으로 자식의 메소드를 호출했지만 overriding이 되지않아 호출불가.
		//부모객체변수이름으로 같은 Statement로 호출했지만 결과값은 다르다. 다형성.////////////////////////////// 
		
	}

}
























