package com.simplewidget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ZoomButton;

public class ZoomButtonDemo extends Activity {  
	private ZoomButton zb;  
	private TextView text;     
	static long size = 12;    
	
	@Override 
	protected void onCreate(Bundle savedInstanceState) {  
		// TODO Auto-generated method stub   
		super.onCreate(savedInstanceState);    
		setContentView(R.layout.zoombutton);  
		zb = (ZoomButton) findViewById(R.id.zoombutton);  
		text = (TextView) findViewById(R.id.text);    
		zb.setOnClickListener(new OnClickListener() {   
			
			public void onClick(View v) {    
				size = size + 2;     
				text.setTextSize(size);     }  
			}); 
		
	}  
	
}
