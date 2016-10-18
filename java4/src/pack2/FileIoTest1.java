package pack2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileIoTest1 {
	//1byte 단위로 자료 처리 : class의 이름에 stream이 들어 있다.
	//모든 형태의 자료를 처리
	
	public void write_file(File file, ArrayList<String> str_list){ //파일명과 대상자료
		try {	
			// 모든메소드에 에러를 잡고 싶을경우 메인메소드에 넣어준다
			// 따로따로 모두 잡고 싶으면 분리해서 각 메소드에 넣어준다.
			//BufferedWriter : 문자열 stream에 연결해 버퍼를 제공하는 보조 stream
			//OutputStreamWriter : 바이트 출력 Stream에 연결되어 Writer로 변환시키는 역할
			//FileOutputStream : 바이트 단위로 자료를 저장하는 바이트 기반 출력 전용 stream
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
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
			// 모든메소드에 에러를 잡고 싶을경우 메인메소드에 넣어준다
			// 따로따로 모두 잡고 싶으면 분리해서 각 메소드에 넣어준다.
			//BufferedReader : 문자열 stream에 연결해 버퍼를 제공하는 보조 stream
			//InputputStreamWriter : 바이트 출력 Stream에 연결되어 Reader로 변환시키는 역할
			//FileInputStream : 바이트 단위로 모든자료를 자료를 읽는 바이트 기반 입력 전용 stream 
			BufferedReader reader = new BufferedReader( //"utf-8"은 선택사항.
					new InputStreamReader(new FileInputStream(file), "utf-8"));
		
		/*	while(true){ //eof까지 읽기 계속
				String oneline = reader.readLine();
				if(oneline == null) break;
				System.out.println(oneline);
			}
			reader.close();
		*/
			
			String oneline;
			String imsi = "";
			StringBuffer buffer = new StringBuffer(); //문자열 누적용 // StringBuilder등
			while((oneline = reader.readLine()) != null) {
				//System.out.println(oneline);
				//imsi += oneline + "\n"; //1. 담아두고 //문자열엔 쓰면 안되는 방식(속도저하) 
				buffer.append(oneline+ "\n"); //문자열 누적 StringBuffer 
			}
			//System.out.println(imsi); //2. 어디선가 찍는다 //문자열엔 쓰면 안되는 방식(속도저하)
			System.out.println(buffer.toString()); //StringBuffer의 내용 출력 // 뽑을땐 toString()
			reader.close();
		} catch (Exception e) {
			System.out.println("read_file에러: " + e);
		}
	}
	
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>(); //문자열에 ArrayList를 쓰진 않는다. 문자열은 별도취급.
		list.add("하하하");
		list.add("Have a nice week");
		
		File file = new File("c:/work/abc.txt");
		
		FileIoTest1 test1 = new FileIoTest1();
		test1.write_file(file, list);
		
		test1.read_file(file);
	}
}
