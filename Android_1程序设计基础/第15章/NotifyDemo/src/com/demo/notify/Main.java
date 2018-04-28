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
        btn4.setText("发出一个通知（Notification）");   
        btn4.setOnClickListener(new Button.OnClickListener() {   
            public void onClick(View v) { 
            	// 实例化一个通知，并指定其图标和标题（在提示栏上显示）   
                Notification n = new Notification(R.drawable.icon, "Hi,android status notify", System.currentTimeMillis());   
                // 实例化通知管理器   
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);   
  
                // 指定单击通知后所打开的详细的通知页面（单击通知后打开 NotificationView）   
                PendingIntent contentIntent = PendingIntent.getActivity(   
                        Main.this, 0, new Intent(Main.this,    NotificationView.class), 0);   
  
                   
                // 设置通知的发送人和通知的详细内容（打开提示栏后在通知列表中显示）   
                n.setLatestEventInfo(Main.this, "android", "just test", contentIntent);   
               // n.defaults |= Notification.DEFAULT_SOUND;
              //  n.sound=Uri.parse("file:///sdcard/06.mp3");
                // 100 毫秒延迟后，震动 250 毫秒，暂停 100 毫秒后，再震动 500 毫秒   
              // n.vibrate = new long[] { 100, 250, 100, 500 };   
               // n.ledARGB = 0xff00ff00;
               // n.ledOnMS = 3000;
               // n.ledOffMS = 1000;
               // n.flags |= Notification.FLAG_SHOW_LIGHTS;
                n.defaults |= Notification.DEFAULT_LIGHTS;

                // 发出通知（其中第一个参数为通知标识符）   
                nm.notify(0, n);   
            }   
        });   
    }   
  
  
}   
