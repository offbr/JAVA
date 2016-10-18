package pack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

//import javax.sound.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JFrame;

public class PackMan extends JFrame implements KeyListener{
	Image image;
	Image food;
	int x = 0, y = 0, sel = 1;
	
	int xx = (int)(Math.random()*280); 
	int yy = (int)(Math.random()*280);
	int point = 0;
	
	public PackMan() {
		super("PackMan");
		setIconImage(Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack1.jpg")); //이미지프레임에 담는다
		
		setLayout(null);
		setBounds(200, 200, 300, 300);
		setResizable(false);
		setBackground(Color.white);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		x = (int)getWidth() /2; //프레임 너비의 반
		y = (int)getHeight() /2; 
		
		addKeyListener(this);
	
	}
	
	public void bgm(){
		File bgm;
		AudioInputStream stream;
		AudioFormat format;
		
		DataLine.Info info;
		Clip clip;

		bgm = new File("c://work/sori/beginning.wav");
		try {
			stream = AudioSystem.getAudioInputStream(bgm);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip)AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
		} catch (Exception e) {
			System.out.println("bgm err: " + e);
		}
	}
	
	public void eat(){
		File eat;
		AudioInputStream stream;
		AudioFormat format;
		
		DataLine.Info info;
		Clip clip;

		eat = new File("c://work/sori/eat.wav");
		try {
			stream = AudioSystem.getAudioInputStream(eat);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip)AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
		} catch (Exception e) {
			System.out.println("eat err: " + e);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		switch (sel) {
		case 1:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack1.jpg");
			break;
		case 2:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack2.jpg");
			break;
		case 3:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack3.jpg");
			break;
		case 4:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack4.jpg");
			break;
		case 5:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack5.jpg");
			break;
		case 6:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack6.jpg");
			break;
		case 7:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack7.jpg");
			break;
		case 8:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack8.jpg");
			break;
		}
		food = Toolkit.getDefaultToolkit().getImage("c:/work/pack/food.png");
		g.clearRect(0, 0, getWidth(), getHeight()); //잔상제거 기능 //프레임의 너비 높이
		//g.drawImage(image, x, y, this);
		g.drawImage(image, 
				x - image.getWidth(this) / 2, y - image.getHeight(this)/2, this);
//		g.drawImage(food, xx-food.getWidth(this) / 2, yy-food.getHeight(this) / 2, this);
		g.drawImage(food, xx, yy, this);
		
//		if(x + 15 >  xx && x - 15 <  xx   && y + 15 >  yy && y - 15 <  yy){
		if((Math.abs(x-xx) <= image.getWidth(this)/2) && (Math.abs(y-yy) <= image.getHeight(this)/2)){
			point++;
			xx = (int)(Math.random()*230 )+50; 
			yy = (int)(Math.random()*230 )+50;
			/*
			xx = (int)(Math.random()*(getWidth() - 10) / 10) * 10;
			yy = (int)(Math.random()*(getHeight() - 10) / 10) * 10; 
			*/ 
			setTitle("point : " + point);
			eat();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//System.out.println(key);

		//오른쪽 화살표
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_NUMPAD6){
			//System.out.println("right");
			//if(sel == 1) sel = 2; else sel = 1;
			sel = (sel == 1)?2:1; //삼항 연산자
			x = (x < getWidth())?x+=10:-image.getWidth(this);
			System.out.println(x);
		}
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_NUMPAD4){
			sel = (sel == 3)?4:3; //삼항 연산자
			//x = (x < getWidth())?x-=10:-image.getWidth(this);
			//if(x == -60) x += image.getHeight(this)+300;
			x = (x > 0)?x-=10:getWidth() + image.getWidth(this);
			System.out.println(x);
		}
		if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_NUMPAD8){
			sel = (sel == 5)?6:5; //삼항 연산자
			y = (y < getHeight())?y+=10:-image.getHeight(this);
		}
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_NUMPAD2){
			sel = (sel == 7)?8:7; //삼항 연산자
			//y = (y < getHeight())?y-=10:image.getHeight(this);
			//if(y == -60)  y += image.getHeight(this)+300;
			y = (y > 0)?y-=10:getHeight() + image.getHeight(this);
			System.out.println(y);
		}
		repaint();
		//System.out.println("x: " + x + ",y: " + y + ",xx: " + xx+ ",yy: " + yy);
	}
	
	@Override
	public void keyReleased(KeyEvent e){
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}

	public static void main(String[] args) {
		PackMan pack = new PackMan();
		while(true){
			try {
				pack.bgm();
				Thread.sleep(3000);		
			} catch (Exception e) {
			}
		}
	}

}

