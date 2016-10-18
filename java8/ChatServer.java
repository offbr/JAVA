import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class ChatServer implements Runnable{
	ServerSocket ss;
	Service service;
	ArrayList<Service> list = new ArrayList<>(); //들어오는 메시지를 담는다.
	
	public ChatServer() {
		try {
			Collections.synchronizedList(list);
			ss = new ServerSocket(5558);
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
				service.chat_name = service.in.readLine(); //접속하자마자 닉네임을 가지고 온다.
				//System.out.println("대화명 : " + service.chat_name);
				
				list.add(service); //모든 클라이언트와의 통신 socket를 컬렉션에 저장
				service.messageAll("/c" + service.chat_name); // /c대화명
				
				/*for (int i = 0; i < list.size(); i++) {
					Service service = list.get(i);
					service.messageSend("/c" + service.chat_name);
				}*/
			} catch (Exception e) {
				System.out.println("ChatServer run 에러: " + e);
				return;
			}			
		}
	}
	
	//내부 클래스: 채팅에 참여한 각 클라이언트 처리 용
	class Service extends Thread {
		Socket socket;
		BufferedReader in;
		OutputStream out;
		String chat_name;
		
		public Service(Socket socket) {
			try {
				this.socket = socket;
				in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "euc-kr"));
				out = socket.getOutputStream();
			} catch (Exception e) {
				System.out.println("Service 생성자 에러: " + e);
				return;
			}
		}
		
		@Override
		public void run() {
			while(true){
				try {
					String msg = in.readLine(); //클라이언트 자료 수집
					if(msg == null || msg.equals("")) return;
					
					if(msg.charAt(0) == '/'){
						if(msg.charAt(1) == 'n'){ //대화명 변경 /n 새대화명
							if(msg.charAt(2) == ' '){ // /n옛이름 - 새이름
								messageAll("/n" + chat_name + "-" + msg.substring(3).trim());
								this.chat_name = msg.substring(3).trim();
							}
						}else if(msg.charAt(1) == 'q'){ //퇴장
							try {
								messageAll("/q" + chat_name);
								list.remove(service);
								socket.close();
							} catch (Exception e) {
								
							}
							break;
						}else if(msg.charAt(1) == 's'){ //대화명 /s대화명 - 메시지
							String name = msg.substring(2, msg.indexOf('-')).trim();
							for (int i = 0; i < list.size(); i++) {
								Service css = (Service)list.get(i); //위에 캐스팅 해주므로 생략 가능
								if(name.equals(css.chat_name)){
									css.messageSend(chat_name + " >(secret) " + msg.substring(msg.indexOf('-') +1));
								}
							}
						}
					}else{
						messageAll(chat_name + " > " + msg); //일반 메시지
					}
					
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
				out.write((msg + "\n").getBytes("euc-kr")); //직렬화 시킨 후 클라이언트로 메시지 전송
			} catch (Exception e) {
				//System.out.println("messageSend err: " + e);
			}
		}	
	}
	
	public static void main(String[] args) {
		new ChatServer();
	}
}
