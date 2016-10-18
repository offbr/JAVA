package ex;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

public class ProductDisp {
	ArrayList<Product> list = new ArrayList<>();
	//Product bb = new Product();
	Calendar calendar = Calendar.getInstance();
	public void inputData(String[] args){
		for (int i = 0; i < args.length; i++) {
			StringTokenizer tokenizer = new StringTokenizer(args[i], ",");
			int saben = Integer.parseInt(tokenizer.nextToken());
			String name = tokenizer.nextToken();
			int yearmoney = Integer.parseInt(tokenizer.nextToken());
			int year = Integer.parseInt(tokenizer.nextToken());
			
			Product bb = new Product();
			bb.setSaben(saben);
			bb.setName(name);
			bb.setYearmoney(yearmoney);
			bb.setYear(year);
			list.add(bb);
		}
		disp();
	}
	public void disp(){
		System.out.println("사원 정보");
		System.out.println("사번\t이름\t기본급\t근무년수\t근속수당\t공제액\t수령액");
		for (int i = 0; i < list.size(); i++) {
			Product bb = new Product();
			bb = list.get(i);
			
			int gunsu, sureng;
			double gongje;
			int year = calendar.get(calendar.YEAR) - bb.getYear();

			if(year <= 3){
				gunsu = 150000;
			}else if(year <= 8){
				gunsu = 450000;
			}else{
				gunsu = 1000000;
			}

			gongje = bb.getYearmoney() + gunsu;
			if(gongje >= 3000000){
				gongje = gongje * 0.005;
			}else if(gongje >= 2000000){
				gongje = gongje * 0.003;
			}else if(gongje < 2000000){
				gongje = gongje * 0.0015;
			} 
			
			sureng = (int) (bb.getYearmoney() + gunsu - gongje);
			
			System.out.println(bb.getSaben() + "\t"+
							bb.getName() + "\t" +
							bb.getYearmoney() + "\t" +
							year + "\t\t" +
							gunsu +"\t\t"+
							gongje + "\t" +
							sureng
							);
		}
		System.out.println("처리 건 수: " + list.size() + "건"); 
	}
}
