package com.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver3 extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		Toast.makeText(context, "MyReceive3接收到:" + action, 1000).show();
		Log.i("MyReceive3", "current priority is 30");
		String t="com.testBroadcastReceiver.MyAction2";
		if (t.equals(action) ){//消息处理器可以处理多个Action，这里需要判断
			// 当未知Intent包含的内容，则需要通过以下方法来列举
			Bundle b = intent.getExtras();
			if (b != null) {
				Object[] lstName = b.keySet().toArray();

				for (int i = 0; i < lstName.length; i++) {
					String keyName = lstName[i].toString();
					Log.e(keyName, b.getString(keyName));
				}
			}
		}
	}

}

