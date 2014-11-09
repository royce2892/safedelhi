package com.andro_yce.safedelhi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FireRescueActivity extends ActionBarActivity {

	ListView list_bomb, list_fire;

	private static String[] bomb = { "South West", "New Delhi", "Delhi",
			"North" };
	private static String[] fire = { "Fire Services Officer", "Head Quarter" };

	private static String[] fire_p = { "23314000", "23312222" };
	private static String[] bomb_p = { "26152810", "23361231", "22512201",
			"23962281" };
	private static String uri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setTitle("Fire Rescue");
		setContentView(R.layout.activity_fire);

		list_fire = (ListView) findViewById(R.id.help_fire);
		list_bomb = (ListView) findViewById(R.id.help_bomb);

		ArrayAdapter<String> adapter_fire = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, fire);
		ArrayAdapter<String> adapter_bomb = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, bomb);

		list_fire.setAdapter(adapter_fire);
		list_bomb.setAdapter(adapter_bomb);
		list_fire.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				uri = "tel:" + fire_p[position].toString();
				call(uri);
			}
		});
		list_bomb.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				uri = "tel:011" + bomb_p[position].toString();
				call(uri);
			}
		});
	}

	public void call(String number) {
		Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
		startActivity(callIntent);
	}

}
