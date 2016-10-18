package mypack;

public class AnimalMain {

	public static void main(String[] args) {
		Animal tiger = new Animal(); //객체를 만들때 3가지 방법이 있다 ( .. / int / String)
		tiger.display(); //method overloading 기능은 같으나 처리값이 다르다. 갯수,타입,순서 다르면 성립.
		tiger.display(2);
		tiger.display("호돌이");
		tiger.display(3, "호랑이"); //method overloading은 실행하는 쪽에서 생각한다.
		tiger.display("타이거", 5);
		tiger.display(7, 4);

		System.out.println("\n생성자 오버로딩 연습------------------");
		Animal dog = new Animal(); 
		dog.display();
		
		System.out.println(); //setter,생성자를 통해서 private 값을 줄수있다 //한번만 줄땐 생성자를 통해서
		Animal gugu = new Animal(2); //여러번 줄땐 setter
		gugu.display();
		
		System.out.println();
		Animal cat = new Animal("고양이");
		cat.display();
		
		System.out.println("------------");
		Animal hores = new Animal("백마", 7);
		hores.display();
		System.out.println(Animal.MOUSH); //final static은 대문자
		//Color.ORANGE //final static은 대문자
	}

}


/* 문제) 
		클래스명 : Production
		멤버 : 
		+ name (문 : public)  +:public -:private
		- price (수 : private) //생성자로 값주입
		- productionDay (문 : private) //생산일 //셋터로 값주입
		+ display() : 상품명, 가격 , 생산일 출력		
*/		