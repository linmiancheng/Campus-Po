package com.team.campuspo.domain;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

public class Timeline implements java.io.Serializable{
	
	private List<Poster> posters;
	
	private JSONArray jsonArray;
	
	public Timeline(JSONArray array) {
		this.jsonArray = array;
	}

	public List<Poster> getPosters() {
		
		int length = jsonArray.length();
		
		posters = new ArrayList<Poster>();
		
		
			try {
				for(int i = 0; i < length; i++)
					posters.add(new Poster(jsonArray.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		
		return posters;
	}
}
