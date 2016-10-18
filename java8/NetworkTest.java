import java.net.InetAddress;

public class NetworkTest {

	public static void main(String[] args) {
		InetAddress address = null;
		InetAddress[] arr = null;
		
		try {
			address = InetAddress.getByName("www.daun.net"); //도메인의 아이피주소
			System.out.println(address); 
			System.out.println(address.getHostAddress()); //아이피
			System.out.println(address.getHostName()); //도메인네임
			
			System.out.println();
			address = InetAddress.getLocalHost(); // 내 아이피주소
			System.out.println(address.getHostAddress()); //아이피
			System.out.println(address.getHostName()); //내 네임
			
			System.out.println();
			arr = InetAddress.getAllByName("www.naver.com");
			System.out.println(arr.length);
			for(InetAddress aa:arr){
				System.out.println(aa.getHostAddress()); //아이피
				System.out.println(aa.getHostName()); //내 네임
			}
		} catch (Exception e) {
			System.out.println("network err: " + e);
		}
	}
}
