package com.example.tbprassetyo.einstein.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tbprassetyo.einstein.model.Akun;
import com.example.tbprassetyo.einstein.model.Message;

public class DBHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db_einstein";

    // Tag table create statement
    private static final String CREATE_TABLE_MESSAGE = "CREATE TABLE " + DBContract.MessageEntry.TABLE_NAME
            + "(" + DBContract.MessageEntry.COLUMN_ID + " INTEGER PRIMARY KEY,"
            + DBContract.MessageEntry.COLUMN_BODY + " TEXT,"
            + DBContract.MessageEntry.COLUMN_MSG_TYPE + " INTEGER" + ")";

    private static final String CREATE_TABLE_AKUN = "CREATE TABLE " + DBContract.AkunEntry.TABLE_NAME
            + "(" + DBContract.AkunEntry.COLUMN_ID + " INTEGER PRIMARY KEY,"
            + DBContract.AkunEntry.COLUMN_NISN + " TEXT,"
            + DBContract.AkunEntry.COLUMN_KELAS + " TEXT,"
            + DBContract.AkunEntry.COLUMN_USERNAME + " TEXT,"
            + DBContract.AkunEntry.COLUMN_PASSWORD + " TEXT" + ")";

    private static final String CREATE_TABLE_MATERI = "CREATE TABLE " +DBContract.MateriEntry.TABLE_NAME
            + "(" + DBContract.MateriEntry.COLUMN_ID + "INTEGER PRIMARY KEY,"
            + DBContract.MateriEntry.COLUMN_ACTIVITY_ID + "INTEGER,"
            + DBContract.MateriEntry.COLUMN_MATERI_NAME + "TEXT,"
            + DBContract.MateriEntry.COLUMN_INTERACT_MODE + "INTEGER" + ")";

    private static final String CREATE_TABLE_ACTIVITY = "CREATE TABLE " +DBContract.ActivityEntry.TABLE_NAME
            + "(" + DBContract.MateriEntry.COLUMN_ID + "INTEGER PRIMARY KEY,"
            + DBContract.ActivityEntry.COLUMN_REQUEST_NUMBER + "INTEGER,"
            + DBContract.ActivityEntry.COLUMN_REQUEST_MATERI_NAME + "INTEGER,"
            + DBContract.ActivityEntry.COLUMN_ANSWEAR_CORRECT + "INTEGER,"
            + DBContract.ActivityEntry.COLUMN_ANSWEAR_FAIL + "INTEGER" + ")";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_MESSAGE);
        db.execSQL(CREATE_TABLE_AKUN);
        db.execSQL(CREATE_TABLE_MATERI);
        db.execSQL(CREATE_TABLE_ACTIVITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.MessageEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.MateriEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.ActivityEntry.TABLE_NAME);

        // create new tables
        onCreate(db);
    }

    public Message[] getAllMessages() {
        SQLiteDatabase mDb= this.getWritableDatabase();
        //Inside, call query on mDb passing in the table name and projection String [] order by COLUMN_TIMESTAMP
        Cursor c= mDb.query(
                DBContract.MessageEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        Message[] messages = new Message[c.getCount()];
        int i =0;
        if(c!=null){
            while(c.moveToNext()){
                messages[i] = new Message(c.getString(c.getColumnIndex(DBContract.MessageEntry.COLUMN_BODY)),c.getInt(c.getColumnIndex(DBContract.MessageEntry.COLUMN_MSG_TYPE)));
                messages[i].setId(c.getInt(c.getColumnIndex(DBContract.MessageEntry.COLUMN_ID)));
                i++;
            }
        }

        c.close();

        return messages;
    }

    public long addNewMessage(Message message) {
        SQLiteDatabase mDb = this.getWritableDatabase();
        //Inside, create a ContentValues instance to pass the values onto the insert query
        ContentValues cv = new ContentValues();
        //call put to insert the name value with the key COLUMN_GUEST_NAME
        cv.put(DBContract.MessageEntry.COLUMN_BODY, message.getBody());
        cv.put(DBContract.MessageEntry.COLUMN_MSG_TYPE, message.getMsgType());

        //call insert to run an insert query on TABLE_NAME with the ContentValues created
        return mDb.insert(DBContract.MessageEntry.TABLE_NAME, null, cv);
    }

    public long addNewAkun(Akun akun) {
        SQLiteDatabase mDb = this.getWritableDatabase();
        //Inside, create a ContentValues instance to pass the values onto the insert query
        ContentValues cv = new ContentValues();
        //call put to insert the name value with the key COLUMN_GUEST_NAME
        cv.put(DBContract.AkunEntry.COLUMN_NISN, akun.getNisn());
        cv.put(DBContract.AkunEntry.COLUMN_KELAS, akun.getKelas());
        cv.put(DBContract.AkunEntry.COLUMN_USERNAME,akun.getUsername());
        cv.put(DBContract.AkunEntry.COLUMN_PASSWORD, akun.getPassword());

        //call insert to run an insert query on TABLE_NAME with the ContentValues created
        return mDb.insert(DBContract.AkunEntry.TABLE_NAME, null, cv);
    }

    public long addNewActivity(com.example.tbprassetyo.einstein.model.Activity activity){
    SQLiteDatabase mDb = this.getReadableDatabase();
    //create a ContentValues instance to pass thevalues onto the insert
    ContentValues cv = new ContentValues();

    cv.put(DBContract.ActivityEntry.COLUMN_REQUEST_NUMBER, activity.getRequestNumber());
    cv.put(DBContract.ActivityEntry.COLUMN_REQUEST_MATERI_NAME, activity.getNameMateri());
    cv.put(DBContract.ActivityEntry.COLUMN_ANSWEAR_CORRECT, activity.getAnswearCorrect());
    cv.put(DBContract.ActivityEntry.COLUMN_ANSWEAR_FAIL, activity.getAnswearFail());

        //call insert to run an insert query on TABLE_NAME with the ContentValues created
        return  mDb.insert(DBContract.ActivityEntry.TABLE_NAME, null, cv);
    }


    public Boolean validateUsername(String username){
        SQLiteDatabase mDb= this.getWritableDatabase();
        Cursor c = mDb.rawQuery("SELECT * FROM akun WHERE username = '"+username.trim()+"'", null);

        if(c!=null && c.getCount()>0){
            Log.d("cot", "c!=null");
            c.close();
            return true;
        }else{
            return false;
        }
    }

    public Boolean validatePassword(String username, String password){
        SQLiteDatabase mDb= this.getWritableDatabase();
        Cursor c = mDb.rawQuery("SELECT * FROM akun WHERE username = '"+username.trim()+"' AND password = '"+password.trim()+"'", null);

        if(c!=null && c.getCount()>0){
            c.close();
            return true;
        }else{
            return false;
        }
    }


}
