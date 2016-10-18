package pack;

import java.util.Scanner;

public class Test12ex {

	public static void main(String[] args) {
		//문1) 1~ 100사이의 숫자 중 3배수이지만 2의 배수가 아닌 수를 출력하고 합과 갯수 출력
				
		int i = 0;
		int hap = 0;
		int su = 0;
		while(i < 100){
			i++;
			if(i%3==0 && i%2!=0){
				System.out.println(i);
				hap += i; //합
				su++; //갯수
			}
		}
		System.out.println("합: " + hap + ", 갯수 " + su); 
		
		
		
		
		//문2) -1, 3, -5, 7, -9, 11 ~ 99 까지의 합 출력
		i=0;
		int aa=0;
		int mm=0;
		hap = 0;
		
		while(i < 100 ){
			i++;
			if(i%2!=0){
				if(i%4==1){					
					aa += i*-1;
					//System.out.println(aa);
					
				}else{
					mm += i;
					//System.out.println(mm);
					}
						
			}
			hap = mm + aa;
		}
		System.out.println("합은: " + hap);
			//System.out.println(aa)
			
	
	
		//문3) 1~ 1000 사이의 숫자 중 소수들을 출력하고 그 갯수를 출력
		// 소수 : 1보다 크며 1과 그 수 자체 이외의 다른 수로는 나누어 떨어지지 않는 수.
		
		i = 1;
		int j = 1;
		int susu=0;
		while (i < 1000) {
			i++;
			mm = 0;
			//if(i == 9)continue;
			for(j = 1; j <= 1000; j++){
				if(i%j==0){
					mm++;				
				}
			}
			if(mm == 2){
				//System.out.println(i);
				susu++;
			}	
		}
		System.out.println("합은 : " + susu);
	
		
		//문4)
		/*		
						AA
					   ABBA
					  ABCCBA
					 ABCDDBCA
					ABCDEEDBCA
					ABCDEEDBCA
					 ABCDDBCA
					  ABCCBA
					   ABBA
					    AA
				
		*/	
		/*
		for (i = 1; i < 6; i++) {
			for (k = i; k < 5; k++) {
				System.out.print("0");
			}
			for(j = 0; j < i; j++){
				System.out.print("AA");
			}
			System.out.println(" ");
		}
		*/
		
		
		int kk = 0;
		int hang = 1;
		int gongbak = 1;
		char alpha='A';
		while(hang < 6){	
			gongbak = hang;
			while(gongbak < 5){
				gongbak ++;
				System.out.print("0");
			}
			kk = 0;
			while(kk < hang){			
				System.out.print(alpha);
				alpha++;
				kk++;
			}
			while(1 <= kk){		
				alpha--;
				System.out.print(alpha);			
				kk--;
			}
			alpha = 'A';
			kk = 0;
			while(kk < hang){
				//System.out.println("1");
				kk++;
			}
			hang++;
			System.out.println();		
		}
		hang=1;
		while(hang < 6){	
			gongbak = 1;
			while(gongbak < hang){
				gongbak ++;
				System.out.print("0");
			}
			kk = 6;
			while(kk > hang){			
				System.out.print(alpha);
				alpha++;
				kk--;
			}
			while(6 > kk){		
				alpha--;
				System.out.print(alpha);			
				kk++;
			}
			alpha = 'A';
			kk = 5;
			
			while(kk < hang){
				System.out.println("1");
				kk++;
			}
			hang++;
			System.out.println();		
		}
		System.out.println();
		int il = 0;
		int jul = 0;
		int ab = 0;
		char al = 'A';
		for(jul = 0; jul < 5; jul++){
			for (il = jul; il < 4; il++){
				System.out.print(" ");
			}
			for (ab = 0; ab <= jul; ab++) {
				System.out.print(al);
				al++;
			}
			for (ab = 0; ab <= jul; ab++) {
				al--;
				System.out.print(al);
			}
			al = 'A';
			System.out.println();
		}
			al = 'A';
		for(jul = 0; jul < 5; jul++){
			for(il = 0; il < jul; il++){
				System.out.print(" ");
			}
			for (ab = 5; ab > jul; ab--) {
				System.out.print(al);
				al++;
			}
			for (ab = 5; ab > jul; ab--) {
				al--;
				System.out.print(al);
			}
			al = 'A';
			System.out.println();
		}
		
	
			
		
		
	
		
		
			
		
		//문5) 키보드로 부터 숫자를 입력받아 1부터 그 수 까지의 합을 출력
		//     입력되는 숫자는 2 이상만 허용			
		//	   예) 숫자를 입력: 5			
		//    	   5까지의 합 : **			
		//         계속할까요?(1/0)		
		//     1을 입력하면 다시 숫자를 입력하도록 한다. 그외에는 반복 처리 종료
		System.out.println();
		
		while(true){ //무한루프
		int cc = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자를 입력: ");
		int bb = sc.nextInt(); 
		if(bb < 2){
			System.out.println("숫자 2 이상만 가능합니다");
			continue;
		}else{
			for(int v = 1; v <= bb; v++){
				cc += v;	
			}
			System.out.println(bb + "까지의 합: " + cc);
			System.out.print("\n계속할까요? (1/0): \n");
			int dd = sc.nextInt();
				if(dd == 1){
					continue;
				}else{
					System.out.println("종료");
					System.exit(0);
				}
		}
	}
		
			
		
	}
}
