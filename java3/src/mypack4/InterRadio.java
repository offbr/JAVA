package mypack4;

public class InterRadio implements Intervol{ //다중 상속 (interface만 가능)
	private int v = 0;
	
	@Override
	public void volUp(int v) {
		this.v += v;
	}
	
	@Override
	public void volDown(int v) {
		this.v -= v;
	}	
}
