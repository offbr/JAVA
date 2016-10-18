package mypack3;

public class AnimalMain {
	public static void main(String[] args) {
		AniCow cow = new AniCow();
		System.out.println(cow.name() + " " + cow.action() + " " + cow.eat()+" 먹음" );
		cow.print();
		
		AniLion lion = new AniLion();
		System.out.println(lion.name() + " " + lion.action() + " " + lion.eat()+" 먹음" );
		lion.print();
		lion.eatOther();
		
		System.out.println("\n별도의 클래스 사용");
		AnimalFind.find(cow);
		System.out.println();
		AnimalFind.find(lion);
		
		System.out.println();
		//Anidog dog = new Anidog(); //추상클래스이므로 new 할수 없다
		//System.out.println(dog.name()); 
		
		AnidogWolf wolf = new AnidogWolf();
		System.out.println(wolf.name());
	}
}
