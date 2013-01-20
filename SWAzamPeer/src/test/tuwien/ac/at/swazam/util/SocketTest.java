package test.tuwien.ac.at.swazam.util;

import main.tuwien.ac.at.swazam.util.FileNameClientSocket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SocketTest {
	
	FileNameClientSocket cls;
	
	@Before
	public void setUp() throws Exception {
		cls = new FileNameClientSocket("localhost", 4444);
	}

	@After
	public void tearDown() throws Exception {
		cls = null;
	}
	
	@Test
	public void testConstructorWithCode() {
		cls.sendMsg("FileNameTest");
	}
}
