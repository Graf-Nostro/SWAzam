package main.tuwien.ac.at.swazam.peer.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class PeerCreator {

	public Peer createFromRequest(HttpServletRequest request) {
		Pattern pattern = Pattern.compile("http://(.*?):(\\d*?)/SWAzamPeer-(.*?)/");
		Matcher matcher = pattern.matcher(request.getRequestURL().toString());
		Peer peer = null;
		if (matcher.find()) {
			peer = new Peer(matcher.group(3), matcher.group(1), new Integer(matcher.group(2)));
		}
		return peer;
	}
	
}
