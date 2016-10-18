package mypack3;

public class JepumTv extends Jepum{
	
	public JepumTv() {
		System.out.println("TV 생성자");
	}
	
	@Override
	public void volumeControl() { //abstract를 쓰면 필수 오버라이딩
		System.out.println("Tv 소리 조절");
		
	}
	
	@Override
	public void volumeShow() { //선택
		volume = 10;
		System.out.println("테레비 소리 크기: " + volume);
	}
}
