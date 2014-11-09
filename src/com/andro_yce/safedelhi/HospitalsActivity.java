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

public class HospitalsActivity extends ActionBarActivity {

	ListView list_blood, list_eye;

	private static String[] blood = { "AIIMS, Aurobindo Marg",
			"Blood Bank Organisation.Pusa Road", "Indian Red Cross Society",
			"White Cross Bank,East of Kailash", "Apollo Blood Bank",
			"Sunil Blood Bank,Kotla Mubarkakpur",
			"Balaji Blood Bank,Karol Bagh" };
	private static String[] eye = { "Guru Nanak Eye Centre",
			"National Eye Bank (AIIMS)", "AIIMS(Emergency)",
			"Rotary Delhi Centre Eye Bank", "Sir Ganga Ram Hospital",
			"Apollo Eye Bank", "A-Edward Maumenee- Eye Bank" };

	private static String[] blood_p = { "26561123", "25752924", "23711551",
			"26831063", "26825707", "24697646", "25729849" };
	private static String[] eye_p = { "23234612", "22651123", "26569461",
			"25781837", "25721800", "26925858", "26291951" };

	private static String uri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setTitle("Blood Bank Help");
		setContentView(R.layout.activity_hospitals);

		list_blood = (ListView) findViewById(R.id.help_blood);
		list_eye = (ListView) findViewById(R.id.help_eye);

		ArrayAdapter<String> adapter_blood = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, blood);
		ArrayAdapter<String> adapter_eye = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, eye);

		list_blood.setAdapter(adapter_blood);
		list_eye.setAdapter(adapter_eye);
		list_blood.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				uri = "tel:" + blood_p[position].toString();
				call(uri);
			}
		});
		list_eye.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				uri = "tel:011" + eye_p[position].toString();
				call(uri);
			}
		});
	}

	public void call(String number) {
		Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
		startActivity(callIntent);
	}

}
