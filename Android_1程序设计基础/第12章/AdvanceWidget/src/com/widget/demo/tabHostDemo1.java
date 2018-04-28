package com.widget.demo;




import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class tabHostDemo1 extends TabActivity {
	/** Called when the activity is first created. */
	private TabHost tabHost;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		tabHost = this.getTabHost();
		LayoutInflater li = LayoutInflater.from(this);
		li.inflate(R.layout.tabhost1, tabHost.getTabContentView(), true);
		tabHost.addTab(tabHost.newTabSpec("Tab_1").setContent(R.id.tab1)
				.setIndicator("TAB1",
						this.getResources().getDrawable(R.drawable.android)));
		tabHost.addTab(tabHost.newTabSpec("Tab_2").setContent(R.id.tab2)
				.setIndicator("TAB2",
						this.getResources().getDrawable(R.drawable.monkey)));
		tabHost.addTab(tabHost.newTabSpec("Tab_3").setContent(R.id.tab3)
				.setIndicator("TAB3",
						this.getResources().getDrawable(R.drawable.panda)));
		tabHost.setCurrentTab(1);
//		tabHost.setBackgroundColor(Color.GRAY);
		tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

			public void onTabChanged(String tabId) {
				Dialog dialog = new AlertDialog.Builder(tabHostDemo1.this)
						.setTitle("提示").setMessage(
								"选中了" + tabId + "选项卡").setIcon(R.drawable.icon).setPositiveButton("确定", new DialogInterface.OnClickListener(){

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										
									}
									
								}).create();
				dialog.show();

			}

		});
	}
}

