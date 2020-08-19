package com.example.admin.fcbarcelona;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//This class creates the database, it's table and various columns
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "scores.db";
    public static final String TABLE_NAME = "scores_table";
    public static final String COL_1 = "MATCH_ID";
    public static final String COL_2 = "HOME_TEAM";
    public static final String COL_3 = "AWAY_TEAM";
    public static final String COL_4 = "SCORE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //This method creates the database and links the column names to this class
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (MATCH_ID INTEGER PRIMARY KEY AUTOINCREMENT, HOME_TEAM TEXT, AWAY_TEAM TEXT, SCORE VARCHAR) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    //This method takes input from the user and stores into each column
    public boolean insertData(String home_team, String away_team, String score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, home_team);
        contentValues.put(COL_3, away_team);
        contentValues.put(COL_4, score);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //Prints all data entered in so far through a select query
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME,null);
        return res;
    }

    //Allows the user to change a mistake they may have made, however they must enter which match it is(matchid)
    public boolean updateData(String matchid, String home_team, String away_team, String score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, matchid);
        contentValues.put(COL_2, home_team);
        contentValues.put(COL_3, away_team);
        contentValues.put(COL_4, score);
        db.update(TABLE_NAME, contentValues, "MATCH_ID = ?", new String[] { matchid });
        return true;
    }

    //Deletes all data inputted for that particular match, matchid is needed again
    public Integer deleteData (String matchid) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "MATCH_ID = ?", new String[] {matchid});
    }
}

