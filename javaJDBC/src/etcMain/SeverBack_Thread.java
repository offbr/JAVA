package etcMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SeverBack_Thread extends JFrame implements ActionListener, Runnable{
	private JTextArea jta = new JTextArea(25, 25);
	private JTextField jtf = new JTextField(25);
	private JScrollPane js = new JScrollPane(jta);
	
	private ServerSocket serverSocket; // 서버소켓 포트를 만든다
	private Socket socket;  // 서버소켓포트로 accept 연결한다.
	private DataInputStream in; //내가 읽어들이는 데이터 
	private	DataOutputStream out; //내가 보내는 데이터
	
	Thread T1 = new Thread(this);
	
	public SeverBack_Thread() {
		setTitle("SeverGuI");
		add("South",jtf);
		add("North",js);
		jta.setEditable(false);
		jtf.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 300, 470);
		setVisible(true);
		
		setting();
		
	}

	public void setting() {
		try {
			serverSocket = new ServerSocket(8945); // 7777포트를 만든다
			jta.setText("sever 준비 완료\n");
			socket = serverSocket.accept(); //7777 포트번호를 가지고 소켓을 만든다
			jta.append(socket.getInetAddress() + "로 상대방이 접속\n");
			in = new DataInputStream(socket.getInputStream()); //상대방의 데이터를 가져온다.
			out = new DataOutputStream(socket.getOutputStream()); //내 데이터를 상대방에게 보내준다.

			T1.start();
		} catch (Exception e) {
			System.out.println("서버 에러가 난다 " + e);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = jtf.getText();
		if ("".equals(msg)) //값이 없으면 보내지 않는다
			return;
		if (out != null) { 
			try {
				out.writeUTF("sever" + " > " + msg); //내 데이터를 상대방에게 보내준다
			} catch (IOException e1) {
				jta.append("sever>상대방에게 전송을 실패했습니다: " + e1);
			}
			jta.append("\n" + "sever" + " > " + msg); //보내준 데이터를 내 텍스트에도 입력한다
			js.getVerticalScrollBar().setValue(js.getVerticalScrollBar().getMaximum());
			jtf.setText("");
		}	
	}
	
	@Override
	public void run() {
		try {
			while (in != null) { 
				String str = in.readUTF(); //값이 없을 때 까지 상대방이 보내준 데이터를 가져온다
				jta.append("\n" + str); //가져온 데이터를 내 텍스트에 입력한다
				js.getVerticalScrollBar().setValue(js.getVerticalScrollBar().getMaximum());
				Thread.sleep(1000);
			}			
		} catch (Exception e2) {
			jta.append("\n client의 데이터가 제대로 들어오지 않았습니다\n" + e2);
		}	
	}
	
	public static void main(String[] args) {
		new SeverBack_Thread();
	}
}