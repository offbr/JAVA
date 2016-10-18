package designcomponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.plaf.ScrollPaneUI;
import javax.swing.plaf.ViewportUI;
import javax.swing.plaf.metal.MetalScrollBarUI;
import javax.swing.table.DefaultTableModel;



public class BScrollPane extends JScrollPane implements Cinfo{
	
	private ImageIcon image;
	
	public BScrollPane() {
		// TODO Auto-generated constructor stub
		getViewport().setBackground(FRAME_COLOR);
		getVerticalScrollBar().setUI(new MyScrollbarUI());
		
		
	}
	public BScrollPane(JTable table) {
		// TODO Auto-generated constructor stub
		super(table);
		getViewport().setBackground(FRAME_COLOR);

		getVerticalScrollBar().setUI(new MyScrollbarUI());
		
		
	}
	public BScrollPane(JTextArea textArea) {
		// TODO Auto-generated constructor stub
		super(textArea);
		getViewport().setBackground(FRAME_COLOR);
		
		getVerticalScrollBar().setUI(new MyScrollbarUI());
		
		
	}
	
	
	
	
	
	 static class MyScrollbarUI extends MetalScrollBarUI implements Cinfo{

	        private Image imageThumb, imageTrack;
	        private JButton b = new JButton() {

	            @Override
	            public Dimension getPreferredSize() {
	                return new Dimension(0, 0);
	            }

	        };

	        MyScrollbarUI() {
	            imageThumb = FauxImage.create(32, 32, BUTTON_COLOR);
	            imageTrack = FauxImage.create(32, 32, PANEL_COLOR);
	        }

	        @Override
	        protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
	            g.setColor(Color.blue);
	            ((Graphics2D) g).drawImage(imageThumb,
	                r.x, r.y, r.width, r.height, null);
	        }

	        @Override
	        protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
	            ((Graphics2D) g).drawImage(imageTrack,
	                r.x, r.y, r.width, r.height, null);
	        }

	        @Override
	        protected JButton createDecreaseButton(int orientation) {
	            return b;
	        }

	        @Override
	        protected JButton createIncreaseButton(int orientation) {
	            return b;
	        }
	    }

	    private static class FauxImage {

	        static public Image create(int w, int h, Color c) {
	            BufferedImage bi = new BufferedImage(
	                w, h, BufferedImage.TYPE_INT_ARGB);
	            Graphics2D g2d = bi.createGraphics();
	            g2d.setPaint(c);
	            g2d.fillRect(0, 0, w, h);
	            g2d.dispose();
	            return bi;
	        }
	    }
	
}
