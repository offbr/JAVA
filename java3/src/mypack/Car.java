package mypack;

//int a; int type 4byte

//class 는 설계도  > 1. 행위(멤버메소드) 2. 속성(멤버필드)
public class Car { //접근지정자 기타제한자 class 클래스명(대문자로 시작)
	int wheel;		//멤버필드 (전역변수) // 멤버필드에 값을 주지않으면 0을 취한다.
	private int airBag = 1;  //접근지정자 기타제한자 형(type) 필드명
	private int speed; //private는 외부 클래스에서 접근이 안된다
	public String irum;
	
	public Car(){ // 생성자 (멤버메소드 일종) //class와 이름이 같고 반환값이 없으면 생성자 //초기값을 초기화하기 위해.. 
		System.out.println("생성자"); //생성자에 내용이 없을 경우 생략가능 (이 경우 시스템에서 자동으로 만든다.)
		wheel = 4; //값을 생성자를 통해서도 줄수있다
	}
	
	public void abc(){ //멤버메소드 : 접근지정자(public,private 등) 기타제한자(static,final 등) 반환형 메소드명(인자 ...){}
		System.out.println("abc 메소드 수행");
		System.out.println("에어백 수는 " + airBag);
		System.out.println("바퀴 수는 " + wheel);
		
		def(); //메소드 호출
		int result = korea(50); //actual argument(실인수) 
		System.out.println("결과는 " + result);
		System.out.println(korea(150)); //넘어온 결과값을 바로 찍는다.
		System.out.println(kor("대한"));
	}
	//void 반환값 없다
	private void def(){ //private는 외부 클래스에서 접근이 안된다
		System.out.println("def 메소드 수행");		
	}
	//int 반환값 있다 return을 써줘야 한다. int를 다른걸로 바꾸면 그 타입으로 보내준다는 뜻
	private int korea(int jumsu){ //메소드명(인수...) 인자, dummy argument(가인수), parameter 
		int imsi = 50; //변수 (지역변수)
		return jumsu + imsi;
	}
	// 반환값 있다 return을 써줘야 한다. String를 다른걸로 바꾸면 그 타입으로 보내준다는 뜻
	public	String kor(String ju){
		String imsi = "민국"; //변수 (지역변수)
		return ju + imsi;
	}
	
	//setter, getter
	public void setAirBag(int air, int password){ //setter 자동화 메소드명 약속.
		if(password == 123){ //캡슐화(암호화) 
		airBag = air; //private에 접근할 수 있다
		}else{
			System.out.println("암호 불일치, 자격없음");
		}
	}
	
	public int getAirBag(){ //getter 자동화 메소드명 약속.
		return airBag;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	
	
}























//