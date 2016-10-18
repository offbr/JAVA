package exs;

public class Bus extends Car{
	//public int humansu;
	
	public Bus() {
		super();
		System.out.println("bus 생성자");
	}
	
	@Override
	public int getHumansu() {
		return humansu;
	}
	
	@Override
	public String slogan() {
		return "자나깨나 빠른운전";
	}
}
