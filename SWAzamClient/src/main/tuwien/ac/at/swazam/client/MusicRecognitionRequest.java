package main.tuwien.ac.at.swazam.client;

import java.io.File;
import java.util.logging.Logger;

import main.tuwien.ac.at.swazam.client.connector.IPeerConnector;
import main.tuwien.ac.at.swazam.client.connector.PeerConnector;

public class MusicRecognitionRequest {
	
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.client.MusicRecognitionRequest");

	@SuppressWarnings("unused")
	private IPeerConnector peerConnector;
	private File requestFile;
	
	public MusicRecognitionRequest() {
		peerConnector = new PeerConnector("http://localhost:8081/SWAzamPeer");
	}
	
	public void setFile(File requestFile) {
		this.requestFile = requestFile;
	}
	
	public File getFile() {
		return this.requestFile;
	}
	
	public Boolean sendRequest() {
		if(this.requestFile == null)
			return false;
		
		else
			return sendRequest(requestFile);
	}
	
	public Boolean sendRequest(File requestFile) {
/*		
		try {
			Fingerprint fp = Fingerprinter.getFingerprint(requestFile);
			Boolean result = peerConnector.sendMusicRecognitionRequest(new PeerRequest(fp));

			if(result) {
				logger.info("Request sent successfully");
			}
			
			return result;
		} catch (IOException e) {
			logger.warning("Error in sendRequest");
		} catch (PeerNotAvailableException e) {
			logger.warning("Peer not available");
			e.printStackTrace();
		}
		*/
		return false;
	}
	
}
