package com.example.tanushreechaubal.trackmyhabits.data;

import android.provider.BaseColumns;

/**
 * Created by TanushreeChaubal on 5/23/18.
 */

public class HabitContract {

    private HabitContract(){}

    public static final class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habits";
        public final static String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_DURATION = "duration";
        public static final String COLUMN_HABIT_RATING = "rating";
        public static final String COLUMN_HABIT_TARGET = "target";
        public static final String COLUMN_HABIT_FREE_OR_PAID = "freeOrPaid";
    }
}
