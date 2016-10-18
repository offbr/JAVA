import java.rmi.Naming;
import java.util.Scanner;

public class Ex_RmiDanClient {

	public static void main(String[] args) {
		try {
			Ex_RmiDanInter d = (Ex_RmiDanInter)Naming.lookup("rmi://192.168.0.15:1099/ks");
			Scanner sc = new Scanner(System.in);
			System.out.println("원하는 단 입력: ");
			int dan = sc.nextInt();
			String result = d.dansu(dan);
			System.out.println("RMI 수행 결과: " + result);
		} catch (Exception e) {
			System.out.println("Client err:"+ e);
		}

	}

}
