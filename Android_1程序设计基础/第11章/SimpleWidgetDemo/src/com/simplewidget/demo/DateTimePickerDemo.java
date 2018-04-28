package com.simplewidget.demo;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class DateTimePickerDemo  extends Activity {
	/** Called when the activity is first created. */
	private TextView view = null;
	private DatePicker date = null;
	private TimePicker time = null;
	private Button button1 = null;
	private Button button2 = null;
	Calendar c;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datetimepicker);
		view = (TextView) findViewById(R.id.dateview);
		view.setText("��ǰʱ��");
		// ȡ��ϵͳʱ�����
		c = Calendar.getInstance();
		date = (DatePicker) findViewById(R.id.date);
		time = (TimePicker) findViewById(R.id.time);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button1.setText("a");
		button2.setText("b");
		
		date.init(c.get(Calendar.DAY_OF_YEAR), c.get(Calendar.MONTH), c
				.get(Calendar.DAY_OF_MONTH),
				new DatePicker.OnDateChangedListener() {
					public void onDateChanged(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// �����ڸı�ʱ�������ﴦ��
						c.set(year, monthOfYear, dayOfMonth);
					}
				});
		// ����Ϊ24Сʱ��
		
		time.setIs24HourView(true);
		time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// ��ǰʱ��ı�ʱ�������ﴦ��
				c.set(c.get(Calendar.DAY_OF_YEAR), c.get(Calendar.MONTH), c
						.get(Calendar.DAY_OF_MONTH), c
						.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c
						.get(Calendar.SECOND));
			}
		});
		button1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				new DatePickerDialog(DateTimePickerDemo.this,
						new DatePickerDialog.OnDateSetListener() {

							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								// ��������
							}
						}, c.get(Calendar.DAY_OF_YEAR), c.get(Calendar.MONTH),
						c.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		button2.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				new TimePickerDialog(DateTimePickerDemo.this,
						new TimePickerDialog.OnTimeSetListener() {
							public void onTimeSet(TimePicker view,
									int hourOfDay, int minute) {
								// ����ʱ��
							}
						}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
						true).show();
			}
		});
	}
}
