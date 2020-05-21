package com.remmarees.themindofegypt.sqlHelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import org.xmlpull.v1.XmlPullParser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class bohiricLettersSqlHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "letters.db";
    private static final String ASSETS_PATH = "databases";
    public static final int DB_VERSION = 1;
    public static String DB_Location;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    //private SharedPreferences preferences= mContext.getSharedPreferences("${context.packageName}.database_versions",Context.MODE_PRIVATE);

    public bohiricLettersSqlHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d("TAG", "bohiricLettersSqlHelper: 0");
        DB_Location = context.getApplicationInfo().dataDir + "/databases/";
        this.mContext = context;
    }

    public String createDataBase() throws IOException {
        String error = XmlPullParser.NO_NAMESPACE;
        Log.d("TAG", "bohiricLettersSqlHelper: 1");
        if (!checkDataBase()) {
            getReadableDatabase();
            close();
            try {
                copyDataBase();
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
        } catch (Exception e) {
        }

        if (checkDB != null) {
            checkDB.close();
        }
        boolean check= (checkDB != null) ? true : false;
        return check;//preferences.getInt(DB_NAME, 0)  != DB_VERSION;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput= mContext.getAssets().open(ASSETS_PATH +"/"+ DB_NAME);
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
                //SharedPreferences.Editor edit=preferences.edit();
                //edit.putInt(DB_NAME, DB_VERSION);
                //edit.apply();
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

    public List<LetterModule> getBohiricLetters() {
        LetterModule module = null;
        List<LetterModule> list = new ArrayList<>();
        openDataBase();
        Cursor cursor = null;
        cursor = mDatabase.rawQuery("select * from bohiric_letters ORDER BY id", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
             module= new LetterModule(cursor.getString(cursor.getColumnIndex("letter")),cursor.getString(cursor.getColumnIndex("capital")), cursor.getString(cursor.getColumnIndex("name")), cursor.getInt(cursor.getColumnIndex("type")), cursor.getString(cursor.getColumnIndex("comments")));
            list.add(module);
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return list;
    }

    public List<PronounceModule> getBohiricPronouncation(String letter, String type) {
        PronounceModule module = null;
        List<PronounceModule> list = new ArrayList<>();
        openDataBase();
        Cursor cursor = null;
        //cursor = mDatabase.rawQuery("select * from 'pronouncation' order by sort", null);
        cursor = mDatabase.rawQuery("select * from 'bohiric_pronouncation' Where letter=\"" + letter + "\" and type=\""+type+"\" order by sort", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //module= new PronounceModule(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("letter")),cursor.getString(cursor.getColumnIndex("IPA")),
            module= new PronounceModule(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("letter")),cursor.getString(cursor.getColumnIndex("IPA")),cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("arabic_description")),cursor.getString(cursor.getColumnIndex("english_description")),cursor.getString(cursor.getColumnIndex("letter_name")),cursor.getString(cursor.getColumnIndex("audio")));
            list.add(module);
            Log.d("tag", "getProunounce: 0");
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return list;
    }

    public List<LetterModule> getSahidicLetters() {
        LetterModule module = null;
        List<LetterModule> list = new ArrayList<>();
        openDataBase();
        Cursor cursor = null;
        cursor = mDatabase.rawQuery("select * from sahidic_letters ORDER BY id", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            module= new LetterModule(cursor.getString(cursor.getColumnIndex("letter")),cursor.getString(cursor.getColumnIndex("capital")), cursor.getString(cursor.getColumnIndex("name")), cursor.getInt(cursor.getColumnIndex("type")),"-");
            list.add(module);
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return list;
    }

    public List<PronounceModule> getSahidicPronouncation(String letter, String type) {
        PronounceModule module = null;
        List<PronounceModule> list = new ArrayList<>();
        openDataBase();
        Cursor cursor = null;
        cursor = mDatabase.rawQuery("select * from 'sahidic_pronouncation' Where letter=\"" + letter + "\" and type=\""+type+"\" order by sort", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //module= new PronounceModule(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("letter")),cursor.getString(cursor.getColumnIndex("IPA")),
            module= new PronounceModule(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("letter")),cursor.getString(cursor.getColumnIndex("IPA")),cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("arabic_description")),cursor.getString(cursor.getColumnIndex("english_description")),cursor.getString(cursor.getColumnIndex("letter_name")),cursor.getString(cursor.getColumnIndex("audio")));
            list.add(module);
            Log.d("tag", "getProunounce: 0");
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return list;
    }
}
