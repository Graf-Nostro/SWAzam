package main.tuwien.ac.at.swazam.client;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Recorder {

	
	
	public static AudioInputStream getSample(String path) throws IOException {
		
		try {
			File file = new File(path);
			return AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}
}
