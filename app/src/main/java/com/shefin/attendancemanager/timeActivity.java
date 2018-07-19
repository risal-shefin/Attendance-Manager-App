package com.shefin.attendancemanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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

public class timeActivity
        extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_navtime);
        Button localButton1 = (Button) findViewById(R.id.theory1);
        Button localButton2 = (Button) findViewById(R.id.theory2);
        Button localButton3 = (Button) findViewById(R.id.theory3);
        Button localButton4 = (Button) findViewById(R.id.theory4);
        Button localButton5 = (Button) findViewById(R.id.theory5);
        Button localButton6 = (Button) findViewById(R.id.theory6);
        Button localButton7 = (Button) findViewById(R.id.theory7);
        Button localButton8 = (Button) findViewById(R.id.theory8);
        Button localButton9 = (Button) findViewById(R.id.theory9);
        View.OnClickListener local1 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent localIntent = new Intent(timeActivity.this, classtypeActivity.class);
                timeActivity.this.startActivity(localIntent);
            }
        };
        localButton1.setOnClickListener(local1);
        localButton2.setOnClickListener(local1);
        localButton3.setOnClickListener(local1);
        localButton4.setOnClickListener(local1);
        localButton5.setOnClickListener(local1);
        localButton6.setOnClickListener(local1);
        localButton7.setOnClickListener(local1);
        localButton8.setOnClickListener(local1);
        localButton9.setOnClickListener(local1);

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
            Intent intent1 = new Intent(timeActivity.this, percenttypeActivity.class);
            startActivity(intent1);
        } else if (id == R.id.nav_del) {
            try {
                getBaseContext().deleteDatabase("attendance.db");
            }catch(Exception e){
                Toast.makeText(timeActivity.this, "Can't Delete Data!", Toast.LENGTH_LONG).show();
            }
            doRestart(timeActivity.this);
        } else if (id == R.id.nav_exit) {
             finishAffinity(); System.exit(0);
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
                        //create a pending intent so the application is restarted after finishAndRemoveTask(); was called.
                        // We use an AlarmManager to call this intent in 100ms
                        int mPendingIntentId = 223344;
                        PendingIntent mPendingIntent = PendingIntent
                                .getActivity(c, mPendingIntentId, mStartActivity,
                                        PendingIntent.FLAG_CANCEL_CURRENT);
                        AlarmManager mgr = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
                        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                        //kill the application
                        Toast.makeText(timeActivity.this, "Deleting Data. Please Wait!", Toast.LENGTH_LONG).show();
                         finishAffinity(); System.exit(0);
                    } else {
                        Toast.makeText(timeActivity.this, "Was not able to restart application", Toast.LENGTH_LONG).show();
                         finishAffinity(); System.exit(0);
                    }
                } else {
                    Toast.makeText(timeActivity.this, "Was not able to restart application", Toast.LENGTH_LONG).show();
                     finishAffinity(); System.exit(0);
                }
            } else {
                Toast.makeText(timeActivity.this, "Was not able to restart application", Toast.LENGTH_LONG).show();
                 finishAffinity(); System.exit(0);
            }
        } catch (Exception ex) {
            Toast.makeText(timeActivity.this, "Was not able to restart application", Toast.LENGTH_LONG).show();
             finishAffinity(); System.exit(0);
        }
    }
}