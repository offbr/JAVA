package mypack4;

public class VolumeMain {
	
	public static void main(String[] args) {
		//Volume volume = new Volume(); //인터페이스 부모로서의 기능만한다 객체화불가
		Volume volume;
		
		VolumeRadio radio = new VolumeRadio();
		VolumeTv tv = new VolumeTv();
		
		radio.volumeUp(5);
		radio.volumeDown(2);
		
		System.out.println();
		tv.volumeUp(7);
		tv.volumeDown(3);
		
		System.out.println("\n");
		volume = radio;
		volume.volumeUp(10);
		volume.volumeDown(7);
		
		System.out.println();
		volume = tv;
		volume.volumeUp(10);
		volume.volumeDown(7);
		
		System.out.println();
		Volume[] v = new Volume[2]; //볼륨을 만든게 아니고 배열을 만든 것.
		v[0] = radio;
		v[1] = tv;
		
		for(Volume imsi:v){
			imsi.volumeUp(2);
		}
	
	}
}
