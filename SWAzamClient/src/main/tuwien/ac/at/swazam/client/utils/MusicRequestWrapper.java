package main.tuwien.ac.at.swazam.client.utils;

import java.io.File;

public class MusicRequestWrapper {

	private File sample;
	
	
	public MusicRequestWrapper() {
		
	}
	
	public MusicRequestWrapper(File sample) {
		this.sample = sample;
	}


	/**
	 * @return the sample
	 */
	public File getSample() {
		return sample;
	}


	/**
	 * @param sample the sample to set
	 */
	public void setSample(File sample) {
		this.sample = sample;
	}
	
	
}
