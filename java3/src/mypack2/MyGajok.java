package mypack2;

public class MyGajok {
	public static void main(String[] args) {
		GrandFa gr = new GrandFa();
		System.out.println("가보: " + gr.gabo);
		System.out.println("가훈: " + gr.gahun);
		System.out.println(gr.say());
		gr.eat();
		System.out.println("할아버지 나이: " + gr.getNai());
		
		System.out.println();
		GrandFa gr2 = new GrandFa(88);
		System.out.println("할아버지 나이: " + gr2.getNai());
		
		
		System.out.println("------------------");
		Father fa = new Father();
		System.out.println("가보: " + fa.gabo);
		System.out.println("가훈: " + fa.gahun);
		System.out.println(fa.say());
		fa.eat();
		System.out.println("아버지 나이: " + fa.getNai());
		System.out.println("집 수: " + fa.getHouse());
		fa.showData();
		
		System.out.println("--------------------------");
		Me me = new Me();
		System.out.println("가보: " + me.gabo);
		System.out.println("가훈: " + me.gahun);
		System.out.println(me.say());
		me.eat();
		System.out.println("아버지 나이: " + me.getNai());
		System.out.println("집 수: " + me.getHouse());
		System.out.println(me.binking());
	}
}
