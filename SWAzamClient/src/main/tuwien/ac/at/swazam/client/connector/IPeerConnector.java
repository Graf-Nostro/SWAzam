package main.tuwien.ac.at.swazam.client.connector;

import java.io.File;

import main.tuwien.ac.at.swazam.client.exception.PeerNotAvailableException;

public interface IPeerConnector {

	Boolean sendMusicRecognitionRequest(File sample) throws PeerNotAvailableException;
}
