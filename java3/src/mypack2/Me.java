package mypack2;

public final class Me extends Father{ //class에 final을 주면 최종클래스가 됨(파생클래스없음), 상속불가
	
	public Me() {
		//super();
		System.out.println("Me 생성자");
	}
	
	public String binking(){
		//System.out.println(gahun);
		//gahun = "멋대로 살래!; //final 수정불가.
		return "자전거로 전국일주!";
	}
}
