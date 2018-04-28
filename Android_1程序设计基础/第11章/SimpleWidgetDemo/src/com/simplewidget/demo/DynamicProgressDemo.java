package com.simplewidget.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class DynamicProgressDemo extends Activity {
    private ProgressBar progressBar = null;
    private Button start = null, stop = null;
    // 定义Handler对象
    private Handler handler = new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamicprogress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handler.post(runnable); //开始执行
            }
        });
        stop=(Button)findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handler.removeCallbacks(runnable);//停止执行
             
            }
        });
    }
    int pro=0;
    Runnable runnable=new Runnable(){
        public void run() {
            progressBar.setVisibility(View.VISIBLE);
            pro=progressBar.getProgress()+10;
            progressBar.setProgress(pro);
            //如果进度小于100,，则延迟1000毫秒后重复执行runnable
            if(pro<100){
                handler.postDelayed(runnable, 1000);
            }else{
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(DynamicProgressDemo.this, main.class));
                handler.removeCallbacks(runnable);
                progressBar.setProgress(0);
            }
        }
    };
}

