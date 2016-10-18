package mypack3;

abstract public class Jepum { //원형 클래스 //추상클래스 //추상클래스는 new할수없다 슈퍼클래스로만 쓰인다
	public int volume = 0;
	
	public Jepum() {
		System.out.println("추상 클래스 생성자");
	}
	
	abstract public void volumeControl(); //바디가({})없는 메소드 == 추상 메소드
	
	public void volumeShow(){ //일반 메소드
		System.out.println("소리 크기: " + volume);
	}
	
}
