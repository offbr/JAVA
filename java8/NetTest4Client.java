import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class NetTest4Client {
	Socket socket;
	PrintWriter out;
	BufferedReader reader;
	
	public NetTest4Client() {
		try {
			socket = new Socket("192.168.0.50", 8888);
			out = new PrintWriter(socket.getOutputStream(), true); //out.flush(); //쓰고
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //읽기
		} catch (Exception e) {
			System.out.println("NetTest4Client err: " + e);
		}
	}
	
	public void sendMsg(){
		try {
			//전송
			Scanner scanner = new Scanner(System.in);
			System.out.println("전송 자료 입력:");
			String data = scanner.nextLine();
			out.println(data);
			
			//서버가 넘긴 자료 수신 후 출력
			String re_data = reader.readLine();
			System.out.println("수신자료는 " + re_data);
		} catch (Exception e) {
			System.out.println("sendMsg err: " + e);
		} finally {
			try {
				//reader.close(); out.close(); socket.close();
			} catch (Exception e2) {
				System.out.println("닫기 에러: " + e2);
			}
		}
	}
	
	public static void main(String[] args) {
		NetTest4Client client = new NetTest4Client();
		client.sendMsg();

	}

}
