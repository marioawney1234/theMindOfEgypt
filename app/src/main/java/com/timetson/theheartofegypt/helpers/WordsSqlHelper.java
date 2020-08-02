package com.timetson.theheartofegypt.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.preference.PreferenceManager;

import com.timetson.theheartofegypt.modules.TheHeartOfEgypt;
import com.timetson.theheartofegypt.modules.copticCalender;

import org.xmlpull.v1.XmlPullParser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class WordsSqlHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "ϩⲁⲛcⲁϫⲓ.db";
    public static final int DB_CURRENT_VERSION = 2;
    private static final String ASSETS_PATH = "databases";
    public static String DB_Location;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    public WordsSqlHelper(Context context) {
        super(context, DB_NAME, null, DB_CURRENT_VERSION);
        Log.d("TAG", "WordsSqlHelper: 0");
        DB_Location = context.getApplicationInfo().dataDir + "/databases/";
        this.mContext = context;
    }

    public String createDataBase() throws IOException {
        String error = XmlPullParser.NO_NAMESPACE;
        Log.d("TAG", "WordsSqlHelper: 1");
        if (!checkDataBase()) {
            getReadableDatabase();
            close();
            try {
                copyDataBase();
                Log.d("TAG", "createDataBase: DB copied");
            } catch (IOException mIOException) {
                return error + mIOException.getStackTrace().toString();
            }
        }
        return error;
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(DB_Location + DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception ignored) {
        }

        if (checkDB != null) {
            checkDB.close();
        }
        boolean check = checkDB != null;

        int DB_Version = PreferenceManager.getDefaultSharedPreferences(TheHeartOfEgypt.getAppContext()).getInt(DB_NAME, 0);
        if (DB_Version != DB_CURRENT_VERSION)
            check = false;

        return check;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = mContext.getAssets().open(ASSETS_PATH + "/" + DB_NAME);
        OutputStream myOutput = new FileOutputStream(DB_Location + DB_NAME);
        byte[] buffer = new byte[AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT];
        while (true) {
            int length = myInput.read(buffer);
            if (length > 0) {
                myOutput.write(buffer, 0, length);
            } else {
                myOutput.flush();
                myOutput.close();
                myInput.close();
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(TheHeartOfEgypt.getAppContext()).edit();
                edit.putInt(DB_NAME, DB_CURRENT_VERSION);
                edit.apply();
                return;
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean openDataBase() throws SQLException {
        this.mDatabase = getReadableDatabase();// SQLiteDatabase.openDatabase(DB_Location + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        return this.mDatabase != null;
    }

    public synchronized void close() {
        if (this.mDatabase != null) {
            this.mDatabase.close();
        }
        super.close();
    }

    ///////////////////////////////////////////////////////////////////////

    public String[] getWord() {
        long today = (copticCalender.dayOfYear() % 255) + 1;
        String[] list = {"", "", ""};
        openDataBase();
        Cursor cursor;
        cursor = mDatabase.rawQuery("select * from 'words' Where word_id=\"" + today + "\"", null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            list[0] = cursor.getString(cursor.getColumnIndex("coptic"));
            list[1] = cursor.getString(cursor.getColumnIndex("arabic"));
            list[2] = cursor.getString(cursor.getColumnIndex("english"));
        }
        cursor.close();
        close();
        return list;
    }
}
