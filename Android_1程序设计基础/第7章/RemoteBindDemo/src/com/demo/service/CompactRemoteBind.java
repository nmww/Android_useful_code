package com.demo.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.demo.bindservice.IMyCountBeanService;

public class CompactRemoteBind extends Activity {
    private ServiceConnection serviceConnection = new ServiceConnection() {
   	 private IMyCountBeanService countService;
       @Override
       public void onServiceConnected(ComponentName name, IBinder service) {
       	try{  
           countService = IMyCountBeanService.Stub.asInterface(service); //service;
           Log.v("CompactRemoteBind", "on serivce connected, count is "
                   + countService.getCount().getCount());
       	}catch(RemoteException e){
       		throw new RuntimeException(e);
       	}
       }

       @Override
       public void onServiceDisconnected(ComponentName name) {
           countService = null;
       }

   };

 
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       Log.v("CompactRemoteBind", "oncreate, ");
       setContentView(R.layout.main);
       this.bindService(new Intent("com.demo.service.RemoteCountBeanService"),
               this.serviceConnection, BIND_AUTO_CREATE);
   }

   @Override
   protected void onDestroy() {
         this.unbindService(serviceConnection);       
         Log.v("CompactRemoteBind", "onDestory, ");
         super.onDestroy();      
   }
}

