package pack;

import java.util.Scanner;

public class Test10for {

	public static void main(String[] args) {
		// 반복문 for
		System.out.println("뭔가를 진행 하다가...");
		{
			int kbs = 9;
			{
				int sbs =6;
				System.out.println(kbs + " " + sbs);
			}
			//System.out.println(kbs + " " + sbs);
		}
		
		// int hap = 1+ 2+ 3+ ...+ 10;
		// int hap = 0; hap = hap + 1...; 
		int a;
		int hap = 0; //누적 기억장소 * 초기화하라
		//for(a = 1; a <= 10; a=a+1){
		//for(int a =1; a <= 10; a++){
		for(a = 1; a <= 10; a++){ //초기 목적 증감
			System.out.print(a + "\t");
			hap += a;
		}
		System.out.println("\n탈출 후 a: " + a);
		System.out.println("hap: " + hap);
		
		System.out.println();
		for(int i = 65; i <= 90; i++){
			System.out.print((char)i + " "); //숫자값을 ascii코드 값으로 형변환 (char)
		}
		
		System.out.println();
		for (int i = 'A'; i <= 'Z'; i++){ 
			System.out.print(i + " "); //ascii코드 값을 숫자로
		}
		
		System.out.println();
		for (int i = 10; i >= 1; i--){ 
			System.out.print(i + " "); 
		}
		
		System.out.println();
		int aa = 1;
		for (; aa<=5; aa++) { // 비권장
			System.out.print(aa + " ");
		}
		
		
		//구구단 출력
		System.out.println("\n구구단 출력");
		
		for (int i = 1; i < 10; i++){
			System.out.println("3*" + i + "=" + (3 * i));
		}
		
		System.out.println("\n다중 for ----------");
		
		for (int m = 1; m <= 3; m++){
			System.out.println("m:" + m);
			for (int n = 1; n <= 4; n++){
				System.out.print("n:" + n + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		for (char i = 65; i <= 90; i++){
			System.out.print(i + " : ");
			for (char j = i; j <= 'Z'; j++){
				System.out.print(j);
			}
			System.out.println();
		}
		
		System.out.println("\n계속");
		
		//문제1) 키보드로 부터 숫자를 받아 구구단 출력 (2~9까지만 허용)
	/*	Scanner sc = new Scanner(System.in);
		System.out.print("원하는 구구단수를 입력: ");
		int dan = sc.nextInt();
		if(dan < 2 || dan > 9){
			System.out.println("2단 ~ 9단까지만 가능합니다");
			System.exit(0);
		}
		for(int i = 1; i < 10; i++){
			System.out.println(dan+ " * " + i + " = " + (dan * i));
		}*/
		
		//문제2) 2 ~ 9 출력
		System.out.println("2단부터 9단까지 출력");
		for(int i = 2; i < 10; i++){
			for(int j = 1; j < 10; j++){
				System.out.print(i + "*" + j + "= " + (i * j)+ " ");
			}
			System.out.println("");
		}
		
		//문제3) 1 ~ 100 사이의 3의 배수이면서 5의 배수인 숫자의 갯수와 그 둘의 합출력
		int su = 0;
		int ku = 0;
		int i = 0;
		for(i = 1; i <= 100; i++){
			if(i%3==0 && i%5==0){
				ku += i;
				su++;
			}	
		}
		System.out.println("합: " + ku +", 갯수: "+ su);
		
//		문제4)   *********  9개
//				  *******   7개
//				   *****    5개
//				   	***     3개
//				   	 *      1개
		int j = 0;
		int u = 0;
		System.out.println("=========");
		// 1번
		System.out.println();
		
		for(i = 0; i < 10; i+=2){
			for(u = 1; u <= i; u+=2){
				System.out.print(" ");
			}
			for(j = i; j < 9; j++){
			 	System.out.print("*");
			}	
		System.out.println();
		}
		
		System.out.println("=========");
		//2번
		System.out.println();
		
		for(i = 0; i < 10; i+=2){
			for(j = i; j < 9; j++){
			 	System.out.print("*");
			}	
		System.out.println();
			for(u = 0; u <= i; u+=2){
				System.out.print(" ");
			}
			//System.out.println();
		}
		
		//3

		
//		문제5) 문제4를 뒤집어서 출력;
		
		System.out.println();
		for(i = 0; i < 12; i+=2){
			for(j = 1; j < i; j++){
			 	System.out.print("*");
			}	
		System.out.println();
			for(u = i; u <= 6; u+=2){
				System.out.print(" ");
			}
		}
		
		
	System.out.println("=======");
		System.out.println();
		
		for(i = 1; i < 10; i+=2){
			for(u = i; u <= 8; u+=2){
				System.out.print(" ");
			}
			for(j = 0; j < i; j++){
			 	System.out.print("*");
			}	
		System.out.println();
		}
		
		
		System.out.println();
		for(i = 0; i < 9; i+=2){
			for(u = 1; u <= i; u+=2){
				System.out.print("0");
			}
			for(j = i; j < 9; j++){
				System.out.print("*");
			}			
			System.out.println();
		}
		

		System.out.println("55555");
		for(i = 0; i < 9; i+=2){
			for (u = i; u < 8; u+=2) {
				System.out.print("0");
			}
			for(j = 0; j <= i; j++){
				System.out.print("*");
			}			
			System.out.println();
		}
		
//참고
		System.out.println("===");
		for (i = 0; i < 5; i++) {
			//System.out.println(i);
			for (j = 0; j < i; j++) {  // 공백 출력
				System.out.print("0");
			}
			//System.out.println(2 * ( 5 - i)); //10, 8, 6, 4, 2를 얻어 조건값으로 사용
			for (int k = 1; k < 2 * ( 5 - i); k++) {  // * 출력
				System.out.print("*");
			}
			System.out.println();
		}
		System.out.println();
		//문제4
		for (i = 4; i >= 0; i--) {
			//System.out.println(i);
			for (j = 0; j < i; j++) {
				System.out.print("0");
			}
			for (int k = 1; k < 2 * ( 5 - i); k++) {
				System.out.print("*");
			}
			System.out.println();
		}
//참고
		
		
		
		
	}
}
