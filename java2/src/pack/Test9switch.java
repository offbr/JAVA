package pack;

public class Test9switch {

	public static void main(String[] args) {
		// switch test
		//문자 비교도 가능
		double su1 = 10.5, su2 = 5, result = 0.0;
		char op = '*'; // '문자', "문자열"
		switch(op){
		case '+':
			result = su1 + su2;
			break;
		case '-':
			result = su1 - su2;
			break;
		case '*':
			result = su1 * su2;
			break;
		case '/':
			result = su1 / su2;
			if(su2 == 0.0){
				System.out.println("0으로 나눌수 없어요");
				result = 0.0;
			}
			break;
		default:
			System.out.println("연산자 오류");
		}
		System.out.println("연산결과: " + result);
		
		
		System.out.println("\n 문자열 비교도 가능");
		String jik = "사원";
		switch (jik) {
		case "사원":
			System.out.println("열심히 해");
			break;
		case "과장":
			System.out.println("살살해 해");
			break;
		default:
			System.out.println("안녕");
		}
		
		
		
	}

}
