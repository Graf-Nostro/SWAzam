package main.tuwien.ac.at.swazam.peer.music.library;

/**
 * 
 * Exception for Music Library  
 * 
 * @author Raunig Stefan
 * 
 * SuppressWarning serial id is optimized by compiler
 */
@SuppressWarnings("serial")
public class NoSuchFileInMusicLibrary extends Exception {

	public NoSuchFileInMusicLibrary(String msg){
		super(msg);
	}
}
