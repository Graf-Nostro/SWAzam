package main.tuwien.ac.at.swazam.client;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;
import ac.at.tuwien.infosys.swa.audio.FingerprintSystem;

public class Fingerprinter {
	
	private static Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.client.Fingerprinter");

	public static Fingerprint getFingerprint(File file) throws IOException {
		
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(file);
			return FingerprintSystem.fingerprint(audio);
		} catch (UnsupportedAudioFileException e) {
			logger.severe("Could not extract the audio from file "+file.getName());
		}
		return null;
	}
}
