package com.demo.scrollview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class main extends Activity {
	/** Called when the activity is first created. */
	private LinearLayout mLayout;
	private HorizontalScrollView sView;
	private final Handler mHandler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.horizontalscroll);
		// 创建一个线性布局
		mLayout = (LinearLayout) this.findViewById(R.id.LinearLayout);
		// 创建一个ScrollView对象
		sView = (HorizontalScrollView) this.findViewById(R.id.ScrollView);
		Button mBtn = (Button) this.findViewById(R.id.Button);
		mBtn.setOnClickListener(mClickListener);// 添加点击事件监听
	}

	// Button事件监听,当点击第一个按钮时增加一个button和一个textview
	private Button.OnClickListener mClickListener = new Button.OnClickListener() {

		@Override
		public void onClick(View v) {
			TextView tView = new TextView(main.this);// 定义一个TextView
			tView.setText("ScrollView也是一个Layout布局，可以让它内部的数据显示不下的时候出现垂直滚动条，要注意的是不能在ScrollView中放多个组件");// 设置TextView的文本信息

			// 设置线性布局的属性
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.FILL_PARENT
					
);
			mLayout.addView(tView, params);// 添加一个TextView控件
			mHandler.post(mScrollToButton);//传递一个消息进行滚动
		}

	};
	private Runnable mScrollToButton = new Runnable() {

		@Override
		public void run() {
			int off = mLayout.getMeasuredWidth() - sView.getWidth();
			if (off > 0) {
				sView.scrollTo( off,0);// 改变滚动条的位置
			}
		}

	};

}
