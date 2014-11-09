package com.andro_yce.safedelhi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;

public class SplashActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.activity_splash);
		Handler mHandler = new Handler();
		 mHandler.postDelayed(new Runnable() {
	            public void run() {
	                check();
	            }
	        }, 2000);
		
	}

	protected void check() {
		// TODO Auto-generated method stub
		SharedPreferences sp = getApplicationContext().getSharedPreferences("SignUpPrefs", Context.MODE_MULTI_PROCESS);
		int signup_done = sp.getInt("signupkey", 0);

		if (signup_done==1)
		{
			Intent main_intent = new Intent(this, MainActivity.class);
			startActivity(main_intent);
		}
		else
		{
			Intent signup_intent = new Intent(this, SignupActivity.class);
			startActivity(signup_intent);
		}	
	}

	
}
