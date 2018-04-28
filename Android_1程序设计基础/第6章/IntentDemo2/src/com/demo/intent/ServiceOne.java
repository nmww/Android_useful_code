package com.demo.intent;

import android.app.Service;   
import android.content.Intent;   
import android.os.IBinder;   
import android.util.Log;   
public class ServiceOne extends Service{   
 @Override  
 public IBinder onBind(Intent intent) {   
  return null;   
 }   
    
 @Override  
 public void onCreate() {   
  super.onCreate();   
     
  String tag = " ServiceOne on Create";   
  Log.d(tag,"This is the  service One...");   
 }   
} 
