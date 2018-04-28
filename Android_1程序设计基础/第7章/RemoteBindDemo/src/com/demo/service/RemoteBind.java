package com.demo.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.demo.bindservice.IMyCountService;

public class RemoteBind extends Activity {
	private ServiceConnection serviceConnection = new ServiceConnection() {
		private IMyCountService remotecountService;

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			try {
				remotecountService = IMyCountService.Stub.asInterface(service);
				Log.v("RemoteBind", "on serivce connected, count is "
						+ remotecountService.getCount());
			} catch (RemoteException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			remotecountService = null;
		}

	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this.bindService(new Intent("com.demo.service.RemoteCountService"),
				this.serviceConnection, BIND_AUTO_CREATE);
	}

	@Override
	protected void onDestroy() {
		this.unbindService(serviceConnection);
		super.onDestroy();
	}
}
