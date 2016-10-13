package com.example.pc.login.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DBPref extends  DBHelper{

    public DBPref(Context context) {
        super(context);
    }

    public void addRecord(String quote, String author){
        ContentValues contentValues = new ContentValues();
        contentValues.put("quote", quote);
        contentValues.put("author", author);

        this.db.insert("quotes", null, contentValues);
    }

    public Cursor getVals(){
        return this.db.query("quotes", new String[]{"quote", "author"}, null, null, null, null, null);
    }
}
