package main.tuwien.ac.at.swazam.client.utils;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;

public class PeerRequest {
	
	private String username;
	private String password;
	
	
	public PeerRequest() {
		this.username = UserManagement.getUsername();
		this.password = UserManagement.getPassword();
	}
	
	public PeerRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	

}
