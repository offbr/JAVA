package lambdapack;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class LamdaTest {
	public LamdaTest() {
		//1.8 부터 지원
		IntStream.range(1, 11).forEach((int value) -> System.out.print(value)); //나열형
		
		System.out.println();
		IntStream.range(10, 21).forEach(value -> System.out.print(value));
		
		System.out.println();
		IntStream.range(20, 30).forEach(System.out::print);
		
		System.out.println("\n");
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			arr.add(i);
			System.out.println(arr + "\n");			
		}
		
		//향상된 for로 컬렉션 값 출력
		for(Integer i : arr){
			System.out.print(i);
			System.out.print(" ");
		}
		
		for(Integer i : arr){
			System.out.print(i);
			System.out.print(" ");
		}
		
		System.out.println();
		//lambda로 컬렉션 값 출력
		arr.forEach(i -> {
			System.out.print(i);
			System.out.print(" ");
		});
	}
	
	public static void main(String[] args) {
		new LamdaTest();
	}
}
