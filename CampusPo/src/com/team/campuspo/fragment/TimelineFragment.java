package com.team.campuspo.fragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.team.campuspo.R;
import com.team.campuspo.activity.PosterActivity;
import com.team.campuspo.domain.Poster;
import com.team.campuspo.domain.Timeline;
import com.team.campuspo.service.CampusPoServiceHelper;
import com.team.campuspo.service.ServiceContants;

public abstract class TimelineFragment extends Fragment {
	
	private ListView mListView;
	private PosterListAdapter mListAdapter;	
	private List<Poster> mPosterList;	
	
	private BroadcastReceiver mRequestReceiver;
	//private CampusPoServiceHelper mServiceHelper;
	
	private ViewPager mPager;
	private HeadlinePagerAdapter mPagerAdapter;
	
	private Timer mTimer;
	private Handler mTimeChangedHandler;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prepareData();
	}	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mListView = new ListView(getActivity());
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), PosterActivity.class);
				startActivity(intent);
			}
		});
		mPager = new ViewPager(getActivity());
		mPager.setId(R.id.pager);
		mPager.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,400));
		mPagerAdapter = new HeadlinePagerAdapter(getChildFragmentManager());
		mPager.setAdapter(mPagerAdapter);
		
		
		mListView.addHeaderView(mPager);
		mListAdapter = new PosterListAdapter(getActivity());
		mListView.setAdapter(mListAdapter);
		
		return mListView;
	}
	
	private void prepareData() {
		
		mPosterList = new ArrayList<Poster>();
		mTimeChangedHandler = new Handler() {
			
			@Override
			public void handleMessage(Message msg) {
				changeHeadline();
			}
		};
		mTimer = new Timer();
		
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		IntentFilter filter =new IntentFilter(CampusPoServiceHelper.ACTION_REQUEST_RESULT);
		
		mRequestReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				int resultCode = 
						intent.getIntExtra(CampusPoServiceHelper.REQUEST_RESULT_CODE, 0);
				int requestType =
						intent.getIntExtra(CampusPoServiceHelper.REQUEST_TYPE, 0);
				Bundle data = intent.getBundleExtra(CampusPoServiceHelper.REQUEST_RESULT_DATA);
				
				if(resultCode == 200) {
					if(requestType == ServiceContants.REQUEST_PUBLIC_TIMELINE
							||requestType == ServiceContants.REQUEST_USER_TIMELINE) {
						Timeline timeline = (Timeline) data.getSerializable("timeline");
						update(timeline.getPosters());
						
					}
				}
				
			}
			
		};
		
		this.getActivity().registerReceiver(mRequestReceiver, filter);		
		
		mTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				mTimeChangedHandler.sendEmptyMessage(1);				
			}
			
		}, 0, 2000);
		
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
		if(mRequestReceiver != null)
			this.getActivity().unregisterReceiver(mRequestReceiver);
		
		mTimer.purge();
	}
	
	
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
	
	public void update(List<Poster> posters) {
		
		long maxId = posters.get(posters.size() - 1).getPosterId();
		long sinceId = mPosterList.get(mPosterList.size() - 1).getPosterId();
		
		if(sinceId > maxId)
			mPosterList.addAll(posters);
		else
			mPosterList = posters;
		
		mListAdapter.notifyDataSetChanged();
	}
	//未实现无限循环
	public void changeHeadline() {
		/*int count = mPager.getAdapter().getCount();
		int currentPos = mPager.getCurrentItem();
		
		mPager.setCurrentItem((currentPos + 1) % count);*/
	}
	
	protected abstract void refresh();
	
	private class PosterListAdapter extends BaseAdapter{
		
		private LayoutInflater mInflater;
		
		public PosterListAdapter(Context ctx) {
			mInflater = LayoutInflater.from(ctx);
		}

		@Override
		public int getCount() {
			return mPosterList.size()+8;
		}

		@Override
		public Object getItem(int position) {
			return mPosterList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder holder = null;
			if(convertView == null) {
				
				convertView = mInflater.inflate(R.layout.item_poster_title, parent, false);
				
				holder = new ViewHolder();
				
				holder.mImageViewSponserAvatar = (ImageView) convertView.findViewById(R.id.iv_sponser_profile);
				holder.mTextViewSponserName = (TextView) convertView.findViewById(R.id.tv_sponser_name);
				holder.mTextViewPosterTitle = (TextView) convertView.findViewById(R.id.tv_poster_title);
				holder.mTextViewReleasedTime = (TextView) convertView.findViewById(R.id.tv_released_time);
				holder.mTextViewParticipantNum = (TextView) convertView.findViewById(R.id.tv_participant_number);
				convertView.setTag(holder);
			}else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			// set the value to each ui component;
			
			
			
			return convertView;
		}		
		
		class ViewHolder {
			public ImageView mImageViewSponserAvatar;
			public TextView mTextViewSponserName;
			public TextView mTextViewPosterTitle;
			public TextView mTextViewReleasedTime;
			public TextView mTextViewParticipantNum;
		}
	}

	
	public static class HeadlinePagerAdapter extends FragmentPagerAdapter {
		
		public HeadlinePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int pos) {
			//return HeadlineFragment.newInstance(pos);
			return new HeadlineFragment();
		}

		@Override
		public int getCount() {
			return 3;
		}
		
	}
	
	public static class HeadlineFragment extends Fragment {
		
		@SuppressWarnings("unused")
		private String TAG = HeadlineFragment.class.getSimpleName();
		
		private static final String POSTION = "position";
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
		}	
		
		@Override
		public View onCreateView(LayoutInflater inflater,
							ViewGroup container, Bundle savedInstanceState) {
			
			return inflater.inflate(R.layout.fragment_headline, container, false);
		}
		
		public static HeadlineFragment newInstance(int pos) {
			HeadlineFragment newFragment= new HeadlineFragment();
			Bundle bundle = new Bundle();
			bundle.putInt(HeadlineFragment.POSTION, pos);
			newFragment.setArguments(bundle);
			return newFragment;
			
		}
		
	}
	


}
