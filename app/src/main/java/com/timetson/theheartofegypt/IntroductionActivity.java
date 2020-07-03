package com.timetson.theheartofegypt;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class IntroductionActivity extends AppCompatActivity {

    ////////// assign views ////////
    private TextView introductionTitleText;
    private TextView introductionText;

    ////////////for Language Setting///////////////////
    private String LanguageCode = PreferenceManager.getDefaultSharedPreferences(TheHeartOfEgypt.getAppContext()).getString("lang_code", "ar");

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    //////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        ////////initialization///////////
        final Context context = this;
        introductionText=findViewById(R.id.introduction_text);
        introductionTitleText = findViewById(R.id.intoduction_title_text);
        ////////////////////////////////

        ///////set language///////////
        setLanguage(context, LanguageCode);
        //////////////////////////

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

       introductionText.setMovementMethod(new ScrollingMovementMethod());
    }

    private void setLanguage(Context context, String languageCode) {
        if (languageCode.equals("en")) {
            introductionTitleText.setText(context.getResources().getString(R.string.string_introduction_title_en));
            introductionText.setText("*We are sorry, English translation not available yet.*\n"+context.getResources().getString(R.string.string_introduction_ar));
        } else if (languageCode.equals("ar")) {
            introductionTitleText.setText(context.getResources().getString(R.string.string_introduction_title_ar));
            introductionText.setText(context.getResources().getString(R.string.string_introduction_ar));
        }
    }

}
