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
public class LibraryThread extends Thread {
	
	private static final Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.music.library.Librarythread.class");
	
	private final String path;
	
	private Map<Fingerprint, String> library = new HashMap<Fingerprint, String>();
	private final LibrarySerializer  libS = new LibrarySerializer();
	
	public LibraryThread(final String path){
		this.path = path;
	}
	
	@Override
	public void run() {
		logger.log(Level.INFO, "Thread is running and computing fingerprints");
		
		for(File f: listFiles(path)){
			try {
				
				//compute fingerprint
				Fingerprint fpRaw = Fingerprinter.getFingerprint(f);
				
				//save in library
				if( !library.containsKey(fpRaw) ) library.put(fpRaw, f.getName());
				
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
		libS.serializeMap(library);
	}
	
	/**
	 * Lists files in directory 
	 * 
	 * @param path to files
	 * @return all files in that path
	 */
	private File[] listFiles(final String path){
		String files;
		File folder = new File(path);
		
		File[] listOfFiles = folder.listFiles(); 	
		
		//print out all files
		for (int i = 0; i < listOfFiles.length; i++){
			if (listOfFiles[i].isFile()) {
				files = listOfFiles[i].getName();
				logger.log(Level.FINE, files);
			}
		}
		return listOfFiles;
	}
}
