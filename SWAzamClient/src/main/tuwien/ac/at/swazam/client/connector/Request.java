package main.tuwien.ac.at.swazam.client.connector;

import main.tuwien.ac.at.swazam.client.utils.ClientUtils;
import ac.at.tuwien.infosys.swa.audio.Fingerprint;

public class Request {
	
	private String ip;
	private int port;
	private Fingerprint fingerprint;
	
	
	public Request(Fingerprint fp) {
		this(ClientUtils.getIp(), ClientUtils.getPort(), fp);
	}
	
	public Request(String ip, int port, Fingerprint fp) {
		this.ip = ip;
		this.port = port;
		this.fingerprint = fp;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the fingerprint
	 */
	public Fingerprint getFingerprint() {
		return fingerprint;
	}

	/**
	 * @param fingerprint the fingerprint to set
	 */
	public void setFingerprint(Fingerprint fingerprint) {
		this.fingerprint = fingerprint;
	}
	

}
