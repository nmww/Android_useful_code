package com.demo.database;

import java.io.File;

import android.app.Activity;   
import android.app.AlertDialog;   
import android.content.ContentValues;   
import android.content.DialogInterface;   
import android.database.Cursor;   
import android.database.sqlite.SQLiteDatabase;   
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;   
import android.widget.ListAdapter;   
import android.widget.ListView;   
import android.widget.SimpleCursorAdapter;   
import android.widget.TextView;
  
public class main extends Activity {   
    /** Called when the activity is first created. */  
    private ListView lv = null;   
    private TextView tv=null;
    private SQLiteDatabase mSQLiteDatabase = null;  
    private SQLiteStatement st=null;
    private static final String DATABASE_NAME = "Test.db";   
    private static final String TABLE_NAME = "table_test";   
    private static final String COLUMN_ID = "_id";// INTEGER PRIMARY KEY   
    private static final String COLUMN_NAME = "name";// TEXT   
    private static final String COLUMN_AGE = "age";// INTEGER   
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "  
            + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY,"  
            + COLUMN_NAME + " TEXT," + COLUMN_AGE + " INTEGER)";   
	private File path = new File("/sdcard/myfile");// ����Ŀ¼
	private File f = new File("/sdcard/myfile/test.db");// �����ļ�

    @Override  
    public void onCreate(Bundle savedInstanceState) {   
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.main);   
        lv = (ListView) this.findViewById(R.id.ListView01);   
        tv = (TextView) this.findViewById(R.id.counttip); 
        try {   
           // mSQLiteDatabase = this.openOrCreateDatabase(DATABASE_NAME,   
           //         Activity.MODE_PRIVATE, null);   
        	if (!path.exists()) {// Ŀ¼���ڷ���false
    			path.mkdirs();// ����һ��Ŀ¼
    		}
    		if (!f.exists())f.createNewFile();//�����ļ�  
    		mSQLiteDatabase=SQLiteDatabase.openOrCreateDatabase(f, null); 


        } catch (Exception ex) {   
            this.ShowDialog("�򿪻��ߴ������ݿ��쳣:" + ex.getMessage());   
        }   
        try {   
            mSQLiteDatabase.execSQL(CREATE_TABLE);  
            String str = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAME + ","  
            + COLUMN_AGE + ")VALUES(?,?)";   
             st= mSQLiteDatabase.compileStatement(str);
        } catch (Exception ex) {   
            this.ShowDialog("�������쳣:" + ex.getMessage());   
        }   
         this.InsertData3();   
         this.InsertData4();   
        // this.InsertData2();   
        // this.AddData();   
        // this.UpdateData();   
        // this.UpdateData2();   
        // this.UpdateData3();   
        // this.DeleteData();   
        // this.DeleteData2();   
        // this.DeleteData3();   
        this.SelectData();   
    }   
  
    @Override  
    protected void onPause() {         
        super.onPause();   
        mSQLiteDatabase.close();// �ر����ݿ�   
    }   
  
    /**  
     * ��������-execSQL  
     */  
    private void InsertData() {   
        try {   
            String str = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAME + ","  
                    + COLUMN_AGE + ")VALUES('����',30)";   
            mSQLiteDatabase.execSQL(str);   
        } catch (Exception ex) {   
            this.ShowDialog("���������쳣:" + ex.getMessage());   
        }   
  
    }   
  
    /**  
     * ��������-execSQL  
     */  
    private void InsertData2() {   
        try {   
            String str = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAME + ","  
                    + COLUMN_AGE + ")VALUES(?,?)";   
            Object[] ob = new Object[] { "����", 50 };   
            mSQLiteDatabase.execSQL(str, ob);   
        } catch (Exception ex) {   
            this.ShowDialog("���������쳣:" + ex.getMessage());   
        }   
    }   
    /**  
     * ����statement��������-execSQL  
     */  
    private void InsertData3() { 
    	
        try { 
        	
           
           st.bindString(1, "����");
           st.bindLong(2, 50);
            st.execute();
             
            //mSQLiteDatabase.execSQL(str, ob);   
        } catch (Exception ex) {   
            this.ShowDialog("���������쳣:" + ex.getMessage());   
        }   
    }   
    /**  
     * ����statement��������-execSQL  
     */  
    private void InsertData4() { 
    	
        try { 
        	
           
           st.bindString(1, "�ܾ�");
           st.bindLong(2, 33);
            st.execute();
             
            //mSQLiteDatabase.execSQL(str, ob);   
        } catch (Exception ex) {   
            this.ShowDialog("���������쳣:" + ex.getMessage());   
        }   
    }   
  
    /**  
     * ��������-insert() nullColumnHack�����������Ҫ����һ��������SQL��׼����������������о�Ϊ�յ�һ�����ݣ�  
     * ���Ե������initialValuesֵΪ�ջ���Ϊ0ʱ  
     * ����nullColumnHack����ָ�����лᱻ����ֵΪNULL�����ݣ�Ȼ���ٽ����в��뵽���С�  
     */  
    private void AddData() {   
        try {   
            ContentValues cv = new ContentValues();   
            cv.put(COLUMN_NAME, "����");   
            cv.put(COLUMN_AGE, 40);   
            // long num = mSQLiteDatabase.insert(TABLE_NAME, COLUMN_NAME, cv);   
            long num = mSQLiteDatabase.insertOrThrow(TABLE_NAME, null, cv);   
            this.setTitle("num==" + num);   
        } catch (Exception ex) {   
            this.ShowDialog("���������쳣:" + ex.getMessage());   
        }   
  
    }   
  
    /**  
     * ��������  
     */  
    private void UpdateData() {   
        try {   
            String str = "UPDATE " + TABLE_NAME + " SET " + COLUMN_AGE   
                    + " = 25 WHERE _id=1";   
            mSQLiteDatabase.execSQL(str);   
        } catch (Exception ex) {   
            this.ShowDialog("���������쳣:" + ex.getMessage());   
        }   
    }   
  
    /**  
     * ��������  
     */  
    private void UpdateData2() {   
        try {   
            String str = "UPDATE " + TABLE_NAME + " SET " + COLUMN_AGE   
                    + " = ? WHERE _id=?";   
            Object[] Ob = new Object[] { 33, 2 };   
            mSQLiteDatabase.execSQL(str, Ob);   
        } catch (Exception ex) {   
            this.ShowDialog("���������쳣:" + ex.getMessage());   
        }   
    }   
  
    /**  
     * ��������  
     */  
    private void UpdateData3() {   
        try {   
            ContentValues cv = new ContentValues();   
            cv.put(COLUMN_NAME, "����4");   
            cv.put(COLUMN_AGE, 43);   
            int num = mSQLiteDatabase.update(TABLE_NAME, cv,   
                    COLUMN_NAME + "=?", new String[] { "����" });   
            this.setTitle("�޸�����num=" + num);   
        } catch (Exception ex) {   
            this.ShowDialog("���������쳣:" + ex.getMessage());   
        }   
    }   
  
    /**  
     * ɾ������  
     */  
    private void DeleteData() {   
        try {   
            String str = "DELETE FROM " + TABLE_NAME + " WHERE _id=3";   
            mSQLiteDatabase.execSQL(str);   
        } catch (Exception ex) {   
            this.ShowDialog("ɾ�������쳣:" + ex.getMessage());   
        }   
    }   
  
    /**  
     * ɾ������  
     */  
    private void DeleteData2() {   
        try {   
            String str = "DELETE FROM " + TABLE_NAME + " WHERE _id=?";   
            mSQLiteDatabase.execSQL(str, new Object[] { 2 });   
        } catch (Exception ex) {   
            this.ShowDialog("ɾ�������쳣:" + ex.getMessage());   
        }   
    }   
  
    /**  
     * ɾ������  
     */  
    private void DeleteData3() {   
        try {   
            int num = mSQLiteDatabase.delete(TABLE_NAME, "_id=1", null);   
            this.setTitle("ɾ������num=" + num);   
        } catch (Exception ex) {   
            this.ShowDialog("ɾ�������쳣:" + ex.getMessage());   
        }   
    }   
  
    /**  
     * ��ѯ����  
     */  
    private void SelectData() {   
        try {   
            String sql = "SELECT * FROM " + TABLE_NAME;   
            Cursor cursor = mSQLiteDatabase.rawQuery(sql,null);   
            // Cursor cursor = mSQLiteDatabase.query(TABLE_NAME, new String[] {   
            // COLUMN_ID, COLUMN_NAME, COLUMN_AGE }, COLUMN_NAME + "=?",   
            // new String[] { "����" }, null, null, null);   
            if (cursor != null) {   
                ListAdapter adapter = new SimpleCursorAdapter(this,   
                        R.layout.list, cursor, new String[] { COLUMN_ID,   
                                COLUMN_NAME, COLUMN_AGE },   
                        new int[] { R.id.TextView1, R.id.TextView2,   
                                R.id.TextView3 });   
                lv.setAdapter(adapter);   
                
                tv.setText(Integer.toString(cursor.getCount()));
              
            }   
        } catch (Exception ex) {   
            this.ShowDialog("��ѯ�����쳣:" + ex.getMessage());   
        }   
    }   
    /**  
     * ɾ����  
     */  
    private void DropTable(){   
        try{   
            String sql = "DROP TABLE "+TABLE_NAME;   
            mSQLiteDatabase.execSQL(sql);   
        }catch(Exception ex){   
            this.ShowDialog("ɾ�����쳣:"+ex.getMessage());   
        }   
    }   
    /**  
     * ɾ�����ݿ�  
     */  
    private void DropDatabase(){           
        try{   
            this.deleteDatabase(DATABASE_NAME);   
        }catch(Exception ex){   
            this.ShowDialog("ɾ�����ݿ��쳣:"+ex.getMessage());   
        }   
    }   
    /**  
     * ��ʾ�Ի���  
     * @param msg  
     */  
    private void ShowDialog(String msg) {   
        new AlertDialog.Builder(this).setTitle("��ʾ").setMessage(msg)   
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {   
  
                    @Override  
                    public void onClick(DialogInterface dialog, int which) {   
  
                    }   
  
                }).show();   
    }   
}  
