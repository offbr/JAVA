import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Date_ChatServer implements Runnable{
	ServerSocket ss;
	Service service;
	ArrayList<Service> list = new ArrayList<>(); //들어오는 메시지를 담는다.
	
	public Date_ChatServer() {
		try {
			ss = new ServerSocket(5557);
			System.out.println("채팅 서비스 중...");
			new Thread(this).start();
		} catch (Exception e) {
			System.out.println("ChatServer 생성자 에러: " + e);
			System.exit(0);
		}
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Socket socket = ss.accept(); //접속 대기 
				service = new Service(socket);
				service.start();
				service.chat_name = service.in.readUTF(); //접속하자마자 닉네임을 가지고 온다.
				//System.out.println("대화명 : " + service.chat_name);
				
				list.add(service); //모든 클라이언트와의 통신 socket를 컬렉션에 저장
				service.messageAll("/c" + service.chat_name); // /c대화명
			} catch (Exception e) {
				System.out.println("ChatServer run 에러: " + e);
			}
			
		}
	}
	
	//내부 클래스: 채팅에 참여한 각 클라이언트 처리 용
	class Service extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
		String chat_name;
		
		public Service(Socket socket) {
			try {
				this.socket = socket;
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				System.out.println("Service 생성자 에러: " + e);
			}
		}
		
		@Override
		public void run() {
			while(true){
				try {
					
				} catch (Exception e) {
					break;
				}
			}
		}
		
		public void messageAll(String msg){
			try { //메시지 받기만
				for (int i = 0; i < list.size(); i++) {
					Service ser = (Service)list.get(i);
					ser.messageSend(msg);
				}
			} catch (Exception e) {
				System.out.println("messageAll err: " + e);
			}
		}
		
		public void messageSend(String msg){
			try { //Client로 보내는 메시지
				out.writeUTF(msg + "\n"); //직렬화 시킨 후 클라이언트로 메시지 전송
			} catch (Exception e) {
				//System.out.println("messageSend err: " + e);
			}
		}
	}
	
	public static void main(String[] args) {
		new Date_ChatServer();
	}
}



