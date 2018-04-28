package com.demo.notify;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
  
public class Main extends Activity {   
  
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {   
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.main);   
             
        Button btn4 = (Button) this.findViewById(R.id.btn4);   
        btn4.setText("����һ��֪ͨ��Notification��");   
        btn4.setOnClickListener(new Button.OnClickListener() {   
            public void onClick(View v) { 
            	// ʵ����һ��֪ͨ����ָ����ͼ��ͱ��⣨����ʾ������ʾ��   
                Notification n = new Notification(R.drawable.icon, "Hi,android status notify", System.currentTimeMillis());   
                // ʵ����֪ͨ������   
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);   
  
                // ָ������֪ͨ�����򿪵���ϸ��֪ͨҳ�棨����֪ͨ��� NotificationView��   
                PendingIntent contentIntent = PendingIntent.getActivity(   
                        Main.this, 0, new Intent(Main.this,    NotificationView.class), 0);   
  
                   
                // ����֪ͨ�ķ����˺�֪ͨ����ϸ���ݣ�����ʾ������֪ͨ�б�����ʾ��   
                n.setLatestEventInfo(Main.this, "android", "just test", contentIntent);   
               // n.defaults |= Notification.DEFAULT_SOUND;
              //  n.sound=Uri.parse("file:///sdcard/06.mp3");
                // 100 �����ӳٺ��� 250 ���룬��ͣ 100 ��������� 500 ����   
              // n.vibrate = new long[] { 100, 250, 100, 500 };   
               // n.ledARGB = 0xff00ff00;
               // n.ledOnMS = 3000;
               // n.ledOffMS = 1000;
               // n.flags |= Notification.FLAG_SHOW_LIGHTS;
                n.defaults |= Notification.DEFAULT_LIGHTS;

                // ����֪ͨ�����е�һ������Ϊ֪ͨ��ʶ����   
                nm.notify(0, n);   
            }   
        });   
    }   
  
  
}   
