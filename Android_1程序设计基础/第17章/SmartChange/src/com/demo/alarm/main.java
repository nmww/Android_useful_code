package com.demo.alarm;

import java.util.Date;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class main extends Activity {
	String TAG="smartchange";
	protected boolean isChange;
	
	AlarmManager alarms;
	
	TimePicker tp,tp1 ;
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        alarms = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        tp = (TimePicker)findViewById(R.id.timePkr);
        tp.setIs24HourView(true);
        tp1 = (TimePicker)findViewById(R.id.timePkr2);
        tp1.setIs24HourView(true);
        
        //监视按钮变化
        RadioGroup group = (RadioGroup) findViewById(R.id.menu);
        group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                                if (isChange)
                                        return;
                                
                                switch (checkedId) {
                                        case R.id.ring_and_vibrate: ringAndVibrate(); break;
                                        case R.id.ring: ring(); break;
                                        case R.id.vibrate: vibrate(); break;
                                        case R.id.silent: silent(); break;
                                }
                                
		                        RadioButton radio = (RadioButton) findViewById(checkedId);
		                        if (radio != null)
		                        	radio.setTextSize(30);
                                
		                        main.this.waitClosing();
                        }
        		});
        //RadioButton添加监听器
        for (int i = 0, l = group.getChildCount(); i < l; i++) {
                RadioButton radio = (RadioButton) group.getChildAt(i);
                radio.setOnTouchListener(new OnTouchListener() {
                                public boolean onTouch(View v, MotionEvent event) {
                                		RadioButton radio = (RadioButton) v;
                                        if (!radio.isChecked())
                                                return false;
                                        
                                        radio.setTextSize(30);
                                        main.this.waitClosing();
                                        
                                        return false;
                                }
                });
        }
    }
    
	@Override
	protected void onResume() {
		super.onResume();
		updateRadioGroup();
	}
	//更新情景模式
    protected void updateRadioGroup() {
        int checkedId = currentMode();
        RadioButton checked = (RadioButton) findViewById(checkedId);
        isChange = true;
        checked.setChecked(true);
        isChange = false;
    }
    //取得当前情景模式
    protected int currentMode() {
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        
        switch (audio.getRingerMode()) {
                case AudioManager.RINGER_MODE_SILENT: return R.id.silent;
                case AudioManager.RINGER_MODE_VIBRATE: return R.id.vibrate;
        }
        
        if (audio.shouldVibrate(AudioManager.VIBRATE_TYPE_RINGER))
                return R.id.ring_and_vibrate;
        
        return R.id.ring;
    }
    protected String returnModeString(int oldmode){
    	switch(oldmode){
    	case R.id.silent: return AlarmReceiver.SILENT_CHANGED;
    	case R.id.vibrate:return AlarmReceiver.SILENT_CHANGED;
    	case R.id.ring_and_vibrate: return AlarmReceiver.RV_CHANGED;
    	case R.id.ring:return AlarmReceiver.RING_CHANGED;
    	}
    	return AlarmReceiver.RING_CHANGED;
    	
    }
    //铃声和震动
    protected void ringAndVibrate() {
    	int temp=currentMode();
    	Log.e(TAG,""+R.id.ring_and_vibrate);
    	Intent intent = new Intent(AlarmReceiver.RV_CHANGED);
    	intent.putExtra("checkedId", R.id.ring_and_vibrate);
    	
    	PendingIntent alarmIntent = PendingIntent.getBroadcast(this,
				AlarmReceiver.REQUEST_CODE,
				intent,
				0);
    	Log.e(TAG,""+intent);
    	alarms.set(AlarmManager.RTC_WAKEUP,getTime(), alarmIntent);
    	///////////////////////////////////////
    	//发送定时还原警告
    	Intent intent2 = new Intent(returnModeString(temp));
    	intent2.putExtra("checkedId", temp);
    	
    	PendingIntent alarmIntent2 = PendingIntent.getBroadcast(this,
    			AlarmReceiver.REQUEST_CODE+1,
				intent2,
				0);
    	Log.e(TAG,""+intent2);
    	AlarmManager alarm2=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
    	alarm2.set(AlarmManager.RTC_WAKEUP,getTime2(), alarmIntent2);
    }
  //铃声
    protected void ring() {
    	int temp=currentMode();
    	Log.e(TAG,""+R.id.ring);
    	Intent intent = new Intent(AlarmReceiver.RING_CHANGED);
    	intent.putExtra("checkedId", R.id.ring);
    	PendingIntent alarmIntent = PendingIntent.getBroadcast(this,
				AlarmReceiver.REQUEST_CODE,
				intent,
				0);
    	Log.e(TAG,""+intent);
    	alarms.set(AlarmManager.RTC_WAKEUP,getTime(), alarmIntent);
    	//发送定时还原警告
    	Intent intent2 = new Intent(returnModeString(temp));
    	intent2.putExtra("checkedId", temp);
    	
    	PendingIntent alarmIntent2 = PendingIntent.getBroadcast(this,
    			AlarmReceiver.REQUEST_CODE+1,
				intent2,
				0);
    	Log.e(TAG,""+intent2);
    	AlarmManager alarm2=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
    	alarm2.set(AlarmManager.RTC_WAKEUP,getTime2(), alarmIntent2);
    }
  //震动
    protected void vibrate() {
    	int temp=currentMode();
    	Log.e(TAG,""+R.id.vibrate);
    	Intent intent = new Intent(AlarmReceiver.VIBRATE_CHANGED);
    	intent.putExtra("checkedId", R.id.vibrate);
    	PendingIntent alarmIntent = PendingIntent.getBroadcast(this,
				AlarmReceiver.REQUEST_CODE,
				intent,
				0);
    	Log.e(TAG,""+intent);
    	alarms.set(AlarmManager.RTC_WAKEUP,getTime(), alarmIntent);
    	//发送定时还原警告
    	Intent intent2 = new Intent(returnModeString(temp));
    	intent2.putExtra("checkedId", temp);
    	
    	PendingIntent alarmIntent2 = PendingIntent.getBroadcast(this,
    			AlarmReceiver.REQUEST_CODE+1,
				intent2,
				0);
    	Log.e(TAG,""+intent2);
    	alarms.set(AlarmManager.RTC_WAKEUP,getTime2(), alarmIntent2);
    }
  //静音
    protected void silent() {
    	int temp=currentMode();
    	Log.i("smartchange","mode="+temp);
    	Log.e(TAG,""+R.id.silent);
    	Intent intent = new Intent(AlarmReceiver.SILENT_CHANGED);
    	intent.putExtra("checkedId", R.id.silent);
    	PendingIntent alarmIntent = PendingIntent.getBroadcast(this,
				AlarmReceiver.REQUEST_CODE,
				intent,
				0);
    	Log.e(TAG,""+intent);
    	alarms.set(AlarmManager.RTC_WAKEUP,getTime(), alarmIntent);
    	//发送定时还原警告
    	Intent intent2 = new Intent(returnModeString(temp));
    	intent2.putExtra("checkedId", temp);
    	
    	PendingIntent alarmIntent2 = PendingIntent.getBroadcast(this,
    			AlarmReceiver.REQUEST_CODE+1,
				intent2,
				0);
    	Log.e(TAG,""+intent2);
    	AlarmManager alarm2=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
    	alarm2.set(AlarmManager.RTC_WAKEUP,getTime2(), alarmIntent2);
    }
    //关闭程序
    protected void waitClosing() {
        new Thread() {
        	public void run() {
                try {
                	Thread.sleep(1000);
                } catch (InterruptedException e) {}
                	main.this.finish();
        	}
        }.start();
    }
    //计算切换时间
    private long getTime() {
    	Date dateNow = new Date();
    	long hour = tp.getCurrentHour()-dateNow.getHours();
    	if(hour<0)hour+=24;
    	long min = tp.getCurrentMinute()-dateNow.getMinutes();
    	
    	long second = dateNow.getSeconds();
    	return dateNow.getTime() + (hour*60 + min)*60*1000 - second*1000;
    }
    private long getTime2() {
    	Date dateNow = new Date();
    	long hour = tp1.getCurrentHour()-dateNow.getHours();
    	if(hour<0)hour+=24;
    	long min = tp1.getCurrentMinute()-dateNow.getMinutes();
    	long second = dateNow.getSeconds();
    	return dateNow.getTime() + (hour*60 + min)*60*1000 - second*1000;
    }
}