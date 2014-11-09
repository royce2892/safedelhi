package com.andro_yce.safedelhi;

import com.andro_yce.safedelhi.signup.FacebookSignUpActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences.Editor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class SignupActivity extends Activity implements
		android.view.View.OnClickListener, ConnectionCallbacks,
		OnConnectionFailedListener {

	ImageView login_fb, login_go;
	private boolean mSignInClicked;
	TextView skip;

	private ConnectionResult mConnectionResult;

	private static final int RC_SIGN_IN = 0;

	private boolean mIntentInProgress;

	private GoogleApiClient mGoogleApiClient;
	public static final String MyPREFERENCES = "SignUpPrefs";
	public static final String signupkey = "signupkey";

	SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		checkFirstTime();
		setContentView(R.layout.activity_signup);
		login_fb = (ImageView) findViewById(R.id.login_fb);
		login_go = (ImageView) findViewById(R.id.login_go);
		skip = (TextView) findViewById(R.id.skip);
		login_fb.setClickable(true);
		login_go.setClickable(true);
		skip.setClickable(true);
		login_fb.setOnClickListener(this);
		login_go.setOnClickListener(this);
		skip.setOnClickListener(this);
		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this).addApi(Plus.API)
				.addScope(Plus.SCOPE_PLUS_LOGIN)
				.addScope(Plus.SCOPE_PLUS_PROFILE).build();
	}

	@Override
	public void onBackPressed() {
		
	}

	private void checkFirstTime() {
		// TODO Auto-generated method stub
		sharedpreferences = getApplicationContext().getSharedPreferences(
				MyPREFERENCES, Context.MODE_MULTI_PROCESS);
		if (!sharedpreferences.contains("firsttimekey"))
		{
			Intent screen_intent = new Intent(this, ScreenSlideActivity.class);
			startActivity(screen_intent);
			Editor editor = sharedpreferences.edit();
			editor.putBoolean("firsttimekey", false);
			editor.commit();
		}

	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onClick(View v) {

		sharedpreferences = getApplicationContext().getSharedPreferences(
				MyPREFERENCES, Context.MODE_MULTI_PROCESS);
		Editor editor = sharedpreferences.edit();

		switch (v.getId()) {
		case R.id.login_fb:
			editor.putInt(signupkey, 1);

			Intent fb_intent = new Intent(this, FacebookSignUpActivity.class);
			startActivity(fb_intent);
			editor.commit();

			break;
		case R.id.login_go:
			if (!mGoogleApiClient.isConnecting()) {
				mSignInClicked = true;
				resolveSignInError();
			}
			editor.putInt(signupkey, 1);
			editor.commit();

			break;
		case R.id.skip:
			Intent main_intent = new Intent(this, MainActivity.class);
			startActivity(main_intent);
			editor.putInt(signupkey, 0);
			editor.commit();
			break;
		}
	}

	private void resolveSignInError() {
		if (mConnectionResult.hasResolution()) {
			try {
				mIntentInProgress = true;
				startIntentSenderForResult(mConnectionResult.getResolution()
						.getIntentSender(), RC_SIGN_IN, null, 0, 0, 0);
			} catch (SendIntentException e) {
				// The intent was canceled before it was sent. Return to the
				// default state and attempt to connect to get an updated
				// ConnectionResult.
				mIntentInProgress = false;
				mGoogleApiClient.connect();
			}
		}
	}

	public void onConnectionFailed(ConnectionResult result) {
		if (!mIntentInProgress) {
			// Store the ConnectionResult so that we can use it later when the
			// user clicks
			// 'sign-in'.
			mConnectionResult = result;

			if (mSignInClicked) {
				// The user has already clicked 'sign-in' so we attempt to
				// resolve all
				// errors until the user is signed in, or they cancel.
				resolveSignInError();
			}
		}
	}

	protected void onActivityResult(int requestCode, int responseCode,
			Intent intent) {
		if (requestCode == RC_SIGN_IN) {
			if (responseCode != RESULT_OK) {
				mSignInClicked = false;
			}

			mIntentInProgress = false;

			if (!mGoogleApiClient.isConnecting()) {
				mGoogleApiClient.connect();
			}
		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// if(googleSignInClicked)
		mGoogleApiClient.connect();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// if(googleSignInClicked)
		mGoogleApiClient.disconnect();

	}

	@Override
	public void onConnected(Bundle connectionHint) {
		mSignInClicked = false;

		if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
			Person currentPerson = Plus.PeopleApi
					.getCurrentPerson(mGoogleApiClient);
			String personName = currentPerson.getDisplayName();
			String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
			String birthday = currentPerson.getBirthday();
			int gender = currentPerson.getGender();

			Log.i("info", personName + email + birthday + gender);
		}

		Intent main_intent = new Intent(this, MainActivity.class);
		startActivity(main_intent);

	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub

	}

}
