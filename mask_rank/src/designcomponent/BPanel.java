package designcomponent;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.xml.soap.Text;

public class BPanel extends JPanel implements Cinfo{
	public BPanel(String title) {
		// TODO Auto-generated constructor stub
		
		setBackground(PANEL_COLOR);
		setBorder(new TitledBorder(new MatteBorder(3,3,3,3,FRAME_COLOR),title,TitledBorder.LEFT,TitledBorder.TOP+1,
				new Font("Sanserif", Font.BOLD, 12),TEXT_COLOR));
	
		setForeground(TEXT_COLOR);
	
		
	}
	public BPanel(String title,LayoutManager layout) {
		// TODO Auto-generated constructor stub
		
		setBackground(PANEL_COLOR);
		setBorder(new TitledBorder(new MatteBorder(3,3,3,3,FRAME_COLOR),title,TitledBorder.LEFT,TitledBorder.TOP+1,
				new Font("Sanserif", Font.BOLD, 12),TEXT_COLOR));
	
		setForeground(TEXT_COLOR);
		setLayout(layout);
		
	}
	public BPanel(LayoutManager layout,String title) {
		// TODO Auto-generated constructor stub
		
		setBackground(PANEL_COLOR);
		setBorder(new TitledBorder(new MatteBorder(3,3,3,3,FRAME_COLOR),title,TitledBorder.LEFT,TitledBorder.TOP+1,
				new Font("Sanserif", Font.BOLD, 12),TEXT_COLOR));
	
		setForeground(TEXT_COLOR);
		setLayout(layout);
		
	}
	public BPanel() {
		// TODO Auto-generated constructor stub
		
		setBackground(PANEL_COLOR);
		setForeground(TEXT_COLOR);
	
		
	}
	public BPanel(LayoutManager layout) {
		// TODO Auto-generated constructor stub
		
		setBackground(PANEL_COLOR);
		setForeground(TEXT_COLOR);
		setLayout(layout);
		
	}
	
	
	

}
