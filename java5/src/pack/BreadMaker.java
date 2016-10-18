package pack;

public class BreadMaker extends Thread{
	private BreadPlate plate;
	
	public BreadMaker(BreadPlate plate){
		this.plate = plate;	
	}

	@Override
	public void run() {
		for (int i = 0; i < 30; i++) {
			plate.makeBread();
		}
	}
}
