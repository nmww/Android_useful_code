package com.demo.alarm;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;


public class OneShotAlarm extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
    	//获取alarm uri
    	Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); 

    	//创建media player
    	MediaPlayer mMediaPlayer = new MediaPlayer();
    	try{
    	mMediaPlayer.setDataSource(context, alert);
    	//mMediaPlayer.setDataSource("/raw/test.mp3");
    	final AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
    	if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
    	            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
    	            mMediaPlayer.setLooping(false);
    	            mMediaPlayer.prepare();
    	            mMediaPlayer.start();
    	  }
    	}catch(Exception e){
    		Log.e("OneShotAlarm", e.toString());
    	}

    	Toast.makeText(context, "闹钟被触发", Toast.LENGTH_SHORT).show();
    }
}
