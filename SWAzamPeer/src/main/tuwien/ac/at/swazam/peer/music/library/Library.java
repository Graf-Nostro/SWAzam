package main.tuwien.ac.at.swazam.peer.music.library;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.UnsupportedAudioFileException;

import main.tuwien.ac.at.swazam.peer.MainPeer;
import main.tuwien.ac.at.swazam.peer.util.Peer;
import main.tuwien.ac.at.swazam.util.PropertyReader;
import ac.at.tuwien.infosys.swa.audio.Fingerprint;

/**
 * 
 * Custom library, files are only visible if added in this library 
 * 
 * @author Raunig Stefan
 * @author Florian Eckerstorfer <florian@eckerstorfer.co>
 */
public class Library implements ILibrary
{
	private Peer peer;
	private List<File> songs;
	private String libraryPath;
	private String libraryFile;
	private Map<Fingerprint, String> fingerprintMap = new HashMap<Fingerprint, String>();
	
	public Library(final Peer peer){
		this(peer, new ArrayList<File>());
	}
	
	public Library(final Peer peer, final List<File> songs){
		this.peer = peer;
		this.songs = songs;
		libraryPath = PropertyReader.getInstance(MainPeer.PROPERTY_FILE).getProperty("library-directory");
		libraryFile = libraryPath + "/" + peer.getName() + "Library.dat";
	}
	
	@Override
	public Library addSong(File song, Fingerprint fingerprint) {
		if (!hasSong(song.getName())) {
			songs.add(song);
			fingerprintMap.put(fingerprint, song.getName());
		}
		return this;
	}
	
	@Override
	public Library addSong(File song) throws IOException, UnsupportedAudioFileException {
		if (!hasSong(song.getName())) {
			addSong(song, Fingerprinter.getFingerprint(song));
		}
		return this;
	}

	
	@Override
	public Library addSongs(List<File> songs)  throws IOException, UnsupportedAudioFileException {
		for (File song : songs) {
			addSong(song);
		}
		return this;
	}
	
	@Override
	public Library deleteSong(File song) {
		if(songs.contains(song)) {
			songs.remove(song);
		}
		Fingerprint key = null;
		for (Map.Entry<Fingerprint, String> entry : fingerprintMap.entrySet()) {
			if (song.getName().equals(entry.getValue())) {
				key = entry.getKey();
			}
		}
		if (key != null) {
			fingerprintMap.remove(key);
		}
		return this;
	}

	@Override
	public List<File> getSongs() {
		return songs;
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
	public String getLibraryFile() {
		return libraryFile;
	}
	
	@Override
	public String getLibraryPath() {
		return libraryPath;
	}
	
	@Override
	public Map<Fingerprint, String> getFingerprintMap() {
		return fingerprintMap;
	}
	
	@Override
	public String matchFingerprint(Fingerprint fingerprint) {
		if (fingerprintMap.containsKey(fingerprint)) {
			return fingerprintMap.get(fingerprint);
		} else {
			return null;
		}
	}
	
	private Boolean hasSong(String name) {
		for (File song : songs) {
			if (name.equals(song.getName())) {
				return new Boolean(true);
			}
		}
		return new Boolean(false);
	}
}
