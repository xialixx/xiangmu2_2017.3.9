package com.example.administrator.myapplication_class_227.mvc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/1/23.
 */
public class DB_M extends SQLiteOpenHelper {
    public static final String one="table_name";
    public DB_M(Context context) {
        super(context, "one.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String  table_sql="create table "+one+"( _id  integer primary key autoincrement," +
                " ischeak not null, c2 not null,c3 not null,c1 not null,c4T1 not null,c4R not null,c4T2 not null,c52Link not null)";
        db.execSQL(table_sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

