package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.timetson.theheartofegypt.helpers.WordsHelper;
import com.timetson.theheartofegypt.helpers.localeHelper;
import com.timetson.theheartofegypt.modules.DataContainer;

public class WordActivity extends AppCompatActivity {


    ////////// assign views ////////
    private TextView wordTitleText;
    private TextView wordCoptic;
    private TextView wordArabic;
    private TextView wordEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        ////////initialization///////////
        final Context mContext = this;
        wordTitleText = findViewById(R.id.word_title);
        wordCoptic = findViewById(R.id.word_coptic);
        wordArabic = findViewById(R.id.word_arabic);
        wordEnglish = findViewById(R.id.word_english);
        ////////////////////////////////

        ///////set language///////////
        updateLanguage(mContext, DataContainer.LanguageCode);
        //////////////////////////

        // Ads code
        DataContainer.AdmobLoad(this, mContext, R.id.adView);
        // end Ads code

        String[] wordsList = new WordsHelper(mContext).getWord();
        wordCoptic.setText(wordsList[0]);
        wordArabic.setText(wordsList[1]);
        wordEnglish.setText(wordsList[2]);
    }

    ////////////for Language Setting///////////////////
    private void updateLanguage(Context context, String language) {
        Resources resources = localeHelper.getLocalizedResources(context, language);
        wordTitleText.setText(resources.getString(R.string.string_word_title));
    }
    /////////////////////////////////////////////////
}