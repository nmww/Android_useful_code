package com.demo.intent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import android.app.Activity;   
import android.content.Intent;
import android.os.Bundle;   
import android.util.Log;
public class ActivityOne extends Activity {   
 @Override  
 protected void onCreate(Bundle savedInstanceState) {   
  super.onCreate(savedInstanceState);   
  setContentView(R.layout.activityone);   
  String tag = "ActivityOne onCreate..";   
  Intent i=getIntent();
  Bundle b=i.getBundleExtra("attachment");
  Log.d(tag, b.getString("username") );   
  Log.d(tag, "han been started " ); 
  ArrayList<Student> al=new ArrayList <Student>();
  al=i.getParcelableArrayListExtra("attachment2");
  if(al!=null){
  Student s = (Student) al.get(0);
     Log.i(tag,"---->"+s.name);   
     Iterator iter = s.scores.entrySet().iterator(); 
     while (iter.hasNext()) { 
    	 Map.Entry entry = (Map.Entry) iter.next(); 
         String key = (String)entry.getKey(); 
         String val = (String)entry.getValue(); 
         Log.i(tag,key.toString()+val.toString());
     } 
  }

 }   
}   
  
