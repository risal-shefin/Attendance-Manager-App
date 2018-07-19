package com.shefin.attendancemanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import java.lang.reflect.Array;

public class csecActivity
        extends AppCompatActivity {
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.csec);
        SQLiteDatabase localSQLiteDatabase = getBaseContext().openOrCreateDatabase("attendance.db", 0, null);
        final CheckBox[][] arrayOfCheckBox = (CheckBox[][]) Array.newInstance(CheckBox.class, new int[]{65, 16});
        try {
            localSQLiteDatabase.execSQL("CREATE TABLE csec(id INTEGER);");
            localSQLiteDatabase.beginTransaction();
            SQLiteStatement localSQLiteStatement = localSQLiteDatabase.compileStatement("INSERT INTO csec VALUES(?);");
            Integer localInteger5 = Integer.valueOf(121);
            for (Integer localInteger6 = Integer.valueOf(1); localInteger5.intValue() <= 180; localInteger6 = Integer.valueOf(1 + localInteger6.intValue())) {
                for (Integer localInteger7 = Integer.valueOf(1); localInteger7.intValue() <= 14; localInteger7 = Integer.valueOf(1 + localInteger7.intValue())) {
                    String str2 = "c" + localInteger5.toString() + localInteger7.toString();
                    localSQLiteStatement.bindLong(1, getResources().getIdentifier(str2, "id", getPackageName()));
                    localSQLiteStatement.execute();
                    localSQLiteStatement.clearBindings();
                }
                localSQLiteStatement.clearBindings();
                localInteger5 = Integer.valueOf(1 + localInteger5.intValue());
            }
            localSQLiteDatabase.setTransactionSuccessful();
            localSQLiteDatabase.endTransaction();
        } catch (Exception localException) {
        }

        Cursor localCursor1 = null;
        Integer localInteger1 = 0;
        Integer localInteger2 = 0;
        String str1 = "";
        Cursor localCursor2 = null;
        View.OnClickListener local1 = null;
        Integer localInteger3 = 0;

        localCursor1 = localSQLiteDatabase.rawQuery("SELECT * FROM csec;", null);
        localInteger1 = Integer.valueOf(1);
        localInteger2 = Integer.valueOf(1);
        if (localCursor1.moveToFirst()) {
            do {
                arrayOfCheckBox[localInteger1.intValue()][localInteger2.intValue()] = ((CheckBox) findViewById(localCursor1.getInt(0)));
                localInteger2 = Integer.valueOf(1 + localInteger2.intValue());
                if (localInteger2.intValue() > 14) {
                    localInteger1 = Integer.valueOf(1 + localInteger1.intValue());
                    localInteger2 = Integer.valueOf(1);
                }
            } while (localCursor1.moveToNext());
        }
        str1 = "classes";
        if (classtypeActivity.type == "Lab") {
            str1 = "labs";
        }

        localCursor2 = localSQLiteDatabase.rawQuery("SELECT roll, a, b, c, d, e, f, g, h, i, j, k, l, m, n FROM " + str1 + " WHERE year = " + classtypeActivity.year + " AND section = '" + classtypeActivity.section + "' AND day = '" + MainActivity.Day + "'", null);
        if (localCursor2.moveToFirst()) {
            do {
                int i = -120 + localCursor2.getInt(0);
                if (localCursor2.getInt(1) == 1) {
                    arrayOfCheckBox[i][1].setChecked(true);
                }
                if (localCursor2.getInt(2) == 1) {
                    arrayOfCheckBox[i][2].setChecked(true);
                }
                if (localCursor2.getInt(3) == 1) {
                    arrayOfCheckBox[i][3].setChecked(true);
                }
                if (localCursor2.getInt(4) == 1) {
                    arrayOfCheckBox[i][4].setChecked(true);
                }
                if (localCursor2.getInt(5) == 1) {
                    arrayOfCheckBox[i][5].setChecked(true);
                }
                if (localCursor2.getInt(6) == 1) {
                    arrayOfCheckBox[i][6].setChecked(true);
                }
                if (localCursor2.getInt(7) == 1) {
                    arrayOfCheckBox[i][7].setChecked(true);
                }
                if (localCursor2.getInt(8) == 1) {
                    arrayOfCheckBox[i][8].setChecked(true);
                }
                if (localCursor2.getInt(9) == 1) {
                    arrayOfCheckBox[i][9].setChecked(true);
                }
                if (localCursor2.getInt(10) == 1) {
                    arrayOfCheckBox[i][10].setChecked(true);
                }
                if (localCursor2.getInt(11) == 1) {
                    arrayOfCheckBox[i][11].setChecked(true);
                }
                if (localCursor2.getInt(12) == 1) {
                    arrayOfCheckBox[i][12].setChecked(true);
                }
                if (localCursor2.getInt(13) == 1) {
                    arrayOfCheckBox[i][13].setChecked(true);
                }
                if (localCursor2.getInt(14) == 1) {
                    arrayOfCheckBox[i][14].setChecked(true);
                }
            } while (localCursor2.moveToNext());
        }
        localCursor2.close();
        localSQLiteDatabase.close();
        local1 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Boolean localBoolean = Boolean.valueOf(false);
                Integer localInteger1 = Integer.valueOf(121);
                for (Integer localInteger2 = Integer.valueOf(1); localInteger1.intValue() <= 180; localInteger2 = Integer.valueOf(1 + localInteger2.intValue())) {

                    for (Integer localInteger3 = Integer.valueOf(1); localInteger3.intValue() <= 14; localInteger3 = Integer.valueOf(1 + localInteger3.intValue())) {
                        SQLiteDatabase localSQLiteDatabase = null;
                        CheckBox localCheckBox = null;
                        String str1 = "";
                        String str2 = "";
                        char c = '\0';
                        Cursor localCursor1 = null;
                        Integer localInteger4 = 0;

                        if (arrayOfCheckBox[localInteger2.intValue()][localInteger3.intValue()].getId() != paramAnonymousView.getId()) {
                            continue;
                        }

                        localBoolean = Boolean.valueOf(true);
                        localSQLiteDatabase = getBaseContext().openOrCreateDatabase("attendance.db", 0, null);
                        localCheckBox = (CheckBox) paramAnonymousView;
                        str1 = "classes";
                        str2 = "total";
                        if (classtypeActivity.type == "Lab") {
                            str1 = "labs";
                            str2 = "total2";
                        }
                        c = (char) (97 + (-1 + localInteger3.intValue()));
                        localCursor1 = localSQLiteDatabase.rawQuery("SELECT " + c + " FROM " + str1 + " WHERE roll = " + localInteger1.toString() + " AND year = " + classtypeActivity.year + " AND day = '" + MainActivity.Day + "'", null);
                        if (localCursor1.moveToFirst()) {
                            localInteger4 = Integer.valueOf(localCursor1.getInt(0));
                            if (localInteger4.intValue() == 0 && localCheckBox.isChecked()) {
                                localSQLiteDatabase.execSQL("UPDATE " + str1 + " SET " + c + " = 1 WHERE roll = " + localInteger1.toString() + " AND year = " + classtypeActivity.year + " AND day = '" + MainActivity.Day + "'");
                                Cursor localCursor3 = localSQLiteDatabase.rawQuery("SELECT total FROM " + str2 + " WHERE roll = " + localInteger1.toString() + " AND year = " + classtypeActivity.year, null);
                                if (localCursor3.moveToFirst()) {
                                    Integer localInteger6 = Integer.valueOf(1 + Integer.valueOf(localCursor3.getInt(0)).intValue());
                                    localSQLiteDatabase.execSQL("UPDATE " + str2 + " SET total = " + localInteger6.toString() + " WHERE roll = " + localInteger1.toString() + " AND year = " + classtypeActivity.year);
                                }
                                localCursor3.close();
                            }

                            if ((localInteger4.intValue() == 1) && (!localCheckBox.isChecked())) {
                                localSQLiteDatabase.execSQL("UPDATE " + str1 + " SET " + c + " = 0 WHERE roll = " + localInteger1.toString() + " AND year = " + classtypeActivity.year + " AND day = '" + MainActivity.Day + "'");
                                Cursor localCursor2 = localSQLiteDatabase.rawQuery("SELECT total FROM " + str2 + " WHERE roll = " + localInteger1.toString() + " AND year = " + classtypeActivity.year, null);
                                if (localCursor2.moveToFirst()) {
                                    Integer localInteger5 = Integer.valueOf(-1 + Integer.valueOf(localCursor2.getInt(0)).intValue());
                                    localSQLiteDatabase.execSQL("UPDATE " + str2 + " SET total = " + localInteger5.toString() + " WHERE roll = " + localInteger1.toString() + " AND year = " + classtypeActivity.year);
                                }
                                localCursor2.close();
                            }
                        }


                        try {
                            localCursor1.close();
                            localSQLiteDatabase.close();
                        } catch (Exception e) {

                        }
                        if (localBoolean.booleanValue())
                            break;

                    }

                    if (localBoolean.booleanValue())
                        break;

                    localInteger1 = Integer.valueOf(1 + localInteger1.intValue());
                }
            }
        };
        for (localInteger3 = Integer.valueOf(1); localInteger3.intValue() <= 60; localInteger3 = Integer.valueOf(1 + localInteger3.intValue())) {
            for (Integer localInteger4 = Integer.valueOf(1); localInteger4.intValue() <= 14; localInteger4 = Integer.valueOf(1 + localInteger4.intValue())) {
                arrayOfCheckBox[localInteger3.intValue()][localInteger4.intValue()].setOnClickListener(local1);
            }
        }
        classtypeActivity.dialog.dismiss();
    }
}