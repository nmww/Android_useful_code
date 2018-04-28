package com.demo.startmodeservice;

import java.text.SimpleDateFormat;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class CountIntentService extends IntentService {
	int span;
	public CountIntentService() {
		super("CountIntentService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		Log.v("onHandleIntent", "��������ʱ�� " +getCurrentTime());
		Bundle b=arg0.getBundleExtra("attachment");
		span=b.getInt("waitingtime");
		long endTime = System.currentTimeMillis() + span*1000;
		Log.v("onHandleIntent", "�������ʱ�� " +span);
		while (System.currentTimeMillis() < endTime) {
		synchronized (this) {
		try {
		wait(endTime - System.currentTimeMillis());
		} catch (Exception e) {
		}
		}
		}

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
	
		  Log.v("onDestroy", "��������ʱ�� " +getCurrentTime());
		super.onDestroy();
	}
	public String getCurrentTime(){
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());
		return datetime;
	}

}
