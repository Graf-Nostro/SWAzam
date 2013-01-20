package test.tuwien.ac.at.swazam.util;

import main.tuwien.ac.at.swazam.util.FileNameServerSocket;

public class FileNameSocketTest {

	public FileNameSocketTest(){}
	
	public static void main(String[] args){
		FileNameServerSocket fns = new FileNameServerSocket(4444);
		
		if(fns.readMsg().equals("FileNameTest")) System.out.println("SUCCESS");
		else System.err.println("FAIL");
	}
	
}
