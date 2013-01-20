package main.tuwien.ac.at.swazam.client.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.tuwien.ac.at.swazam.client.MusicRecognitionRequest;
import net.miginfocom.swing.MigLayout;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class ClientUI extends JFrame implements ActionListener {

	
	private final Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.client.GUI");
	
	private JPanel contentPane;
	private JTextField fdUsername;
	private JPasswordField fdPassword;
	
	private JPanel plRegistration;
	
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblFill;
	private JLabel lblFillRequest;
	private JLabel lblSample;
	private JLabel lblStatus;
	
	private JTextArea results;

	private JButton btnLogin;
	private JButton btnRegister;
	private JButton btnSubmit;

	private final JFileChooser fc;
	private final JButton btnRecord;
	
	private final MusicRecognitionRequest request;
	


	/**
	 * Create the frame.
	 */
	public ClientUI() {
		
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
		contentPane.setLayout(new MigLayout("", "[grow]", "[top][][][][][grow]"));
		
		plRegistration = new JPanel();
		plRegistration.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(plRegistration, "cell 0 0,growx,aligny top");
		plRegistration.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		lblUsername = new JLabel("Username:");
		plRegistration.add(lblUsername, "cell 0 0,alignx trailing");
		
		fdUsername = new JTextField();
		plRegistration.add(fdUsername, "flowx,cell 1 0,alignx left");
		fdUsername.setColumns(10);
		
		lblPassword = new JLabel("password:");
		plRegistration.add(lblPassword, "cell 1 0");
		
		fdPassword = new JPasswordField();
		fdPassword.setColumns(10);
		plRegistration.add(fdPassword, "cell 1 0");
		
		lblFill = new JLabel("");
		plRegistration.add(lblFill, "cell 1 0,growx");
		
		btnLogin = new JButton("login");
		plRegistration.add(btnLogin, "cell 1 0,alignx right");
		
		btnRegister = new JButton("register");
		btnRegister.setHorizontalAlignment(SwingConstants.RIGHT);
		plRegistration.add(btnRegister, "cell 1 0,alignx right");
		
		fc = new JFileChooser();
		btnRecord = new JButton("Choose sample");
		contentPane.add(btnRecord, "flowx,cell 0 2");
		
		btnSubmit = new JButton("Submit request");
		contentPane.add(btnSubmit, "cell 0 2,alignx center");
		
		lblSample = new JLabel("No sample selected");
		contentPane.add(lblSample, "flowx,cell 0 3,alignx left");
		
		results = new JTextArea(10,40);
		results.setText(" Results of Music Recognition Requests \n" +
						" ------------------------------------- \n\n");
		results.setEditable(false);
		JScrollPane sp = new JScrollPane(results);
		contentPane.add(sp, "cell 0 5,alignx center");
		
		lblFillRequest = new JLabel("");
		lblFillRequest.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblFillRequest, "cell 0 3,growx");
		
		lblStatus = new JLabel("");
		contentPane.add(lblStatus, "cell 0 3,alignx left");
	
		
		btnRecord.addActionListener(this);
		btnSubmit.addActionListener(this);
		
		request = new MusicRecognitionRequest();
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnRecord) {
			int val = fc.showOpenDialog(ClientUI.this);
			
			if(val == JFileChooser.APPROVE_OPTION) {
				File requestFile = fc.getSelectedFile();
				request.setFile(requestFile);
				lblSample.setText("Chosen sample: "+requestFile.getName());
			}
			else
				logger.fine("no file chosen");
		}
		
		if(e.getSource() == btnSubmit) {
			if(request.getFile() == null)
				JOptionPane.showMessageDialog(this, "Please choose a sample file first", "Selecting file", JOptionPane.INFORMATION_MESSAGE);
			
			else {
				lblStatus.setText("Sending request ...");
				
				boolean result = request.sendRequest();
				
				if(result) {
					JOptionPane.showMessageDialog(this, "Request sent successfully", "Sending request", JOptionPane.INFORMATION_MESSAGE);
					lblStatus.setText("Request sent");
				}
				
				else {
					JOptionPane.showMessageDialog(this, "Request failed", "Request not successful", JOptionPane.ERROR_MESSAGE);
					lblStatus.setText("Request failed");
				}
				
			}
		}
	}

}
