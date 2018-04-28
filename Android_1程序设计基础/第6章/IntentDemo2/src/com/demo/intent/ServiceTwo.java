package com.demo.intent;

import android.app.Service;   
import android.content.Intent;   
import android.os.IBinder;   
import android.util.Log;   
public class ServiceTwo extends Service{   
 @Override  
 public IBinder onBind(Intent intent) {   
  return null;   
 }   
    
 @Override  
 public void onCreate() {   
  super.onCreate();   
     
  String tag = " ServiceTwo on Create";   
  Log.d(tag,"This is the  service Two...");   
   
 }   
} 

