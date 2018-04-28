package com.widget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerDemo  extends Activity {  
      
      
    private static final String[] m_bloods={"o型","A型","B型","AB型","其他"};  
    private TextView m_txtView;  
    private Spinner m_Spinner;  
    private ArrayAdapter<String> adapter;  
      
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.spinner);  
          
        m_txtView=(TextView)this.findViewById(R.id.TextView01);  
        m_Spinner=(Spinner)this.findViewById(R.id.Spinner01);  
        //将可选内容与ArrayAdapter连接起来  
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m_bloods);  
          
        //设置下拉列表的风格  
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
          
        //将adapter 添加到m_Spinner中  
        m_Spinner.setAdapter(adapter);  
          
        //添加事件Spinner事件监听  
        m_Spinner.setOnItemSelectedListener(m_SpinnerListener);  
          
        //设置默认值  
        m_Spinner.setVisibility(View.VISIBLE);  
          
    }  
    private Spinner.OnItemSelectedListener m_SpinnerListener=new Spinner.OnItemSelectedListener()  
    {  
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
                long arg3) {  
            // TODO Auto-generated method stub            
            m_txtView.setText("你的血型是："+m_bloods[arg2]);  
            //arg0.setVisibility(View.VISIBLE);   
              
        }  
          
        public void onNothingSelected(AdapterView<?> arg0) {  
            // TODO Auto-generated method stub  
              
        }         
    };  
}  


