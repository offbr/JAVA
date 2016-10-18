package other;

import mypack2.*;

public class Father extends GrandFa{ //상속(확장) extends GrandFa
	
	public Father() {
		System.out.println("아버지 생성자");
	}
	
	public static void main(String[] args) {
		Father father = new Father();
		System.out.println(father.gahun);
	}
}
