package com.campuspo.biz;

import java.util.List;

import net.sf.json.JSONObject;

import com.campuspo.bean.TsFocus;
import com.campuspo.bean.TsUsers;

public interface UserBiz {

	public JSONObject getUser(TsUsers user);

	public JSONObject checkLogin(String email, String password);

	public  JSONObject getUserFocusTimeLine(TsFocus focus, int since_id, int length);

	public JSONObject getFollowing(TsUsers user, int since_id, int length);
	
	public JSONObject getMyPosters(TsUsers user, int since_id, int length);

}
