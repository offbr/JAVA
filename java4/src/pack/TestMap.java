package pack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TestMap {

	public static void main(String[] args) {
		// Map류 : 자료를  키와 값으로 분리해 저장. 검색에 용이
		HashMap<String, String> list = new HashMap<>();
		list.put("0", "lee");
		list.put("1", "kim");
		list.put("2", "park");
		list.put("3", "choi");
		list.put("4", "lee");
		//list.put("4", "oh"); //키 중복 불가
		System.out.println(list.size());
		System.out.println(list.containsKey("1"));
		System.out.println(list.containsValue("hi"));
		list.remove("2");
		
		System.out.println();
		print(list);
		
	}
	public static void print(Map map){
		Set set = map.keySet(); //키값을 Set type으로 전달
		Iterator iter = set.iterator();
		while(iter.hasNext()){
			String key = (String)iter.next();
			System.out.println(key + " " + map.get(key));
		}
		
	}
}
