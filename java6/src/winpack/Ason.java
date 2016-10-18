package winpack;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Ason extends JFrame {

	private JPanel contentPane;
	private JTextField numtxt;
	private JTextField nametxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup1 = new ButtonGroup();
	private JTable table;
	String etc = "출근";
	String etc1 = "정상";
	int aa = 0;//,bb = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ason frame = new Ason();
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
	
	public Ason() {
		ArrayList<AsonDto> list = new ArrayList<>();
		AsonDto dto = new AsonDto();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String columnNames[] =
			{"번호", "이름", "근퇴"};

		Object rowData[][] = {};

		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
		JTable jTable = new JTable(model);
		jTable.setEnabled(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 424, 49);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbl1 = new JLabel("관리");
		lbl1.setFont(new Font("굴림", Font.BOLD, 20));
		lbl1.setBounds(184, 0, 42, 42);
		panel.add(lbl1);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(5, 64, 424, 119);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lbl2 = new JLabel("번호 : ");
		lbl2.setBounds(12, 23, 57, 15);
		panel1.add(lbl2);
		
		JLabel lbl3 = new JLabel("이름 : ");
		lbl3.setBounds(12, 73, 57, 15);
		panel1.add(lbl3);
		
		numtxt = new JTextField();
		numtxt.setBounds(53, 20, 359, 21);
		panel1.add(numtxt);
		numtxt.setColumns(10);
		
		nametxt = new JTextField();
		nametxt.setBounds(53, 70, 359, 21);
		panel1.add(nametxt);
		nametxt.setColumns(10);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(5, 191, 424, 31);
		contentPane.add(panel2);
		panel2.setLayout(null);
		
		JRadioButton btn1 = new JRadioButton("출근");
		btn1.setSelected(true);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etc = "출근";
			}
		});
		buttonGroup.add(btn1);
		btn1.setBounds(68, 5, 61, 23);
		panel2.add(btn1);
		
		JRadioButton btn2 = new JRadioButton("퇴근");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etc = "퇴근";
			}
		});
		buttonGroup.add(btn2);
		btn2.setBounds(149, 5, 61, 23);
		panel2.add(btn2);
		
		JRadioButton btn3 = new JRadioButton("정상");
		btn3.setSelected(true);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etc1 = "정상";
			}
		});
		btn3.setBounds(240, 5, 61, 23);
		panel2.add(btn3);
		buttonGroup1.add(btn3);
		
		JRadioButton btn4 = new JRadioButton("그외");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etc1 = "그 외";
			}
		});
		btn4.setBounds(313, 5, 69, 23);
		panel2.add(btn4);
		buttonGroup1.add(btn4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 232, 424, 40);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btn11 = new JButton("등록");
		btn11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Object[] rowData = {numtxt.getText(), nametxt.getText(), btn4.getActionCommand()}; 
				//model.addRow(rowData);
				/*for (int i = 0; i <= list.size(); i++) {
					AsonDto dto1 = new AsonDto();
					dto1 = list.get(i);
					Object[] rowData = {dto1.getNum(), dto1.getName(), dto.getEtc()};
					model.addRow(rowData);
				}*/
				
				AsonDto dto = new AsonDto();
				String num = numtxt.getText();
				String name = nametxt.getText();
				String hap = etc + " " + etc1;
				dto.setNum(num);
				dto.setName(name);
				dto.setEtc(hap);
				list.add(dto);
				
				//bb++;
				//System.out.println(aa);
				
			}
		});
		btn11.setBounds(12, 10, 81, 23);
		panel_1.add(btn11);
		
		JButton btn22 = new JButton("삭제");
		btn22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeRow(0);
				list.remove(0);
				aa = 0;
			}
		});
		btn22.setBounds(105, 10, 81, 23);
		panel_1.add(btn22);
		
		JButton btn33 = new JButton("수정");
		btn33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTable.setEnabled(true);
			}
		});
		btn33.setBounds(238, 10, 81, 23);
		panel_1.add(btn33);
		
		JButton btn44 = new JButton("확인");
		btn44.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*for (AsonDto dto1 : list) {
					Object[] rowData = {dto1.getNum(), dto1.getName(), dto1.getEtc()};
					System.out.println(dto1.getEtc());
					model.addRow(rowData);
				}*/
				for (int i = 0+aa; i < list.size(); i++) {
					AsonDto dto = new AsonDto();
					dto = list.get(i);
					Object[] rowData = {dto.getNum(), dto.getName(), dto.getEtc()};
					model.addRow(rowData);
				}
				aa++;
				jTable.setEnabled(false);
				//aa=0;
			}
		});
		btn44.setBounds(331, 10, 81, 23);
		panel_1.add(btn44);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(5, 282, 424, 170);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 424, 160);
		panel_2.add(scrollPane);
		
	
		scrollPane.setViewportView(jTable);
		
		/*
		Object[] rowData1 = {"1", "이연남", "정상"};
		model.addRow(rowData1);
		*/
	}
}

class AsonDto{
	private String num, name, etc;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEtc() {
		return etc;
	}
	
	public void setEtc(String etc) {
		this.etc = etc;
	}
}
