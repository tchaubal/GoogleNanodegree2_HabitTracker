package com.example.tanushreechaubal.trackmyhabits.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.tanushreechaubal.trackmyhabits.data.HabitContract.HabitEntry;

/**
 * Created by TanushreeChaubal on 5/23/18.
 */

public class HabitDBHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "habitTracker.db";
    public static final int DB_VERSION = 1;

    public HabitDBHelper(Context context) { super(context, DB_NAME, null, DB_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                                        + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                                        + HabitEntry.COLUMN_HABIT_DURATION + " TEXT, "
                                        + HabitEntry.COLUMN_HABIT_RATING + " INTEGER NOT NULL DEFAULT 0, "
                                        + HabitEntry.COLUMN_HABIT_TARGET + " TEXT, "
                                        + HabitEntry.COLUMN_HABIT_FREE_OR_PAID + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
