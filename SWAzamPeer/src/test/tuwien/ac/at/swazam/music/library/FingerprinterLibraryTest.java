package test.tuwien.ac.at.swazam.music.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import main.tuwien.ac.at.swazam.peer.MainPeer;
import main.tuwien.ac.at.swazam.peer.music.library.Fingerprinter;
import main.tuwien.ac.at.swazam.peer.music.library.Library;
import main.tuwien.ac.at.swazam.peer.util.Peer;
import main.tuwien.ac.at.swazam.util.PropertyReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;

public class FingerprinterLibraryTest {

	private String PATH = PropertyReader.getInstance(MainPeer.PROPERTY_FILE).getProperty("library-directory");
	
	private final String FILE_NAME1 = "II - High Winter - 01 - High Winter.wav";
	private final String FILE_NAME2 = "Leslie - Woods - 05 - Accommodation.wav";
	private final String FILE_NAME3 = "Verschwundene - MariGGGopa - 01 MariGGGopa.wav";
	private final String FILE_NAME4 = "Leslie - Woods - 01 - The Good In Each Other.wav";
	private final String FILE_NAME5 = "PPNG - PPNG2 - 01 - Randy.wav";
	
	private Library       library;
	
	@Before
	public void setUp() throws Exception {
		//setup
		Peer peer = new Peer("peer2", "localhost", 8080);
		library = peer.getLibrary();
		library.addSong(new File(PATH + "/" + FILE_NAME1));
		library.addSong(new File(PATH + "/" + FILE_NAME2));
		library.addSong(new File(PATH + "/" + FILE_NAME3));
		library.addSong(new File(PATH + "/" + FILE_NAME4));
		library.addSong(new File(PATH + "/" + FILE_NAME5));
	}
	
	@After
	public void tearDown() throws Exception {
		library  = null;
	}
	
	@Test
	public void simpleLookupTest(){		
		try {
			Fingerprint fpRaw = Fingerprinter.getFingerprint(library.getSongs().get(1));
			String result	  = library.matchFingerprint(fpRaw);	
			
			assertEquals(FILE_NAME2, result);
			
		} catch (IOException e) {
			assertFalse(true);
		} catch (UnsupportedAudioFileException e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void simpleLookupJsonEncodingTest(){
		try {
			Fingerprint fpRaw = Fingerprinter.getFingerprint(library.getSongs().get(1));
			
			Gson gson      = new Gson();
			String json    = gson.toJson(fpRaw);
			Fingerprint fp = gson.fromJson(json, Fingerprint.class);
			
			String result= library.matchFingerprint(fp);
			
			assertEquals(FILE_NAME2, result);
			
		} catch (IOException e) {
			assertFalse(true);
		} catch (UnsupportedAudioFileException e) {
			assertFalse(true);
		}	
	}
	
}
