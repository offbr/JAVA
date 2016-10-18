package designcomponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.border.Border;



public class BTextArea extends JTextArea  implements Cinfo{   
   public BTextArea() {
      setBackground(PANEL_COLOR);
      setForeground(TEXT_COLOR);
      setFont(new Font("����", Font.BOLD, 12));
      setDisabledTextColor(FRAME_COLOR);
      Border linebor = BorderFactory.createLineBorder(HIGHLIGHT_COLOR,3);
      Border linebor2 = BorderFactory.createEmptyBorder(7,7,7,7);
      addPropertyChangeListener(new PropertyChangeListener() {
      
         @Override
         public void propertyChange(PropertyChangeEvent evt) {
            if(evt.getPropertyName().equals("enabled")){
               if ((boolean)evt.getNewValue()==false) {
                  setBackground(FRAME_COLOR);
                  setBorder(BorderFactory.createCompoundBorder(linebor, linebor2));
               }else{
                  setBackground(PANEL_COLOR);
               }
            }
            
         }
      });
   }
   public BTextArea(int a , int b) {
      super(a, b);
      setBackground(TEXT_COLOR);
      setForeground(PANEL_COLOR);
      setFont(new Font("����", Font.BOLD, 12));
      setDisabledTextColor(FRAME_COLOR);
      Border linebor = BorderFactory.createLineBorder(HIGHLIGHT_COLOR,3);
      Border linebor2 = BorderFactory.createEmptyBorder(7,7,7,7);
      addPropertyChangeListener(new PropertyChangeListener() {
      
         @Override
         public void propertyChange(PropertyChangeEvent evt) {
            if(evt.getPropertyName().equals("enabled")){
               if ((boolean)evt.getNewValue()==false) {
                  setBackground(FRAME_COLOR);
                  setBorder(BorderFactory.createCompoundBorder(linebor, linebor2));
               }else{
                  setBackground(PANEL_COLOR);
               }
            }
            
         }
      });
      
   }}