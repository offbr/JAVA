package mypack2;

public class GrandFa { //final쓰면 상속불가.
	private int nai = 80; //private는 본인 class 내에서만 유효
	public String gabo = "상감청자"; //public는 project에서도 유효
	protected final String gahun = "자바를 공부하라"; //protected를 썼다는건 상속한다는 암시.. //final 수정불가, 변수명:대문자 
	//protected는 같은 package에선 public , project로 넘어가면 private / package가 다르지만 자식클래스면 project가 넘어가도 유효 
	String imsi = "보이나요"; //friendly는 같은 package에선 public
	
	public GrandFa() {
		System.out.println("할아버지 생성자");
	}
	
	public GrandFa(int nai) {
		this(); //생성자 호출 총 한번
		this.nai = nai;	
	}
		
	public String say(){
		return "할아버지 말씀 : 졸지 마!";
	}
	
	public void eat(){
		System.out.println("밥은 맛있게...");
	}

	public int getNai(){
	//public final int getNai(){ //method의 final을 주면 override 불가 (수정불가);
		return nai;
	}
	
	
	
}
