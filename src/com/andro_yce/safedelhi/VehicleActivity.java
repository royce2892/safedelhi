package com.andro_yce.safedelhi;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class VehicleActivity extends ActionBarActivity implements
		OnClickListener {

	private static String vehicle_type;
	private static String vehicle_model;
	private static String vehicle_rc_number;
	private Spinner spinner;
	EditText model, number;
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String typekey = "typeKey";
	public static final String modelkey = "modelKey";
	public static final String numberkey = "numberKey";
	SharedPreferences sharedpreferences;
	RelativeLayout display, edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setTitle("Vehicle Details");
		setContentView(R.layout.activity_vehicle);
		display = (RelativeLayout) findViewById(R.id.display_vehicle);
		edit = (RelativeLayout) findViewById(R.id.edit_vehicle);
		sharedpreferences = getApplicationContext().getSharedPreferences("MyPrefs",
				Context.MODE_MULTI_PROCESS);
		if (sharedpreferences.contains(typekey))
			display();
		else
			edit();

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_edit) {
			edit();
		}
		return super.onOptionsItemSelected(item);
	}
	private void edit() {
		edit.setVisibility(View.VISIBLE);
		display.setVisibility(View.GONE);
		spinner = (Spinner) findViewById(R.id.vehicle_type_spinner);
		model = (EditText) findViewById(R.id.vehicle_name_edit);
		number = (EditText) findViewById(R.id.vehicle_number_edit);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.vehicle_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		Button save = (Button) findViewById(R.id.save);
		save.setOnClickListener(this);
	}

	private void display() {
		edit.setVisibility(View.GONE);
		display.setVisibility(View.VISIBLE);
		ImageView vehicle_image = (ImageView) findViewById(R.id.vehicle_image);
		TextView vehicle_model = (TextView) findViewById(R.id.vehicle_name);
		TextView vehicle_number = (TextView) findViewById(R.id.vehicle_number);
		vehicle_model.setText("Model : "+sharedpreferences.getString(modelkey, ""));
		vehicle_number.setText("Number : "+sharedpreferences.getString(numberkey, ""));
		if (sharedpreferences.getString(typekey, "").contentEquals("4-Wheeler"))
			vehicle_image.setImageResource(R.drawable.car);
		else
			vehicle_image.setImageResource(R.drawable.bike);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.save:
			vehicle_type = spinner.getSelectedItem().toString();
			vehicle_model = model.getText().toString();
			vehicle_rc_number = number.getText().toString();
			Editor editor = sharedpreferences.edit();
			editor.putString(typekey, vehicle_type);
			editor.putString(modelkey, vehicle_model);
			editor.putString(numberkey, vehicle_rc_number);

			editor.commit();
			display();
			break;

		}
	}
	
	

}
