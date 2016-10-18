package ex;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.io.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.net.Socket;
import java.text.AttributedCharacterIterator;

import designcomponent.*;

public class Mask_MDI extends JFrame implements ActionListener, Runnable {
	public static JInternalFrame childWinProfile, childWinRanking, childWinMain, childWinFp;
	public static BMenuItem LogOut, Exit;
	private JDesktopPane desktopPane = new JDesktopPane();
	private BButton btn_M, btn_P, btn_R, btn_Fp;
	private JPanel pnl1;
	public static Socket socket;
	public static DataInputStream datain;
	public static DataOutputStream dataout;
	public static String serverIp = "192.168.0.50";
	String file_path;
	String basic_file_path = "C:/image/";
	static Mask_MDI mdi = new Mask_MDI();
	Mask_Main main = new Mask_Main();
	Mask_Versus versus = new Mask_Versus();
	Mask_Change change = new Mask_Change();
	Mask_Rank rank = new Mask_Rank();
	Mask_Login login = new Mask_Login();
	boolean file_wait=false;
	int versus_page;
	String receive_file;
	int versus_index;
	int receive_count=0;
	String filesize;
	String[] image_path=new String[4];

	public static Mask_MDI getInstance() {
		return mdi;
	}

	boolean login_state = false;

	public Mask_MDI() {
		setTitle("Mask Rank");
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		JMenuBar mbar = new JMenuBar(); // 메뉴바
		desktopPane.setBackground(Cinfo.FRAME_COLOR);
		mbar.setBackground(Cinfo.BUTTON_COLOR);
		mbar.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		JMenu jMenu = new JMenu("메뉴");
		jMenu.setForeground(Cinfo.TEXT_COLOR);
		LogOut = new BMenuItem("로그아웃");
		Exit = new BMenuItem("종료");

		jMenu.add(LogOut);
		jMenu.add(Exit);

		mbar.add(jMenu);
		setJMenuBar(mbar); // frame에 메뉴바 장착

		getContentPane().add(desktopPane, BorderLayout.CENTER);

		btn_M = new BButton("Chat"); // 1번 버튼생성
		btn_M.setBounds(0, 0, 1000, 500);
		btn_M.setFont(new Font("Sanserif", Font.BOLD, 80));
		desktopPane.add(btn_M);

		btn_P = new BButton("Profile"); // 2번 버튼생성
		btn_P.setBounds(0, 500, 1000, 550);
		btn_P.setFont(new Font("Sanserif", Font.BOLD, 80));
		desktopPane.add(btn_P);

		btn_R = new BButton("Rank"); // 3번 버튼생성
		btn_R.setBounds(1000, 0, 1000, 500);
		btn_R.setFont(new Font("Sanserif", Font.BOLD, 80));
		desktopPane.add(btn_R);

		btn_Fp = new BButton("Versus"); // 4번 버튼생성
		btn_Fp.setBounds(1000, 500, 1000, 550);
		btn_Fp.setFont(new Font("Sanserif", Font.BOLD, 80));
		desktopPane.add(btn_Fp);
		btn_Fp.setEnabled(false);				
		btn_M.addActionListener(this); // 버튼 리스너 장착
		btn_P.addActionListener(this);
		btn_R.addActionListener(this);
		btn_Fp.addActionListener(this);
		LogOut.addActionListener(this);
		Exit.addActionListener(this);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); // 풀스크린(크기조절x)

		this.setLocation(0, 0);
		this.setSize(dimension.width, dimension.height);
		this.setVisible(false);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Mask_MDI.getInstance().dataout.writeUTF("x");
				} catch (Exception e2) {
					System.out.println("종료오류: "+e2);
				}
				System.exit(0);
			}
		});
		server_connect();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_M) { // 1번버튼 클릭이벤트
			createListen("채팅", 1000, 500, "Main");

			childWinMain.getContentPane().add(main);
			childWinMain.setLocation(0, 0);
			desktopPane.add(childWinMain);
			this.getContentPane().add(desktopPane);
			childWinMain.show();
			btn_M.setVisible(false);
			main.btn_exit.addActionListener(new ActionListener() { // 1번버튼 자식창  내부에있는 종료버튼 이벤트처리
				@Override
				public void actionPerformed(ActionEvent e) {
					btn_M.setVisible(true);
					childWinMain.dispose();
				}
			});

		} else if (e.getSource() == btn_R) { // 2번버튼 클릭이벤트
			createListen("랭킹", 910, 500, "Rank");

			childWinRanking.getContentPane().add(rank);
			childWinRanking.setLocation(1000, 0);
			desktopPane.add(childWinRanking);
			this.getContentPane().add(desktopPane);
			childWinRanking.show();
			btn_R.setVisible(false);
			rank.btn_exit.addActionListener(new ActionListener() { // 3번버튼 자식창 내부에있는 종료버튼 이벤트처리
				@Override
				public void actionPerformed(ActionEvent e) {
					btn_R.setVisible(true);
					childWinRanking.dispose();
				}
			});
		} else if (e.getSource() == btn_P) { // 3번버튼 클릭이벤트
			createListen("회원정보", 1000, 550, "Profile");

			childWinProfile.getContentPane().add(change);
			childWinProfile.setLocation(0, 500);
			desktopPane.add(childWinProfile);
			this.getContentPane().add(desktopPane);
			childWinProfile.show();
			btn_P.setVisible(false);
			change.btn_exit.addActionListener(new ActionListener() { // 2번버튼 자식창 내부에있는 종료버튼 이벤트처리
				@Override
				public void actionPerformed(ActionEvent e) {
					btn_P.setVisible(true);
					childWinProfile.dispose();
				}
			});
		} else if (e.getSource() == btn_Fp) { // 4번버튼 클릭이벤트
			createListen("페이크프로필", 910, 550, "Fp");

			childWinFp.getContentPane().add(versus);
			childWinFp.setLocation(1000, 500);
			desktopPane.add(childWinFp);
			this.getContentPane().add(desktopPane);
			childWinFp.show();
			btn_Fp.setVisible(false);
			versus.btn_exit.addActionListener(new ActionListener() { // 4번버튼 자식창  내부에있는 종료버튼  이벤트처리
				@Override
				public void actionPerformed(ActionEvent e) {
					btn_Fp.setVisible(true);
					childWinFp.dispose();
				}
			});
		} else if (e.getSource() == LogOut) { // 메뉴바 로그아웃 버튼이벤트
			// desktopPane.setVisible(false);
			setVisible(false);
			login.setVisible(true);
			login_state = false;
			try {
				Mask_MDI.getInstance().dataout.writeUTF("lo");
			} catch (Exception e2) {
				System.out.println("로그아웃전송오류: "+e2);
			}
			
		} else if (e.getSource() == Exit) { // 메뉴바 종료 버튼이벤트
			int re = JOptionPane.showConfirmDialog(this, "정말 종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);
			if (re == JOptionPane.YES_OPTION) {
				try {
					Mask_MDI.getInstance().dataout.writeUTF("x");
				} catch (Exception e2) {
					System.out.println("종료오류: "+e2);
				}
				System.exit(0);
			}
		}
	}

	private void createListen(String title, int w, int h, String str) {
		if (str.equals("Main")) {
			childWinMain = new JInternalFrame(title, false, false, false, false);
			childWinMain.setSize(w, h);
		} else if (str.equals("Profile")) {
			childWinProfile = new JInternalFrame(title, false, false, false, false);
			childWinProfile.setSize(w, h);
		} else if (str.equals("Rank")) {
			childWinRanking = new JInternalFrame(title, false, false, false, false);
			childWinRanking.setSize(w, h);
		} else if (str.equals("Fp")) {
			childWinFp = new JInternalFrame(title, false, false, false, false);
			childWinFp.setSize(w, h);
		}
	}

	private void server_connect() {
		try {
			socket = new Socket(serverIp, 8888);
			datain = new DataInputStream(socket.getInputStream());
			dataout = new DataOutputStream(socket.getOutputStream());
			new Thread(this).start();
			System.out.println("연결성공");
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(this, "인터넷 연결을 확인해주십시오");
			System.out.println("mdi 서버 연결 실패 : " + e);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				if(file_wait==true){
					file_receive(receive_file,filesize);
				}
				String msg = datain.readUTF();
				System.out.println(msg);
				if (msg.charAt(0) == ('n')) {
					System.out.println("서버로부터 다시수신");
					System.out.println(login.infomation.txt_image.getText().trim());
					file_send(login.infomation.txt_image.getText().trim());
				}
				if (login_state == false) {
					if (msg.equals("f")) { // 로그인실패메세지 수신
						JOptionPane.showMessageDialog(this, "로그인을 실패하였습니다.");
					} else if (msg.charAt(0)=='l') {
						login.setVisible(false);
						setVisible(true);
						login_state = true;
						dataout.writeUTF("lrd");
						dataout.writeUTF("lcr");
					}
					continue;
				}
				if (msg.charAt(0) == 'l') {
					if (msg.charAt(1) == 't') {
						main.txt_a.append(msg.substring(2) + "\n");
						main.scrollPane2.getVerticalScrollBar().setValue(main.scrollPane2.getVerticalScrollBar().getMaximum());
					} else if (msg.charAt(1) == 'a') {
						if(msg.charAt(2)=='c'){
							main.model.setNumRows(0);
						}
						String[] imsi={msg.split(",")[1],msg.split(",")[2],msg.split(",")[3],msg.split(",")[4]};
						main.model.addRow(imsi);
						main.txt_count.setText(String.valueOf(main.model.getRowCount()));
					} else if (msg.charAt(1) == 'u'){
						change.id = msg.split(",")[0].substring(2);
						change.txt_Name.setText(msg.split(",")[1]);
						change.txt_NickName.setText(msg.split(",")[2]);
						change.txt_Gen.setText(msg.split(",")[3]);
						change.txt_Age.setText(msg.split(",")[4]);
						change.txt_Tel.setText(msg.split(",")[5]);
						change.txt_Memo.setText(msg.split(",")[6]);
						change.txt_RanKing.setText(msg.split(",")[7]);
						change.txt_Image.setText(msg.split(",")[8]);
						
						if(change.file_switch == true) file_send(change.txt_Image.getText().trim());
					}else if(msg.charAt(1) == 'c'){
						if(msg.charAt(2)=='r'){
							String imsi2[] = { msg.substring(3).split(",")[1], 
												msg.substring(3).split(",")[2],
												msg.substring(3).split(",")[3], 
												msg.substring(3).split(",")[4] };
							change.model.addRow(imsi2);
							
						}else if(msg.charAt(2) == 't'){
							change.txt_FakeRanKing.setText(msg.split(",")[1]);
							change.txt_FakeName.setText(msg.split(",")[2]);
							change.txt_FakeAge.setText(msg.split(",")[3]);
							change.txt_FakeGen.setText(msg.split(",")[4]);
							change.textArea.setText(msg.split(",")[5]);
							change.txt_FakeImage.setText(msg.split(",")[6]);
						}else if(msg.charAt(2)=='i'){				//페이크프로필 insert완료메시지 받으면 프로필갱신요청
							change.model.setNumRows(0);
							dataout.writeUTF("lcr");
							change.textArea.setText("");
							change.txt_FakeAge.setText("");
							change.txt_FakeGen.setText("");
							change.txt_FakeImage.setText("");
							change.txt_FakeName.setText("");
							change.txt_FakeRanKing.setText("");
							
						}
					}else if(msg.charAt(1) == 'r'){
						if(msg.charAt(2)=='t'){
							System.out.println(msg.split(",")[1]);
							rank.txt_memo.setText(msg.split(",")[1]);
						}else if(msg.charAt(2)=='s'){
							String[] rowData={msg.split(",")[1],msg.split(",")[2],msg.split(",")[3],msg.split(",")[4]};
							rank.model.addRow(rowData);
						}				
						
						
						
					}
					else if(msg.charAt(1) == 'v'){		
						if(msg.charAt(2) == 'c'){
							file_wait=true;

							receive_file=msg.substring(3).split(",")[0];
							filesize=msg.split(",")[1];
							image_path[versus_index]=receive_file;
							versus_index++;
							
						}else if(msg.charAt(2)=='e'){
							versus_index=0;
							//이미지 수신작업 끝나면 versus에 이미지 그리기
						}else{
							versus_page=Integer.parseInt(msg.substring(2));
						}
					}
				}
			} catch (Exception e) {
				System.out.println("읽기오류: " + e);
			}
		}
	}

	public void file_send(String filePath) {
		try {

			File f = new File(filePath);
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);

			byte[] buffer = new byte[4096];// 파일 서버로 전송
			int len;
			while ((len = bis.read(buffer, 0 , buffer.length)) != -1) {
				dataout.write(buffer, 0, len);
				dataout.flush();
			}
			dataout.write(1);
			dataout.flush();
			f=null;
			fis.close();
			bis.close();
		} catch (Exception e) {
			System.out.println("file error" + e);
		}
	}

	public void file_receive(String filename,String size) {
		try {

			file_path = basic_file_path + filename; // 기본파일경로_id_filename
			System.out.println(file_path);
			File f = new File(file_path);
			FileOutputStream fos = new FileOutputStream(f);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			int len=0;
			byte[] data = new byte[Integer.parseInt(size)];
			
			while ((len=datain.read(data))!=1) {
				bos.write(data, 0, len);
				bos.flush();
				
			}
			
			System.out.println("end : " + f.length() +"  /  "+ data.length);
			bos.close();
			fos.close();
			f=null;
			file_wait=false;
			dataout.writeUTF("");
		} catch (Exception e) {
			System.out.println("파일받기 err " + e);
		}
	}
	public void draw_image(JPanel panel,String filename){
		ImageIcon icon=new ImageIcon(filename);
		panel.paintComponents(new GraphicAdapter(){
			@Override
			public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
					Color bgcolor, ImageObserver observer) {
				// TODO Auto-generated method stub
				Dimension d =panel.getSize();
				return super.drawImage(img, 0, 0, d.width, d.height, observer);
			}
		});
	}
	public void draw_image(JButton btn,String filename){
		ImageIcon icon=new ImageIcon(filename);
		btn.paintComponents(new GraphicAdapter(){
			@Override
			public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor,
					ImageObserver observer) {
				Dimension d =btn.getSize();
				return super.drawImage(img, 0, 0, d.width, d.height, observer);
			}
		});
		
	}


	public static void main(String[] args) {
		Mask_MDI.getInstance();
	}
}











