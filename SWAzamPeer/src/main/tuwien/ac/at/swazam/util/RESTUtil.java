package main.tuwien.ac.at.swazam.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * RESTUtil
 * 
 * @author Florian Eckerstorfer <florian@eckerstorfer.co>
 */
public class RESTUtil {
	
	public Response get(URL url) throws IOException {
		return execute(url, "GET");
	}
	
	public Response put(URL url, String content) throws IOException {
		return execute(url, "PUT", content);
	}
	
	protected Response execute(URL url, String method) throws IOException {
		return execute(url, method, null);
	}
	
	protected Response execute(URL url, String method, String content) throws IOException {
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoInput(true);
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod(method);
		
		if ((method == "PUT" || method == "POST") && content != null) {
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			out.write(content);
			out.close();
		}
		
		httpCon.connect();
		
		InputStream is;
		if (httpCon.getResponseCode() >= 400) {
			is = httpCon.getErrorStream();
		} else {
			is = httpCon.getInputStream();
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = reader.readLine();
		String body = "";
		while (line != null) {
			body = body + line;
			line = reader.readLine();
		}
		
		return createResponse(httpCon.getResponseCode(), httpCon.getResponseMessage(), body);
	}

	protected Response createResponse(Integer responseCode, String responseMessage, String body) {
		return new Response(responseCode, responseMessage, body);
	}
}
