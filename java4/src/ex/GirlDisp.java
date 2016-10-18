package ex;

import java.util.ArrayList;
import java.util.Scanner;

public class GirlDisp {
	ArrayList<GirlData> list = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
	GirlData girl = null;
	public void calc(){
		GirlData girl = new GirlData();
		System.out.print("이름: ");
		String name = sc.next();
		System.out.print("나이: ");
		int nai = sc.nextInt();
		System.out.print("키: ");
		int ki = sc.nextInt();
		
		girl.setName(name);
		girl.setNai(nai);
		girl.setKi(ki);
		list.add(girl);
	}
	
	public void show(){
		while(true){
			calc();
			System.out.println("계속 하시겠습니까(Y/N): ");
			String exit = sc.next();
			if(exit.equals("y")){
				continue;
			}else{
				disp();
				break;
			}
		}
	}

	public void disp(){
		for (int i = 0; i < list.size(); i++) {
			girl = list.get(i);
			System.out.println(girl.getName() + " " + girl.getNai() + " " + girl.getKi());
		}
	}
}
