package com.demo.preferenceactivitydemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class other extends Activity implements View.OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView t = (TextView) this.findViewById(R.id.tv01);
		SharedPreferences spc = this.getSharedPreferences("com.demo.preferenceactivitydemo_preferences",MODE_WORLD_WRITEABLE );
		String name=spc.getString("name", "");
		SharedPreferences.Editor e=spc.edit();
		e.putString("name", "changed");
		e.commit();
		t.setText("您当前设置的姓名是:"+name);
		
		Button bt = (Button) this.findViewById(R.id.Button01);
		bt.setOnClickListener(this);

	}

	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.Button01:

			Intent intent = new Intent(this, main.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}