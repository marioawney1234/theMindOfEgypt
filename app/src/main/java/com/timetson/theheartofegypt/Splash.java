package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.timetson.theheartofegypt.helpers.LettersSqlHelper;
import com.timetson.theheartofegypt.helpers.WordsSqlHelper;

import java.io.IOException;


public class Splash extends AppCompatActivity {

    public final Context context = this;
    private LettersSqlHelper lettersDbHelper;
    private WordsSqlHelper wordsDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        //Force light Mode, Disable Night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        /* ***** Create Thread that will sleep for 3 seconds ************ */
        Thread background = new Thread() {
            public void run() {
                Intent i = null;
                // databases
                lettersDbHelper = new LettersSqlHelper(context);
                wordsDbHelper = new WordsSqlHelper(context);

                try {
                    lettersDbHelper.createDataBase();
                    wordsDbHelper.createDataBase();
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

