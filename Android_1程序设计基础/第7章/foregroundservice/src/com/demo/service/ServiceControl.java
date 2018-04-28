package com.demo.service;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServiceControl extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.foreground);

        // Watch for button clicks.
        Button button = (Button)findViewById(R.id.start_foreground);
        button.setOnClickListener(mForegroundListener);
        button = (Button)findViewById(R.id.start_background);
        button.setOnClickListener(mBackgroundListener);
        button = (Button)findViewById(R.id.stop);
        button.setOnClickListener(mStopListener);
    }

    private OnClickListener mForegroundListener = new OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(ForegroundService.ACTION_FOREGROUND);
            intent.setClass(ServiceControl.this, ForegroundService.class);
            startService(intent);
        }
    };

    private OnClickListener mBackgroundListener = new OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(ForegroundService.ACTION_BACKGROUND);
            intent.setClass(ServiceControl.this, ForegroundService.class);
            startService(intent);
        }
    };

    private OnClickListener mStopListener = new OnClickListener() {
        public void onClick(View v) {
            stopService(new Intent(ServiceControl.this,
                    ForegroundService.class));
        }
    };
}