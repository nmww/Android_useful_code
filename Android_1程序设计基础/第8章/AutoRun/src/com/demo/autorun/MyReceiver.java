package com.demo.autorun;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
@Override
public void onReceive(Context context, Intent intent){
	Intent activityIntent = new Intent(context,Main.class);
	
	activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
    context.startActivity(activityIntent);  
	

}
}

