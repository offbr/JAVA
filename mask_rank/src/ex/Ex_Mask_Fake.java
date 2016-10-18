package ex;

import javax.swing.JPanel;
import java.awt.Color;
import designcomponent.*;

public class Ex_Mask_Fake extends JPanel {
	private BTextField textField;
	private BTextField textField_1;
	private BTextField textField_2;

	/**
	 * Create the panel.
	 */
	public Ex_Mask_Fake() {
		setBackground(Color.BLACK);
		setLayout(null);
		
		BPanel panel = new BPanel();
		panel.setBounds(12, 10, 300, 400);
		add(panel);
		
		BLabel label = new BLabel("닉네임");
		label.setForeground(Color.WHITE);
		label.setBounds(484, 25, 57, 15);
		add(label);
		
		textField = new BTextField();
		textField.setBounds(529, 22, 116, 21);
		add(textField);
		textField.setColumns(10);
		
		BLabel label_1 = new BLabel("나이");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(484, 73, 57, 15);
		add(label_1);
		
		textField_1 = new BTextField();
		textField_1.setBounds(529, 70, 116, 21);
		add(textField_1);
		textField_1.setColumns(10);
		
		BLabel label_2 = new BLabel("성별");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(484, 122, 57, 15);
		add(label_2);
		
		textField_2 = new BTextField();
		textField_2.setBounds(529, 119, 116, 21);
		add(textField_2);
		textField_2.setColumns(10);

	}
}
