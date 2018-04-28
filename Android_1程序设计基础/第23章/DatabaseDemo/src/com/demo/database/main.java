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
	private File path = new File("/sdcard/myfile");// 创建目录
	private File f = new File("/sdcard/myfile/test.db");// 创建文件

    @Override  
    public void onCreate(Bundle savedInstanceState) {   
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.main);   
        lv = (ListView) this.findViewById(R.id.ListView01);   
        tv = (TextView) this.findViewById(R.id.counttip); 
        try {   
           // mSQLiteDatabase = this.openOrCreateDatabase(DATABASE_NAME,   
           //         Activity.MODE_PRIVATE, null);   
        	if (!path.exists()) {// 目录存在返回false
    			path.mkdirs();// 创建一个目录
    		}
    		if (!f.exists())f.createNewFile();//创建文件  
    		mSQLiteDatabase=SQLiteDatabase.openOrCreateDatabase(f, null); 


        } catch (Exception ex) {   
            this.ShowDialog("打开或者创建数据库异常:" + ex.getMessage());   
        }   
        try {   
            mSQLiteDatabase.execSQL(CREATE_TABLE);  
            String str = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAME + ","  
            + COLUMN_AGE + ")VALUES(?,?)";   
             st= mSQLiteDatabase.compileStatement(str);
        } catch (Exception ex) {   
            this.ShowDialog("创建表异常:" + ex.getMessage());   
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
        mSQLiteDatabase.close();// 关闭数据库   
    }   
  
    /**  
     * 插入数据-execSQL  
     */  
    private void InsertData() {   
        try {   
            String str = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAME + ","  
                    + COLUMN_AGE + ")VALUES('张三',30)";   
            mSQLiteDatabase.execSQL(str);   
        } catch (Exception ex) {   
            this.ShowDialog("插入数据异常:" + ex.getMessage());   
        }   
  
    }   
  
    /**  
     * 插入数据-execSQL  
     */  
    private void InsertData2() {   
        try {   
            String str = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAME + ","  
                    + COLUMN_AGE + ")VALUES(?,?)";   
            Object[] ob = new Object[] { "王五", 50 };   
            mSQLiteDatabase.execSQL(str, ob);   
        } catch (Exception ex) {   
            this.ShowDialog("插入数据异常:" + ex.getMessage());   
        }   
    }   
    /**  
     * 利用statement插入数据-execSQL  
     */  
    private void InsertData3() { 
    	
        try { 
        	
           
           st.bindString(1, "王阳");
           st.bindLong(2, 50);
            st.execute();
             
            //mSQLiteDatabase.execSQL(str, ob);   
        } catch (Exception ex) {   
            this.ShowDialog("插入数据异常:" + ex.getMessage());   
        }   
    }   
    /**  
     * 利用statement插入数据-execSQL  
     */  
    private void InsertData4() { 
    	
        try { 
        	
           
           st.bindString(1, "周军");
           st.bindLong(2, 33);
            st.execute();
             
            //mSQLiteDatabase.execSQL(str, ob);   
        } catch (Exception ex) {   
            this.ShowDialog("插入数据异常:" + ex.getMessage());   
        }   
    }   
  
    /**  
     * 插入数据-insert() nullColumnHack，这个参数需要传入一个列名。SQL标准并不允许插入所有列均为空的一行数据，  
     * 所以当传入的initialValues值为空或者为0时  
     * ，用nullColumnHack参数指定的列会被插入值为NULL的数据，然后再将此行插入到表中。  
     */  
    private void AddData() {   
        try {   
            ContentValues cv = new ContentValues();   
            cv.put(COLUMN_NAME, "李四");   
            cv.put(COLUMN_AGE, 40);   
            // long num = mSQLiteDatabase.insert(TABLE_NAME, COLUMN_NAME, cv);   
            long num = mSQLiteDatabase.insertOrThrow(TABLE_NAME, null, cv);   
            this.setTitle("num==" + num);   
        } catch (Exception ex) {   
            this.ShowDialog("插入数据异常:" + ex.getMessage());   
        }   
  
    }   
  
    /**  
     * 更新数据  
     */  
    private void UpdateData() {   
        try {   
            String str = "UPDATE " + TABLE_NAME + " SET " + COLUMN_AGE   
                    + " = 25 WHERE _id=1";   
            mSQLiteDatabase.execSQL(str);   
        } catch (Exception ex) {   
            this.ShowDialog("更新数据异常:" + ex.getMessage());   
        }   
    }   
  
    /**  
     * 更新数据  
     */  
    private void UpdateData2() {   
        try {   
            String str = "UPDATE " + TABLE_NAME + " SET " + COLUMN_AGE   
                    + " = ? WHERE _id=?";   
            Object[] Ob = new Object[] { 33, 2 };   
            mSQLiteDatabase.execSQL(str, Ob);   
        } catch (Exception ex) {   
            this.ShowDialog("更新数据异常:" + ex.getMessage());   
        }   
    }   
  
    /**  
     * 更新数据  
     */  
    private void UpdateData3() {   
        try {   
            ContentValues cv = new ContentValues();   
            cv.put(COLUMN_NAME, "李四4");   
            cv.put(COLUMN_AGE, 43);   
            int num = mSQLiteDatabase.update(TABLE_NAME, cv,   
                    COLUMN_NAME + "=?", new String[] { "李四" });   
            this.setTitle("修改行数num=" + num);   
        } catch (Exception ex) {   
            this.ShowDialog("更新数据异常:" + ex.getMessage());   
        }   
    }   
  
    /**  
     * 删除数据  
     */  
    private void DeleteData() {   
        try {   
            String str = "DELETE FROM " + TABLE_NAME + " WHERE _id=3";   
            mSQLiteDatabase.execSQL(str);   
        } catch (Exception ex) {   
            this.ShowDialog("删除数据异常:" + ex.getMessage());   
        }   
    }   
  
    /**  
     * 删除数据  
     */  
    private void DeleteData2() {   
        try {   
            String str = "DELETE FROM " + TABLE_NAME + " WHERE _id=?";   
            mSQLiteDatabase.execSQL(str, new Object[] { 2 });   
        } catch (Exception ex) {   
            this.ShowDialog("删除数据异常:" + ex.getMessage());   
        }   
    }   
  
    /**  
     * 删除数据  
     */  
    private void DeleteData3() {   
        try {   
            int num = mSQLiteDatabase.delete(TABLE_NAME, "_id=1", null);   
            this.setTitle("删除行数num=" + num);   
        } catch (Exception ex) {   
            this.ShowDialog("删除数据异常:" + ex.getMessage());   
        }   
    }   
  
    /**  
     * 查询数据  
     */  
    private void SelectData() {   
        try {   
            String sql = "SELECT * FROM " + TABLE_NAME;   
            Cursor cursor = mSQLiteDatabase.rawQuery(sql,null);   
            // Cursor cursor = mSQLiteDatabase.query(TABLE_NAME, new String[] {   
            // COLUMN_ID, COLUMN_NAME, COLUMN_AGE }, COLUMN_NAME + "=?",   
            // new String[] { "李四" }, null, null, null);   
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
            this.ShowDialog("查询数据异常:" + ex.getMessage());   
        }   
    }   
    /**  
     * 删除表  
     */  
    private void DropTable(){   
        try{   
            String sql = "DROP TABLE "+TABLE_NAME;   
            mSQLiteDatabase.execSQL(sql);   
        }catch(Exception ex){   
            this.ShowDialog("删除表异常:"+ex.getMessage());   
        }   
    }   
    /**  
     * 删除数据库  
     */  
    private void DropDatabase(){           
        try{   
            this.deleteDatabase(DATABASE_NAME);   
        }catch(Exception ex){   
            this.ShowDialog("删除数据库异常:"+ex.getMessage());   
        }   
    }   
    /**  
     * 提示对话框  
     * @param msg  
     */  
    private void ShowDialog(String msg) {   
        new AlertDialog.Builder(this).setTitle("提示").setMessage(msg)   
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {   
  
                    @Override  
                    public void onClick(DialogInterface dialog, int which) {   
  
                    }   
  
                }).show();   
    }   
}  
