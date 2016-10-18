package pack;

import java.util.Scanner;

public class Test7if {

	public static void main(String[] args) {
		//조건 판단문 if
		int num = 5;
		
		if(num >=  3){
			System.out.println("크군요");
			System.out.println("참일 때");
		}
		
		System.out.println("다음 작업 계속1");
		
		num = 5;
		if(num < 3){
			System.out.println("작군요");
			System.out.println("난 참");
		}else{
			System.out.println("작지 않아요");
		}
		
		System.out.println("다음 작업 계속2");
		
		int jumsu = 40; //다중 if
		if(jumsu >= 70){
			if(jumsu >= 90){
				System.out.println("매우 우수");
			}else{
				System.out.println("보통");
			}
		}else{
			if(jumsu >= 50){
				System.out.println("저조");
			}else{
				System.out.println("엄청 저조");
			}
		}
		System.out.println("다음 작업 계속3");	
		
		jumsu =75;
		String re;
		if(jumsu >= 90){
			re = "수";
		}else if(jumsu >= 80){
			re = "우";
		}else if(jumsu >= 70){
			re = "미";
		}else if(jumsu >= 60){
			re = "양";
		}else{
			re = "가";
			}
		System.out.println("평가 결과 " + re);
	
		
		//문제) 키보드로 부터 상품명, 수량, 단가를 각각 입력받아 금액(수량*단가)를 구하시오
		// 조건 : 금액이 5만원 이상이면 금액을 10%를 , 아니면 금액에 5%를 세금으로 출력 
		// 출력 모양은 상품명 : ***	금액 : *** 세금 : *** 

		int hap = 0;
		int se = 0;
		int cong = 0;
		int fa = 0;
		System.out.println("=====문제=====");
		Scanner pc = new Scanner(System.in);
		
		System.out.print("상품명 입력: ");
		String product = pc.next();
		
		System.out.print("수량 입력: ");
		int su = pc.nextInt();
		
		System.out.print("단가 입력: ");
		int price = pc.nextInt();
		
		hap = su * price;		
		//System.out.println("상품명: " + product + ", 수량: " + su + ", 단가: " + price + ", 합계: " + hap);
		
		if(hap >= 50000){
			se = hap / 10;	// (0.9 + 0.1) double (int)se //반올림 Math.round()
		}else{
			se = hap / 20;	// (0.95+0.05) double로 받으면 출력할때 (int)se
		}
		System.out.println("상품명: " + product + ", 수량: " + su + ", 단가: " + price + ", 합계: " + hap + ", 세금: " + se);
		
		
		
		
		
		
		
		
		
				
		
		
		
		
		
		
		
	}
}
