package com.example.doanltdd.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhellper extends SQLiteOpenHelper {
    public DBhellper(@Nullable Context context) {
        super(context, "QLAmNhac", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table NhacSy(mans text, tenns Text )";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
