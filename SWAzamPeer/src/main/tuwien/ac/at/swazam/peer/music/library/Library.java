package main.tuwien.ac.at.swazam.peer.music.library;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import main.tuwien.ac.at.swazam.peer.MainPeer;
import main.tuwien.ac.at.swazam.peer.util.Peer;
import main.tuwien.ac.at.swazam.util.PropertyReader;

/**
 * 
 * Custom library, files are only visible if added in this library 
 * 
 * @author Raunig Stefan
 */
public class Library implements ILibrary {

	private Peer peer;
	
	private List<File> songs = new ArrayList<File>();
	
	private String libPath = PropertyReader.getInstance(MainPeer.PROPERTY_FILE).getProperty("library-directory");
	private String libName;
	
	public Library(final Peer peer){
		this.peer = peer;
		this.libName = peer.getName() + "Library.dat";
	}
	
	public Library(final Peer peer, final List<File> songs){
		this(peer);
		this.songs = songs;
	}
	
	@Override
	public void addSong(File file) {
		songs.add(file);
	}

	@Override
	public void deleteSong(File file) {
		if(songs.contains(file)) songs.remove(file);
	}

	@Override
	public String listSongs() {
		StringBuilder sb = new StringBuilder();
		
		for(File f: songs){
			sb.append("File: " + f.getName());
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public List<File> getSongs() {
		return songs;
	}
	
	@Override
	public void addSongs(List<File> songs) {
		this.songs = songs;		
	}

	@Override
	public Peer getPeer() {
		return this.peer;
	}

	@Override
	public void setPeer(final Peer peer) {
		this.peer = peer;
	}

	@Override
	public String getPath() {
		return this.libPath;
	}

	@Override
	public void setPath(final String path) {
		this.libPath = path;
	}

	@Override
	public String getLibName() {
		return this.libName;
	}

	@Override
	public void setLibName(String name) {
		this.libName = name;
	}
}
