package com.demo.datashare;

import android.app.Activity;   
import android.content.ContentResolver;   
import android.database.Cursor;   
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;   
import android.provider.ContactsContract;
import android.util.Log;   
import android.widget.TextView;


public class main extends Activity {   
    //查询Content Provider时希望返回的列   
    String [] columns = {   
            ContactsContract.Contacts.DISPLAY_NAME,   
            ContactsContract.Contacts._ID,   
            //People._ID,   
            //People.NAME   
    };   
       
    Uri contactUri = ContactsContract.Contacts.CONTENT_URI;   
    TextView tv;   
    //Uri contaUri = Contacts.People.CONTENT_URI;   
  
    @Override  
    public void onCreate(Bundle savedInstanceState) {   
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.main);   
        tv = (TextView)findViewById(R.id.tv);   
        String result = getQueryData();   
        tv.setTextColor(Color.GREEN);   
        tv.setTextSize(20.0f);   
        tv.setText("ID\t姓名\t电话号码\n" + result);   
           
    }   
    //获取联系人列表的信息,返回 String对象   
    public String getQueryData(){   
        String result = "";   
       //获取ContentResolver对象   
        ContentResolver resolver = getContentResolver();   
        Cursor cursor = resolver.query(contactUri, columns, null, null, null);   
        //获得_ID字段的索引   
        int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);   
        //获得Name字段的索引   
        int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);   
        String  phonenumber="";
        //遍历Cursor提取数据   
        for (cursor.moveToFirst();(!cursor.isAfterLast());cursor.moveToNext()) {   
            result = result + cursor.getString(idIndex) + "\t";   
            result = result + cursor.getString(nameIndex)+ "\t";   
           // 获取电话号码
            Cursor phone = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,    
            		           ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+cursor.getString(idIndex), null, null);   
            		   while (phone.moveToNext())    
            		  {   
            	      int numberFieldColumnIndex = phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);   
            	          String temp  = phone.getString(numberFieldColumnIndex);   
            	          phonenumber += " " + temp;   
            		    }   
            		   
            		    phone.close();   
            		    result = result +phonenumber+"\t\n";
            ////
            
        }   
        
        cursor.close();   
        return result;   
    }   
}  
