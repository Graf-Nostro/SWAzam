package main.tuwien.ac.at.swazam.client.utils;

public class ClientUtils {

	private static final String ip = "127.0.0.1";
	private static final int port = 80;
	private static final String filePath = System.getProperty("user.dir") + "/library/";
	
	
	/**
	 * @return the ip
	 */
	public static String getIp() {
		return ip;
	}
	
	
	/**
	 * @return the port
	 */
	public static int getPort() {
		return port;
	}
	
	
	/**
	 * 
	 * @return the filePath
	 */
	public static String getFilePath() {
		return filePath;
	}
}
