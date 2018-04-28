package com.demo.simplefile;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class  main extends Activity {
	
	private EditText fileName;
	private EditText content;
	private TextView textContent;
	private static final String TAG = "simplefile";
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        fileName = (EditText) this.findViewById(R.id.fileName);
        content  = (EditText) this.findViewById(R.id.content);
        textContent = (TextView) this.findViewById(R.id.textContent);
        Button saveBtn = (Button)this.findViewById(R.id.saveButton);
        Button viewBtn = (Button)this.findViewById(R.id.viewButton);
        
        saveBtn.setOnClickListener(listener);//保存按钮事件监听
        viewBtn.setOnClickListener(listener);//查看按钮事件监听
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
				
	    				fileOS  = main.this.openFileOutput(nameStr+".txt",
	    						Context.MODE_APPEND);
	    				fileOS.write(contentStr.getBytes());
	    				fileOS.close();
	    				
	    			} catch (Exception e) {
	    				resId_s = R.string.failure;
	    				e.printStackTrace();
	    			}
	    			//界面提示
	    			Toast.makeText(main.this, resId_s, Toast.LENGTH_LONG).show();
	    			Log.i(TAG, nameStr);
	    			Log.i(TAG, contentStr);
	    			break;
	        	case R.id.viewButton :
	        		int resId_v 	   = R.string.readSucc;
	        		InputStream fileIS = null;
	        		String contentSt   = null;
					try {
						fileIS    = main.this.openFileInput(nameStr+".txt");
						ByteArrayOutputStream oStream = new ByteArrayOutputStream();
						byte[] buffer = new byte[1024];
						int len = -1;
						while((len = fileIS.read(buffer)) != -1){
							oStream.write(buffer, 0, len);
						}
						contentSt = oStream.toString("GBK");
						//contentSt = oStream.toString();
						oStream.close();
						fileIS.close();
					} catch (Exception e) {
						resId_v = R.string.readFail;
						e.printStackTrace();
						return;
					}
					textContent.setText(contentSt);
					Log.i(TAG, contentSt);
					Toast.makeText(main.this, resId_v, Toast.LENGTH_LONG).show();
					Log.i(TAG, nameStr);
					break;
	        }

		}
    	   	
    };
    
}