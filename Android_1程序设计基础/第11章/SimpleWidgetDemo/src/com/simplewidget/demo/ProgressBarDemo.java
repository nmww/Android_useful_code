package com.simplewidget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class ProgressBarDemo extends Activity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ô��ڽ��������Է��

		requestWindowFeature(Window.FEATURE_PROGRESS);
		// ���󴰿���ɫ����������óɲ���ȷ�Ľ��ȷ��

		// requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		setContentView(R.layout.progress);
		// ���ý������ɼ���

		setProgressBarVisibility(true);
		// setProgressBarIndeterminateVisibility(true);

		// ���ñ������еĲ���ȷ�Ľ������Ƿ������ʾ

		// ���ý���������ֵ,Ҫ����100��

		setProgress(60 * 100);

		setSecondaryProgress(80 * 100);

	}

}
