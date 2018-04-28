package com.demo.simplefile;

import android.os.FileObserver;
import android.util.Log;

public class SDCardListener extends FileObserver {

	public SDCardListener(String path) {
		super(path);
	}

	@Override
	public void onEvent(int event, String path) {
		switch (event) {

		case FileObserver.CREATE:
			Log.d("Create", "filename:" + path);
			break;

		case FileObserver.ALL_EVENTS:
			Log.d("all", "filename:" + path);
			break;
		}
	}
}
