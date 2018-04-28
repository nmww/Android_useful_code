package com.widget.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CompactListDemo extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				R.layout.listitem,

				new String[] { "title", "info", "img" },

				new int[] { R.id.title, R.id.info, R.id.img });

		setListAdapter(adapter);
		

	}

	private List<Map<String, Object>> getData() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("title", "Android");

		map.put("info", "google product");

		map.put("img", R.drawable.android);

		list.add(map);

		map = new HashMap<String, Object>();

		map.put("title", "Monkey");

		map.put("info", "i like");

		map.put("img", R.drawable.monkey);

		list.add(map);

		map = new HashMap<String, Object>();

		map.put("title", "Panda");

		map.put("info", "sign of china");

		map.put("img", R.drawable.panda);

		list.add(map);

		return list;

	}

}
