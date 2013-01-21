package main.tuwien.ac.at.swazam.peer.music.library;

import static main.tuwien.ac.at.swazam.peer.Strings.CLASS_NOT_FOUND;
import static main.tuwien.ac.at.swazam.peer.Strings.FILE_NOT_FOUND;
import static main.tuwien.ac.at.swazam.peer.Strings.IO_SERIALIZATION;

import java.io.File;
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

/**
 * 
 * Utility Class to save and load the HashMap library for performance issus
 * 
 * @author Raunig Stefan
 * @author Florian Eckerstorfer <florian@eckerstorfer.co>
 *
 * SuppressWarning serial id is optimized by compiler
 */
@SuppressWarnings("serial")
public class LibrarySerializer implements Serializable
{
	private static Logger logger = Logger.getLogger("ac.at.tuwien.swazam.peer.music.library.LibrarySerializer");
	
	private Library library;
	
	public LibrarySerializer(final Library library){
		this.library = library; 
	}
	
	public LibrarySerializer serialize() {
		try {
			FileOutputStream fos = new FileOutputStream(library.getLibraryFile());
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(library.getFingerprintMap());
			oos.close();
			fos.close();
		} catch (IOException e) {
			logger.log(Level.SEVERE, IO_SERIALIZATION);
			e.printStackTrace();
		}
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public LibrarySerializer deserialize() {
		Map<Fingerprint, String> fingerprintMap = null;
		if (!new File(library.getLibraryFile()).exists()) {
			return this;
		}
		
		try {
			FileInputStream fis = new FileInputStream(library.getLibraryFile());
			ObjectInputStream ois = new ObjectInputStream(fis);
			fingerprintMap = (Map<Fingerprint, String>)ois.readObject();
			ois.close();
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
		
		if (fingerprintMap.size() > 0) {
			for (Map.Entry<Fingerprint, String> entry : fingerprintMap.entrySet()) {
				library.addSong(new File(library.getLibraryPath() + "/" + entry.getValue()), entry.getKey());
			}
		}
		
		return this;
	}
}
