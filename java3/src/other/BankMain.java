package other;

//import mypack.Bank; // import 로 package.class;
import mypack.*; //class 로딩 (전체는 아니고 지정되는 class 만 로딩된다)
public class BankMain {
//package가 다르면 class이름이 같아도 된다
	public static void main(String[] args) {
		//mypack.Bank james = new mypack.Bank(); //앞에 class앞에 경로명(package.class)
		Bank james = new Bank(); 
		//System.out.println(james.imsi);
		System.out.println(james.imsi2);
	}

}
