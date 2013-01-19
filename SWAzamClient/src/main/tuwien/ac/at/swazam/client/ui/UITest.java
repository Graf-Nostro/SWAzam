package main.tuwien.ac.at.swazam.client.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class UITest extends JFrame {

	
	private final Logger logger = Logger.getLogger("UITest");
	
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UITest frame = new UITest();
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
	public UITest() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		setTitle("SWAzam Client");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[top][][][][grow]"));
		
		JPanel plRegistration = new JPanel();
		plRegistration.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(plRegistration, "cell 0 0,growx,aligny top");
		plRegistration.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		JLabel lblUsername = new JLabel("Username:");
		plRegistration.add(lblUsername, "cell 0 0,alignx trailing");
		
		username = new JTextField();
		plRegistration.add(username, "flowx,cell 1 0,alignx left");
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("password:");
		plRegistration.add(lblPassword, "cell 1 0");
		
		password = new JPasswordField();
		password.setColumns(10);
		plRegistration.add(password, "cell 1 0");
		
		JLabel lblFill = new JLabel("");
		plRegistration.add(lblFill, "cell 1 0,growx");
		
		JButton btnLogin = new JButton("login");
		plRegistration.add(btnLogin, "cell 1 0,alignx right");
		
		JButton btnRegister = new JButton("register");
		btnRegister.setHorizontalAlignment(SwingConstants.RIGHT);
		plRegistration.add(btnRegister, "cell 1 0,alignx right");
		
		final JFileChooser fc = new JFileChooser();
		final JButton btnRecord = new JButton("Record / Choose sample");
		contentPane.add(btnRecord, "flowx,cell 0 2");
		
		JButton btnSubmit = new JButton("Submit request");
		contentPane.add(btnSubmit, "cell 0 2,alignx center");
		btnRecord.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnRecord) {
					int val = fc.showOpenDialog(UITest.this);
					
					if(val == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						
						logger.fine("get File "+file.getName());
					}
					else
						logger.fine("no file chosen");
				}
				
			}
			
		});
	}

}
