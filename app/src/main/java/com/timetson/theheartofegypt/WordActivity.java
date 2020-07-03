package com.timetson.theheartofegypt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.timetson.theheartofegypt.Helper.LettersSqlHelper;
import com.timetson.theheartofegypt.Helper.WordsSqlHelper;

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

        // adds code
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        // end ads code

        long today= (copticCalender.dayOfYear()%255)+1;
        String[] wordsList =new WordsSqlHelper(context).getWord((int)today);
        wordCoptic.setText(wordsList[0]);
        wordArabic.setText(wordsList[1]);
        wordEnglsh.setText(wordsList[2]);
    }
}