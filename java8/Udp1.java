import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Udp1 { // UDP로 송신
	public static void main(String[] args) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("보낼 자료 입력: ");
			String msg = in.readLine();
			
			DatagramSocket dsoc = new DatagramSocket();
			/*
			InetAddress ia = InetAddress.getByName("192.168.0.10"); //255
			DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length, ia, 7777);
			dsoc.send(dp);
			dsoc.close();
			*/
			for (int i = 1; i < 254; i++) {
				InetAddress ia = InetAddress.getByName("192.168.0." + i);	
				DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length, ia, 7777);
				dsoc.send(dp);
			}
			dsoc.close();
			System.out.println("전송 완료");
		} catch (Exception e) {
			System.out.println("err : " + e);
		}
		
	}
}

































