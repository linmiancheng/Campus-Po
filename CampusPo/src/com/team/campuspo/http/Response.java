package com.team.campuspo.http;

import java.util.HashMap;
import java.util.List;

public class Response {
	
	private int mStatusCode;
	
	private HashMap<String, List<String>> mHeaders;
	
	private byte[] mBody;
	
	public Response(int statusCode, HashMap<String, List<String>> headers,
						byte[] body) {
		mStatusCode = statusCode;
		mHeaders = headers;
		mBody = body;
	}
	
	public int getStatusCode;
	
	public HashMap<String, List<String>> getHeaders() {
		return mHeaders;
	}
	
	public byte[] getBody() {
		return mBody;
	}
}
