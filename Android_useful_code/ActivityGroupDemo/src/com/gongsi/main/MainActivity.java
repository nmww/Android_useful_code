package com.gongsi.main;

import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
/**
 * 1.drawable中建立selector
 * 2.values中建立style
 * 3.ActivityGroup 
 * 4.FrameLayout  RadioGroup
 * */
public class MainActivity extends ActivityGroup {
	private FrameLayout container;
	private RadioGroup ogOpterator;

	private void findViews() {
		container = (FrameLayout) findViewById(R.id.container);
		ogOpterator = (RadioGroup) findViewById(R.id.rgOperator);

		ogOpterator.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				LocalActivityManager manager = (LocalActivityManager) getLocalActivityManager();
				Window subActivity = null;
				Intent intent = null;
				container.removeAllViews();
				switch (checkedId) {
				case R.id.rdoAdd:
					intent = new Intent(MainActivity.this, AddActivity.class);
					subActivity = manager.startActivity("add", intent);
					break;
				case R.id.rdoUpdate:
					intent = new Intent(MainActivity.this, UpdateActivity.class);
					subActivity = manager.startActivity("update", intent);
					break;
				case R.id.rdoSearch:
					intent = new Intent(MainActivity.this, SearchActivity.class);
					subActivity = manager.startActivity("search", intent);
					break;
				case R.id.rdoDelete:
					intent = new Intent(MainActivity.this, DeleteActivity.class);
					subActivity = manager.startActivity("delete", intent);
					break;
				case R.id.rdoExit:
					finish();
					return;
				}
				container.addView(subActivity.getDecorView());
			}
		});
		/*启动的时候加载初始页面*/
		container.addView(getLocalActivityManager().startActivity("search",
				new Intent(this, SearchActivity.class)).getDecorView());
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViews();
	}
}