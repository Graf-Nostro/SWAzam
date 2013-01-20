package main.tuwien.ac.at.swazam.peer.util;


/**
 * Peer object.
 * 
 * @author Florian Eckerstorfer <florian@eckerstorfer.co>
 */
public class SimplePeer {
	
	private String  ip;
	private Integer port;
	private String  name;
			
	/**
	 * Initializes a peer with name, IP address and port.
	 * 
	 * @param name
	 * @param ip
	 * @param port
	 */
	public SimplePeer(String name, String ip, Integer port) {
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
	public SimplePeer setName(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * Sets the IP address.
	 * 
	 * @param ip
	 * @return
	 */
	public SimplePeer setIp(String ip) {
		this.ip = ip;
		return this;
	}
	
	/**
	 * Sets the port.
	 * 
	 * @param port
	 * @return
	 */
	public SimplePeer setPort(Integer port) {
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
