package ex;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Aaa extends JFrame {

   private JPanel contentPane;
   private JTextField textField;
   private JTextField textField_1;
   private JTextField textField_2;
   private JTextField textField_3;
   private JTextField textField_4;
   private JTextField textField_5;
   private JTextField textField_6;
   private JTextField textField_8;
   private JTextField textField_7;
   private JTextField textField_9;
   private JTextField textField_10;
   private JTextField textField_11;
   private JTextField textField_12;
   private JTextField textField_13;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               
               
              Aaa frame = new Aaa();
               
               
               frame.setVisible(true);
               
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public Aaa() {
      
      setBounds(0, 0, 910, 511);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(377, 10, 167, 406);
      contentPane.add(scrollPane);
      
      JButton btnNewButton = new JButton("New button");
      btnNewButton.setBounds(377, 425, 78, 37);
      contentPane.add(btnNewButton);
      
      JButton btnNewButton_1 = new JButton("New button");
      btnNewButton_1.setBounds(467, 426, 78, 36);
      contentPane.add(btnNewButton_1);
      
      JPanel panel = new JPanel();
      panel.setBounds(0, 10, 365, 462);
      contentPane.add(panel);
      panel.setLayout(null);
      
      JButton btnNewButton_2 = new JButton("\uC218\uC815\uD655\uC778");
      btnNewButton_2.setBounds(114, 419, 97, 23);
      panel.add(btnNewButton_2);
      
      JLabel lblName = new JLabel("NAME");
      lblName.setBounds(34, 13, 57, 15);
      panel.add(lblName);
      
      JLabel lblNewLabel = new JLabel("PWD");
      lblNewLabel.setBounds(34, 53, 57, 15);
      panel.add(lblNewLabel);
      
      JLabel lblNewLabel_1 = new JLabel("PWD OK");
      lblNewLabel_1.setBounds(34, 84, 57, 15);
      panel.add(lblNewLabel_1);
      
      JLabel lblNewLabel_2 = new JLabel("NICK");
      lblNewLabel_2.setBounds(34, 115, 57, 15);
      panel.add(lblNewLabel_2);
      
      JLabel lblNewLabel_3 = new JLabel("GEN");
      lblNewLabel_3.setBounds(34, 146, 57, 15);
      panel.add(lblNewLabel_3);
      
      JLabel lblNewLabel_4 = new JLabel("AGE");
      lblNewLabel_4.setBounds(34, 180, 57, 15);
      panel.add(lblNewLabel_4);
      
      JLabel lblNewLabel_5 = new JLabel("TEL");
      lblNewLabel_5.setBounds(34, 214, 57, 15);
      panel.add(lblNewLabel_5);
      
      JLabel lblNewLabel_6 = new JLabel("MEMO");
      lblNewLabel_6.setBounds(34, 242, 57, 15);
      panel.add(lblNewLabel_6);
      
      JLabel lblNewLabel_7 = new JLabel("IMAGE");
      lblNewLabel_7.setBounds(34, 372, 57, 15);
      panel.add(lblNewLabel_7);
      
      textField = new JTextField();
      textField.setBounds(103, 10, 127, 21);
      panel.add(textField);
      textField.setColumns(10);
      
      textField_1 = new JTextField();
      textField_1.setBounds(103, 50, 127, 21);
      panel.add(textField_1);
      textField_1.setColumns(10);
      
      textField_2 = new JTextField();
      textField_2.setBounds(103, 81, 127, 21);
      panel.add(textField_2);
      textField_2.setColumns(10);
      
      textField_3 = new JTextField();
      textField_3.setBounds(103, 112, 127, 21);
      panel.add(textField_3);
      textField_3.setColumns(10);
      
      textField_4 = new JTextField();
      textField_4.setBounds(103, 143, 43, 21);
      panel.add(textField_4);
      textField_4.setColumns(10);
      
      textField_5 = new JTextField();
      textField_5.setBounds(103, 180, 43, 21);
      panel.add(textField_5);
      textField_5.setColumns(10);
      
      textField_6 = new JTextField();
      textField_6.setBounds(103, 211, 127, 21);
      panel.add(textField_6);
      textField_6.setColumns(10);
      
      textField_8 = new JTextField();
      textField_8.setBounds(103, 369, 127, 21);
      panel.add(textField_8);
      textField_8.setColumns(10);
      
      JButton btnNewButton_4 = new JButton("PWD OK");
      btnNewButton_4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
         }
      });
      btnNewButton_4.setBounds(242, 80, 84, 23);
      panel.add(btnNewButton_4);
      
      JScrollPane scrollPane_1 = new JScrollPane();
      scrollPane_1.setBounds(103, 242, 127, 117);
      panel.add(scrollPane_1);
      
      JPanel panel_2 = new JPanel();
      panel_2.setBounds(242, 279, 99, 78);
      panel.add(panel_2);
      
      JButton btnNewButton_5 = new JButton("FILE");
      btnNewButton_5.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
      btnNewButton_5.setBounds(244, 368, 65, 23);
      panel.add(btnNewButton_5);
      
      textField_11 = new JTextField();
      textField_11.setBounds(298, 11, 43, 19);
      panel.add(textField_11);
      textField_11.setColumns(10);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBounds(556, 10, 326, 452);
      contentPane.add(panel_1);
      panel_1.setLayout(null);
      
      JButton btnNewButton_3 = new JButton("New button");
      btnNewButton_3.setBounds(123, 419, 97, 23);
      panel_1.add(btnNewButton_3);
      
      JLabel lblNewLabel_8 = new JLabel("New label");
      lblNewLabel_8.setBounds(30, 29, 57, 15);
      panel_1.add(lblNewLabel_8);
      
      textField_7 = new JTextField();
      textField_7.setBounds(99, 26, 116, 21);
      panel_1.add(textField_7);
      textField_7.setColumns(10);
      
      textField_9 = new JTextField();
      textField_9.setBounds(99, 71, 116, 21);
      panel_1.add(textField_9);
      textField_9.setColumns(10);
      
      JLabel lblNewLabel_9 = new JLabel("New label");
      lblNewLabel_9.setBounds(30, 74, 57, 15);
      panel_1.add(lblNewLabel_9);
      
      textField_10 = new JTextField();
      textField_10.setBounds(99, 118, 57, 21);
      panel_1.add(textField_10);
      textField_10.setColumns(10);
      
      JLabel lblNewLabel_10 = new JLabel("New label");
      lblNewLabel_10.setBounds(30, 121, 57, 15);
      panel_1.add(lblNewLabel_10);
      
      JLabel lblNewLabel_11 = new JLabel("New label");
      lblNewLabel_11.setBounds(30, 165, 57, 15);
      panel_1.add(lblNewLabel_11);
      
      JScrollPane scrollPane_2 = new JScrollPane();
      scrollPane_2.setBounds(99, 165, 116, 86);
      panel_1.add(scrollPane_2);
      
      textField_12 = new JTextField();
      textField_12.setBounds(269, 10, 45, 23);
      panel_1.add(textField_12);
      textField_12.setColumns(10);
      
      JLabel lblNewLabel_12 = new JLabel("New label");
      lblNewLabel_12.setBounds(30, 276, 57, 15);
      panel_1.add(lblNewLabel_12);
      
      textField_13 = new JTextField();
      textField_13.setBounds(99, 273, 116, 21);
      panel_1.add(textField_13);
      textField_13.setColumns(10);
      
      JButton btnNewButton_6 = new JButton("New button");
      btnNewButton_6.setBounds(227, 272, 64, 19);
      panel_1.add(btnNewButton_6);
      
      JPanel panel_3 = new JPanel();
      panel_3.setBounds(99, 304, 116, 98);
      panel_1.add(panel_3);
   
   }
}