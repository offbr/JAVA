import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UrlMap extends JFrame{
	private JPanel panel;
	private JTextField textField, textField1, textField2, textField3;
	private JButton btnSearch, btnShowMap;
	
	public UrlMap() {
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);
		
		//1행
		JLabel label = new JLabel("입력 : ");
		label.setBounds(30, 15, 100, 15);
		panel.add(label);
		
		textField = new JTextField();
		textField.setBounds(100, 15, 255, 20);
		textField.setColumns(10);
		panel.add(textField);
		
		btnSearch = new JButton("검색");
		btnSearch.setBounds(370, 15, 100, 25);
		panel.add(btnSearch);
		//2행
		JLabel label1 = new JLabel("주소 : ");
		label1.setBounds(30, 40, 100, 15);
		panel.add(label1);
		
		textField1 = new JTextField();
		textField1.setBounds(100, 40, 255, 20);
		textField1.setColumns(10);
		textField1.setEditable(false);
		panel.add(textField1);
		
		btnShowMap = new JButton("지도보기");
		btnShowMap.setBounds(370, 50, 100, 25);
		panel.add(btnShowMap);
		
		//3행
		JLabel label2 = new JLabel("위도 : ");
		label2.setBounds(30, 75, 100, 15);
		panel.add(label2);
		
		textField2 = new JTextField();
		textField2.setBounds(100, 70, 255, 20);
		textField2.setColumns(10);
		textField2.setEditable(false);
		panel.add(textField2);
		
		//4행
		JLabel label3 = new JLabel("경도 : ");
		label3.setBounds(30, 105, 100, 15);
		panel.add(label3);
		
		textField3 = new JTextField();
		textField3.setBounds(100, 100, 255, 20);
		textField3.setColumns(10);
		textField3.setEditable(false);
		panel.add(textField3);

		setBounds(600, 300,  500, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addEvent();
		
		btnSearch.setMnemonic(KeyEvent.VK_NUMPAD1);
		btnShowMap.setMnemonic(KeyEvent.VK_NUMPAD0);
	}

	void addEvent(){
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String search = textField.getText();
				String search1 = "https://maps.googleapis.com/maps/api/geocode/xml?address=";
				String search2 = "&language=ko";
				String search_make = search1+search+search2; 
				
				//System.out.println(search_make);
				
				try {
					BufferedReader br = null;
					URL url = new URL(search_make);
					br = new BufferedReader(new InputStreamReader(url.openStream()));
					//System.out.println(br.readLine());
					
					String line = "", address = "", lat = "", lng = "";  // 라인 주소 위도 경도
					
					while((line = br.readLine()) != null){
						//System.out.println(line);
						//주소 얻기
						if(line.contains("<formatted_address>")){
							line = line.replace("<formatted_address>", "");
							line = line.replace("</formatted_address>", "");
							address = line.trim();							
						}else if(line.contains("<lat>")){
							line = line.replace("<lat>", "");
							line = line.replace("</lat>", "");
							lat = line.trim();							
						}else if(line.contains("<lng>")){
							line = line.replace("<lng>", "");
							line = line.replace("</lng>", "");
							lng = line.trim();			
							break;
						}
					}
					
					//System.out.println("주소: " + address + ",위도: " + lat + ",경도: "  + lng);
					textField1.setText(address);
					textField2.setText(lat);
					textField3.setText(lng);
				} catch (Exception e2) {
					
				}
			}
		});
		
		btnShowMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String lat = textField2.getText(); 
				String lng = textField3.getText();
				String myurl = "http://www.google.co.kr/maps/@" + lat + "," + lng + ",18z";
				
				//브라우저 실행
				//ProcessBuilder pb = new ProcessBuilder("c:/Program Files (x86)/Google/Chrome/Application/chrome.exe", myurl);
				ProcessBuilder pb = new ProcessBuilder("c:/Program Files (x86)/Google/Chrome/Application/chrome.exe", myurl);
				
				try {
					pb.start();
				} catch (Exception e2) {
					
				}
			}
		});
	}
	
	public static void main(String[] args) {
		new UrlMap();
	}
}
















