package mypack;

public class OverloadTest { //method overloading
	public int coffee(int money){ //커피 기본값은 200
		return coffee(money, 200);
	}
	
	public int coffee(int money, int coffeeValue){ //600 들어오고 200씀
		if(money >= 0){
			return money / coffeeValue;
		}else{
			return -1;			
		}
	}
	
	public void printCoffee(int cups){
		if(cups > 0){
			System.out.println("커피 " + cups + " 잔입니다");
		}else{
			System.out.println("금액이 부족합니다");
		}
	}
	
	public void coffIn(int money){ //금액 누적
		money += 300;
		System.out.print("method coffIn : ");
		System.out.println("money는 " + money);
	}
	
	public void coffIn(String money){ 
		money = money.replace('m', 'y'); //m을 y로 바꿈 char
		System.out.print("method coffIn : ");
		System.out.println("money는 " + money);
	}
	
	public static void main(String[] args) { //따로 만드는게 원칙
		int myMoney = 500;
		
		OverloadTest test = new OverloadTest(); //new연산자 객체생성, 생성자호출
		int cups = test.coffee(600); //600을 넣음
		System.out.println(cups);
		test.printCoffee(cups);
		
		System.out.println();
		cups = test.coffee(myMoney, 300); //coffeeValue값 300으로 바꿈
		test.printCoffee(cups);
		
		System.out.println(); //메소드의 이름은 같으나 내용은 다르다 내용은 선택적으로 바꿀수 있다.
		int noChange = 400;
		test.coffIn(noChange);
		System.out.print("main : ");
		System.out.println("noChange는 " + noChange);
		
		System.out.println();
		String sChange = "tom"; //m은 y로 바뀜
		test.coffIn(sChange);
		System.out.print("main : ");
		System.out.println("sChange는 " + sChange);
		
		
	}
}


















//
