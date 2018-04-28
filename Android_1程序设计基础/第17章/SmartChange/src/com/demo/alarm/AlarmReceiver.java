package com.demo.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;


public class AlarmReceiver extends BroadcastReceiver{

	public static final String VIBRATE_CHANGED = "com.demo.smartchange.VIBRATE_CHANGED";
	public static final String SILENT_CHANGED = "com.demo.smartchange.SILENT_CHANGED";
	public static final String RV_CHANGED = "com.demo.smartchange.RV_CHANGED";
	public static final String RING_CHANGED = "com.demo.smartchange.RING_CHANGED";
	
	public static final int REQUEST_CODE = 0;
	
	String TAG = "AlarmReceiver";
	@Override
	public void onReceive(Context context, Intent intent) {

			AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
	        
			int checkedId = intent.getIntExtra("checkedId",0);
           
			Log.e(TAG,checkedId + intent.getAction());
			//�л��龰ģʽ
			switch (checkedId) {
	            case R.id.ring_and_vibrate: ringAndVibrate(audio); break;
	            case R.id.vibrate: vibrate(audio); break;
	            case R.id.silent: silent(audio); break;
	            default: ring(audio); break;
			}

	}
	
	//��������
    protected void ringAndVibrate(AudioManager audio) {
    	audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER, AudioManager.VIBRATE_SETTING_ON);
        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION, AudioManager.VIBRATE_SETTING_ON);
    }
  //����
    protected void ring(AudioManager audio) {
    	audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER, AudioManager.VIBRATE_SETTING_OFF);
        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION, AudioManager.VIBRATE_SETTING_OFF);
        
    }
  //��
    protected void vibrate(AudioManager audio) {
    	audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER, AudioManager.VIBRATE_SETTING_ON);
        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION, AudioManager.VIBRATE_SETTING_ON);   
        
    }
  //����
    protected void silent(AudioManager audio) {
    	audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER, AudioManager.VIBRATE_SETTING_OFF);
        audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION, AudioManager.VIBRATE_SETTING_OFF);
          
    }

}
