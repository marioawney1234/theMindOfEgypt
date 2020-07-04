package com.timetson.theheartofegypt;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.timetson.theheartofegypt.Helper.WordsSqlHelper;
import com.timetson.theheartofegypt.modules.DataContainer;

public class WordActivity extends AppCompatActivity {


    ////////// assign views ////////
    private TextView wordTitleText;
    private TextView wordCoptic;
    private TextView wordArabic;
    private TextView wordEnglsh;

    ////////////for Language Setting///////////////////
    private String LanguageCode = PreferenceManager.getDefaultSharedPreferences(TheHeartOfEgypt.getAppContext()).getString("lang_code", "ar");

    private void setLanguage(Context context, String languageCode) {
        if (languageCode.equals("en")) {
            wordTitleText.setText(context.getResources().getString(R.string.string_word_title_en));
        } else if (languageCode.equals("ar")) {
            wordTitleText.setText(context.getResources().getString(R.string.string_word_title_ar));
        }
    }
    /////////////////////////////////////////////////

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }
    ////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        ////////initialization///////////
        final Context context = this;
        wordTitleText = findViewById(R.id.word_title);
        wordCoptic = findViewById(R.id.word_coptic);
        wordArabic = findViewById(R.id.word_arabic);
        wordEnglsh = findViewById(R.id.word_english);
        ////////////////////////////////

        ///////set language///////////
        setLanguage(context, LanguageCode);
        //////////////////////////

        // Ads code
        DataContainer.AdmobLoad(this,context,R.id.adView);
        // end Ads code

        String[] wordsList =new WordsSqlHelper(context).getWord();
        wordCoptic.setText(wordsList[0]);
        wordArabic.setText(wordsList[1]);
        wordEnglsh.setText(wordsList[2]);
    }
}