package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.timetson.theheartofegypt.helpers.localeHelper;
import com.timetson.theheartofegypt.modules.DataContainer;


public class SahidicMenuActivity extends AppCompatActivity {

    ////////// assign views ////////
    private Button buttonAbout;
    private Button buttonAlphabet;
    private TextView title;
    ////////////////////////////////

    ////////////for Language Setting///////////////////

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    private void updateLanguage(Context context, String language) {
        Resources resources = localeHelper.getLocalizedResources(context, language);
        title.setText(resources.getString(R.string.dialect_menu_sahidic));
        buttonAbout.setText(resources.getString(R.string.dialect_menu_about));
        buttonAlphabet.setText(resources.getString(R.string.dialect_menu_alphabet));
    }
    ////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialect_menu);

        ////////initialization///////////
        final Context context = this;
        buttonAlphabet = findViewById(R.id.dialect_menu_button_alphbet);
        buttonAbout = findViewById(R.id.dialect_menu_button_about);
        title = findViewById(R.id.head_title);
        ///////////////////////////////

        ///////set language///////////
        updateLanguage(context, DataContainer.LanguageCode);
        //////////////////////////

        // Ads code
        DataContainer.AdmobLoad(this, context, R.id.adView);
        // end Ads code


        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, SahidicAboutActivity.class);
                startActivity(intent);
            }
        });

        buttonAlphabet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, SahidicLettersListActivity.class);
                startActivity(intent);
            }
        });

    }
}
