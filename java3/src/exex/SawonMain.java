package exex;

public class SawonMain {
	public static void main(String[] args) {
		//임시직 : 이름, 나이, 일수, 일당
		Temporary tem = new Temporary("대한", 21, 20, 9000);
		tem.print();
		Regular re = new Regular("민국", 23, 90000);
		re.print();
		SalesMan man = new SalesMan("만세", 25, 90000, 3000, 0.25);
		man.print();
		Manager ger = new Manager("한국", 27, 200000);
		ger.print();
		
	}
}
