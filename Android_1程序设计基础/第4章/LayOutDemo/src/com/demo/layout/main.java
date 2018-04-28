package com.demo.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class main extends Activity {
	private Boolean m_hiddened=false;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.show_layout);
        //TextView myTextView = (TextView) findViewById(R.id.my_textview);
       // myTextView.setText("changed !!");

        //setContentView(R.layout.line1);
       // setContentView(R.layout.line2);
        //setContentView(R.layout.ratioline);
        setContentView(R.layout.relative);
        /*setContentView(R.layout.table1);
        Button finish=(Button)findViewById(R.id.mybutton);
        finish.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TableLayout tl=(TableLayout)findViewById(R.id.menu);
                m_hiddened=!m_hiddened;
                tl.setColumnCollapsed(0, m_hiddened);
                if(m_hiddened)
                ((Button)findViewById(R.id.mybutton)).setText("ÏÔÊ¾");
                else
                ((Button)findViewById(R.id.mybutton)).setText("Òþ²Ø");
                
            }
        });*/
       // setContentView(R.layout.relative);
       // setContentView(R.layout.frame);
    }
}