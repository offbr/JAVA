package mypack;

public class ProgrammerMain {

	public static void main(String[] args) {
		// 클래스의 기본 이해
		Programmer tom = new Programmer();
		//tom.Programmer(); //호출 불가! 생성자 호출은 시스템이 하는 일.
		System.out.println("tom의 별명은 " + tom.nickName);
		System.out.println("tom의 보유기술은 " + tom.speck);
		//System.out.println("tom의 나이는 " + tom.age);
		//tom = null;
		//System.out.println("tom의 보유기술은 " + tom.speck); //NullPointerException 에러 주소가없다.
		//String irum = tom.nickName;
		
		tom.nickName = "자바귀신"; // 부여
		tom.speck += ", 파이썬"; //추가
		// class내의 private 빼고는 참조,수정 가능
		System.out.println("tom의 별명은 " + tom.nickName);
		System.out.println("tom의 보유기술은 " + tom.speck);
		tom.displayData();
		//tom.reSpeck(); //호출 불가! private 이므로
		tom.setAge(22);
		System.out.println("tom의 나이는 " + tom.getAge());
		tom.displayData();
	
		System.out.println("\n------------------------");
		Programmer james = new Programmer();
		james.nickName = "파이썬선수";
		james.displayData();
		james.setAge(33);
		james.displayData();
		
		System.out.println();
		tom.displayData();

		System.out.println("\nstatic에 관하여 / final에 관하여");//static을 주면 (인스턴스)객체화안시켜도 클래스의 이름으로 바로 부를수있다
		//String imsi = james.motto; // 비권장 //객체변수로 static멤버를 호출 하는 것은 비권장
		String imsi = Programmer.motto; //static은 멤버필드한테만 쓸수있다.
		System.out.println(imsi); //static멤버는 out 글자가 기울어져있다
		System.out.println();
		System.out.println("파이는 " + james.PI);
		//james.PI = 12.3; //final은 수정불가 readonly // 멤버필드한테만 쓸수 있고 필드명은 대문자로준다]
		
		System.out.println();
		//james.myMethod(); //비권장
		Programmer.myMethod(); //권장 //static method

	}
}




















//