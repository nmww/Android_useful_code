package com.demo.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_comm_1 extends Activity {

	public static int start_flag = 1;
	TextView text = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comm_1);

		Button startButton = (Button) findViewById(R.id.edit);
		startButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent i = new Intent(Activity_comm_1.this,
						Activity_comm_2.class);
				Bundle bd = new Bundle();
				text = (TextView) findViewById(R.id.textView1);
				bd.putString("content", (String) text.getText());
				i.putExtra("attachment", bd);
				startActivityForResult(i, start_flag);

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == start_flag) {
			if (resultCode == RESULT_OK) {
				Bundle b = data.getBundleExtra("attachment2");

				text.setText(b.getString("newcontent"));
			} else {
				return;

			}
		}

	}
}
