package com.andro_yce.safedelhi;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class SOSActivity extends ActionBarActivity implements OnClickListener,
		LocationListener {
	ImageView real, test;
	String[] vehicle_details = new String[2];
	String model, number;
	String name, mobile, mail;
	String f1, f2, f3, f4, f5;
	String p1, p2, p3, p4, p5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setTitle("SOS");
		setContentView(R.layout.activity_sos);
		Toast.makeText(
				this,
				"Click on the red button to send for help or blue button to test our app ",
				1200).show();
		real = (ImageView) findViewById(R.id.sos_real);
		test = (ImageView) findViewById(R.id.sos_test);
		real.setClickable(true);
		test.setClickable(true);
		real.setOnClickListener(this);
		test.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		getAllDetails();
		if (v.getId() == R.id.sos_real) {
			sendrealtext();
		} else {
			sendtesttext();
		}
	}

	private void sendrealtext() {
		// TODO Auto-generated method stub
		String location = getLocation();
		if (location == null)
			location = "[ Location couldn't be obtained ]";
		String message = " Hi dear , I [ Vehicle -> " + model
				+ " RC number -> " + number + " ] met with an accident at "
				+ location + ". Reach out to me ASAP";
		sendSms(p1, message);
		sendSms(p2, message);
		sendSms(p3, message);
		sendSms(p4, message);
		sendSms(p5, message);

	}

	private String getLocation() {
		// TODO Auto-generated method stub
		int flag = 0;
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, false);
		Location location = new Location(provider);
	
		if (provider != null && !provider.equals("")) {

			// Get the location from the given provider
			location = locationManager.getLastKnownLocation(provider);

			locationManager.requestLocationUpdates(provider, 20000, 1, this);

			if (location != null)
				onLocationChanged(location);
			else {

				flag = 1;
			}

		} else {
			flag = 1;

		}
		if (flag == 0) {
			return ("longitude = " + location.getLongitude() + " latitude = " + location
					.getLatitude());
		} else
			return null;
 
	}

	private void sendtesttext() {
		// TODO Auto-generated method stub
		String m1 = " Dear "
				+ f1
				+ " , "
				+ name
				+ " started using the app SAFE DELHI and has added you as one of his five emergency contacts . Have a good day";
		String m2 = " Dear "
				+ f2
				+ " , "
				+ name
				+ " started using the app SAFE DELHI and has added you as one of his five emergency contacts . Have a good day";
		String m3 = " Dear "
				+ f3
				+ " , "
				+ name
				+ " started using the app SAFE DELHI and has added you as one of his five emergency contacts . Have a good day";
		String m4 = " Dear "
				+ f4
				+ " , "
				+ name
				+ " started using the app SAFE DELHI and has added you as one of his five emergency contacts . Have a good day";
		String m5 = " Dear "
				+ f5
				+ " , "
				+ name
				+ " started using the app SAFE DELHI and has added you as one of his five emergency contacts . Have a good day";
		sendSms(p1, m1);
		sendSms(p2, m2);
		sendSms(p3, m3);
		sendSms(p4, m4);
		sendSms(p5, m5);

	}

	private void sendSms(String phone_no, String message) {
		String n = phone_no.trim().replace("-", "").replace("+", "").replace("*", "").replace("#", "");
		String number;
		if (n.length() > 10)
			number = n.substring(n.length() - 10,
					n.length());
		else
			number = n;
	
		Log.i("data-p",number);
		if(!(number.length()<10))
		{
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(number, null, message, null, null);
			Toast.makeText(getApplicationContext(), "SMS sent.",
					Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(SOSActivity.this, "SMS faild, please try again.",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		}
		
	}

	private void getAllDetails() {
		SharedPreferences sp;
		sp = getApplicationContext().getSharedPreferences("MyPrefs",
				Context.MODE_MULTI_PROCESS);
		model = sp.getString("modelKey", "");
		number = sp.getString("numberKey", "");
		sp = getApplicationContext().getSharedPreferences("MyPrefs2",
				Context.MODE_MULTI_PROCESS);
		name = sp.getString("nameKey", "");
		mobile = sp.getString("mobilekey", "");
		mail = sp.getString("mailKey", "");
		sp = getApplicationContext().getSharedPreferences("MyPrefs3",
				Context.MODE_MULTI_PROCESS);
		f1 = sp.getString("f1namekey", "");
		f2 = sp.getString("f2namekey", "");
		f3 = sp.getString("f3namekey", "");
		f4 = sp.getString("f4namekey", "");
		f5 = sp.getString("f5namekey", "");
		p1 = sp.getString("f1mobilekey", "");
		p2 = sp.getString("f2mobilekey", "");
		p3 = sp.getString("f3mobilekey", "");
		p4 = sp.getString("f4mobilekey", "");
		p5 = sp.getString("f5mobilekey", "");
		
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
