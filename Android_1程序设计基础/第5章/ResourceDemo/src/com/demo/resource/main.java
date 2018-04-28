package com.demo.resource;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.getWindow().setBackgroundDrawableResource(R.color.mycolor);
        TextView tv=(TextView) this.findViewById(R.id.tv);
        String temp = this.getString(R.string.format_string);
        String substitutedString = String.format(temp,"Hello","Android");
        tv.setText(substitutedString);

       
    }
}