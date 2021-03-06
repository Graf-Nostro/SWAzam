package main.tuwien.ac.at.swazam.entity;


public class Peer {
	
	public static final String NAME_FIELD_NAME = "name";
	public static final String IP_FIELD_NAME = "ip";
	public static final String PORT_FIELD_NAME = "port";
		
	private	String name = "";
	private	String ip = "";
	private Integer port = 0;
	
	public Peer() { 
	}
	
	public Peer(String name, String ip, Integer port) {
		this.name = name;
		this.ip = ip;
		this.port = port;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}
	
	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getPort() {
		return port;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || other.getClass() != getClass()) {
			return false;
		}
		return name.equals(((Peer) other).name);
	}
}
