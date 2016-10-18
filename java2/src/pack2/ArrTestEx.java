package pack2;

public class ArrTestEx {

	public static void main(String[] args) {
	/*	문제) ArrtTestEx.java 프로그램 실행 시 인수를 받아 그 수까지의 합 출력,
    	조건 : 인수가 하나일 경우에는 1부터 해당 수까지의 합 출력
    	인수가 두개일 때는 첫번째부터 두 번째 수까지의 합 출력
	*/	
		//System.out.println(args.length);
		//System.out.print(args[0] +" " + args[1]);
		if (args.length == 0){
			System.out.println("인수를 입력하세요");
		}
		int a = Integer.parseInt(args[0]);
		int hap = 0;
		if (args.length == 1){
			for (int i = 1; i <= a; i++) {
				hap += i;			
			}
			System.out.println(a+ "까지의 합은: " + hap);
		}else if(args.length == 2){
			int b = Integer.parseInt(args[1]);
			if (a > b){
				int bb = a;
				a = b; 
				b = bb; 
			}
			for (int j = a; j <= b; j++) {
				//System.out.println(j);
				hap += j;
			}
			System.out.println(a + "에서 " +b+ "까지의 합은: " + hap);
		}else{
			System.out.print("숫자는 두개까지만 가능합니다");
		}
		
	}
}
