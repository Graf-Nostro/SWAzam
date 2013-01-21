package main.tuwien.ac.at.swazam.peer.music.library;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.UnsupportedAudioFileException;

import main.tuwien.ac.at.swazam.peer.util.Peer;
import ac.at.tuwien.infosys.swa.audio.Fingerprint;

public interface ILibrary {

	//Songs in library
	public Library addSong(File file, Fingerprint fingerprint);
	public Library addSong(File file) throws IOException, UnsupportedAudioFileException;
	
	public Library addSongs(List<File> songs) throws IOException, UnsupportedAudioFileException;
	
	public Library deleteSong(File file);
	
	public List<File> getSongs();
	
	public Peer getPeer();
	
	public void setPeer(Peer peer);
	
	public String getLibraryFile();
	public String getLibraryPath();
	
	public Map<Fingerprint, String> getFingerprintMap();
	
	public String matchFingerprint(Fingerprint fingerprint);
}
