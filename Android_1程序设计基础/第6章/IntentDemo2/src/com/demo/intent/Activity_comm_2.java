package com.demo.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity_comm_2 extends Activity {
	EditText text = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comm_2);
		text = (EditText) findViewById(R.id.editText1);
		// 从Intent读取信息
		Intent i = getIntent();
		Bundle b = i.getBundleExtra("attachment");
		String temp = b.getString("content");
		text.setText(temp);
		// setup button listener
		Button saveButton = (Button) findViewById(R.id.save);
		saveButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent i =new Intent(Activity_comm_2.this,Activity_comm_1.class);
				Bundle bd = new Bundle();

				bd.putString("newcontent", String.valueOf(text.getText()));
				i.putExtra("attachment2", bd);
				setResult(RESULT_OK, i);
				finish();
			}
		});
		Button abortButton = (Button) findViewById(R.id.abort);
		abortButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				setResult(RESULT_CANCELED);
				finish();

			}
		});

	}
}
