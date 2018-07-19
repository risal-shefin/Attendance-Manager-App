package com.shefin.attendancemanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity
        extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static String Day = null;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_navbar);

        String str1 = "";
        for (Integer localInteger1 = Integer.valueOf(1); localInteger1.intValue() <= 14; localInteger1 = Integer.valueOf(1 + localInteger1.intValue())) {
            str1 = str1 + ", 0";
        }
        SQLiteDatabase localSQLiteDatabase = getBaseContext().openOrCreateDatabase("attendance.db", 0, null);
        try {
            localSQLiteDatabase.execSQL("CREATE TABLE classes(roll INTEGER, year INTEGER, section TEXT, day TEXT, a INTEGER, b INTEGER, c INTEGER, d INTEGER, e INTEGER, f INTEGER, g INTEGER, h INTEGER, i INTEGER, j INTEGER, k INTEGER, l INTEGER, m INTEGER, n INTEGER);");
            localSQLiteDatabase.execSQL("CREATE TABLE labs(roll INTEGER, year INTEGER, section TEXT, day TEXT, a INTEGER, b INTEGER, c INTEGER, d INTEGER, e INTEGER, f INTEGER, g INTEGER, h INTEGER, i INTEGER, j INTEGER, k INTEGER, l INTEGER, m INTEGER, n INTEGER);");
            localSQLiteDatabase.execSQL("CREATE TABLE total(roll INTEGER, year INTEGER, section TEXT, total INTEGER);");
            localSQLiteDatabase.execSQL("CREATE TABLE total2(roll INTEGER, year INTEGER, section TEXT, total INTEGER);");
            localSQLiteDatabase.beginTransaction();
            SQLiteStatement localSQLiteStatement1 = localSQLiteDatabase.compileStatement("INSERT INTO classes VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            SQLiteStatement localSQLiteStatement2 = localSQLiteDatabase.compileStatement("INSERT INTO labs VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            SQLiteStatement localSQLiteStatement3 = localSQLiteDatabase.compileStatement("INSERT INTO total VALUES(?, ?, ?, ?);");
            SQLiteStatement localSQLiteStatement4 = localSQLiteDatabase.compileStatement("INSERT INTO total2 VALUES(?, ?, ?, ?);");
            for (Integer localInteger2 = Integer.valueOf(1); localInteger2.intValue() <= 180; localInteger2 = Integer.valueOf(1 + localInteger2.intValue())) {
                localSQLiteStatement1.bindLong(1, localInteger2.intValue());
                localSQLiteStatement2.bindLong(1, localInteger2.intValue());
                localSQLiteStatement3.bindLong(1, localInteger2.intValue());
                localSQLiteStatement4.bindLong(1, localInteger2.intValue());
                for (Integer localInteger3 = Integer.valueOf(1); localInteger3.intValue() <= 8; localInteger3 = Integer.valueOf(1 + localInteger3.intValue())) {
                    localSQLiteStatement1.bindLong(2, localInteger3.intValue());
                    localSQLiteStatement2.bindLong(2, localInteger3.intValue());
                    localSQLiteStatement3.bindLong(2, localInteger3.intValue());
                    localSQLiteStatement4.bindLong(2, localInteger3.intValue());

                    char c1 = 'A';
                    if (localInteger2 > 120)
                        c1 = 'C';
                    else if (localInteger2 > 60)
                        c1 = 'B';

                    String str2 = "" + c1;

                    localSQLiteStatement1.bindString(3, str2);
                    localSQLiteStatement2.bindString(3, str2);
                    localSQLiteStatement3.bindString(3, str2);
                    localSQLiteStatement3.bindLong(4, 0L);
                    localSQLiteStatement4.bindString(3, str2);
                    localSQLiteStatement4.bindLong(4, 0L);
                    localSQLiteStatement3.execute();
                    localSQLiteStatement4.execute();
                    for (Integer localInteger5 = Integer.valueOf(0); localInteger5.intValue() < 5; localInteger5 = Integer.valueOf(1 + localInteger5.intValue())) {
                        char c2 = (char) (65 + localInteger5.intValue());
                        String str3 = "" + c2;
                        localSQLiteStatement1.bindString(4, str3);
                        localSQLiteStatement2.bindString(4, str3);
                        for (Integer localInteger6 = Integer.valueOf(5); localInteger6.intValue() <= 18; localInteger6 = Integer.valueOf(1 + localInteger6.intValue())) {
                            localSQLiteStatement1.bindLong(localInteger6.intValue(), 0L);
                            localSQLiteStatement2.bindLong(localInteger6.intValue(), 0L);
                        }
                        localSQLiteStatement1.execute();
                        localSQLiteStatement2.execute();
                    }

                }
                localSQLiteStatement1.clearBindings();
                localSQLiteStatement2.clearBindings();
                localSQLiteStatement3.clearBindings();
                localSQLiteStatement4.clearBindings();

            }
            localSQLiteDatabase.setTransactionSuccessful();
            localSQLiteDatabase.endTransaction();

        } catch (Exception localException) {
        }
        Button localButton1;
        Button localButton2;
        Button localButton3;
        Button localButton4;
        Button localButton5;
        Button localButton6;
        View.OnClickListener local1;
        View.OnClickListener local2;

        localSQLiteDatabase.close();
        localButton1 = (Button) findViewById(R.id.button);
        localButton2 = (Button) findViewById(R.id.button2);
        localButton3 = (Button) findViewById(R.id.button3);
        localButton4 = (Button) findViewById(R.id.button4);
        localButton5 = (Button) findViewById(R.id.button5);
        localButton6 = (Button) findViewById(R.id.percentage);
        local1 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                String str = ((Button) paramAnonymousView).getText().toString();
                MainActivity.Day = "" + str.charAt(0);
                Intent localIntent = new Intent(MainActivity.this, typeselectActivity.class);
                MainActivity.this.startActivity(localIntent);
            }
        };

        localButton1.setOnClickListener(local1);
        localButton2.setOnClickListener(local1);
        localButton3.setOnClickListener(local1);
        localButton4.setOnClickListener(local1);
        localButton5.setOnClickListener(local1);
        local2 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent localIntent = new Intent(MainActivity.this, percenttypeActivity.class);
                MainActivity.this.startActivity(localIntent);
            }
        };
        localButton6.setOnClickListener(local2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_perc) {
            Intent intent1 = new Intent(MainActivity.this, percenttypeActivity.class);
            startActivity(intent1);
        } else if (id == R.id.nav_del) {
            try {
                getBaseContext().deleteDatabase("attendance.db");
            }catch(Exception e){
                Toast.makeText(MainActivity.this, "Can't Delete Data!", Toast.LENGTH_LONG).show();
            }
            doRestart(MainActivity.this);
        } else if (id == R.id.nav_exit) {
            finishAndRemoveTask();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void doRestart(Context c) {
        try {
            //check if the context is given
            if (c != null) {
                //fetch the packagemanager so we can get the default launch activity
                // (you can replace this intent with any other activity if you want
                PackageManager pm = c.getPackageManager();
                //check if we got the PackageManager
                if (pm != null) {
                    //create the intent with the default start activity for your application
                    Intent mStartActivity = pm.getLaunchIntentForPackage(
                            c.getPackageName()
                    );
                    if (mStartActivity != null) {
                        mStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        //create a pending intent so the application is restarted after finishAffinity(); System.exit(0); was called.
                        // We use an AlarmManager to call this intent in 100ms
                        int mPendingIntentId = 223344;
                        PendingIntent mPendingIntent = PendingIntent
                                .getActivity(c, mPendingIntentId, mStartActivity,
                                        PendingIntent.FLAG_CANCEL_CURRENT);
                        AlarmManager mgr = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
                        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                        //kill the application
                        Toast.makeText(MainActivity.this, "Deleting Data. Please Wait!", Toast.LENGTH_LONG).show();
                        finishAffinity(); System.exit(0);
                    } else {
                        Toast.makeText(MainActivity.this, "Was not able to restart application", Toast.LENGTH_LONG).show();
                        finishAffinity(); System.exit(0);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Was not able to restart application", Toast.LENGTH_LONG).show();
                    finishAffinity(); System.exit(0);
                }
            } else {
                Toast.makeText(MainActivity.this, "Was not able to restart application", Toast.LENGTH_LONG).show();
                finishAffinity(); System.exit(0);
            }
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, "Was not able to restart application", Toast.LENGTH_LONG).show();
            finishAffinity(); System.exit(0);
        }
    }

}


