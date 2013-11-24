package com.team.campuspo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.team.campuspo.R;
import com.team.campuspo.fragment.CommentFragment;
import com.team.campuspo.fragment.PosterDetailFragment;
import com.team.campuspo.fragment.PosterRelatedFragment;


public class PosterActivity extends ActionBarActivity{
	

	private FragmentTabHost mTabHost;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_poster);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);

		mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_content);
		
		//ImageView imageView = new ImageView(this);
		
		//imageView.setImageResource(R.drawable.actionbar_tab_indicator);
		//mPager.setla
		
		mTabHost.addTab(mTabHost.newTabSpec("Detail").setIndicator("Detail",getResources().getDrawable(R.drawable.actionbar_tab_indicator)),
						PosterDetailFragment.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("Participant").setIndicator("Join"),
						CommentFragment.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("Related").setIndicator("Related"),
						PosterRelatedFragment.class, null);		
		
	}
	
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case android.R.id.home :
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	
}
