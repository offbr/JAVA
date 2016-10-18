package ex;

import java.util.ArrayList;
import java.util.Scanner;

public class StoDtoDisp {
	ArrayList<StudDto> list = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
	
	public void InsertScanner(){
		System.out.print("이름: ");
		String irum = sc.next();
		System.out.print("국어: ");
		int kor = sc.nextInt();
		System.out.print("영어: ");
		int eng = sc.nextInt();

		StudDto dto = new StudDto();
		dto.setIrum(irum);
		dto.setKor(kor);
		dto.setEng(eng);
		list.add(dto);
	}
	
	public void on(){
		aa:while(true){
			InsertScanner();
			bb:while(true){
			System.out.print("계속할까요?(y/n): ");
			String exit = sc.next();
				if(exit.equals("y")){
					continue aa;
				}else if(exit.equals("n")){
					print();
					System.exit(0);
					//break aa;
				}else{
					continue bb;
				}
			}	
		}
	}
	
	public void print(){
		System.out.println("\n학생정보\n");
		System.out.println("이름\t"+  "국어\t"  + "영어\t"  + "총점");
		for (int i = 0; i < list.size(); i++) {
			StudDto dto = new StudDto();
			dto = list.get(i);
			System.out.println(dto.getIrum() + "\t" + dto.getKor() + "\t" + dto.getEng() + "\t" +dto.getHap());
		}
		System.out.println("\n응시 인원은: " + list.size());
	}
}
