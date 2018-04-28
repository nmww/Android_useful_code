package com.demo.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;



public class RemoteCountBeanService extends Service {

    private boolean threadDisable;

    private int count;

    private IMyCountBeanService.Stub serviceBinder = new IMyCountBeanService.Stub() {

        @Override
        public CountBean getCount() throws RemoteException {
        	CountBean cn=new CountBean();
        	cn.setCount(count);//用当前服务中的值为CountBean赋值
        	
            return cn;
        }
    };



    @Override

    public IBinder onBind(Intent intent) {
    	   Log.v("CountBeanService", "onBind");
        return serviceBinder;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {

            @Override
          public void run() {
                while (!threadDisable) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    count++;
                    Log.v("CountBeanService", "Count is " + count);
                }
            }
        }).start();
         /**/
        Log.v("CountBeanService", "oncreate");
    }
    @Override
    public int onStartCommand(Intent intent,int flag,int startid) {
    	 Log.v("CountBeanService", "onStartCommand");
    	 
    	
        
    	return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.threadDisable = true;
        Log.v("CountBeanService", "on destroy");
    }

    

}