package com.example.tanushreechaubal.trackmyhabits;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tanushreechaubal.trackmyhabits.data.HabitContract.HabitEntry;
import com.example.tanushreechaubal.trackmyhabits.data.HabitDBHelper;

public class TrackHabitsMainActivity extends AppCompatActivity {
    long newRowId;
    String idString;
    String currentName;
    String currentDuration;
    String currentTarget;
    int currentFreeOrPaid;
    int currentRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_habits_main);

        insertHabit();
    }

    private void insertHabit() {

        HabitDBHelper habitDBHelper = new HabitDBHelper(this);
        SQLiteDatabase db = habitDBHelper.getWritableDatabase();

        String[] name_array = getResources().getStringArray(R.array.habit_name);
        String[] duration_array = getResources().getStringArray(R.array.habit_duration);
        String[] target_array = getResources().getStringArray(R.array.habit_target);
        String[] free_or_paid_array = getResources().getStringArray(R.array.habit_free_or_paid);
        String[] rating_array = getResources().getStringArray(R.array.habit_rating);

        ContentValues contentValues = new ContentValues();
        for(int i=0; i<name_array.length; i++) {
            String name = name_array[i];
            String duration = duration_array[i];
            String target = target_array[i];
            String status = free_or_paid_array[i];
            String rating = rating_array[i];

            contentValues.put(HabitEntry.COLUMN_HABIT_NAME, name);
            contentValues.put(HabitEntry.COLUMN_HABIT_DURATION, duration);
            contentValues.put(HabitEntry.COLUMN_HABIT_TARGET, target);
            contentValues.put(HabitEntry.COLUMN_HABIT_FREE_OR_PAID, status);
            contentValues.put(HabitEntry.COLUMN_HABIT_RATING, rating);
            newRowId = db.insert(HabitEntry.TABLE_NAME, null, contentValues);
        }
    }

    private Cursor read() {
        HabitDBHelper mDbHelper = new HabitDBHelper(this);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_DURATION,
                HabitEntry.COLUMN_HABIT_TARGET,
                HabitEntry.COLUMN_HABIT_FREE_OR_PAID,
                HabitEntry.COLUMN_HABIT_RATING
        };

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        try {
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int durationColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DURATION);
            int targetColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_TARGET);
            int freeOrPaidColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_FREE_OR_PAID);
            int ratingColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_RATING);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                idString = String.valueOf(currentID);
                currentName = cursor.getString(nameColumnIndex);
                currentDuration = cursor.getString(durationColumnIndex);
                currentTarget = cursor.getString(targetColumnIndex);
                currentFreeOrPaid = cursor.getInt(freeOrPaidColumnIndex);
                currentRating = cursor.getInt(ratingColumnIndex);

                Log.v("Cursor fields ", " --- ID: " + idString);
                Log.v("Cursor fields", "--- Name: " + currentName);
                Log.v("Cursor fields", "--- Duration: " + currentDuration);
                Log.v("Cursor fields", "--- Target: " + currentTarget);
                Log.v("Cursor fields", "--- Status (Free=0/Paid=1): " + currentFreeOrPaid);
                Log.v("Cursor fields", "--- Rating (Lowest=0/Highest=5): " + currentRating);
            }
        } finally {
            cursor.close();
        }
        return cursor;
    }

    @Override
    protected void onStart() {
        super.onStart();
        read();
    }
}
