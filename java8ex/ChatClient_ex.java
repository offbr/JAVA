import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient_ex implements Runnable{
	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	String msg, str;
	Scanner sc = new Scanner(System.in);
	
	public ChatClient_ex() {
		try {
			socket = new Socket("127.0.0.1", 5555);	
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			
			Thread t1 = new Thread(this);
			t1.start();	
			
			while (true) {
				if (out != null) {
					try {
						str = sc.nextLine();
						out.writeUTF("Client >" + str + "\n");
						System.out.println("Client >" + str + "\n");
						if(str.equals("exit")){
							in.close(); out.close(); socket.close();
							System.exit(0);
							break;
						}
					} catch (IOException e1) {
						System.out.println("쓰기 에러: " + e1);
					}
				}
			}			
		} catch (Exception e) {
			System.out.println("서버 에러: " + e);
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
		new ChatClient_ex();
	}
}
