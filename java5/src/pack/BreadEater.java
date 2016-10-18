package pack;

public class BreadEater extends Thread{
	private BreadPlate plate;
	
	public BreadEater(BreadPlate plate){
		this.plate = plate;	
	}

	@Override
	public void run() {
		for (int i = 0; i < 30; i++) {
			plate.eatBread();
		}
	}
}
