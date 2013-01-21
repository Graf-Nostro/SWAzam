package main.tuwien.ac.at.swazam.client.exception;

@SuppressWarnings("serial")
public class NoPeerAvailableException extends Exception {

	public NoPeerAvailableException() {}
	
	public NoPeerAvailableException(String msg) {
		super(msg);
	}
}
