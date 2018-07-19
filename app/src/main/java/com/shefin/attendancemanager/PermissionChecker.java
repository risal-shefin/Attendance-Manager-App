package com.shefin.attendancemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class PermissionChecker
        extends AppCompatActivity
{
    public static final int RequestPermissionCode = 12;

    private void RequestMultiplePermission()
    {
        ActivityCompat.requestPermissions(this, new String[] { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE" }, 12);
    }

    public boolean CheckingPermissionIsEnabledOrNot()
    {
        int i = ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.READ_EXTERNAL_STORAGE");
        int j = ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE");
        return (i == 0) && (j == 0);
    }

    public void WarningDisplay()
    {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setMessage("Without permission App Can't Start!!!!");
        localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                PermissionChecker.this.finish();
            }
        });
        localBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                PermissionChecker.this.startActivity(new Intent(PermissionChecker.this, PermissionChecker.class));
                PermissionChecker.this.finish();
            }
        });
        localBuilder.show();
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        if (Build.VERSION.SDK_INT < 23)
        {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }
        if (CheckingPermissionIsEnabledOrNot())
        {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }
        RequestMultiplePermission();
    }

    public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
    {

        int i = 0;
        if (paramArrayOfInt[0] == 0)
        {
            i = 1;

        }

        for (int j = 1;; j = 0)
        {
            if ((i == 0) || (j == 0)) {
                break;
            }
            startActivity(new Intent(this, MainActivity.class));
            finish();
            i = 0;
            break;
        }
        WarningDisplay();
    }
}
