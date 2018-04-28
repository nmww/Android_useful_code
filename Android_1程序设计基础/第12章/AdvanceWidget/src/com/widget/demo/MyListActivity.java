package com.widget.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData()));
		this.getListView().setTextFilterEnabled(true);
		this.getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}

	private List<String> getData() {

		List<String> data = new ArrayList<String>();

		data.add("asdd");

		data.add("bcdddd");

		data.add("zzzzzz");

		data.add("qqqqq");

		return data;

	}
}
