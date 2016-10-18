package pack2;

public class ArrTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// 배열: Array : 성격과 크기가 일치하는 복수 개의 기억장소를 대표명 하나를 주고 첨자로 각 기억장소를 구분
		// 		  반복처리가 효과적
		//int[] ar;
		//int ar[]; // [0]  0 = 첨자
		//ar = new int[5]; //기억장소를 확보
		int[] ar = new int[5]; 
		System.out.println("배열의 크기: " + ar.length);
		ar[0] = 10; ar[1] = 20; ar[4] = ar[0] + ar[1];
		System.out.println(ar[4]);
		//ar[5] = 50; //ArrayIndexOutOfBoundsException : 실행에러
		//System.out.println(ar[5]);
		int a = 4, b = 4; //첨자에 변수를 쓸수도 있다
		System.out.println(ar[4] + " " + ar[a] + " " + ar[b] + " " + ar[3 + 1]);
		
		//선언과 동시에 값을 부여
		int[] ar1 = {1,2,3,4,5};
		System.out.println(ar1[1]);
		System.out.println(ar1[0] + " " + ar1[1]); 
		for (int i = 0; i < ar1.length; i++) {
			System.out.print(ar1[i] + " ");
		}
		
		System.out.println();
		for(int i:ar1){  //향상된 for문 : 배열 , 컬렉션에서 사용 가능 
			System.out.print(i + " ");
		}
		
		System.out.println();
		String[] city = {"서울", "대전", "대구", "부산", "광주"}; //기본형처럼 쓰는 객체
		for(String c:city){ //문자열 가능 
			System.out.println("도시명: " + c);
		}
		
		System.out.println();
		int[] ar2 = new int[5];
		for (int i = 0; i < ar2.length; i++) {
			ar2[i] = i + 1;
		}
		for (int i = 0; i < ar2.length; i++) {
			System.out.print(ar2[i] + " ");
		}
		
		int tot = 0;
		for (int j = 0; j < ar2.length; j++) {
			tot += ar2[j];
		}
		System.out.println("\n합은: " + tot);
		// 여기까지 1차원 배열
		
		//다차원 배열 
		int su[][] = new int[3][4]; //행첨자 //열첨자
		System.out.println(su.length + " " + su[0].length); 
		su[0][0] = 100;
		System.out.println(su[0][0]);
		int num = 10;
		for (int i = 0; i < su.length; i++) {
			for (int j = 0; j < su[i].length; j++) {
				su[i][j] = num++;
			}
		}
		System.out.println(su[0][0]);
		System.out.println();
		for (int i = 0; i < su.length; i++) {
			for (int j = 0; j < su[i].length; j++) {
				System.out.print(su[i][j] + " ");
			}
			System.out.println();
		}
		
		int[][] scores = new int [2][]; // 가변배열 //행만 잡음 2묶음
		scores[0] = new int[2]; //열첨자
		scores[1] = new int[3]; 
		System.out.println(scores.length + " " + scores[0].length + " " + scores[1].length);
		
		System.out.println();
		int[][] jum = {{90, 96},{89, 87}}; //선언과 함께 초기치 부여
		
/*		
		문제) ArrtTestEx.java 프로그램 실행 시 인수를 받아 그 수까지의 합 출력,
		      조건 : 인수가 하나일 경우에는 1부터 해당 수까지의 합 출력
		      인수가 두개일 때는 첫번째부터 두 번째 수까지의 합 출력	
				
*/		
		
		
		
		
		
		
		
		
	}
}
