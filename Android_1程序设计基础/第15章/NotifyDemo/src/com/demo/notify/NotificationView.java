package com.demo.notify;

import android.app.Activity;   
import android.app.NotificationManager;   
import android.os.Bundle;   
import android.widget.TextView;   
  
// ����֪ͨ�б��ĳ��֪ͨ�����򿪵���ϸ��֪ͨҳ   
public class NotificationView extends Activity {   
    protected void onCreate(Bundle savedInstanceState) {   
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.view);   
  
        TextView txtMsg = (TextView)this.findViewById(R.id.txtMsg);   
        txtMsg.setText("��֪֮ͨ�������ӵ��� Activity");   
           
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);   
        // ȡ����ʾ��֪ͨ�б��е�ָ��֪ͨ������Ϊ֪ͨ��ʶ����   
        nm.cancel(0);   
           
        // ��Ҫ�رմ� Activity �Ļ��� finish ���ȿ�   
        // this.finish();   
    }   
}  

