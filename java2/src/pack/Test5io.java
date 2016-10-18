package pack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Test5io {

	public static void main(String[] args) throws Exception{ //키보드로 값 입력받을시 에러 날 경우 해당 메소스에 throws Exception 입력
		// 프로그램 진행 도중에 외부에서 값 얻기
		if(args.length == 0){
			System.out.println("외부에서 값 얻기 실패!");
			System.exit(0); //응용 프로그램의 무조건 종료
		}
		
		System.out.println("외부에서 얻은 값 : " + args[0]);
		
		System.out.println("\n프로그램 진행 도중 키보드로 부터 값 얻기");
		System.out.print("문자 입력:");
		int ch = System.in.read();  //Ascii 코드 값 입력 // 'a'문자,"a"문자열
		System.out.println("ch" + ch + " " + (char)ch); //(char)문자로 형변환
		
		System.in.read(); //Enter 키 처리용
		System.in.read();
		
		
		//키보드에서 문자열 얻기
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.print("이름 입력: ");
		String irum = buf.readLine(); // read();는 int로 받는거 같지만 실제로는 chr ascii코드로 받는다
		System.out.print("나이 입력: ");
		String nai = buf.readLine();
		System.out.println("이름은 " + irum + ", 나이는 " + nai);
		
		//키보드에서 문자열 얻기2
		System.out.println("\n스캐너 클래스 사용 ============");
		Scanner sc = new Scanner(System.in);
		System.out.print("상품 이름 입력: ");
		String product = sc.next();
		System.out.print("가격 입력: ");
		int price = sc.nextInt();
		System.out.println("상품명은 " + product + ", 가격은 " + price);
		
		System.out.println("처리 완료");
	}

}


















