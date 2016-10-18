package mypack2;

public class PolyMain {

	public static void main(String[] args) {
		PolyCar car1 = new PolyCar();
		PolyBus bus1 = new PolyBus();
		PolyTaxi taxi1 = new PolyTaxi();
		
		System.out.println();
		car1.dispData();
		System.out.println(car1.getSpeed());

		System.out.println();
		bus1.dispData();
		bus1.show();
		System.out.println(bus1.getSpeed());
		
		System.out.println();
		taxi1.dispData();
		System.out.println(taxi1.getSpeed());
		
		System.out.println("-----------------------------------");
		PolyCar car2 = new PolyBus(); //가능 부모타입의 객체변수에게 자식의 인스턴스의 주소를 넘긴다. //프로모션 자동형변환
		car2.dispData(); //오버라이딩된 메소드 처리 //자식의 인스턴스의 주소를 부모 객체변수가 갖고 부모객체변수의 이름으로 자식의 오버라이딩된 메소드를 부른다.
		System.out.println(car2.getSpeed()); //부모가 가진 메소드 처리
		//car2.show(); // X: 자식 고유의 메소드
		
		System.out.println();
		PolyCar car3 = taxi1;
		car3.dispData();
		System.out.println(car3.getSpeed());
		
		System.out.println("***************************************");
		//PolyBus bus2 = new PolyCar(); //X type mismatch
		//PolyBus bus2 = car2; //자식의 주소를 가치키지만 타입은 부모타입 미스매치. 아래와같이 강제캐스팅.
		PolyBus bus2 = (PolyBus)car2; //부모타입이나 자식의 객체주소를 가지므로 캐스팅 가능
		bus2.dispData();
		bus2.show(); //car2는 원래 show 호출이 안됐었으나 원래타입으로 바뀐 후 호출가능.
		System.out.println(bus2.getSpeed());
		
		System.out.println();
		//PolyTaxi taxi2 = new PolyCar(); //x 부모의객체를 자식에게 치환하는 건 안된다. 불가
		//PolyTaxi taxi2 = car3; //부모타입이 자식의 객체주소를 가지고 있다 그걸 대입하려면 자식타입으로 캐스팅 아래와같이 
		PolyTaxi taxi2 = (PolyTaxi)car3; //부모타입이나 자식의 객체주소를 가지므로 캐스팅 가능
		//PolyTaxi taxi3 = (PolyTaxi)car1; // ClassCastException: 실행오류
		
		System.out.println("================================");
		PolyCar p[] = new PolyCar[3]; 
		p[0] = car1; 
		p[1] = bus1;
		p[2] = taxi1;
		for(int a = 0; a<p.length; a++){
			p[a].dispData();
		}
		
		System.out.println();
		for(PolyCar car:p){
			car.dispData();
		}

	}
}


































