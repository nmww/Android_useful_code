package com.demo.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

public class ShortCut extends Activity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		final Intent intent = getIntent();
		final String action = intent.getAction();

		if (Intent.ACTION_CREATE_SHORTCUT.equals(action)) {
			setupShortcut();
			finish();
			return;
		}
		setContentView(R.layout.shortcut);

	}

	private void setupShortcut() {
		Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
		shortcutIntent.setClassName(this, "com.demo.intent.main");
		Intent intent = new Intent();
		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "shortcutsample");
		Parcelable iconResource = Intent.ShortcutIconResource.fromContext(this,
				R.drawable.icon);
		intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);
		setResult(RESULT_OK, intent);
	}
}
