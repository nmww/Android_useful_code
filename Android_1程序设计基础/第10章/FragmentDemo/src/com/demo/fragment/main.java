package com.demo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
public class main extends Activity {
    /** Called when the activity is first created. */
	static String Tag="Activity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(Tag,"in oncreate()............");
        setContentView(R.layout.main);
        //addFragment();
        Log.i(Tag,"out oncreate()............");
    }
    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
    	Log.i(Tag,"in onDestory()............");
		super.onDestroy();
		
		 Log.i(Tag,"out onDestory()............");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		 Log.i(Tag,"in onPause()............");
		super.onPause();
		 
		  Log.i(Tag,"out onPause()............");
	}
	@Override
	protected void onResume() {
		 Log.i(Tag,"in onResume()............");
		// TODO Auto-generated method stub
		super.onResume();
		 
		  Log.i(Tag,"out onResume()............");
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		  Log.i(Tag,"in onStart()............");
		super.onStart();
		
		  Log.i(Tag,"out onStart()............");
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		 Log.i(Tag,"in onStop()............");
		super.onStop();
		
		 Log.i(Tag,"out onStop()............");
	}
	void addFragment() {
       

        // Instantiate a new fragment.
        Fragment newFragment = new MyFirstFragment();

       
        FragmentTransaction ft = getFragmentManager().beginTransaction();
       // LinearLayout  l=(LinearLayout) this.findViewById(R.id.myui)
        ft.add(R.id.myui,newFragment, "first");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
       
        ft.commit();
    }
}