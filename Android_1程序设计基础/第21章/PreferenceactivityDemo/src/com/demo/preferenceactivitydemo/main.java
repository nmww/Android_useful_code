package com.demo.preferenceactivitydemo;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.widget.Toast;

public class main extends PreferenceActivity {
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.preference);
	}
	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		Log.i("PreferenceActivity",preference.getKey()+"has been changed");
		if(preference.getKey().equals("sport1")){
			if(((CheckBoxPreference)preference).isChecked() ){
				Log.i("PreferenceActivity",preference.getKey()+"has been checck");
			}
			else
				Log.i("PreferenceActivity",preference.getKey()+"has been uncheck");
			
		}
	
		return true;
	}
	


}