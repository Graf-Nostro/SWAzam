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

import static main.tuwien.ac.at.swazam.peer.Strings.*;

/**
 * 
 * Makes a Fingerprint of a music file in the music directory/library
 * 
 * Uses lacy loading and stores the values offline on the filesystem
 * 
 * @author Raunig Stefan
 */
public class Fingerprinter {
	  
	private static Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.peer.music.library.Fingerprinter");
	
	private final String path;
	
	private Map<String, Fingerprint> library = new HashMap<String, Fingerprint>();
	private final LibrarySerializer libS = new LibrarySerializer();
	
	public Fingerprinter(String path){
		if(path == null || path.equals("")){
			path = System.getProperty("user.dir")+"/library/";
		}
		
		this.path = path;

		//load in file
		if(new File(path + "Library.dat").exists()) library = libS.deserializeMap();
		
		File[] files = listFiles(path);
		
		for(File f : files){
			if( !library.containsKey(f.getName()) ) {
				//puts a dummy obj. into map for lazy loading			
				library.put(f.getName(), null);
			}
		}		
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

	/**
	 * Loads a Fingerprint of a file from the Map, uses lazy loading at this point
	 * 
	 * @param fileName
	 * @return fingerprint if found, else null and throws an exception
	 */
	public Fingerprint loadFingerprintFromMap(final String fileName) throws NoSuchFileInMusicLibrary{
		if(library.containsKey(fileName)) {
			
			Fingerprint fp = library.get(fileName);
			
			if(fp != null) {
				logger.log(Level.INFO, FOUND_FILE_MAP); 
				return fp; 
			}
			
			File f = new File(path + fileName);
			
			try {
				//lazy load calc. fingerprint at the time it is needed
				Fingerprint print = Fingerprinter.getFingerprint(f);
				library.put(fileName, print);
				
				logger.log(Level.FINE, library.toString());
				
				libS.serializeMap(library);
				
				return print;
			} catch (IOException e) {
				logger.log(Level.SEVERE, IOEXCEPTION_FINGERPRINT);
				
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				logger.log(Level.SEVERE, "File causing trouble: " +  f.getName());
				logger.log(Level.SEVERE, UNSUPPORTED_AUDIO_FILE);
				
				//e.printStackTrace();
			}
		} else {
			throw new NoSuchFileInMusicLibrary(FILE_NOT_FOUND_LIBRARY);
		}
		//this code is never reached
		return null;
	}

	public String matchFingerprintToLibrary(final Fingerprint fp){
		/**
		 * DEGUB MOCKUP 
		 */
		
		if( library.containsValue(fp) ) return "YES";
		else return "NO";
	}

}
