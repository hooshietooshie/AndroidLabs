package com.cst2335.shah0300;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyOpenHelper extends SQLiteOpenHelper {

    public static final String FILENAME = "MyDatabase";
    public static final int VERSION = 2;
    public static final String TABLE_NAME = "MyData";
    public static final String COL_ID = "_id";
    public static final String COL_MESSAGE = "Message";
    public static final String COL_SEND_RECEIVE = "SendOrReceive";

    public MyOpenHelper(Context context) {
        super(context, FILENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String result = String.format(" %s %s %s", "FirstString", "10", "10.0");

        sqlDB.execSQL(String.format("Create table %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s  INTEGER);", TABLE_NAME, COL_ID, COL_MESSAGE, COL_SEND_RECEIVE));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int oldVersion, int newVersion) {
        sqlDB.execSQL("Drop table if exists " + TABLE_NAME);
        this.onCreate(sqlDB);
        Log.i(FILENAME, "DATABASE VERSION, oldVersion "+ oldVersion + ",newVersion " + newVersion);
    }
}
