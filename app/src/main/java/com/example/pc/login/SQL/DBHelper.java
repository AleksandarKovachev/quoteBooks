package com.example.pc.login.SQL;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "androidDb";
    static final int CURRENT_VERSION = 1;
    protected SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, CURRENT_VERSION);
        open();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table quotes (_id integer primary key autoincrement, " + "quote text not null, " + "author text not null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void open() throws SQLException {
        db = getWritableDatabase();
    }

    public void close(){
        db.close();
    }
}
