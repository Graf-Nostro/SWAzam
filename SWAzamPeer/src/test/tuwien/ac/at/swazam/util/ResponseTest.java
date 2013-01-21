package test.tuwien.ac.at.swazam.util;

import static org.junit.Assert.assertEquals;
import main.tuwien.ac.at.swazam.util.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResponseTest {
	
	private Response response;

	@Before
	public void setUp() throws Exception {
		response = new Response();
	}

	@After
	public void tearDown() throws Exception {
		response = null;
	}
	
	@Test
	public void testConstructorWithCode() {
		response = new Response(200);
		assertEquals(new Integer(200), response.getCode());
	}
	
	@Test
	public void testConstructorWithMessage() {
		response = new Response("ok");
		assertEquals("ok", response.getMessage());
	}
	
	@Test
	public void testConstructorWithCodeAndMessage() {
		response = new Response(200, "ok");
		assertEquals(new Integer(200), response.getCode());
		assertEquals("ok", response.getMessage());
	}
	
	@Test
	public void testConstructorWithCodeAndMessageAndBody() {
		response = new Response(200, "OK", "Hello");
		assertEquals(new Integer(200), response.getCode());
		assertEquals("OK", response.getMessage());
		assertEquals("Hello", response.getBody());
	}

	@Test
	public void testSetCode() {
		response.setCode(200);
		assertEquals(new Integer(200), response.getCode());
	}
	
	@Test
	public void testSetMessage() {
		response.setMessage("ok");
		assertEquals("ok", response.getMessage());
	}

	@Test
	public void testSetBody() {
		response.setBody("Hello");
		assertEquals("Hello", response.getBody());
	}
}
