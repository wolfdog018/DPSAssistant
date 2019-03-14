package com.example.dpsassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Datahelp extends SQLiteOpenHelper {

    public static final String database_name = "name.db";
    public static final String table_name = "name";
    public static final String col1 = "Id";
    public static final String col2 = "NAME";

    public Datahelp(Context context)
    {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+table_name+ "(ID text, NAME text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+table_name);
        onCreate(db);
    }

    public boolean insert(String id, String name){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1,id);
        contentValues.put(col2,name);
        long result = sqLiteDatabase.insert(table_name,null,contentValues);
        if (result == -1){
            return false;
        }
        else { return true;}
    }

    public Cursor getView(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+table_name,null);
        return res;
    }

}
