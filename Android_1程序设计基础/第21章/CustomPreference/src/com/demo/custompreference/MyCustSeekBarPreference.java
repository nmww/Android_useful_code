package com.demo.custompreference;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
public class MyCustSeekBarPreference extends Preference implements OnSeekBarChangeListener {
	public static int maximum = 78; // 由于目前是针对字体的所有最大值设成 78
	public static int interval = 1; // 按照 1增长
	private float oldValue = 50;
	private TextView monitorBox;
	public MyCustSeekBarPreference(Context context) {
		super(context);
	}
	public MyCustSeekBarPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public MyCustSeekBarPreference(Context context, AttributeSet attrs,	int defStyle) {
		super(context, attrs, defStyle);
	}
	@Override
	protected View onCreateView(ViewGroup parent) {
		// 定义布局
		LinearLayout layout = new LinearLayout(getContext());
		// 定义属性1
		LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.WRAP_CONTENT,
		LinearLayout.LayoutParams.WRAP_CONTENT);
		params1.gravity = Gravity.CENTER_VERTICAL;
		params1.weight = 1.0f;
		// 定义属性2
		LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(90,LinearLayout.LayoutParams.WRAP_CONTENT);
		params2.gravity = Gravity.CENTER_VERTICAL;
		// 定义属性2
		LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(110,	LinearLayout.LayoutParams.WRAP_CONTENT);
		params3.gravity = Gravity.CENTER;

		layout.setPadding(15, 5, 2, 5);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		TextView view = new TextView(getContext());
		view.setText(getTitle());
		view.setTextSize(18);
		view.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
		view.setGravity(Gravity.LEFT);
		view.setLayoutParams(params1);
		SeekBar bar = new SeekBar(getContext());
		bar.setMax(maximum);
		bar.setProgress((int) this.oldValue);
		bar.setLayoutParams(params2);
		bar.setOnSeekBarChangeListener(this);
		this.monitorBox = new TextView(getContext());
		this.monitorBox.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
		this.monitorBox.setLayoutParams(params3);
		this.monitorBox.setPadding(2, 0, 0, 0);
		this.monitorBox.setText(bar.getProgress() + "");
		this.monitorBox.setTextSize(bar.getProgress()); 
		layout.addView(view);
		layout.addView(bar);
		layout.addView(this.monitorBox);
		layout.setId(android.R.id.widget_frame);
		return layout;
	}
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		progress = Math.round(((float) progress) / interval) * interval;
		if (progress < 10) 
		{
			progress = 10;
		} else if (progress > 72)
		{
			progress = 72;
		}
		if (!callChangeListener(progress)) {
			seekBar.setProgress((int) this.oldValue);
			return;
		}
		seekBar.setProgress(progress);
		this.oldValue = progress;
		this.monitorBox.setText(progress + "");
	this.monitorBox.setTextSize(progress); // 设置 monitorBox 的字体大小跟着SeekBar变化
		updatePreference(progress);
		notifyChanged();
	}
	public void onStartTrackingTouch(SeekBar seekBar) {
	}
	public void onStopTrackingTouch(SeekBar seekBar) {
	}
	@Override
	protected Object onGetDefaultValue(TypedArray ta, int index) {
		int dValue = (int) ta.getInt(index, 50);
		return validateValue(dValue);
	}
	@Override
	protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
		int temp = restoreValue ? getPersistedInt(50) : (Integer) defaultValue;
		if (!restoreValue)
			persistInt(temp);
		this.oldValue = temp;
	}
	private int validateValue(int value) {
		if (value > 72)
			value = 72;
		else if (value < 10)
			value = 10;
		else if (value % interval != 0)
			value = Math.round(((float) value) / interval) * interval;
		return value;
	}
	private void updatePreference(int newValue) {
		SharedPreferences.Editor editor = getEditor();
		editor.putInt(getKey(), newValue);
		editor.commit();
	}
}

