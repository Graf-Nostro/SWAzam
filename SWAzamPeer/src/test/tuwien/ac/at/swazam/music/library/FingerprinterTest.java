package test.tuwien.ac.at.swazam.music.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import main.tuwien.ac.at.swazam.peer.music.library.Fingerprinter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;

public class FingerprinterTest {

	private final String FILE_NAME = "f01small.wav";
	private Fingerprinter fprinter;	
	
	private final String PATH = System.getProperty("user.dir")+"/library/";
	
	private File file;
		
	@Before
	public void setUp() throws Exception {
		fprinter = new Fingerprinter(PATH);
		file = new File(PATH + FILE_NAME);
	}
	
	@After
	public void tearDown() throws Exception {
		fprinter = null;
		file 	 = null;
	}
	
	@Test
	public void findMatchToFingerprintTest(){		
		try {
			Fingerprint fpRaw = Fingerprinter.getFingerprint(file);
			String result	  = fprinter.matchFingerprintToLibrary(fpRaw);	
			
			assertEquals(FILE_NAME, result);
			
		} catch (IOException e) {
			assertFalse(true);
		} catch (UnsupportedAudioFileException e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void findMatchToFingerprintwithJsonEncodingTest(){
		try {
			Fingerprint fpRaw = Fingerprinter.getFingerprint(file);
			
			Gson gson = new Gson();
			String json = gson.toJson(fpRaw);
			Fingerprint fp = gson.fromJson(json, Fingerprint.class);
			
			String result = fprinter.matchFingerprintToLibrary(fp);	
			
			assertEquals(FILE_NAME, result);
			
		} catch (IOException e) {
			assertFalse(true);
		} catch (UnsupportedAudioFileException e) {
			assertFalse(true);
		}
	}
	
}
