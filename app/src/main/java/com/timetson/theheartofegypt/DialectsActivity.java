package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.timetson.theheartofegypt.helpers.localeHelper;
import com.timetson.theheartofegypt.modules.DataContainer;


public class DialectsActivity extends AppCompatActivity {

    ////////// assign views ////////
    private Button buttonBohiric;
    private Button buttonSahidic;
    ////////////////////////////////

    ////////////for Language Setting///////////////////

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    private void updateLanguage(Context context, String language) {
        Resources resources = localeHelper.getLocalizedResources(context, language);
        buttonBohiric.setText(resources.getString(R.string.string_dialict_bohiric));
        buttonSahidic.setText(resources.getString(R.string.string_dialict_sahidic));
    }
    ////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialects);

        ////////initialization///////////
        final Context context = this;
        buttonBohiric = findViewById(R.id.button_bohiric);
        buttonSahidic = findViewById(R.id.button_sahidic);
        ///////////////////////////////

        ///////set language///////////
        updateLanguage(context, DataContainer.LanguageCode);
        //////////////////////////

        // Ads code
        DataContainer.AdmobLoad(this, context, R.id.adView);
        // end Ads code


        buttonBohiric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, BohiricMenuActivity.class);
                startActivity(intent);
            }
        });

        buttonSahidic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, SahidicMenuActivity.class);
                startActivity(intent);
            }
        });

    }
}
