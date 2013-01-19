package main.tuwien.ac.at.swazam.peer.music.library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;

import static main.tuwien.ac.at.swazam.peer.Strings.*;

/**
 * 
 * Utility Class to save and load the HashMap library for performance issus
 * 
 * @author Raunig Stefan
 *
 * SuppressWarning serial id is optimized by compiler
 */
@SuppressWarnings("serial")
public class LibrarySerializer implements Serializable {

	private static Logger logger = Logger.getLogger("ac.at.tuwien.swazam.peer.music.library.LibrarySerializer");
	
	private final String LIBRARY_NAME = System.getProperty("user.dir") + "/library/Library.dat";
	
	public LibrarySerializer(){}

	/**
	 * serialize map and save it to file
	 */
	public void serializeMap(final Map<Fingerprint, String> map){		 
	    try {  		    	
	    	FileOutputStream fos = new FileOutputStream(LIBRARY_NAME);
	    	ObjectOutputStream oos = new ObjectOutputStream(fos);
	    	oos.writeObject(map);
	    	oos.close();

	    	logger.log(Level.INFO, map.toString());
	    	
	    	fos.close();	      
	    } catch(IOException e) { 
	    	logger.log(Level.SEVERE, IO_SERIALIZATION);
	    	
	    	e.printStackTrace();
	    }
	}
	
	/**
	 * Deserialize the map and load it from file
	 * 
	 * @return Fingerprint HashMap
	 */
	@SuppressWarnings("unchecked")
	public Map<Fingerprint, String> deserializeMap(){
		Map<Fingerprint, String> deSerMap = null;
		
		try {
			FileInputStream fis = new FileInputStream(LIBRARY_NAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			deSerMap = (Map<Fingerprint, String>) ois.readObject();
			ois.close();
		
			logger.log(Level.FINE, deSerMap.toString());
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, FILE_NOT_FOUND);
			
			e.printStackTrace();
		} catch (IOException e) {
			logger.log(Level.SEVERE, IO_SERIALIZATION);
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			logger.log(Level.SEVERE, CLASS_NOT_FOUND);

			e.printStackTrace();
		}
		return deSerMap;
	}
}
