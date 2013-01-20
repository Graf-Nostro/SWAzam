package main.tuwien.ac.at.swazam.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	
	private static PropertyReader instance;
	
	public static PropertyReader getInstance(String propertyFile) {
		if (instance == null) {
			instance = new PropertyReader(propertyFile);
		}
		return instance;
	}
	
	private String propertyFile;
	private Properties properties;
	
	private PropertyReader(String propertyFile) {
		this.propertyFile = propertyFile;
	}
	
	public String getProperty(String key) {
		if (null == properties) {
			readPropertyFile();
		}
		return properties.getProperty(key);
	}
	
	protected void readPropertyFile() {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFile);
		properties = new Properties();    
    
        try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}

}
