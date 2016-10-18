package pack2;

import java.io.*;
import java.util.ArrayList;

public class FileIoTest2_1 {
	ArrayList<Test2_1DTO> list = new ArrayList<>();
	ArrayList<DTOex<String>> list2=new ArrayList<>();
	
	Test2_1DTO dto = null;
	String bb;

	public FileIoTest2_1() {
		File file = new File("c://work/aa.csv");
		read_file(file);
	}

	public void read_file(File file) { //파일 읽기
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String aa;
			while ((aa = reader.readLine()) != null) {
				bb = aa;
				calc();
			}
			disp(); 
		} catch (Exception e) {
			System.out.println("read_file에러: " + e);
		}
	}

	public void calc() { //dto 입력
		String number = bb.split(",")[0];
		String juso = bb.split(",")[1];
		String juca = bb.split(",")[2];//ASLDKJF, DSAFSDF AA.SPLIT(", ")
		dto = new Test2_1DTO();
		dto.setNumber(number);
		dto.setJuso(juso);
		dto.setJuca(juca);
		list.add(dto);
	}

	public void disp() { //dto 출력
		System.out.println("정거장명\t\t\t주소\t\t\t보관대수");
		for (Test2_1DTO dto : list) {
			String sss = dto.getNumber();
			/*if(sss.length() > 8){
				sss = sss.substring(0, 9);
			}*/
			System.out.println(sss+"\t"+ dto.getJuso() +"\t" + dto.getJuca());
		}
		System.out.println("총 건수: " + (list.size() -1));
	}

	public static void main(String[] args) {
		FileIoTest2_1 test = new FileIoTest2_1();
	}
}
