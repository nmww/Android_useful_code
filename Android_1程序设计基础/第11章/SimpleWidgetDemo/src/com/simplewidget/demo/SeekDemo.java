package com.simplewidget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class SeekDemo  extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seekbar);

		//找到拖动条和文本框
		final SeekBar sb = (SeekBar) findViewById(R.id.SeekBar01);
		final TextView tv1 = (TextView) findViewById(R.id.TextView01);

		//设置拖动条的初始值和文本框的初始值
		sb.setMax(100);
		sb.setProgress(30);
		tv1.setText("当前进度：" + sb.getProgress());

		//设置拖动条改变监听器
		OnSeekBarChangeListener osbcl = new OnSeekBarChangeListener() {

			
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				tv1.setText("当前进度：" + sb.getProgress());
				Toast.makeText(getApplicationContext(), "onProgressChanged",
						Toast.LENGTH_SHORT).show();
			}

			
			public void onStartTrackingTouch(SeekBar seekBar) {
				Toast.makeText(getApplicationContext(), "onStartTrackingTouch",
						Toast.LENGTH_SHORT).show();
			}

			
			public void onStopTrackingTouch(SeekBar seekBar) {
				Toast.makeText(getApplicationContext(), "onStopTrackingTouch",
						Toast.LENGTH_SHORT).show();
			}

		};

		//为拖动条绑定监听器
		sb.setOnSeekBarChangeListener(osbcl);

	}
}
