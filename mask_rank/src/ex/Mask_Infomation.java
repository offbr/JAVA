package ex;

import designcomponent.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

import javax.swing.*;

public class Mask_Infomation extends JFrame implements ActionListener{
	BTextField txt_id, txt_pwd, txt_nick, txt_name, txt_tel, txt_image;
	private BTextArea txt_memo;
	private BButton btn_Search, btn_join, btn_cancle;
	private BComboBox comboBox_age, comboBox_gen;
	private String imgPath, image_name;
	private File file;
	
	/**
	 * Create the panel.
	 */
	public Mask_Infomation() {
		
		setTitle("회원가입");
		layInit();
		setBounds(440,110,465,657);
		setVisible(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void layInit(){
		getContentPane().setLayout(null);
		
		BPanel panel = new BPanel();
		panel.setBounds(12, 10, 426, 597);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		BLabel label = new BLabel("회원가입");
		label.setBounds(196, 10, 97, 15);
		panel.add(label);
		
		BLabel lbl_id = new BLabel("ID");
		lbl_id.setForeground(Color.WHITE);
		lbl_id.setBounds(12, 45, 39, 15);
		panel.add(lbl_id);
		
		txt_id = new BTextField();
		txt_id.setBounds(82, 42, 307, 21);
		panel.add(txt_id);
		txt_id.setColumns(10);
		
		BLabel lbl_pwd = new BLabel("Password");
		lbl_pwd.setForeground(Color.WHITE);
		lbl_pwd.setBounds(12, 79, 70, 15);
		panel.add(lbl_pwd);
		
		txt_pwd = new BTextField();
		txt_pwd.setBounds(82, 76, 307, 21);
		panel.add(txt_pwd);
		txt_pwd.setColumns(10);
		
		BLabel lbl_Nickname = new BLabel("Nickname");
		lbl_Nickname.setForeground(Color.WHITE);
		lbl_Nickname.setBounds(12, 121, 70, 15);
		panel.add(lbl_Nickname);
		
		txt_nick = new BTextField();
		txt_nick.setBounds(82, 118, 307, 21);
		panel.add(txt_nick);
		txt_nick.setColumns(10);
		
		BLabel lbl_name = new BLabel("Name");
		lbl_name.setForeground(Color.WHITE);
		lbl_name.setBounds(12, 161, 57, 15);
		panel.add(lbl_name);
		
		txt_name = new BTextField();
		txt_name.setBounds(81, 158, 308, 21);
		panel.add(txt_name);
		txt_name.setColumns(10);
		
		BLabel lbl_age = new BLabel("Age");
		lbl_age.setForeground(Color.WHITE);
		lbl_age.setBounds(12, 199, 57, 15);
		panel.add(lbl_age);
		
		comboBox_age = new BComboBox();
		comboBox_age.setModel(new DefaultComboBoxModel(
				new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
						"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", 
						"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", 
						"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", 
						"41", "42", "43", "44", "45", "46", "47", "48", "49", "50", 
						"51", "52", "53", "54", "55", "56", "57", "58", "59", "60", 
						"61", "62", "63", "64", "65", "66", "67", "68", "69", "70", 
						"71", "72", "73", "74", "75", "76", "77", "78", "79", "80", 
						"81", "82", "83", "84", "85", "86", "87", "88", "89", "90", 
						"91", "92", "93", "94", "95", "96", "97", "98", "99"}));
		comboBox_age.setBounds(82, 196, 97, 21);
		panel.add(comboBox_age);
		
		BLabel lbl_gen = new BLabel("Gen");
		lbl_gen.setBounds(226, 199, 39, 15);
		panel.add(lbl_gen);
		
		BLabel lbl_tel = new BLabel("Tel");
		lbl_tel.setBounds(12, 235, 57, 15);
		panel.add(lbl_tel);
		
		txt_tel = new BTextField();
		txt_tel.setBounds(82, 232, 307, 21);
		panel.add(txt_tel);
		txt_tel.setColumns(10);
		
		BLabel lbl_memo = new BLabel("Memo");
		lbl_memo.setBounds(12, 387, 57, 15);
		panel.add(lbl_memo);
		
		txt_memo = new BTextArea();
		txt_memo.setBackground(Cinfo.BUTTON_COLOR);
		txt_memo.setBounds(82, 275, 307, 216);
		panel.add(txt_memo);
		
		btn_join = new BButton("회원가입");
		btn_join.setBounds(122, 551, 97, 23);
		panel.add(btn_join);
		
		btn_cancle = new BButton("취소");
		btn_cancle.setBounds(233, 551, 97, 23);
		panel.add(btn_cancle);
		
		comboBox_gen = new BComboBox();
		comboBox_gen.setModel(new DefaultComboBoxModel(
				new String[] {"남자", "여자"}));
		comboBox_gen.setBounds(283, 196, 106, 21);
		panel.add(comboBox_gen);
		
		txt_image = new BTextField();
		txt_image.setBounds(82, 509, 227, 21);
		panel.add(txt_image);
		txt_image.setColumns(10);
		
		BLabel lbl_image = new BLabel("이미지첨부");
		lbl_image.setBounds(12, 512, 85, 15);
		panel.add(lbl_image);
		
		btn_Search = new BButton("찾기");
		btn_Search.setBounds(321, 508, 68, 23);
		panel.add(btn_Search);
		
		comboBox_age.addActionListener(this);
		comboBox_gen.addActionListener(this);
		btn_Search.addActionListener(this);
		btn_join.addActionListener(this);
		btn_cancle.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_join) {
			try { //id,pw,이름,닉네임,성별,나이,폰번호,메모,랭크순위,이미지,로그순
				if(!txt_id.getText().equals("")){
				String id_image = txt_id.getText().trim()+"_"+txt_nick.getText().trim()+"_"+image_name;
				Mask_MDI.getInstance().dataout.writeUTF("n," + txt_id.getText().trim() + "," + txt_pwd.getText().trim() + ","
						+ txt_name.getText().trim() + "," + txt_nick.getText().trim() + ","
						+ comboBox_gen.getSelectedItem() + "," + comboBox_age.getSelectedItem() + ","
						+ txt_tel.getText().trim() + "," + txt_memo.getText().trim() + "," + "Rank" + "," + id_image+ "," + "log");
				}else{
					JOptionPane.showMessageDialog(this, "회원정보를 입력하세요");
					return;
				}
				setVisible(false);
				Mask_MDI.getInstance().login.setVisible(true);
			} catch (Exception e2) {
				System.out.println("로그인정보 전송실패" + e2);
			}
		}else if(e.getSource() == btn_cancle){
			setVisible(false);
			Mask_MDI.getInstance().login.setVisible(true);
		}else if(e.getSource() == btn_Search){ //이미지파일 첨부
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); //파일만 본다
			int result = chooser.showOpenDialog(this);
			if(result == JFileChooser.CANCEL_OPTION){
				file = null;
			} else {
				try {
					file = chooser.getSelectedFile();
					txt_image.setText("" + file);
					image_name = file.getName();
					 // 컴퓨터에서
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "파일을 다시 선택해주세요");
					System.out.println("파일 에러: " + e2);
				}
			}
		}
	}
	public static void main(String[] args) {
		new Mask_Infomation();
		//new Mask_Infomation().setVisible(true);
	}
}












