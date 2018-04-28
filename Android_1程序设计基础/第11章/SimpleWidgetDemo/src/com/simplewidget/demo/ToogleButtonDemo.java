package com.simplewidget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ToogleButtonDemo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.toogglebutton);
		final ToggleButton btn = (ToggleButton) this
				.findViewById(R.id.toggleButton1);

		btn.setTextOn("¿ªµÆ");
		btn.setTextOff("¹ØµÆ");

		btn.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {

				TextView txt = (TextView) ToogleButtonDemo.this
						.findViewById(R.id.tv1);

				// ToggleButton.isChecked() - Ë«×´Ì¬°´Å¥µÄ°´Å¥×´Ì¬

				txt.setText("°´Å¥×´Ì¬£º" + String.valueOf(btn.isChecked()));

			}

		});

	}

}
