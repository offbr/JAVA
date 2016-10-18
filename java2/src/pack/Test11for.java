package pack;

public class Test11for {

	public static void main(String[] args) {
		// for : coutinue, break
		for (int i = 1; i <= 10; i++) {
			if(i == 5) continue; // 생략
			if(i == 7) break; //for문 무조건 탈출
			//if(i == 7) return; // 메소드 무조건 탈출
			//if(i == 7) System.exit(0); //프로그램 무조건 종료 //0은 정상종료 그외 비정상종료
			System.out.println(i);
		}

		System.out.println("작업 계속");
		
		int kk = 0;
		for(;;){	//무한 looping. 무한루프
			kk++;
			System.out.println("출력" + kk);
			if(kk == 10) break; //for문 탈출
		}
		
		System.out.println("작업 계속2");
		aa:for (int i = 1; i <= 3; i++) {
			bb:for (int j = 1; j <= 5; j++) {
				//if(j == 3) continue bb; //기본값
				//if(j == 3) continue aa;
				System.out.print(i + " " + j + " ");
				//if(j == 3) break bb;
				//if(j == 3) break aa;
			}
		System.out.println("\n********************");
		}
	}

}
