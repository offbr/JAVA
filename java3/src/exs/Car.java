package exs;

public class Car {
	private String maker = "현대";
	private String name = "제네시스";
	private String color = "흰색";
	public int humansu;
	public Car() {
		System.out.println("car 생성자");
	}
	
	public Car(String maker, String name,String color) {
		this.maker = maker;
		this.name = name;
		this.color = color;
	}
	
	public String slogan(){
		return "자나깨나 안전운전";
	}
	
	public void setHumansu(int humansu) {
		this.humansu = humansu;
	}
	
	public int getHumansu() {
		return humansu;
	}
	
	public void display(){
		System.out.println("maker: " + maker + ",name: " + name + ",color: " + color +  ",탑승인원: " + humansu);
	}
}
