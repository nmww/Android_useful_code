package com.demo.preference;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;

public class main extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		SharedPreferences mPerferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		int counter = mPerferences.getInt("counter", 0);
		// 注册监听器
		mPerferences
				.registerOnSharedPreferenceChangeListener(new OnSharedPreferenceChangeListener() {

					@Override
					public void onSharedPreferenceChanged(
							SharedPreferences sharedPreferences, String key) {
						// TODO Auto-generated method stub

						if (key.equals("counter")) {
							int i = sharedPreferences.getInt("counter", 0);
							Log.i("SharePreferenceMonitor",
									"application has been start" + i + "times");
						}
					}

				});

		// 获取activity私有的preference
		SharedPreferences m_privatepreference = this
				.getPreferences(MODE_PRIVATE);
		String m_date = m_privatepreference.getString("date", "");
		if (!m_date.equals(""))
			m_date = "上次启动时间：" + m_date;
		TextView mTextView = (TextView) findViewById(R.id.text);
		mTextView.setText("应用已经被启动" + counter + " 次 " + m_date);
		SharedPreferences.Editor mEditor = mPerferences.edit();
		mEditor.putInt("counter", ++counter);
		mEditor.commit();

		Date dt = new Date();// 如果不需要格式,可直接用dt,dt就是当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 设置显示格式
		String nowTime = "";
		nowTime = df.format(dt);// 用DateFormat的format()方法在dt中获取并以yyyy/MM/dd
								// HH:mm:ss格式显示
		SharedPreferences.Editor mEditor2 = m_privatepreference.edit();
		mEditor2.putString("date", nowTime);
		mEditor2.commit();

	}
	
	 
}