package com.demo.bindservice;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
public class LocalCountService extends Service {
	private boolean threadDisable;
	private int count;
	private ServiceBinder serviceBinder = new ServiceBinder();
	public class ServiceBinder extends Binder implements ICountService {
		@Override
		public int getCount() {
			return count;
		}
	}
	@Override
	public IBinder onBind(Intent intent) {
		Log.v("CountService", "onBind");
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
					Log.v("CountService", "Count is " + count);
				}
			}
		}).start();
		/**/
		Log.v("CountService", "oncreate");
	}

	@Override
	public int onStartCommand(Intent intent, int flag, int startid) {
		Log.v("CountService", "onStartCommand");
		return START_STICKY;
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
