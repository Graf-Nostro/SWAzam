package main.tuwien.ac.at.swazam.client.connector;

import java.io.IOException;

public interface IPeerConnector {
	
	void sendFingerprint() throws IOException;
}
