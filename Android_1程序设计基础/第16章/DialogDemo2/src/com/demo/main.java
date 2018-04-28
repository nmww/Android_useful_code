package com.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class main extends Activity {

	private static final int DIALOG_Data_Exchange = 2;
	private static final int DIALOG_Custom = 3;
	String[] arr;
	TextView txt;
	String[] temp = new String[3];
	Dialog dlg = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button SimpleButtons = (Button) findViewById(R.id.choose_button);
		SimpleButtons.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_Data_Exchange);

			}
		});
		Button SimpleButtons2 = (Button) findViewById(R.id.choose_button2);
		SimpleButtons2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_Custom);

			}
		});
		Resources res = this.getResources();

		arr = res.getStringArray(R.array.os);
		txt = (TextView) findViewById(R.id.result);

	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case DIALOG_Data_Exchange:

			dlg = new AlertDialog.Builder(main.this)
					.setMultiChoiceItems(R.array.os,
							new boolean[] { false, false, false },
							new DialogInterface.OnMultiChoiceClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton, boolean isChecked) {

									if (isChecked) {
										temp[whichButton] = arr[whichButton];
									} else
										temp[whichButton] = "";

								}

							})
					.setPositiveButton(R.string.dialog_ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									/* User clicked Yes so do some stuff */
									String temp2 = "your  operate system is:";
									for (int ii = 0; ii < 3; ii++) {
										if (temp[ii] != null)
											temp2 += temp[ii];
									}

									txt.setText(temp2);

								}
							})
					.setNegativeButton(R.string.dialog_cancle,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									/* User clicked No so do some stuff */

								}
							}).create();

			return dlg;

		case DIALOG_Custom:
			dlg = new Dialog(main.this);

			dlg.setContentView(R.layout.custom_dialog);
			dlg.setTitle("自定义 Dialog");

			TextView text = (TextView) dlg.findViewById(R.id.text);
			text.setText("Hello, this is a custom dialog!");
			ImageView image = (ImageView) dlg.findViewById(R.id.image);
			image.setImageResource(R.drawable.icon);
			Window window = dlg.getWindow();// 　　　得到Dialog的窗口．
			WindowManager.LayoutParams wl = window.getAttributes();
			wl.x = 100;// 　设置Dialog的位置
			wl.y = 700;
			wl.alpha = 0.9f;// 　　设置Dialog的透明度
			wl.gravity = Gravity.BOTTOM;
			window.setAttributes(wl);

			return dlg;

		}
		return null;
	}

	public void settext(String s) {
		txt.setText(s);
	}

}