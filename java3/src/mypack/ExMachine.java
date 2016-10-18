package mypack;

import java.util.Scanner;

public class ExMachine {
	private int coffee = 20;
	private int sugar = 10;
	//ExCoinIn coin = new ExCoinIn();
	ExCoinIn coinIn;
	
	public ExMachine(){
		coinIn = new ExCoinIn();
		System.out.println("자판기");
	}
		/*
		public void showData(){
			Scanner sc = new Scanner(System.in);
			System.out.println("동전을 입력하세요: ");
			int coin = sc.nextInt();
			if(coin >= 200){
				System.out.println("몇잔을 원하세요: ");
				cupCount = sc.nextInt();
				coinIn.calc(coin, cupCount);
				if(coin/100-cupCount >= cupCount){
				int jandon = coinIn.calc(coin, cupCount);
				System.out.println("커피" + cupCount + "잔과 잔돈" + jandon + "원");	
				}else{
					System.out.println("커피1잔에 200원입니다");
				}
			}else{
			System.out.println("금액이 부족합니다");
			}
		}
		*/
	
	public void showData(){
		Scanner sc = new Scanner(System.in);
		System.out.println("동전을 입력하세요: ");
		int coin= sc.nextInt();
		coinIn.setCoin(coin);
		System.out.println("몇잔이 필요하세요: ");
		int cupCount = sc.nextInt();
		coinIn.setCupCount(cupCount);
		coinIn.calc();
	}
	
}
















