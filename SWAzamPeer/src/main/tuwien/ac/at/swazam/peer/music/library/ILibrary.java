package main.tuwien.ac.at.swazam.peer.music.library;

import java.io.File;
import java.util.List;

import main.tuwien.ac.at.swazam.peer.util.Peer;

public interface ILibrary {

	//Songs in library
	public void       addSong   (final File file);
	public void       addSongs  (final List<File> songs);
	public void       deleteSong(final File file);
	public List<File> getSongs();
	public String     listSongs();
	
	//Library Path
	public String getPath();
	public void   setPath(final String path);
	
	//Library Name
	public String getLibName();
	public void   setLibName(final String name);
	
	//Peer information
	public Peer getPeer();
	public void setPeer(final Peer peer);
}
