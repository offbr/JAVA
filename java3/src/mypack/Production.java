package mypack;

/* 문제) 
클래스명 : Production
멤버 : 
+ name (문 : public)  +:public -:private
- price (수 : private) //생성자로 값주입
- productionDay (문 : private) //생산일 //셋터로 값주입
+ display() : 상품명, 가격 , 생산일 출력		
*/		

public class Production {
	public String name;
	private int price;
	private String productionDay; 
	public static String origin;
	
	public Production(){
		System.out.println("생성자");
	}
	
	public Production(String name){
		this.name = name;
	}
	
	public Production(String name, int price){
		this.name = name;
		this.price = price;
	}
	
	public Production(String name, int price, String productionDay){
		this.name = name;
		this.price = price;
		this.productionDay = productionDay;
	}
	
	public void display(){
		System.out.println("상품명: " + name + ", 가격: " + price + ", 원산지: " + origin + ", 생산일: " + productionDay);
	}
	
	public void display(int price){
		this.price = price;
	}
		
	public void setProductionDay(String productionDay){
		this.productionDay = productionDay;
	}
	public String ProductionDay(){ //getter //접근지정자뒤에 타입 리턴값을 줄수있다 //캡슐화
		return productionDay; //this를 안써줘도 된다 지역변수age 찾고 없으면 멤버필드를 찾는다. 그러므로
	}
	
}

