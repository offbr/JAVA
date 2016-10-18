package etcMain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SeverBack {
	private ServerSocket serverSocket;
	private Socket socket;
	private DataInputStream in;
	private	DataOutputStream out;
	private ServerGui gui;
	
	public final void setGui(ServerGui gui) {
        this.gui = gui;
    }
	
	public void setting() {
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("서버 대기중...");
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + " 접속");
			
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			while (in != null) {
			String str = in.readUTF();
			System.out.println("Client: " + str);
			gui.appendStr(str);
			}
		} catch (Exception e) {
			System.out.println("서버 에러가 난다 " + e);
		}
	}
	
	public static void main(String[] args) {
		SeverBack severBack = new SeverBack();
		severBack.setting();
	}

}
