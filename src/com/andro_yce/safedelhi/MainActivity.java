package com.andro_yce.safedelhi;

import com.andro_yce.safedelhi.adapters.CustomGrid;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manageAndHandleGrid();
	}

	private void manageAndHandleGrid() {
		GridView gridview = (GridView) findViewById(R.id.main_menu_options);
		CustomGrid adapter = new CustomGrid(MainActivity.this, options,
				mThumbIds);
		gridview.setAdapter(adapter);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				handleItemClick(position);
			}

		});
	}

	private void handleItemClick(int position) {
		Intent intent = new Intent();
		switch (position) {
		case 0:
			intent = new Intent(this, EmergencyContactsActivity.class);
			break;
		case 1:
			intent = new Intent(this, FiveFriendsActivity.class);
			break;
		case 2:
			intent = new Intent(this, HospitalsActivity.class);
			break;
		case 3:
			intent = new Intent(this, FireRescueActivity.class);
			break;
		case 4:
			intent = new Intent(this, SOSActivity.class);
			break;
		case 5:
			intent = new Intent(this, VehicleActivity.class);
			break;
		case 6:
			intent = new Intent(this, CheckListActivity.class);
			break;
		case 7:
			intent = new Intent(this, UserActivity.class);
			break;
		case 8:
			intent = new Intent(this, SettingsActivity.class);
			break;

		}
		startActivity(intent);
	}

	

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_input_delete)
				.setTitle("Closing Application")
				.setMessage("Are you sure you want to close this activity?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								closeapp();
							}

						}).setNegativeButton("No", null).show();
	}

	protected void closeapp() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	private int[] mThumbIds = { R.drawable.help, R.drawable.contacts,
			R.drawable.blood, R.drawable.fire, R.drawable.sos,
			R.drawable.vehicle, R.drawable.checklist, R.drawable.user,
			R.drawable.settings };

	private String[] options = { "Help", "Contacts", "Blood", "Fire", "SOS",
			"Vehicle", "CheckList", "User", "Settings" };
}
