package com.demo.thread;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AsyncTaskDemo extends Activity {
	private ListView list = null;
	private ProgressDialog m_pDialog=null;
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

						//adapter = null;

						// showDialog(PROGRESS_DIALOG);

						// new ProgressThread(handler, data).start();
						new ProgressTask().execute(data);

					}

				});
		list = (ListView) findViewById(R.id.listView1);

	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {

		case PROGRESS_DIALOG:
			 m_pDialog = new ProgressDialog(AsyncTaskDemo.this);   
			                 
			                // 设置进度条风格，风格为长形   
			               m_pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);   
			                
			             // 设置ProgressDialog 标题   
			                m_pDialog.setTitle("提示");   
			                 
			                // 设置ProgressDialog 提示信息   
			                m_pDialog.setMessage("数据加载中...");   
			                   
			               // 设置ProgressDialog 标题图标   
			                m_pDialog.setIcon(R.drawable.load);   
			                 
			               // 设置ProgressDialog 进度条进度   
			                m_pDialog.setMax(1000);   
			                  
			                 // 设置ProgressDialog 的进度条是否不明确   
			               m_pDialog.setIndeterminate(false);   
			             
			                // 让ProgressDialog显示   
			                 m_pDialog.show();   
			                 return m_pDialog;
			
			//return ProgressDialog.show(this, "",	"正在加载,请稍侯...", true);

		default:
			return null;

		}

	}

	private class ProgressTask extends
			AsyncTask<ArrayList<String>, Integer, Integer> {

		/* 该方法将在执行实际的后台操作前被UI thread调用。可以在该方法中做一些准备工作，如在界面上显示一个进度条。 */

		@Override
		protected void onPreExecute() {

			// 先显示ProgressDialog

			showDialog(PROGRESS_DIALOG);

		}

		/* 执行那些很耗时的后台计算工作。可以调用publishProgress方法来更新实时的任务进度。 */

		@Override
		protected Integer doInBackground(ArrayList<String>... datas) {

			ArrayList<String> data = datas[0];

			for (int i = 0; i < 1000; i++) {

				data.add("项目"+Integer.toString(i));
				publishProgress(i);

			}

			return STATE_FINISH;

		}
		@Override 
     protected void onCancelled() { 
       super.onCancelled(); 
       } 
		 @Override
       protected void onProgressUpdate(Integer... values) { 
          // 更新进度
			 m_pDialog.setProgress(values[0]); 
        } 
		/*
		 * 在doInBackground 执行完成后，onPostExecute 方法将被UI thread调用，
		 * 
		 * 后台的计算结果将通过该方法传递到UI thread.
		 */

		@Override
		protected void onPostExecute(Integer result) {

			int state = result.intValue();

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
	}

	private ArrayAdapter<String> adapter;

	private ArrayList<String> data;

	private static final int PROGRESS_DIALOG = 1;

	private static final int STATE_FINISH = 1;

	private static final int STATE_ERROR = -1;

}
