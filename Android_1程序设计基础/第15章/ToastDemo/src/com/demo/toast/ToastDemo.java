package com.demo.toast;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ToastDemo extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 通过 Tost.makeText().show() 来实现提示性的通知效果
		// 短时间的提示性通知的 Demo
		Button btn1 = (Button) this.findViewById(R.id.btn1);
		btn1.setText("短时间提示");
		btn1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Toast t = Toast.makeText(ToastDemo.this, "我是短时间提示",
						Toast.LENGTH_SHORT);
				t.setGravity(Gravity.TOP | Gravity.LEFT, 100, 0);
				t.setMargin(0.5f, 0.1f);
				t.show();
			}
		});
		// 短时间的提示性通知的 Demo
		Button btn2 = (Button) this.findViewById(R.id.btn2);
		btn2.setText("自定义" +
				"View提示");
		btn2.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				View view = vi.inflate(R.layout.toastview, null);

				Toast toast = new Toast(ToastDemo.this);
				toast.setView(view);
				toast.setDuration(Toast.LENGTH_LONG);
				toast.show();
			}
		});
	}
}