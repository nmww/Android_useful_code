package com.demo.provider;

import java.util.HashMap;   

import com.demo.provider.TestContentProviderMetaData.BookTableMetaData;

import android.content.ContentProvider;   
import android.content.ContentResolver;   
import android.content.ContentUris;   
import android.content.ContentValues;   
import android.content.Context;   
import android.content.UriMatcher;   
import android.database.Cursor;   
import android.database.SQLException;   
import android.database.sqlite.SQLiteDatabase;   
import android.database.sqlite.SQLiteOpenHelper;   
import android.database.sqlite.SQLiteQueryBuilder;   
import android.database.sqlite.SQLiteDatabase.CursorFactory;   
import android.net.Uri;   
import android.provider.BaseColumns;   
import android.text.TextUtils;   
import android.util.Log;   
  
public class TestContentProvider extends ContentProvider {   
    public static final String TAG = "TestContentProvider";   
    public DatabaseHelper openHelper = null;   
       
         //-- 创建表列名与JavaBean 映射.   
    public static HashMap<String, String> sBookProjectionMap = null;   
    static{   
        sBookProjectionMap = new HashMap<String, String >();   
        sBookProjectionMap.put(BookTableMetaData._ID, BookTableMetaData._ID);   
        sBookProjectionMap.put(BookTableMetaData.BOOK_NAME, BookTableMetaData.BOOK_NAME);   
        sBookProjectionMap.put(BookTableMetaData.BOOK_ISBN, BookTableMetaData.BOOK_ISBN);   
        sBookProjectionMap.put(BookTableMetaData.BOOK_AUTHOR, BookTableMetaData.BOOK_AUTHOR);   
        sBookProjectionMap.put(BookTableMetaData.CREATED_DATE, BookTableMetaData.CREATED_DATE);   
        sBookProjectionMap.put(BookTableMetaData.MODIFIED_DATE, BookTableMetaData.MODIFIED_DATE);   
           
    }   
       
         //-- 创建URI最佳匹配器   
    private static UriMatcher sUriMatcher = null;   
         //-- 注册URI请求类型   
    private static final int INCOMMING_BOOK_COLLECTION_URI_INDICATOR = 1;   
    private static final int INCOMMING_SINGLE_BOOK_URI_INDICATOR = 2;   
    static{   
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);   
        sUriMatcher.addURI(TestContentProviderMetaData.AUTHORITY, "books", INCOMMING_BOOK_COLLECTION_URI_INDICATOR);   
        sUriMatcher.addURI(TestContentProviderMetaData.AUTHORITY, "books/#", INCOMMING_SINGLE_BOOK_URI_INDICATOR);   
    }   
       
       
    @Override  
    public int delete(Uri uri, String whereClause, String[] whereArgs) {   
        Log.i(TAG, "del");   
        // TODO Auto-generated method stub   
        SQLiteDatabase db = openHelper.getWritableDatabase();   
        int count = 0;   
        switch(sUriMatcher.match(uri)){   
        case INCOMMING_BOOK_COLLECTION_URI_INDICATOR:   
            count = db.delete(BookTableMetaData.TABLE_NAME, whereClause, whereArgs);   
            break;   
        case INCOMMING_SINGLE_BOOK_URI_INDICATOR:   
            String rowID = uri.getPathSegments().get(1);   
            String where = BookTableMetaData._ID + "=" + rowID + (!TextUtils.isEmpty(whereClause)?" AND (" + whereClause + ')':"");    
            count = db.delete(BookTableMetaData.TABLE_NAME, where, whereArgs);   
            break;   
            default:   
                throw new IllegalArgumentException("Unknown URI " + uri);   
        }   
        this.getContext().getContentResolver().notifyChange(uri, null);   
           
           
        return count;   
    }   
  
    @Override  
    public String getType(Uri uri) {   
        switch(sUriMatcher.match(uri)){   
            case INCOMMING_BOOK_COLLECTION_URI_INDICATOR:   
                return BookTableMetaData.CONTENT_TYPE;   
            case INCOMMING_SINGLE_BOOK_URI_INDICATOR:   
                return BookTableMetaData.CONTENT_ITEM_TYPE;   
            default:   
                throw new IllegalArgumentException("Unknown URI " + uri);   
        }   
    }   
  
    @Override  
    public Uri insert(Uri uri, ContentValues values) {   
        // TODO Auto-generated method stub   
        Log.i(TAG, "insert");   
           
       // if(sUriMatcher.match(uri) != INCOMMING_BOOK_COLLECTION_URI_INDICATOR){   
       //     throw new IllegalArgumentException("Unknown URI " + uri);   
       // }   
           
        long now = Long.valueOf(System.currentTimeMillis());   
           
        if(values.containsKey(BookTableMetaData.CREATED_DATE) == false){   
            values.put(BookTableMetaData.CREATED_DATE, now);   
        }   
           
        if(values.containsKey(BookTableMetaData.MODIFIED_DATE) == false){   
            values.put(BookTableMetaData.MODIFIED_DATE, now);   
        }   
           
        if(values.containsKey(BookTableMetaData.BOOK_NAME) == false){   
            //values.put(BookTableMetaData.BOOK_NAME, "null");   
            throw new SQLException("Failed to insert row ,because Book Name is needed " + uri);   
        }   
           
        if(values.containsKey(BookTableMetaData.BOOK_ISBN) == false){   
            values.put(BookTableMetaData.BOOK_ISBN, "Unknown ISBN");   
        }   
           
        if(values.containsKey(BookTableMetaData.BOOK_AUTHOR) == false){   
            values.put(BookTableMetaData.BOOK_AUTHOR, "Unknown author");   
        }   
           
        SQLiteDatabase db = openHelper.getWritableDatabase();   
        long rowID = db.insert(BookTableMetaData.TABLE_NAME, BookTableMetaData.BOOK_NAME, values);   
        if(rowID > 0){   
            Uri insertBookedUri = ContentUris.withAppendedId(BookTableMetaData.CONTENT_URI, rowID);   
            getContext().getContentResolver().notifyChange(insertBookedUri, null);   
            return insertBookedUri;   
        }   
           
        throw new SQLException("Failed to insert row into " + uri);   
    }   
       
  
    @Override  
    public boolean onCreate() {   
        // TODO Auto-generated method stub   
        Log.i(TAG, "create table");   
        openHelper = new DatabaseHelper(this.getContext());   
        Log.i(TAG, openHelper.toString());   
        return true;   
    }   
  
    @Override  
    public Cursor query(Uri uri, String[] projection, String selection,   
            String[] selectionArgs, String sortOrder) {   
        // TODO Auto-generated method stub   
        Log.i(TAG, "query");   
        Cursor cursor = null;   
        SQLiteQueryBuilder qb = null;   
           
        qb = new SQLiteQueryBuilder();   
        switch(sUriMatcher.match(uri)){   
            case INCOMMING_BOOK_COLLECTION_URI_INDICATOR:   
                qb.setTables(BookTableMetaData.TABLE_NAME);   
                qb.setProjectionMap(sBookProjectionMap);   
                   
                break;   
            case INCOMMING_SINGLE_BOOK_URI_INDICATOR:   
                qb.setTables(BookTableMetaData.TABLE_NAME);   
                qb.setProjectionMap(sBookProjectionMap);   
                qb.appendWhere(BookTableMetaData._ID + "=" + uri.getPathSegments().get(1));   
                break;   
            default:   
                throw new IllegalArgumentException("Unknown URI " + uri);   
        }   
           
        String orderBy = "";   
        if(TextUtils.isEmpty(sortOrder)){   
            orderBy = BookTableMetaData.DEFAULT_SORT_ORDER;   
        }else{   
            orderBy = sortOrder;   
        }   
           
        SQLiteDatabase db = openHelper.getReadableDatabase();   
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);   
        int i = c.getCount();   
        ContentResolver cr = this.getContext().getContentResolver();   
        c.setNotificationUri(cr, uri);   
           
        return c;   
    }   
  
    @Override  
    public int update(Uri uri, ContentValues values, String selection,   
            String[] selectionArgs) {   
        // TODO Auto-generated method stub   
        Log.i(TAG, "update");   
        SQLiteDatabase db = openHelper.getWritableDatabase();   
        int count = 0;   
        switch(sUriMatcher.match(uri)){   
        case INCOMMING_BOOK_COLLECTION_URI_INDICATOR:   
            count = db.update(BookTableMetaData.TABLE_NAME, values, selection, selectionArgs);   
               
            break;   
        case INCOMMING_SINGLE_BOOK_URI_INDICATOR:   
            String rowID = uri.getPathSegments().get(1);   
            String where = "BookTableMetaData._ID" + "=" + rowID + (!TextUtils.isEmpty(selection)?" AND(" + selection + ')':"");   
            count = db.update(BookTableMetaData.TABLE_NAME, values, where, selectionArgs);   
               
            break;   
        default:   
            throw new IllegalArgumentException("Unknown URI " + uri);   
        }   
           
        getContext().getContentResolver().notifyChange(uri, null);   
           
        return count;   
    }   
       
           
    //-- create table   
    public class DatabaseHelper extends SQLiteOpenHelper{   
  
        public DatabaseHelper(Context context) {   
            super(context, TestContentProviderMetaData.DATABASE_NAME, null, TestContentProviderMetaData.DATABASE_VERSION);   
        }   
  
        @Override  
        public void onCreate(SQLiteDatabase db) {   
            // TODO Auto-generated method stub   
            String sql = "CREATE TABLE " + BookTableMetaData.TABLE_NAME   
                        + " (" + TestContentProviderMetaData.BookTableMetaData._ID    
                        + " INTEGER PRIMARY KEY,"  
                        + BookTableMetaData.BOOK_NAME + " TEXT,"  
                        + BookTableMetaData.BOOK_ISBN + " TEXT,"  
                        + BookTableMetaData.BOOK_AUTHOR + " TEXT,"  
                        + BookTableMetaData.CREATED_DATE + " INTEGER,"  
                        + BookTableMetaData.MODIFIED_DATE + " INTEGER"  
                        + ");";   
            Log.i(TAG, db.getPath());   
            db.execSQL(sql);   
               
        }   
  
        @Override  
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {   
            // TODO Auto-generated method stub   
            Log.i(TAG, "Upgrade database from " + oldVersion + " to "  
                    + newVersion + ", which will destroy old data!");   
               
            String sql = "DROP TABLE IF EXISTS " + BookTableMetaData.TABLE_NAME;   
            db.execSQL(sql);   
            onCreate(db);   
        }   
           
    }   
}   
  
  
  
class TestContentProviderMetaData {   
    public static final String DATABASE_NAME = "books.db";   
    public static final int DATABASE_VERSION = 1;   
       
    public static final String AUTHORITY = "com.demo.provider.bookprovider";   
       
    public static final String BOOKS_TABLE_NAME = "books";   
       
    public static final class BookTableMetaData implements BaseColumns{   
        public static final String TABLE_NAME = "books";   
           
        // String    
        public static final String BOOK_NAME = "name";   
        // String   
        public static final String BOOK_ISBN = "isbn";   
        // String   
        public static final String BOOK_AUTHOR = "author";   
        // Integer   
        public static final String CREATED_DATE = "created";   
        // Integer   
        public static final String MODIFIED_DATE = "modified";   
           
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/books");   
        public static final Uri CONTENT_SINGLE_URI = Uri.parse("content://" + AUTHORITY + "/books/#");   
        //-- 多记录   
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.androidbook.book";   
        //-- 单记录   
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.androidbook.book";   
           
        public static final String DEFAULT_SORT_ORDER = "modified DESC";    
           
           
    }   
       
       
}  

