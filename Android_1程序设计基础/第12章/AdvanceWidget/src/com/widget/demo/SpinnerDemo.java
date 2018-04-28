package com.widget.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerDemo  extends Activity {  
      
      
    private static final String[] m_bloods={"o��","A��","B��","AB��","����"};  
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
        //����ѡ������ArrayAdapter��������  
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m_bloods);  
          
        //���������б�ķ��  
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
          
        //��adapter ��ӵ�m_Spinner��  
        m_Spinner.setAdapter(adapter);  
          
        //����¼�Spinner�¼�����  
        m_Spinner.setOnItemSelectedListener(m_SpinnerListener);  
          
        //����Ĭ��ֵ  
        m_Spinner.setVisibility(View.VISIBLE);  
          
    }  
    private Spinner.OnItemSelectedListener m_SpinnerListener=new Spinner.OnItemSelectedListener()  
    {  
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
                long arg3) {  
            // TODO Auto-generated method stub            
            m_txtView.setText("���Ѫ���ǣ�"+m_bloods[arg2]);  
            //arg0.setVisibility(View.VISIBLE);   
              
        }  
          
        public void onNothingSelected(AdapterView<?> arg0) {  
            // TODO Auto-generated method stub  
              
        }         
    };  
}  


