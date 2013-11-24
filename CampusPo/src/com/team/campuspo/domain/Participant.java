package com.team.campuspo.domain;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class Participant implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8823164832226165565L;
	private long participantId;
	private String profileIconUrl;
	private String screenName;
	private Date joinAt;
	private long posterId;
	
	public void init(JSONObject object) {
		try {
			this.participantId = object.getLong("participant_id");
			this.setProfileIconUrl(object.getString("profile_icon_url"));
			this.setScreenName(object.getString("screen_name"));
			this.joinAt = null;
			this.posterId = object.getLong("poster_id");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public long getParticipantId() {
		return participantId;
	}
	public void setParticipantId(long participantId) {
		this.participantId = participantId;
	}
	
	public Date getJoinAt() {
		return joinAt;
	}
	public void setJoinAt(Date joinAt) {
		this.joinAt = joinAt;
	}
	public long getPosterId() {
		return posterId;
	}
	public void setPosterId(long posterId) {
		this.posterId = posterId;
	}

	public String getProfileIconUrl() {
		return profileIconUrl;
	}

	public void setProfileIconUrl(String profileIconUrl) {
		this.profileIconUrl = profileIconUrl;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
}
