package main.tuwien.ac.at.swazam.peer.music.library;

import main.tuwien.ac.at.swazam.peer.util.Peer;
import ac.at.tuwien.infosys.swa.audio.Fingerprint;

/**
 * MusicRecognizer
 * 
 * @author Florian Eckerstorfer <florian@eckerstorfer.co>
 */
public class MusicRecognizer
{
	private Peer peer;
	private Fingerprint fingerprint;
	
	public MusicRecognizer(Peer peer, Fingerprint fingerprint) {
		this.peer = peer;
		this.fingerprint = fingerprint;
		
		new LibrarySerializer(peer.getLibrary()).deserialize();
	}
	
	public String recognize() {
		String result = peer.getLibrary().matchFingerprint(fingerprint);
		return result;
	}
}
