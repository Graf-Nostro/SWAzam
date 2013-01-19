package main.tuwien.ac.at.swazam.client.exception;

public class LoginFailedException extends Exception {

	
	public LoginFailedException() {}
	
	public LoginFailedException(String msg) {
		super(msg);
	}
}
