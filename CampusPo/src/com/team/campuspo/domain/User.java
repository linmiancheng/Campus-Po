package com.team.campuspo.domain;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6235826363309376722L;
	private long userId;
	private String userEmail;
	private String userScreenName;
	private String userProfileIconUrl;
	private String userSignature;
	
	private String userName;
	private int userGender;
	
	private boolean isFocus;
	
	private List<Skill> skills;
	
	public User(JSONObject object) {
		try {
			this.userId = object.getLong("user_id");
			this.userEmail = object.getString("user_email");
			this.userScreenName = object.getString("user_screen_name");
			this.userProfileIconUrl = object.getString("user_profile_icon_url");
			this.userSignature = object.getString("user_signature");
			this.userName = object.getString("user_name");
			this.userGender = object.getInt("user_gender");
			this.setFocus(object.getBoolean("isFoucs"));
			
			if(object.isNull("skills")) {
				JSONArray array = object.getJSONArray("skills");
				skills = new ArrayList<Skill>();
				int length = array.length();
				for(int i = 0; i < length; i++)
					skills.add(new Skill(array.getJSONObject(i)));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserProfileIconUrl() {
		return userProfileIconUrl;
	}

	public void setUserProfileIconUrl(String userProfileIconUrl) {
		this.userProfileIconUrl = userProfileIconUrl;
	}

	public String getUserScreenName() {
		return userScreenName;
	}

	public void setUserScreenName(String userScreenName) {
		this.userScreenName = userScreenName;
	}

	public String getUserSignature() {
		return userSignature;
	}

	public void setUserSignature(String userSignature) {
		this.userSignature = userSignature;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserGender() {
		return userGender;
	}

	public void setUserGender(int userGender) {
		this.userGender = userGender;
	}

	public boolean isFocus() {
		return isFocus;
	}

	public void setFocus(boolean isFocus) {
		this.isFocus = isFocus;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}




	
	
}
