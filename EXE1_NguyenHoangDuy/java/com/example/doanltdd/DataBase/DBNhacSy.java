package com.example.doanltdd.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.doanltdd.Model.NhacSy;

import java.util.ArrayList;

public class DBNhacSy {
    DBhellper dbHelper;

    public DBNhacSy(Context context) {
        dbHelper= new DBhellper(context);
    }

    public void Them(NhacSy nhacSy)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mans",nhacSy.getMaNS());
        values.put("tenns",nhacSy.getTenNS());
        db.insert("NhacSy",null,values);
    }

    public  void Sua(NhacSy nhacSy)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mans",nhacSy.getMaNS());
        values.put("tenns",nhacSy.getTenNS());
        db.update("NhacSy",values,"mans ='"+nhacSy.getMaNS() +"'",null);
    }


    public  void Xoa(NhacSy nhacSy)
    {
//       SQLiteDatabase db = dbHelper.getWritableDatabase();
//       db.delete("NhacSy,","mans ='"+nhacSy.getMaNS() +"'",null);


      SQLiteDatabase db = dbHelper.getWritableDatabase();
      String sql ="Delete from NhacSy where mans= '"+nhacSy.getMaNS()+"'";
      db.execSQL(sql);

    }

    public ArrayList<NhacSy> LayDL()
    {
        ArrayList<NhacSy> data = new ArrayList<>();
        String sql="select * from NhacSy";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        do {
            NhacSy nhacSy=new NhacSy();
            nhacSy.setMaNS(cursor.getString(0));
            nhacSy.setTenNS(cursor.getString(1));
            data.add(nhacSy);
        }
        while (cursor.moveToNext());

        return  data;
    }

}
