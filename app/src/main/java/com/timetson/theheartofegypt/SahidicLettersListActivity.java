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

    final Context context = this;
    List<LetterModule> moduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letters_list);

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

        ((TextView) findViewById(R.id.title_text_coptic)).setText("ⲁⲗⲫⲁⲃⲏⲧⲟⲛ ⲛⲣⲉⲙⲣⲏⲥ");
        ((TextView) findViewById(R.id.title_text_arabic)).setText("الابجديه القبطيه الصعيديه");
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
