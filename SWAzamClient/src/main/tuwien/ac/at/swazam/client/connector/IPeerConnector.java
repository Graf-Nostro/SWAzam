package main.tuwien.ac.at.swazam.client.connector;

import main.tuwien.ac.at.swazam.client.exception.PeerNotAvailableException;

public interface IPeerConnector {

	Boolean sendRequest(Request request) throws PeerNotAvailableException;
}
