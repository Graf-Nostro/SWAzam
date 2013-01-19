package main.tuwien.ac.at.swazam.peer;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;

import main.tuwien.ac.at.swazam.peer.music.library.Fingerprinter;
import main.tuwien.ac.at.swazam.peer.music.library.NoSuchFileInMusicLibrary;

public class MainPeer {

	public static void main(String[] args) {
		//TODO make a junit testcase out of it
		
		Fingerprinter fp = new Fingerprinter("");
		
		try {
			String filename = "f01.wav";
			//String sample   = "sample-01.wav";
			
			String path = System.getProperty("user.dir")+"/library/";
			
			File file = new File(path + filename);
			
			Fingerprint fp1 = Fingerprinter.getFingerprint(file);
			Fingerprint fp2 = fp.loadFingerprintFromMap(filename);
			
			System.out.print("Matching original file to stored file: ");
			double result1 = fp1.match(fp2);

			System.out.println(result1);
			
		} catch (NoSuchFileInMusicLibrary e) {
			System.out.println("Error " + e.getMessage());
			
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error IO");
			
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
}
