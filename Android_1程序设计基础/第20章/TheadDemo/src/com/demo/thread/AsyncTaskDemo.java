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
			                 
			                // ���ý�������񣬷��Ϊ����   
			               m_pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);   
			                
			             // ����ProgressDialog ����   
			                m_pDialog.setTitle("��ʾ");   
			                 
			                // ����ProgressDialog ��ʾ��Ϣ   
			                m_pDialog.setMessage("���ݼ�����...");   
			                   
			               // ����ProgressDialog ����ͼ��   
			                m_pDialog.setIcon(R.drawable.load);   
			                 
			               // ����ProgressDialog ����������   
			                m_pDialog.setMax(1000);   
			                  
			                 // ����ProgressDialog �Ľ������Ƿ���ȷ   
			               m_pDialog.setIndeterminate(false);   
			             
			                // ��ProgressDialog��ʾ   
			                 m_pDialog.show();   
			                 return m_pDialog;
			
			//return ProgressDialog.show(this, "",	"���ڼ���,���Ժ�...", true);

		default:
			return null;

		}

	}

	private class ProgressTask extends
			AsyncTask<ArrayList<String>, Integer, Integer> {

		/* �÷�������ִ��ʵ�ʵĺ�̨����ǰ��UI thread���á������ڸ÷�������һЩ׼�����������ڽ�������ʾһ���������� */

		@Override
		protected void onPreExecute() {

			// ����ʾProgressDialog

			showDialog(PROGRESS_DIALOG);

		}

		/* ִ����Щ�ܺ�ʱ�ĺ�̨���㹤�������Ե���publishProgress����������ʵʱ��������ȡ� */

		@Override
		protected Integer doInBackground(ArrayList<String>... datas) {

			ArrayList<String> data = datas[0];

			for (int i = 0; i < 1000; i++) {

				data.add("��Ŀ"+Integer.toString(i));
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
          // ���½���
			 m_pDialog.setProgress(values[0]); 
        } 
		/*
		 * ��doInBackground ִ����ɺ�onPostExecute ��������UI thread���ã�
		 * 
		 * ��̨�ļ�������ͨ���÷������ݵ�UI thread.
		 */

		@Override
		protected void onPostExecute(Integer result) {

			int state = result.intValue();

			switch (state) {

			case STATE_FINISH:

				dismissDialog(PROGRESS_DIALOG);

				Toast.makeText(getApplicationContext(),

				"�������!",

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

				"������̷�������!",

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
