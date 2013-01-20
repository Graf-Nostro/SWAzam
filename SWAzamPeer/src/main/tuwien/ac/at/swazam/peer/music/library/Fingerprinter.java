package main.tuwien.ac.at.swazam.peer.music.library;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;
import ac.at.tuwien.infosys.swa.audio.FingerprintSystem;

/**
 * 
 * Makes a Fingerprint of a music file in the music directory/library
 * 
 * Uses a thread to compute the fingerprints
 * 
 * @author Raunig Stefan
 * @author Florian Eckerstorfer <florian@eckerstorfer.co>
 */
public class Fingerprinter
{	
	/**
	 * Computes a fingerprint of a music file
	 *
	 * @param  audio
	 * @return Fingerprint of the music file
	 * @throws IOException, UnsupportedAudioFileExceptio
	 **/
	public static Fingerprint getFingerprint(final File file) throws IOException, UnsupportedAudioFileException {
		AudioInputStream audio;
		audio = AudioSystem.getAudioInputStream(file);
		
		return FingerprintSystem.fingerprint(audio);
	}
}
