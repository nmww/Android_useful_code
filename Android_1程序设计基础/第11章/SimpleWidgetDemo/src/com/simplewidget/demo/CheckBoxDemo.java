package com.simplewidget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class CheckBoxDemo extends Activity {
    
    private TextView tv;
     private CheckBox cb1;
     private CheckBox cb2;
     private CheckBox cb3;
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.checkbox);
         
        tv = (TextView)findViewById(R.id.textview1);
         cb1 = (CheckBox)findViewById(R.id.checkbox1);
         cb2 = (CheckBox)findViewById(R.id.checkbox2);
         cb3 = (CheckBox)findViewById(R.id.checkbox3);
         
        
        cb1.setOnCheckedChangeListener(cbListener);
         cb2.setOnCheckedChangeListener(cbListener); 
         cb3.setOnCheckedChangeListener(cbListener);
        
    }
     
    private CheckBox.OnCheckedChangeListener cbListener = 
        new CheckBox.OnCheckedChangeListener(){
         
        public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
         {
             String stv = getString(R.string.mobileos);
             String scb1 = getString(R.string.ios);
             String scb2 = getString(R.string.android);
             String scb3 = getString(R.string.wphone);
             String temp=stv;
             
             if(cb1.isChecked()== true )temp+=scb1;
             if(cb2.isChecked()== true )temp+=" "+scb2;
             if(cb3.isChecked()== true )temp+=" "+scb3;
            
              tv.setText(temp);
           
         }
     }; 
    
}


