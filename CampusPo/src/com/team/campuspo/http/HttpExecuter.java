package com.team.campuspo.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;

import com.team.campuspo.utils.HttpUtils;

public abstract class HttpExecuter {
	
	private static final String TAG = "HttpExecuter";
		
	
	//模版方法
	public Result excute() {
		
		Response response = doRequest(buildRequest());
		
		Result result = buildResult(response);
		
		process(result);
		
		return result;
	}
	
	public Response doRequest(Request request) {
		
		return null;
	}
	
	public void process(Result result) {
		
	}

	
	public abstract Request buildRequest();
	
	public abstract Result buildResult(Response reponse);
	
	
	public static enum Method {
		GET, POST
	}
	
	public static class HttpMetaData {
		
		public static final String DOMAIN = "http://www.campuspo.com";
	}
}
