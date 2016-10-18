package mypack;

public class ProductionMain {

	public static void main(String[] args) {
		Production apple = new Production();
		apple.display();
		apple.name = "사과";
		apple.display(1000);
		apple.display();
		apple.setProductionDay("2016년 6월 1일");
		Production.origin = "중국"; 
		apple.display();
		
		Production orange = new Production("오렌지");
		orange.display(2000);
		orange.setProductionDay("방금");
		orange.display();
		
		Production.origin = "한국";
		
		orange.display();
		
		Production pie = new Production("파이", 3000, "일주일전");
		pie.display();
		

	}

}
