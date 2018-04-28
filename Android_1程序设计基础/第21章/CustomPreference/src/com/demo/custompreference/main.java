package com.demo.custompreference;


import android.os.Bundle;
import android.preference.PreferenceActivity;

public class main extends PreferenceActivity {
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.preference);
	}
}