package etcMain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientBack {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private ClientGui gui;
	
	public final void setGui(ClientGui gui) {
        this.gui = gui;
    }
	
	public void connet() {
		try {
			socket = new Socket("127.0.0.1", 7777);
			System.out.println("sever 연결");
			
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			while (in != null) {
				String str = in.readUTF();
				
			}
			
		} catch (Exception e) {
			System.out.println("에러가 난다 " + e);
		}
	}
	
	public static void main(String[] args) {
		ClientBack clientBack = new ClientBack();
		clientBack.connet();
	}

}
