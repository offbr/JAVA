package ex;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ExJepumDisp{
	ArrayList<ExJepum> list = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
	ExJepum jepum = null; 
	
	public void calc(){
		System.out.print("지역코드,상품명,수량: ");
		String aa = sc.next();
		String code = aa.split(",")[0];
		String name = aa.split(",")[1];
		int su = Integer.parseInt(aa.split(",")[2]);
		jepum = new ExJepum();
		jepum.setCode(code);
		jepum.setSangpum(name);
		jepum.setSu(su);			
		list.add(jepum);
	}
	public void dd(){
		while(true){
			calc();
			System.out.print("계속할까요(y/n): ");
			String exit = sc.next();
			if(exit.equals("n")){ 
			disp(); 
			break;
			}
		}
		sc.close();
	}
	public void disp(){
		String code;
		int danga = 0,  sa = 0, ga = 0, happsa = 0, happga = 0, hap = 0;
		System.out.println("\n지역\t상품명\t수량\t단가\t금액");
			
		for(ExJepum jepum : list){
			switch(jepum.getCode()){
				case "100": code = "강북"; break;
				case "200": code = "강남"; break;
				case "300": code = "강서"; break;
				default : code = "그외";
			}
			
			switch(jepum.getSangpum()){
				case "새우깡":	danga = 450; 
								sa++;
								happsa += jepum.getSu()*danga;
								break;
				case "감자깡":	danga = 300; 
								ga++;	
								happga += jepum.getSu()*danga;
								break;			
				default : danga = 0;
			}
			
			hap = jepum.getSu() * danga;
			
			System.out.println(	code + "\t" + 
							  	jepum.getSangpum() + "\t" + 
								jepum.getSu()+ "\t" +
								danga + "\t" +
								hap 
								);
		}	
		NumberFormat format = NumberFormat.getNumberInstance();
		System.out.println("\n소계");
		System.out.println("새우깡: " + sa + "\t합계: " + happsa);
		System.out.println("감자깡: " + ga + "\t합계: " + happga);
		System.out.println("총 건수: " + list.size() + "\t총 액: " + format.format(happga + happsa));
		System.err.println();
	}
	
	public static void main(String[] args) {
		ExJepumDisp cc= new ExJepumDisp();		
		cc.dd();
	}
}
