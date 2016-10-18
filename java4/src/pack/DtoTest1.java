package pack;

import java.util.ArrayList;

public class DtoTest1 {
	ArrayList<StudentDto> list = new ArrayList<>();
	
	//DTO type 유추
	public void aa(){
		String[] persons = new String[3];
		persons[0] = "홍길동";
		persons[1] = "고길동";
		persons[2] = "나길동";
				
		for (String s : persons){
			System.out.println(s);
		}
	}

	//DTO type으로 자료 저장
	public void insertData(){
		StudentDto dto = null;
		
		dto = new StudentDto(); //Data Transfer Object : 레코드 단위의 자료 처리용 기억장소
		dto.setHakbun("ks10");
		dto.setIrum("손오공");
		dto.setJusmsu(90);
		list.add(dto); //첫번째 학생 저장
		
		dto = new StudentDto(); 
		dto.setHakbun("ks20");
		dto.setIrum("저팔계");
		dto.setJusmsu(100);
		list.add(dto); //두번째 저장
		
		dto = new StudentDto(); //Data Transfer Object : 레코드 단위의 자료 처리용 기억장소
		dto.setHakbun("ks30");
		dto.setIrum("사오정");
		dto.setJusmsu(70);
		list.add(dto); //1명 저장		
	}
	
	public void showData(){
		System.out.println("학생수는 " + list.size());
		
		for(int i = 0; i < list.size(); i++){
			StudentDto dto = new StudentDto();
			dto = list.get(i);
			System.out.println(dto.getHakbun() + " " + dto.getIrum() + " " + dto.getJusmsu());
		}
	}
	
	public static void main(String[] args) {
		DtoTest1 test1 = new DtoTest1();
		test1.aa();                                                                                            
		test1.insertData(); // DTO type으로 컬렉션에 담기
		test1.showData();	// DTO type으로 자료 읽은 후 출력하기
		System.out.println(test1.list.get(2));

	}
}
