package com.timetson.theheartofegypt;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.timetson.theheartofegypt.modules.DataContainer;

public class ReferencesActivity extends AppCompatActivity {

    ////////// assign views ////////
    private TextView referenceTitleText;

    ////////////for Language Setting///////////////////
    private String LanguageCode = PreferenceManager.getDefaultSharedPreferences(TheHeartOfEgypt.getAppContext()).getString("lang_code", "ar");

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        ////////initialization///////////
        final Context context = this;
        referenceTitleText = findViewById(R.id.references_title);
        ////////////////////////////////

        ///////set language///////////
        setLanguage(context, LanguageCode);
        //////////////////////////

        // Ads code
        DataContainer.AdmobLoad(this,context,R.id.adView);
        // end Ads code

        ((ListView) findViewById(R.id.references_list)).setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.References)));
    }

    private void setLanguage(Context context, String languageCode) {
        if (languageCode.equals("en")) {
            referenceTitleText.setText(context.getResources().getString(R.string.string_references_title_en));
        } else if (languageCode.equals("ar")) {
            referenceTitleText.setText(context.getResources().getString(R.string.string_references_title_ar));
        }
    }
}
