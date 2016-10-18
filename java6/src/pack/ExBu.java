package pack;

import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.FeatureDescriptor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ExBu extends JPanel implements ActionListener{
	private JTextField txt1, txt2, txt3;
	private JButton btn1, btn2, btn3, btn4; //등록, 수정, 확인, 삭제
	private JLabel relbl, lbl4, lbl5;
	private JTextArea txta;
	ArrayList<ExBuDTO> list;
	ExBuDTO dto;
	
	int i = 0;
	int j = 0;
	int a = 0;
	JLabel[] lbl6 = new JLabel[10];
	
	public ExBu() {
		list = new ArrayList<>();
		
		setLayout(new GridLayout(6, 1));
		//1행
		JLabel lbl1 = new JLabel("번호: ");
		JPanel pn1 = new JPanel();
		txt1 = new JTextField("", 15);
		pn1.add(lbl1);
		pn1.add(txt1);
		pn1.setLayout(new FlowLayout(FlowLayout.CENTER));
		add(pn1);
		
		
		
		//2행
		JLabel lbl2 = new JLabel("이름: ");
		JPanel pn2 = new JPanel();
		txt2 = new JTextField("", 15);
		pn2.add(lbl2);
		pn2.add(txt2);
		pn2.setLayout(new FlowLayout(FlowLayout.CENTER));
		add(pn2);
	
		
		//3행
		JLabel lbl3 = new JLabel("전화: ");
		JPanel pn3 = new JPanel();
		txt3 = new JTextField("", 15);
		pn3.add(lbl3);
		pn3.add(txt3);
		pn3.setLayout(new FlowLayout(FlowLayout.CENTER));
		add(pn3);
		
		
		JPanel pn4 = new JPanel();
		btn1 = new JButton("등록");
		btn2 = new JButton("수정");
		btn3 = new JButton("삭제");
		btn4 = new JButton("출력");
		pn4.add(btn1);
		pn4.add(btn2);
		pn4.add(btn3);
		pn4.add(btn4);
		add(pn4);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		//4행
		JPanel pn5 = new JPanel();
		txta = new JTextArea();
		lbl4 = new JLabel("결과: ", JLabel.CENTER);
		pn5.add(lbl4);
		pn5.add(txta);
		add(pn5);
	
		//5행
		lbl5 = new JLabel("인원수: ", JLabel.CENTER);
		add(lbl5);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if(e.getActionCommand().equals("수정")){
			txt1.setText("");
			txt2.setText("");
			txt3.setText("");
		}
		if(e.getActionCommand().equals("등록")){
				String number = txt1.getText();
				String irum = txt2.getText();
				String phone = txt3.getText();
				dto = new ExBuDTO();
				dto.setNumber(number);
				dto.setIrum(irum);
				dto.setPhone(phone);
				list.add(dto);	
					
		}
		if(e.getActionCommand().equals("삭제")){
			lbl5.setText("인원수: ");
			txta.setText("");
			list.remove(dto);
		}
		if(e.getActionCommand().equals("출력")){
			for(ExBuDTO dto: list){
				txta.append("번호: " + dto.getNumber() +", 이름: "+ dto.getIrum() + ", 전화번호: "+ dto.getPhone()+"\n");
				lbl5.setText("인원수: " + list.size());
				listTxt();
			}
		}
	}
	private void listTxt(){
		try {
		BufferedWriter writer = new BufferedWriter(new FileWriter("c:/work/log1.txt"));
		PrintWriter pw = new PrintWriter(writer);
		writer.append(txta.getText());
		writer.newLine();
		writer.close();			
		} catch (Exception e) {
			System.out.println("저장에러: " + e);
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		ExBu bu = new ExBu();
		
		frame.setTitle("회원");
		frame.getContentPane().add(bu);
		frame.setBounds(200, 200, 400, 800);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
