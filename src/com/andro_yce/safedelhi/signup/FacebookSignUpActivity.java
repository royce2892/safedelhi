package com.andro_yce.safedelhi.signup;

import com.andro_yce.safedelhi.MainActivity;
import com.andro_yce.safedelhi.R;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebookConfiguration;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnProfileListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class FacebookSignUpActivity extends Activity {

	SimpleFacebook mSimpleFacebook;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    mSimpleFacebook = SimpleFacebook.getInstance(this);
	    skip_to_fb_signup();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    mSimpleFacebook.onActivityResult(this, requestCode, resultCode, data); 
	    super.onActivityResult(requestCode, resultCode, data);
	    Intent intent = new Intent(this, MainActivity.class);
	    startActivity(intent);

	}
	
    private void skip_to_fb_signup() {
    	// add more permission to access all details
		Permission[] permissions = new Permission[] {
	//		    Permission.USER_PHOTOS,
			    Permission.EMAIL,
	//		    Permission.PUBLISH_ACTION
			    Permission.USER_BIRTHDAY,
			    Permission.USER_HOMETOWN,
			    Permission.USER_LOCATION,
			    
			};
		SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
	    .setAppId("853693581355555")
	    .setNamespace("safedelhi")
	    .setPermissions(permissions)
	    .build();
		SimpleFacebook.setConfiguration(configuration);
		mSimpleFacebook.login(onLoginListener);

	}
	
	OnLoginListener onLoginListener = new OnLoginListener() {
	    @Override
	    public void onLogin() {
	    	mSimpleFacebook.getProfile(onProfileListener);

	    }

	    @Override
	    public void onNotAcceptingPermissions(Permission.Type type) {
	        // user didn't accept READ or WRITE permission
	    }

		@Override
		public void onThinking() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onException(Throwable arg0) {
			// TODO Auto-generated method stub
	    	Log.i("FB Login","0");
	
		}

		@Override
		public void onFail(String arg0) {
			// TODO Auto-generated method stub
	    	Log.i("FB Login","0");

		}

	  
	};
	OnProfileListener onProfileListener = new OnProfileListener() {         
	    @Override
	    public void onComplete(Profile profile) {
	      /*  Log.i("fb", "My profile id = " + profile.getId());
	        Log.i("fb", "My first name is = " + profile.getFirstName());
	        Log.i("fb", "email = " + profile.getEmail());
	        Log.i("fb", "last name = " + profile.getLastName());
	        Log.i("fb", "bio = " + profile.getBio());
	        Log.i("fb", "birthday = " + profile.getBirthday());
	        Log.i("fb", "gender = " + profile.getGender());
	        Log.i("fb", "home = " + profile.getHometown());
	        Log.i("fb", "pic = " + profile.getPicture());
	        Log.i("fb", "r-status = " + profile.getRelationshipStatus());
	        Log.i("fb", "religion = " + profile.getReligion());
	        Log.i("fb", "username = " + profile.getUsername());
	        Log.i("fb", "edu = " + profile.getEducation());
	        Log.i("fb", "work = " + profile.getWork());*/
	    	String name = profile.getFirstName();
	    	String email = profile.getEmail();
	    	String birthday = profile.getBirthday();
	    	String gender = profile.getGender();
	    	String location = profile.getHometown();
	    	Log.i("info = ",name+email+ birthday+gender+location);

	        
	        
	    }

	    
	};
	

}
	
	


