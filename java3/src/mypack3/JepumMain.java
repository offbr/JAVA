package mypack3;

public class JepumMain {
	
	public static void main(String[] args) {
		// 추상 클래스는 new 안됨
		// Jepum jepum = new Jepum();
		Jepum jepum = null;
		
		JepumTv tv = new JepumTv();
		tv.volumeControl();
		tv.volumeShow();
		
		System.out.println();
		JepumRadio radio = new JepumRadio();
		radio.volumeControl();
		radio.volumeShow();
		
		System.out.println();
		jepum = tv;
		jepum.volumeControl();
		jepum.volumeShow();
		System.out.println();
		jepum = radio;
		jepum.volumeControl();
		jepum.volumeShow();
		
		
	}
}

