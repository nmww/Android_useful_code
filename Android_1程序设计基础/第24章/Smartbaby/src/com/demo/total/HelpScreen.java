
package com.demo.total;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class HelpScreen extends Activity implements OnTouchListener{
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView body = new TextView(this);
		body.setText(R.string.help_body);
		body.setTextSize((float)30); 
		body.setOnTouchListener(this);
		setContentView(body);
	}
	
	public boolean onTouch(View v, MotionEvent e) {
		finish();
		return true;
	}
}
