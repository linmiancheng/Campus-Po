package com.team.campuspo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.team.campuspo.R;
import com.team.campuspo.app.CampusPoApplication;
import com.team.campuspo.domain.User;
import com.team.campuspo.utils.CommonThreadTask;

public class LoginActivity extends FragmentActivity{
	
	public static final String TAG = "LoginActivity";
	
	private EditText mEditTextEmail;
	private EditText mEditTextPassword;
	private Button mButtonLogin;
	private Button mButtonRegister;
	private TextView mTextViewForget;
	
	private LoginTask mLoginTask;
	
	@Override
	public void onCreate(Bundle outState) {
		super.onCreate(outState);
		setContentView(R.layout.activity_login);
		
		SharedPreferences preferences = 
				getSharedPreferences("preferences", Context.MODE_PRIVATE);
		boolean stayLogin = preferences.getBoolean("stayLogin", false);
		if(stayLogin == true)
			startMainActivity();
		//SharedPreferences.Editor editor = preferences.edit();
		
		
		prepareData();
		populateUi();
	}
	
	private void startMainActivity() {
		Intent intent = new Intent(LoginActivity.this, MainActivity.class);
		this.startActivity(intent);
		this.finish();		
	}
	
	public void setStayLogin(boolean stayLogin) {
		SharedPreferences sharedPref = 
				this.getSharedPreferences("preferences", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putBoolean("stayLogin", stayLogin);
		editor.commit();
	}

	private void prepareData() {
		mEditTextEmail = (EditText) findViewById(R.id.et_email);
		mEditTextPassword = (EditText) findViewById(R.id.et_password);
		mButtonLogin = (Button) findViewById(R.id.btn_login);
		mButtonRegister = (Button) findViewById(R.id.btn_register);
		mTextViewForget = (TextView) findViewById(R.id.tv_forget_password);
		
		RetainFragment retainFragment = findOrCreateRetainFragment(getSupportFragmentManager());
		mLoginTask = (LoginTask) retainFragment.getObject();
	}
	
	private void populateUi() {
		mButtonLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if("".equals(getEmail()))
					Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
				else if("".equals(getPassword()))
					Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
				else {
					if(mLoginTask == null || 
							mLoginTask.getStatus() == CommonThreadTask.Status.FINISHED) {
						mLoginTask = new LoginTask(getEmail(), getPassword());
						RetainFragment retainFragment = findOrCreateRetainFragment(getSupportFragmentManager());
						retainFragment.setObject(mLoginTask);
						mLoginTask.execute();
					}
						
				}
				
			}
		});
		
		mButtonRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setStayLogin(true);
				startMainActivity();
				
			}
		});
		
		mTextViewForget.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
	}
	

	private String getEmail() {
		return mEditTextEmail.getText().toString().trim();
	}
	
	private String getPassword() {
		return mEditTextPassword.getText().toString().trim();
	}
	
	private class LoginTask extends CommonThreadTask<Void, Void, User> {

		private String mEmail;
		private String mPassword;
		
		public LoginTask(String email, String password) {
			mEmail = email;
			mPassword = password;
		}
		
		@Override
		protected User doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;//CampusPo.doLogin(mEmail, mPassword);
		}
		
		
		@Override
		public void onPostExecute(User user) {
			if(user == null){
				Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();;
			}else {
				CampusPoApplication.setUser(user);
				//have to modify
				setStayLogin(true);
				startMainActivity();
				
			}
		}
	}
	
	public RetainFragment findOrCreateRetainFragment(FragmentManager fm) {
		RetainFragment retainFragment = (RetainFragment) fm.findFragmentByTag(TAG);
	
		if(retainFragment == null) {
			retainFragment = new RetainFragment();
			fm.beginTransaction().add(retainFragment, TAG).commit();
		}
		
		return retainFragment;
	}
	
	/**
     * A simple non-UI Fragment that stores a single Object and is retained over configuration
     * changes. It will be used to retain the ImageCache object.
     */
    public static class RetainFragment extends Fragment {
        private Object mObject;

        /**
         * Empty constructor as per the Fragment documentation
         */
        public RetainFragment() {}

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Make sure this Fragment is retained over a configuration change
            setRetainInstance(true);
        }

        /**
         * Store a single object in this Fragment.
         *
         * @param object The object to store
         */
        public void setObject(Object object) {
            mObject = object;
        }

        /**
         * Get the stored object.
         *
         * @return The stored object
         */
        public Object getObject() {
            return mObject;
        }
    }
}
