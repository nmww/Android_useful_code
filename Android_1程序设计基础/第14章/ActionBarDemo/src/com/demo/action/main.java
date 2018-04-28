package com.demo.action;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

public class main extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		new MenuInflater(this).inflate(R.menu.menu, menu);

		MenuItem actionItem = menu.add("·ÖÏí£¨¶¯Ì¬Ìí¼Ó£©");
		actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		actionItem.setIcon(android.R.drawable.ic_menu_share);
		return (super.onCreateOptionsMenu(menu));

	}
	@Override
	protected void onStart() {
	    super.onStart();
	    ActionBar actionBar = this.getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
					Toast.makeText(this, "Selected Item: " + item.getTitle(),
					Toast.LENGTH_SHORT).show();
			return true;
		}
	
}
