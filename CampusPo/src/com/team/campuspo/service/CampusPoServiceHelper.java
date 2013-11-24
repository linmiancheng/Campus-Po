package com.team.campuspo.service;

import java.util.HashMap;
import java.util.LinkedList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

public class CampusPoServiceHelper {
	
	private static CampusPoServiceHelper instance;
	
	public static final String ACTION_REQUEST_RESULT = "ACTION_REQUEST_RESULT";
	public static final String REQUEST_TYPE = "REQUEST_TYPE";
	public static final String REQUEST_RESULT_CODE = "REQUEST_RESULT_CODE";
	public static final String REQUEST_RESULT_DATA = "REQUEST_RESUTL_DATA";
	
	private Context mContext;
	private LinkedList<Integer> pendingRequests = new LinkedList<Integer>();
	
	private CampusPoServiceHelper(Context ctx) {
		mContext = ctx;
	}
	
	public synchronized static CampusPoServiceHelper getInstance(Context ctx) {				
		if(instance == null)
			instance = new CampusPoServiceHelper(ctx);
		return instance;
	}
	
	//fetch the latest, default length of the timeline, the default length is 20;
	public void getPublicTimeline() {
		this.getPublicTimeline(-1, 20);
	}
	
	public void getPublicTimeline(int startId, int length) {
		
		if(pendingRequests.contains(ServiceContants.REQUEST_PUBLIC_TIMELINE))
			return;
		
		pendingRequests.add(ServiceContants.REQUEST_PUBLIC_TIMELINE);
		
		Intent intent  = new Intent(mContext, CampusPoService.class);
		
		ResultReceiver serviceCallback = new ResultReceiver(null) {
			
			@Override
			protected void onReceiveResult(int resultCode, Bundle resultData) {
				handleRequestResponse(resultCode, resultData);
			}
		};
		
		HashMap<String, String> params = new HashMap<String, String>();
		
		params.put("startId", "" + startId);
		params.put("length", "" + length);
		
		intent.putExtra("RequestParams", params);
		intent.putExtra("ServiceCallback", serviceCallback);
		
		mContext.startService(intent);
	}
	
	public void getUserTimeline(int uid) {
		
	}
	
	public void getUserTimeline(int uid, int startId, int length) {
		
	}
	
	public void handleRequestResponse(int resultCode, Bundle resultData) {
		Intent intent = new Intent(ACTION_REQUEST_RESULT);
		
		intent.putExtra("ResultCode", resultCode);
		intent.putExtra("ResultData", resultData);
		intent.putExtra("RequestType", pendingRequests.poll());
		
		mContext.sendBroadcast(intent);
	}
	
	
}
