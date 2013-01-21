package main.tuwien.ac.at.swazam.client;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.logging.Logger;

import javax.sound.sampled.UnsupportedAudioFileException;

import main.tuwien.ac.at.swazam.client.ui.ClientUI;


public class MainClient {

	private static Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.client.MainClient");
	
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 */
	public static void main(String[] args) throws IOException {
		
		if(args.length == 2) {
			
			if(args[0].equals("query"))
				new Thread(new ClientThread(args[1])).start();
			
			else
				logger.info("usage cmdline: java main.tuwien.ac.at.swazam.client.MainClient query filename.wav\n" +
						"usage gui: java main.tuwien.ac.at.swazam.client.MainClient");
			
		}
		
		else if(args.length != 0) 
			logger.info("usage cmdline: java main.tuwien.ac.at.swazam.client.MainClient query filename.wav\n" +
					"usage gui: java main.tuwien.ac.at.swazam.client.MainClient");
		
		else {
			// GUI code
			logger.info("ClientUI");
			
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
						ClientUI frame = new ClientUI();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}
		
	}

}
