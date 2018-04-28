package com.simplewidget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class ProgressBarDemo extends Activity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置窗口进度条特性风格

		requestWindowFeature(Window.FEATURE_PROGRESS);
		// 请求窗口特色风格，这里设置成不明确的进度风格

		// requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		setContentView(R.layout.progress);
		// 设置进度条可见性

		setProgressBarVisibility(true);
		// setProgressBarIndeterminateVisibility(true);

		// 设置标题栏中的不明确的进度条是否可以显示

		// 设置进度条进度值,要乘以100的

		setProgress(60 * 100);

		setSecondaryProgress(80 * 100);

	}

}
