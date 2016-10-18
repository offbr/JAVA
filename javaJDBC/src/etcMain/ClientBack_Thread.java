package etcMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientBack_Thread extends JFrame implements ActionListener, Runnable{
	private JTextArea jta = new JTextArea(25, 25);
	private JTextField jtf = new JTextField(25);
	private JScrollPane js = new JScrollPane(jta);
	
	private Socket socket; // 소켓연결
	private DataInputStream in; // 내가 가져오는 데이터
	static DataOutputStream out; //내가 내보내는 데이터
	
	Thread T1 = new Thread(this);
	
	public ClientBack_Thread() {
		setTitle("ClientGuI");
		add("South",jtf);
		add("North",js);
		jta.setEditable(false);
		jtf.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 300, 470);
		setVisible(true);
	
		connet();
	}
	
	public void connet() {
		try {
			socket = new Socket("127.0.0.1", 8945); //서버의 아이피와 소켓포트로 접속한다
			jta.setText("sever 연결 완료\n");			
			out = new DataOutputStream(socket.getOutputStream()); // 내보내는 데이터, 소켓을 통해서 값을 보낸다
			in = new DataInputStream(socket.getInputStream()); // 읽어들이는 데이터, 소켓을 통해서 값을 얻는다

			T1.start();
		} catch (Exception e) {
			System.out.println("에러가 난다 " + e);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = jtf.getText(); // 내가 입력한 데이터를 msg에 치환
		if ("".equals(msg)) //값이 없으면 보내지 않는다
			return;
		if (out != null) {
			try {
				out.writeUTF("client" + " > " + msg);  //치환 된 데이터를 상대방에게 보낸다. 
			} catch (IOException e1) {
				jta.append("client>상대방에게 전송을 실패했습니다: " + e1);
			}
			jta.append("\n" + "client" + " > " + msg); //치환 된 데이터를 내 텍스트에 출력
			js.getVerticalScrollBar().setValue(js.getVerticalScrollBar().getMaximum());
			jtf.setText("");
		}	
	}
	
	@Override
	public void run() {
		try {
			while (in != null) {
				String str = in.readUTF(); //상대방의 데이터를 읽어온다.
				jta.append("\n" + str); //읽어온 데이터를 내 텍스트에 출력
				js.getVerticalScrollBar().setValue(js.getVerticalScrollBar().getMaximum());
				Thread.sleep(1000);
				}
		} catch (Exception e2) {
			jta.append("\n sever의 데이터가 제대로 들어오지 않았습니다\n" + e2);
		}
	}
	
	public static void main(String[] args) {
		new ClientBack_Thread();
	}
}
