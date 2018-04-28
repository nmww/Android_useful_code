package com.demo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.TextView;

public class progressdlgdemo extends Activity implements Runnable {
    private String show_string;
    private TextView tv;
    private ProgressDialog pd;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.progress);
        tv = (TextView) this.findViewById(R.id.textView1);
        tv.setText("单击任意键开始计算" );
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        pd = ProgressDialog.show(this, "计算中..", "正在计算", true,
                false);
        Thread thread = new Thread(this);
        thread.start();
        return super.onKeyDown(keyCode, event);
    }
    public void run() {
    	int result=0;
    	for(int i=0;i<100;i++){
    		result+=i;
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
        show_string =Integer.toString(result);
        handler.sendEmptyMessage(0);
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();
            tv.setText(show_string);
        }
    };
}
