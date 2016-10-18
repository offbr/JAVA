package mypack3;

public class AnimalFind {
	public static void find(Animal animal){
		animal.print();
		
		if(animal instanceof AniCow){	//instanceof : 객체타입비교 연산자 : true/false
			Animal a = animal;
			System.out.println("이름: " + a.name());
		}else if(animal instanceof AniLion){
			System.out.println("이름: " + animal.name());
		}else{
			System.out.println("기타 동물");
		}
	}
}
