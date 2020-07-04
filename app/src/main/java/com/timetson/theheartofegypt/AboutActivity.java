package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.timetson.theheartofegypt.modules.DataContainer;

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

        // Ads code
        DataContainer.AdmobLoad(this,context,R.id.adView);
        // end Ads code

        LinearLayout facebookPage=findViewById(R.id.about_facebook_layout);
        facebookPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/theheartofkeami"));//("fb://page/110853817349569"));//("https://facebook.com/theheartofkemi"));
                startActivity(browserIntent);
            }
        });

        LinearLayout shareApp=findViewById(R.id.about_share_layout);
        shareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                PackageManager packageManager = context.getPackageManager();
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "يمكنك تنزيل تطبيق \"Ⲡϩⲏⲧ ⲛⲭⲏⲙⲓ\" من الرابط التالي : \nYou can download \"Ⲡϩⲏⲧ ⲛⲭⲏⲙⲓ\" APP from below link :\n" + "https://play.google.com/store/apps/details?id=" +
                        getPackageName();
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Ⲡϩⲏⲧ ⲛⲭⲏⲙⲓ");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share APP"));
            }
        });
    }

}