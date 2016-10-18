package etc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex_Test {
	
	public static void main(String[] args) {
		InetAddress ia = null;
		Socket soc = null;
		DataInputStream in;
		DataOutputStream out;
		try {
			ia = InetAddress.getByName("192.168.0.6");
			soc = new Socket(ia, 10001);
			
			in = new DataInputStream(soc.getInputStream());
			String str1 = in.readUTF();
			System.out.println(str1);
			

			out = new DataOutputStream(soc.getOutputStream());
			String str = "안녕 난 클라이언트야";
			out.writeUTF(str);
			
			//out.flush();
			
			
			
			in.close();
			out.close();
			soc.close();
		} catch (Exception e) {
			System.out.println("접속 오류 : " + e);
			System.exit(-1);
		}
	}
}
