package com.simplewidget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

public class CalandarViewDemo extends Activity {  
	protected void onCreate(Bundle savedInstanceState) {  
		// TODO Auto-generated method stub   
		super.onCreate(savedInstanceState);    
		setContentView(R.layout.calendarview);  
		CalendarView cv=(CalendarView) findViewById(R.id.widgetCalendarView1);
		cv.setOnDateChangeListener(new OnDateChangeListener(){

			public void onSelectedDayChange(CalendarView view, int year,
					int month, int day) {
				// TODO Auto-generated method stub
				String s="��ѡ����" +Integer.toString(year)+"��"+Integer.toString(year)+"��"+Integer.toString(year)+"��";
				Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
			}
			
		}
				);
	}
}
