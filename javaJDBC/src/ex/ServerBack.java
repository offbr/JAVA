package ex;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
 
public class ServerBack {
    private ServerSocket serverSocket;
    private Socket socket;
    private ServerGui gui;
    private String msg;
    //사용자들의 정보 저장
    private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();
 
    public final void setGui(ServerGui gui) {
        this.gui = gui;
    }
 
    public void setting() throws IOException {
            Collections.synchronizedMap(clientsMap); //동기화
            serverSocket = new ServerSocket(7777);
            while (true) {
                //서버가 계속 접속만 받는다
                socket = serverSocket.accept(); // 계속 반복해서 사용자를 받는다.
                System.out.println(socket.getInetAddress() + "에서 접속했습니다.");
                // 여기서 새로운 사용자 쓰레드 클래스 생성해서 소켓정보 담기
                Receiver receiver = new Receiver(socket);
                receiver.start();
            }
    }
 
    public static void main(String[] args){
        ServerBack serverBack = new ServerBack();
    }
    // 맵의내용(클라이언트) 저장과 삭제
    public void addClient(String nick, DataOutputStream out){
    	try {
    		sendMessage(nick + "님이 접속하셨습니다.\n");
            clientsMap.put(nick, out);	
		} catch (Exception e) {
			System.out.println("들어왔습니다");
		}
    }
 
    public void removeClient(String nick) {
    	try {
    		sendMessage(nick + "님이 나가셨습니다.\n");
            clientsMap.remove(nick);	
		} catch (Exception e) {
			System.out.println("나갔습니다");
		}
    }
    // 메시지 내용 전파
    public void sendMessage(String msg) {
        Iterator<String> it = clientsMap.keySet().iterator();
        String key = "";
        while (it.hasNext()) {
            key = it.next();
            try {
                clientsMap.get(key).writeUTF(msg);
            } catch (IOException e) {
                System.out.println("나갔다: " + e);
            }
        }
    }
    // -----------------------------------------------------------------------------
    class Receiver extends Thread {
        private DataInputStream in;
        private DataOutputStream out;
        private String nick;
        //네트워크를 계속 처리.
        public Receiver(Socket socket) throws IOException {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            nick = in.readUTF();
            addClient(nick, out);
        }
        //클라이언트들 채팅 받기
        public void run() {
            try {
                while (in != null) {
                    msg = in.readUTF();
                    sendMessage(msg);
                    gui.appendMsg(msg);
                }
            } catch (IOException e) {
                // 사용접속종료시 여기서 에러 발생. 그럼나간거에요.. 여기서 리무브 클라이언트 처리 해줍니다.
                removeClient(nick);
            }
        }
    }
}