package com.demo.thread;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ListProgressDemo extends Activity {
private ListView list=null;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		((Button) findViewById(R.id.load))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View view) {

						data = null;

						data = new ArrayList<String>();

						adapter = null;

						showDialog(PROGRESS_DIALOG);

						new ProgressThread(handler, data).start();

					}

				});
		list=(ListView) findViewById(R.id.listView1);

	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {

		case PROGRESS_DIALOG:

			return ProgressDialog.show(this, "",

			"正在加载,请稍侯...", true);

		default:
			return null;

		}

	}

	private class ProgressThread extends Thread {

		private Handler handler;

		private ArrayList<String> data;

		public ProgressThread(Handler handler, ArrayList<String> data) {

			this.handler = handler;

			this.data = data;

		}

		@Override
		public void run() {

			for (int i = 0; i < 10; i++) {

				data.add("项目"+Integer.toString(i));

				try {

					Thread.sleep(1000);

				} catch (InterruptedException e) {

					Message msg = handler.obtainMessage();

					Bundle b = new Bundle();

					b.putInt("state", STATE_ERROR);

					msg.setData(b);

					handler.sendMessage(msg);

				}

			}

			Message msg = handler.obtainMessage();

			Bundle b = new Bundle();

			b.putInt("state", STATE_FINISH);

			msg.setData(b);

			handler.sendMessage(msg);

		}

	}

	

	private final Handler handler = new Handler(Looper.getMainLooper()) {

		public void handleMessage(Message msg) { // 处理Message，更新ListView

			int state = msg.getData().getInt("state");

			switch (state) {

			case STATE_FINISH:

				dismissDialog(PROGRESS_DIALOG);

				Toast.makeText(getApplicationContext(),

				"加载完成!",

				Toast.LENGTH_LONG)

				.show();

				adapter = new ArrayAdapter<String>(getApplicationContext(),

				android.R.layout.simple_list_item_1,

				data);

				list.setAdapter(adapter);

				break;

			case STATE_ERROR:

				dismissDialog(PROGRESS_DIALOG);

				Toast.makeText(getApplicationContext(),

				"处理过程发生错误!",

				Toast.LENGTH_LONG)

				.show();

				adapter = new ArrayAdapter<String>(getApplicationContext(),

				android.R.layout.simple_list_item_1,

				data);

				list.setAdapter(adapter);

				break;

			default:

			}

		}

	};

	private ArrayAdapter<String> adapter;

	private ArrayList<String> data;

	private static final int PROGRESS_DIALOG = 1;

	private static final int STATE_FINISH = 1;

	private static final int STATE_ERROR = -1;

}
