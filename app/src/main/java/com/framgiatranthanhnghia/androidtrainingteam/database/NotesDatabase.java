package com.framgiatranthanhnghia.androidtrainingteam.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 27/08/2015.
 */
public class NotesDatabase extends SQLiteOpenHelper {

    public static final int VERSION=0;

    public NotesDatabase(Context context){
        super(context,NotesDatabase.class.getName(),null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
