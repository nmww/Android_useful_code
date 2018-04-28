package com.demo.notify;

import android.app.Activity;   
import android.app.NotificationManager;   
import android.os.Bundle;   
import android.widget.TextView;   
  
// 单击通知列表的某个通知后，所打开的详细的通知页   
public class NotificationView extends Activity {   
    protected void onCreate(Bundle savedInstanceState) {   
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.view);   
  
        TextView txtMsg = (TextView)this.findViewById(R.id.txtMsg);   
        txtMsg.setText("点通知之后所链接到的 Activity");   
           
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);   
        // 取消显示在通知列表中的指定通知（参数为通知标识符）   
        nm.cancel(0);   
           
        // 需要关闭此 Activity 的话就 finish 它既可   
        // this.finish();   
    }   
}  

