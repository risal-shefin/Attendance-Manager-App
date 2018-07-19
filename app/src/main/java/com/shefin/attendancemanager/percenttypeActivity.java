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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class percenttypeActivity
        extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static String section;
    public static String semester = "Odd";
    public static String type;
    public static String year = "1";
    public static String tmpyear = "";

    static {
        section = "A";
        type = "Theory";
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_navpercenttype);
        final String[] arrayOfString1 = {"1", "2", "3", "4"};
        final String[] arrayOfString2 = {"A", "B", "C"};
        final String[] arrayOfString3 = {"Theory", "Sessional"};
        final String[] arrayOfString4 = {"Odd", "Even"};
        ArrayAdapter localArrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayOfString1);
        localArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter localArrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayOfString2);
        localArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter localArrayAdapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayOfString3);
        localArrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter localArrayAdapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayOfString4);
        localArrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner localSpinner1 = (Spinner) findViewById(R.id.yearsel2);
        Spinner localSpinner2 = (Spinner) findViewById(R.id.secsel2);
        Spinner localSpinner3 = (Spinner) findViewById(R.id.typesel2);
        Spinner localSpinner4 = (Spinner) findViewById(R.id.semsel);
        localSpinner1.setAdapter(localArrayAdapter1);
        localSpinner2.setAdapter(localArrayAdapter2);
        localSpinner3.setAdapter(localArrayAdapter3);
        localSpinner4.setAdapter(localArrayAdapter4);
        AdapterView.OnItemSelectedListener local1 = new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                percenttypeActivity.this.tmpyear = arrayOfString1[paramAnonymousInt];
                Integer localInteger = Integer.valueOf(-1 + 2 * Integer.valueOf(Integer.parseInt(percenttypeActivity.this.tmpyear)).intValue());
                if (percenttypeActivity.semester == "Even") {
                    localInteger = Integer.valueOf(1 + localInteger.intValue());
                }
                percenttypeActivity.year = localInteger.toString();
            }

            public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {
            }
        };
        AdapterView.OnItemSelectedListener local2 = new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                percenttypeActivity.section = arrayOfString2[paramAnonymousInt];
            }

            public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {
            }
        };
        AdapterView.OnItemSelectedListener local3 = new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                percenttypeActivity.type = arrayOfString3[paramAnonymousInt];
                if (paramAnonymousInt == 1) {
                    percenttypeActivity.type = "Lab";
                }
            }

            public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {
            }
        };
        AdapterView.OnItemSelectedListener local4 = new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                percenttypeActivity.semester = arrayOfString4[paramAnonymousInt];
                Integer localInteger = Integer.valueOf(-1 + 2 * Integer.valueOf(Integer.parseInt(percenttypeActivity.this.tmpyear)).intValue());
                if (percenttypeActivity.semester == "Even") {
                    localInteger = Integer.valueOf(1 + localInteger.intValue());
                }
                percenttypeActivity.year = localInteger.toString();
            }

            public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {
            }
        };
        localSpinner1.setOnItemSelectedListener(local1);
        localSpinner2.setOnItemSelectedListener(local2);
        localSpinner3.setOnItemSelectedListener(local3);
        localSpinner4.setOnItemSelectedListener(local4);
        ((Button) findViewById(R.id.savetype2)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (percenttypeActivity.section == "A") {
                    Intent localIntent1 = new Intent(percenttypeActivity.this, percentaActivity.class);
                    percenttypeActivity.this.startActivity(localIntent1);
                }
                if (percenttypeActivity.section == "B") {
                    Intent localIntent2 = new Intent(percenttypeActivity.this, percentbActivity.class);
                    percenttypeActivity.this.startActivity(localIntent2);
                }
                if (percenttypeActivity.section == "C") {
                    Intent localIntent3 = new Intent(percenttypeActivity.this, percentcActivity.class);
                    percenttypeActivity.this.startActivity(localIntent3);
                }
            }
        });

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
            Intent intent1 = new Intent(percenttypeActivity.this, percenttypeActivity.class);
            startActivity(intent1);
        } else if (id == R.id.nav_del) {
            try {
                getBaseContext().deleteDatabase("attendance.db");
            }catch(Exception e){
                Toast.makeText(percenttypeActivity.this, "Can't Delete Data!", Toast.LENGTH_LONG).show();
            }
            doRestart(percenttypeActivity.this);
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
                        Toast.makeText(percenttypeActivity.this, "Deleting Data. Please Wait!", Toast.LENGTH_LONG).show();
                        finishAffinity(); System.exit(0);
                    } else {
                        Toast.makeText(percenttypeActivity.this, "Was not able to restart application", Toast.LENGTH_LONG).show();
                        finishAffinity(); System.exit(0);
                    }
                } else {
                    Toast.makeText(percenttypeActivity.this, "Was not able to restart application", Toast.LENGTH_LONG).show();
                    finishAffinity(); System.exit(0);
                }
            } else {
                Toast.makeText(percenttypeActivity.this, "Was not able to restart application", Toast.LENGTH_LONG).show();
                finishAffinity(); System.exit(0);
            }
        } catch (Exception ex) {
            Toast.makeText(percenttypeActivity.this, "Was not able to restart application", Toast.LENGTH_LONG).show();
            finishAffinity(); System.exit(0);
        }
    }
}
