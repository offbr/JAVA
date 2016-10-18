package pack;
	
public class BreadPlate {
	private int breadCount = 0; //공유 대상
	
	public BreadPlate() {
	
	}
	
	public synchronized void makeBread(){
		if(breadCount >= 10){
			try {
				System.out.println("빵 생산 초과");
				wait(); //Thread를 비활성화
			} catch (Exception e) {
			}
		}
		breadCount++; //빵 생산
		System.out.println("빵 만듦, 총: " + breadCount + "개");
		this.notify(); //wait()에 의해 비활성화된 Thread 할성화
	}
	
	public synchronized void eatBread(){
		if(breadCount < 1){
			try {
				System.out.println("빵 일시품절");
				wait(); //Thread를 비활성화
			} catch (Exception e) {
			}
		}
		breadCount--; //빵 소비
		System.out.println("빵 먹음, 총: " + breadCount + "개");
		this.notify();
		
	}
}
