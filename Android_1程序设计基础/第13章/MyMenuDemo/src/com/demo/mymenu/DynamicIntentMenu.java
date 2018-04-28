package com.demo.mymenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DynamicIntentMenu extends Activity {
	TextView selection;
	
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
	new MenuInflater(getApplication()).inflate(R.menu.basic, menu);
	
	Intent intent = new Intent("com.demo.menu.DYNAMIC");
    intent.addCategory(Intent.CATEGORY_SELECTED_ALTERNATIVE);//注意这与创建选项菜单时是不同的
    

    // Search and populate the menu with acceptable offering applications.
    menu.addIntentOptions(
         R.id.dynamic,  // Menu group to which new items will be added
         0,      // Unique item ID (none)
         0,      // Order for the items (none)
         this.getComponentName(),   // The current activity name
         null,   // Specific items to place first (none)
         intent, // Intent created above that describes our requirements
         0,      // Additional flags to control items (none)
         null);  // Array of MenuItems that correlate to specific items (none)

    
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return	super.onContextItemSelected(item);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(getApplication()).inflate(R.menu.basic, menu);
		
		Intent intent = new Intent("com.demo.menu.DYNAMIC");
	    intent.addCategory(Intent.CATEGORY_ALTERNATIVE);

	    // Search and populate the menu with acceptable offering applications.
	    menu.addIntentOptions(
	         R.id.dynamic,  // Menu group to which new items will be added
	         0,      // Unique item ID (none)
	         0,      // Order for the items (none)
	         this.getComponentName(),   // The current activity name
	         null,   // Specific items to place first (none)
	         intent, // Intent created above that describes our requirements
	         0,      // Additional flags to control items (none)
	         null);  // Array of MenuItems that correlate to specific items (none)

	    return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		return( super.onOptionsItemSelected(item));
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
      
		
      
        return true;
    }

	
	
}