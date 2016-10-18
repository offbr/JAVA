package streampack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Stream2{

	public static void main(String[] args) {
		List<Person> list = Arrays.asList(
				new Person("홍길동", "남", 24),
				new Person("한가해", "여", 25),
				new Person("고길동", "남", 21),
				new Person("이기자", "여", 14),
				new Person("한국인", "남", 40)
				);
		
		
		//컬렉션 자료에 대한 중간/최종 처리
		double avg = list.stream().mapToInt(Person :: getAge).average().getAsDouble();
		System.out.println("나이 전체 평균:" + avg);
		
		double avgM = list.stream()
				.filter(m -> m.getGender().equals("남")).mapToInt(Person :: getAge).average().getAsDouble();
		System.out.println("나이 전체 평균:" + avgM);
		
		System.out.println();
		list.stream().filter(a -> a.getName().startsWith("한"))
		.forEach(a -> System.out.println(a.getName()));
		
		System.out.println();
		List<String> list2 = Arrays.asList("홍길동","고길동","홍길동","신길동","고길동");
		list2.stream().distinct().forEach(a -> System.out.println(a));
		
		System.out.println();
		list2.stream().sorted().forEach(a -> System.out.println(a));
		System.out.println();
		list2.stream().sorted(Comparator.reverseOrder()).forEach(a -> System.out.println(a));
		

	}
	static class Person{
		private String name;
		private String gender;
		private int age;
		
		public Person(String name, String gender, int age) {
			this.name = name;
			this.gender = gender;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
		
		
	}
}
