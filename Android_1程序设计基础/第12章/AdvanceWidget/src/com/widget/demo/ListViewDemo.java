package com.widget.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewDemo extends Activity {

	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		listView = new ListView(this);

		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData()));

		setContentView(listView);

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
