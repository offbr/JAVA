package pack;

public class BreadMain {
	
	public static void main(String[] args) throws Exception{
		//빵 자원 공유 여부 확인
		BreadPlate breadPlate = new BreadPlate();
		
		BreadMaker maker = new BreadMaker(breadPlate);
		
		BreadEater eater = new BreadEater(breadPlate);
		
		maker.setPriority(10);
		maker.start();
		eater.start();
		
		maker.join();
		eater.join();
		System.out.println("오늘 장사 끝!");
	}
}
