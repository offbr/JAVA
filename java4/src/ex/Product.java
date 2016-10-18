package ex;


public class Product {
	private String name;
	private int saben, year, yearmoney;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSaben() {
		return saben;
	}
	public void setSaben(int saben) {
		this.saben = saben;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
		//this.year = day.get(Calendar.YEAR) - year;
	}
	public int getYearmoney() {
		return yearmoney;
	}
	public void setYearmoney(int yearmoney) {
		this.yearmoney = yearmoney;
	}
}
