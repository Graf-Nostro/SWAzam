package test.tuwien.ac.at.swazam.music.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import main.tuwien.ac.at.swazam.peer.MainPeer;
import main.tuwien.ac.at.swazam.peer.music.library.Fingerprinter;
import main.tuwien.ac.at.swazam.peer.music.library.MusicRecognizer;
import main.tuwien.ac.at.swazam.peer.util.Peer;
import main.tuwien.ac.at.swazam.util.PropertyReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;

/**
 * MusicRecognizerTest
 * 
 * @author Florian Eckerstorfer <florian@eckerstorfer.co>
 */
public class MusicRecognizerTest
{
	private final String FILE1 = PropertyReader.getInstance(MainPeer.PROPERTY_FILE).getProperty("library-directory") + "/Leslie - Woods - 01 - The Good In Each Other.wav";
	private final String FILE2 = PropertyReader.getInstance(MainPeer.PROPERTY_FILE).getProperty("library-directory") + "/Verschwundene - MariGGGopa - 01 MariGGGopa.wav";
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testRecognize() {
		Peer peer = new Peer("florian", "localhost", 8080);
		Fingerprint fingerprint = null;
		try {
			fingerprint = Fingerprinter.getFingerprint(new File(FILE1));
			assertTrue(true);
		} catch (IOException e) {
			assertTrue(false);
		} catch (UnsupportedAudioFileException e) {
			assertTrue(false);
		}
		
		MusicRecognizer musicRecognizer = new MusicRecognizer(peer, fingerprint);
		String result = musicRecognizer.recognize();
		assertEquals("f01.wav", result);
	}
	
	@Test
	public void testRecognizeWithRedirect() {
		Peer peer = new Peer("florian", "localhost", 8080);
		Fingerprint fingerprint = null;
		try {
			fingerprint = Fingerprinter.getFingerprint(new File(FILE2));
			assertTrue(true);
		} catch (IOException e) {
			assertTrue(false);
		} catch (UnsupportedAudioFileException e) {
			assertTrue(false);
		}
		
		MusicRecognizer musicRecognizer = new MusicRecognizer(peer, fingerprint);
		String result = musicRecognizer.recognize();
		assertEquals("d01.wav", result);
	}
}
