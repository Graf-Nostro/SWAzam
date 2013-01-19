package main.tuwien.ac.at.swazam.peer.music.library;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
 */
public class Fingerprinter {
	  
	private static Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.peer.music.library.Fingerprinter");
	
	private Map<Fingerprint, String> map = new HashMap<Fingerprint, String>();
	private final LibrarySerializer libS;
	private final Library			library;
	
	public Fingerprinter(final Library library){	
		this.library = library;
		libS = new LibrarySerializer(library);
		
		LibraryThread th = new LibraryThread(library);
		th.start();
		
		//load in file
		if( new File( library.getPath() + library.getLibName() ).exists() ) map = libS.deserializeMap();
		else th.run();
	}
	
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
		
	/**
	 * Search the library for a fingerprint match
	 * 
	 * @param fp
	 * @return filename or No match found
	 */
	public String matchFingerprintToLibrary(final Fingerprint fp){		
		logger.log(Level.INFO, "Searching for file");
		
		//load in file
		if( new File( library.getPath() + library.getLibName() ).exists() ) map = libS.deserializeMap();
		
		if(map.containsKey(fp)) {
			logger.log(Level.INFO, "Found one!");
			return map.get(fp);
		} else {
			logger.log(Level.INFO, "Found none!");
			return "No match found";
		}
	}
}
