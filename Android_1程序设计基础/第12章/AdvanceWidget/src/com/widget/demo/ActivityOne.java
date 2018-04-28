package com.widget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityOne extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This  tab content from activity one");
        setContentView(textview);
    }
}

