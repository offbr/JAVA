package designcomponent;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BTextField extends JTextField implements Cinfo{

	
	public BTextField(int col) {
		super(col);
		setBackground(BUTTON_COLOR);
		setBorder(new LineBorder(HIGHLIGHT_COLOR));
		setForeground(TEXT_COLOR);
		setFont(new Font("SanSerif", Font.BOLD, 12));
		
		addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				// TODO Auto-generated method stub
				
				if(arg0.getPropertyName().equals("enabled")){
					
					if((boolean)arg0.getNewValue()==false){
						setBackground(FRAME_COLOR);
					}else {
						setBackground(BUTTON_COLOR);
					}
					
				}else if(arg0.getPropertyName().equals("editable")){
					
					if((boolean)arg0.getNewValue()==false){
						
						setBackground(FRAME_COLOR);
					}else {
						setBackground(BUTTON_COLOR);
					}
					
					
				}
			}
		});// TODO Auto-generated constructor stub
		
	}

	public BTextField() {
	
		setBackground(BUTTON_COLOR);
		setBorder(new LineBorder(HIGHLIGHT_COLOR));
		setForeground(TEXT_COLOR);
		setFont(new Font("SanSerif", Font.BOLD, 12));
		
		addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				// TODO Auto-generated method stub
				
				if(arg0.getPropertyName().equals("enabled")){
					
					if((boolean)arg0.getNewValue()==false){
						setBackground(FRAME_COLOR);
					}else {
						setBackground(BUTTON_COLOR);
					}
					
				}else if(arg0.getPropertyName().equals("editable")){
					
					if((boolean)arg0.getNewValue()==false){
						
						setBackground(FRAME_COLOR);
					}else {
						setBackground(BUTTON_COLOR);
					}
					
					
				}
			}
		});// TODO Auto-generated constructor stub
		
	}
	public BTextField(String name,int col) {
		super(name,col);
		setBackground(BUTTON_COLOR);
		setBorder(new LineBorder(HIGHLIGHT_COLOR));
		setForeground(TEXT_COLOR);
		setFont(new Font("SanSerif", Font.BOLD, 12));
		setDisabledTextColor(TEXT_COLOR);
		addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				// TODO Auto-generated method stub
				
				if(arg0.getPropertyName().equals("enabled")){
					
					if((boolean)arg0.getNewValue()==false){
						
						setBackground(FRAME_COLOR);
					}else {
						setBackground(BUTTON_COLOR);
					}
					
					
				}else if(arg0.getPropertyName().equals("editable")){
					
					if((boolean)arg0.getNewValue()==false){
						
						setBackground(FRAME_COLOR);
					}else {
						setBackground(BUTTON_COLOR);
					}
					
					
				}
			}
		});
		// TODO Auto-generated constructor stub
		
	}
	
}
