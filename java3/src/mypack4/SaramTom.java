package mypack4;

public class SaramTom {
	
	public Saram getSaram(){
		//return new Saram();
		return new Saram(){ //주로 이벤트처리할때 씀
			//내부 무명(익명) 클래스
			@Override
			public String getIr(){ //Saram 클래스의 메소드를 오버라이드
				return "홍길동";
			}
			
			/*
			public void aa(){ //의미 없음.
				System.out.println("aaa");
			}
			*/
		};

	}
	
}
