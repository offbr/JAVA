package mypack4;

public class InterAudio implements Intervol, InterAdvanceVol{ //다중 상속 (interface만 가능)
	//overriding을 하지 않으려면 추상클래스로 사용, overriding을 다 할경우 일반메소드로 사용 
	private int v = 0;
	
	@Override
	public void volUp(int v) {
		this.v += v;
	}
	
	@Override
	public void volDown(int v) {
		this.v -= v;
	}	
	
	@Override
	public void volOff() {
		System.out.println("오디오 끄기");
			
	}
	
	@Override
	public void volResume() {
		System.out.println("오디오 켜기");
			
	}
}
