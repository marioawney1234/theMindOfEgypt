package com.timetson.theheartofegypt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class SahidicAboutActivity extends AppCompatActivity {

    Context context=this;
    private String LanguageCode = PreferenceManager.getDefaultSharedPreferences(TheHeartOfEgypt.getAppContext()).getString("lang_code", "ar");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialict_about);

        ////////// adds code  ////////////////
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ////////////// end ads code /////////////////

        TextView textView=findViewById(R.id.dialect_text);
        if(LanguageCode.equals("en"))
            textView.setText("*We are sorry, English translation not available yet.*\n"+context.getResources().getString(R.string.dialect_about_sahidic));
        else
            textView.setText(context.getResources().getString(R.string.dialect_about_sahidic));
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}