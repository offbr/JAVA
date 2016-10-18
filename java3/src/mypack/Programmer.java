package mypack;

public class Programmer {
	//public String nickName = ""; //아래와 같은뜻 //멤버필드
	public String nickName;
	//private int age = 0;
	private int age; //멤버필드에서 초기치는 0;
	String speck = "자바";
	
	public static String motto = "미치자"; //static (멤버에만 준다 지역변수x)
	public final double PI = 3.1415927;	//final을 주면 최종적; 수정 불가! ((멤버에만 준다 지역변수x)
	//final은 대문자로 준다!.
	
	
	public Programmer(){ //지역변수는 초기화 하지않으면 안된다.(멤버필드는 예외)
		System.out.println("난 생성자! 역할은 객체 생성 시 초기화 담당");
		System.out.println("초기화 없을 때는 안적어도 됨, 단 없단 얘기는 아니다.");
		age = 19;
	}
	
	public void displayData(){ //public(접근지정자) 없을수도 있다.
		String sp = reSpeck(); //메소드는 메소드를 호출할 수 있다
		System.out.println("별명이 " + nickName + "인 " + age + "살 " + sp);
	}
	
	private String reSpeck(){
		return "보유 기술은 " + speck;
	}
	
	public void setAge(int age){ //setter //void 리턴값 없음 //캡슐화
		//age = age; // 멤버필드 = 지역변수
		this.age = age; //멤버필드age에 지역변수age값을 치환(대입) //this 자신의 멤버를 찾을때.
	}
	
	public int getAge(){ //getter //접근지정자뒤에 타입 리턴값을 줄수있다 //캡슐화
		return age; //this를 안써줘도 된다 지역변수age 찾고 없으면 멤버필드를 찾는다. 그러므로
	}
	
	public static void myMethod(){ //static method //static 멤버만 호출 가능, 단 일반메소드는 static멤버 호출 가능
		System.out.println("클래스의 이름으로 호출 가능한 메소드"); //static이라 가능
		//System.out.println("나이는 " + age);//에러//static은 객체화가 안되있어서 같은 static 멤버만 가능하다
		System.out.println("좌우명은 " + motto); // motto = static멤버;
	}
}
