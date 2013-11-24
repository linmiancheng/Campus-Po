package com.team.campuspo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Poster implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3340186305346216581L;
	private long posterId;
	private long sponsorId;
	private String sponsorScreenName;
	private String sponsorProfileIconUrl;
	private String posterTitle;
	private String posterDescription;
	private int posterWantedNum;
	private int posterParticipantNum;
	private Date posterReleatedTime;

	private boolean isFavour = false;
	private boolean isJoin = false;
	private boolean isSponsor = false;

	public Poster(JSONObject object) {
		init(object);
	}

	public Poster(String str) {
		try {
			init(new JSONObject(str));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init(JSONObject object) {
		try {
			this.posterId = object.getLong("poster_id");
			this.posterTitle = object.getString("poster_title");
			this.sponsorId = object.getLong("sponsor_id");
			this.sponsorScreenName = object.getString("sponsor_screen_name");
			this.sponsorProfileIconUrl = object
					.getString("sponsor_profile_icon_url");
			this.posterDescription = object.getString("poster_description");
			this.posterWantedNum = object.getInt("poster_wanted_num");
			this.posterParticipantNum = object.getInt("poster_participant_num");
			this.posterReleatedTime = null;// parseDate(object.getString("poster_released_time"),
											// "EEE MMM dd HH:mm:ss z yyyy");

			this.setFavour(object.getBoolean("isFavour"));
			this.isJoin = object.getBoolean("isJoin");
			this.isSponsor = object.getBoolean("isSponsor");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public long getPosterId() {
		return posterId;
	}

	public void setPosterId(long posterId) {
		this.posterId = posterId;
	}

	public long getSponserId() {
		return sponsorId;
	}

	public void setSponserId(int sponserId) {
		this.sponsorId = sponserId;
	}

	public String getSponserName() {
		return this.sponsorScreenName;
	}

	public void setSponserName(String sponserName) {
		this.sponsorScreenName = sponserName;
	}

	public String getPosterTitle() {
		return posterTitle;
	}

	public void setPosterTitle(String posterTitle) {
		this.posterTitle = posterTitle;
	}

	public int getPosterWantedNum() {
		return posterWantedNum;
	}

	public void setPosterWantedNum(int posterWantedNum) {
		this.posterWantedNum = posterWantedNum;
	}

	public String getPosterDescription() {
		return posterDescription;
	}

	public void setPosterDescription(String posterDescription) {
		this.posterDescription = posterDescription;
	}

	public int getPosterParticipantNum() {
		return posterParticipantNum;
	}

	public void setPosterParticipantNum(int posterParticipantNum) {
		this.posterParticipantNum = posterParticipantNum;
	}

	public Date getPosterReleatedTime() {
		return posterReleatedTime;
	}

	public void setPosterReleatedTime(Date posterReleatedTime) {
		this.posterReleatedTime = posterReleatedTime;
	}

	public String getSponserProfileIconUrl() {
		return sponsorProfileIconUrl;
	}

	public void setSponserProfileIconUrl(String sponserProfileIconUrl) {
		this.sponsorProfileIconUrl = sponserProfileIconUrl;
	}

	public boolean isJoin() {
		return isJoin;
	}

	public void setJoin(boolean isJoin) {
		this.isJoin = isJoin;
	}

	public boolean isSponsor() {
		return isSponsor;
	}

	public void setSponser(boolean isSponsor) {
		this.isSponsor = isSponsor;
	}

	public boolean isFavour() {
		return isFavour;
	}

	public void setFavour(boolean isFavour) {
		this.isFavour = isFavour;
	}

	public static List<Poster> constructPosters(JSONArray array) {

		try {
			List<Poster> posters = new ArrayList<Poster>();
			int length = array.length();
			for (int i = 0; i < length; i++)
				posters.add(new Poster(array.getJSONObject(i)));

			return posters;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
