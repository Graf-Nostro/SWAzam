package main.tuwien.ac.at.swazam.client;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;

public class FingerprintTest {

	private static Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.client.FingerprintTest");
	
	
	public static void test() {
	
		try {
			
			System.out.println("Matching of fingerprints of a sample and actual file");
			
	//		File file = new File(System.getProperty("user.dir")+"/res/sample-01.wav");
	//		System.out.println("Abs path = "+file.getAbsolutePath());
	//		System.out.println("AudioFileFormat = "+AudioSystem.getAudioFileFormat(file).toString());
			
			String path = System.getProperty("user.dir")+"/res/";
		/*	
			System.out.println("Reading audio 01 ...");
			AudioInputStream audio01 = Recorder.getSample(path+"audio-01.wav");
			System.out.println("Reading sample 01 ...");
			AudioInputStream sample01 = Recorder.getSample(path+"sample-01.wav");
			System.out.println("Reading sample 01 distorted ...");
			AudioInputStream sample01dist = Recorder.getSample(path+"sample-01-dist.wav");
			System.out.println("Fingerprinting audio 01");
			Fingerprint fp1 = Fingerprinter.getFingerprint(audio01);
			System.out.println("Fingerprinting sample 01");
			Fingerprint fp2 = Fingerprinter.getFingerprint(sample01);
			System.out.println("Fingerprinting sample 01 distorted");
			Fingerprint fp3 = Fingerprinter.getFingerprint(sample01dist);
			*/
			
			logger.finest("Fingerprinting audio 01");
			Fingerprint fp1 = Fingerprinter.getFingerprint(new File(path+"audio-01.wav"));
			logger.finest("Fingerprinting sample 01");
			Fingerprint fp2 = Fingerprinter.getFingerprint(new File(path+"sample-01.wav"));
			logger.finest("Fingerprinting sample 01 distorted");
			Fingerprint fp3 = Fingerprinter.getFingerprint(new File(path+"sample-01-dist.wav"));
			
			System.out.print("Matching original file to itself: ");
			double result1 = fp1.match(fp1);
			System.out.println(result1);
			
			System.out.print("Matching original file to sample file: ");
			double result2 = fp1.match(fp2);
			System.out.println(result2);
			
			System.out.print("Matching original file to distorted sample file: ");
			double result3 = fp1.match(fp3);
			System.out.println(result3);
			
			System.out.println("\n Results");
			System.out.println("compared to original file: "+result1);
			System.out.println("compared to sample file: "+result2);
			System.out.println("compared to distorted sample file: "+result3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
}
