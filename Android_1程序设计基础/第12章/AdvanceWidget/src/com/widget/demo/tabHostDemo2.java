package com.widget.demo;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

public class tabHostDemo2  extends Activity {
	/** Called when the activity is first created. */
	private TabHost tabHost;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabhost2);
		try{
			tabHost = (TabHost) this.findViewById(R.id.TabHost01);
			tabHost.setup();
			
			tabHost.addTab(tabHost.newTabSpec("tab_1")
					.setContent(R.id.LinearLayout1)
					.setIndicator("TAB1",this.getResources().getDrawable(R.drawable.android)));
			tabHost.addTab(tabHost.newTabSpec("tab_2")
					.setContent(R.id.LinearLayout2).setIndicator("TAB2",
							this.getResources().getDrawable(R.drawable.monkey)));
			tabHost.addTab(tabHost.newTabSpec("tab_3")
					.setContent(R.id.LinearLayout3).setIndicator("TAB3",
							this.getResources().getDrawable(R.drawable.panda)));
			tabHost.setCurrentTab(1);
		}catch(Exception ex){
			ex.printStackTrace();
			Log.d("EXCEPTION", ex.getMessage());
		}

	}
}
