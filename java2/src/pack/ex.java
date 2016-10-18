package pack;

import java.util.Scanner;

public class ex {

	public static void main(String[] args) {
		
		int i = 0;
		int j = 0;
		
		System.out.println("===for문 구구단 한단 출력===");
		for(i = 1; i < 10; i++){
			System.out.println(2 + " * " + i + " = " + (2*i));
		}
		
		System.out.println("===for문 구구단 전체단 출력===");
		for(i = 2; i < 10; i++){
			for(j = 1; j < 10; j++)
				System.out.println(i + " * " + j + " = " + (i*j));
		}
		
		System.out.println("===while문 구구단 한단 출력===");
		i = 0;
		while(i < 9){
			i++;
			System.out.println(2 + " * " + i + " = " + (2*i));
		}
		
		System.out.println("===while문 구구단 전체단 출력===");
		i = 1;
		while(i < 9){
			i++;
			j = 0;
			while(j <9){
				j++;
				System.out.println(i + " * " + j + " = " + (i*j));
			}
		}
		
	/*	System.out.println("===Scaneer 원하는 구구단 출력===");
		Scanner sc = new Scanner(System.in);
		System.out.print("원하는 단수를 입력하세요: ");	
		int a = sc.nextInt();
		for(i = 1; i < 10; i++){
			System.out.println(a + " * " + i + " = " + (a*i));
		}
	*/
		
	/*	System.out.println("===Scaneer 원하는 구구단 출력===");
		Scanner sc = new Scanner(System.in);
		System.out.print("원하는 단수를 입력하세요: ");	
		int a = sc.nextInt();
		System.out.println(" ==="+ a + "단" + "===");
		for(i = 1; i < 10; i++){;
			System.out.println(a + " * " + i + " = " + (a*i));
		}
	*/
		/*
		String b;
		System.out.println("===Scaneer 원하는 구구단 출력===");
		Scanner sc = new Scanner(System.in);
		System.out.print("원하는 단수를 입력하세요: ");	
		int a = sc.nextInt();
		if(a < 1 || a > 12){
			System.out.println("1 ~ 12까지만 가능합니다");
			System.exit(0);
		}
		System.out.println(" ==="+ a + "단" + "===");
		for(i = 1; i < 10; i++){
			System.out.println(a + " * " + i + " = " + (a*i));
		}
		*/
		//별찍기 1 증감치
		System.out.println();
		for(i = 0; i < 6; i++){
			for(j = 0; j < i; j++){
				System.out.print("*");
			}
			System.out.println();
		}
		
		//별찍기 2 증감치 사용
		System.out.println();
		for(i = 0; i < 5; i++){
			for(j = i; j < 5; j++){	
				System.out.print("*");
			}
			System.out.println();
		}
		
		//별찍기 2 감소치 사용
		System.out.println();
		for(i = 0; i < 5; i++){
			for(j = 5; j > i; j--){
				System.out.print("*");
			}
			System.out.println();
		}
				
		//별찍기 3 //3for문 사용
		System.out.println();
		for(i = 0; i < 6; i++){
			for(j = 5; j > i; j--){
				System.out.print(" ");
			}
			for(j=0; j < i; j++){
				System.out.print("*");	
			}
			System.out.println(" ");
		}

		/*System.out.println("===Scaneer 원하는 구구단 출력===");
		Scanner sc = new Scanner(System.in);
		System.out.print("1 ~ 10 까지 원하는 값을 입력하세요 : ");	
		int a = sc.nextInt();
		System.out.print("1 ~ 10까지 원하는 값을 입력하세요 : ");	
		int b = sc.nextInt();
		for(i = 1; i < 10; i++){
			System.out.println(a + " * " + i + " = " + (a*i));
		}*/
		
		i = 0;
		int hap = 0;
		while(i < 99){
			i++;
			hap += i;	
		}
		System.out.println(hap);
		
		i = 0;
		while(i < 100){
		System.out.println(i++);
		}do{
			System.out.println(--i);
		}while(i > 1);
		System.out.println("=========");
		i = 100;
		while(i>0){
			i--;
			//System.out.println(i);
			if(i%2==0 && i%7==0){
				System.out.println(i);
			}
		}
		System.out.println("======");
		hap = 1;
		for (i = 1; i < 11; i++) {
			hap *= i;
			System.out.println(hap);
		}
		
		System.out.println("===");
		int p = 0;
		int pp = 0;
		int ss = 0;
		while(true){
			p++;
			if(p%2!=0){
				//System.out.println(p);
			}else if(p%3==0){		
				//System.out.println(p);
				pp += p;
				ss++;
				//System.out.println(pp);
				if(pp > 1000){
					System.out.println(pp);
					System.out.println(ss);
					break;
				}
				//System.out.println(pp);
			}					
		}
		//System.out.println(pp);
		System.out.println("=====");
		for (int k = 2; k < 10; k++){
			for (int k2 = 1; k2 < 10; k2++) {
				if(k%2==0 && k >= k2){
					System.out.println(k + " * " + k2 + " = " + (k * k2));					
				}
			}
		}
		System.out.println("=========");
		int hap3 = 0;
		for (int k = 0; k < 10; k++){
			for (int k2 = 0; k2 < 10; k2++) {
				//System.out.println(k+k2);
				//hap3; 
				if((k*10+k2)+(k2*10+k) == 99){
					System.out.println(k + " " + k2);
				}
			}	
		}
		
		System.out.println("===============");
		int p1,p2,p3,p4;
		for (p1 = 0; p1 < 9; p1+=2) {
			for (p2 = p1; p2 < 8; p2++) {
				System.out.print(" ");
			}
			for (p3 = 0; p3 < p1+10; p3++) {
				System.out.print("*");
			}
			for (p3 = 0; p3 < p1+10; p3++) {
				System.out.print("*");
			}
			System.out.println();
		}
		for (p1 = 0; p1 <= 17; p1+=2) {
			for (p2 = 0; p2 <= p1; p2++) {
				System.out.print(" ");
			}
			for (p3 = p1; p3 < 17; p3++) {
				System.out.print("*");
			}
			for (p3 = p1; p3 < 17; p3++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
	}

}












//