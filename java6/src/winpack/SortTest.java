package winpack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SortTest extends JFrame {

	private JPanel contentPane;
	private JTextField txtSource;
	ArrayList list;
	int i = 0;
	int j = 0;
	int temp;
	String[] arr;
	int[] br;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SortTest frame = new SortTest();
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
	public SortTest() {
		setTitle("정렬 연습");
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list = new ArrayList<>();
		
		JLabel lblNewLabel = new JLabel("정렬 알고리즘 (숫자는 10 개 이하 입력)");
		lblNewLabel.setBounds(67, 10, 235, 15);
		contentPane.add(lblNewLabel);
		
		txtSource = new JTextField();
		txtSource.setToolTipText("숫자만");
		txtSource.setBounds(67, 52, 116, 21);
		contentPane.add(txtSource);
		txtSource.setColumns(10);
		
		JLabel label = new JLabel("대상 :");
		label.setBounds(29, 55, 44, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("결과 :");
		label_1.setBounds(29, 106, 57, 15);
		contentPane.add(label_1);
		
		JTextArea txtTarget = new JTextArea();
		txtTarget.setEditable(false);
		txtTarget.setBounds(67, 102, 116, 19);
		contentPane.add(txtTarget);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uC791\uC5C5 \uC120\uD0DD", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(44, 186, 327, 47);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSelection = new JButton("Selection");
		btnSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arr = txtSource.getText().split("");
				//System.out.println(arr[2]);
				//System.out.println(arr.length);
				br = new int[arr.length];
				for (int i = 0; i < arr.length; i++) {
					br[i] = Integer.parseInt(arr[i]);
					System.out.println(br[i]);
				}
				System.out.println(br.length);
				for (i = 0; i < br.length; i++) {
					for (j = i + 1; j < br.length; j++) {
						if(br[i] > br[j]){
							temp = br[i];
							br[i] = br[j];
							br[j] = temp;
						}
					}
				}
				System.out.println(br[0]+" " + br[1]);

				//셀렉션 소트
				for (int i = 0; i < br.length; i++) {
					txtTarget.append(String.valueOf(br[i]));
					System.out.println(String.valueOf(br[i]));
					
				}		
			}
		});
		btnSelection.setBounds(6, 17, 97, 23);
		panel.add(btnSelection);
		
		JButton btnButton = new JButton("Bubble");
		btnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//버블 소트
			}
		});
		btnButton.setBounds(115, 17, 97, 23);
		panel.add(btnButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//초기화 작어
			}
		});
		btnClear.setBounds(224, 17, 97, 23);
		panel.add(btnClear);
	}
}
