import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer_ex implements Runnable{
	ServerSocket serverSocket = null;
	Socket socket = null;
	DataInputStream in;
	DataOutputStream out;
	String str, msg;
	Scanner sc = new Scanner(System.in);
	
	public ChatServer_ex() {
		Server();
	}
	
	public void Server(){
		try {
			serverSocket = new ServerSocket(5555);
			socket = serverSocket.accept();
			System.out.println(socket.toString().substring(13, 22) + " 님이 접속하셨습니다\n");			
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());

			Thread t1 = new Thread(this);
			t1.start();

			while (true) {
				if (out != null) {
					try {
						str = sc.nextLine();
						out.writeUTF("server >" + str + "\n");
						System.out.println("server >" + str + "\n");
						if(str.equals("exit")){
							in.close(); out.close(); socket.close(); serverSocket.close();
							System.exit(0);
							break;
							
						}
					} catch (IOException e1) {
						System.out.println("쓰기에러: " + e1);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("서버 에러 : " + e);
		}
		
	}
	
	@Override
	public void run() {
		while (in != null) {
			try {
				msg = in.readUTF();
				System.out.println(socket.toString().substring(13, 22) + " > " + msg + "\n");
			} catch (Exception e) {
				//System.out.println("읽기 에러: " + e);
			}
		}
	}

	public static void main(String[] args) {
		new ChatServer_ex();
	}
}



















