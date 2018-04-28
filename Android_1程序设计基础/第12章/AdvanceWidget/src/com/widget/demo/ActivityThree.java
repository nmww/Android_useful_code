package com.widget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityThree extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This  tab content from activity three");
        setContentView(textview);
    }
}
