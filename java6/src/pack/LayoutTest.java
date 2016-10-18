package pack;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LayoutTest extends Frame implements ActionListener{
	private Panel pn1 = new Panel();
	private Panel pn2 = new Panel();
	private Panel pn3 = new Panel();
	private Panel pn4 = new Panel();
	private Panel pn5 = new Panel();
	private Panel pn6 = new Panel();
	private Button btnGo; 
	private TextField txtBun, txtIrum;
	private CardLayout card = new CardLayout();
	
	public LayoutTest() {
		//Frame : BorderLayout(east, west, south, north, center) 기본장착
		setLayout(new GridLayout(2, 1)); //Frame을 2행 1열 배치형태로 레이아웃 변경

		//첫번째 행
		Label lbl1 = new Label("bunho: "); //메시지 표시 전용 클래스(컴포넌트)
		txtBun = new TextField("", 20); //키보드로 자료 입력용 클래스(컴포넌트)
		pn1.add(lbl1); //panel은 FlowLayout 기본장착(컨테이너)
		pn1.add(txtBun);
		pn1.setBackground(Color.yellow);
		this.add(pn1);
		
		Label lbl2 = new Label("Irum: "); //메시지 표시 전용 클래스(컴포넌트)
		txtIrum = new TextField("", 20); //키보드로 자료 입력용 클래스(컴포넌트)
		pn2.add(lbl2); //panel은 FlowLayout 기본장착(컨테이너)
		pn2.add(txtIrum);
		pn2.setBackground(Color.cyan);
		//this.add(pn2);
		pn3.setLayout(card); //FlowLayout을 cardLayout으로 변경 
		pn3.add("aa", pn1);
		pn3.add("bb", pn2);
		this.add(pn3);
		btnGo = new Button("ok");
		btnGo.addActionListener(this);
		pn4.add(pn3); //pn4 = FlowLayout
		pn4.add(btnGo);
		this.add(pn4);
	
		//두번째 행
		pn6.setLayout(new BorderLayout());
		pn6.setBackground(Color.red);
		pn5.add(new Label("My name is pn5"));
		pn6.add("Center", pn5);
		pn6.add("East", new Label("East"));
		pn6.add("West", new Label("West"));
		pn6.add("South", new Label("South", Label.CENTER));
		pn6.add("North", new Label("North", Label.CENTER));
		
		this.add(pn6);
			
		this.setTitle("Layout Test");
		this.setBounds(200, 200, 400, 300);
		this.setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("하하하핳");
		//System.out.println(e.getActionCommand());
		//if(e.getActionCommand().equals("ok")) //대소문자 구분
		if(e.getActionCommand().equalsIgnoreCase("ok")){ //대소문자 구분 안함
			btnGo.setLabel("click");
			card.show(pn3, "bb");
		}else{
			btnGo.setLabel("ok");
			card.show(pn3, "aa");
		}	
	}
	
	public static void main(String[] args) {
		new LayoutTest();
	}
}
