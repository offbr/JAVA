import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.io.*;
import java.net.*;
public class Date_ChatClient extends JFrame implements ActionListener, Runnable{
    private JLabel jLabel1 = new JLabel();
    private JTextField txtname = new JTextField();
    private JButton btnconn = new JButton();
    private JTextArea txtarea = new JTextArea();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextField txtsend = new JTextField();
    private JButton btnok = new JButton();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel lblinwon = new JLabel();
    private JRadioButton radio1 = new JRadioButton();
    private JRadioButton radio2 = new JRadioButton();
    private JButton btnclose = new JButton();
    private List list = new List();
    private JButton btnchange = new JButton();
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    int count = 0; //접속 인원수
     
    public Date_ChatClient() {
        try {
            jbInit();
            addListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
   }
    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize(new Dimension(652, 264));
        this.setTitle("채팅 프로그램");
        this.setBackground(new Color(198, 214, 255));
        jLabel1.setText("대화명:");
        jLabel1.setBounds(new Rectangle(15, 10, 45, 25));
        txtname.setBounds(new Rectangle(60, 10, 105, 25));
        btnconn.setText("접속");
        btnconn.setBounds(new Rectangle(165, 10, 60, 25));
        jScrollPane1.setBounds(new Rectangle(15, 40, 495, 155));
        txtsend.setBounds(new Rectangle(15, 200, 435, 25));
        btnok.setText("확인");
        btnok.setBounds(new Rectangle(450, 200, 60, 25));
        jLabel2.setText("접속자 목록");
        jLabel2.setBounds(new Rectangle(520, 10, 75, 20));
        jLabel3.setText("인원:");
        jLabel3.setBounds(new Rectangle(530, 170, 35, 25));
        lblinwon.setText("0");
        lblinwon.setBounds(new Rectangle(565, 170, 50, 25));
        lblinwon.setBackground(new Color(198, 198, 200));
        lblinwon.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        lblinwon.setHorizontalAlignment(SwingConstants.CENTER);
        lblinwon.setHorizontalTextPosition(SwingConstants.CENTER);
        radio1.setText("귓속말");
        radio1.setBounds(new Rectangle(345, 10, 70, 25));
        radio2.setText("귓속말해제");
        radio2.setBounds(new Rectangle(420, 10, 90, 25));
        btnclose.setText("나가기");
        btnclose.setBounds(new Rectangle(530, 200, 90, 25));
        list.setBounds(new Rectangle(525, 40, 110, 120));
        btnchange.setText("대화명 변경");
        btnchange.setBounds(new Rectangle(230, 10, 110, 25));
        ButtonGroup group = new ButtonGroup();
        group.add(radio1); 
        group.add(radio2);
        this.getContentPane().add(btnchange, null);
        this.getContentPane().add(list, null);
        this.getContentPane().add(btnclose, null);
        this.getContentPane().add(radio1, null);
        this.getContentPane().add(radio2, null);
        this.getContentPane().add(lblinwon, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(btnok, null);
        this.getContentPane().add(txtsend, null);
        jScrollPane1.getViewport().add(txtarea, null);
        this.getContentPane().add(jScrollPane1, null);
        this.getContentPane().add(btnconn, null);
        this.getContentPane().add(txtname, null);
        this.getContentPane().add(jLabel1, null);
    }
 
    public void addListener(){
        txtname.addActionListener(this);
        txtsend.addActionListener(this);
        btnok.addActionListener(this);
        btnconn.addActionListener(this);
        btnclose.addActionListener(this);
        btnchange.addActionListener(this);
   }

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == txtname || e.getSource() == btnconn) {			// 대화명 입력 후 접속
			if(txtname.getText().equals("")){
				JOptionPane.showMessageDialog(this, "대화명 입력!!!!!!!!!!!!!");
				txtname.requestFocus();
				return;
			}
			txtname.setEnabled(false);
			try {
				socket = new Socket("192.168.0.48", 5555);
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF((txtname.getText() + "\n"));
				
				new Thread(this).start();
			} catch (Exception e2) {
				System.out.println("접속 에러" + e2);
			}
		} else if (e.getSource() == txtsend || e.getSource() == btnok) { 			// 메세지 전송
			try {
				if(radio1.isSelected()){ //귓속말
					String name = list.getSelectedItem();
					out.writeUTF("/s" + name + "-" + txtsend.getText() + "\n");
					txtarea.append(name + "님에게 귓속말이 전달되었습니다.\n");
				}else{ //일반 메시지
					out.writeUTF(txtsend.getText() + "\n");
				}
				txtsend.setText("");
				txtsend.requestFocus();
			} catch (Exception e2) {
				System.out.println("메시지전송 에러" + e2);
			}
		} else if (e.getSource() == btnchange) { 			// 대화명 변경
			if(btnchange.getText().equals("대화명 변경")){
				btnchange.setText("변경 확인");
				txtname.setEnabled(true);
				txtname.requestFocus();
			}else{
				btnchange.setText("대화명 변경");
				txtname.setEnabled(false);
				try {
					out.writeUTF("/n " + txtname.getText() + "\n");
				} catch (Exception e2) {
					System.out.println("대화명 변경 에러: " + e2);
				}
			}
		} else if (e.getSource() == btnclose) { 			// 나가기
			try {
				out.writeUTF("/q\n");
				in.close(); out.close(); socket.close();
			} catch (Exception e2) {
				System.out.println("나가기 에러" + e2);
			} finally{
				System.exit(0);
			}
		}
	}

	@Override
	public void run() {
		while(true){
			try {
				String msg = in.readUTF(); //서버로부터 메시지 받기
				if(msg == null || msg.equals("")) return;
				
				if(msg.charAt(0) == '/'){
					if(msg.charAt(1) == 'c'){ //대화명(최초 접속자)
						list.add(msg.substring(2), count);
						count++;
						lblinwon.setText(String.valueOf(count));
						
						txtarea.append("**" + msg.substring(2) + " 님이 입장했습니다.\n");
						btnconn.setEnabled(false); //접속 버튼 비활성화
					}else if(msg.charAt(1) == 'q'){ //나가기(퇴장)
						txtarea.append("^^" + msg.substring(2) + " 님이 퇴장했습니다.\n");
						String cname = msg.substring(2);
						for (int i = 0; i < count; i++) {
							if(cname.equals(list.getItem(i))){
								list.remove(i);
								count--;
								lblinwon.setText(String.valueOf(count));
								break;
							}
						}
					}else if(msg.charAt(1) == 'n'){ //대화명 변경 /n옛이름 - 새이름
						String oldName = msg.substring(2, msg.indexOf('-'));
						String newName = msg.substring(2, msg.indexOf('-'));
						txtarea.append("*" + oldName + " 님의 대화명이 " + newName + " 으로 변경 되었습니다.\n");
						for (int i = 0; i < count; i++) {
							if(oldName.equals(list.getItem(i))){
								list.replaceItem(newName, i);
								break;
							}
						}
					}
				}else{ //일반메시지
					txtarea.append(msg + "\n");
					jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
		}
	}
   public static void main(String args[]){
        Date_ChatClient fr = new Date_ChatClient();
        fr.getPreferredSize();
        fr.setLocation(200,200);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}




























