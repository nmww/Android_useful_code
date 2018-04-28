package com.demo.advanceview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class main extends Activity {
    /** Called when the activity is first created. */
	 boolean flag=false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
       setTheme(R.style.CustomRedTheme);
       setContentView(R.layout.main);
    }
}