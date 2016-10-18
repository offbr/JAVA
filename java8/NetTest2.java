import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class NetTest2 {
	
	public static void main(String[] args) {
		try {
			InetAddress ia = InetAddress.getByName("www.mnet.com");
			Socket socket = new Socket(ia, 80); //서버에 소켓으로 연결
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			out.println("GET http://www.daum.net"); //자료를 요청
			out.flush(); //요청 후 버퍼를 지움
			
			
			//서버에서 넘어 온 자료 출력
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true){
				String str = reader.readLine();
				if(str == null) break;
				System.out.println(str);
			}
			reader.close(); out.close(); socket.close();
		} catch (Exception e) {
			System.out.println("err :" + e);
		}
	}
}
