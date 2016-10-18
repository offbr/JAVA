package pack2;

import java.util.ArrayList;

public class EnFor {

	public static void main(String[] args) {
		// 향상된 for
		int[] arr = {5,4,7,1,8,0,3,2,6,5};
		
		for (int a = 0; a < arr.length; a++) {
			System.out.print(arr[a] + " ");
		}
		
		
		System.out.println();
		for(int kbs:arr){
			System.out.print(kbs + " ");
			//System.out.print(arr[kbs] + " "); //비권장
		}
		
		System.out.println();
		String[] names = {"tom", "james", "oscar"};
		for(String s:names){
			System.out.print(s + " ");
		}
		
		System.out.println(); //다차원 배열
		int[][] arr2 = {{3,4,5}, {7,8,9}, {1,2,3}}; // 0 , 1 , 2
		for(int[] a:arr2){ //행 묶음
			for(int b:a){ //열
				System.out.print(b + " ");
			}
			System.out.println();
		}
		System.out.println("-----"); //컬렉션 : 객체를 기억하기위한 기억장소
		ArrayList<String> list = new ArrayList<>();
		list.add("java");
		list.add("oracle");
		list.add("python");
		System.out.println("크기: " + list.size());
		
		for(String s:list){
			System.out.print(s+ " ");
		}
		
		System.out.println();
		System.out.println(Flower.rose);
		
		System.out.println();
		Flower f = Flower.rose;
		for(Flower flo:Flower.values()){
			System.out.print(flo+" ");
		}
		
		System.out.println("\n동적 인자------");
		printData(arr);
		System.out.println();
		printData(10);
		System.out.println();
		printData(10, 20);
		
		System.out.println();
		printData(names);
		printData("홍길동");
		printData("고길동", "홍길동");
		
		System.out.println();
		printData(Flower.values());
	}
	
	public static void printData(int ... p){ // ... 타입은 일치하나 인자수 다양
		for(int a:p){
			System.out.print(a + " ");
		}
	}

	public static void printData(String ... p){ // ... 타입은 일치하나 인자수 다양
		for(String a:p){
			System.out.print(a + " ");
		}
	}
	
	public static void printData(Flower ... p){ // ... 타입은 일치하나 인자수 다양
		for(Flower a:p){
			System.out.print(a + " ");
		}
	}
}






























//
