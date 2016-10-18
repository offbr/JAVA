package lambdapack;

public class Jikwon {
	int bunho;
	String irum;
	String junhwa;
	
	public Jikwon(int bunho, String irum, String junhwa) {
		this.bunho = bunho;
		this.irum = irum;
		this.junhwa = junhwa;
	}
	
	@Override //object 
	public String toString() { //주소를 반환
		return bunho + "," + irum + "," + junhwa;
	}
}
