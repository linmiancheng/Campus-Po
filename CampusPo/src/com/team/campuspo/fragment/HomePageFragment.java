package com.team.campuspo.fragment;

import java.lang.reflect.Field;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.team.campuspo.R;
import com.team.campuspo.widget.SegmentedRadioGroup;

public class HomePageFragment extends Fragment implements OnCheckedChangeListener{
	
	FragmentManager mFragmentManager;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home_page, container, false);
		
		SegmentedRadioGroup group = (SegmentedRadioGroup) v.findViewById(R.id.segment_text);
	    group.setOnCheckedChangeListener(this);
	    
	   mFragmentManager = getChildFragmentManager();
	    
	    if(savedInstanceState != null) 
	    	return v;
	    
	    group.check(R.id.btn_lastest);

		return v;
	}
	
	//修补support包中frament中的bug
	@Override
	public void onDetach() {
	    super.onDetach();

	    try {
	        Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
	        childFragmentManager.setAccessible(true);
	        childFragmentManager.set(this, null);

	    } catch (NoSuchFieldException e) {
	        throw new RuntimeException(e);
	    } catch (IllegalAccessException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Override
    public void onDestroyView() {
        super.onDestroyView();
    }

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
		FragmentTransaction ft = mFragmentManager.beginTransaction();
		Fragment newFragment = null;
		
		switch(checkedId) {
		case R.id.btn_lastest :
			newFragment = getChildFragmentManager().findFragmentByTag(PublicTimelineFragment.TAG);
			if(newFragment == null)
				newFragment = new PublicTimelineFragment();
			ft.replace(R.id.content, newFragment, PublicTimelineFragment.TAG);
			ft.commit();
			break;
		case R.id.btn_focus :
			newFragment = getChildFragmentManager().findFragmentByTag(FocusTimelineFragment.TAG);
			if(newFragment == null)
				newFragment = new FocusTimelineFragment();
			ft.replace(R.id.content, newFragment, FocusTimelineFragment.TAG);
			ft.commit();
			break;
		}		
	}
	
	class SegmentListner implements RadioGroup.OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			
		}
		
	}
}

