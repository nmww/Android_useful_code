package com.demo.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class RemoteCountService extends Service {

	private boolean threadDisable;
	private int count;
	private IMyCountService.Stub serviceBinder = new IMyCountService.Stub() {

		@Override
		public int getCount() throws RemoteException {
			return count;
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		Log.v("RemoteCountService", "onBind");
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
					Log.v("RemoteCountService", "Count is " + count);
				}
			}
		}).start();
		/**/
		Log.v("RemoteCountService", "oncreate");
	}

	@Override
	public int onStartCommand(Intent intent, int flag, int startid) {
		Log.v("RemoteCountService", "onStartCommand");

		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		this.threadDisable = true;
		Log.v("RemoteCountService", "on destroy");
	}

	public int getCount() {
		return count;
	}

}
