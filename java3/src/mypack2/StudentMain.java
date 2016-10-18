package mypack2;

public class StudentMain {

	public static void main(String[] args) {
		//Student student = new Student();
		Student student = new Student();
		System.out.println(student);
		System.out.println(student.toString());
		
		System.out.println("----------------------");
		StudentTom tom = new StudentTom();
		System.out.println(tom.name + " " + tom.grade + " " + tom.gender);
		
		StudentTom tom2 = new StudentTom("톰", 1, "남");
		System.out.println(tom2.name + " " + tom2.grade + " " + tom2.gender);
		tom2.study();
		
		System.out.println("----------------------");
		StudentJames james = new StudentJames();
		System.out.println(james.name + " " + james.grade + " " + james.gender);
		james.study();
	}

}
