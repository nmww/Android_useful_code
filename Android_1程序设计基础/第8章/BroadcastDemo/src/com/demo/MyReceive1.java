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
        Toast.makeText(context, "MyReceive1接收到:"+action, 1000).show(); 
       Log.i("MyReceive1","current priority is 0");
     //获取上一receiver的处理结果
       Bundle b = getResultExtras(true);  
       if(b!=null){
       String name=(String)b.getString("name");
       Toast.makeText(context, "上一节点处理数据:"+name, 1000).show(); 
        if(name!=null){
       if(name.equals("hyl"))abortBroadcast();}
       }
    }   

}

