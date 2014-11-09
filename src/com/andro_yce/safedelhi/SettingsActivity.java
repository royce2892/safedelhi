package com.andro_yce.safedelhi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends ActionBarActivity implements
		OnClickListener {

	TextView rate, open;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setTitle("Settings");
		setContentView(R.layout.activity_settings);
		rate = (TextView) findViewById(R.id.textView3);
		open = (TextView) findViewById(R.id.textView4);
		rate.setClickable(true);
		open.setClickable(true);
		rate.setOnClickListener(this);
		open.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.textView3) {
			Toast.makeText(this, "Rate us when we are live on PlayStore", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Intent screen_intent = new Intent(this, ScreenSlideActivity.class);
			startActivity(screen_intent);
		}
	}

}
