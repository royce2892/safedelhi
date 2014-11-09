package com.andro_yce.safedelhi;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class CheckListActivity extends ActionBarActivity{

	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setTitle("Checklist");
		setContentView(R.layout.activity_check_list);
		tv= (TextView) findViewById(R.id.check);
		tv.setText(R.string.checklist);
		tv.setMovementMethod(new ScrollingMovementMethod());

	}

	
}
