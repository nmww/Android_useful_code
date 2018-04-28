package com.demo.provider;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

public class main extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ContentResolver cr = this.getContentResolver();
		/*ContentValues cv = new ContentValues();
		cv.put("name", "android 程序设计基础");
		cr.insert(TestContentProviderMetaData.BookTableMetaData.CONTENT_URI, cv);
		cv.put("name", "Java EE 编程技术" +
				"");
		cr.insert(TestContentProviderMetaData.BookTableMetaData.CONTENT_URI, cv);*/
		Log.i("test", "begin load data");
		Cursor cursor = cr.query(
				TestContentProviderMetaData.BookTableMetaData.CONTENT_URI,
				null, null, null, null);
		// 获得_ID字段的索引
		int idIndex = cursor
				.getColumnIndex(TestContentProviderMetaData.BookTableMetaData._ID);
		// 获得Name字段的索引
		int nameIndex = cursor
				.getColumnIndex(TestContentProviderMetaData.BookTableMetaData.BOOK_NAME);
		String result = "";
		String tempid=null;
		// 遍历Cursor提取数据
		for (cursor.moveToFirst(); (!cursor.isAfterLast()); cursor.moveToNext()) {
			result = result + cursor.getString(idIndex) + "\t";
			result = result + "    " + cursor.getString(nameIndex) + "\t\n";
			if(tempid==null)tempid=cursor.getString(idIndex);
		}
		Log.i("test", result);
		Log.i("test", "begin update data");
		Uri myURI= Uri.withAppendedPath(TestContentProviderMetaData.BookTableMetaData.CONTENT_SINGLE_URI, tempid);
		ContentValues values = new ContentValues();
		values.put("author", "haoyulong");
		values.put("name", "Java EE 编程技术"); 
		cr.insert(myURI, values);
		
	}
}