package designcomponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.plaf.metal.MetalTabbedPaneUI;

public class BTabbedPane extends JTabbedPane implements Cinfo{

	
	
	public BTabbedPane() {
		// TODO Auto-generated constructor stub
		setBackground(BUTTON_COLOR);
		setForeground(TEXT_COLOR);
		setFocusable(false);
		
		
		
		UIManager.put("TabbedPane.selected", new ColorUIResource(FRAME_COLOR));	
		SwingUtilities.updateComponentTreeUI(this);
		
		addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				getComponentAt(getSelectedIndex()).setBackground(FRAME_COLOR);	
			}
		});
		
		
		
	}
	
	
	
	
}
