package ex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
public class ClientGui  extends JFrame implements ActionListener{
    private JTextArea jta = new JTextArea(40, 25);
    private JTextField jtf = new JTextField(25);
    private JScrollPane js = new JScrollPane(jta);
    private ClientBack client = new ClientBack();
    private static String nickName;
     
    public ClientGui() {
    	add("Center", js);
		add("South", jtf);
        jta.setEditable(false);
        jtf.addActionListener(this);
         
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(800, 100, 400, 600);
        setTitle("클라이언트");
        
        JPanel panel1 = new JPanel();
        JTextField nick= new JTextField(5);
        panel1.add(new JLabel("닉네임: "));
        panel1.add(nick);
        //프레임창 키면 닉네임 생성
        int yn = JOptionPane.showConfirmDialog(this, panel1, "닉네임", JOptionPane.YES_NO_OPTION);
        if(yn == JOptionPane.YES_OPTION) nickName = nick.getText();
        else System.exit(0);
        
        client.setGui(this);
        client.setNickname(nickName);
        client.connet();
    }
     
    public static void main(String[] args) {
        new ClientGui();
    }
    //프레임에 글쓰기
    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = nickName+ ":" + jtf.getText()+"\n";
        client.sendMessage(msg);
        jtf.setText("");
    }
 
    public void appendMsg(String msg) {
        jta.append(msg);
        jta.setCaretPosition(jta.getDocument().getLength());
    }
}