package com.campuspo.biz;

import java.util.List;

import net.sf.json.JSONObject;

import com.campuspo.bean.TsFavour;
import com.campuspo.bean.TsParticipant;
import com.campuspo.bean.TsPoster;
import com.campuspo.bean.TsUsers;

public interface PosterBiz {
	
	public JSONObject getPosterList(Integer userId , int startPosition , int length);
	
	public JSONObject getPoster(String posterId,Integer userId);
	
	public JSONObject getParticipants(TsPoster poster);
	
	public boolean addParticipant(TsParticipant pacticipant);
	
	public JSONObject addPoster(TsPoster poster,TsUsers user);
	
	public void addFavour(TsFavour favour);
	
	public boolean removeParticipant(TsParticipant participant);
}
