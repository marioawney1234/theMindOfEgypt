package com.timetson.theheartofegypt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class AboutActivity extends AppCompatActivity {

    ////////// assign views ////////
    private TextView textViewTeam;
    private TextView textViewVersion;
    private TextView textViewCaution;
    private TextView textViewPlan;
    private TextView textViewShare;
    ////////////////////////////////

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
    private void setLanguage(Context context, String languageCode) {
        if (languageCode.equals("en")) {
            textViewTeam.setText(context.getResources().getString(R.string.about_team_en));
            textViewVersion.setText(context.getResources().getString(R.string.about_version_en));
            textViewCaution.setText(context.getResources().getString(R.string.about_caution_en));
            textViewPlan.setText(context.getResources().getString(R.string.about_plan_en));
            textViewShare.setText(context.getResources().getString(R.string.about_share_en));
        } else if (languageCode.equals("ar")) {
            textViewTeam.setText(context.getResources().getString(R.string.about_team_ar));
            textViewVersion.setText(context.getResources().getString(R.string.about_version_ar));
            textViewCaution.setText(context.getResources().getString(R.string.about_caution_ar));
            textViewPlan.setText(context.getResources().getString(R.string.about_plan_ar));
            textViewShare.setText(context.getResources().getString(R.string.about_share_ar));
        }
    }
    ////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ////////initialization///////////
        final Context context = this;
        textViewTeam=findViewById(R.id.about_team_text);
        textViewVersion=findViewById(R.id.about_version_text);
        textViewCaution=findViewById(R.id.about_caution_text);
        textViewPlan=findViewById(R.id.about_plan_text);
        textViewShare=findViewById(R.id.about_share_text);
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

        LinearLayout facebookPage=findViewById(R.id.about_facebook_layout);
        facebookPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //To do code
            }
        });

        LinearLayout shareApp=findViewById(R.id.about_share_layout);
        shareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "download \"Ⲡϩⲏⲧ ⲛⲭⲏⲙⲓ\" from below link \n" + "https://play.google.com/store/apps/details?id=" +
                        getPackageName();
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Ⲡϩⲏⲧ ⲛⲭⲏⲙⲓ");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share APP"));
            }
        });
    }
}