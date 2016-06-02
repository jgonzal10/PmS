package controllers;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

import models.PM;

/**
 * Created by jgo on 02/06/2016.
 */
public class DBHandler extends SQLiteOpenHelper {
    //DATABASE VERSION
    private static final int DATABASE_VERSION = 2;
    //DATABASE NAME
    private static final String DATABASE_NAME = "pms";


    //TABLE NAME
    private static final String TABLE_NAME = "PM";

    //COLUMNS ID, DATA
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private SQLiteDatabase db;


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PM_TABLE ="CREATE TABLE "+ TABLE_NAME+ "("+KEY_ID+" INTEGER PRIMARY KEY," + KEY_DATE+" TEXT"+")";
        try {
            db.execSQL(CREATE_PM_TABLE);
        }catch (Exception ex){}


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);



    }


    public void CloseDb() {
        try {
            this.db.close();
        } catch (Exception ex) {
        }
        try {
            this.db.releaseReference();
        } catch (Exception ex) {
        }
    }

    public void addPM(PM pm){
        this.db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, pm.getDate());
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public List<PM> getAllPM() {
        List<PM> pmList = new ArrayList<PM>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        this.db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            try {
                do {
                    PM pm = new PM();
                    pm.setId(Integer.parseInt(cursor.getString(0)));
                    pm.setDate(cursor.getColumnName(1));
                    pmList.add(pm);

                } while (cursor.moveToNext());

            } catch (Exception ex) {

            }


        }
    ///returning my pms
    return pmList;
    }



    //UPDATING RECORDS

    public int updatePM(PM pm){
        this.db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, pm.getDate());

        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(pm.getId())});


    }

}
