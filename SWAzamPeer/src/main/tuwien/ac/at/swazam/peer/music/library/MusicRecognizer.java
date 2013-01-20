package main.tuwien.ac.at.swazam.peer.music.library;

import java.util.logging.Level;
import java.util.logging.Logger;

import main.tuwien.ac.at.swazam.peer.MainPeer;
import main.tuwien.ac.at.swazam.peer.connector.PeerConnector;
import main.tuwien.ac.at.swazam.peer.connector.ServerConnector;
import main.tuwien.ac.at.swazam.peer.connector.ServerNotAvailableException;
import main.tuwien.ac.at.swazam.peer.util.Peer;
import main.tuwien.ac.at.swazam.peer.util.PeerFinder;
import main.tuwien.ac.at.swazam.peer.util.PeerRegistry;
import main.tuwien.ac.at.swazam.util.PropertyReader;
import ac.at.tuwien.infosys.swa.audio.Fingerprint;

/**
 * MusicRecognizer
 * 
 * @author Florian Eckerstorfer <florian@eckerstorfer.co>
 */
public class MusicRecognizer
{
	private static Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.peer.music.library.MusicLibrary ");
	private Peer peer;
	private Fingerprint fingerprint;
	private PeerFinder peerFinder;
	
	public MusicRecognizer(Peer peer, Fingerprint fingerprint) {
		this.peer = peer;
		this.fingerprint = fingerprint;
		
		String serverUrl = PropertyReader.getInstance(MainPeer.PROPERTY_FILE).getProperty("server-url");
		if (null == serverUrl) {
			serverUrl = System.getProperty("server-url");
		}
		
		peerFinder = new PeerFinder(new PeerRegistry(
				ServerConnector.getInstance().setServerURL(serverUrl)
		).updatePeersFromServer());
		new LibrarySerializer(peer.getLibrary()).deserialize();
	}
	
	public String recognize() {
		String result = peer.getLibrary().matchFingerprint(fingerprint);
		if (null == result) {
			Peer peer = peerFinder.findPeer();
			if (peer == null) {
				return null;
			} else {
				return PeerConnector.getInstance().redirectMatchRequest(peer, fingerprint);
			}
		} else {
			try {
				ServerConnector.getInstance().acceptRequest(peer, result);
			} catch (ServerNotAvailableException e) {
				logger.log(Level.SEVERE, "Could not send acceptrequest request to server.");
				e.printStackTrace();
			}
			return result;
		}
	}
}
