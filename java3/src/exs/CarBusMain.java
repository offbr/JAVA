package exs;

public class CarBusMain {

	public static void main(String[] args) {
		Car car = new Car();
		//Car car = new Car("현대", "제네시스", "화이트");
		car.display();
		car.setHumansu(4);
		car.display();
		System.out.println("슬로건: " + car.slogan());
		System.out.println("--------------");
		Bus bus = new Bus();
		bus.display();
		bus.setHumansu(12);
		bus.display();
		System.out.println("슬로건 " + bus.slogan());
		System.out.println(bus.getHumansu());
		
		System.out.println("==================");
		Car car2 = new Car("혼다", "빠른차", "검정");
		car2.display();
		Bus bus2 = new Bus();
		bus2.display();
		bus2.setHumansu(5);
		bus2.display();
		car2.display();
		
		
		
	}

}
