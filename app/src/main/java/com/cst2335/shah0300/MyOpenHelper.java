package com.cst2335.shah0300;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

    public  static final String filename = "MyDatabase";
    public static final int version = 1;
    public static final String TABLE_NAME = "MyData";
    public static final String COL_ID = "_id";
    public static final String COL_MESSAGE = "Message";
    public static final String COL_SEND_RECEIVE = "SendOrReceive";
    public static final String COL_TIME_SENT = "TimeSent";

    public MyOpenHelper(Context context) {
        super(context,filename,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
    String result = String.format(" %s %s %s", "FirstString","10","10.0");

        sqlDB.execSQL( String.format( "Create table %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s  INTEGER, %s TEXT );"
                , TABLE_NAME, COL_ID,                       COL_MESSAGE, COL_SEND_RECEIVE, COL_TIME_SENT ) );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
