package pack2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileIoTest2 {
	//2byte 단위로 자료 처리 : text 자료만 처리
	//모든 형태의 자료를 처리
	
	public void write_file(File file, ArrayList<String> str_list){ //파일명과 대상자료
		try {	
			//FileWriter : 텍스트 파일로 저장			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for(String str: str_list){
				writer.write(str, 0, str.length()); 
				writer.newLine(); //line skip
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("write_file에러: " + e);
		}
	}
	
	
	public void read_file(File file){ //파일명
		try {	
			//FileReader : 텍스트 파일로 읽기
			BufferedReader reader = new BufferedReader(new FileReader(file));	
			
			String oneline;
			StringBuffer buffer = new StringBuffer(); //문자열 누적용 // StringBuilder등
			while((oneline = reader.readLine()) != null) {
				buffer.append(oneline+ "\n"); //문자열 누적 StringBuffer 
			}
			System.out.println(buffer.toString()); //StringBuffer의 내용 출력 // 뽑을땐 toString()
			reader.close();
		} catch (Exception e) {
			System.out.println("read_file에러: " + e);
		}
	}
	
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>(); //문자열에 ArrayList를 쓰진 않는다. 문자열은 별도취급.
		list.add("푸하하하");
		list.add("Have a nice week");
		
		//File file = new File("c:/work/abc.txt");
		
		FileIoTest2 test1 = new FileIoTest2();
		//test1.write_file(file, list);
		File file = new File("c:/work/kbs.csv");
		test1.read_file(file);
	}
}
