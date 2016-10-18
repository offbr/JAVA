package myapi;
/**
 * my api doc test
 * make by 2016. 6. 14 <br>
 * @author BB
 * @version 1.0
 */
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.*;

public class MyFrame6 extends Frame{

	public MyFrame6() {
		super("nice");
		setBounds(200, 200, 400, 300);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			/**
			 * windowClosing override method
			 * @param e event argument
			 * not return 
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = (int)(Math.random()* 255);
				int g = (int)(Math.random()* 255);
				int b = (int)(Math.random()* 255);
				System.out.println(r + " " + g + " " + b);
				setBackground(new Color(r, g, b));
			}
		});
		
	}
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		new MyFrame6();

	}

}
