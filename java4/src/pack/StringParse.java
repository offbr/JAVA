package pack;

public class StringParse {

	public static void main(String[] args) {
		String ss = "하하 호호,abc 123 12 ABCD ab a";
		String[] arr = ss.split("[ ,]");
		for(String a:arr){
			System.out.println(a);
		}

	}

}
