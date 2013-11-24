package com.team.campuspo.service;

import java.util.HashMap;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.team.campuspo.domain.Timeline;
import com.team.campuspo.http.GetPublicTimelineExecuter;
import com.team.campuspo.http.HttpExecuter;
import com.team.campuspo.http.Result;

public class CampusPoService extends IntentService{
	
	private static final String TAG = "CampusPoService";
	
	private static final String REQUEST_PARAMS = "REQUEST_PARAMS";
	private static final String REQUEST_CALLBACK = "REQUEST_CALLBACK";
	
	private ResultReceiver mCallback;

	public CampusPoService() {
		super("CampusPoService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		Intent requestIntent = intent;
		int requestType = requestIntent.getIntExtra(ServiceContants.REQUEST_TYPE, 0);
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> params =
				(HashMap<String, String>) requestIntent.getSerializableExtra(CampusPoService.REQUEST_PARAMS);
		mCallback = (ResultReceiver)requestIntent.getParcelableExtra(CampusPoService.REQUEST_CALLBACK);
		
		HttpExecuter executer = createExecuter(requestType, params);
		
		Result result = executer.excute();
		
		int resultCode = result.getCode();
		
		mCallback.send(resultCode, buildResultData(result));
	}
	
	public HttpExecuter createExecuter(int requestType, 
			HashMap<String, String> params) {
		
		HttpExecuter executer = null;
		switch (requestType) {
		case ServiceContants.REQUEST_PUBLIC_TIMELINE :
			executer = new GetPublicTimelineExecuter( params);
			break;
		
		case ServiceContants.REQUEST_USER_TIMELINE :
			break;
			
		}
		return executer;
	}
	
	public Bundle buildResultData(Result result) {
		Bundle data = new Bundle();
		
		data.putSerializable("Serializable",result.getEntity());
		
		return data;
	}
	
}
