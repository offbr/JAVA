package exex;

abstract public class Employee {
	
	private String irum = "";
	private int nai = 0;	
	
	public Employee(String irum, int nai) {
		this.irum = irum;
		this.nai = nai;
	}
	
	abstract public double pay();
	
	abstract public void print();
	
	public void display() {
		System.out.print("이름: " + irum + ", 나이: " + nai);
	}
}





