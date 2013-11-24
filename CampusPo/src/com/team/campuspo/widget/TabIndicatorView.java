package com.team.campuspo.widget;

import com.team.campuspo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TabIndicatorView extends RelativeLayout{
	
	private ImageView mTabIcon;
	private TextView mTabLabel;

	public TabIndicatorView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater =  LayoutInflater.from(context);
		inflater.inflate(R.layout.view_tab_indicator, this);
		mTabIcon = (ImageView) findViewById(R.id.iv_tab_icon);
		//mTabLabel = (TextView) findViewById(R.id.tv_tab_label);
	}
	
	public TabIndicatorView setIcon(int resId) {
		mTabIcon.setImageResource(resId);
		return this;
	}
	
	public TabIndicatorView setLabel(CharSequence label) {
		mTabLabel.setText(label);
		return this;
	}

}
