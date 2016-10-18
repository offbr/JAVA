package pack;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DtoTest2 {
	ArrayList<HaksaengDto> list;
	//HaksaengDto dto = null;
	public DtoTest2() {
		list = new ArrayList<>();
	}
	
	public void inputData(String[] datas){
		//문자열 자르기
		/*
		StringTokenizer tokenizer = new StringTokenizer("kbs,mbc", ",");
		String ss1 = tokenizer.nextToken();
		String ss2 = tokenizer.nextToken();
		System.out.println(ss1 + " " + ss2);
		*/
		
		for (int i = 0; i < datas.length; i++) {
			StringTokenizer tokenizer = new StringTokenizer(datas[i], ",");
			String irum = tokenizer.nextToken();
			int kor = Integer.parseInt(tokenizer.nextToken());
			int eng = Integer.parseInt(tokenizer.nextToken());
			int mat = Integer.parseInt(tokenizer.nextToken());
			//System.out.println(irum + " " + kor);
			HaksaengDto dto = new HaksaengDto();
			dto.setName(irum);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			list.add(dto);
		}
		
	}
	
	public void printData(){
		System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
		for (int i = 0; i < list.size(); i++) {
			HaksaengDto dto = new HaksaengDto();
			dto = list.get(i);
			int tot = dto.getKor() + dto.getEng() + dto.getMat();
			int avg = tot / 3;
			System.out.println(
					dto.getName()+ "\t" + 
					dto.getKor() + "\t" + 
					dto.getEng() + "\t" + 
					dto.getMat() + "\t" + 
					tot + "\t" + avg
			);		
		}
		
		System.out.println();
		System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
		for(HaksaengDto d : list){
			int tot2 = d.getKor() + d.getEng() + d.getMat();
			int avg2 = tot2 / 3;
			System.out.println(
					d.getName()+ "\t" + 
					d.getKor() + "\t" + 
					d.getEng() + "\t" + 
					d.getMat() + "\t" + 
					tot2 + "\t" + avg2
			);
		}
	}
	
	public static void main(String[] args) {
		String [] datas = new String[3];
		datas[0] = "김밥,100,100,100";
		datas[1] = "비빔밥,80,85,88";
		datas[2] = "공기밥,75,90,78";
		
		DtoTest2 test2 = new DtoTest2();
		test2.inputData(datas);
		test2.printData();
	}
}
