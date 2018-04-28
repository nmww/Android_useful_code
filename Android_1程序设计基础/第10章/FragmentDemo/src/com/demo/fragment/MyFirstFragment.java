package com.demo.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFirstFragment extends Fragment {
	static String Tag = "MyFirstFragment";

	/**
	 * When creating, retrieve this instance's number from its arguments.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(Tag, "in oncreate()............");
		super.onCreate(savedInstanceState);
        setRetainInstance(true);
		Log.i(Tag, "out oncreate()............");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i(Tag, "in onActivityCreate()............");
		super.onActivityCreated(savedInstanceState);

		Log.i(Tag, "out onActivityCreate()............");
	}

	@Override
	public void onDestroyView() {
		Log.i(Tag, "in onDestroyView()............");
		super.onDestroyView();
		Log.i(Tag, "out onDestroyView()............");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(Tag, "in onDestroy()............");
		super.onDestroy();

		Log.i(Tag, "out onDestroy()............");
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		Log.i(Tag, "in onDetach()............");
		super.onDetach();

		Log.i(Tag, "out onDetach()............");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		Log.i(Tag, "in onPause()............");
		super.onPause();
		Log.i(Tag, "out onPause()............");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		Log.i(Tag, "in onResume()............");
		super.onResume();
		Log.i(Tag, "out onResume()............");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		Log.i(Tag, "in onStart()............");
		super.onStart();
		Log.i(Tag, "out onStart()............");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		Log.i(Tag, "in onStop()............");
		super.onStop();
		Log.i(Tag, "out onStop()............");
	}

	/**
	 * The Fragment's UI is just a simple text view showing its instance number.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(Tag, "in onCreateView()............");
		View v = inflater.inflate(R.layout.firstfragment, container, false);
		Log.i(Tag, "out onCreateView()............");
		return v;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		Log.i(Tag, "in onAttach()............");
		super.onAttach(activity);
		Log.i(Tag, "out onAttach()............");

	}
}
