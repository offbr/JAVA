package mypack2;

//import java.lang.*;

//public class Student extends Object{ //모든 class 는 object의 파생클래스, 즉 Student의 부모클래스는 object
public class Student { //protected가 멤버로 나오면 상속 
/*	
	@Override
	public String toString() { //override 재정의
		return "자바만세";
	}
*/
	protected String name; 
	protected int grade;
	protected String gender;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Student(String name, int grade, String gender) {
		this.name = name;
		this.grade = grade;
		this.gender = gender;
	}
	
	public void study(){
		System.out.println("학생은 공부를 해야한다. 스승을 헐 뜯지 말고...");
	}
}
