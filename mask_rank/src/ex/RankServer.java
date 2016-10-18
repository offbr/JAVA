package ex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class RankServer implements Runnable{
	
	ServerSocket ss;
	ArrayList<Client> list= new ArrayList<>();
	Client client;
	
	public RankServer() {  
		try {
			ss = new ServerSocket(7777);      		//서버시작
			System.out.println("서버시작 중...");
			new Thread(this).start();
		} catch (Exception e) {
			System.out.println("ChatServer 생성자 오류:"+e);
			System.exit(0);
		}	
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Socket socket=ss.accept();
				client=new Client(socket);
				System.out.println("클라이언트 연결 및 생성 성공");
				client.start();
				list.add(client);
			} catch (Exception e) {
				System.out.println("client 연결에러");
			}	
		}
	}
	
	class Client extends Thread{
		Connection con;
		PreparedStatement pst;
		ResultSet rs;
		String sql;
		DataInputStream datain;
		DataOutputStream dataout;
		Socket socket;
	
		public Client(Socket socket) {								//client 생성및 소켓연결
			try {
				this.socket=socket;
				datain=new DataInputStream(socket.getInputStream());     //클라이언트 소켓과 in out 스트림연결
				dataout=new DataOutputStream(socket.getOutputStream());
				
			} catch (Exception e) {
				System.out.println("client 생성자 오류"+e);
				return;
			}
		}
		
		@Override
		public void run() {										//클라이언트로부터 메세지수신
			boolean loginstate=false;
			String account_id;
			String account_nickname;
			String account_name;
			String account_gen;
			String account_age;
			String account_phone;
			String account_memo;
			String account_rankno;
			String account_image;
			String account_log;
			
			int login_false_count=0;
			while(true){	
				try {
					String msg=datain.readUTF();
					if(msg==null||msg.equals(""))return;
					if(msg.charAt(0)==('n')){	//신규가입시								
						try {
							accdb();
							sql="insert into C_account values(?,?,?,?,?,?,?,?,?,?,?)";
							pstinit();
							pst.setString(1,msg.split("/")[1]);
							pst.setString(2,msg.split("/")[2]);
							pst.setString(3,msg.split("/")[3]);
							pst.setString(4,msg.split("/")[4]);
							pst.setString(5,msg.split("/")[5]);
							pst.setString(6,msg.split("/")[6]);
							pst.setString(7,msg.split("/")[7]);
							pst.setString(8,msg.split("/")[8]);
							pst.setString(9,msg.split("/")[9]);
							pst.setString(10,msg.split("/")[10]);
							pst.setString(11,msg.split("/")[11]);
							init();
						} catch (Exception e) {
							System.out.println("신규가입 에러"+e);
						}finally{
							closedb();
						}
					}else if(msg.charAt(0)==('l')){//로그인시
													//로그인검사후 로그인state true or false변경
						try {
							if(loginstate==false){
								accdb();
								
								sql="select id from c_account where id=?";
								pstinit();
								pst.setString(1,msg.split("/")[1]);
								init();
								
								if(rs.next()){
									
									sql="select * from C_account where id=? and pwd=?";
									pstinit();
								
									pst.setString(1,msg.split("/")[1]);
									pst.setString(2,msg.split("/")[2]);
									init();
									if(rs.next()){
										loginstate=true;
										account_id=rs.getString("id");
										account_nickname=rs.getString("nickname");
										account_name=rs.getString("name");
										account_gen=rs.getString("gen");
										account_age=rs.getString("age");
										account_phone=rs.getString("phone");
										account_memo=rs.getString("memo");
										account_rankno=rs.getString("rank_no");
										account_image=rs.getString("image");
										account_log=rs.getString("log");
									}
								}
							}
						} catch (Exception e) {
							System.out.println("login fail"+e);				//로그인실패시
						}finally{
							closedb();
						}
						if(loginstate==false){							//l메시지를 받고 로그인실패상태로 접속시 서버상 로그인후행동접근 불가 
							dataout.writeUTF("f");
							continue;
						}
																		//로그인후 행동에 대해서
						if(msg.charAt(1)==('o')){ //로그아웃시
							loginstate=false;
							account_id=null;
							account_nickname=null;
							account_name=null;
							account_gen=null;
							account_age=null;
							account_phone=null;
							account_memo=null;
							account_rankno=null;
							account_image=null;
							account_log=null;
						}
					}else if(msg.charAt(0)==('x')){//종료시
						datain.close();
						dataout.close();
						socket.close();
						list.remove(this);
						
						System.out.println("사용자가 종료하였습니다");
					}
					
				} catch (Exception e) {
					System.out.println("읽기에러" + e);
				}	
			} 
		}
		public void messageSend(){
			
		}
		public void messageSendAll(){
			
		}
		public void	fileSend(){
			
		}
		public void fileSendArea(){
			
		}
		public void fileReceive(){
			
		}
		public void accdb(){    //db연결 메소드
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@59.29.232.162:1521:xe", "class1", "1234");
			} catch (Exception e) {
				System.out.println("accdb err"+e);
			}
		}
		private void pstinit(){
			try {
				pst = con.prepareStatement(sql);
			} catch (Exception e) {
				System.out.println("pst err"+e);
			}
		}
		private void init() { //db연결시 
			try {
				rs = pst.executeQuery();
			} catch (Exception e) {
				System.out.println("dbinit err"+e);
			}
		}
		private void closedb(){
			try {
				rs.close();
				pst.close();
				con.close();
			} catch (Exception e) {
				System.out.println("close err"+e);
			}
		}
	}
	public static void main(String[] args) {
		new RankServer();
	}
}
