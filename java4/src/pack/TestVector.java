package pack;

import java.util.Vector;

public class TestVector {
	
	public static void main(String[] args) {
		// List의 서브 클래스
		//Vector<Object> vec = new Vector<>(5, 3); //크기 증가치
		Vector<Object> vec = new Vector<>();
		vec.add('a'); // Boxing //기본형을 객체로 자동으로 바꿔줌
		vec.addElement("한국인");
		vec.add(100);
		vec.add(123.456);
		TestVector tv = new TestVector();
		vec.add(tv);
		System.out.println(vec.size());
		System.out.println(vec.elementAt(1));
		
		System.out.println();
		for(Object obj : vec){
			System.out.println(obj);
		}
	}
}
