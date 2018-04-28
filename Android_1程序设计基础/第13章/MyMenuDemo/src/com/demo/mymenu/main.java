package com.demo.mymenu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class main extends Activity {
	TextView selection;
	
	public static final int Blue_ID = Menu.FIRST+1;
	public static final int CYAN_ID = Menu.FIRST+2;
	public static final int RED_ID = Menu.FIRST+3;
	public static final int YELLOW_ID = Menu.FIRST+4;
	public static final int GREEN_ID = Menu.FIRST+5;
	public static final int DKGRAY_ID = Menu.FIRST+6;
	public static final int MEGENTA_ID = Menu.FIRST+7;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		
		selection=(TextView)findViewById(R.id.selection);
		
		registerForContextMenu(selection);
	}
	
	
	
@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
																		ContextMenu.ContextMenuInfo menuInfo) {
		populateMenu(menu);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return(applyMenuChoice(item) ||
						super.onContextItemSelected(item));
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		populateMenu(menu);

		return(super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return(applyMenuChoice(item) ||
						super.onOptionsItemSelected(item));
		//return( super.onOptionsItemSelected(item));
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
      //将绿色菜单项禁用
		
       menu.findItem(GREEN_ID).setEnabled(false);
        return true;
    }

	
	private void populateMenu(Menu menu) {
		Intent i=new Intent(main.this,OtherActivity.class);
		
		menu.add(Menu.NONE, RED_ID, Menu.NONE, "红色").setIcon(R.drawable.palette).setAlphabeticShortcut('R').setIntent(i);
		
		menu.add(Menu.NONE, Blue_ID, Menu.NONE, "蓝色").setIcon(R.drawable.palette).setAlphabeticShortcut('B');
		menu.add(Menu.NONE, YELLOW_ID, Menu.NONE, "黄色").setIcon(R.drawable.palette).setAlphabeticShortcut('Y');
		menu.add(Menu.NONE, CYAN_ID, Menu.NONE, "蓝绿").setIcon(R.drawable.palette).setAlphabeticShortcut('C');
		menu.add(Menu.NONE, GREEN_ID, Menu.NONE, "绿色").setIcon(R.drawable.palette).setAlphabeticShortcut('G');
		menu.add(Menu.NONE, DKGRAY_ID, Menu.NONE, "深灰").setIcon(R.drawable.palette).setAlphabeticShortcut('D');
		menu.add(Menu.NONE, MEGENTA_ID, Menu.NONE, "紫罗兰").setIcon(R.drawable.palette).setAlphabeticShortcut('M');
	}
	
	private boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
			case Blue_ID:
				
				
				selection.setBackgroundColor(Color.BLUE);
				return(true);
		
			case CYAN_ID:
				selection.setBackgroundColor(Color.CYAN);
				return(true);
		
			case DKGRAY_ID:
				selection.setBackgroundColor(Color.DKGRAY);
				return(true);
		
			case GREEN_ID:
				selection.setBackgroundColor(Color.GREEN);
				return(true);
		
			case MEGENTA_ID:
				selection.setBackgroundColor(Color.MAGENTA);
				return(true);

			case RED_ID:
				selection.setBackgroundColor(Color.RED);
				return(false);
				
		
			case YELLOW_ID:
				selection.setBackgroundColor(Color.YELLOW);
				return(true);
		}

		return(false);
	}
}