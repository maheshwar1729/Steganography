package com.rttc.whatsappfbstego;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DBAdapterGP {
	public static final String KEY_ID = "_id";
	public static final String KEY_ARR1 = "arr1";
    public static final String KEY_ARR2 = "arr2";
    public static final String KEY_ARR3 = "arr3";
    public static final String KEY_ARR4 = "arr4";
    public static final String KEY_Ques = "secques";
    public static final String KEY_Ans = "secans";
   
            
    private static final String TAG = "DBAdapter";
    
    private static final String DATABASE_NAME = "DBForGP";
    private static final String DATABASE_TABLE = "Graphical_Password";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
        "create table Graphical_Password (_id integer primary key autoincrement ,arr1 integer , "
        + "arr2 integer , arr3 integer,arr4 integer,secques text,secans text);";
    
    private final Context context; 
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    
    
    public DBAdapterGP(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
        	//db.execSQL("DROP TABLE IF EXISTS Graphical_Password");
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
        int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                    + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS Graphical_Password");
            onCreate(db);
        }
    }    
    
    //---opens the database---
    public DBAdapterGP open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        //db.execSQL("DROP TABLE IF EXISTS Graphical_Password");
        //db.execSQL(DATABASE_CREATE);
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    
    //---insert a contact into the database---
    public long insertRecord(Integer r1, Integer r2, Integer r3,Integer r4,String ques,String ans) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ARR1,r1);
        initialValues.put(KEY_ARR2,r2);
        initialValues.put(KEY_ARR3,r3);
        initialValues.put(KEY_ARR4,r4);
        initialValues.put(KEY_Ques, ques);
        initialValues.put(KEY_Ans, ans);
       
              
        return db.insert(DATABASE_TABLE, null, initialValues);
        
    }

  //---deletes All Records---
    public boolean deleteRecord() 
    {
        //return db.delete(DATABASE_TABLE, KEY_FNAME LIKE+ id, null) > 0;
    	//db.execSQL("DELETE FROM CONTACTS WHERE FNAME="+id+";");
    	db.execSQL("DELETE FROM Graphical_Password;");
    	return true;
    }
    
    //---retrieves all the records---
    public Cursor getAllRecords() 
    {
    	
    	
        Cursor mCursor= db.query(DATABASE_TABLE, new String[] {
        		KEY_ARR1, 
        		KEY_ARR2,
        		KEY_ARR3,
        		KEY_ARR4,
        		KEY_Ques,
        		KEY_Ans
                }, 
                null, 
                null, 
                null, 
                null, 
                null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    
    //---retrieves a particular contact---
  public Cursor getRecord(String ques) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		KEY_ARR1, 
                		KEY_ARR2,
                		KEY_ARR3,
                		KEY_ARR4,
                		KEY_Ques,
                		KEY_Ans}, 
                		KEY_Ques + "="+ ques , 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
 /* public Cursor getContactLastName(String rowId) throws SQLException 
  {
	//  query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
      Cursor mCursor =
              db.query(true, DATABASE_TABLE, new String[] {
              		KEY_ROWID,
              		KEY_FNAME, 
              		KEY_LNAME,
              		KEY_PHONE,
              		KEY_EMAIL,KEY_ADDRESS
              		}, 
              		KEY_LNAME + "='" + rowId+"'", 
              		null,
              		null, 
              		null, 
              		null, 
              		null);
      if (mCursor != null) {
          mCursor.moveToFirst();
      }
      return mCursor;
  }    */
 /* public int updateRecord(int id,String sms,String call) throws SQLException
  {
	  ContentValues initialValues = new ContentValues();
	  initialValues.put(KEY_SMS,sms);
      initialValues.put(KEY_CALL, call);
      return db.update(DATABASE_TABLE,initialValues, KEY_ID+"="+id, null);
  }*/
  
}


