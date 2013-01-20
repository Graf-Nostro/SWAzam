package main.tuwien.ac.at.swazam.client.connector;

import main.tuwien.ac.at.swazam.client.exception.LoginFailedException;
import main.tuwien.ac.at.swazam.client.exception.NoPeerAvailableException;
import main.tuwien.ac.at.swazam.client.exception.RegistrationFailedException;
import main.tuwien.ac.at.swazam.entity.Peer;

public interface IServerConnector {

	Boolean register(String username, String password) throws RegistrationFailedException;
	Boolean login(String username, String password) throws LoginFailedException;
	Peer askForPeer(String username, String password) throws NoPeerAvailableException;
}
