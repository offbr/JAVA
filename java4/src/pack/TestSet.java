package pack;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TestSet {

	public static void main(String[] args) {
		// set : 중복 불가, 순서X - HashSet 클래스가 일반적
		// 컬렉션클래스<제네릭> 제네릭 = 타입  
		HashSet<String> list = new HashSet<>(); 
		//list.add(1); //오브젝트 
		list.add("일"); 
		list.add("lee");
		list.add("kim");
		list.add("park");
		list.add("choi");
		list.add("lee");
		list.add("kim");
		System.out.println(list.size());
		list.remove("일");
		System.out.println(list.size());
		//list.clear();
		//System.out.println(list.size());
		print(list);
		System.out.println();
		print(list.toArray());
	}
	
	public static void print(Set set){
		Iterator<String> iter = set.iterator(); //컬렉션에 있는 자료 뽑기
		while(iter.hasNext()){
			//String ss = (String)iter.next();
			String ss = iter.next();
			System.out.println(ss);
		}
	}

	public static void print(Object[] obj){
		int count = obj.length;
		for (int i = 0; i < count; i++) {
			System.out.println(obj[i]);
		}
		
	}
}
