import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Udp2 { //수신

	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(7777);
			byte[] data = new byte[65000]; //65535
			
			DatagramPacket packet = new DatagramPacket(data, data.length);
			System.out.println("서비스 시작...");
			
			while(true){
				socket.receive(packet);
				System.out.println("보낸 곳 주소: " + packet.getAddress().getHostAddress());
				System.out.println("자료 크기: " + packet.getLength());
				System.out.println("자료 내용:" + new String(packet.getData()).trim());
			}
		} catch (Exception e) {
			System.out.println("수신 에러");
		}

	}

}
