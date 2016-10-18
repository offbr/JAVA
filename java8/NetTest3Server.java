import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class NetTest3Server {

	public static void main(String[] args) {
		ServerSocket ss = null;
		/*
		for (int i = 0; i <= 65536; i++) { //현재 내컴이 사용 중인 포트번호 확인
			try {
				ss = new ServerSocket(i);
				
				ss.close();	
			} catch (Exception e) {
				System.out.println(i + " 번 port는 현재 사용중입니다.");
			}
		}
		System.out.println("확인 종료");
		*/
		Socket socket = null;
		try {
			ss = new ServerSocket(9999);
			System.out.println("서버 서비스 중.....");
			socket = ss.accept();
			System.out.println("접속자 정보: " + socket.toString());
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String data = reader.readLine();
			System.out.println("수신자료: " + data);
			
			reader.close(); socket.close(); ss.close();
		} catch (Exception e) {
			System.out.println("server err: " + e);
		}
	}
}
