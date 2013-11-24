package com.team.campuspo.http;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.team.campuspo.domain.Timeline;

public class GetPublicTimelineExecuter extends HttpExecuter{
	
	private final static Method methodType = Method.GET;
	private final static String PATH = "/posters/public_timeline";	
	private final static URI mUri =  URI.create(HttpExecuter.HttpMetaData.DOMAIN + PATH);
	
	private HashMap<String, String> mParams;
	
	public GetPublicTimelineExecuter( HashMap<String, String> params) {
		mParams = params;
	}

	@Override
	public Request buildRequest() {
		
		
		
		Request request = new Request(methodType, mUri, null, null);
		
		return request;
	}

	@Override
	public Result buildResult(Response reponse) {
		
		int statusCode = reponse.getStatusCode;
		
		byte[] body = reponse.getBody();
		
		Timeline timeline = null;
		
		try {
			JSONObject jsonObject = new JSONObject(body.toString());
			
			int code = jsonObject.getInt("code");
					
			if(code == 1) {
				timeline = new Timeline(jsonObject.getJSONArray("data"));
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Result(statusCode, null, timeline);
	}

	
}
