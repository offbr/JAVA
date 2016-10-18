package mypack2;

public class PolyRadio extends PolyProduct{
	@Override
	public void volumeControl() {
		System.out.println("라디오 소리 조절 후 " + getVolume());
	}
}
