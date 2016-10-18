package winpack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinBuild extends JFrame {

	private JPanel contentPane;
	private JTextField txtIrum;
	private JLabel lblP;
	private JTextField txtP;
	private JLabel lblG;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JMenuBar menuBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinBuild frame = new WinBuild();
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
	public WinBuild() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(WinBuild.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 471);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnuFile = new JMenu("File");
		menuBar.add(mnuFile);
		
		JMenuItem mnuExit = new JMenuItem("Exit");
		mnuExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnuFile.add(mnuExit);
		
		JMenu mnuHelp = new JMenu("Help");
		menuBar.add(mnuHelp);
		
		JMenuItem mnuAbout = new JMenuItem("About...");
		mnuHelp.add(mnuAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtIrum = new JTextField();
		txtIrum.setDropMode(DropMode.INSERT);
		txtIrum.setBounds(60, 51, 116, 21);
		contentPane.add(txtIrum);
		txtIrum.setColumns(10);
		
		JLabel lblIrum = new JLabel("이름");
		lblIrum.setHorizontalAlignment(SwingConstants.CENTER);
		lblIrum.setBounds(12, 54, 36, 15);
		contentPane.add(lblIrum);
		
		lblP = new JLabel("전화번호");
		lblP.setBounds(0, 111, 57, 15);
		contentPane.add(lblP);
		
		txtP = new JTextField();
		txtP.setBounds(60, 108, 116, 21);
		contentPane.add(txtP);
		txtP.setColumns(10);
		
		lblG = new JLabel("성별");
		lblG.setHorizontalAlignment(SwingConstants.CENTER);
		lblG.setBounds(12, 79, 36, 15);
		contentPane.add(lblG);
		
		JRadioButton btnM = new JRadioButton("남");
		buttonGroup.add(btnM);
		btnM.setBounds(60, 78, 47, 23);
		contentPane.add(btnM);
		
		JRadioButton btnW = new JRadioButton("여");
		buttonGroup.add(btnW);
		btnW.setBounds(109, 78, 67, 23);
		contentPane.add(btnW);
		
		JLabel lblTitle = new JLabel("에이콘 회원관리");
		lblTitle.setFont(new Font("바탕체", Font.BOLD, 18));
		lblTitle.setBounds(137, 10, 152, 28);
		contentPane.add(lblTitle);
	}
}
