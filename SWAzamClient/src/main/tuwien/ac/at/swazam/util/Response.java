package main.tuwien.ac.at.swazam.util;

public class Response {
	private Integer code;
	private String message;
	private String body;
	
	public Response() {
	}
	
	public Response(Integer code) {
		this(code, null);
	}
	
	public Response(String message) {
		this(null, message);
	}
	
	public Response(Integer code, String message) {
		this(code, message, null);
	}
	
	public Response(Integer code, String message, String body) {
		setCode(code);
		setMessage(message);
		setBody(body);
	}
	
	public Response setCode(Integer code) {
		this.code = code;
		return this;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public Response setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Response setBody(String body) {
		this.body = body;
		return this;
	}
	
	public String getBody() {
		return body;
	}
}
