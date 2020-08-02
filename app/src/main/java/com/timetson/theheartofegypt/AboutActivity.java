package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.timetson.theheartofegypt.helpers.localeHelper;
import com.timetson.theheartofegypt.modules.DataContainer;

public class AboutActivity extends AppCompatActivity {

    ////////// Declare views ////////
    private TextView textViewTeam;
    private TextView textViewVersion;
    private TextView textViewCaution;
    private TextView textViewPlan;
    private TextView textViewShare;
    private TextView textViewContrbution;
    ////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ////////initialization///////////
        final Context mContext = this;
        textViewTeam = findViewById(R.id.about_team_text);
        textViewVersion = findViewById(R.id.about_version_text);
        textViewCaution = findViewById(R.id.about_caution_text);
        textViewPlan = findViewById(R.id.about_plan_text);
        textViewShare = findViewById(R.id.about_share_text);
        textViewContrbution = findViewById(R.id.about_contrbution_text);

        ///////////////////////////////

        // update language
        updateLanguage(mContext, DataContainer.LanguageCode);

        // Ads code
        DataContainer.AdmobLoad(this, mContext, R.id.adView);
        // end Ads code

        LinearLayout facebookPage = findViewById(R.id.about_facebook_layout);
        facebookPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/theheartofkeami"));//("fb://page/110853817349569"));//("https://facebook.com/theheartofkemi"));
                startActivity(browserIntent);
            }
        });

        LinearLayout shareApp = findViewById(R.id.about_share_layout);
        shareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
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

    void updateLanguage(Context context, String language) {
        Resources resources = localeHelper.getLocalizedResources(context, language);
        textViewTeam.setText(resources.getString(R.string.about_team));
        textViewVersion.setText(resources.getString(R.string.about_version));
        textViewCaution.setText(resources.getString(R.string.about_caution));
        textViewPlan.setText(resources.getString(R.string.about_plan));
        textViewShare.setText(resources.getString(R.string.about_share));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textViewContrbution.setText(Html.fromHtml(resources.getString(R.string.about_contrbution), Html.FROM_HTML_MODE_COMPACT));
        } else {
            textViewContrbution.setText(Html.fromHtml(resources.getString(R.string.about_contrbution)));
        }
        textViewContrbution.setMovementMethod(LinkMovementMethod.getInstance());
    }

}