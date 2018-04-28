package com.demo.intent;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main extends Activity {
	public static final String User_ACTION = "com.demo.intent.Useraction";
	public static final String User_ACTION2 = "com.demo.intent.Useraction2";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button firstbtn = (Button) findViewById(R.id.activity1);
		Button firstbtnservice = (Button) findViewById(R.id.service1);
		Button secondbtn = (Button) findViewById(R.id.activity2);
		Button secondbtnservice = (Button) findViewById(R.id.service2);
		Button choose = (Button) findViewById(R.id.Button01);
		Button create = (Button) findViewById(R.id.Button_createshortcut);
		Button delete = (Button) findViewById(R.id.Button_deleteshortcut);
		firstbtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// ��ʽ���� Activity One
				Intent i = new Intent(getApplicationContext(),
						ActivityOne.class);
				Bundle bd = new Bundle();
				Student s = new Student();
				HashMap<String, String> sc = new HashMap<String, String>();
				sc.put("english", "98");
				sc.put("computer", "75");
				s.name = "tom";
				s.scores = sc;
				ArrayList<Student> al = new ArrayList<Student>();
				al.add(s);
				i.putParcelableArrayListExtra("attachment2", al);
				bd.putString("username", "android");
				i.putExtra("attachment", bd);
				startActivity(i);
			}
		});

		firstbtnservice.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(), ServiceOne.class);
				startService(i);
			}

		});

		secondbtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent intent = new Intent();
				// intent.setAction(User_ACTION);
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://www.google.com.hk"));
				startActivity(intent);

				startActivity(intent);
			}
		});
		secondbtnservice.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(User_ACTION2);

				startService(intent);
			}
		});
		choose.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

				intent.setDataAndType(Uri.parse("file:///sdcard/a.mp3"),
						"audio/music1");
				// startActivity(intent);
				startActivity(Intent.createChooser(intent, "ѡ�����ֲ�����"));

			}

		});
		create.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent shortcut = new Intent(
						"com.android.launcher.action.INSTALL_SHORTCUT");

				// ��ݷ�ʽ������
				shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
						getString(R.string.app_name));
				// �������ظ�����
				shortcut.putExtra("duplicate", false);

				// ָ����ǰ��ActivityΪ��ݷ�ʽ�����Ķ���: �� com.everest.video.VideoPlayer
				// �������ΪIntent����һ��action����������(����װ��ж��ʱ�ò�������һ��)
				String action = "com.android.action.test";
				Intent respondIntent = new Intent(main.this, main.this
						.getClass());
				respondIntent.setAction(action);
				shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, respondIntent);
				sendBroadcast(shortcut);

			}

		});
		delete.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent shortcut = new Intent(
				"com.android.launcher.action.UNINSTALL_SHORTCUT");

		// ��ݷ�ʽ������
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
				getString(R.string.app_name));
	
		// ָ����ǰ��ActivityΪ��ݷ�ʽ�����Ķ���: �� com.everest.video.VideoPlayer
		// �������ΪIntent����һ��action����������(����װ��ж��ʱ�ò�������һ��)
		String action = "com.android.action.test";
		Intent respondIntent = new Intent(main.this, main.this
				.getClass());
		respondIntent.setAction(action);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, respondIntent);
		sendBroadcast(shortcut);


			}

		});
	}
}
