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
import main.tuwien.ac.at.swazam.client.connector.IPeerConnector;
import main.tuwien.ac.at.swazam.client.connector.IServerConnector;
import main.tuwien.ac.at.swazam.client.connector.PeerConnector;
import main.tuwien.ac.at.swazam.client.connector.ServerConnector;
import main.tuwien.ac.at.swazam.client.exception.LoginFailedException;
import main.tuwien.ac.at.swazam.client.exception.PeerNotAvailableException;
import main.tuwien.ac.at.swazam.client.exception.RegistrationFailedException;
import main.tuwien.ac.at.swazam.client.utils.MusicRequestWrapper;
import main.tuwien.ac.at.swazam.client.utils.PeerManagement;
import main.tuwien.ac.at.swazam.client.utils.PeerRequestHandler;
import main.tuwien.ac.at.swazam.client.utils.UserManagement;
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
	
	private JTextArea taResults;

	private JButton btnLogin;
	private JButton btnRegister;
	private JButton btnSubmit;

	private final JFileChooser fc;
	private final JButton btnRecord;
	
	private MusicRequestWrapper musicRequest;
	
	private PeerRequestHandler peerRequestHandler;
	
	private final IPeerConnector peerConnector;
	private final IServerConnector serverConnector;
	


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
		
		taResults = new JTextArea(10,40);
		taResults.setText(" Results of Music Recognition Requests \n" +
						" ------------------------------------- \n\n");
		taResults.setEditable(false);
		JScrollPane sp = new JScrollPane(taResults);
		contentPane.add(sp, "cell 0 5,alignx center");
		
		lblFillRequest = new JLabel("");
		lblFillRequest.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblFillRequest, "cell 0 3,growx");
		
		lblStatus = new JLabel("");
		contentPane.add(lblStatus, "cell 0 3,alignx left");
	
		
		btnRegister.addActionListener(this);
		btnLogin.addActionListener(this);
		btnRecord.addActionListener(this);
		btnSubmit.addActionListener(this);
		
		musicRequest = new MusicRequestWrapper();
		
		UserManagement.setUsername("admin");
		UserManagement.setPassword("admin");
		
		peerConnector = new PeerConnector();
		serverConnector = new ServerConnector();
		
		peerRequestHandler = new PeerRequestHandler(serverConnector);
		peerRequestHandler.start();
	}


	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnRegister) {
			if(fdUsername.getText().equals("") || fdPassword.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please fill in both fields", "Registration", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			try {
				serverConnector.register(fdUsername.getText(), fdPassword.getText());
				
				JOptionPane.showMessageDialog(this, "Registration process was successful", "Registration", JOptionPane.INFORMATION_MESSAGE);
				lblStatus.setText("Registration successful");
				UserManagement.setUsername(fdUsername.getText());
				UserManagement.setPassword(fdPassword.getText());
				
				
			} catch (RegistrationFailedException e1) {
				logger.warning("Registration could not be completed");
				JOptionPane.showMessageDialog(this, "Registration not successful", "Registration failed", JOptionPane.ERROR_MESSAGE);
				lblStatus.setText("Registration failed");
			}
		}
		
		
		if(e.getSource() == btnLogin) {
			if(fdUsername.getText().equals("") || fdPassword.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please fill in both fields", "Login", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			try {
				serverConnector.login(fdUsername.getText(), fdPassword.getText());
				
				JOptionPane.showMessageDialog(this, "Login was successful", "Login", JOptionPane.INFORMATION_MESSAGE);
				lblStatus.setText("Login successful");
				UserManagement.setUsername(fdUsername.getText());
				UserManagement.setPassword(fdPassword.getText());
				
			} catch (LoginFailedException e1) {
				logger.warning("Login failed");
				JOptionPane.showMessageDialog(this, "Login not successful, try again", "Login", JOptionPane.ERROR_MESSAGE);
				lblStatus.setText("Login failed");
			}
		}
		
		
		if(e.getSource() == btnRecord) {
			int val = fc.showOpenDialog(ClientUI.this);
			
			if(val == JFileChooser.APPROVE_OPTION) {
				File requestFile = fc.getSelectedFile();
				musicRequest.setSample(requestFile);
				lblSample.setText("Chosen sample: "+requestFile.getName());
			}
			else
				logger.fine("no file chosen");
		}
		
		if(e.getSource() == btnSubmit) {
			if(musicRequest.getSample() == null)
				JOptionPane.showMessageDialog(this, "Please choose a sample file first", "Selecting file", JOptionPane.INFORMATION_MESSAGE);
			
			else {
				if(!PeerManagement.isPeerAvailable()) {
					JOptionPane.showMessageDialog(this, "No peers available, try again later.", "Request delayed", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				lblStatus.setText("Sending request ...");
				
				String result = null;
				try {
					result = peerConnector.sendMusicRecognitionRequest(musicRequest.getSample());
				} catch (PeerNotAvailableException e1) {
					logger.warning("Sending music recognition request failed");
				}
				
				if(result != null) {
					JOptionPane.showMessageDialog(this, "Request processed successfully", "Processing request", JOptionPane.INFORMATION_MESSAGE);
					lblStatus.setText("Request sent");
					taResults.setText(taResults.getText() + " " + result + "\n");
				}
				
				else {
					JOptionPane.showMessageDialog(this, "Request failed", "Request not successful", JOptionPane.ERROR_MESSAGE);
					lblStatus.setText("Request failed");
				}
			}
		}
	}

}
