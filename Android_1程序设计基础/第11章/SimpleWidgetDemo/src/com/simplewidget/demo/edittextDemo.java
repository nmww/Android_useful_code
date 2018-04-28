package com.simplewidget.demo;

import android.app.Activity;   
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;   
import android.view.KeyEvent;   
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;   
import android.widget.TextView;   
 
import android.widget.TextView.OnEditorActionListener; 

public class edittextDemo extends Activity
{


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edittext);
		
	}
}