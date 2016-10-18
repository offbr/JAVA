package pack;

import java.util.Scanner;

public class Test8switch {

	public static void main(String[] args) {
		// 조건 판단문 switch
		int nai = 30;
		nai = nai / 10 * 10;
		//System.out.println(nai);
		switch (nai) {
		case 30: case 31: case 32:
			System.out.println("삼십대");
			System.out.println("잘산다");
			break;
		case 40: //마지막 break는 생략가능
			System.out.println("사십대 중년");
			break;
		default:
			System.out.println("기타");
			break;
		}
		System.out.println("====");
		//int time = (int)(Math.random() * 10); //난수 발생 실수값을 int로 정수값 받는다
		int time = (int)(Math.random() * 4) + 8;
		//System.out.println(time);
		switch(time){
		case 8:
			System.out.println("출근하자");
			//break;
		case 9:
			System.out.println("회의하자");
			//break;	
		case 10:
			System.out.println("프로그램짜자");
			//break;
		default:
			System.out.println("명상하기");
		}
		System.out.println("작업 계속");
		
		System.out.println("======문제=====");
		//문제 : 키보드로 부터 년과 월을 각각 입력 받아
		//	     해당년 월에 날수를 출력. 윤년을 체크
		//	     윤년은 해당년 4의 배수 이고, 100의 배수가 아니거나 400의 배수이면 윤년
		//	     출력은 ****년 **월 **일 평년 / 윤년 
		// 	   case문안에 if문 올 수 있다
		
		
		int day = 0;
		String nn = "";
		Scanner sc = new Scanner(System.in);
		System.out.print("년 입력: ");
		int year = sc.nextInt();
		if(year < 1900 || year > 2100){
			System.out.println("1900년 ~ 2100년까지 있습니다");
			System.exit(0);
		}
		System.out.print("월 입력: ");
		int month = sc.nextInt();
		if(month < 1 || month > 12){
			System.out.println("1월 ~ 12월까지 있습니다");
			System.exit(0);
		}
		
/*		if((year % 4 == 0 && year % 100 !=0) || year % 400 == 0){
			nn = "윤년";
		}else{
			nn = "평년";
		}
		switch(month){
		case 4: case 6: case 9: case 11:
			day = 30;	
			break;
		case 2:
			if(nn=="윤년"){
				day = 29;
				break;
			}else{
				day = 28;
				break;
			}			
			default : 	
				day = 31;		
				break;
		}
		System.out.println(year + "년 " + month + "월 " + day + "일 " + nn);
		
	*/	
		
	int n = ((year % 4 == 0 && year % 100 !=0) || year % 400 == 0)?1:0;	
		switch(month){
		case 4: case 6: case 9: case 11:
			day = 30;	
			break;
		case 2:
			if(n==1){
				day = 29;
				break;
			}else{
				day = 28;
				break;
			}		
		default :
			day = 31;
		}
		System.out.println(year + "년 " + month + "월 " + day + "일 " + ((n==1)?"윤년":"평년"));
		
		System.out.println();
	
		
	}
}
