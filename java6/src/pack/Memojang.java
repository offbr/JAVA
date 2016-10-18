package pack;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Memojang extends JFrame implements ActionListener{
	private JButton btnCopy = new JButton("copy");
	private JButton btnPaste = new JButton("paste");
	private JButton btnCut = new JButton("cut");
	private JButton btnDel = new JButton("del");
	JPanel pn = new JPanel();
	JTextArea txtMemo = new JTextArea();
	String copyText;
	
	//메뉴
	JMenuItem mnuNew, mnuSave, mnuOpen, mnuExit;
	JMenuItem mnuCopy, mnuPaste, mnuCut, mnuDel;
	JMenuItem mnuAbout, mnuEtc1, mnuEtc2, mnuMade;
	
	//팝업메뉴
	JPopupMenu popupMenu;
	JMenuItem m_white, m_blue, m_black, m_random; 
	
	public Memojang() {
		super("메모장");
		
		initLayout();
		menuLayout();
		
		setBounds(200, 200, 400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initLayout(){
		pn.add(btnCopy);
		pn.add(btnPaste);
		pn.add(btnCut);
		pn.add(btnDel);
		add("South", pn);
		JScrollPane scrollPane = new JScrollPane(txtMemo); 
		add("Center", scrollPane);
		
		btnCopy.addActionListener(this);
		btnPaste.addActionListener(this);
		btnCut.addActionListener(this);
		btnDel.addActionListener(this);
	}
	
	private void menuLayout(){
		JMenuBar menuBar = new JMenuBar(); //메뉴바
		
		JMenu mnuFile = new JMenu("파일"); //주메뉴
		mnuNew = new JMenuItem("새로만들기"); //부메뉴
		mnuOpen = new JMenuItem("열기...");
		mnuSave = new JMenuItem("저장...");
		mnuExit = new JMenuItem("종료");
		mnuFile.add(mnuNew);
		mnuFile.add(mnuOpen);
		mnuFile.add(mnuSave);
		mnuFile.addSeparator(); //구분선
		mnuFile.add(mnuExit);
		
		JMenu mnuEdit = new JMenu("편집"); //주메뉴
		mnuCopy = new JMenuItem("복사");
		mnuPaste = new JMenuItem("붙여넣기");
		mnuCut = new JMenuItem("잘라내기");
		mnuDel = new JMenuItem("삭제");
		mnuEdit.add(mnuCopy);
		mnuEdit.add(mnuPaste);
		mnuEdit.add(mnuCut);
		mnuEdit.add(mnuDel);
		
		JMenu mnuHelp = new JMenu("도움말"); //주메뉴
		mnuAbout = new JMenuItem("메모장이란.....");
		mnuHelp.add(mnuAbout);
		JMenu mnuEtc = new JMenu("기타");
		mnuEtc1 = new JMenuItem("계산기");
		mnuEtc2 = new JMenuItem("프리셀");
		mnuMade = new JMenuItem("만든이");
		mnuEtc.add(mnuEtc1);
		mnuEtc.add(mnuEtc2);
		mnuHelp.add(mnuEtc);
		mnuHelp.addSeparator(); //구분선
		mnuHelp.add(mnuMade);
		
		menuBar.add(mnuFile); //메뉴바에 주메뉴 등록
		menuBar.add(mnuEdit);
		menuBar.add(mnuHelp);
		
		setJMenuBar(menuBar); //프레임에 메뉴바 등록
		
		//메뉴에 리스너 장착
		mnuNew.addActionListener(this);
		mnuSave.addActionListener(this);
		mnuOpen.addActionListener(this);
		mnuExit.addActionListener(this);
		mnuCopy.addActionListener(this);
		mnuPaste.addActionListener(this);
		mnuCut.addActionListener(this);
		mnuDel.addActionListener(this);
		mnuAbout.addActionListener(this);
		mnuEtc1.addActionListener(this);
		mnuEtc2.addActionListener(this);
		mnuMade.addActionListener(this);
		
		//팝업 메뉴
		popupMenu = new JPopupMenu();
		JMenu m_color = new JMenu("색상선택");
		m_white = new JMenuItem("White");
		m_blue = new JMenuItem("Blue");
		m_black = new JMenuItem("Black");
		m_random = new JMenuItem("Random");
		m_color.add(m_white);
		m_color.add(m_blue);
		m_color.add(m_black);
		m_color.add(m_random);
		popupMenu.add(m_color);
		txtMemo.add(popupMenu); //팝업의 대상 지정
		
		m_white.addActionListener(this);
		m_blue.addActionListener(this);
		m_black.addActionListener(this);
		m_random.addActionListener(this);
		
		txtMemo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//System.out.println("good");
				if(e.getModifiers() == MouseEvent.BUTTON3_MASK){					
					//System.out.println("good");
					popupMenu.show(txtMemo, e.getX(), e.getY());
				}
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnCopy) || e.getSource() == mnuCopy){ //복사
			//System.out.println(txtMemo.getSelectedText());
			//txtMemo.copy();
			copyText = txtMemo.getSelectedText();			
		}else if(e.getSource().equals(btnPaste) || e.getSource() == mnuPaste){ //붙여넣기
			//txtMemo.setText(copyText); 덮어버림..
			//txtMemo.paste();
			int position = txtMemo.getCaretPosition(); //범위지정
			txtMemo.insert(copyText, position);
		}else if(e.getSource().equals(btnCut) || e.getSource() == mnuCut){ //잘라내기
			//txtMemo.cut();
			copyText = txtMemo.getSelectedText();
			int start = txtMemo.getSelectionStart();
			int end = txtMemo.getSelectionEnd();
			//System.out.println(start + " " + end);
			txtMemo.replaceRange("", start, end);
		}else if(e.getSource().equals(btnDel) || e.getSource() == mnuDel){ //삭제
			int start = txtMemo.getSelectionStart();
			int end = txtMemo.getSelectionEnd();
			txtMemo.replaceRange("", start, end);
		}else if(e.getSource().equals(mnuNew)){ //새로만들기
			int re1 = JOptionPane.showConfirmDialog(this, "새로 만드시겠습니까?", "새로만들기", JOptionPane.YES_NO_OPTION);
			if(re1 == JOptionPane.YES_OPTION){
				int re2 = JOptionPane.showConfirmDialog(this, "저장하시겠습니까?", "저장", JOptionPane.YES_NO_OPTION);
				if(re2 == JOptionPane.YES_OPTION){
					FileDialog dialog = new FileDialog(this, "저장", FileDialog.SAVE);
					dialog.setDirectory("."); //자기본인. / (../)상위폴더
					dialog.setVisible(true);
					if(dialog.getFiles() == null) return;
					String df = dialog.getDirectory() + dialog.getFile();
					//System.out.println(df);
						try {
							//String df = "c:/work/good.txt";
							BufferedWriter writer = new BufferedWriter(new FileWriter(df));
							writer.write(txtMemo.getText());
							writer.close();
							setTitle(dialog.getFile() + " - 메모장");
						} catch (Exception e2) {
							System.out.println("저장오류: " +e);
				}
			}
		}
			txtMemo.setText(null);	//txtMemo.setText("");	
			setTitle("제목 없음 - 메모장");
		}else if(e.getSource().equals(mnuOpen)){ //열기
			//String df = "c:/work/good.txt";
			FileDialog dialog = new FileDialog(this, "열기", FileDialog.LOAD);
			dialog.setDirectory("."); //자기본인. / (../)상위폴더
			dialog.setVisible(true);
			if(dialog.getFiles() == null) return;
			String df = dialog.getDirectory() + dialog.getFile();
			//System.out.println(df);
			try {
				BufferedReader reader = new BufferedReader(new FileReader(df));
				
				txtMemo.setText("");
				String line;
				while((line = reader.readLine()) != null){
					txtMemo.append(line + "\n");
				}
				reader.close();
				
				setTitle(dialog.getFile() + " - 메모장");
			} catch (Exception e2) {
				System.out.println("열기오류: " +e);
			}				
		}else if(e.getSource().equals(mnuSave)){ //저장
			//파일 작업을 위한 대화상자 호출 - 경로명 및 파일명 얻기
			FileDialog dialog = new FileDialog(this, "저장", FileDialog.SAVE);
			dialog.setDirectory("."); //자기본인. / (../)상위폴더
			dialog.setVisible(true);
			if(dialog.getFiles() == null) return;
			String df = dialog.getDirectory() + dialog.getFile();
			//System.out.println(df);
			try {
				//String df = "c:/work/good.txt";
				BufferedWriter writer = new BufferedWriter(new FileWriter(df));
				writer.write(txtMemo.getText());
				writer.close();
				setTitle(dialog.getFile() + " - 메모장");
			} catch (Exception e2) {
				System.out.println("저장오류: " +e);
			} finally { //에러가 생겨도 수행
				//if(write) 지역변수라 못 불러온다.
			}
		}else if(e.getSource().equals(mnuExit)){ //종료
			//System.exit(0);
			int re = JOptionPane.showConfirmDialog(this, "정말 종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);
			if(re == JOptionPane.YES_OPTION) System.exit(0);
		}else if(e.getSource().equals(mnuAbout)){
			new MemoAbout(this);
			System.out.println("메모대화상자 확인완료");
		}else if(e.getSource().equals(mnuEtc1)){ //계산기
			try {
				Runtime runtime = Runtime.getRuntime();
				runtime.exec("calc.exe");
			} catch (Exception e2) {
				System.out.println();
			}
		}else if(e.getSource().equals(mnuEtc2)){ //프리셀
			try {
				Runtime runtime = Runtime.getRuntime();
				runtime.exec("freecell.exe");
			} catch (Exception e2) {
				System.out.println("프리셀 오류: " + e);
			}
		}else if(e.getActionCommand().equals("만든이")){ //만든사람
			int About1 = JOptionPane.showConfirmDialog(this, "made by J.ari", "만든이", JOptionPane.PLAIN_MESSAGE);
		}else if(e.getSource().equals(m_white)){ //바탕
			txtMemo.setBackground(Color.white);
		}else if(e.getSource().equals(m_blue)){ //바탕
			txtMemo.setBackground(Color.blue);
		}else if(e.getSource().equals(m_black)){
			txtMemo.setBackground(Color.black);
		}else if(e.getSource().equals(m_random)){
			int r = (int)(Math.random()*255);
			int g = (int)(Math.random()*255);
			int b = (int)(Math.random()*255);
			int r1 = (int)(Math.random()*255);
			int g1 = (int)(Math.random()*255);
			int b1 = (int)(Math.random()*255);
			txtMemo.setBackground(new Color(r, g, b));
			txtMemo.setForeground(new Color(r1, g1, b1));
		}
	
		txtMemo.requestFocus(); //원래 포커스로
		
		System.out.println(e.getActionCommand());
		//txtMemo.setText(e.getActionCommand());
	}
	
	public static void main(String[] args) {
		new Memojang();
	}
}


































