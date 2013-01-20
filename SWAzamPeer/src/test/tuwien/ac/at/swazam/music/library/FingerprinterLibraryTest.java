package test.tuwien.ac.at.swazam.music.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

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
	
	private final String FILE_NAME1 = "j01.wav";
	private final String FILE_NAME2 = "f01small.wav";
	private final String FILE_NAME3 = "d01.wav";
	private final String FILE_NAME4 = "f01.wav";
	private final String FILE_NAME5 = "b01.wav";
	
	private Fingerprinter fprinter;
	private Library       library;
	
	@Before
	public void setUp() throws Exception {
		Peer peer = new Peer("peer1", "localhost", 8080);
		
		List<File> files = new ArrayList<File>();
		
		files.add(new File(PATH + FILE_NAME1));
		files.add(new File(PATH + FILE_NAME2));
		files.add(new File(PATH + FILE_NAME3));
		files.add(new File(PATH + FILE_NAME4));
		files.add(new File(PATH + FILE_NAME5));
		
		library  = new Library(peer, files);
		fprinter = new Fingerprinter(library);
	}
	
	@After
	public void tearDown() throws Exception {
		library  = null;
		fprinter = null;
	}
	
	@Test
	public void simpleLookupTest(){		
		try {
			Fingerprint fpRaw = Fingerprinter.getFingerprint(library.getSongs().get(1));
			String result	  = fprinter.matchFingerprintToLibrary(fpRaw);	
			
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
			
			String result= fprinter.matchFingerprintToLibrary(fp);
			
			assertEquals(FILE_NAME2, result);
			
		} catch (IOException e) {
			assertFalse(true);
		} catch (UnsupportedAudioFileException e) {
			assertFalse(true);
		}	
	}
	
}
