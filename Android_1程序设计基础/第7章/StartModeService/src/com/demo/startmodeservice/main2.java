package com.demo.startmodeservice;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main2  extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        Button Button1 = (Button) findViewById(R.id.button1);
        Button1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent i=new Intent(main2.this, CountIntentService.class);
            	Bundle bd=new Bundle();
            	double a = Math.random()*10;  
            	  a = Math.ceil(a);  
            	  int randomNum = new Double(a).intValue();  
				bd.putInt("waitingtime", randomNum);
				i.putExtra("attachment", bd);

            	 startService(i);
            }
        });
        Button Button2 = (Button) findViewById(R.id.button2);
        Button2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	 stopService(new Intent(main2.this, CountIntentService.class));
            }
        });
       // this.startService(new Intent(this, CountService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // this.stopService(new Intent(this, CountService.class));
    }
   


}
