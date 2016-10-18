package ex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
 
public class ClientBack {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ClientGui gui;
    private String msg;
    private String nickName;
 
    public final void setGui(ClientGui gui) {
        this.gui = gui;
    }
 
    public void connet() {
        try {
            socket = new Socket("192.168.0.6", 7777);
            
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            //접속하자마자 닉네임 전송. 서버가 닉네임으로 인식
            out.writeUTF(nickName); 
            while(in!=null){
                msg=in.readUTF();
                gui.appendMsg(msg);             
            }
        } catch (IOException e) {
        	gui.appendMsg("접속 에러 " + e); 
        }
    }
 
    public static void main(String[] args) {
        ClientBack clientBack = new ClientBack();
        clientBack.connet();
    }
 
    public void sendMessage(String msg2) {
        try {
            out.writeUTF(msg2);
        } catch (IOException e) {
        	 gui.appendMsg("상대방에게 전송 실패  " + e);
        }
    }
 
    public void setNickname(String nickName) {
        this.nickName = nickName;
    }
}