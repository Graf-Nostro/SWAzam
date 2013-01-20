package main.tuwien.ac.at.swazam.client.exception;

public class NoPeerAvailableException extends Exception {

	public NoPeerAvailableException() {}
	
	public NoPeerAvailableException(String msg) {
		super(msg);
	}
}
