package main.tuwien.ac.at.swazam.client;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;
import ac.at.tuwien.infosys.swa.audio.FingerprintSystem;

public class Fingerprinter {

	public static Fingerprint getFingerprint(AudioInputStream audio) throws IOException {
		return FingerprintSystem.fingerprint(audio);
	}
}
