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

public class UserActivity extends ActionBarActivity implements
		OnClickListener {

	private static String user_name;
	private static String user_number;
	private static String user_mail;
	private static String user_gender;
	private Spinner spinner;
	EditText name, number,mail;
	public static final String MyPREFERENCES = "MyPrefs2";
	public static final String namekey = "nameKey";
	public static final String mailkey = "mailKey";
	public static final String mobilekey = "mobilekey";
	public static final String genderkey = "genderKey";
	SharedPreferences sharedpreferences;
	RelativeLayout display, edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setTitle("User Details");
		setContentView(R.layout.activity_user);
		display = (RelativeLayout) findViewById(R.id.display_user);
		edit = (RelativeLayout) findViewById(R.id.edit_user);
		sharedpreferences = getApplicationContext().getSharedPreferences(MyPREFERENCES,
				Context.MODE_MULTI_PROCESS);
		if (sharedpreferences.contains(namekey))
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
		spinner = (Spinner) findViewById(R.id.user_gender_spinner);
		name = (EditText) findViewById(R.id.user_name_edit);
		number = (EditText) findViewById(R.id.user_number_edit);
		mail = (EditText) findViewById(R.id.user_mail_edit);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.gender_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		Button save = (Button) findViewById(R.id.save_user);
		save.setOnClickListener(this);
	}

	private void display() {
		edit.setVisibility(View.GONE);
		display.setVisibility(View.VISIBLE);
		ImageView user_image = (ImageView) findViewById(R.id.user_image);
		TextView user_name = (TextView) findViewById(R.id.user_name);
		TextView user_mail = (TextView) findViewById(R.id.user_mail);
		TextView user_number = (TextView) findViewById(R.id.user_number);
		user_name.setText("Name : "+sharedpreferences.getString(namekey, ""));
		user_number.setText("Number : "+sharedpreferences.getString(mobilekey, ""));
		user_mail.setText("Mail : "+sharedpreferences.getString(mailkey, ""));
		if (sharedpreferences.getString(genderkey, "").contentEquals("Male"))
			user_image.setImageResource(R.drawable.user);
		else
			user_image.setImageResource(R.drawable.female);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.save_user:
			user_gender = spinner.getSelectedItem().toString();
			user_name = name.getText().toString();
			user_number = number.getText().toString();
			user_mail = mail.getText().toString();
			Editor editor = sharedpreferences.edit();
			editor.putString(namekey, user_name);
			editor.putString(mailkey, user_mail);
			editor.putString(mobilekey, user_number);
			editor.putString(genderkey, user_gender);

			editor.commit();
			display();
			break;

		}
	}

}
