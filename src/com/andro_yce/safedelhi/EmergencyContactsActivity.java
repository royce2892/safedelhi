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

public class EmergencyContactsActivity extends ActionBarActivity {

	ListView list_general, list_police;
	private static String[] general = { "Police", "Fire", "Ambulance" };
	private static String[] police = { "Central District", "North District",
			"South District", "South-West District", "East District",
			"North-West District", "North-East District", "West District",
			"New Delhi District" };

	private static String[] police_p = { "23261377", "23937012", "26482871",
			"26152781", "22305577", "27229835", "22825655", "25447100",
			"23344452" };
	private static String[] general_p = { "100", "101", "102" };

	private static String uri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setTitle("Emergency DIAL");
		setContentView(R.layout.activity_emergency_contacts);

		list_general = (ListView) findViewById(R.id.help_emergency);
		list_police = (ListView) findViewById(R.id.help_police);

		ArrayAdapter<String> adapter_general = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, general);
		ArrayAdapter<String> adapter_police = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, police);

		list_general.setAdapter(adapter_general);
		list_police.setAdapter(adapter_police);
		list_general.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				uri = "tel:" + general_p[position].toString();
				call(uri);
			}
		});
		list_police.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				uri = "tel:011" + police_p[position].toString();
				call(uri);
			}
		});
	}

	public void call(String number) {
		Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
		startActivity(callIntent);
	}

}
