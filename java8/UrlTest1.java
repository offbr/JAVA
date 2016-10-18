import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlTest1 {
	
	public UrlTest1(String str) {
		try {
			URL url = new URL(str); //서버와 접속
			
			InputStream in = url.openStream(); //읽어들여온다
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String ss;
			
			while((ss = br.readLine()) != null){
				System.out.println(ss);
			}
			in.close();
			br.close();
		} catch (Exception e) {
			
		}
	}
	public static void main(String[] args) {
		new UrlTest1("http://www.naver.com:80/index.html");
	}
}
