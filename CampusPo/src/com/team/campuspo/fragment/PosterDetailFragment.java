package com.team.campuspo.fragment;

import com.team.campuspo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PosterDetailFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater,
						ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		View v = inflater.inflate(R.layout.fragment_poster_detail, container, false);
		return v;
	}
						
}
