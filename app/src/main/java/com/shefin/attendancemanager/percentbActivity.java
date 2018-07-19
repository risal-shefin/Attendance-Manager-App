package com.shefin.attendancemanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import crl.android.pdfwriter.PDFWriter;
import crl.android.pdfwriter.PaperSize;
import crl.android.pdfwriter.StandardFonts;

public class percentbActivity
        extends AppCompatActivity {

    Integer rex = 150, rey = 800;
    public void outputToFile(String fileName, String pdfContent, String encoding) {
        File newFile = new File(Environment.getExternalStorageDirectory() + "/" + fileName);
        try {
            newFile.createNewFile();
            try {
                FileOutputStream pdfFile = new FileOutputStream(newFile);
                pdfFile.write(pdfContent.getBytes(encoding));
                pdfFile.close();
                Toast.makeText(percentbActivity.this, "Result exported in Internal Storage", Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                Toast.makeText(percentbActivity.this, "File I/O Error !", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            Toast.makeText(percentbActivity.this, "File I/O Error !", Toast.LENGTH_LONG).show();
        }
    }
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.percentb);
        TextView[] arrayOfTextView = new TextView[65];
        Integer localInteger1 = Integer.valueOf(61);
        for (Integer localInteger2 = Integer.valueOf(1); localInteger1.intValue() <= 120; localInteger2 = Integer.valueOf(1 + localInteger2.intValue())) {
            String str4 = "percent" + localInteger1.toString();
            int k = getResources().getIdentifier(str4, "id", getPackageName());
            arrayOfTextView[localInteger2.intValue()] = ((TextView) findViewById(k));
            localInteger1 = Integer.valueOf(1 + localInteger1.intValue());
        }
        int i = 1;
        SQLiteDatabase localSQLiteDatabase = getBaseContext().openOrCreateDatabase("attendance.db", 0, null);
        String str1 = "classes";
        if (percenttypeActivity.type == "Lab") {
            str1 = "labs";
        }
        for (char c1 = 'a'; c1 <= 'n'; c1 = (char) (c1 + '\001')) {
            int j = 0;
            for(char c2 = 'A'; c2 <= 'E'; c2++) {
                Cursor localCursor2 = localSQLiteDatabase.rawQuery("SELECT " + c1 + " FROM " + str1 + " WHERE year = " + percenttypeActivity.year + " AND section = '" + percenttypeActivity.section + "' AND day = '" + c2 + "'", null);
                if (localCursor2.moveToFirst()) {
                    if (localCursor2.getInt(0) == 1)
                        j++;
                }
            }
            if (j > i) {
                i = j;
            }
        }
        String str2 = "total";
        if (percenttypeActivity.type == "Lab") {
            str2 = "total2";
        }

        String report = "";
        if(percenttypeActivity.tmpyear == "1")
            report = "Attendance Report of " + percenttypeActivity.tmpyear + "st Year " + percenttypeActivity.semester + " Semester Section:" + percenttypeActivity.section;
        if(percenttypeActivity.tmpyear == "2")
            report = "Attendance Report of " + percenttypeActivity.tmpyear + "nd Year " + percenttypeActivity.semester + " Semester Section:" + percenttypeActivity.section;
        if(percenttypeActivity.tmpyear == "3")
            report = "Attendance Report of " + percenttypeActivity.tmpyear + "rd Year " + percenttypeActivity.semester + " Semester Section:" + percenttypeActivity.section;
        if(percenttypeActivity.tmpyear == "4")
            report = "Attendance Report of " + percenttypeActivity.tmpyear + "th Year " + percenttypeActivity.semester + " Semester Section:" + percenttypeActivity.section;

        PDFWriter writer = new PDFWriter(PaperSize.A4_WIDTH, PaperSize.A4_HEIGHT);
        writer.setFont(StandardFonts.SUBTYPE, StandardFonts.TIMES_BOLDITALIC, StandardFonts.WIN_ANSI_ENCODING);
        writer.addText(5, rey, 25, report);

        writer.setFont(StandardFonts.SUBTYPE, StandardFonts.TIMES_BOLD, StandardFonts.WIN_ANSI_ENCODING);
        rey = 740;
        writer.addText(rex - 10, rey, 20, "Roll");
        writer.addText(rex + 220, rey, 20, "Percentage");
        rey -= 40;

        Integer counter = 61;
        Double localDouble = Double.valueOf(14.0D * i);
        Cursor localCursor1 = localSQLiteDatabase.rawQuery("SELECT roll, total  FROM " + str2 + " WHERE year = " + percenttypeActivity.year + " AND section = 'B'", null);
        if (localCursor1.moveToFirst()) {
            do {
                Integer localInteger3 = Integer.valueOf(-60 + localCursor1.getInt(0));
                String str3 = String.format("%.2f", new Object[]{Double.valueOf(100.0D * (Integer.valueOf(localCursor1.getInt(1)).intValue() / localDouble.doubleValue()))});
                arrayOfTextView[localInteger3.intValue()].setText(str3);

                writer.addText(rex + 250, rey, 20, str3);
                writer.addText(rex, rey, 20, counter.toString());
                counter++;
                rey -= 40;
                if(rey <= 0) {
                    writer.newPage();
                    rey = 800;
                    writer.setFont(StandardFonts.SUBTYPE, StandardFonts.TIMES_BOLD, StandardFonts.WIN_ANSI_ENCODING);
                    writer.addText(rex - 10, rey, 20, "Roll");
                    writer.addText(rex + 220, rey, 20, "Percentage");
                    rey -= 40;
                }
            } while (localCursor1.moveToNext());

            final String freport = report + " " + percenttypeActivity.type + ".pdf";
            final PDFWriter fwriter = writer;
            AlertDialog.Builder builder = new AlertDialog.Builder(percentbActivity.this);
            builder.setTitle("Export")
                    .setMessage("Do you want to export this Attendance Report?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with yes
                            outputToFile(freport, fwriter.asString(), "ISO-8859-1");
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            //outputToFile(report + percenttypeActivity.type + ".pdf", writer.asString(), "ISO-8859-1");
        }
        localCursor1.close();
        localSQLiteDatabase.close();
    }
}