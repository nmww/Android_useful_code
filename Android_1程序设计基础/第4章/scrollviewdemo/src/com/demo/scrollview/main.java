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
		// ����һ�����Բ���
		mLayout = (LinearLayout) this.findViewById(R.id.LinearLayout);
		// ����һ��ScrollView����
		sView = (HorizontalScrollView) this.findViewById(R.id.ScrollView);
		Button mBtn = (Button) this.findViewById(R.id.Button);
		mBtn.setOnClickListener(mClickListener);// ��ӵ���¼�����
	}

	// Button�¼�����,�������һ����ťʱ����һ��button��һ��textview
	private Button.OnClickListener mClickListener = new Button.OnClickListener() {

		@Override
		public void onClick(View v) {
			TextView tView = new TextView(main.this);// ����һ��TextView
			tView.setText("ScrollViewҲ��һ��Layout���֣����������ڲ���������ʾ���µ�ʱ����ִ�ֱ��������Ҫע����ǲ�����ScrollView�зŶ�����");// ����TextView���ı���Ϣ

			// �������Բ��ֵ�����
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.FILL_PARENT
					
);
			mLayout.addView(tView, params);// ���һ��TextView�ؼ�
			mHandler.post(mScrollToButton);//����һ����Ϣ���й���
		}

	};
	private Runnable mScrollToButton = new Runnable() {

		@Override
		public void run() {
			int off = mLayout.getMeasuredWidth() - sView.getWidth();
			if (off > 0) {
				sView.scrollTo( off,0);// �ı��������λ��
			}
		}

	};

}
