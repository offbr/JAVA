package ex;

import javax.swing.*;
import designcomponent.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.Socket;
import java.sql.*;

public class Mask_Main extends BPanel implements ActionListener {
	private BTable table;
	private BTextField txt_f;
	BTextArea txt_a;
	JScrollPane scrollPane2;
	private String[] title = { "성별", "닉네임", "나이", "순위" };
	private String[][] rowData = new String[0][4];
	DefaultTableModel model;
	public BButton btn_exit, btn_ok;
	private BRadioButton rbtn_unknown;
	
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private String sql = "";
	BTextField txt_count;
	private Socket socket;
	

	/**
	 * Create the panel.
	 */
	
	public Mask_Main() {
		layInit();
	}

	private void layInit() {
		setLayout(null);

		BPanel panel1 = new BPanel();
		panel1.setBounds(649, 10, 328, 447);
		add(panel1);
		panel1.setLayout(null);

		BScrollPane scrollPane1 = new BScrollPane();
		scrollPane1.setBounds(12, 10, 304, 377);
		panel1.add(scrollPane1);

		model = new DefaultTableModel(rowData, title);
		table = new BTable(model);
		scrollPane1.setViewportView(table);

		BLabel lbl_count = new BLabel("현재 접속자");
		lbl_count.setBounds(12, 413, 80, 15);
		panel1.add(lbl_count);

		txt_count = new BTextField();
		txt_count.setBounds(84, 410, 58, 21);
		panel1.add(txt_count);
		txt_count.setColumns(10);

		btn_exit = new BButton("Exit");
		btn_exit.setBounds(219, 409, 97, 23);
		panel1.add(btn_exit);

		BPanel panel2 = new BPanel();
		panel2.setBounds(12, 10, 625, 447);
		add(panel2);
		panel2.setLayout(null);

		txt_f = new BTextField();
		txt_f.setBounds(100, 408, 404, 25);
		panel2.add(txt_f);
		txt_f.setColumns(10);

		btn_ok = new BButton("Send");
		btn_ok.setBounds(516, 409, 97, 23);
		panel2.add(btn_ok);

		rbtn_unknown = new BRadioButton("익명");
		rbtn_unknown.setBounds(12, 408, 70, 25);
		panel2.add(rbtn_unknown);

		scrollPane2 = new BScrollPane();
		scrollPane2.setBounds(12, 10, 601, 378);
		panel2.add(scrollPane2);

		txt_a = new BTextArea();
		scrollPane2.setViewportView(txt_a);
		
		txt_a.setEditable(false);
		txt_count.setEditable(false);
		table.setEnabled(false);
		
		txt_f.addActionListener(this);
		btn_exit.addActionListener(this);
		btn_ok.addActionListener(this);
		rbtn_unknown.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn_ok || e.getSource()==txt_f){
			try {
				Mask_MDI.getInstance().dataout.writeUTF("l"+(rbtn_unknown.isSelected()?"f":"t")+txt_f.getText());
				txt_f.setText("");
				txt_f.requestFocus();
			} catch (Exception e2) {
				System.out.println("채팅송신 에러"+e2);
			}
			
		}
	}

	public static void main(String[] args) {
		Mask_Main main = new Mask_Main();

		JFrame frame = new JFrame("메인");
		frame.getContentPane().add(main);
		frame.setBounds(440, 110, 1000, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}