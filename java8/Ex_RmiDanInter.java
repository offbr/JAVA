import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Ex_RmiDanInter extends Remote{
	public String dansu(int dan) throws RemoteException;
}
