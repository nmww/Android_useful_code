package com.demo.custompreference;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
public class MySeekBarPreference extends DialogPreference {
	private Context context;
	private SeekBar sensitivityLevel = null;
	private LinearLayout layout = null;
	public MySeekBarPreference(Context context, AttributeSet attrs) {

		super(context, attrs);
		this.context = context;
		persistInt(10);
	}
	protected void onPrepareDialogBuilder(Builder builder) {
		// 添加布局
		layout = new LinearLayout(context);
		layout.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT)); // 布局属性
		layout.setMinimumWidth(400); // 布局的最小宽度
		layout.setPadding(20, 20, 20, 20); // 上下左右的Padding
		// 添加SeekBar
		sensitivityLevel = new SeekBar(context);
		sensitivityLevel.setMax(100); // 最大值
		sensitivityLevel.setLayoutParams(new ViewGroup.LayoutParams(
			ViewGroup.LayoutParams.FILL_PARENT,
			ViewGroup.LayoutParams.WRAP_CONTENT)); // SeekBar的布局属性
		sensitivityLevel.setProgress(getPersistedInt(10)); // 设置默认值
		layout.addView(sensitivityLevel); // 把SeekBar加到 layout的布局中
		builder.setView(layout);
	}
	protected void onDialogClosed(boolean positiveResult) {
		if (positiveResult) {
			persistInt(sensitivityLevel.getProgress()); // 保存SeekBar的值
		}
		super.onDialogClosed(positiveResult);
	}
}

