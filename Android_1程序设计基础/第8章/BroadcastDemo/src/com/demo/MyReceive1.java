package com.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
public class MyReceive1 extends BroadcastReceiver{  
	public void onReceive(Context context, Intent intent) {   
        String action = intent.getAction();   
        Toast.makeText(context, "MyReceive1���յ�:"+action, 1000).show(); 
       Log.i("MyReceive1","current priority is 0");
     //��ȡ��һreceiver�Ĵ�����
       Bundle b = getResultExtras(true);  
       if(b!=null){
       String name=(String)b.getString("name");
       Toast.makeText(context, "��һ�ڵ㴦������:"+name, 1000).show(); 
        if(name!=null){
       if(name.equals("hyl"))abortBroadcast();}
       }
    }   

}

