package mypack2;

public class PolyTv extends PolyProduct{
	@Override
	public void volumeControl() {
		System.out.println("Tv 사운드 변경 후 " + getVolume());
		System.out.println("Tv 제품에 맞게 메소드 작성");
	}
	
	public void tvshow(){
		System.out.println("Tv만의 고유 메소드");
	}
}
