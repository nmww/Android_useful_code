package com.simplewidget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class RadioDemo extends Activity {   
	private TextView myTextView;  
	private TextView myTextView2;  
private RadioButton Btn1; 
private RadioButton Btn2;  
private RadioButton Btn3;   
private RadioButton Btns1; 
private RadioButton Btns2;  
private RadioButton Btns3;   
private RadioGroup service;    
private RadioGroup device;  
/** Called when the activity is first created. */    
@Override    public void onCreate(Bundle savedInstanceState) {      
	super.onCreate(savedInstanceState);       
	setContentView(R.layout.radio);       
	//通过ID找到TextView       
	myTextView = (TextView) findViewById(R.id.myTextView);  
	myTextView2 = (TextView) findViewById(R.id.myTextView2);  
	//通过ID找到RadioButton      
	Btn1 = (RadioButton) findViewById(R.id.moto);       
	Btn2 = (RadioButton) findViewById(R.id.samsung);      
	Btn3 = (RadioButton) findViewById(R.id.htc);      
	//通过ID找到RadioGroup      
	device = (RadioGroup) findViewById(R.id.rBtnGroup_mobile);       
	//只要对RadioGroup进行监听     
	device.setOnCheckedChangeListener(new OnCheckedChangeListener() {       
	        public void onCheckedChanged(RadioGroup group, int checkedId) {  
			// TODO Auto-generated method stub             
			if(R.id.moto == checkedId){               
				myTextView.setText("您选择的设备是：" + Btn1.getText().toString());      }    
			else if(R.id.samsung == checkedId){             
				myTextView.setText("您选择的设备是：" + Btn2.getText().toString());    }      
			else if(R.id.htc == checkedId){   
				myTextView.setText("您选择的设备是：" + Btn3.getText().toString());     }     
			}        }); 
	//通过ID找到RadioButton      
	Btns1 = (RadioButton) findViewById(R.id.tele);       
	Btns2 = (RadioButton) findViewById(R.id.mbile);      
	Btns3 = (RadioButton) findViewById(R.id.unicomm);      
	//通过ID找到RadioGroup      
	service = (RadioGroup) findViewById(R.id.rBtnGroup_service);       
	//只要对RadioGroup进行监听     
	service.setOnCheckedChangeListener(new OnCheckedChangeListener() {       
	        public void onCheckedChanged(RadioGroup group, int checkedId) {  
			// TODO Auto-generated method stub             
			if(R.id.tele == checkedId){               
				myTextView2.setText("您选择的运行商是：" + Btns1.getText().toString());      }    
			else if(R.id.mbile == checkedId){             
				myTextView2.setText("您选择的运行商是：" + Btns2.getText().toString());    }      
			else if(R.id.unicomm == checkedId){   
				myTextView2.setText("您选择的运行商是：" + Btns3.getText().toString());     }     
			}        }); 
	}
}

