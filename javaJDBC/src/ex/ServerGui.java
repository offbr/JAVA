package ex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerGui extends JFrame implements ActionListener {
    private JTextArea jta = new JTextArea(40, 25);
    private JTextField jtf = new JTextField(25);
    private JScrollPane js = new JScrollPane(jta);
    private ServerBack server = new ServerBack();
    
    public ServerGui() throws IOException {
    	add("Center", js);
		add("South", jtf);
        jta.setEditable(false);
        jtf.addActionListener(this);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(200, 100, 400, 600);
        setTitle("서버");
     
        server.setGui(this);
        server.setting();
    }
 
    public static void main(String[] args) throws IOException {
        new ServerGui();
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = jtf.getText() + "\n";
        jta.append(msg);
        jtf.setText("");
    }
 
    public void appendMsg(String msg) {
        jta.append(msg);
        jta.setCaretPosition(jta.getDocument().getLength());
    }
}