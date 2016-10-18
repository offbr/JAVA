package mypack4;

public class ResumeMain {
	public static void main(String[] args) {
		Resume resume = null;
		
		System.out.println("톰 이력서 작성 후 확인------------");
		R_Tom r_Tom = new R_Tom();
		r_Tom.setIrum("톰");
		r_Tom.setPhone("111-1111");
		r_Tom.setJuso("강남구 테헤란로 123");
		r_Tom.print();
		
		System.out.println();
		r_Tom.playJava(false);
		//r_Tom.changeData(); //불가 static메소드.
		Resume.changeData();
		r_Tom.abc(); //톰 고유 메소드
		
		System.out.println("\n제임스 이력서 작성 후 확인------------");
		R_James r_James = new R_James();
		r_James.setIrum("제임스");
		r_James.setPhone("111-2222");
		r_James.setSkill("웹 프로그래밍 전문가");
		r_James.print();
		r_James.playJava(true);
		
		System.out.println("\n인사담당자가 이력서 확인------------");
		Resume[] resBox = new Resume[2];
		resBox[0] = r_Tom;
		resBox[1] = r_James;
		
		for(Resume r:resBox){
			r.print();
			System.out.println();
		}
		
		
		
		
		
	}
}
