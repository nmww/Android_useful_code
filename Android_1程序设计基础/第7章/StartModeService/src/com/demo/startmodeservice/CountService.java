package com.demo.startmodeservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class CountService extends Service {
    private boolean threadDisable;
    private int count;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
         Log.v("CountService", "oncreate");
    }
    @Override
    public int onStartCommand(Intent intent,int flag,int startid) {
    	 Log.v("CountService", "onStartCommand");
    	 new Thread(new Runnable() {

             @Override
           public void run() {
                 while (!threadDisable) {
                     try {
                         Thread.sleep(1000);
                     } catch (InterruptedException e) {
                     }
                     count++;
                     Log.v("CountService", "Count is " + count);
                 }
             }
         }).start();

    	return START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.threadDisable = true;
        Log.v("CountService", "on destroy");
    }
    public int getCount() {
        return count;
    }

}

