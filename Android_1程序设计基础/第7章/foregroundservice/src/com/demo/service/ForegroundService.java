package com.demo.service;

import java.io.IOException;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class ForegroundService extends Service {
	static final String ACTION_FOREGROUND = "com.service.demo.FOREGROUND";
	static final String ACTION_BACKGROUND = "com.service.demo.BACKGROUND";

	MediaPlayer mp = null;

	@Override
	public void onCreate() {
		// 初始化音乐资源
		try {
			// 创建MediaPlayer对象
			mp = new MediaPlayer();
			// 将音乐以Import的方式保存在res/raw/tip.wma
			mp = MediaPlayer.create(this.getApplicationContext(),
					R.raw.test_cbr);
			

			// 在MediaPlayer取得播放资源与stop()之后要准备PlayBack的状
			// 态前一定要使用MediaPlayer.prepeare()
			mp.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onDestroy() {
		// 服务停止时停止播放音乐并释放资源
		mp.stop();
		mp.release();

		stopForeground(false);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		handleCommand(intent);
		new Thread(new Runnable() {

			public void run() {
				// 开始播放音乐
				mp.start();
				// 音乐播放完毕的事件处理
				mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

					public void onCompletion(MediaPlayer mp) {
						// TODO Auto-generated method stub
						// 循环播放
						try {
							mp.start();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				// 播放音乐时发生错误的事件处理
				mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {

					public boolean onError(MediaPlayer mp, int what, int extra) {
						// TODO Auto-generated method stub
						// 释放资源
						try {
							mp.release();
						} catch (Exception e) {
							e.printStackTrace();
						}

						return false;
					}
				});

			}
		}).start();
		// 希望服务一直运行
		return START_STICKY;
	}

	void handleCommand(Intent intent) {
		if (ACTION_FOREGROUND.equals(intent.getAction())) {

			CharSequence text = ("前台服务启动");

			// Set the icon, scrolling text and timestamp
			Notification notification = new Notification(R.drawable.play, text,
					System.currentTimeMillis());

			// The PendingIntent to launch our activity if the user selects this
			// notification
			PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
					new Intent(this, ServiceControl.class), 0);

			// Set the info for the views that show in the notification panel.
			notification.setLatestEventInfo(this, "音乐", text, contentIntent);

			this.startForeground(1, notification);

		} else if (ACTION_BACKGROUND.equals(intent.getAction())) {

			this.stopForeground(true);
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	

}
