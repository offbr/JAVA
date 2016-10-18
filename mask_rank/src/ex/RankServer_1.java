package ex;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;



public class RankServer_1 implements Runnable{
	
	ServerSocket ss;
	HashMap<String,Client> list= new HashMap<>();
	String basic_file_path="D:/userimage/";
	String file_path;
	Client client;
	
	public RankServer_1() {  
		try {
			ss=new ServerSocket(7777);      		//서버시작
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
				list.put(socket.getInetAddress().toString(), client);
				client.start();
				
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
		String ip;
		String msg;
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
		public Client(Socket socket) {								//client 생성및 소켓연결
			try {
				this.socket=socket;
				datain=new DataInputStream(socket.getInputStream());     //클라이언트 소켓과 in out 스트림연결
				dataout=new DataOutputStream(socket.getOutputStream());
				ip=socket.getInetAddress().toString();
			} catch (Exception e) {
				System.out.println("client 생성자 오류"+e);
				return;
			}
			
			
		}
		@Override
		public void run() {										//클라이언트로부터 메세지수신
			boolean loginstate=false;
			
			
			int login_false_count=0;
			while(true){
				
				try {
					msg=datain.readUTF();
					if(msg==null||msg.equals(""))return;
					if(msg.split(",")[10].matches("_")){					//파일명에 특스문자 들어올시 거부처리
						dataout.writeUTF("nf");
						return;
						}  				
					if(msg.charAt(0)==('n')){	//신규가입시								
						
						try {
							accdb();
							sql="insert into C_account values(?,?,?,?,?,?,?,?,?,?,?)";
							pstinit();
							pst.setString(1,msg.split(",")[1]);
							pst.setString(2,msg.split(",")[2]);
							pst.setString(3,msg.split(",")[3]);
							pst.setString(4,msg.split(",")[4]);
							pst.setString(5,msg.split(",")[5]);
							pst.setString(6,msg.split(",")[6]);
							pst.setString(7,msg.split(",")[7]);
							pst.setString(8,msg.split(",")[8]);
							pst.setString(9,msg.split(",")[9]);
							fileReceive();
							pst.setString(10,file_path);
							pst.setString(11,msg.split(",")[11]);		//서버에서 파일저장시 
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
								pst.setString(1,msg.split(",")[1]);
								init();
								
								
								if(rs.next()){
									
									sql="select * from C_account where id=? and pwd=?";
									pstinit();
								
									pst.setString(1,msg.split(",")[1]);
									pst.setString(2,msg.split(",")[2]);
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
						
						if(msg.charAt(1)==('c')){						//페이크프로필접근
							if (msg.charAt(2)=='c'){					//페이크프로필생성
								
							}else if(msg.charAt(2)=='u'){				//페이크프로필업데이트
								
							}else if(msg.charAt(2)=='d'){				//페이크프로필 삭제
								
							}
						}else if(msg.charAt(1)==('v')){					//사진순위 변경시
							
						}else if(msg.charAt(1)==('t')){					//채팅접근시
							if(msg.charAt(2)==('/')){
								if(msg.charAt(3)==('r')){				//귓속말시
									Iterator iter=list.keySet().iterator();
									while(iter.hasNext()){
										Client client =list.get(iter.next());
										if(client.account_nickname.equals(msg.split(" ")[1])){
											messageSend(client, msg);
										}
									}
									
									
								}else if(msg.charAt(3)==('s')){			//쪽지보낼시
									
								}
							}else{
								messageSendAll();						//일반채팅할시
							}
						}else if(msg.charAt(1)==('p')){					//사진정보 접근시
							if(msg.charAt(2)==('n')){					//사진생성시
								
							}else if(msg.charAt(2)==('u')){				//사진수정시
								
							}else if(msg.charAt(2)==('d')){				//사진삭제시
								
							}
						}else if(msg.charAt(1)==('u')){					//프로필정보 접근시
							
						}else if(msg.charAt(1)==('o')){ //로그아웃시
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
						list.remove(ip);
						
						
						System.out.println("사용자가 종료하였습니다");
					}
					
				} catch (Exception e) {
				}
				
			}
			
		}
		public void messageSend(Client client,String msg){
			try {
				client.dataout.writeUTF(msg);
			} catch (Exception e) {
				System.out.println("message send err"+e);
			}
		}
		public void messageSendAll(){
			try {
				Iterator iter=list.keySet().iterator();
				while(iter.hasNext()){
					Client client=list.get(iter.next());
						messageSend(client, msg);
					
				}
			} catch (Exception e) {
				System.out.println("messageSendAll err "+e);
			}
			
		}
		public void	fileSend(){
			
		}
		public void fileSendArea(){
			
		}
		public void fileReceive(){
			BufferedOutputStream bos;
			try {
				
				file_path=basic_file_path+			//기본파일경로_id_filename
						"_"+msg.split(",")[1]+"_"+				//id
						msg.split(",")[10].split("/")[msg.split(",")[10].split("/").length-1];	//순수파일명
				
				File f = new File(file_path);
	            bos = new BufferedOutputStream(new FileOutputStream(f));
	            System.out.println(msg.split(",")[10] + "파일을 생성하였습니다.");
	 
	            // 바이트 데이터를 전송받으면서 기록
	            int len;
	            int size = 4096;
	            byte[] data = new byte[size];
	            while ((len = datain.read(data)) != -1) {
	                bos.write(data, 0, len);
	            }
	            bos.flush();
		        bos.close();
			} catch (Exception e) {
				// TODO: handle exception
				 System.out.println("파일받기 err "+e);
			}
			
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
		new RankServer_1();
	}
}
