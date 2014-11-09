package com.andro_yce.safedelhi;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;

import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import android.widget.ListView;
import android.widget.Toast;

public class FiveFriendsActivity extends ActionBarActivity {

	private static ListView mList;
	private static MatrixCursor mMatrixCursor = new MatrixCursor(new String[] {
			"_id", "name", "details" });;
	private static String[] names = new String[1000];
	private static String[] values = new String[1000];
	private static int count = 0;
	public static final String MyPREFERENCES = "MyPrefs3";
	public static final String f1namekey = "f1namekey";
	public static final String f1mobilekey = "f1mobilekey";
	public static final String f2namekey = "f2namekey";
	public static final String f2mobilekey = "f2mobilekey";
	public static final String f3namekey = "f3namekey";
	public static final String f3mobilekey = "f3mobilekey";
	public static final String f4namekey = "f4namekey";
	public static final String f4mobilekey = "f4mobilekey";
	public static final String f5namekey = "f5namekey";
	public static final String f5mobilekey = "f5mobilekey";
	public static int contacts_selected = 0;
	public static String[] contacts_name_selected = new String[5];
	public static String[] contacts_phone_selected = new String[5];
	SharedPreferences sharedpreferences;
	ProgressBar p;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setTitle("Friends");
		setContentView(R.layout.activity_five_friends);
		mList = (ListView) findViewById(R.id.list_five_friends);
		p = (ProgressBar) findViewById(R.id.progressBar1);
		sharedpreferences = getApplicationContext().getSharedPreferences(
				MyPREFERENCES, Context.MODE_MULTI_PROCESS);
		if (sharedpreferences.contains(f1namekey))
			displayselectedcontacts();
		else {
			contacts_selected=0;
			addContacts();
		}

	}

	private void addContacts() {
		mList.setVisibility(View.GONE);
		p.setVisibility(View.VISIBLE);
		p.animate();
		ListViewContactsLoader listViewContactsLoader = new ListViewContactsLoader();
		listViewContactsLoader.execute();
	}

	private class ListViewContactsLoader extends AsyncTask<Void, Void, Cursor> {

		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			p.setVisibility(View.VISIBLE);
			p.animate();
		}

		@Override
		protected Cursor doInBackground(Void... params) {
			Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;

			// Querying the table ContactsContract.Contacts to retrieve all the
			// contacts
			Cursor contactsCursor = getContentResolver().query(contactsUri,
					null, null, null,
					ContactsContract.Contacts.DISPLAY_NAME + " ASC ");

			if (contactsCursor.moveToFirst()) {
				do {
					long contactId = contactsCursor.getLong(contactsCursor
							.getColumnIndex("_ID"));

					Uri dataUri = ContactsContract.Data.CONTENT_URI;

					Cursor dataCursor = getContentResolver().query(dataUri,
							null,
							ContactsContract.Data.CONTACT_ID + "=" + contactId,
							null, null);

					String displayName = "";

					String homePhone = "";
					String mobilePhone = "";
					String workPhone = "";

					if (dataCursor.moveToFirst()) {
						// Getting Display Name
						displayName = dataCursor
								.getString(dataCursor
										.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
						do {

							// Getting Phone numbers
							if (dataCursor
									.getString(
											dataCursor
													.getColumnIndex("mimetype"))
									.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {
								switch (dataCursor.getInt(dataCursor
										.getColumnIndex("data2"))) {

								case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
									mobilePhone = dataCursor
											.getString(dataCursor
													.getColumnIndex("data1"));
									break;

								}
							}

						} while (dataCursor.moveToNext());
						dataCursor.close();
						String details = "";

						if (mobilePhone != null && !mobilePhone.equals(""))
							details = mobilePhone;

						if ((mobilePhone == null || mobilePhone.equals(""))) {
							// no phone number contacts
						} else {
							mMatrixCursor.addRow(new Object[] {
									Long.toString(contactId), displayName,
									details });
							names[count] = displayName;
							values[count++] = details;
						}
					}
				} while (contactsCursor.moveToNext());
				contactsCursor.close();
			}

			return mMatrixCursor;
		}

		@Override
		protected void onPostExecute(Cursor result) {
			displayAndSelectContacts();

		}

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
			p.animate();
			addContacts();
		}
		return super.onOptionsItemSelected(item);
	}

	public void displayAndSelectContacts() {
		p.setVisibility(View.GONE);
		mList.setVisibility(View.VISIBLE);

		LayoutParams params = mList.getLayoutParams();
		params.height = LayoutParams.WRAP_CONTENT;
		String names_new[] = new String[count];
		for (int i = 0; i < count; i++)
			names_new[i] = names[i];
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				FiveFriendsActivity.this, android.R.layout.simple_list_item_1,
				names_new);
		mList.setAdapter(adapter);
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub

				String number = values[position].trim();
				String name = names[position];

				contacts_name_selected[contacts_selected] = name;
				contacts_phone_selected[contacts_selected++] = number;
				if (contacts_selected != 5)
					Toast.makeText(
							FiveFriendsActivity.this,
							name + " added . You need to pick "
									+ (5 - contacts_selected)
									+ " more contacts",1000)
							.show();
				if (contacts_selected == 5) {
					addtosharedpreferences();

					displayselectedcontacts();
				}
			}

		});

	}

	protected void addtosharedpreferences() {
		// TODO Auto-generated method stub
		Editor editor = sharedpreferences.edit();
		editor.putString(f1namekey, contacts_name_selected[0]);
		editor.putString(f1mobilekey, contacts_phone_selected[0]);
		editor.putString(f2namekey, contacts_name_selected[1]);
		editor.putString(f2mobilekey, contacts_phone_selected[1]);
		editor.putString(f3namekey, contacts_name_selected[2]);
		editor.putString(f3mobilekey, contacts_phone_selected[2]);
		editor.putString(f4namekey, contacts_name_selected[3]);
		editor.putString(f4mobilekey, contacts_phone_selected[3]);
		editor.putString(f5namekey, contacts_name_selected[4]);
		editor.putString(f5mobilekey, contacts_phone_selected[4]);
		editor.commit();
	}

	public void displayselectedcontacts() {
		p.setVisibility(View.GONE);
		mList.setVisibility(View.VISIBLE);

		LayoutParams params = mList.getLayoutParams();
		params.height = LayoutParams.WRAP_CONTENT;
		contacts_name_selected[0] = sharedpreferences.getString(f1namekey, "");
		contacts_name_selected[1] = sharedpreferences.getString(f2namekey, "");
		contacts_name_selected[2] = sharedpreferences.getString(f3namekey, "");
		contacts_name_selected[3] = sharedpreferences.getString(f4namekey, "");
		contacts_name_selected[4] = sharedpreferences.getString(f5namekey, "");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				FiveFriendsActivity.this, android.R.layout.simple_list_item_1,
				contacts_name_selected);
		mList.setAdapter(adapter);
	}

}