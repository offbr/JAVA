package etc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex_Test2 {
	
	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket soc = null;
		DataInputStream in;
		DataOutputStream out;
			try {
				ss = new ServerSocket(10001);
				System.out.println("sever ready....");
			} catch (Exception e) {
				System.out.println("해당 포트가 열려 있습니다");
				System.exit(-1);
				
			}
			try {
				
				soc = ss.accept(); //연결을 기다린다
				System.out.println("접속자: " + soc.toString());
				in = new DataInputStream(soc.getInputStream());
				String str = in.readUTF();
				System.out.println("전송 내용: " + str);
				
				
				out = new DataOutputStream(soc.getOutputStream());
				String str1 = "메롱";
				out.writeUTF(str1);
				
				out.close();
				in.close();
				soc.close();
			} catch (Exception e) {
				
			}
	}
}
