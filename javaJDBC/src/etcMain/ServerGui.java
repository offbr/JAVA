package etcMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerGui extends JFrame implements ActionListener{
	private JTextArea jta = new JTextArea(40, 25);
	private JTextField jtf = new JTextField(25);
	
	//연동
	private SeverBack sever = new SeverBack();
	
	
	public ServerGui() {
		setTitle("SeverGuI");
		add("South",jtf);
		add("North",jta);
		jta.setEditable(false);
		jtf.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 300, 400);
		setVisible(true);
		
		sever.setting();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = jtf.getText() + "\n";
		jta.append(str);
		jtf.setText("");
	}
	
	public void appendStr(String str){
		jta.append(str);
		System.out.println("Client: " + str);
	}
	
	public static void main(String[] args) {
		new ServerGui();

	}

}
