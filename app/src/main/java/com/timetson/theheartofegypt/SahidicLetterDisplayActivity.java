package com.timetson.theheartofegypt;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.timetson.theheartofegypt.Helper.DataContainer;
import com.timetson.theheartofegypt.Helper.LettersSqlHelper;
import com.timetson.theheartofegypt.Helper.PronounceModule;

import java.util.List;

public class SahidicLetterDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_display);

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

        final Context context = this;
        final int position = getIntent().getIntExtra("POSITION", 0);
        final TextView Capital_letter = findViewById(R.id.cabital_letter);
        final TextView Small_letter = findViewById(R.id.small_letter);
        final TextView letter_type = findViewById(R.id.letter_type);
        final TextView letter_name = findViewById(R.id.letter_name);
        final String[] types = getResources().getStringArray(R.array.letters_type);
        final ScrollView scrollView = findViewById(R.id.scrollview);


        Capital_letter.setText(DataContainer.sahidicLetterModuleList.get(position).getCapital());
        Small_letter.setText(DataContainer.sahidicLetterModuleList.get(position).getLetter());
        letter_type.setText(types[DataContainer.sahidicLetterModuleList.get(position).getType()]);
        letter_name.setText(DataContainer.sahidicLetterModuleList.get(position).getName());

        DataContainer.SahidicPronouncation = new LettersSqlHelper(context).getSahidicPronouncation(DataContainer.sahidicLetterModuleList.get(position).getLetter(), "g");
        setLayoutContents(context, findViewById(R.id.general), "Ⲡⲓϫⲓⲛⲧⲁⲟⲩⲟ ⲛ̀ⲣⲉⲙⲣⲏⲥ", "النطق الصعيدي", DataContainer.SahidicPronouncation);
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Ready, move up
                scrollView.fullScroll(View.FOCUS_UP);
            }
        });


        findViewById(R.id.acadimic_bohiric).setVisibility(View.GONE);
        findViewById(R.id.new_bohiric).setVisibility(View.GONE);
        findViewById(R.id.late_bohiric).setVisibility(View.GONE);
    }

    private void setLayoutContents(Context context, View view, String copticTitle, String title, List<PronounceModule> list) {
        if (list.size() == 0) {
            view.setVisibility(View.GONE);
            return;
        }
        TextView copticTitleText = view.findViewById(R.id.coptic_title);
        TextView translatedTitleText = view.findViewById(R.id.translated_title);

        copticTitleText.setText(copticTitle);
        translatedTitleText.setText(title);

        ProunouncationAdapter adapter = new ProunouncationAdapter(context, list);
        ListView listView = view.findViewById(R.id.general_list);
        listView.setAdapter(adapter);
        listView.setEnabled(false);
        view.findViewById(R.id.expand_image).setVisibility(View.GONE);
    }


}
