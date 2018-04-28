  package com.widget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityTwo extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This  tab content from activity two");
        setContentView(textview);
    }
}
