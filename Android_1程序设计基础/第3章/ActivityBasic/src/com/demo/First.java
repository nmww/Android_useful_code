package com.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View.OnClickListener;

public class First extends Activity {
	static final  String  Activity_ID="First";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //定制窗口标题的代码-start
      /*  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
       
        setContentView(R.layout.main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title);
        //定制窗口标题的代码-end
        //全屏代码-strat
       //  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
       //  setContentView(R.layout.main);
 
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       */
        Log.i(Activity_ID, "oncreate has been called");
        Button finish=(Button)findViewById(R.id.testfinish);
        finish.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();//退出activity
            }
        });
    }
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		 Log.i(Activity_ID, "onDestroy has been called");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 Log.i(Activity_ID, "onPause has been called");
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 Log.i(Activity_ID, "onResume has been called");
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		 Log.i(Activity_ID, "onStart has been called");
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		 Log.i(Activity_ID, "onStop has been called");
	}
    

}