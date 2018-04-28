package com.demo.intent;

import android.app.Activity;   
import android.content.Intent;   
import android.os.Bundle;   
import android.util.Log;   
public class ActivityTwo extends Activity {   
 @Override  
 protected void onCreate(Bundle savedInstanceState) {   
  super.onCreate(savedInstanceState);   
  String tag = "ActivityTwo onCreate..";   
  setContentView(R.layout.activitytwo);   
  Intent i = getIntent();   
  Log.d(tag, "intent action : " + i.getAction());   
  
 }   
}  

