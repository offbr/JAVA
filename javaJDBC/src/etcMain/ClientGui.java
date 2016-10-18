package etcMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGui extends JFrame implements ActionListener {

	private JTextArea jta = new JTextArea(40, 25);
	private JTextField jtf = new JTextField(25);
	
	
	private ClientBack client = new ClientBack();

	public ClientGui() {
			setTitle("ClientGuI");
			add("South",jtf);
			add("North",jta);
			jta.setEditable(false);
			jtf.addActionListener(this);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(200, 100, 300, 400);
			setVisible(true);
		
			client.connet();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = jtf.getText() + "\n";
		jta.append(str);
		jtf.setText("");
	}

	public static void main(String[] args) {
		new ClientGui();

	}

}
