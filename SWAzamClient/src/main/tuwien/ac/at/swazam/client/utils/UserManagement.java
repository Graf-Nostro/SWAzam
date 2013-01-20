package main.tuwien.ac.at.swazam.client.utils;

public class UserManagement {

	private static String username;
	private static String password;
	
	
	/**
	 * @return the username
	 */
	public static String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public static void setUsername(String username) {
		UserManagement.username = username;
	}
	
	/**
	 * @return the password
	 */
	public static String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public static void setPassword(String password) {
		UserManagement.password = password;
	}
	
	
	public static Boolean isLoginDataAvailable() {
		return username != null  &&  password != null;
	}
	
	
}
