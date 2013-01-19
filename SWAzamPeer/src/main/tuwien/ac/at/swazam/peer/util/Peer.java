package main.tuwien.ac.at.swazam.peer.util;

/**
 * Peer object.
 * 
 * @author Florian Eckerstorfer <florian@eckerstorfer.co>
 */
public class Peer {
	
	private String ip;
	private Integer port;
	private String name;
	
	public Peer() {
	}
		
	/**
	 * Initializes a peer with name, IP address and port.
	 * 
	 * @param name
	 * @param ip
	 * @param port
	 */
	public Peer(String name, String ip, Integer port) {
		setName(name);
		setIp(ip);
		setPort(port);
	}
	
	/**
	 * Sets the name.
	 * 
	 * @param name
	 * @return
	 */
	public Peer setName(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * Sets the IP address.
	 * 
	 * @param ip
	 * @return
	 */
	public Peer setIp(String ip) {
		this.ip = ip;
		return this;
	}
	
	/**
	 * Sets the port.
	 * 
	 * @param port
	 * @return
	 */
	public Peer setPort(Integer port) {
		this.port = port;
		return this;
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the IP address.
	 * 
	 * @return
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * Returns the port.
	 * 
	 * @return
	 */
	public Integer getPort() {
		return port;
	}
}
