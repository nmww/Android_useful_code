package com.demo.widget;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Chronometer.OnChronometerTickListener;

public class main extends Activity implements OnClickListener,
		OnChronometerTickListener
{
	private Chronometer chronometer;
	private TextView tvTime;


	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.btnStart:
				chronometer.start();
				break;
			case R.id.btnStop:
				chronometer.stop();
				break;
			case R.id.btnReset:
				chronometer.setBase(SystemClock.elapsedRealtime());
				break;
			case R.id.btnformat:
				chronometer.setFormat("Current Time %s");
				break;
		}
	}

	@Override
	public void onChronometerTick(Chronometer chronometer)//每秒钟都会调用一次
	{
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		tvTime.setText("当前时间：" + sdf.format(new Date()));

	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tvTime = (TextView)findViewById(R.id.tvTime);
		Button btnStart = (Button) findViewById(R.id.btnStart);
		Button btnStop = (Button) findViewById(R.id.btnStop);
		Button btnReset = (Button) findViewById(R.id.btnReset);
		Button btnFormat= (Button) findViewById(R.id.btnformat);
		chronometer = (Chronometer) findViewById(R.id.chronometer);
		btnStart.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnReset.setOnClickListener(this);
		btnFormat.setOnClickListener(this);
		chronometer.setOnChronometerTickListener(this);
		chronometer.setFormat("秒表：%s");
		
	}
}