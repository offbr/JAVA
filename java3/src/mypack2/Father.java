package mypack2;

public class Father extends GrandFa{ //상속(확장) extends GrandFa
	private int nai = 55; //은닉화
	private int house = 1; //Father 고유 멤버
	public String gabo = "물병"; //은닉화 //지역변수(인스턴스변수) 먼저 찾기 때문에 부모멤버가 숨겨진다.
	
	//this(); 자기 생성자실행
	//super(); 부모 생성자실행
	public Father() { //overload
		super();	//부모 생성자 호출 생략 가능
		//super(77);
		System.out.println("아버지 생성자");
	}
	
	@Override //가독성이 좋아지고 정보제공 //메소드
	public int getNai() { //method override //부모 method와 똑같아야한다 .
		//부모 클래스의 메소드와 동일한 메소드를 재정의
		return nai;
	}
	
	public int getHouse() {
		return house;
	}
	
	@Override
		public String say() {
			return "아버지 말씀: 일해!";
		}
	
	public void showData(){
		System.out.println("가보 출력하기-------");
		System.out.println(gabo);
		System.out.println(this.gabo);
		System.out.println(super.gabo);
		System.out.println("메소드 호출하기-------");
		System.out.println(getNai());
		System.out.println(this.getNai()); //멤버필드와 지역변수와 구분할때만 this를 쓴다. 그외에는 비권장. 
		System.out.println(super.getNai());
		System.out.println(this.say());
		System.out.println(super.say());
	}
}
