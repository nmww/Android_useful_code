package com.demo;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class main extends Activity {
	    Button btn1,btn2,btn3,btn4,btn5;   
	    static final String User_ACTION = "com.testBroadcastReceiver.MyAction";   
	    static final String User_ACTION2 = "com.testBroadcastReceiver.MyAction2";   
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {   
	        super.onCreate(savedInstanceState);   
	        setContentView(R.layout.main);   
	        btn1=(Button)this.findViewById(R.id.Button1);   
	        btn1.setOnClickListener(new ClickEvent());   
	        btn2=(Button)this.findViewById(R.id.Button2);   
	        btn2.setOnClickListener(new ClickEvent());   
	        btn3=(Button)this.findViewById(R.id.Button3);   
	        btn3.setOnClickListener(new ClickEvent());   
	        btn4=(Button)this.findViewById(R.id.Button4);   
	        btn4.setOnClickListener(new ClickEvent());   
	        btn5=(Button)this.findViewById(R.id.Button5);   
	        btn5.setOnClickListener(new ClickEvent());   
	        
	    }   
	    class ClickEvent implements View.OnClickListener{   
	        public void onClick(View v) {   
	            if(v==btn1)//发送普通广播  
	            {   
	                Intent intent = new Intent(User_ACTION);   
	                sendBroadcast(intent);   
	            }   
	            else if(v==btn2)//发送顺序广播 
	            {   
	            	Intent intent = new Intent(User_ACTION);   
	                sendOrderedBroadcast(intent,null);    
	            }   
	            else if(v==btn3)//发送持续广播
	            
	            {   
	            	Intent intent = new Intent(User_ACTION);   
	                sendStickyBroadcast(intent);    
	               
	            }   
	            else if(v==btn4)//动态注册Broadcast receiver
	            {
	            	IntentFilter it=new IntentFilter();
	            	it.addAction(User_ACTION);
	            	it.setPriority(10);
	            	registerReceiver (mydynamic,it);
	            	//Toast.makeText(getParent(), "消息处理器注册成功", 1000);
	            	IntentFilter it2=new IntentFilter();
	            	it2.addAction(User_ACTION);
	            	it2.setPriority(4);
	            	registerReceiver (mydynamic2,it2);
	            }
	            else if(v==btn5)//发送包含数据的intent
	            {
	            	Intent intent = new Intent(User_ACTION2);  
	            	intent.putExtra("name", "hyl");
	            	 sendBroadcast(intent);   
	             }
	            }  
	        /*  
		     * 接收动态注册广播的BroadcastReceiver  
		     */  
		    private BroadcastReceiver mydynamic = new BroadcastReceiver() {   
		           
		        public void onReceive(Context context, Intent intent) {   
		            String action = intent.getAction();   
		            Toast.makeText(context, "动态MyReceive接收到:"+action, 1000).show(); 
		            Log.i("MyReceive_dynic","current priority is 10");
		            Bundle b=new Bundle();
		            b.putString("name", "hyl");
		            setResultExtras(b);
		           
		        }   
		    };   
	           
	    }   
	    /*  
	     * 接收动态注册广播的BroadcastReceiver  
	     */  
	    private BroadcastReceiver mydynamic2 = new BroadcastReceiver() {   
	           
	        public void onReceive(Context context, Intent intent) {   
	            String action = intent.getAction();   
	            Toast.makeText(context, "动态MyReceive2接收到:"+action, 1000).show(); 
	            Log.i("MyReceive_dynic2","current priority is 4");
	            Bundle b=new Bundle();
	            b.putString("name", "hyl");
	            setResultExtras(b);
	           
	        }   
	    };   
           
   
}
