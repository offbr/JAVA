package mypack4;

public interface Resume {
	String SIZE = "A4"; //public final static //대문자 기울어짐
	
	void setIrum(String irum);
	void setPhone(String phone);
	void print();
	
	//java 8 이후에 가능
	default void playJava(boolean b){
		if(b){
			System.out.println("자바 프로그래밍 가능");
		}else{
			System.out.println("자바 불가능");
		}
	}
	
	static void changeData(){
		System.out.println("스테틱 메소드 처리 가능");
	}
}
