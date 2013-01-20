package main.tuwien.ac.at.swazam.peer.music.library;

import static main.tuwien.ac.at.swazam.peer.Strings.IOEXCEPTION_FINGERPRINT;
import static main.tuwien.ac.at.swazam.peer.Strings.UNSUPPORTED_AUDIO_FILE;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.UnsupportedAudioFileException;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;

/**
 * 
 * Makes fingerprints of all music files in the directory
 * 
 * @author Raunig Stefan
 */
public class LibraryWorker {

	private static final Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.music.library.Librarythread.class");
	
	private final Library library;
		
	private Map<Fingerprint, String> map = new HashMap<Fingerprint, String>();
	private final LibrarySerializer  libS;
	
	
	public LibraryWorker(final Library library){
		this.library = library;
		
		 libS = new LibrarySerializer(this.library);
	}
	
	public void run() {
		logger.log(Level.INFO, "Thread is running and computing fingerprints");
		
		logger.log(Level.INFO, "\n" + library.listSongs());
		
		for(File f: library.getSongs()){
			try {
				//compute fingerprint
				Fingerprint fpRaw = Fingerprinter.getFingerprint(f);
				
				//save in library
				if( !map.containsKey(fpRaw) ) map.put(fpRaw, f.getName());
				
			} catch (IOException e) {
				logger.log(Level.SEVERE, IOEXCEPTION_FINGERPRINT);
				
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				logger.log(Level.SEVERE, "File causing trouble: " +  f.getName());
				logger.log(Level.SEVERE, UNSUPPORTED_AUDIO_FILE);
				
				//e.printStackTrace();
			}
		}
		//persist
		libS.serializeMap(map);
	}
}
