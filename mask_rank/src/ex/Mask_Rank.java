package ex;

import java.awt.Color;

import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import designcomponent.*;

public class Mask_Rank extends BPanel implements ActionListener {
	public BButton btn_Search, btn_Reset, btn_exit;
	BLabel lbl_Ranking, lbl_Picture, lbl_memo, lbl_img;
	private BTextField txt_Search;
	BTextArea txt_memo;
	private String[][] datas = new String[0][4];
	private String[] title = { "닉네임", "성별", "나이", "순위" };
	DefaultTableModel model = new DefaultTableModel(datas, title);
	private BTable table = new BTable(model);
	private String imgPath;
	private File file;
	BPanel pn_picture;

	private static Mask_Rank mask=new Mask_Rank();
	public static Mask_Rank getInstance(){
		return mask;
	}
	public Mask_Rank() {
		layInit();
		imgPath = "C:/Users/user/Desktop/문종/seo rina.jpg";
		displayImage();
	}

	private void displayImage() {
		if (imgPath != null) {
			ImageIcon icon = new ImageIcon(imgPath);
			lbl_img.setIcon(icon);
			lbl_img.setToolTipText("경로는 " + imgPath.toLowerCase()); // 툴팁 표시
			if (icon != null) {
				lbl_img.setText(null);
			} else {
				lbl_img.setText("그림 없음");
			}
		}
	}

	private void layInit() {
		setLayout(null);

		lbl_Ranking = new BLabel("Ranking");
		lbl_Ranking.setFont(new Font("궁서", Font.BOLD, 34));
		lbl_Ranking.setBounds(40, 10, 273, 54);
		add(lbl_Ranking);

		btn_Search = new BButton("Search");
		btn_Search.setFont(new Font("궁서", Font.BOLD, 12));
		btn_Search.setBounds(730, 358, 147, 41);
		add(btn_Search);

		btn_Reset = new BButton("Reset");
		btn_Reset.setFont(new Font("궁서", Font.BOLD, 12));
		btn_Reset.setBounds(571, 409, 147, 39);
		add(btn_Reset);

		btn_exit = new BButton("Exit");
		btn_exit.setFont(new Font("궁서", Font.BOLD, 12));
		btn_exit.setBounds(730, 409, 147, 41);
		add(btn_exit);

		BScrollPane scrollPane = new BScrollPane(table);
		scrollPane.setBounds(40, 72, 433, 276);
		add(scrollPane);

		pn_picture = new BPanel();
		pn_picture.setBorder(new LineBorder(new Color(176, 193, 217), 3));
		pn_picture.setBounds(485, 72, 234, 276);
		add(pn_picture);

		lbl_img = new BLabel("No Image");
		pn_picture.add(lbl_img);

		btn_Search.addActionListener(this);
		btn_Reset.addActionListener(this);
		btn_exit.addActionListener(this);

		txt_Search = new BTextField();
		txt_Search.setBounds(571, 358, 147, 41);
		add(txt_Search);
		txt_Search.setColumns(10);

		txt_memo = new BTextArea();
		
		txt_memo.setEditable(false);
		txt_memo.setBounds(731, 72, 147, 276);
		add(txt_memo);

		lbl_Picture = new BLabel("Picture");
		lbl_Picture.setBounds(485, 37, 57, 15);
		add(lbl_Picture);

		lbl_memo = new BLabel("Memo");
		lbl_memo.setBounds(731, 37, 57, 15);
		add(lbl_memo);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Mask_MDI.getInstance().dataout.writeUTF("lrt,"+model.getValueAt(table.getSelectedRow(), 0));
				} catch (Exception e2) {
					System.out.println("랭킹뷰 닉네임전송에러"+e2);
				}
				
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btn_Search)) {
			try {
				model.setNumRows(0);
				Mask_MDI.getInstance().dataout.writeUTF("lrs,"+txt_Search.getText());
				txt_Search.setText("");
				txt_Search.requestFocus();
			} catch (Exception e2) {
				System.out.println("검색 실패 오류: " + e2);
			}
		} else if (e.getSource().equals(btn_Reset)) {
			model.setNumRows(0);
			try {
				Mask_MDI.getInstance().dataout.writeUTF("lrd");				
			} catch (Exception e2) {
			}
			txt_Search.setText("");
			lbl_img.setText("");
			txt_memo.setText("");
		}
	}

	
	public static void main(String[] args) {
		Mask_Rank rank = new Mask_Rank();

		JFrame frame = new JFrame("Main");
		frame.getContentPane().add(rank);
		frame.setBounds(440, 110, 1000, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}