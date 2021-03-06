package com.campuspo.fragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.campuspo.BuildConfig;
import com.campuspo.R;
import com.campuspo.activity.PosterActivity;
import com.campuspo.activity.PublishPosterActivity;
import com.campuspo.domain.Poster;
import com.campuspo.domain.Timeline;
import com.campuspo.service.CampusPoServiceHelper;
import com.campuspo.service.ServiceContants;
import com.campuspo.ui.adapter.PosterListAdapter;
import com.campuspo.util.DateUtils;
import com.campuspo.util.ImageLoader;
import com.campuspo.util.Logger;
import com.campuspo.util.Utils;

public abstract class TimelineFragment extends Fragment {

	private static final String TAG = TimelineFragment.class.getSimpleName();

	private ListView mListView;
	private PosterListAdapter mListAdapter;
	private List<Poster> mPosterList;

	private BroadcastReceiver mRequestReceiver;
	private CampusPoServiceHelper mServiceHelper;

	private ViewPager mPager;
	private HeadlinePagerAdapter mPagerAdapter;

	private Timer mTimer;
	private Handler mTimeChangedHandler;

	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(BuildConfig.DEBUG)
			Log.d(TAG, "onCreate() called");
		prepareData();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		setHasOptionsMenu(true);
		mListView = new ListView(getActivity());
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Logger.debug(TAG, "the item on position " + position + "is clicked");
				Intent intent = new Intent(getActivity(), PosterActivity.class);
				Poster poster = mPosterList.get(position - 1);
				intent.putExtra("POSTER", poster); // have to modify
				startActivity(intent);
			}
		});
		mPager = new ViewPager(getActivity());
		mPager.setId(R.id.pager);
		mPager.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 400));
		mPagerAdapter = new HeadlinePagerAdapter(getChildFragmentManager());
		mPager.setAdapter(mPagerAdapter);

		mListView.addHeaderView(mPager);
		mListAdapter = new PosterListAdapter(getActivity(), mPosterList);

		mListView.setAdapter(mListAdapter);

		return mListView;
	}

	private void prepareData() {

		mServiceHelper = CampusPoServiceHelper.getInstance(getActivity());

		// Set the optional menu for fragment

		if (mPosterList == null)
			mPosterList = new ArrayList<Poster>();

		mTimeChangedHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				changeHeadline();
			}
		};

		// create a timer for transfering headlines
		mTimer = new Timer();
		

	}

	@Override
	public void onResume() {
		super.onResume();

		IntentFilter filter = new IntentFilter(
				CampusPoServiceHelper.ACTION_REQUEST_RESULT);

		mRequestReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {

				if (BuildConfig.DEBUG)
					Log.d(TAG, "onReceive() called");

				int requestType = intent.getIntExtra(
						ServiceContants.REQUEST_TYPE, 0);

				if (requestType == ServiceContants.REQUEST_PUBLIC_TIMELINE
						|| requestType == ServiceContants.REQUEST_SPONSOR_TIMELINE) {
					if (BuildConfig.DEBUG)
						Log.d(TAG, "processing...");
					
					int resultCode = intent.getIntExtra(
							CampusPoServiceHelper.REQUEST_RESULT_CODE, 0);
					Bundle data = intent
							.getBundleExtra(CampusPoServiceHelper.REQUEST_RESULT_DATA);

					if (resultCode == 1) {
						Timeline timeline = (Timeline) data
								.getSerializable(ServiceContants.RESULT_SERIALIZABLE);
						if (BuildConfig.DEBUG)
							Log.d(TAG, "get Timeline - finished");

						update(timeline.getPosters());

					} else if (resultCode == -1) {
						Toast.makeText(getActivity(), "无法连接服务器：连接超时",
								Toast.LENGTH_LONG).show();
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

		}, 0, 4000);

	}

	@Override
	public void onPause() {
		super.onPause();

		if (mRequestReceiver != null)
			this.getActivity().unregisterReceiver(mRequestReceiver);

		mTimer.purge();
	}

	@Override
	public void onDetach() {
		super.onDetach();
		// to handle the bug from where a fragment used in another fragment
		try {
			Field childFragmentManager = Fragment.class
					.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);

		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	//=============have to modify
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		super.onCreateOptionsMenu(menu, inflater);

		inflater.inflate(R.menu.menu_timeline_fragment, menu);

		if (Utils.hasHoneycomb()) {
			MenuItem item = menu.getItem(0);
			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		}
		if (Utils.hasHoneycomb()) {
			MenuItem item = menu.getItem(1);
			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_refresh:

			if (BuildConfig.DEBUG)
				Log.d(TAG, "send request for timeline--begin");
			mServiceHelper.getPublicTimeline();
			break;
		case R.id.action_edit:
			Intent intent = new Intent(getActivity(), PublishPosterActivity.class);
			startActivity(intent);
		default:
		}

		return super.onOptionsItemSelected(item);
	}

	public void update(List<Poster> posters) {

		if (BuildConfig.DEBUG)
			Log.d(TAG, "update() called");

		// long maxId = posters.get(posters.size() - 1).getPosterId();
		// long sinceId = mPosterList.get(mPosterList.size() - 1).getPosterId();

		/*
		 * if(sinceId > maxId) mPosterList.addAll(posters); else mPosterList =
		 * posters;
		 */
		mPosterList = posters;
		mListAdapter = new PosterListAdapter(getActivity(), mPosterList);
		mListView.setAdapter(mListAdapter);
		mListAdapter.notifyDataSetChanged();

		if (BuildConfig.DEBUG)
			Log.d(TAG, "update() finished----" + posters.size() + mListView.getCount());
	}

	// 未实现无限循环
	public void changeHeadline() {
		  /*
		  int count = mPager.getAdapter().getCount(); 
		  int currentPos = mPager.getCurrentItem();
		  
		  mPager.setCurrentItem((currentPos + 1) % count);*/
		 
	}

	protected abstract void refresh();

	

	public static class HeadlinePagerAdapter extends FragmentPagerAdapter {

		public HeadlinePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int pos) {
			// return HeadlineFragment.newInstance(pos);
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
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			return inflater.inflate(R.layout.fragment_headline, container,
					false);
		}

		public static HeadlineFragment newInstance(int pos) {
			HeadlineFragment newFragment = new HeadlineFragment();
			Bundle bundle = new Bundle();
			bundle.putInt(HeadlineFragment.POSTION, pos);
			newFragment.setArguments(bundle);
			return newFragment;

		}

	}
}
