package com.demo.inflatmenu;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class main extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		new MenuInflater(getApplication()).inflate(R.menu.sample, menu);

		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
          if(item.getItemId()==R.id.swap){
        	  Intent i=new Intent(main.this,Another.class);
        	  startActivity(i);
        	  return true;
          }
		String message = (String) (item.getTitle());
        
		if (message != null) {
			Toast.makeText(this, "你选择穿越回到" + message, Toast.LENGTH_SHORT)
					.show();

			return (true);
		}

		return (super.onOptionsItemSelected(item));
	}
}