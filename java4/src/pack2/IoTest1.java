package pack2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class IoTest1 {

	public static void main(String[] args) throws Exception{
		//1 byte 단위 io : stream
		/*
		System.out.println("1바이트 입력: ");
		int a = System.in.read(); // 1바이트라 한글x // Unhandled exception type IOException 예외처리해야한다
		System.out.println(a + " " + (char)a);
		*/
		System.out.println("\n문자열 출력");
		//문자열 출력 : stream
		OutputStreamWriter os = new OutputStreamWriter(System.out);
		
		/*
		byte b = 97;
		os.write(b);
		os.flush();
		*/
		
		/*
		BufferedWriter bw = new BufferedWriter(os);
		PrintWriter out = new PrintWriter(bw);
		out.println(123);
		out.print("문자열이 출력");
		out.close(); //자원반납(자원 해제)
		bw.close();
		os.close(); // 필수사항은 아니지만 Stream 사용한 후 close 해주는게 좋다
		*/
		
		System.out.println();
		//문자열 파일로 출력 : 2byte 단위 처리 //모든 파일은 열고 닫아야한다
		File f = new File("c:/work/iotest.txt"); //text 파일
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw1 = new BufferedWriter(fw, 1024); //1024, 2048 안 쓰면 자동으로 1024로 잡힌다
		PrintWriter out1 = new PrintWriter(bw1);
		out1.println("12345");
		out1.print("오늘은 금요일");
		out1.println("이렇게 한 주가 지나갑니다");
		out1.println("모두 화이팅");
		out1.close();
		bw1.close();
		fw.close();
		
		System.out.println();
		//문자열 파일로 입력 : 2byte 단위 처리 // 모든 파일은 열고 닫아야한다
		File f2 = new File("c:/work/iotest.txt"); // 유닉스/  윈도우\\
		FileReader fr = new FileReader(f2);
		BufferedReader br2 = new BufferedReader(fr);
		while(true){ //eof까지 읽기 계속
			String str = br2.readLine();
			if(str == null) break;
			System.out.println(str);
		}
		br2.close();
		fr.close();
	}	
}

















