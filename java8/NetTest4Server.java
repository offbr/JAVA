import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetTest4Server {
	ServerSocket ss;
	Socket socket;
	PrintWriter out;
	BufferedReader reader;
	
	public NetTest4Server() {
		try {
			ss = new ServerSocket(8888);
		} catch (Exception e) {
			System.out.println("접속 에러: " + e);
			return;
		}
		System.out.println("서버 서비스 진행 중.....");
		
		try {
			socket = ss.accept();
			out = new PrintWriter(socket.getOutputStream(), true); // true = out.flush(); //전달
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //읽기
		} catch (Exception e) {
			System.out.println("오류: " + e);
		}
	}
	
	public void receiveMsg(){
		try {
			//서버가 수신한 자료
			String msg = reader.readLine(); 
			System.out.println("receive data: " + msg);
			
			//서버가 송신하는 자료
			out.println("from server : " + msg);
			reader.close(); out.close(); socket.close(); ss.close();
		} catch (Exception e) {
			System.out.println("receiveMsg err: " + e);
		}
	}
	
	public static void main(String[] args) {		
		while(true){
			NetTest4Server server = new NetTest4Server();
			server.receiveMsg();
		}
	}
}


























