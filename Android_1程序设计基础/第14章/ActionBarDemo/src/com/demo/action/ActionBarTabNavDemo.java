package com.demo.action;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class ActionBarTabNavDemo  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.action_bar_tabs);
        final ActionBar bar = getActionBar();
       
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
    	new MenuInflater(this).inflate(R.menu.menu, menu);
    	
    	
		return (super.onCreateOptionsMenu(menu));

    }

    public void onAddTab(View v) {
        final ActionBar bar = getActionBar();
        final int tabCount = bar.getTabCount();
        final String text = "Tab " + tabCount;
        bar.addTab(bar.newTab()
                .setText(text)
                .setTabListener(new TabListener(new TabContentFragment(text))));
    }

    public void onRemoveTab(View v) {
        final ActionBar bar = getActionBar();
        bar.removeTabAt(bar.getTabCount() - 1);
    }

    public void onToggleTabs(View v) {
        final ActionBar bar = getActionBar();

        if (bar.getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS) {
            bar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE, ActionBar.DISPLAY_SHOW_TITLE);
        } else {
            bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        }
    }

    public void onRemoveAllTabs(View v) {
        getActionBar().removeAllTabs();
    }

    /**
     * A TabListener receives event callbacks from the action bar as tabs
     * are deselected, selected, and reselected. A FragmentTransaction
     * is provided to each of these callbacks; if any operations are added
     * to it, it will be committed at the end of the full tab switch operation.
     * This lets tab switches be atomic without the app needing to track
     * the interactions between different tabs.
     */
    private class TabListener implements ActionBar.TabListener {
        private TabContentFragment mFragment;

        public TabListener(TabContentFragment fragment) {
            mFragment = fragment;
        }

        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            ft.add(R.id.fragment_content, mFragment, mFragment.getText());
        }

        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            ft.remove(mFragment);
        }

        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            Toast.makeText(ActionBarTabNavDemo.this, "Reselected!", Toast.LENGTH_SHORT).show();
        }

    }

    private class TabContentFragment extends Fragment {
        private String mText;

        public TabContentFragment(String text) {
            mText = text;
        }

        public String getText() {
            return mText;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View fragView = inflater.inflate(R.layout.action_bar_tab_content, container, false);

            TextView text = (TextView) fragView.findViewById(R.id.text);
            text.setText(mText);

            return fragView;
        }

    }
}
