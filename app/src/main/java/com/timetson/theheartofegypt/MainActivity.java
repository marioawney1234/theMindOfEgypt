package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;


public class MainActivity extends AppCompatActivity {

    ////////// assign views ////////
    private Button buttonIntroduction;
    private Button buttonAlphabet;
    private Button buttonReference;
    private Button buttonDictionary;
    private Button buttonLessons;
    private TextView textViewUpdates;

    ////////////for Language Setting///////////////////
    private String LanguageCode = PreferenceManager.getDefaultSharedPreferences(TheHeartOfEgypt.getAppContext()).getString("lang_code", "ar");

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onRestart() {
        recreate();
        super.onRestart();
    }
    //////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////initialization///////////
        final Context context = this;
        buttonIntroduction = findViewById(R.id.main_button_introduction);
        buttonAlphabet = findViewById(R.id.main_button_alphabet);
        buttonReference = findViewById(R.id.main_button_references);
        buttonDictionary = findViewById(R.id.id_main_button_dictionary);
        buttonLessons = findViewById(R.id.main_button_lessons);
        textViewUpdates = findViewById(R.id.main_text_updates);

        ImageView buttonSettings = findViewById(R.id.main_settings_button);
        Button btnTTS = findViewById(R.id.btn_tts);

        ///////set language///////////
        setLanguage(context, LanguageCode);
        //////////////////////////
        buttonIntroduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, IntroductionActivity.class);
                startActivity(intent);
            }
        });

        buttonAlphabet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, DialictsActivity.class);
                startActivity(intent);
            }
        });

        buttonReference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, ReferencesActivity.class);
                startActivity(intent);
            }
        });

        btnTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, TTsActivity.class);
                startActivity(intent);
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, SettingsActivity.class);
                startActivity(intent);
            }
        });

        ((TextView) findViewById(R.id.main_calender_text)).setText(copticCalender.get_calender());
    }

    private void setLanguage(Context context, String languageCode) {
        if (languageCode.equals("en")) {
            buttonIntroduction.setText(context.getResources().getString(R.string.string_main_introduction));
            buttonAlphabet.setText(context.getResources().getString(R.string.string_main_alphabet));
            buttonReference.setText(context.getResources().getString(R.string.string_main_references));
            buttonDictionary.setText(context.getResources().getString(R.string.string_main_dictionary));
            buttonLessons.setText(context.getResources().getString(R.string.string_main_lessons));
            textViewUpdates.setText(context.getResources().getString(R.string.string_main_updates));
        } else if (languageCode.equals("ar")) {
            buttonIntroduction.setText(context.getResources().getString(R.string.string_main_introduction_ar));
            buttonAlphabet.setText(getResources().getString(R.string.string_main_alphabet_ar));
            buttonReference.setText(getResources().getString(R.string.string_main_references_ar));
            buttonDictionary.setText(getResources().getString(R.string.string_main_dictionary_ar));
            buttonLessons.setText(getResources().getString(R.string.string_main_lessons_ar));
            textViewUpdates.setText(getResources().getString(R.string.string_main_updatese_ar));
        }
    }


}
