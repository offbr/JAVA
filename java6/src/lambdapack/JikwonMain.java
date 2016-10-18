package lambdapack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class JikwonMain {
	//다른 클래스의 인자로 사용되는 람다
	//람다 바꾸기 ctrl + 1
	static ArrayList<Jikwon> jikwons = new ArrayList<>();
	static{
		jikwons.add(new Jikwon(3, "홍길동", "111-1111"));
		jikwons.add(new Jikwon(1, "고길동", "222-1111"));
		jikwons.add(new Jikwon(2, "신길동", "333-1111"));
		
		System.out.println("정렬 전: " + jikwons); //jikwons.toString();
	}
	
	public static void main(String[] args) {
		//1. 정렬:  익명 클래스 사용
		Collections.sort(jikwons, new Comparator<Jikwon>() {
			@Override
			public int compare(Jikwon o1, Jikwon o2) {
				return o1.bunho - o2.bunho;
			};
		});
		System.out.println("정렬 후: " + jikwons.toString()); // jikwons;
		
		//2 .정렬: 람다 사용
		//Collections.sort(jikwons, (o1, o2) -> o1.bunho - o2.bunho);
		Collections.sort(jikwons, (o1,o2) -> o1.bunho - o2.bunho);
		System.out.println("정렬 후: " + jikwons.toString()); // jikwons;
	}
}
