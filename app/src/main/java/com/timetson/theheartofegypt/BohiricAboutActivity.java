package com.timetson.theheartofegypt;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.timetson.theheartofegypt.helpers.localeHelper;
import com.timetson.theheartofegypt.modules.DataContainer;

public class BohiricAboutActivity extends AppCompatActivity {

    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialict_about);

        // Ads code
        DataContainer.AdmobLoad(this, mContext, R.id.adView);
        // end Ads code

        TextView textView = findViewById(R.id.dialect_text);
        textView.setText(localeHelper.getLocalizedResources(mContext, DataContainer.LanguageCode).getString(R.string.dialect_about_bohiric));
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}