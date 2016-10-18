import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Ex_RmiDanImpl extends UnicastRemoteObject implements Ex_RmiDanInter{
	public Ex_RmiDanImpl() throws RemoteException{
	
	}
	
	@Override
	public String dansu(int dan) throws RemoteException {
		String result = "";
		for (int i = 1; i < 10; i++) {
			result += "\n" + dan + "*" + i + " = " + (dan*i);
		}
		return "\n"+ "---" + dan + "단---" + result;
	}
}
