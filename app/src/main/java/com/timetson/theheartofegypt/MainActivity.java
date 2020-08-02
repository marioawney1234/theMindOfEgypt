package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.timetson.theheartofegypt.helpers.localeHelper;
import com.timetson.theheartofegypt.modules.DataContainer;
import com.timetson.theheartofegypt.modules.copticCalender;

public class MainActivity extends AppCompatActivity {

    ////////// Declare used views ////////
    private Button buttonIntroduction;
    private Button buttonDialects;
    private Button buttonReference;
    private Button buttonWord;
    private Button buttonAbout;
    private ImageView buttonSettings;
    private TextView copticCalenderText;
    private TextView newsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ////////// intialization ///////////
        final Context mContext = this;
        DataContainer.LanguageCode = PreferenceManager.getDefaultSharedPreferences(mContext).getString("lang_code", "ar");
        DataContainer.copticCalenderCode = PreferenceManager.getDefaultSharedPreferences(mContext).getString("coptic_calender_code", "1");
        buttonIntroduction = findViewById(R.id.main_button_introduction);
        buttonDialects = findViewById(R.id.main_button_dialicts);
        buttonReference = findViewById(R.id.main_button_references);
        buttonWord = findViewById(R.id.main_button_word);
        buttonAbout = findViewById(R.id.main_button_about);
        buttonSettings = findViewById(R.id.main_settings_button);
        copticCalenderText = findViewById(R.id.main_calender_text);
        newsText = findViewById(R.id.main_text_new);
        /////////////////////////////////////////
        /////// Update selected language ////////
        updateLanguage(mContext, DataContainer.LanguageCode);
        ////////////////////////////////////////

        buttonIntroduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(mContext, IntroductionActivity.class);
                startActivity(intent);
            }
        });

        buttonDialects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(mContext, DialectsActivity.class);
                startActivity(intent);
            }
        });

        buttonReference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(mContext, ReferencesActivity.class);
                startActivity(intent);
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(mContext, SettingsActivity.class);
                startActivity(intent);
            }
        });

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(mContext, AboutActivity.class);
                startActivity(intent);
            }
        });

        buttonWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(mContext, WordActivity.class);
                startActivity(intent);
            }
        });

        copticCalenderText.setText(copticCalender.get_calender(DataContainer.copticCalenderCode));
    }

    @Override
    protected void onRestart() {
        recreate();
        super.onRestart();
    }

    void updateLanguage(Context context, String language) {
        Resources resources = localeHelper.getLocalizedResources(context, language);
        buttonIntroduction.setText(resources.getString(R.string.main_introduction));
        buttonDialects.setText(resources.getString(R.string.main_dialects));
        buttonReference.setText(resources.getString(R.string.main_references));
        buttonWord.setText(resources.getString(R.string.main_word));
        buttonAbout.setText(resources.getString(R.string.main_about));
        newsText.setText(resources.getString(R.string.main_new));
    }
}