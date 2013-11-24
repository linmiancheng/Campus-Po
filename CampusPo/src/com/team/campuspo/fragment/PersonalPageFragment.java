package com.team.campuspo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.campuspo.R;
import com.team.campuspo.domain.User;

public class PersonalPageFragment extends Fragment
					implements View.OnClickListener{
	
	private static final String TAG = "PersonalPageFragment";
	
	private ImageView mImageViewUserProfile;
	private TextView mTextViewScreenName;
	private TextView mTextViewUserSignature;
	
	private Button mButtonSkill;
	private Button mButtonPoster;
	private Button mButtonFollowing;
	private Button mButtonMessage;
	private Button mButtonDetail;
	
	private User mUser;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//init the user here from the cache
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		View v = inflater.inflate(R.layout.fragment_personal_page, container, false);
		//bind id to each component 
		mImageViewUserProfile = (ImageView) v.findViewById(R.id.iv_user_profile);
		mTextViewScreenName = (TextView) v.findViewById(R.id.tv_user_screen_name);
		mTextViewUserSignature = (TextView) v.findViewById(R.id.tv_user_signature);
		mButtonSkill = (Button)v.findViewById(R.id.btn_skill);
		mButtonPoster = (Button)v.findViewById(R.id.btn_poster);
		mButtonFollowing = (Button)v.findViewById(R.id.btn_following);
		mButtonMessage = (Button)v.findViewById(R.id.btn_message);
		mButtonDetail = (Button)v.findViewById(R.id.btn_detail);
		//bind the listener to each button
		mButtonSkill.setOnClickListener(this);
		mButtonPoster.setOnClickListener(this);
		mButtonFollowing.setOnClickListener(this);
		mButtonMessage.setOnClickListener(this);
		mButtonDetail.setOnClickListener(this);
		//Populate the UI component
		populateUi();
		//update the data
		update();
		return v;
	}
	

	private void update() {
		

	}

	private void populateUi() {
		
		
		if(mUser != null) {			
			mTextViewScreenName.setText(mUser.getUserScreenName());
			mTextViewUserSignature.setText(mUser.getUserSignature());

		}
	}

	@Override
	public void onClick(View v) {
		
		Intent intent = new Intent();
		
		//set the class of Activity that will be invoked here
		switch(v.getId()) {
		case R.id.btn_skill :
			break;
		case R.id.btn_poster :
			break;
		case R.id.btn_following:
			break;
		case R.id.btn_message :
			break;
		case R.id.btn_detail :
			break;
		}
		
		//startActivity(intent);
		
	}

}
