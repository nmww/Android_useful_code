package com.widget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SlidingDrawer;
import android.widget.TextView;

public class SlidingDrawerDemo extends Activity {
	private SlidingDrawer mDrawer;
	private ImageButton imbg;

	private TextView tv;
	
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  // TODO Auto-generated method stub
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.slidingdrawer);
	  
	  imbg=(ImageButton)findViewById(R.id.handle);
	  mDrawer=(SlidingDrawer)findViewById(R.id.slidingdrawer);
	  tv=(TextView)findViewById(R.id.tv);
	  
	  mDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener()
	  {
	   
	   public void onDrawerOpened() {
	  
	    imbg.setImageResource(R.drawable.close);
	   }
	   
	  });
	  
	  mDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener(){
	    public void onDrawerClosed() {
	  
	    imbg.setImageResource(R.drawable.open);
	   }
	   
	  });
	  
	  mDrawer.setOnDrawerScrollListener(new SlidingDrawer.OnDrawerScrollListener(){
	 
	   public void onScrollEnded() {
	   
	   }
	 
	   public void onScrollStarted() {
	   
	   }
	   
	  });
	 
	 }
	}

