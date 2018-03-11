package com.example.joycabildo.finalfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rusellbayote on 03/09/2018.
 */


public class Database extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gsoandroid.db";
    public static final String ITEMDETAIL_TABLE_NAME = "itemdetail";
    public static final String ITEMDETAIL_COLUMN_ID = "id";
    public static final String ITEMDETAIL_COLUMN_DATETIME = "datetime";
    public static final String ITEMDETAIL_COLUMN_ITEMNAME = "itemname";
    public static final String ITEMDETAIL_COLUMN_SERIAL = "serial";
    public static final String ITEMDETAIL_COLUMN_ACCOUNTCODE = "accountcode";
    public static final String ITEMDETAIL_COLUMN_DESCRIPTION = "description";
    public static final String ITEMDETAIL_COLUMN_UNITCOST = "unitcost";
    public static final String ITEMDETAIL_COLUMN_ITEMTYPE = "itemtype";
    public static final String ITEMDETAIL_COLUMN_LIFESPAN = "lifespan";
    public static final String ITEMDETAIL_COLUMN_URL = "url";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Database.ITEMDETAIL_TABLE_NAME + " (" +
                    Database.ITEMDETAIL_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Database.ITEMDETAIL_COLUMN_DATETIME + " TEXT," +
                    Database.ITEMDETAIL_COLUMN_ITEMNAME + " TEXT," +
                    Database.ITEMDETAIL_COLUMN_SERIAL + " TEXT," +
                    Database.ITEMDETAIL_COLUMN_ACCOUNTCODE + " TEXT," +
                    Database.ITEMDETAIL_COLUMN_DESCRIPTION + " TEXT," +
                    Database.ITEMDETAIL_COLUMN_UNITCOST + " TEXT," +
                    Database.ITEMDETAIL_COLUMN_ITEMTYPE + " TEXT," +
                    Database.ITEMDETAIL_COLUMN_LIFESPAN + " TEXT," +
                    Database.ITEMDETAIL_COLUMN_URL + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Database.ITEMDETAIL_TABLE_NAME;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean insertItemDetail (String datetime, String itemname, String serial, String accountcode,String description, String unitcost, String itemtype, String lifespan, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("datetime", datetime);
        contentValues.put("itemname", itemname);
        contentValues.put("serial", serial);
        contentValues.put("accountcode", accountcode);
        contentValues.put("description", description);
        contentValues.put("unitcost", unitcost);
        contentValues.put("itemtype", itemtype);
        contentValues.put("lifespan", lifespan);
        contentValues.put("url", url);
        db.insert("itemdetail", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM itemdetail WHERE id =" + id + "", null );
        return res;
    }

    public Cursor getAllItemDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT id AS _id, itemname, datetime FROM itemdetail", null );
        return res;
    }
}
