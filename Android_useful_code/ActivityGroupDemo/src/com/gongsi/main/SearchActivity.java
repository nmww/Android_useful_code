package com.gongsi.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		tv.setText("Search Activity");
		setContentView(tv);
	}

}
