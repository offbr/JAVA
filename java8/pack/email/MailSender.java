package pack.email;

import java.io.File;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class MailSender {

	public static void main(String[] args) {
		String password = "acorn123test";
		String toMail = "acorngoodjob@gmail.com";
		String fromName = "고길동";
		String subject = "12345678910";
		String content = "안녕하세요"; //일반 텍스트로 전달
		
		//String content = "<html><body>html 형식으로 전달<br>"
		//		+ "<img src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWQUbC9Kn0953otGL1J0ASlEnnZi-qmP4-6CgComyE9bblDmo2_KWZrA' />"
		//		+ "</body></html>";
		
		
		MailSender mailSender = new MailSender();
		mailSender.sendMail(toMail, password, fromName, subject, content);
	}

	public void sendMail(String toMail, String password, String fromName, String subject, String content) {
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
			msg.setHeader("content-type", "text/plain; charset=utf-8");
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail, fromName, "utf-8"));
			//msg.addRecipient(Message.RecipientType.TO, new InternetAddress("aaa@gmail.com", fromName, "utf-8"));
			//msg.addRecipient(Message.RecipientType.TO, new InternetAddress(배열, fromName, "utf-8"));
			
			// 보낼 때
			msg.setSubject(subject);
			//msg.setContent(content, "text/plain; charset=utf-8"); //일반 텍스트 type
			msg.setContent(content, "text/html; charset=utf-8"); //html type
			msg.setSentDate(new Date());

			// 첨부파일 //보낼 때 코드 대신 이 코드를 쓸 수 도 있다
			Multipart multipart = new MimeMultipart();
			MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setText(content, "utf-8");
			multipart.addBodyPart(bodyPart);
		
			FileDataSource fds = new FileDataSource(new File("C:/work/sou/java7/src/image/book10.jpg")); //파일추서나 파일 다이얼로그 사용할 수 있다
			bodyPart.setDataHandler(new DataHandler(fds));
			bodyPart.setFileName(fds.getName());
			multipart.addBodyPart(bodyPart);
			msg.setContent(multipart);
			
			// 보내기
			Transport.send(msg);
			System.out.println("메일전송 완료");
		} catch (Exception e) {
			System.out.println("sendMail err: " + e);
		}
	}
}
