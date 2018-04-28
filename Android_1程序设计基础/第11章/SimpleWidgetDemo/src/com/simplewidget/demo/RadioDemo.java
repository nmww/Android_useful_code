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
	//ͨ��ID�ҵ�TextView       
	myTextView = (TextView) findViewById(R.id.myTextView);  
	myTextView2 = (TextView) findViewById(R.id.myTextView2);  
	//ͨ��ID�ҵ�RadioButton      
	Btn1 = (RadioButton) findViewById(R.id.moto);       
	Btn2 = (RadioButton) findViewById(R.id.samsung);      
	Btn3 = (RadioButton) findViewById(R.id.htc);      
	//ͨ��ID�ҵ�RadioGroup      
	device = (RadioGroup) findViewById(R.id.rBtnGroup_mobile);       
	//ֻҪ��RadioGroup���м���     
	device.setOnCheckedChangeListener(new OnCheckedChangeListener() {       
	        public void onCheckedChanged(RadioGroup group, int checkedId) {  
			// TODO Auto-generated method stub             
			if(R.id.moto == checkedId){               
				myTextView.setText("��ѡ����豸�ǣ�" + Btn1.getText().toString());      }    
			else if(R.id.samsung == checkedId){             
				myTextView.setText("��ѡ����豸�ǣ�" + Btn2.getText().toString());    }      
			else if(R.id.htc == checkedId){   
				myTextView.setText("��ѡ����豸�ǣ�" + Btn3.getText().toString());     }     
			}        }); 
	//ͨ��ID�ҵ�RadioButton      
	Btns1 = (RadioButton) findViewById(R.id.tele);       
	Btns2 = (RadioButton) findViewById(R.id.mbile);      
	Btns3 = (RadioButton) findViewById(R.id.unicomm);      
	//ͨ��ID�ҵ�RadioGroup      
	service = (RadioGroup) findViewById(R.id.rBtnGroup_service);       
	//ֻҪ��RadioGroup���м���     
	service.setOnCheckedChangeListener(new OnCheckedChangeListener() {       
	        public void onCheckedChanged(RadioGroup group, int checkedId) {  
			// TODO Auto-generated method stub             
			if(R.id.tele == checkedId){               
				myTextView2.setText("��ѡ����������ǣ�" + Btns1.getText().toString());      }    
			else if(R.id.mbile == checkedId){             
				myTextView2.setText("��ѡ����������ǣ�" + Btns2.getText().toString());    }      
			else if(R.id.unicomm == checkedId){   
				myTextView2.setText("��ѡ����������ǣ�" + Btns3.getText().toString());     }     
			}        }); 
	}
}

