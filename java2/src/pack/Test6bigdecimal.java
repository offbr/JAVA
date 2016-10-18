package pack;

import java.math.BigDecimal;

public class Test6bigdecimal {

	public static void main(String[] args) {
		// 컴의 double 연산 처리 오류
//		double a = 1.5;
//		double b = 1.2;
		double a = 2.0;
		double b = 1.1;	
		System.out.println(a + b);
		System.out.println(a - b);
		
		System.out.println();
		BigDecimal d1 = new BigDecimal("2.0"); //실수 빼기에 있는 문제점을 고침
		BigDecimal d2 = new BigDecimal("1.1");
		System.out.println(d1.subtract(d2));
		
		System.out.println("\n 1");
		
		//큰 정수값 처리
		BigDecimal no1 = new BigDecimal("123456789123456789123456789");
		BigDecimal no2 = new BigDecimal("123456789123456789123456780");
		System.out.println(no1.add(no2));
		System.out.println(no1.subtract(no2)); 
		System.out.println(no1.multiply(no2)); // 곱하기
		System.out.println(no1.divide(no2, 4, BigDecimal.ROUND_UP)); // 소수 4째자리에서 반올림, 중간에 숫자 자리(4)는 반올림 자리수
		
	}

}
