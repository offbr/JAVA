package exex;

public class Regular extends Employee{
	private int salary = 0;
	
		
	public Regular(String irum, int nai, int salary) {
		super(irum, nai);
		this.salary = salary;
	}
	
	@Override
	public double pay() {
		return salary;
	}
	
	@Override
	public void print() {
		display();
		System.out.println(", 급여 : " + pay());
	}
}
