package main.tuwien.ac.at.swazam.client.connector;

public class Request {
	private Integer code;
	private String message;
	private String body;
	
	public Request() {
	}
	
	public Request(Integer code) {
		this(code, null);
	}
	
	public Request(String message) {
		this(null, message);
	}
	
	public Request(Integer code, String message) {
		this(code, message, null);
	}
	
	public Request(Integer code, String message, String body) {
		setCode(code);
		setMessage(message);
		setBody(body);
	}
	
	public Request setCode(Integer code) {
		this.code = code;
		return this;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public Request setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Request setBody(String body) {
		this.body = body;
		return this;
	}
	
	public String getBody() {
		return body;
	}
}
