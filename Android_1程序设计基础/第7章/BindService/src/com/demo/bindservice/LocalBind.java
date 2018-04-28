package com.demo.bindservice;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
public class LocalBind extends Activity {
	private ServiceConnection serviceConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			LocalcountService = (ICountService) service;
			Log.v("CountService", "on serivce connected, count is "
					+ LocalcountService.getCount());
		}
		@Override
		public void onServiceDisconnected(ComponentName name) {
			LocalcountService = null;
		}
	};
	private ICountService LocalcountService;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this.bindService(new Intent("com.demo.service.LocalCountService"),
				this.serviceConnection, BIND_AUTO_CREATE);
	}
	@Override
	protected void onDestroy() {
		this.unbindService(serviceConnection);
		super.onDestroy();
	}
}
