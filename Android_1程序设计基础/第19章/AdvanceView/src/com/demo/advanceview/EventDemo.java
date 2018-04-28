package com.demo.advanceview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;

public class EventDemo extends Activity {
    /** Called when the activity is first created. */

    private EditText myEdit1;
   

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);

        myEdit1 = (EditText) findViewById(R.id.editText1);
        

        // 编辑文本框的按键事件
        myEdit1.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
            	Log.i("在MyEdittext的事件监听器中","你按下了键"+Integer.toString(keyCode));
               
                return false;
            }
        });
        

        
        // 编辑文本框的焦点事件
        myEdit1.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
            	Log.i("在MyEdittext的事件监听器中","获得焦点");
                Toast.makeText(getApplicationContext(), myEdit1.getText(),
                        Toast.LENGTH_LONG);
            }
        });
        
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	Log.i("在Activity的onKeyDown中","你按下了键"+Integer.toString(keyCode));
    	return super.onKeyDown(keyCode, event);
    }
}

