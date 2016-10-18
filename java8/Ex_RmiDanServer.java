import java.rmi.Naming;

public class Ex_RmiDanServer {

	public static void main(String[] args) throws Exception{
		Ex_RmiDanImpl impl = new Ex_RmiDanImpl();
		Naming.rebind("rmi://192.168.0.15:1099/ks", impl);
		System.out.println("구구단 준비완료");
	}

}
