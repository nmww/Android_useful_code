package com.simplewidget.demo;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class TextViewDemo extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textview);
		TextView textView = (TextView) findViewById(R.id.textview2);
		//  textView.setText("代码中动态赋值的文本");
		textView.setText(Html.fromHtml("Hello <b>Android</b>,<font size=\"3\" color=\"red\">I am studying…</font>"));
		textView.setTextColor(android.graphics.Color.RED);
		
		Resources resources=getBaseContext().getResources();
		Drawable drawable=resources.getDrawable(R.color.background);		
		textView.setBackgroundDrawable(drawable);
	}
}
