package com.demo;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker.OnTimeChangedListener;

public class DateTimeDemo extends Activity {
	/** Called when the activity is first created. */
	private DatePicker dpicker;
	private TimePicker tpicker;
	private Calendar c;
	private TextView tview;
	private Button btn1;
	private Button btn2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datetime);
		c = Calendar.getInstance();
		dpicker = (DatePicker) this.findViewById(R.id.DatePicker01);
		tpicker = (TimePicker) this.findViewById(R.id.TimePicker01);
		btn1 = (Button) this.findViewById(R.id.Button01);
		btn2 = (Button) this.findViewById(R.id.Button02);
		tview = (TextView) this.findViewById(R.id.TextView01);
		dpicker.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
				.get(Calendar.DAY_OF_MONTH), new OnDateChangedListener() {

			public void onDateChanged(DatePicker arg0, int arg1, int arg2,
					int arg3) {
				tview.setText("[" + arg1 + "-" + (arg2 + 1) + "-" + arg3 + "]"
						+ "[" + arg0.getYear() + "-" + (arg0.getMonth() + 1)
						+ "-" + arg0.getDayOfMonth() + "]");

			}

		});

		tpicker.setIs24HourView(true);// 设置是否为24小时制
		tpicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
		tpicker.setCurrentMinute(c.get(Calendar.MINUTE));
		tpicker.setOnTimeChangedListener(new OnTimeChangedListener() {

			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				tview.setText("[" + hourOfDay + ":" + minute + "]" + "["
						+ view.getCurrentHour() + ":" + view.getCurrentMinute()
						+ "]");

			}

		});
		btn1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				new DatePickerDialog(DateTimeDemo.this,
						new DatePickerDialog.OnDateSetListener() {

							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								tview.setText("[" + year + "-"
										+ (monthOfYear + 1) + "-" + dayOfMonth
										+ "]" + "[" + view.getYear() + "-"
										+ (view.getMonth() + 1) + "-"
										+ view.getDayOfMonth() + "]");
								dpicker.init(year, monthOfYear, dayOfMonth,
										null);

							}

						}, dpicker.getYear(), dpicker.getMonth(), dpicker
								.getDayOfMonth()).show();
			}

		});
		btn2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				new TimePickerDialog(DateTimeDemo.this,
						new TimePickerDialog.OnTimeSetListener() {

							public void onTimeSet(TimePicker view,
									int hourOfDay, int minute) {
								tview.setText("[" + hourOfDay + ":" + minute + "]" + "["
										+ view.getCurrentHour() + ":" + view.getCurrentMinute()
										+ "]");
								tpicker.setCurrentHour(hourOfDay);
								tpicker.setCurrentMinute(minute);
							}

						}, tpicker.getCurrentHour(),
						tpicker.getCurrentMinute(), true).show();
			}

		});
	}
}

