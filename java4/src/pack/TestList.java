package pack;

import java.util.*;

public class TestList {
	
	public static void main(String[] args) {
		// List: 순서가 있고, 중복 허용
		ArrayList<String> list = new ArrayList<>();
		list.add("lee");
		list.add("kim");
		list.add("park");
		list.add("choi");
		list.add("lee");
		list.add("sim");
		list.add("jang");
		System.out.println(list.size());
		System.out.println(list.contains("kim"));
		list.remove("kim");
		System.out.println(list.size());
		list.remove(0);
		System.out.println(list.size());
		System.out.println(list.indexOf("park"));
		List li = list.subList(0, 2);
		
		System.out.println("---------------");
		System.out.println(list.get(1));
		
		System.out.println();
		print(list);
		System.out.println();
		print(li);
		
		System.out.println();
		for(String ss:list){
			System.out.println(ss);
		}
	}	
		public static void print(List list){
			Iterator<String> iter = list.iterator(); //컬렉션에 있는 자료 뽑기
			while(iter.hasNext()){
				String ss = iter.next();
				System.out.println(ss);
			}
	}
}
