package ex;

import javax.swing.*;
import designcomponent.*;
import java.awt.*;
import java.sql.*;
import java.text.DateFormat;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.DataFormatException;

public class Mask_Manager extends BPanel implements ActionListener, Runnable{
   private BTable table;
   private String [] title = {"ID","Rank", "NickName", "Memo", "Age", "Gen", "Image","Name", "tel","Pwd", "Log"};
   private String [][] rowData = new String[0][11];
    private DefaultTableModel model;
    
    private BTable table1;
   private String [] title1 = {"Acount_ID","rank_no", "Name","Memo", "Age", "Gen" ,"ID_FilePath"};
   private String [][] rowData1 = new String[0][1];
    private DefaultTableModel model1;
   
    private BTable table2;
   private String [] title2 = {"현재 접속자"};
   private String [][] rowData2 = new String[0][1];
    private DefaultTableModel model2;
    
    private BTextField txt_JoinCount, txt_count, txt_FakeCount; //총 가입자수 , 현재 접속자수, 페이크프로필 등록수
    private BButton btn_start, btn_stop, btn_main, btn_update; //서버시작, 메인, 정보수정
   
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    private String sql;

    private ServerSocket ss;
    private HashMap<String,Client> list= new HashMap<>();      //현재 접속자 리스트
    private HashMap<Integer,String[]> all_list=new HashMap<>(); //전체 랭크 리스트<랭크,(유저정보,페이크정보)>
    private HashMap<String,String[]> user_list=new HashMap<>(); //유저 랭크 리스트<id,유저정보>
    private HashMap<String,String[]> fake_list=new HashMap<>(); //페이크 리스트<id_name_filepath,페이크정보>
    private Iterator<String> iter;
    private String basic_file_path="C:/image/";
    private String file_path;
    private Client client;
    int user_count;
    int fake_count;
   
    private Thread thread =new Thread(this);

   /**
    * Create the panel.
    */
   public Mask_Manager() {
      Collections.synchronizedMap(list);
      layInit();
   }
   
   private void layInit(){
      setLayout(null);
      
      BPanel panel = new BPanel();
      panel.setBounds(12, 10, 734, 611);
      add(panel);
      panel.setLayout(null);
      
      BLabel lbl = new BLabel("관리자");
      lbl.setBounds(319, 10, 97, 24);
      panel.add(lbl);
      lbl.setFont(new Font("굴림", Font.BOLD, 20));
      
      BScrollPane scrollPane = new BScrollPane();
      scrollPane.setBounds(12, 44, 710, 215);
      panel.add(scrollPane);
      
      model = new DefaultTableModel(rowData, title);
      table = new BTable(model);
      scrollPane.setViewportView(table);
      
      btn_start = new BButton("서버시작");
      btn_start.setBounds(12, 568, 97, 23);
      panel.add(btn_start);
      
      btn_main = new BButton("메인");
      btn_main.setBounds(515, 568, 97, 23);
      panel.add(btn_main);
      
      btn_update = new BButton("삭제");
      btn_update.setBounds(625, 568, 97, 23);
      panel.add(btn_update);
      
      BLabel lbl_JoinCount = new BLabel("가입자 수");
      lbl_JoinCount.setBounds(548, 272, 97, 15);
      panel.add(lbl_JoinCount);
      
      txt_JoinCount = new BTextField();
      txt_JoinCount.setBounds(625, 269, 97, 21);
      panel.add(txt_JoinCount);
      txt_JoinCount.setColumns(10);
      
      btn_stop = new BButton("서버중지");
      btn_stop.setBounds(129, 568, 97, 23);
      panel.add(btn_stop);
      
      BScrollPane scrollPane1 = new BScrollPane();
      scrollPane1.setBounds(12, 300, 710, 215);
      panel.add(scrollPane1);
      
      model1 = new DefaultTableModel(rowData1, title1);
      table1 = new BTable(model1);
      scrollPane1.setViewportView(table1);

      
      BLabel lbl_FakeCount = new BLabel("프로필등록 수");
      lbl_FakeCount.setBounds(529, 529, 83, 15);
      panel.add(lbl_FakeCount);
      
      txt_FakeCount = new BTextField();
      txt_FakeCount.setBounds(625, 526, 97, 21);
      panel.add(txt_FakeCount);
      txt_FakeCount.setColumns(10);
   
      BPanel panel1 = new BPanel();
      panel1.setBounds(758, 10, 130, 611);
      add(panel1);
      panel1.setLayout(null);
      
      BScrollPane scrollPane2 = new BScrollPane();
      scrollPane2.setBounds(12, 40, 106, 505);
      panel1.add(scrollPane2);
      
      model2 = new DefaultTableModel(rowData2, title2);
      table2 = new BTable(model2);
      scrollPane2.setViewportView(table2);
      
      BLabel lbl_count = new BLabel("접속자 수");
      lbl_count.setBounds(12, 572, 82, 15);
      panel1.add(lbl_count);
      
      txt_count = new BTextField();
      txt_count.setBounds(74, 569, 44, 21);
      panel1.add(txt_count);
      txt_count.setColumns(10);
      
      btn_start.addActionListener(this);
      btn_stop.addActionListener(this);
      btn_main.addActionListener(this);
      btn_update.addActionListener(this);
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == btn_start){ //서버스타트
         setting(); //서버를 열어준다
         display_user(); //현재 가입된 회원자들의 정보를 보여준다
         display_fake(); //현재 가입된 회원자들의 정보를 보여준다
      }else if(e.getSource() == btn_stop){ //서버중지
         try {
            iter=list.keySet().iterator();
            while(iter.hasNext()){
               String next=iter.next();
               list.get(next).socket.close();
               list.get(next).interrupt();
               list.remove(next);
            }
            thread.interrupt();
            rs.close();
            pst.close();
            con.close();
            System.out.println("서버중지 완료");
         } catch (Exception e5) {
            System.out.println("close err: " + e5);
         }
      }else if(e.getSource() == btn_main){
         System.out.println("메인");
      }else if(e.getSource() == btn_update){ //정보수정
        try {
           BPanel panel_d = new BPanel();
           BTextField txt_delete = new BTextField(10);
           panel_d.add(new BLabel("ID"));
           panel_d.add(txt_delete);
           int re = JOptionPane.showConfirmDialog(this, panel_d, "삭제", JOptionPane.YES_NO_OPTION);
           if(re == JOptionPane.YES_OPTION){
              if(!txt_delete.getText().equals("")){
                 model.setNumRows(0);
                 accdb();
                 sql = "delete c_account where id=?";
                 pst = con.prepareStatement(sql);
                 pst.setString(1, txt_delete.getText());
                 pst.executeUpdate();
                 display_user(); //현재 가입된 회원자들의 정보를 보여준다
              }else{
                 JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
                 txt_delete.requestFocus();
              }
           }
      } catch (Exception e2) {

      }
         
      }
   }
   
   private void accdb(){
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         con = DriverManager.getConnection("jdbc:oracle:thin:@59.29.232.162:1521:xe", "class1", "1234");
      } catch (Exception e) {
         System.out.println("accdb err: "+e);
      }
   }
   
   private void display_user(){ //현재 가입된 회원자들의 정보를 보여준다
      try {
        model.setNumRows(0);
         accdb();
         sql = "select * from c_account";
         pst = con.prepareStatement(sql);
         rs = pst.executeQuery();
         int count = 0;
         while(rs.next()){
            String rowData[] = {
               rs.getString("id"),
               rs.getString("rank_no"),
               rs.getString("nickname"),
               rs.getString("memo"),
               rs.getString("age"),
               rs.getString("gen"),
               rs.getString("image"),
               rs.getString("name"),
               rs.getString("tel"),
               rs.getString("pwd"),
               rs.getString("log")};
            all_list.put(rs.getInt("rank_no"),rowData);
            user_list.put(rs.getString("id"),rowData);
            model.addRow(rowData);
            count++;
         }
         txt_JoinCount.setText(""+count);
         user_count=count;
      } catch (Exception e) {   
         System.out.println("display err: " + e);
      }
      
   }   
         
         
   private void display_fake() { // 현재 가입된 회원자들의 정보를 보여준다
      try {
         model1.setNumRows(0);
         accdb();
         sql = "select * from c_fakeprofile";
         
         pst = con.prepareStatement(sql);
         rs = pst.executeQuery();
        
         int count = 0;
         while (rs.next()) {
            
            String rowData1[] = { 
                  rs.getString("account_id"),
                  rs.getString("rank_no"), 
                  rs.getString("name"), 
                  rs.getString("memo"), 
                  rs.getString("age"),
                  rs.getString("gen"), 
                  rs.getString("id_name_filepath")
                  };
            model1.addRow(rowData1);
            
            all_list.put(rs.getInt("rank_no"),rowData1);
            fake_list.put(rs.getString("id_name_filepath"),rowData1);
            count++;
         }
         txt_FakeCount.setText("" + count);
         fake_count=count;
      } catch (Exception e) {
         System.out.println("display err: " + e);
      }
   }

   private void setting(){
      try {
         ss = new ServerSocket(8888);            //서버시작
         System.out.println("서버시작 중...");
         thread.start();
      } catch (Exception e) {
         JOptionPane.showMessageDialog(this, "서버 에러");
         System.exit(0);
      }    
   }
   
   @Override
   public void run() {
      while(true){
         try {
            Socket socket=ss.accept();
            client = new Client(socket,socket.getInetAddress().toString());
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
      String account_tel;
      String account_memo;
      String account_rankno;
      String account_image;
      String account_log;
      int nowpage=user_count+fake_count;
      int maxpage=user_count+fake_count;
      boolean loginstate;
      public Client(Socket socket,String ip) {                        //client 생성및 소켓연결
         try {
            this.ip=ip;
            this.socket=socket;
            datain = new DataInputStream(socket.getInputStream());     //클라이언트 소켓과 in out 스트림연결
            dataout = new DataOutputStream(socket.getOutputStream());
         } catch (Exception e) {
            System.out.println("client 생성자 오류"+e);
            return;
         }
      }
      
      @Override
      public void run() {                              //클라이언트로부터 메세지수신
         loginstate=false;
         boolean file_wait=false;
         String file_name="";
         int login_false_count=0;
         while(true){   
            try {
               //file_length=datain.readLong();
               if(file_wait==true){
                  fileReceive(file_name);
                  //file_length=null;
                  file_wait=false;
               }
               
               msg=datain.readUTF();
               if(msg==null||msg.equals(""))return;
               
               if(msg.charAt(0)==('n')){   //신규가입시                        
                  try {
                     accdb();
                     sql="insert into C_account values(?,?,?,?,?,?,?,?,?,?,?)";
                     pstinit();
                     pst.setString(1,msg.split(",")[1]);
                     pst.setString(2,msg.split(",")[2]);
                     pst.setString(3,msg.split(",")[3]);
                     pst.setString(4,msg.split(",")[4]);
                     pst.setString(5,msg.split(",")[5]);
                     pst.setInt(6,Integer.parseInt(msg.split(",")[6]));
                     pst.setInt(7,Integer.parseInt(msg.split(",")[7]));
                     pst.setString(8,msg.split(",")[8]);
                     pst.setInt(9,user_count+fake_count+1);
                     pst.setString(10,msg.split(",")[10]);
                     
                     pst.setString(11,DateFormat.getDateInstance().format(new Date()));      //서버에서 파일저장시 
                     update();
                     
                     display_user();
                     file_name=msg.split(",")[10];
                     file_wait=true;
                     dataout.writeUTF("n");
                     System.out.println("dddd");
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
                              account_tel=rs.getString("tel");
                              account_memo=rs.getString("memo");
                              account_rankno=rs.getString("rank_no");
                              account_image=rs.getString("image");
                              account_log=rs.getString("log");
                              dataout.writeUTF("l");
                              renewal();
                              client.dataout.writeUTF("lu"+account_id+","+account_name+","+account_nickname+","+account_gen+","+account_age+","+account_tel+","+account_memo+","+account_rankno+","+account_image);
                              dataout.writeUTF("lv"+nowpage);
                           }
                        }
                     }
                  } catch (Exception e) {
                     System.out.println("login fail: "+e);            //로그인실패시
                  }finally{
                     closedb();
                  }
                  if(loginstate==false){                     //l메시지를 받고 로그인실패상태로 접속시 서버상 로그인후행동접근 불가 
                     dataout.writeUTF("f");
                     continue;
                  }
                                                      //로그인후 행동에 대해서
                  
                  if(msg.charAt(1)==('c')){                  //페이크프로필접근
//                     sql = "select a.gen, a.name, a.age, a.memo from c_fakeprofile a inner join c_account b on a.ACCOUNT_ID = b.ID";
                     
                     if (msg.charAt(2)=='c'){               //페이크프로필생성
                       accdb();
                        sql = "insert into C_fakeprofile values(?,?,?,?,?,?,?)";
                        pstinit();
                        System.out.println(msg.split(",")[2]);
                        pst.setString(1, account_id+"_"+msg.split(",")[2]+"_"+msg.split(",")[1]);
                        pst.setString(2, msg.split(",")[2]);
                        pst.setInt(3, Integer.valueOf(msg.split(",")[3]));
                        pst.setString(4, msg.split(",")[4]);
                        pst.setString(5, msg.split(",")[5]);
                        pst.setString(6, account_id);
                        pst.setInt(7, user_count+fake_count+1);
                        System.out.println("완1111");
                        update();
                        System.out.println("완료료료료료");                        
                        display_fake();
                        dataout.writeUTF("lci");
                        
                        
                     }else if(msg.charAt(2)=='u'){            //페이크프로필업데이트
                        accdb();
                         sql = "update C_fakeprofile set memo=?, id_name_filepath=? where name=?";
                         pstinit();
                         pst.setString(1, msg.split(",")[1]);
                         pst.setString(2, account_id+"_"+msg.split(",")[2]+"_"+msg.split(",")[1]);
                         pst.setString(3, msg.split(",")[3]);
                         update();
                         
                     }else if(msg.charAt(2)=='d'){            //페이크프로필 삭제
                        accdb();
                         sql = "delete from C_fakeprofile where name=?";
                         pstinit();
                         pst.setString(1, msg.split(",")[1]);
                         update();
                         display_fake();
                     }else if(msg.charAt(2)=='r'){            //페이크프로필 갱신
                        Iterator<String> iter=fake_list.keySet().iterator();
                        while(iter.hasNext()){
                          
                           String imsi[]=fake_list.get(iter.next());            //fakelist에 있는 fakeprofile들의 정보 가져오기
                           
                           if(account_id.equals(imsi[0].split("_")[0])){         //각각의 list에서 clientid와 값을 비교하여 정보보내주기
                              System.out.println(imsi[0].split("_")[0]);
                              dataout.writeUTF("lcr,"+imsi[5]+","+imsi[2]+","+imsi[4]+","+imsi[3]);      // 5 2 4 3       
                           }
                        }
                     }else if(msg.charAt(2)=='t'){            //페이크프로필 테이블클릭시 정보 호출
                        Iterator<String> iter=fake_list.keySet().iterator();
                        while(iter.hasNext()){
                           System.out.println(msg.split(",")[1]);
                           String imsi[]=fake_list.get(iter.next());            //fakelist에 있는 fakeprofile들의 정보 가져오기
                           if(msg.split(",")[1].equals(imsi[2])){         
                              System.out.println(imsi[0].split("_")[0]);
                              dataout.writeUTF("lct,"+imsi[1]+","+imsi[2]+","+imsi[4]+","+imsi[5]+","+imsi[3]+","+imsi[6]);      // 5 2 4 3
                              
                           }
                        }
                         
                      }
                  }else if(msg.charAt(1)==('v')){               //사진순위 변경시
                     if(msg.charAt(2)==('c')){
                        String[] imsi;
                        imsi=all_list.get(nowpage);
                        all_list.put(nowpage,all_list.get(nowpage-1));
                        all_list.put(nowpage-1,imsi);
                        Iterator<String> iter=list.keySet().iterator(); 
                        while(iter.hasNext()){
                           list.get(iter.next()).fileSendArea();        //네트워크에있는 클라이언트들이 보고있는 페이지에 해당하는 순위 그림파일들을 보내준다
                        }
                        fileSendArea();
                        
                     }else if(msg.charAt(2)==('f')){                     //순위찾기
                        int findpage=Integer.parseInt(msg.substring(3));
                        if(findpage<2){
                           nowpage=2;
                        }else if(findpage>maxpage){
                           nowpage=maxpage;
                        }else{
                           nowpage=findpage;
                        }
                        fileSendArea();
                        
                     }else if(msg.charAt(2)==('p')){                     //전순위로 이동
                        if(2<nowpage&&nowpage<maxpage){
                           nowpage++;
                        }
                        fileSendArea();
                              
                        
                     }else if(msg.charAt(2)==('n')){                     //다음순위로 이동
                        if(2<nowpage&&nowpage<maxpage){
                           nowpage--;
                        }
                        fileSendArea();
                        
                        
                     }
                  }else if(msg.charAt(1)==('t')||msg.charAt(1)==('f')){               //채팅접근시
                     if(msg.charAt(2)==('/')){
                        if(msg.charAt(3)==('r')){            //귓속말시
                           Iterator iter=list.keySet().iterator();
                           while(iter.hasNext()){
                              Client client =list.get(iter.next());
                              if(client.account_nickname.equals(msg.split(" ")[1])){
                                 messageSend(client, msg);
                              }
                           }
                        }/*else if(msg.charAt(3)==('s')){         //쪽지보낼시
                           
                        }*/
                     }else{
                        messageSendAll();                  //일반채팅할시
                     }
                  }else if(msg.charAt(1)==('p')){               //사진정보 접근시
                     if(msg.charAt(2)==('n')){               //사진생성시
                        
                     }else if(msg.charAt(2)==('u')){            //사진수정시
                        
                     }else if(msg.charAt(2)==('d')){            //사진삭제시
                        
                     }
                  }else if(msg.charAt(1)==('u')){               //프로필정보 접근시
                     accdb();
                           sql = "update c_account set name=?, pwd=?, nickname=?, tel=?, memo=?, image=? where id=?";
                           pstinit();
                           pst.setString(1, msg.split(",")[1]);
                           pst.setString(2, msg.split(",")[2]);
                           pst.setString(3, msg.split(",")[3]);
                           pst.setString(4, msg.split(",")[4]);
                           pst.setString(5, msg.split(",")[5]);
                           pst.setString(6, msg.split(",")[6]);
                           pst.setString(7, account_id);
                           update();
                     
                  }else if(msg.charAt(1)==('o')){ //로그아웃시
                     loginstate=false;
                     account_id=null;
                     account_nickname=null;
                     account_name=null;
                     account_gen=null;
                     account_age=null;
                     account_tel=null;
                     account_memo=null;
                     account_rankno=null;
                     account_image=null;
                     account_log=null;
                     renewal();
                  }else if(msg.charAt(1)==('s')){               
                   
                  }else if(msg.charAt(1)==('r')){                  //랭킹뷰 요청받을시
                     
                    if(msg.charAt(2)==('s')){               
                         
                          Iterator<Integer> iter=all_list.keySet().iterator();
                         while(iter.hasNext()){
                            String[] imsi= all_list.get(iter.next());
                            System.out.println(imsi[2].matches((".*"+msg.split(",")[1])));
                            System.out.println(imsi[2].matches(msg.split(",")[1]+".*"));
                            if(imsi[2].matches((".*"+msg.split(",")[1]))||imsi[2].matches(msg.split(",")[1]+".*")){
                               dataout.writeUTF("lrs,"+imsi[2]+","+imsi[5]+","+imsi[4]+","+imsi[1]);
                            }  
                         }
                        
                      }else if(msg.charAt(2)==('t')){
                         Iterator<Integer> iter=all_list.keySet().iterator();
                         while(iter.hasNext()){
                         String[] imsi= all_list.get(iter.next());
                         if(imsi[2].equals(msg.split(",")[1])){
                            dataout.writeUTF("lrt,"+imsi[3]);
                         }
                         }
                         
                      }else{
                        Iterator<Integer> iter=all_list.keySet().iterator();
                        while(iter.hasNext()){
                        String[] imsi= all_list.get(iter.next());
                        dataout.writeUTF("lrs,"+imsi[2]+","+imsi[5]+","+imsi[4]+","+imsi[1]);
                        }
                      }
                    
                  }
                  
               }else if(msg.charAt(0)==('x')){//종료시
                  loginstate=false;
                  datain.close();
                  dataout.close();
                  socket.close();
                  renewal();
                  list.remove(ip);
                  
                  
                  System.out.println("사용자가 종료하였습니다");
               }
               
            } catch (Exception e) {
            }
            
         }
         
      }
      public void messageSend(Client client,String msg){
         try {
            client.dataout.writeUTF("lt"+(msg.charAt(1)=='f'?"익명":account_nickname)+" > " +msg.substring(2));
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
      public void fileSend(String filePath) {
         try {
            
            File f = new File(filePath);
            FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);

            byte[] buffer = new byte[(int)f.length()];// 파일 서버로 전송
            int len;
            while ((len = bis.read(buffer,0,buffer.length)) > 0) {
               dataout.write(buffer, 0, len);
               dataout.flush();
            }
            dataout.write(1);
            dataout.flush();
            f=null;
            fis.close();
            bis.close();
         } catch (Exception e) {
            System.out.println("file error" + e);
         }
      }
      public void fileSend(String filePath,int filenum) {
         try {
            File f = new File(basic_file_path+filePath);
            dataout.writeUTF("lvc"+filenum+"."+filePath.split("[.]")[1]+","+f.length());
            System.out.println(filenum+filePath);
           
           
            
            FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);

            byte[] buffer = new byte[128];// 파일 서버로 전송
            int len=0;
            while ((len = bis.read(buffer)) !=-1) {
               dataout.write(buffer, 0, len);
               dataout.flush();
               
            }
            dataout.write(1);
            dataout.flush();
            bis.close();
            fis.close();
            f=null;
         } catch (Exception e) {
            System.out.println("file error" + e);
         }
      }
      
      public void fileSendArea(){
            
            try {
               dataout.writeUTF("lv"+nowpage);
               if(nowpage<maxpage){
                    fileSend(all_list.get(nowpage)[6],1);
                    datain.readUTF();
                 }                                                //클라이언트에게 보여지는 뷰에 파일보내주기
                 fileSend(all_list.get(nowpage-1)[6],2);
                 datain.readUTF();
                 fileSend(all_list.get(nowpage-2)[6],3);
                 datain.readUTF();
                 if(nowpage>2){
                    fileSend(all_list.get(nowpage-3)[6],4);
                    datain.readUTF();
                 }
                 try {
                    dataout.writeUTF("lve");
                 } catch (Exception e) {
                    System.out.println("파일송신종료 에러"+e);
                 }
         } catch (Exception e) {
            // TODO: handle exception
         }
            
      }
      public void fileReceive(String filename){
         try {   
            file_path=basic_file_path+filename;         //기본파일경로_id_filename
            System.out.println(file_path);
            File f = new File(file_path);
               FileOutputStream fos = new FileOutputStream(f);
               BufferedOutputStream bos = new BufferedOutputStream(fos);
               //System.out.println(filename + "파일을 생성하였습니다.");
               int len;
               byte[] data = new byte[4096];
               while ((len = datain.read(data)) != 1) {
                   bos.write(data, 0, len);
                   bos.flush();
               }
               f = null;
               fos.close();
              bos.close();              
         } catch (Exception e) {
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
            System.out.println("dbinit err: "+e);
         }
      }
      private void update() { //db연결시 
         try {
            
            pst.executeUpdate();
            
         } catch (Exception e) {
            System.out.println("update err: "+e);
         }
      }
      private void closedb(){
         try {
            if(rs!=null)rs.close();
            if(pst!=null)pst.close();
            if(con!=null)con.close();
         } catch (Exception e) {
            System.out.println("close err: "+e);
         }
      }
      
      private synchronized void renewal(){                     //로그인 로그아웃시에 접속자갱신하게하는 메소드
         iter=list.keySet().iterator();
         model2.setNumRows(0);
         while(iter.hasNext()){
            Client client=list.get(iter.next());
            String imsi[]={client.account_id};
            if(client.loginstate==true){
               try {
                  client.dataout.writeUTF("lac");
                  Iterator<String> iter2=list.keySet().iterator();
                  while(iter2.hasNext()){
                     Client client2=list.get(iter2.next());
                     if(client2.loginstate==true){
                        client.dataout.writeUTF("la,"+client2.account_gen+","+client2.account_nickname+","+client2.account_age+","+client2.account_rankno);
                     }
                  }
               } catch (Exception e) {
                  System.out.println("renewal err "+e);
               }
               model2.addRow(imsi);
            }
         }
      }
      
   }
   public static void main(String[] args){
      Mask_Manager manager = new Mask_Manager();
      
      JFrame frame = new JFrame("관리자");
      frame.getContentPane().add(manager);
      frame.setBounds(440,110,915,670);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}