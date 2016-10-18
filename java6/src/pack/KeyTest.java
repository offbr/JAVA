package pack;

import java.awt.*;
import java.awt.event.*;

public class KeyTest extends Frame {
	Point start = new Point(100, 100);
	Point end = new Point(100, 100);

	public KeyTest() {
		super("Line");
		this.addKeyListener(new Kevent());

		setBounds(100, 100, 300, 300);
		setVisible(true);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public class Kevent extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int keycode = e.getKeyCode();
			int d; // 이동량
			if ((e.getModifiers() & InputEvent.SHIFT_MASK) != 0)
				d = 10;
			else
				d = 1;

			if (keycode == KeyEvent.VK_LEFT)
				add(-d, 0);
			else if (keycode == KeyEvent.VK_RIGHT)
				add(d, 0);
			else if (keycode == KeyEvent.VK_UP)
				add(0, -d);
			else if (keycode == KeyEvent.VK_DOWN)
				add(0, d);
		}
	}// 내부 클래스 끝

	public void add(int dx, int dy) {
		end.x += dx;
		end.y += dy;

		repaint();
	}

	public void update(Graphics g) { // 없으면 점만 움직임
		paint(g);
	}

	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.drawLine(start.x, start.y, end.x, end.y);
		start.x = end.x;
		start.y = end.y;
	}

	public static void main(String[] args) {
		new KeyTest();
	}
}