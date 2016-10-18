import java.rmi.Naming;

public class RmiHelloClient {

	public static void main(String[] args) {
		try {
			RmiHelloInter h = (RmiHelloInter)Naming.lookup("rmi://192.168.0.15:1099/ks");
			String result = h.sayHello("그린컴"); //Rmi 수행
			System.out.println("RMI 수행 결과: \n" + result);
		} catch (Exception e) {
			System.out.println("Client err: " + e);
		}

	}

}
