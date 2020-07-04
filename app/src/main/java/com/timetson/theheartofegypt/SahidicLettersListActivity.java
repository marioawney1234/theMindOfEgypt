package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.timetson.theheartofegypt.Helper.LettersSqlHelper;
import com.timetson.theheartofegypt.modules.DataContainer;
import com.timetson.theheartofegypt.modules.LetterModule;

import java.util.List;

public class SahidicLettersListActivity extends AppCompatActivity {

    ////////// assign views ////////
    List<LetterModule> moduleList;
    private TextView textViewTitleCoptic;
    private TextView textViewTitle;
    private Button buttonAboutDialect;
    ////////////////////////////////

    ////////////for Language Setting///////////////////
    private String LanguageCode = PreferenceManager.getDefaultSharedPreferences(TheHeartOfEgypt.getAppContext()).getString("lang_code", "ar");

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    private void setLanguage(Context context, String languageCode) {
        if (languageCode.equals("en")) {
            textViewTitle.setText(context.getResources().getString(R.string.letter_list_title_sahidic_en));
            buttonAboutDialect.setText(context.getResources().getString(R.string.letter_list_about_sahidic_en));
        } else if (languageCode.equals("ar")) {
            textViewTitle.setText(context.getResources().getString(R.string.letter_list_title_sahidic_ar));
            buttonAboutDialect.setText(context.getResources().getString(R.string.letter_list_about_sahidic_ar));
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
        buttonAboutDialect=findViewById(R.id.letter_list_button_about_dialict);
        ///////////////////////////////

        ///////set language///////////
        setLanguage(context, LanguageCode);
        //////////////////////////

        // Ads code
        DataContainer.AdmobLoad(this,context,R.id.adView);
        // end Ads code


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

        buttonAboutDialect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, SahidicAboutActivity.class);
                startActivity(intent);
            }
        });
    }
}
