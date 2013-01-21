package main.tuwien.ac.at.swazam.client.exception;

@SuppressWarnings("serial")
public class PeerNotAvailableException extends Exception {

	public PeerNotAvailableException() {}
	
	public PeerNotAvailableException(String msg) {
		super(msg);
	}
}
