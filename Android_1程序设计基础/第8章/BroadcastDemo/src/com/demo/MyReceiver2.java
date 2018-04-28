package com.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver{  
	public void onReceive(Context context, Intent intent) {   
        String action = intent.getAction();   
        Toast.makeText(context, "MyReceive2Ω” ’µΩ:"+action, 1000).show();  
        Log.i("MyReceive2","current priority is 20");
    }   

}

