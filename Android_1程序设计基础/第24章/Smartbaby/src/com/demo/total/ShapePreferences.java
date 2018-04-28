

package com.demo.total;


import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ShapePreferences extends PreferenceActivity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
	}
}
