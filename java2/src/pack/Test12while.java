package pack;

public class Test12while {

	public static void main(String[] args) {
		// 반복문 while
		int w = 1;
		while(w <= 5){
			System.out.print("w:" + w + " ");
 			w++; //반복문을 벗어날 수 있는 명령문 꼭 필요!
		}
		System.out.println("\n수행 후 w는" + w);
		
		//1~10까지의 합
		int su = 0, tot = 0;
		while(su < 10){
			su++;
			//System.out.println(su);
			tot += su;
		}
		System.out.println("합은 " + tot);
		
		System.out.println();
		w = 0;
		while(true){ // 무한looping
			w++;
			if(w == 5) continue;
			System.out.println("w: " + w);
			if(w==10) break;
		}
		
		System.out.println();
		w = 10;
		do{ //최소 1회 이상 수행
			System.out.println("w: " + w);
			w++;
		}while(w <= 5);
		
		
		//문1) 1~ 100사이의 숫자 중 3배수이지만 2의 배수가 아닌 수를 출력하고 합과 갯수 출력
		//문2) -1, 3, -5, 7, -9, 11 ~ 99 까지의 합 출력
		//문3) 1~ 1000 사이의 숫자 중 소수들을 출력하고 그 갯수를 출력
		// 소수 : 1보다 크며 1과 그 수 자체 이외의 다른 수로는 나누어 떨어지지 않는 수.
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
		
		//문5) 키보드로 부터 숫자를 입력받아 1부터 그 수 까지의 합을 출력
		//     입력되는 숫자는 2 이상만 허용
		//	   예) 숫자를 입력: 5
		//    	   5까지의 합 : **
		//         계속할까요?(y/n)
		//     y를 입력하면 다시 숫자를 입력하도록 한다. 그외에는 반복 처리 종료
	}
}
