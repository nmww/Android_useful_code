package com.demo.simplefile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class  SDFile extends Activity {
	
	private EditText fileName;
	private EditText content;
	private TextView textContent;
	private static final String TAG = "simplefile";
	 SDCardListener myfilelistener = new SDCardListener("/sdcard");   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        fileName = (EditText) this.findViewById(R.id.fileName);
        content  = (EditText) this.findViewById(R.id.content);
        textContent = (TextView) this.findViewById(R.id.textContent);
        Button saveBtn = (Button)this.findViewById(R.id.saveButton);
        Button viewBtn = (Button)this.findViewById(R.id.viewButton);
        
        saveBtn.setOnClickListener(listener);//���水ť�¼�����
        viewBtn.setOnClickListener(listener);//�鿴��ť�¼�����
       
      
    }
    @Override
    public void onResume(){
    	super.onResume();
    	//��ʼ����   
        myfilelistener.startWatching();   

    }
    @Override
    public void onPause(){
    	super.onPause();
    	 myfilelistener.stopWatching();
    }
    private View.OnClickListener listener = new View.OnClickListener(){
    	    	
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Button button     = (Button) v;
			String nameStr    = fileName.getText().toString().trim();
	        String contentStr = content.getText().toString();
	        
	        switch(button.getId())
	        {
	        	case R.id.saveButton :
	    	        int resId_s         = R.string.success;
	    	        OutputStream fileOS = null;
	    			try {
	    				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){ 
	    				FileOutputStream outstream=new FileOutputStream("/sdcard/"+nameStr+".txt");
	    				outstream.write(contentStr.getBytes());
	    				outstream.close();
	    				}
	    				else
	    					return;
	    				
	    			} catch (Exception e) {
	    				resId_s = R.string.failure;
	    				e.printStackTrace();
	    			}
	    			//������ʾ
	    			Toast.makeText(SDFile.this, resId_s, Toast.LENGTH_LONG).show();
	    			Log.i(TAG, nameStr);
	    			Log.i(TAG, contentStr);
	    			break;
	        	case R.id.viewButton :
	        		int resId_v 	   = R.string.readSucc;
	        		InputStream fileIS = null;
	        		String contentSt   = null;
					try {
						if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){ 
					         File sdCardDir = Environment.getExternalStorageDirectory();//��ȡSDCardĿ¼ 
					         File saveFile = new File(sdCardDir, nameStr+".txt"); 
					         FileInputStream inStream = new FileInputStream(saveFile);
					       ByteArrayOutputStream oStream = new ByteArrayOutputStream();
							byte[] buffer = new byte[1024];
							int len = -1;
							while((len = inStream.read(buffer)) != -1){
								oStream.write(buffer, 0, len);
							}
							contentSt = oStream.toString();
							//contentSt = oStream.toString();
							oStream.close();
							inStream.close();
						}
						else{
							Toast.makeText(SDFile.this, "SD��������", Toast.LENGTH_LONG).show();
							
						}
						
						
						
					} catch (Exception e) {
						resId_v = R.string.readFail;
						e.printStackTrace();
						return;
					}
					textContent.setText(contentSt);
					Log.i(TAG, contentSt);
					Toast.makeText(SDFile.this, resId_v, Toast.LENGTH_LONG).show();
					Log.i(TAG, nameStr);
					break;
	        }

		}
    	   	
    };
    
}
