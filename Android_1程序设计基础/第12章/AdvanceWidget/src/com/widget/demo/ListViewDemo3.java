package com.widget.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewDemo3  extends Activity{

	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		listView = new ListView(this);
        
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_single_choice, getData()));
		  // listView.setItemsCanFocus(false);
        //listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		setContentView(listView);
		//添加点击事件       
		listView.setOnItemClickListener(new OnItemClickListener(){     
		       
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  long arg3) {        
				ArrayList<String> ar=(ArrayList<String>) getData();
				Toast.makeText(getApplicationContext(),  "你选择了第"+arg2+"个Item，item的值是："+ar.get(arg2),    Toast.LENGTH_LONG).show();     
				}              
			}); 
	}

	private List<String> getData() {

		List<String> data = new ArrayList<String>();

		data.add("项目1");

		data.add("项目2");

		data.add("项目3");

		data.add("项目4");

		return data;

	}

	
}
