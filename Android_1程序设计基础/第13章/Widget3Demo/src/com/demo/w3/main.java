package com.demo.w3;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

public class main extends Activity {
	PopupMenu pop=null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void onpopupmenu(View button){
    	pop=new PopupMenu(this,button);
    	pop.getMenuInflater().inflate(R.menu.basic, pop.getMenu());
    	pop.setOnMenuItemClickListener(new OnMenuItemClickListener(){

			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), arg0.getTitle(), Toast.LENGTH_LONG).show();
				return false;
			}
    		
    	}
    	);
    	pop.show();
    	
    }
    public void onpopupmenu2(View button){
    	if(pop!=null)
    	pop.show();
    	
    }
}