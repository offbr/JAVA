import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UrlTest1_ex extends JFrame implements ActionListener{
	private JTextField txt_F;
	private JTextArea txt_A;
	private JButton btn;
	private JScrollPane js;
	
	
	public UrlTest1_ex() {
		setTitle("URL");
		
		layInit();
		
		setBounds(600, 300, 400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void layInit(){
		JPanel panel1 = new JPanel();
		txt_F = new JTextField("http://",20);
		txt_F.setToolTipText("http://www.naver.com:80/index.html");
		btn = new JButton("확인");
		panel1.add(new JLabel("URL: "));
		panel1.add(txt_F);
		panel1.add(btn);
		add("North", panel1);
		txt_A = new JTextArea();
		js = new JScrollPane(txt_A);
		add("Center", js);
		
		btn.addActionListener(this);
		btn.setMnemonic(KeyEvent.VK_ENTER);
		txt_F.requestFocus();
		//js.getVerticalScrollBar().setValue(js.getVerticalScrollBar().getMaximum());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn){
			try {
				URL url = new URL(txt_F.getText());
				InputStream in = url.openStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				
				String ss;
				while((ss = br.readLine()) != null){
					txt_A.append(ss+"\n");
					js.getVerticalScrollBar().setValue(js.getVerticalScrollBar().getMaximum());
				}
				in.close();
				br.close();
			} catch (Exception e2) {
				System.out.println("에러낫따");
			}
		}
	}
	
	public static void main(String[] args) {
		new UrlTest1_ex();
	}
}
