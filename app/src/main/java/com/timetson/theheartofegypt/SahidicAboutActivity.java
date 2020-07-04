package com.timetson.theheartofegypt;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.timetson.theheartofegypt.modules.DataContainer;

public class SahidicAboutActivity extends AppCompatActivity {

    Context context=this;
    private String LanguageCode = PreferenceManager.getDefaultSharedPreferences(TheHeartOfEgypt.getAppContext()).getString("lang_code", "ar");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialict_about);

        // Ads code
        DataContainer.AdmobLoad(this,context,R.id.adView);
        // end Ads code

        TextView textView=findViewById(R.id.dialect_text);
        if(LanguageCode.equals("en"))
            textView.setText("*We are sorry, English translation not available yet.*\n"+context.getResources().getString(R.string.dialect_about_sahidic));
        else
            textView.setText(context.getResources().getString(R.string.dialect_about_sahidic));
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}