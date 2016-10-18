package pack.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class MyAuth extends Authenticator{ // 인증 처리
	private String fromEmail, password;
	
	public MyAuth(String fromEmail, String password) {
		this.fromEmail = fromEmail;
		this.password = password;
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(fromEmail, password); 
	}
	
}
