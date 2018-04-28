package com.widget.demo;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListViewDemo2 extends Activity {

	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		listView = new ListView(this);

		Cursor cursor = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

		startManagingCursor(cursor);

		SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_expandable_list_item_1, cursor,

				new String[] { ContactsContract.Contacts.DISPLAY_NAME },

				new int[] { android.R.id.text1 });
		listView.setAdapter(listAdapter);

		setContentView(listView);

	}

}
