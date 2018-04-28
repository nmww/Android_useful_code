package com.widget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class AutoCompleteDemo  extends Activity {
	/** Called when the activity is first created. */
	private static final String[] nContent = { "zhuo", "kobe", "zhuori",
			"kobebryant", "ko8e", "ko8ebryant" };
	private AutoCompleteTextView autoView = null;
	private MultiAutoCompleteTextView autoView2 = null;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.autocomplete);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, nContent);
		
		autoView = (AutoCompleteTextView) findViewById(R.id.autoView);
		
		// 将adapter添加到AutoCompleteTextView中
		autoView.setAdapter(adapter);
		autoView.setThreshold(4);
		//多行处理
		autoView2 = (MultiAutoCompleteTextView) findViewById(R.id.autoView2);
		autoView2.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		autoView2.setAdapter(adapter);
		autoView2.setThreshold(4);

	}
	

}
