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
    //��ѯContent Providerʱϣ�����ص���   
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
        tv.setText("ID\t����\t�绰����\n" + result);   
           
    }   
    //��ȡ��ϵ���б����Ϣ,���� String����   
    public String getQueryData(){   
        String result = "";   
       //��ȡContentResolver����   
        ContentResolver resolver = getContentResolver();   
        Cursor cursor = resolver.query(contactUri, columns, null, null, null);   
        //���_ID�ֶε�����   
        int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);   
        //���Name�ֶε�����   
        int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);   
        String  phonenumber="";
        //����Cursor��ȡ����   
        for (cursor.moveToFirst();(!cursor.isAfterLast());cursor.moveToNext()) {   
            result = result + cursor.getString(idIndex) + "\t";   
            result = result + cursor.getString(nameIndex)+ "\t";   
           // ��ȡ�绰����
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
