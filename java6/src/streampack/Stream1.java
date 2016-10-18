package streampack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream1 {
	//Stream : 람다식을 기반으로 컬렉션, 배열 등에 대해 반복자 역할을 한다.
	//정렬, 집계 처리 등의 작업도 지원
	static int hap = 0;
	public static void main(String[] args) {
		List<String> list = Arrays.asList("마우스", "키보드", "모니터");
		System.out.println(list.size());
		System.out.println(list);
		//Iterator
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()){
			String ss = iter.next();
			System.out.println(ss);
		}
		
		System.out.println();
		//향상된 for
		for(String s:list){
			System.out.println(s);
		}
		
		System.out.println();
		//Stream : 반복자 역할(람다 기반) - 병렬 처리
		//내부 반복자 사용
		Stream<String> stream = list.stream();
		stream.forEach(ss -> System.out.println(ss)); //Consumer 함수적 인터페이스 - 매개값은 있고, 반환값은 없는형태
	
		System.out.println();
		//컬렉션에서 스트림 얻기 - dto 사용
		List<Student> list2 = Arrays.asList(
				new Student("홍길동", 23),
				new Student("가길동", 33),
				new Student("나길동", 27)
		);
		Stream<Student> stream2 = list2.stream();
		stream2.forEach(s -> {
			System.out.println(s.getName() + " " + s.getAge());
		});
		
		System.out.println();
		//배열에서 스트림 얻기
		String[] arr = {"봄","여름","가을","겨울"};
		Stream<String> arrStream = Arrays.stream(arr);
		arrStream.forEach(a -> System.out.print(a + " "));
		
		IntStream intStream = IntStream.range(1, 11);
//		IntStream intStream2 = IntStream.rangeClosed(1, 10);
		System.out.println();
		intStream.forEach(a -> {
			System.out.print(a + " ");
			hap += a;
		});
		System.out.println("\n10 까지의 합은 " + hap);
	}
	
	static class Student{
		private String name;
		private int age;
		
		public Student(String name, int age) {
		this.name = name;
		this.age = age;
		}
		
		public String getName() {
			return name;
		}
		
		public int getAge() {
			return age;
		}
	}

}
