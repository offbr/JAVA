package mypack;

public class CallBy2{
	public void ex(int a, int b){ //인수로 기본형 //기억장소에 값을 맞바꾼다.
		int imsi = a; // a=b; 로 바로 맞바꿀경우 하나는 지워진다 
		a = b;
		b = imsi;
		System.out.println("메소드 내의 a: " + a + ", b: " + b);
	}
	
	public void ex(CallBy1 data){ //인수로 참조형 (CallBy1 타입의 변수를 지정) //주소를 받는다
		int imsi = data.a; //기억장소에 값을 맞바꾼다.
		data.a = data.b;
		data.b = imsi;
		System.out.println("메소드 내의 a: " + data.a + ", b: " + data.b);
	}
	
	public void ex(int[] ar){ //배열도 참조형
		int imsi = ar[0]; //기억장소에 값을 맞바꾼다.
		ar[0] = ar[1];
		ar[1] = imsi;
	}
	
}
