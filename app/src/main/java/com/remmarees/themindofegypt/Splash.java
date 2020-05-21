package com.remmarees.themindofegypt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.remmarees.themindofegypt.sqlHelper.bohiricLettersSqlHelper;

import java.io.IOException;


public class Splash extends AppCompatActivity {

    public final Context context = this;
    private bohiricLettersSqlHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ///////////////////////////////////////////////
        //SharedPreferences set = getSharedPreferences("${context.packageName}.database_versions",Context.MODE_PRIVATE);
         /* ***** Create Thread that will sleep for 3 seconds ************ */
        Thread background = new Thread() {
            public void run() {
                Intent i = null;
                Log.d("TAG", "bohiricLettersSqlHelper: 2");

                // database
                mDbHelper = new bohiricLettersSqlHelper(context);
                Log.d("TAG", "bohiricLettersSqlHelper: 3");

                try {
                    mDbHelper.createDataBase();
                } catch (IOException mIOException) {
                    throw new Error("UnableToCreateDatabase");
                }


                try {
                    // Thread will sleep for 3 seconds
                    sleep(2 * 1000);

                    // After 2 seconds redirect to another intent
                    i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);

                    //Remove activity
                    finish();

                } catch (Exception e) {
                    startActivity(i);
                }
            }
        };

        // start thread
        background.start();
    }
}
