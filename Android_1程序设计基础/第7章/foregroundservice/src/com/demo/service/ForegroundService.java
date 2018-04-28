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
		// ��ʼ��������Դ
		try {
			// ����MediaPlayer����
			mp = new MediaPlayer();
			// ��������Import�ķ�ʽ������res/raw/tip.wma
			mp = MediaPlayer.create(this.getApplicationContext(),
					R.raw.test_cbr);
			

			// ��MediaPlayerȡ�ò�����Դ��stop()֮��Ҫ׼��PlayBack��״
			// ̬ǰһ��Ҫʹ��MediaPlayer.prepeare()
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
		// ����ֹͣʱֹͣ�������ֲ��ͷ���Դ
		mp.stop();
		mp.release();

		stopForeground(false);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		handleCommand(intent);
		new Thread(new Runnable() {

			public void run() {
				// ��ʼ��������
				mp.start();
				// ���ֲ�����ϵ��¼�����
				mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

					public void onCompletion(MediaPlayer mp) {
						// TODO Auto-generated method stub
						// ѭ������
						try {
							mp.start();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				// ��������ʱ����������¼�����
				mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {

					public boolean onError(MediaPlayer mp, int what, int extra) {
						// TODO Auto-generated method stub
						// �ͷ���Դ
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
		// ϣ������һֱ����
		return START_STICKY;
	}

	void handleCommand(Intent intent) {
		if (ACTION_FOREGROUND.equals(intent.getAction())) {

			CharSequence text = ("ǰ̨��������");

			// Set the icon, scrolling text and timestamp
			Notification notification = new Notification(R.drawable.play, text,
					System.currentTimeMillis());

			// The PendingIntent to launch our activity if the user selects this
			// notification
			PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
					new Intent(this, ServiceControl.class), 0);

			// Set the info for the views that show in the notification panel.
			notification.setLatestEventInfo(this, "����", text, contentIntent);

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
