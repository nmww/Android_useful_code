package com.demo.total;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameScreen extends Activity {

	private static final int MENU_NEW_GAME = 0;
	private static final int MENU_PREFERENCES = 1;
	private static final int MENU_HELP = 2;

	public static final int DIALOG_PAUSED = 0;

	private static final int GET_PREFERENCES = 1;
	private Intent intent = new Intent("com.demo.total.MUSIC");

	// Logging
	private static final String TAG = "GameScreen";

	private GameView gameView;
	private TextView levelView;

	private boolean mActivityPaused;
	private boolean willVibrate;
	private boolean willPlayMusic;
	private boolean IsPlaying = false;
	SharedPreferences settings;

	private Handler GUIHandler = new Handler() {
		public void handleMessage(Message m) {
			String mode = m.getData().getString("mode");
			if (mode != null && mode.equals("pause")) {
				if (!mActivityPaused) {
					userPaused();
					return;
				}
			}

			int level = m.getData().getInt("level");
			setLevel(levelView, level);
		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Black_NoTitleBar_Fullscreen);

		mActivityPaused = false;

		Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		if (savedInstanceState == null) {
			// creates a new game
			gameView = new GameView(this, TAG, GUIHandler, vibrator, false);
		} else {
			// restores previous game
			gameView = new GameView(this, TAG, GUIHandler, vibrator, true);
			gameView.restoreState(savedInstanceState);
		}

		levelView = new TextView(this);
		setLevel(levelView, 0);
		levelView.setTextSize((float) 20);
		levelView.setWidth(android.view.ViewGroup.LayoutParams.FILL_PARENT);
		levelView.setGravity(Gravity.CENTER);

		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		setContentView(linearLayout);
		linearLayout.addView(levelView);
		linearLayout.addView(gameView);

		// Preferences
		SharedPreferences spc = this.getSharedPreferences(
				"com.demo.total.shapes_preferences", MODE_WORLD_WRITEABLE);
		willVibrate = spc.getBoolean("vibrate_preference", false);
		gameView.setWillVibrate(willVibrate);
		willPlayMusic = spc.getBoolean("music_preference", false);
		// ø™∆Ù±≥æ∞“Ù¿÷
		if (willPlayMusic) {
			Log.i(TAG, "i will play music");
			startService(intent);
			IsPlaying = true;
		}

	}

	private void setLevel(TextView view, int lev) {
		view.setText("Score: " + lev);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		menu.add(Menu.NONE, MENU_NEW_GAME, Menu.NONE, R.string.menu_new_game);
		menu.add(Menu.NONE, MENU_PREFERENCES, Menu.NONE,
				R.string.menu_preferences);
		menu.add(Menu.NONE, MENU_HELP, Menu.NONE, R.string.menu_help);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent launchPreferencesIntent;
		switch (item.getItemId()) {
		case MENU_NEW_GAME:
			gameView.startGame();
			return true;
		case MENU_PREFERENCES:
			launchPreferencesIntent = new Intent().setClass(this,
					ShapePreferences.class);
			startActivityForResult(launchPreferencesIntent, GET_PREFERENCES);
			return true;
		case MENU_HELP:
			launchPreferencesIntent = new Intent().setClass(this,
					HelpScreen.class);
			startActivity(launchPreferencesIntent);
			return true;
		}
		return false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == GET_PREFERENCES) {
			// settings = getPreferences(MODE_PRIVATE);
			SharedPreferences settings = this.getSharedPreferences(
					"com.demo.total_preferences", MODE_WORLD_WRITEABLE);
			willVibrate = settings.getBoolean("vibrate_preference", false);
			gameView.setWillVibrate(willVibrate);
			// ∂‘ «∑Ò≤•∑≈“Ù¿÷≈–∂œ
			willPlayMusic = settings.getBoolean("music_preference", false);
			if (!willPlayMusic && IsPlaying) {
				stopService(intent);
				IsPlaying = false;
			} else if (willPlayMusic && !IsPlaying) {
				startService(intent);
				IsPlaying = true;
			}

		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_PAUSED:
			AlertDialog.Builder pausedDialogBuilder = new AlertDialog.Builder(
					this);
			pausedDialogBuilder.setMessage("Game is paused.");
			pausedDialogBuilder.setPositiveButton("unpause",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							gameView.unPause();
						}
					});
			return (Dialog) pausedDialogBuilder.create();
		}
		return null;

	}

	public void userPaused() {
		showDialog(DIALOG_PAUSED);
	}

	// Activity loses focus.
	@Override
	protected void onPause() {
		super.onPause();
		mActivityPaused = true;
		gameView.pause(); // pause game when Activity pauses
		// πÿ±’±≥æ∞“Ù¿÷
		if (IsPlaying) {
			stopService(intent);
			IsPlaying = false;
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		gameView.saveState(outState);

	}

	@Override
	protected void onRestoreInstanceState(Bundle inState) {
		super.onRestoreInstanceState(inState);
		gameView.restoreState(inState);
		mActivityPaused = false;
	}

}
