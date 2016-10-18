package pack.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.*;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Mail_ex extends JFrame implements ActionListener{
	private JTextField txt_email, txt_header, txt_etc;
	private JTextArea txt_a;
	private JRadioButton btn_TEXT, btn_HTML;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btn, btn_sel;
	private JFileChooser chooser;
	
	private String password = "acorn123test";
	private String toMail = "acorngoodjob@gmail.com";
	private String fromName = "고길동";
	private String subject = "12345678910";
	private String content = "안녕하세요"; 
	private String mail_type;

	public Mail_ex() {
		setTitle("메일");
		
		layInit();
		
		setBounds(900, 300, 380, 410);
		setVisible(true);		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void layInit(){
		JPanel panel1 = new JPanel();
		txt_email = new JTextField("acorngoodjob@gmail.com", 20);
		panel1.add(new JLabel("Email: "));
		panel1.add(txt_email);
		add("North", panel1);
		
		JPanel panel2 = new JPanel();
		JPanel panel2_1 = new JPanel();
		panel2.setLayout(new BorderLayout());
		txt_header = new JTextField(20);
		panel2_1.add(new JLabel("  제목: "));
		panel2_1.add(txt_header);
		panel2.add("North", panel2_1);
		
		JPanel panel2_2 = new JPanel();
		panel2_2.setLayout(new BorderLayout());
		
		JPanel panel2_3 = new JPanel();
		btn_TEXT = new JRadioButton("TEXT", true);
		btn_HTML = new JRadioButton("HTML");
		panel2_3.add(new JLabel("속성: "));
		panel2_3.add(btn_TEXT);
		panel2_3.add(btn_HTML);
		panel2_2.add("North", panel2_3);
		
		JPanel panel2_4 = new JPanel();
		txt_a = new JTextArea(11,25);
		JScrollPane js = new JScrollPane(txt_a);
		panel2_4.add(new JLabel("내용: "));
		panel2_4.add(js);
		panel2_2.add("Center", panel2_4);
		panel2.add("Center", panel2_2);
		
		add("Center", panel2);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		
		JPanel panel3_1 = new JPanel();
		txt_etc = new JTextField(15);
		btn_sel = new JButton("선택");
		panel3_1.add(new JLabel("첨부파일: "));
		panel3_1.add(txt_etc);
		panel3_1.add(btn_sel);
		
		panel3.add("North", panel3_1);
		
		JPanel panel3_2 = new JPanel();
		btn = new JButton("전송");
		panel3_2.add(btn);
		panel3.add("Center", panel3_2);
		
		add("South", panel3);
	
		//버튼 그룹 및 버튼액션 설정
		buttonGroup.add(btn_TEXT);
		buttonGroup.add(btn_HTML);
		btn_TEXT.addActionListener(this);
		btn_HTML.addActionListener(this);
		btn.addActionListener(this);
		btn_sel.addActionListener(this);
		
		//각종 수정금지
		txt_etc.setEditable(false);
		txt_etc.setForeground(Color.blue);
		js.getVerticalScrollBar().setValue(js.getVerticalScrollBar().getMaximum());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(btn_TEXT.isSelected()){
			mail_type = "text/plain; charset=utf-8";
			//txt_a.setText("");
		}else if (btn_HTML.isSelected()){
			mail_type = "text/html; charset=utf-8";
			//txt_a.setText("<html><body></body></html>");
		}
		
		if(e.getSource() == btn){
			//내이메일, 패스워드, 보내는이 이메일, 내 이름, 이메일제목, 이메일내용
			System.out.println(txt_a.getText());
			System.out.println(mail_type);
			sendMail(toMail, password, txt_email.getText(), fromName, txt_header.getText(), txt_a.getText());
		}
		
		if(e.getSource() == btn_sel){
			chooser = new JFileChooser("C:/work/sou/java7/src/image/");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); //파일만 본다
			int result = chooser.showOpenDialog(this);
				if(result == JFileChooser.CANCEL_OPTION){
					txt_etc.setText("");
				}else{
					txt_etc.setText(chooser.getSelectedFile().toString());
				}
		}
	}
	
	private void sendMail(String toMail, String password, String gomail, String fromName, String subject, String content) {
		try {
			// 서버에 대한 설정
			Properties props = new Properties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.auth", "true");

			// 인증에 대한 부분
			MyAuth auth = new MyAuth(toMail, password);
			Session session = Session.getDefaultInstance(props, auth);
			
			// 메시지에 내용
			MimeMessage msg = new MimeMessage(session);
			msg.setHeader("content-type", mail_type);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail, fromName, "utf-8"));
			 
			// 보낼 때
			msg.setSubject(subject);
			msg.setContent(content, mail_type); //text / html type
			msg.setSentDate(new Date());

			// 첨부파일 //보낼 때 코드 대신 이 코드를 쓸 수 도 있다
			if(!txt_etc.getText().equals("")){
				Multipart multipart = new MimeMultipart();
				MimeBodyPart bodyPart = new MimeBodyPart();
				bodyPart.setText(content, "utf-8");
				multipart.addBodyPart(bodyPart);

				FileDataSource fds = new FileDataSource(new File(txt_etc.getText()));
				bodyPart.setDataHandler(new DataHandler(fds));
				bodyPart.setFileName(fds.getName());
				multipart.addBodyPart(bodyPart);
				msg.setContent(multipart);
			}
			// 보내기
			Transport.send(msg);
			JOptionPane.showConfirmDialog(this, "메일 전송완료");
		} catch (Exception e) {
			System.out.println("sendMail err: " + e);
		}
	}
	
	public static void main(String[] args) {
		new Mail_ex();
	}
}


































