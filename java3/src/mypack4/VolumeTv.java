package mypack4;

public class VolumeTv implements Volume {
	private int volLevel;
	
	public VolumeTv() {
		volLevel = 0;
	}
	
	@Override
	public void volumeUp(int level) {
		if (level > 0){
			volLevel += level;
			System.out.println("TV 소리 올리면 " + volLevel);
		}else{
			System.out.println("TV 소리 조절 실패");
		}
	}

	@Override
	public void volumeDown(int level) {
		if (level > 0){
			volLevel -= level;
			System.out.println("TV 소리 내리면 " + volLevel);
		}else{
			System.out.println("TV 소리 조절 실패");
		}

	}

}
