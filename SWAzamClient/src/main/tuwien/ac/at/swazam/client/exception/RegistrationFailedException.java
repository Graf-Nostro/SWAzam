package main.tuwien.ac.at.swazam.client.exception;

@SuppressWarnings("serial")
public class RegistrationFailedException extends Exception {

	public RegistrationFailedException() {}
	
	public RegistrationFailedException(String msg) {
		super(msg);
	}
}
