package ex;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;
import designcomponent.*;

public class Mask_Versus extends BPanel implements ActionListener{
   BTextField tf_Find;
   BPanel image1;
   BPanel image4;
   BButton btn_Previous;
   BButton btn_Next;
   BButton image2;
   BButton image3;
   BButton btn_Find;
   BLabel LeftNick;
   BLabel RightNick;
   BLabel LeftMemo;
   BLabel RightMemo;
   BLabel lblNewLabel_4;
   BButton btn_exit;

   public Mask_Versus() {
      layInit();
   }
   
   private void layInit(){
      setLayout(null);

      image1 = new BPanel();
      image1.setBounds(-95, 163, 200, 200);
      add(image1);

      image4 = new BPanel();
      image4.setBounds(805, 163, 200, 200);
      add(image4);

      btn_Previous = new BButton("Previous");
      btn_Previous.setBounds(269, 433, 97, 23);
      add(btn_Previous);

      btn_Next = new BButton("Next");
      btn_Next.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
      btn_Next.setBounds(550, 433, 97, 23);
      add(btn_Next);

      image2 = new BButton("");
      image2.setOpaque(false);
      image2.setBounds(166, 163, 200, 200);
      add(image2);

      image3 = new BButton("");
      image3.setOpaque(false);
      image3.setBounds(550, 163, 200, 200);
      add(image3);

      tf_Find = new BTextField();
      tf_Find.setBounds(433, 377, 56, 23);
      add(tf_Find);
      tf_Find.setColumns(10);

      btn_Find = new BButton("Search");
      btn_Find.setBounds(419, 333, 82, 30);
      add(btn_Find);

      LeftNick = new BLabel("왼쪽닉네임");
      LeftNick.setBounds(166, 105, 200, 23);
      add(LeftNick);

      RightNick = new BLabel("오른쪽닉네임");
      RightNick.setBounds(550, 107, 200, 19);
      add(RightNick);

      LeftMemo = new BLabel("왼쪽메모");
      LeftMemo.setBounds(166, 138, 200, 15);
      add(LeftMemo);

      RightMemo = new BLabel("오른쪽메모");
      RightMemo.setBounds(550, 138, 200, 15);
      add(RightMemo);

      lblNewLabel_4 = new BLabel("Mask Versus");
      lblNewLabel_4.setFont(new Font("Sanserif", Font.BOLD, 30));
      lblNewLabel_4.setBounds(368, 22, 185, 37);
      add(lblNewLabel_4);

      btn_exit = new BButton("Exit");
      btn_exit.setBounds(780, 460, 97, 23);
      add(btn_exit);
      
      btn_Previous.addActionListener(this);
      btn_Next.addActionListener(this);
      image2.addActionListener(this);
      image3.addActionListener(this);
      btn_Find.addActionListener(this);
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == btn_Previous){				//이전이미지송신요청
    	  try {
    		  Mask_MDI.getInstance().dataout.writeUTF("lvp");
		} catch (Exception e2) {
			System.out.println("이전이미지 송신요청 err"+e2);
		}
    	 
      }else if(e.getSource() == btn_Next){				//다음이미지 송신요청
    	  try {
    		  Mask_MDI.getInstance().dataout.writeUTF("lvn");
		} catch (Exception e2) {
			System.out.println("다음이미지 송신요청 err"+e2);
		}
      }else if(e.getSource() == image2||e.getSource() == image3){	//이미지순위 교체요청
    	  try {
    		  Mask_MDI.getInstance().dataout.writeUTF("lvc");
		} catch (Exception e2) {
			System.out.println("이미지 순위교체요청 err"+e2);
		}
      }else if(e.getSource() == btn_Find){				//랭킹number찾기
    	  try {
    		  Mask_MDI.getInstance().dataout.writeUTF("lvf"+Integer.parseInt(tf_Find.getText()));
		} catch (Exception e2) {
			System.out.println("랭킹검색 요청 err"+e2);
		}
      }
   }
   
   public static void main(String[] args) {
      JFrame frame = new JFrame();
      Mask_Versus versus = new Mask_Versus();
      frame.getContentPane().add(versus);
      frame.setBounds(400, 400, 910, 600);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
   }
}
