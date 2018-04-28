package com.demo.systemservice;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class main extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		android.view.WindowManager windowManager = (android.view.WindowManager) getSystemService(Context.WINDOW_SERVICE);

		// 在窗口的标题栏输出当前窗口的宽度和高度，例如，320*480

		setTitle(String.valueOf(windowManager.getDefaultDisplay().getWidth())
				+ "*"

				+ String.valueOf(windowManager.getDefaultDisplay().getHeight()));

	}
}