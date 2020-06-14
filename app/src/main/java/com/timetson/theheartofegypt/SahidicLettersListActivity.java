package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.timetson.theheartofegypt.Helper.DataContainer;
import com.timetson.theheartofegypt.Helper.LetterModule;
import com.timetson.theheartofegypt.Helper.LettersSqlHelper;

import java.util.List;

public class SahidicLettersListActivity extends AppCompatActivity {

    ////////// assign views ////////
    List<LetterModule> moduleList;
    private TextView textViewTitleCoptic;
    private TextView textViewTitle;
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
            textViewTitle.setText(context.getResources().getString(R.string.letter_list_title_sahidic_en));
        } else if (languageCode.equals("ar")) {
            textViewTitle.setText(context.getResources().getString(R.string.letter_list_title_sahidic_ar));
        }
    }
    ////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letters_list);

        ////////initialization///////////
        final Context context = this;
        textViewTitle = findViewById(R.id.letters_list_title);
        textViewTitleCoptic = findViewById(R.id.letters_list_title_coptic);
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


        textViewTitleCoptic.setText(context.getResources().getString(R.string.letter_list_title_sahidic_co));
        ListView lettersListView = findViewById(R.id.lettersListView);
        moduleList = new LettersSqlHelper(context).getSahidicLetters();
        DataContainer.sahidicLetterModuleList = moduleList;
        ArrayAdapter<LetterModule> letterListAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, android.R.id.text1, moduleList);
        lettersListView.setAdapter(letterListAdapter);

        AdapterView.OnItemClickListener aa = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(context, SahidicLetterDisplayActivity.class);
                intent.putExtra("POSITION", position);
                startActivity(intent);
            }
        };
        lettersListView.setOnItemClickListener(aa);
    }
}
