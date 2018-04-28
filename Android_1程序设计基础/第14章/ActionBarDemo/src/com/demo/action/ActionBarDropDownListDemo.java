package com.demo.action;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;


public class ActionBarDropDownListDemo extends Activity {
	
	OnNavigationListener mOnNavigationListener = new OnNavigationListener() { 
		 
		  String[] strings = new String[]{"one","two"}; 
		 
		  @Override 
		  public boolean onNavigationItemSelected(int position, long itemId) { 
		 
		    ListContentFragment newFragment = new ListContentFragment(); 
		    FragmentTransaction ft =getFragmentManager().beginTransaction(); 
		  
		    ft.replace(R.id.list_content, newFragment, strings[position]); 
		 
		    ft.commit(); 
		    return true; 
		  } 
		};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.action_bar_list);
      
        ActionBar actionBar = getActionBar(); 
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setListNavigationCallbacks(new MyAdapter(this), mOnNavigationListener);

    }
   
    public void onSwitch(View v) {
        final ActionBar bar = getActionBar();

        if (bar.getNavigationMode() == ActionBar.NAVIGATION_MODE_LIST) {
            bar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE, ActionBar.DISPLAY_SHOW_TITLE);
        } else {
            bar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
            bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        }
    }

    private class ListContentFragment extends Fragment {
        private String mText;

        @Override 
        public void onAttach(Activity activity) { 

          super.onAttach(activity); 
          mText = getTag(); 
        } 


        public String getText() {
            return mText;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	TextView text = new TextView(getActivity()); 
            text.setText(mText); 
            return text; 

        }

    }
    private class MyAdapter extends ArrayAdapter<String>
    implements SpinnerAdapter
    {
        public MyAdapter(Context ctx)
        {
            super(ctx,
              android.R.layout.simple_spinner_item, 
              new String[]{"one","two"});
            
            this.setDropDownViewResource(
              android.R.layout.simple_spinner_dropdown_item);
        }
        public View getDropDownView(
          int position, View convertView, ViewGroup parent)
        {
            return super.getDropDownView(
              position, convertView, parent);
        }
    }


}

