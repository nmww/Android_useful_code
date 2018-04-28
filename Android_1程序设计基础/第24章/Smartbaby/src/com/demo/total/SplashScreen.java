package com.demo.total;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

public class SplashScreen extends Activity {

	public class SplashView extends View {

		private Context mContext;

		public SplashView(Context context) {
			super(context);
			mContext = context;
		}

		public boolean onTouchEvent(MotionEvent event) {
			try {
				mContext.startActivity(new Intent("com.demo.total.CLEARSPLASH"));
				return (super.onTouchEvent(event));
			} finally {
				finish();
			}
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: if current game is paused then don't display this screen,
		// simply start game activity
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		SplashView mainView = new SplashView(this);
		mainView.setBackgroundResource(R.drawable.background);
		// setContentView(R.layout.splash);
		setContentView(mainView);
	}

}
