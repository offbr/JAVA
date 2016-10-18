import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class NetTest3Client {

	public static void main(String[] args) {
		try {
			//InetAddress ia = InetAddress.getByName("127.0.0.1");
			//Socket socket = new Socket(ia, 9999);
			
			Socket socket = new Socket("192.168.0.", 9999);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true); //auto flush();
			out.println("안녕 " + "\n"); ///서버로 전송
			
			out.close(); socket.close();
		} catch (Exception e) {
			System.out.println("client err : " + e);
		}

	}

}
