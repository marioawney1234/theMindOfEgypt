package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class DialictsActivity extends AppCompatActivity {

    ////////// assign views ////////
    private Button buttonBohiric;
    private Button buttonSahidic;
    ////////////////////////////////

    ////////////for Language Setting///////////////////
    private String LanguageCode = PreferenceManager.getDefaultSharedPreferences(TheHeartOfEgypt.getAppContext()).getString("lang_code", "ar");

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    private void setLanguage(Context context, String languageCode) {
        if (languageCode.equals("en")) {
            buttonBohiric.setText(context.getResources().getString(R.string.string_dialict_bohiric_en));
            buttonSahidic.setText(context.getResources().getString(R.string.string_dialict_sahidic_en));
        } else if (languageCode.equals("ar")) {
            buttonBohiric.setText(context.getResources().getString(R.string.string_dialict_bohiric_ar));
            buttonSahidic.setText(context.getResources().getString(R.string.string_dialict_sahidic_ar));        }
    }
    ////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialicts);

        ////////initialization///////////
        final Context context = this;
        buttonBohiric = findViewById(R.id.button_bohiric);
        buttonSahidic = findViewById(R.id.button_sahidic);
        ///////////////////////////////

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


        buttonBohiric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, BohiricLettersListActivity.class);
                startActivity(intent);
            }
        });

        buttonSahidic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, SahidicLettersListActivity.class);
                startActivity(intent);
            }
        });

    }
}
