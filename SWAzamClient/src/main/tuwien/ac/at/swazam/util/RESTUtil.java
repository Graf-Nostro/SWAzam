package main.tuwien.ac.at.swazam.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RESTUtil {
	
	public Response put(URL url, String content) throws IOException {
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoInput(true);
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("PUT");
		
		OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
		out.write(content);
		out.close();
		
		httpCon.connect();
		
		InputStream is;
		if (httpCon.getResponseCode() >= 400) {
			is = httpCon.getErrorStream();
		} else {
			is = httpCon.getInputStream();
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;
		String body = "";
		do {
			line = reader.readLine();
			body = body + line;
		} while(line != null);
		
		return createResponse(httpCon.getResponseCode(), httpCon.getResponseMessage(), body);
	}

	protected Response createResponse(Integer responseCode, String responseMessage, String body) {
		return new Response(responseCode, responseMessage, body);
	}
}
