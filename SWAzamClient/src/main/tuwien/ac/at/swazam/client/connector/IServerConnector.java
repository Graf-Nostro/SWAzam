package main.tuwien.ac.at.swazam.client.connector;

import main.tuwien.ac.at.swazam.client.exception.LoginFailedException;
import main.tuwien.ac.at.swazam.client.exception.RegistrationFailedException;

public interface IServerConnector {

	void register(String username, String password) throws RegistrationFailedException;
	void login(String username, String password) throws LoginFailedException;
}
