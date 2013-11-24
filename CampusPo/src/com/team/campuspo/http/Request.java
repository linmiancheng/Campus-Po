package com.team.campuspo.http;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

import com.team.campuspo.http.HttpExecuter.Method;

public class Request {
	
	private Method mMethodType;
	
	private URI mUri;
	
	private HashMap<String, List<String>> mHeaders;
	
	private byte[] mRequestBody;
	
	public Request(Method methodType, URI uri,
					HashMap<String, List<String>> headers, byte[] requestBody) {
		
		mUri = uri;
		mMethodType = methodType;
		
		mHeaders = headers;
		
		mRequestBody = requestBody;
	}
	
	public Method getRequestType() {
		return mMethodType;
	}
	
	public URI getUri() {
		return mUri;
	}
	
	public HashMap<String, List<String>> getHeaders () {
		return mHeaders;
	}
	
	public byte[] getRequestBody() {
		return mRequestBody;
	}
}
