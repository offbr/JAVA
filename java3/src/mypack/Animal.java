package mypack;
//package 이름은 도메인 이름을 보통 뒤집어서 쓴다 www.korea.com => com.korea.www 보통 3단계
public class Animal {
	private int leg = 4;
	private int age;
	private String name;
	public final static int MOUSH = 1;
	
	public Animal() {
		// 생략 가능 * 컴파일러가 생성해 줌
		System.out.println("기본 생성자");
	}
	
	public Animal(int leg) { //생성자 오버로딩
		this.leg = leg;
	}
	
	public Animal(String name) { //생성자 오버로딩
		this.name = name;
	}
	
	public Animal(String name, int age) { //생성자 오버로딩
		this(); //생성자는 생성자를 호출할 수 있다. 단, 가장 먼저 적어 주도록 한다/
		//this(10); //생성자는 또 다른 생성자 하나만 부를 수 있다
		this.name = name;
		this.age = age;
		
	}
	
	public void display(){
		System.out.println("leg: " + leg + ", age: " + age +", name: " + name);
	}

	public void display(int leg_num){ //method overload(loading) //재명명 //하나의 클래스에 같은이름의 메소드 여러개
		leg = leg_num;	//argument 갯수,타입,순서 다르면 method overloading 성립 
		System.out.println("leg: " + leg + ", age: " + age +", name: " + name);
	}
	
	public void display(String aname){ 
		name = aname;   //argument 갯수,타입,순서 다르면 method overloading 성립
		System.out.println("leg: " + leg + ", age: " + age +", name: " + name);
	}
	
	public void display(int nai, String aname){ 
		age = nai;   //argument 갯수,타입,순서 다르면 method overloading 성립
		name = aname;
		System.out.println("leg: " + leg + ", age: " + age +", name: " + name);
	}
	public void display(String aname, int nai){ 
		age = nai;   //argument 갯수,타입,순서 다르면 method overloading 성립
		name = aname;
		System.out.println("leg: " + leg + ", age: " + age +", name: " + name);
	}

	public void display(int nai, int leg_num){ 
		age = nai;   //argument 갯수,타입,순서 다르면 method overloading 성립
		leg = leg_num;
		System.out.println("leg: " + leg + ", age: " + age +", name: " + name);
	}
	
	// ----- 아래는 전부 에러////////////////////////////////////
	/*
	public void display(int  leg_num, int nai){		//성립불가 int, int 
	}

	public void display(int leg_su){ 
	}
	public int display(){ 		//method명이 같고 argument가 달라야 성립한다. 리턴값을 받으려면 void는 안된다.
		return 7;
	}
	*/
}



















//