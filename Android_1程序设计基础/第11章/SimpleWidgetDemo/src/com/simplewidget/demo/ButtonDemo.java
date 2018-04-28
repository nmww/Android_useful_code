package com.simplewidget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ButtonDemo extends Activity  {
	TextView tv =null;
	String promote="你喜欢的手机品牌是？";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.button);
		tv = (TextView) findViewById(R.id.TextView01);
		/*Button button1 = (Button) findViewById(R.id.Button01);
		button1.setOnClickListener(this);
		Button button2= (Button) findViewById(R.id.Button02);
		button2.setOnClickListener(this);
		Button button3 = (Button) findViewById(R.id.Button03);
		button3.setOnClickListener(this);
		button1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Button b = (Button)v;
					tv.setText(promote+b.getText().toString());
		}
		});
		Button button2 = (Button) findViewById(R.id.Button02);
		button2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Button b = (Button)v;
					tv.setText(promote+b.getText().toString());
		}

		});
		Button button3 = (Button) findViewById(R.id.Button03);
		button3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Button b = (Button)v;
						tv.setText(promote+b.getText().toString());
			}
		});*/
		ImageButton button4 = (ImageButton) findViewById(R.id.ImageButton);
		button4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
						tv.setText(promote+" 暂时保密吆");
			}
		});

	}
		public void onClick(View v) {
		// TODO Auto-generated method stub
		Button b = (Button)v;
		if(b.getText()!=null)
		tv.setText(promote+b.getText().toString());
		
	}
}
