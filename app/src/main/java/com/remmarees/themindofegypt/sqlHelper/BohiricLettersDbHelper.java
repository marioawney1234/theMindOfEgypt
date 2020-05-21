package com.remmarees.themindofegypt.sqlHelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class BohiricLettersDbHelper extends SQLiteOpenHelper{

    private Context context;
    private static final String ASSETS_PATH = "databases";
    private static final String DATABASE_NAME = "letters.db";
    private static final int DATABASE_VERSION = 1;
    private SharedPreferences preferences= context.getSharedPreferences("${context.packageName}.database_versions",Context.MODE_PRIVATE);

    public BohiricLettersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private Boolean installedDatabaseIsOutdated(){
        return preferences.getInt(DATABASE_NAME, 0) < DATABASE_VERSION;
    }

    private void writeDatabaseVersionInPreferences() {
        SharedPreferences.Editor edit=preferences.edit();
        edit.putInt(DATABASE_NAME, DATABASE_VERSION);
        edit.apply();
    }

    private void installDatabaseFromAssets() throws IOException {
        InputStream inputStream = context.getAssets().open("$ASSETS_PATH/$DATABASE_NAME.sqlite3");
        File outputFile = new File(context.getDatabasePath(DATABASE_NAME).getPath());
        OutputStream outputStream = new FileOutputStream(outputFile);
        byte[] buffer = new byte[AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT];
        while (true) {
            int length = inputStream.read(buffer);
            if (length > 0) {
                outputStream.write(buffer, 0, length);
            } else {
                outputStream.flush();
                outputStream.close();
                inputStream.close();
                return;
            }
        }
    }


    private void installOrUpdateIfNecessary() {
        if (installedDatabaseIsOutdated()) {
            context.deleteDatabase(DATABASE_NAME);
            try {
                installDatabaseFromAssets();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writeDatabaseVersionInPreferences();
        }
    }

    @Override
    public SQLiteDatabase  getReadableDatabase() {
        installOrUpdateIfNecessary();
        return super.getReadableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
