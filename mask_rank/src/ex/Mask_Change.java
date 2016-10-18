package ex;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import designcomponent.*;
import designcomponent.BTable;

public class Mask_Change extends BPanel implements ActionListener{
	BTextField txt_RanKing, txt_Name, txt_PWD, txt_PWDOK, txt_NickName, txt_Gen, txt_Age, txt_Tel, txt_Image;
	BTextArea txt_Memo;
	BTextArea textArea;
	private BButton btn_PWDOK, btn_File, btn_OK;
	private BTable table;
	private String[] title = { "성별", "닉네임", "나이", "메모" };
	private String[][] rowData = new String[0][4];
	DefaultTableModel model;
	private BButton btn_Insert, btn_Delete;
	BTextField txt_FakeName, txt_FakeRanKing, txt_FakeAge, txt_FakeGen, txt_FakeImage;
	private BButton btn_FakeFile, btn_FakeOk;
	public BButton btn_exit;
	private File file;
	Boolean file_switch = false;
	String image_name;
	String id = "";
	boolean insert_state=false;
	boolean delete_state=false;
	public Mask_Change() {
		layInit();
		display();
	}
	
	private void layInit(){
		setLayout(null);
		setBackground(Cinfo.FRAME_COLOR);
		BPanel panel1 = new BPanel();
		panel1.setBounds(12, 10, 342, 471);
		add(panel1);
		panel1.setLayout(null);
		
		BLabel lbl_Name = new BLabel("Name");
		lbl_Name.setBounds(12, 10, 57, 15);
		panel1.add(lbl_Name);
		
		BLabel lbl_Pwd = new BLabel("PWD");
		lbl_Pwd.setBounds(12, 44, 57, 15);
		panel1.add(lbl_Pwd);
		
		txt_Name = new BTextField();
		txt_Name.setBounds(83, 7, 150, 21);
		panel1.add(txt_Name);
		txt_Name.setColumns(10);
		
		txt_PWD = new BTextField();
		txt_PWD.setBounds(81, 42, 150, 21);
		panel1.add(txt_PWD);
		txt_PWD.setColumns(10);
		
		BLabel lbl_PWDOK = new BLabel("PWD OK");
		lbl_PWDOK.setBounds(12, 82, 91, 15);
		panel1.add(lbl_PWDOK);
		
		txt_PWDOK = new BTextField();
		txt_PWDOK.setBounds(83, 79, 150, 21);
		panel1.add(txt_PWDOK);
		txt_PWDOK.setColumns(10);
		
		BLabel lbl_NickName = new BLabel("NickName");
		lbl_NickName.setBounds(12, 119, 91, 15);
		panel1.add(lbl_NickName);
		
		txt_NickName = new BTextField();
		txt_NickName.setBounds(83, 116, 150, 21);
		panel1.add(txt_NickName);
		txt_NickName.setColumns(10);
		
		BLabel lbl_Gen = new BLabel("Gen");
		lbl_Gen.setBounds(12, 155, 57, 15);
		panel1.add(lbl_Gen);
		
		txt_Gen = new BTextField();
		txt_Gen.setBounds(83, 152, 150, 21);
		panel1.add(txt_Gen);
		txt_Gen.setColumns(10);
		
		BLabel lbl_Age = new BLabel("Age");
		lbl_Age.setBounds(12, 193, 57, 15);
		panel1.add(lbl_Age);
		
		txt_Age = new BTextField();
		txt_Age.setBounds(83, 190, 150, 21);
		panel1.add(txt_Age);
		txt_Age.setColumns(10);
		
		BLabel lbl_Tel = new BLabel("Tel");
		lbl_Tel.setBounds(12, 234, 57, 15);
		panel1.add(lbl_Tel);
		
		txt_Tel = new BTextField();
		txt_Tel.setBounds(83, 231, 150, 21);
		panel1.add(txt_Tel);
		txt_Tel.setColumns(10);
		
		BLabel lbl_Memo = new BLabel("Memo");
		lbl_Memo.setBounds(12, 271, 57, 15);
		panel1.add(lbl_Memo);
		
		BScrollPane scrollPane1 = new BScrollPane();
		scrollPane1.setBounds(83, 262, 150, 136);
		panel1.add(scrollPane1);
		
		txt_Memo = new BTextArea();
		scrollPane1.setViewportView(txt_Memo);
		
		BLabel lbl_Ranking = new BLabel("     RanKing");
		lbl_Ranking.setBounds(245, 10, 85, 15);
		panel1.add(lbl_Ranking);
		
		txt_RanKing = new BTextField();
		txt_RanKing.setBounds(245, 41, 85, 21);
		panel1.add(txt_RanKing);
		txt_RanKing.setColumns(10);
		
		BLabel lbl_Image = new BLabel("Image");
		lbl_Image.setBounds(12, 410, 57, 15);
		panel1.add(lbl_Image);
		
		txt_Image = new BTextField();
		txt_Image.setBounds(83, 408, 150, 21);
		panel1.add(txt_Image);
		txt_Image.setColumns(10);
		
		btn_PWDOK = new BButton("PWD OK");
		btn_PWDOK.setBounds(245, 78, 85, 23);
		panel1.add(btn_PWDOK);
		
		btn_File = new BButton("File");
		btn_File.setBounds(245, 407, 85, 23);
		panel1.add(btn_File);
		
		btn_OK = new BButton("OK");
		btn_OK.setBounds(107, 439, 97, 23);
		panel1.add(btn_OK);
		
		BPanel panel2 = new BPanel();
		panel2.setBounds(366, 10, 321, 471);
		add(panel2);
		panel2.setLayout(null);
		
		BScrollPane scrollPane2 = new BScrollPane();
		scrollPane2.setBounds(12, 10, 297, 417);
		panel2.add(scrollPane2);
		
		model = new DefaultTableModel(rowData, title);
		table = new BTable(model);
		scrollPane2.setViewportView(table);
		
		btn_Insert = new BButton("Insert");
		btn_Insert.setBounds(38, 437, 97, 23);
		panel2.add(btn_Insert);
		
		btn_Delete = new BButton("Delete");
		btn_Delete.setBounds(181, 437, 97, 23);
		panel2.add(btn_Delete);
		
		BPanel panel3 = new BPanel();
		panel3.setBounds(699, 10, 278, 427);
		add(panel3);
		panel3.setLayout(null);
		
		BLabel lbl_FakeName = new BLabel("FakeName");
		lbl_FakeName.setBounds(12, 41, 84, 15);
		panel3.add(lbl_FakeName);
		
		txt_FakeRanKing = new BTextField();
		txt_FakeRanKing.setBounds(85, 8, 64, 21);
		panel3.add(txt_FakeRanKing);
		txt_FakeRanKing.setColumns(10);
		
		BLabel lbl_FakeRanKing = new BLabel("Ranking");
		lbl_FakeRanKing.setBounds(12, 10, 64, 15);
		panel3.add(lbl_FakeRanKing);
		
		txt_FakeName = new BTextField();
		txt_FakeName.setBounds(85, 39, 168, 21);
		panel3.add(txt_FakeName);
		txt_FakeName.setColumns(10);
		
		BLabel lbl_FakeAge = new BLabel("FakeAge");
		lbl_FakeAge.setBounds(12, 78, 84, 15);
		panel3.add(lbl_FakeAge);
		
		txt_FakeAge = new BTextField();
		txt_FakeAge.setBounds(85, 76, 64, 21);
		panel3.add(txt_FakeAge);
		txt_FakeAge.setColumns(10);
		
		BLabel lbl_FakeGen = new BLabel("FakeGen");
		lbl_FakeGen.setBounds(12, 109, 84, 15);
		panel3.add(lbl_FakeGen);
		
		txt_FakeGen = new BTextField();
		txt_FakeGen.setBounds(85, 107, 64, 21);
		panel3.add(txt_FakeGen);
		txt_FakeGen.setColumns(10);
		
		BLabel lbl_FakeMemo = new BLabel("FakeMemo");
		lbl_FakeMemo.setBounds(12, 142, 97, 15);
		panel3.add(lbl_FakeMemo);
		
		BScrollPane scrollPane3 = new BScrollPane();
		scrollPane3.setBounds(85, 138, 168, 175);
		panel3.add(scrollPane3);
		
		textArea = new BTextArea();
		scrollPane3.setViewportView(textArea);
		
		BLabel lbl_FakeImage = new BLabel("FakeImage");
		lbl_FakeImage.setBounds(12, 325, 84, 15);
		panel3.add(lbl_FakeImage);
		
		txt_FakeImage = new BTextField();
		txt_FakeImage.setBounds(85, 323, 168, 21);
		panel3.add(txt_FakeImage);
		txt_FakeImage.setColumns(10);
		
		btn_FakeFile = new BButton("File");
		btn_FakeFile.setBounds(156, 354, 97, 23);
		panel3.add(btn_FakeFile);
		
		btn_FakeOk = new BButton("OK");
		btn_FakeOk.setBounds(98, 387, 97, 23);
		panel3.add(btn_FakeOk);
	
		btn_exit = new BButton("Exit");
		btn_exit.setBounds(880, 458, 97, 23);
		add(btn_exit);
		
		txt_RanKing.setEditable(false);
		txt_FakeRanKing.setEditable(false);
		txt_Gen.setEditable(false);
		txt_Age.setEditable(false);
		txt_Image.setEditable(false);
		
		btn_PWDOK.addActionListener(this);
		btn_File.addActionListener(this);
		btn_OK.addActionListener(this);
		btn_Insert.addActionListener(this);
		btn_Delete.addActionListener(this);
		btn_FakeFile.addActionListener(this);
		btn_FakeOk.addActionListener(this);
		btn_exit.addActionListener(this);
	
		txt_FakeAge.setEnabled(false);
		txt_FakeGen.setEnabled(false);
		txt_FakeName.setEnabled(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Mask_MDI.getInstance().dataout.writeUTF("lct,"+model.getValueAt(table.getSelectedRow(), 1));
				} catch (Exception e2) {
					System.out.println("페이크프로필 닉네임전송에러"+e2);
				}
			}
		});
	}
	
	private void display(){
		try {
		
		} catch (Exception e) {
			System.out.println("기본정보 오류: " + e);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_PWDOK){
			try {
				if(txt_PWD.getText().equals(txt_PWDOK.getText())){
					txt_PWD.setEditable(false); txt_PWDOK.setEditable(false); 
				}else{
					txt_PWD.setText(""); txt_PWDOK.setText("");					
				}
			} catch (Exception e2) {	
			}
		}else if(e.getSource() == btn_File){
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); //파일만 본다
			int result = chooser.showOpenDialog(this);
			if(result == JFileChooser.CANCEL_OPTION){
				file = null;
			} else {
				try {
					file = chooser.getSelectedFile();
					txt_Image.setText("" + file);
					image_name = file.getName();
					file_switch = true;
					 // 컴퓨터에서
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "파일을 다시 선택해주세요");
					System.out.println("파일 에러: " + e2);
				}
			}
		}else if(e.getSource() == btn_OK){
			try {
				if(txt_PWDOK.getText().equals("")) JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
				if(file_switch == false){
					Mask_MDI.getInstance().dataout.writeUTF("lu,"+txt_Name.getText().trim()+","+txt_PWDOK.getText().trim()+","+
														txt_NickName.getText().trim()+","+txt_Tel.getText().trim()+","+
														txt_Memo.getText().trim()+","+(id+"_"+txt_NickName.getText().trim()+"_"+txt_Image.getText()));
				}else{
					Mask_MDI.getInstance().dataout.writeUTF("lu,"+txt_Name.getText().trim()+","+txt_PWDOK.getText().trim()+","+
							txt_NickName.getText().trim()+","+txt_Tel.getText().trim()+","+
							txt_Memo.getText().trim()+","+(id+"_"+txt_NickName.getText().trim()+"_"+image_name));
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "수정 오류");
				System.out.println("수정오류 : " + e);
			}
		}else if(e.getSource() == btn_Insert){
			if(insert_state==false){
				try {
					
					model.setNumRows(0);
					btn_Delete.setEnabled(false);
					txt_FakeAge.setEnabled(true);
					txt_FakeGen.setEnabled(true);
					txt_FakeName.setEnabled(true);
					insert_state=true;
					btn_Insert.setText("cancel");
					txt_FakeRanKing.setText("");
					txt_FakeName.setText("");
					txt_FakeAge.setText("");
					txt_FakeGen.setText("");
					textArea.setText("");
					txt_FakeImage.setText("");
				} catch (Exception e2) {
				}
			}else if(insert_state==true){
				try {
					model.setNumRows(0);
					btn_Delete.setEnabled(true);
					txt_FakeAge.setEnabled(false);
					txt_FakeGen.setEnabled(false);
					txt_FakeName.setEnabled(false);
					insert_state=false;
					btn_Insert.setText("Insert");
					try {
						Mask_MDI.getInstance().dataout.writeUTF("lcr");			// model데이터 리콜
					} catch (Exception e2) {
						System.out.println("리콜에러"+e2);
					}
					
					
					
							
				} catch (Exception e2) {
				}
			}
			
		}else if(e.getSource() == btn_Delete){
			if(delete_state==false){
				delete_state=true;
				btn_FakeOk.setEnabled(false);
				btn_FakeFile.setEnabled(false);
				btn_Insert.setEnabled(false);
				btn_Delete.setText("OK");
			}else{
				try {
					delete_state=false;
					btn_FakeOk.setEnabled(true);
					btn_FakeFile.setEnabled(true);
					btn_Insert.setEnabled(true);
					btn_Delete.setText("delete");
					if(txt_FakeName.getText().equals(null)){
						return;
					}
					model.setRowCount(0);
					Mask_MDI.getInstance().dataout.writeUTF("lcd,"+txt_FakeName.getText());
					Mask_MDI.getInstance().dataout.writeUTF("lcr");
				}catch (Exception e2) {

				}
			}
			
		}else if(e.getSource() == btn_FakeFile){
			try {
							
			} catch (Exception e2) {
				
			}
		}else if(e.getSource() == btn_FakeOk){
			if(insert_state==true){
				try {
					insert_state=false;
					txt_FakeAge.setEnabled(false);
					txt_FakeGen.setEnabled(false);
					txt_FakeName.setEnabled(false);
					btn_Insert.setText("Insert");
					System.out.println(1);
					Mask_MDI.getInstance().dataout.writeUTF("lcc,"+txt_FakeImage.getText()+","+txt_FakeName.getText()+","+txt_FakeAge.getText()+","+txt_FakeGen.getText()+","+textArea.getText());
					
					
				} catch (Exception e2) {
					System.out.println("fakeinsert err "+e2);
				}
			}else{
				try {
					Mask_MDI.getInstance().dataout.writeUTF("lcu");
				} catch (Exception e2) {
					System.out.println("fakeinsert err "+e2);
				}
			}
			
			
		}
	}
	
	public static void main(String[] args) {
		Mask_Change mask_Change = new Mask_Change();
		
		JFrame frame = new JFrame("Change");
		frame.getContentPane().add(mask_Change);
		frame.setBounds(440, 110, 1000, 550);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
