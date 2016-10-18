package etc;

//클라이언트 GUI
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatClient extends Frame implements ActionListener {
	String nickname = "";
	String serverIP = "127.0.0.1";
	int serverPort = 7777;
	DataInputStream in;
	DataOutputStream out;

	Panel p = new Panel(new BorderLayout());
	TextArea ta = new TextArea();
	TextField tf = new TextField();

	ChatClient(String nickname) {
		this.nickname = nickname;
		p.add(ta, "Center");
		p.add(tf, "South");

		ta.setEditable(false);
		tf.addActionListener(this);
		this.add(p);
//		this.addWindowListener(new MyWindowHandler());
		this.setBounds(600, 200, 300, 300);
		this.setVisible(true);
		tf.requestFocus();
		
		addWindowListener(new WindowAdapter() {               //종료
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	public static void main(String[] args) {
		try {
			ChatClient client = new ChatClient("Client");
			client.startClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startClient() throws UnknownHostException, IOException {
		Socket socket = new Socket(serverIP, serverPort);
		ta.setText("서버에 연결되었습니다.");
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		while (in != null) {
			String msg = in.readUTF();
			System.out.println("받은 메세지 : " + msg);
			ta.append("\n" + msg);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String msg = tf.getText();
		if ("".equals(msg))
			return;
		if (out != null) {
			try {
				out.writeUTF(nickname + " > " + msg);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		ta.append("\n" + nickname + " > " + msg);
		tf.setText("");
	}
}




